package com.qaverse.smart.Retry;

import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;

import com.qaverse.smart.Analytics.AnalyticsManager;
import com.qaverse.smart.Analytics.StepResult;
import com.qaverse.smart.Failure.FailureClassifier;
import com.qaverse.smart.Failure.FailureManager;
import com.qaverse.smart.Failure.FailureSeverity;
import com.qaverse.smart.Failure.FailureType;
import com.qaverse.smart.Logging.LoggerManager;


public class RetryExecutor {

	private static final long BASE_DELAY = 500;

	public static <T> T execute(WebDriver driver, String stepName, FailureSeverity severity, Supplier<T> action) {

		int attempt = 0;

		while (true) {

			long start = System.currentTimeMillis(); // ✅ START TIME

			try {
				attempt++;

				log(stepName, attempt);

				T resultValue = action.get();

// ================= ✅ SUCCESS =================
				long duration = System.currentTimeMillis() - start;

				StepResult result = new StepResult();
				result.setStepName(stepName);
				result.setStatus("PASS");
				result.setAttempt(attempt);
				result.setDuration(duration);
				result.setTimestamp(System.currentTimeMillis());

				AnalyticsManager.record(result);

				return resultValue;

			} catch (Exception e) {

				FailureType type = FailureClassifier.classify(e);

				int maxRetry = RetryDecisionManager.getMaxRetries(type);

				boolean shouldRetry = RetryDecisionManager.shouldRetry(type, severity) && attempt < maxRetry;

// ================= 🔁 RETRY =================
				StepResult retry = new StepResult();
				retry.setStepName(stepName);
				retry.setStatus("RETRY");
				retry.setAttempt(attempt);
				retry.setFailureType(type.name());

				AnalyticsManager.record(retry);

				logRetry(stepName, attempt, type, shouldRetry);

				if (!shouldRetry) {

					// ================= ❌ FINAL FAILURE =================
					long duration = System.currentTimeMillis() - start;

					StepResult fail = new StepResult();
					fail.setStepName(stepName);
					fail.setStatus("FAIL");
					fail.setAttempt(attempt);
					fail.setFailureType(type.name());
					fail.setDuration(duration);

					AnalyticsManager.record(fail);

					FailureManager.fail(driver, stepName, type, severity, "Failed after " + attempt + " attempts", e);
				}

				sleep(attempt);
			}
		}
	}

	// ===== HELPERS =====

	private static void sleep(int attempt) {
		try {
			Thread.sleep(BASE_DELAY * attempt);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	private static void log(String step, int attempt) {
	    LoggerManager.info("Attempt " + attempt + " → " + step);
	}

	private static void logRetry(String step, int attempt, FailureType type, boolean retry) {
	    LoggerManager.warn("Retry → " + step +
	            " | Attempt: " + attempt +
	            " | Type: " + type +
	            " | Retry: " + retry);
	}
}
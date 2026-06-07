package com.qaverse.smart.core.engine;

import com.qaverse.smart.core.context.ExecutionContext;
import com.qaverse.smart.core.event.ActionFailedEvent;
import com.qaverse.smart.core.event.ActionRecoveredEvent;
import com.qaverse.smart.core.event.ActionRetryEvent;
import com.qaverse.smart.core.event.ActionStartedEvent;
import com.qaverse.smart.core.event.ActionSucceededEvent;
import com.qaverse.smart.core.event.EventConstants;
import com.qaverse.smart.core.event.EventPublisher;
import com.qaverse.smart.core.failure.FailureContext;
import com.qaverse.smart.core.failure.FailureFactory;
import com.qaverse.smart.core.model.ActionRequest;
import com.qaverse.smart.core.model.ActionResult;
import com.qaverse.smart.core.recovery.RecoveryContext;
import com.qaverse.smart.core.recovery.RecoveryDecision;
import com.qaverse.smart.core.recovery.RecoveryDecisionType;
import com.qaverse.smart.core.recovery.RecoveryEngine;
import com.qaverse.smart.core.recovery.RecoveryMapping;
import com.qaverse.smart.core.registry.ActionRegistry;
import com.qaverse.smart.core.retry.RetryConstants;
import com.qaverse.smart.core.retry.RetryContext;
import com.qaverse.smart.core.retry.RetryDecision;
import com.qaverse.smart.core.retry.RetryDecisionType;
import com.qaverse.smart.core.retry.RetryEvaluator;

public final class RetryEngine {

	private final RetryEvaluator retryEvaluator = new RetryEvaluator();

	public ActionResult execute(ActionRequest request, ExecutionContext context, ObservationEngine observationEngine,
			ValidationEngine validationEngine, RecoveryEngine recoveryEngine) {

		EventPublisher.publish(ActionStartedEvent.builder().actionType(request.getActionType())
				.message(EventConstants.ACTION_STARTED).build());

		int attempt = 0;

		Exception lastException = null;

		while (true) {

			attempt++;

			if (attempt > RetryConstants.MAX_ALLOWED_ATTEMPTS) {
				throw new RuntimeException(EngineMessages.RETRY_SAFETY_LIMIT_EXCEEDED, lastException);
			}

			try {

				observationEngine.observe(context);

				ActionResult result = ActionRegistry.getInstance().get(request.getActionType()).execute(request);

				validationEngine.validate(request, context);

				EventPublisher.publish(ActionSucceededEvent.builder().actionType(request.getActionType())
						.message(EventConstants.ACTION_SUCCEEDED).build());

				return result;

			} catch (Exception ex) {
				
				lastException = ex;

				FailureContext failureContext = FailureFactory.create(ex);
				  
				RetryDecision retryDecision = retryEvaluator.evaluate(new RetryContext(failureContext, attempt));

				if (retryDecision.getDecisionType() != RetryDecisionType.FAIL_FAST) {

					ActionRetryEvent.Builder builder = ActionRetryEvent.builder();

					builder.actionType(request.getActionType());

					builder.message(EventConstants.ACTION_RETRY);

					builder.attempt(attempt);

					builder.failureType(failureContext.getFailureType());

					EventPublisher.publish(builder.build());
				}

				if (retryDecision.getDecisionType() == RetryDecisionType.FAIL_FAST) {

					break;
				}

				if (retryDecision.getDecisionType() == RetryDecisionType.RECOVER_THEN_RETRY) {

					RecoveryDecision recoveryDecision = recoveryEngine
							.recover(new RecoveryContext(failureContext, context));

					if (recoveryDecision.getDecisionType() == RecoveryDecisionType.RECOVERED) {

						ActionRecoveredEvent.Builder builder = ActionRecoveredEvent.builder();

						builder.actionType(request.getActionType());

						builder.message(EventConstants.ACTION_RECOVERED);

						builder.recoveryType(RecoveryMapping.get(failureContext.getFailureType()));

						EventPublisher.publish(builder.build());

						continue;
					}

					break;
				}

				if (retryDecision.getDecisionType() == RetryDecisionType.RETRY) {

					continue;
				}
			}
		}

		if (lastException != null) {

			FailureContext failureContext = FailureFactory.create(lastException);

			ActionFailedEvent.Builder builder = ActionFailedEvent.builder();

			builder.actionType(request.getActionType());

			builder.message(EventConstants.ACTION_FAILED);

			builder.failureContext(failureContext);

			EventPublisher.publish(builder.build());
		}
		throw new RuntimeException(EngineMessages.EXECUTION_FAILED + attempt + EngineMessages.ATTEMPTS, lastException);
	}
}
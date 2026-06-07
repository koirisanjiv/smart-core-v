package com.qaverse.smart.core.recovery;

import org.openqa.selenium.WebDriver;

public class RefreshPageRecovery
        implements BaseRecoveryStrategy {

    @Override
    public RecoveryType getType() {
        return RecoveryType.REFRESH_PAGE;
    }

    @Override
    public RecoveryDecision recover(
            RecoveryContext context) {

        try {

            WebDriver driver =
                    context.getExecutionContext()
                           .getDriver();

            driver.navigate().refresh();

            return new RecoveryDecision(
                    RecoveryDecisionType.RECOVERED,
                    "Page refreshed"
            );

        } catch (Exception ex) {

            return new RecoveryDecision(
                    RecoveryDecisionType.NOT_RECOVERED,
                    ex.getMessage()
            );
        }
    }
}
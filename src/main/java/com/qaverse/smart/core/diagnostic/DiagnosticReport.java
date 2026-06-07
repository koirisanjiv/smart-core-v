package com.qaverse.smart.core.diagnostic;

import java.util.Collections;
import java.util.List;

public final class DiagnosticReport {

    private final List<DiagnosticResult> diagnostics;

    public DiagnosticReport(
            List<DiagnosticResult> diagnostics) {

        this.diagnostics =
                Collections.unmodifiableList(
                        diagnostics
                );
    }

    public List<DiagnosticResult> getDiagnostics() {
        return diagnostics;
    }

    public boolean hasCriticalIssue() {

        return diagnostics.stream()
                .anyMatch(
                        d -> d.getSeverity()
                                == DiagnosticSeverity.CRITICAL
                );
    }
}
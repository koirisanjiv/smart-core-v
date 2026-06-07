package com.qaverse.smart.core.diagnostic;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class DiagnosticReport {

    private final List<DiagnosticResult> diagnostics;

    public DiagnosticReport(
            List<DiagnosticResult> diagnostics) {

    	Objects.requireNonNull(
    	        diagnostics,
    	        DiagnosticMessages.DIAGNOSTIC_LIST_NULL
    	);
    	
        this.diagnostics =
                Collections.unmodifiableList(
                        diagnostics
                );
    }
    
    public boolean isEmpty() {

        return diagnostics.isEmpty();
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
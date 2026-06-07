package com.qaverse.smart.core.diagnostic;

public final class DiagnosticResult {

    private final boolean success;

    private final DiagnosticCode code;

    private final DiagnosticCategory category;

    private final DiagnosticSeverity severity;

    private final String message;

    public DiagnosticResult(
            boolean success,
            DiagnosticCode code,
            DiagnosticCategory category,
            DiagnosticSeverity severity,
            String message) {

        this.success = success;
        this.code = code;
        this.category = category;
        this.severity = severity;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public DiagnosticCode getCode() {
        return code;
    }

    public DiagnosticCategory getCategory() {
        return category;
    }

    public DiagnosticSeverity getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }
}
package com.qaverse.smart.core.diagnostic;

import java.util.Objects;

public final class DiagnosticResult {

	private final boolean matched;

	private final DiagnosticCode code;

	private final DiagnosticCategory category;

	private final DiagnosticSeverity severity;

	private final String message;

	public DiagnosticResult(boolean matched, DiagnosticCode code, DiagnosticCategory category,
			DiagnosticSeverity severity, String message) {

		Objects.requireNonNull(code, DiagnosticMessages.DIAGNOSTIC_CODE_NULL);

		Objects.requireNonNull(category, DiagnosticMessages.DIAGNOSTIC_CATEGORY_NULL);

		Objects.requireNonNull(severity, DiagnosticMessages.DIAGNOSTIC_SEVERITY_NULL);

		this.matched = matched;
		this.code = code;
		this.category = category;
		this.severity = severity;
		this.message = message;
	}

	public boolean isMatched(){
		return matched;
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
package com.qaverse.smart.core.diagnostic;

public interface DiagnosticAnalyzer {

    DiagnosticResult analyze(
            DiagnosticContext context);
}
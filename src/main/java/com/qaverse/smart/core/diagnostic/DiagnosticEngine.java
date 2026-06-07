package com.qaverse.smart.core.diagnostic;

import java.util.ArrayList;
import java.util.List;

public final class DiagnosticEngine {

    public DiagnosticReport diagnose(
            DiagnosticContext context) {

        List<DiagnosticResult> results =
                new ArrayList<>();

        for (DiagnosticAnalyzer analyzer :
                DiagnosticRegistry.getAll()) {

            try {

                DiagnosticResult result =
                        analyzer.analyze(
                                context
                        );

                if (result != null) {
                    results.add(result);
                }

            } catch (Exception ignored) {
            }
        }

        return new DiagnosticReport(
                results
        );
    }
}
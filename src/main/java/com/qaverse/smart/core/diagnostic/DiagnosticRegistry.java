package com.qaverse.smart.core.diagnostic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DiagnosticRegistry {

    private static final List<DiagnosticAnalyzer>
            ANALYZERS =
            new ArrayList<>();

    private DiagnosticRegistry() {
    }

    public static void register(
            DiagnosticAnalyzer analyzer) {

        ANALYZERS.add(analyzer);
    }

    public static List<DiagnosticAnalyzer> getAll() {

        return Collections.unmodifiableList(
                ANALYZERS
        );
    }
}
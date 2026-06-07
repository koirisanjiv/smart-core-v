package com.qaverse.smart.core.diagnostic;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class DiagnosticRegistry {

	private static final List<DiagnosticAnalyzer>
    ANALYZERS =
    new CopyOnWriteArrayList<>();

    private DiagnosticRegistry() {
    }

    public static void register(
            DiagnosticAnalyzer analyzer) {

        if (analyzer == null) {
            return;
        }

        ANALYZERS.add(analyzer);
    }

    public static List<DiagnosticAnalyzer> getAll() {

        return Collections.unmodifiableList(
                ANALYZERS
        );
    }
}
package com.qaverse.smart.core.recovery;

import java.util.EnumMap;
import java.util.Map;

import com.qaverse.smart.core.failure.FailureType;

public final class RecoveryMapping {

    private static final Map<FailureType, RecoveryType>
            MAPPINGS =
            new EnumMap<>(FailureType.class);

    static {

        MAPPINGS.put(
                FailureType.STALE_ELEMENT,
                RecoveryType.RELOCATE_ELEMENT
        );

        MAPPINGS.put(
                FailureType.CLICK_INTERCEPTED,
                RecoveryType.SCROLL_TO_ELEMENT
        );

        MAPPINGS.put(
                FailureType.TIMEOUT,
                RecoveryType.WAIT_FOR_DOM
        );

        MAPPINGS.put(
                FailureType.ELEMENT_NOT_VISIBLE,
                RecoveryType.SCROLL_TO_ELEMENT
        );
        
        MAPPINGS.put(
                FailureType.ELEMENT_NOT_INTERACTABLE,
                RecoveryType.SCROLL_TO_ELEMENT
        );

        MAPPINGS.put(
                FailureType.LOADER_ACTIVE,
                RecoveryType.WAIT_FOR_LOADER
        );

        MAPPINGS.put(
                FailureType.DOM_REFRESH,
                RecoveryType.WAIT_FOR_DOM
        );

        MAPPINGS.put(
                FailureType.FRAME_NOT_AVAILABLE,
                RecoveryType.SWITCH_FRAME
        );
    }

    private RecoveryMapping() {
    }

    public static RecoveryType get(
            FailureType failureType) {

        return MAPPINGS.getOrDefault(
                failureType,
                RecoveryType.NONE
        );
    }
}
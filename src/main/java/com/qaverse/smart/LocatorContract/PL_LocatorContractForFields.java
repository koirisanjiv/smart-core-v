package com.qaverse.smart.LocatorContract;

import org.openqa.selenium.By;

import com.qaverse.smart.Locator.FieldType;

public interface PL_LocatorContractForFields {

    // 🔥 CORE (MANDATORY)
    FieldType getType();

    // ===== COMMON =====
    default By field() { return null; }

    default By button() { return null; }

    default String buttonStringFormat() { return null; }

    default String fieldStringFormat() { return null; }

    default By text() { return null; }

    default By list() { return null; }

    default By list1() { return null; }

    default By list2() { return null; }

    default String checkbox() { return null; }

    default String checkboxStringFormat() { return null; }

    // ===== SPECIALIZED CONTRACTS =====

    interface FieldLocatableForDropdown {
        By button();
        By textAlreadySelectedText();
        By fieldSearchBox();
        By listOption();
    }

    interface FieldLocatableForCheckboxDropdownWithAllOption {
        By button();
        By textAlreadySelectedText();
        String optionStringFormat();
    }

    interface FieldLocatableForCheckboxDropdownMultiSelectorWithoutAllOption {
        By button();
        By listAlreadySelected();
        By fieldSearchBox();
        By textAlreadySelectedText();
        String optionStringFormat();
    }

    interface FieldLocatableForDate {
        By buttonDatePicker();
        By buttonDatePickerNext();
        By buttonDatePickerBack();
        By optionsDatePickerDate();
        By optionsDatePickerMonth();
        By optionsDatePickerYear();
    }

    interface FieldLocatableForDateWhenMonthYearNotInListAndHaveDifferentLocator {
        By buttonDatePicker();
        By buttonDatePickerNext();
        By buttonDatePickerBack();
        By optionsDatePickerDate();
        By optionsDatePickerMonth();
        By optionsDatePickerYear();
    }

    interface FieldLocatableForSelectAddressOnMap {
        By field();
        By list();
    }

    interface FieldLocatableForSelectAddressOnMapAdavnce {
        String field();
        String list();
    }

    interface FieldLocatableForHourAndMinuteFromSelectTag {
        By list1();
        By list2();
    }
}
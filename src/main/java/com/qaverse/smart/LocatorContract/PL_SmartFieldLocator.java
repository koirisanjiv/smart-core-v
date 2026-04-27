package com.qaverse.smart.LocatorContract;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.openqa.selenium.By;

import com.qaverse.smart.Locator.FieldType;
import com.qaverse.smart.LocatorContract.PL_LocatorContractForFields.FieldLocatableForDate;
import com.qaverse.smart.LocatorContract.PL_LocatorContractForFields.FieldLocatableForDropdown;
import com.qaverse.smart.LocatorContract.PL_LocatorContractForFields.FieldLocatableForHourAndMinuteFromSelectTag;
import com.qaverse.smart.LocatorContract.PL_LocatorContractForFields.FieldLocatableForSelectAddressOnMap;

public interface PL_SmartFieldLocator {

    // ================= COMMON =================

    static String buildStartsWithCondition(String... ids) {
        return Arrays.stream(ids)
                .map(id -> "starts-with(@id,'" + id + "')")
                .collect(Collectors.joining(" or "));
    }

    static String buildEqualsCondition(String... names) {
        return Arrays.stream(names)
                .map(name -> "@name='" + name + "'")
                .collect(Collectors.joining(" or "));
    }

    // ================= INPUT =================

    class SetDataInSimpleTextInputField implements PL_LocatorContractForFields {

        private final String id;

        public SetDataInSimpleTextInputField(String id) {
            this.id = id;
        }

        @Override
        public By field() {
            return By.id(id);
        }

        @Override
        public FieldType getType() {
            return FieldType.INPUT;
        }
    }

    class SetDataInSimpleTextareaField implements PL_LocatorContractForFields {

        private final String id;

        public SetDataInSimpleTextareaField(String id) {
            this.id = id;
        }

        @Override
        public By field() {
            return By.xpath("//textarea[@id='" + id + "']");
        }

        @Override
        public FieldType getType() {
            return FieldType.INPUT;
        }
    }

    // ================= BUTTON =================

    class ClickOnElement implements PL_LocatorContractForFields {

        private final String xpath;

        public ClickOnElement(String xpath) {
            this.xpath = xpath;
        }

        @Override
        public By button() {
            return By.xpath(xpath);
        }

        @Override
        public FieldType getType() {
            return FieldType.BUTTON;
        }
    }

    // ================= RADIO =================

    class SelectRadioButtonStringFormat implements PL_LocatorContractForFields {

        private final String format;

        public SelectRadioButtonStringFormat(String format) {
            this.format = format;
        }

        @Override
        public String buttonStringFormat() {
            return format;
        }

        @Override
        public FieldType getType() {
            return FieldType.RADIO;
        }
    }

    class SelectRadioButtonMultiSelector implements PL_LocatorContractForFields {

        private final String[] names;

        public SelectRadioButtonMultiSelector(String... names) {
            this.names = names;
        }

        @Override
        public String buttonStringFormat() {
            return "//label[text()='%s']/preceding-sibling::input[" +
                    buildEqualsCondition(names) + "][1]";
        }

        @Override
        public FieldType getType() {
            return FieldType.RADIO;
        }
    }

    // ================= CHECKBOX =================

    class SelectCheckboxButton implements PL_LocatorContractForFields {

        private final String xpath;

        public SelectCheckboxButton(String xpath) {
            this.xpath = xpath;
        }

        @Override
        public String checkbox() {
            return xpath;
        }

        @Override
        public FieldType getType() {
            return FieldType.CHECKBOX;
        }
    }

    class SelectCheckboxButtonStringFormat implements PL_LocatorContractForFields {

        private final String format;

        public SelectCheckboxButtonStringFormat(String format) {
            this.format = format;
        }

        @Override
        public String checkboxStringFormat() {
            return format;
        }

        @Override
        public FieldType getType() {
            return FieldType.CHECKBOX;
        }
    }

    class SelectCheckboxMultiSelector implements PL_LocatorContractForFields {

        private final String[] names;

        public SelectCheckboxMultiSelector(String... names) {
            this.names = names;
        }

        @Override
        public String checkboxStringFormat() {
            return "//label[text()='%s']/preceding-sibling::input[" +
                    buildEqualsCondition(names) + "][1]";
        }

        @Override
        public FieldType getType() {
            return FieldType.CHECKBOX;
        }
    }

    // ================= DROPDOWN =================

    class SelectDropdownFromSelectTag implements PL_LocatorContractForFields {

        private final String name;

        public SelectDropdownFromSelectTag(String name) {
            this.name = name;
        }

        @Override
        public By list() {
            return By.name(name);
        }

        @Override
        public FieldType getType() {
            return FieldType.DROPDOWN;
        }
    }

    class SingleSelectionDropdownByClickSearchSelect implements FieldLocatableForDropdown, PL_LocatorContractForFields {

        private final String[] ids;

        public SingleSelectionDropdownByClickSearchSelect(String... ids) {
            this.ids = ids;
        }

        private String condition() {
            return buildStartsWithCondition(ids);
        }

        @Override
        public By button() {
            return By.xpath("//div[" + condition() + "]");
        }

        @Override
        public By textAlreadySelectedText() {
            return By.xpath("//div[" + condition() + "]//span");
        }

        @Override
        public By fieldSearchBox() {
            return By.xpath("//div[" + condition() + "]//input");
        }

        @Override
        public By listOption() {
            return By.xpath("//div[" + condition() + "]//li[contains(@class,'active-result')]");
        }

        @Override
        public FieldType getType() {
            return FieldType.DROPDOWN;
        }
    }

    // ================= DATE =================

    class DatePickerCalender implements FieldLocatableForDate, PL_LocatorContractForFields {

        private final String id;

        public DatePickerCalender(String id) {
            this.id = id;
        }

        @Override
        public By buttonDatePicker() {
            return By.id(id);
        }

        @Override
        public By buttonDatePickerNext() {
            return By.xpath("//span[contains(@class,'triangle-e')]");
        }

        @Override
        public By buttonDatePickerBack() {
            return By.xpath("//span[contains(@class,'triangle-w')]");
        }

        @Override
        public By optionsDatePickerDate() {
            return By.xpath("//td[@data-handler='selectDay']//a");
        }

        @Override
        public By optionsDatePickerMonth() {
            return By.xpath("//select[@class='ui-datepicker-month']");
        }

        @Override
        public By optionsDatePickerYear() {
            return By.xpath("//select[@class='ui-datepicker-year']");
        }

        @Override
        public FieldType getType() {
            return FieldType.DATE;
        }
    }

    // ================= MAP =================

    class SelectAddressOnMap implements FieldLocatableForSelectAddressOnMap, PL_LocatorContractForFields {

        private final String fieldXpath;
        private final String listXpath;

        public SelectAddressOnMap(String fieldXpath, String listXpath) {
            this.fieldXpath = fieldXpath;
            this.listXpath = listXpath;
        }

        @Override
        public By field() {
            return By.xpath(fieldXpath);
        }

        @Override
        public By list() {
            return By.xpath(listXpath);
        }

        @Override
        public FieldType getType() {
            return FieldType.MAP;
        }
    }

    // ================= TIME =================

    class SelectHourAndMinuteFromSelectTag implements FieldLocatableForHourAndMinuteFromSelectTag, PL_LocatorContractForFields {

        private final String h;
        private final String m;

        public SelectHourAndMinuteFromSelectTag(String h, String m) {
            this.h = h;
            this.m = m;
        }

        @Override
        public By list1() {
            return By.xpath(h);
        }

        @Override
        public By list2() {
            return By.xpath(m);
        }

        @Override
        public FieldType getType() {
            return FieldType.TIME;
        }
    }
}
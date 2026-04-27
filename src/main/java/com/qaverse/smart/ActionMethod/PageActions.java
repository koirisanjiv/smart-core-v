package com.qaverse.smart.ActionMethod;

import com.qaverse.smart.Wait.WaitManager;

public class PageActions {

	private <T extends MyActions> ThreadLocal<T> threadLocal(Class<T> clazz) {
	    return ThreadLocal.withInitial(() -> ActionFactory.getAction(clazz));
	}

    private final ThreadLocal<Click> click = threadLocal(Click.class);
    private final ThreadLocal<AlertManager> alert = threadLocal(AlertManager.class);
    private final ThreadLocal<CheckboxButton> checkboxButton = threadLocal(CheckboxButton.class);
    private final ThreadLocal<CheckboxDropdown> checkboxDropdown = threadLocal(CheckboxDropdown.class);
    private final ThreadLocal<ClickSearchSingleSelectDropdown> clickSearchDropdown = threadLocal(ClickSearchSingleSelectDropdown.class);
    private final ThreadLocal<DatePicker> datePicker = threadLocal(DatePicker.class);
    private final ThreadLocal<DragAndDrop> dragAndDrop = threadLocal(DragAndDrop.class);
    private final ThreadLocal<FileUpload> fileUpload = threadLocal(FileUpload.class);
    private final ThreadLocal<Hover> hover = threadLocal(Hover.class);
    private final ThreadLocal<Iframes> iframes = threadLocal(Iframes.class);
    private final ThreadLocal<Loader> loader = threadLocal(Loader.class);
    private final ThreadLocal<PopupAction> popup = threadLocal(PopupAction.class);
    private final ThreadLocal<RadioButton> radioButton = threadLocal(RadioButton.class);
    private final ThreadLocal<SelectClassDropdown> selectDropdown = threadLocal(SelectClassDropdown.class);
    private final ThreadLocal<TabSwitch> tabSwitch = threadLocal(TabSwitch.class);
    private final ThreadLocal<TextAreaInputField> textArea = threadLocal(TextAreaInputField.class);
    private final ThreadLocal<TextInputField> inputField = threadLocal(TextInputField.class);
    private final ThreadLocal<ToastMessage> toast = threadLocal(ToastMessage.class);
    private final ThreadLocal<Text> text = threadLocal(Text.class);
    private final ThreadLocal<WaitManager> wait = threadLocal(WaitManager.class);

    public Click getClick() { return click.get(); }
    public AlertManager getAlert() { return alert.get(); }
    public CheckboxButton getCheckboxButton() { return checkboxButton.get(); }
    public CheckboxDropdown getCheckboxDropdown() { return checkboxDropdown.get(); }
    public ClickSearchSingleSelectDropdown getClickSearchDropdown() { return clickSearchDropdown.get(); }
    public DatePicker getDatePicker() { return datePicker.get(); }
    public DragAndDrop getDragAndDrop() { return dragAndDrop.get(); }
    public FileUpload getFileUpload() { return fileUpload.get(); }
    public Hover getHover() { return hover.get(); }
    public Iframes getIframes() { return iframes.get(); }
    public Loader getLoader() { return loader.get(); }
    public PopupAction getPopup() { return popup.get(); }
    public RadioButton getRadioButton() { return radioButton.get(); }
    public SelectClassDropdown getSelectDropdown() { return selectDropdown.get(); }
    public TabSwitch getTabSwitch() { return tabSwitch.get(); }
    public TextAreaInputField getTextArea() { return textArea.get(); }
    public TextInputField getInputField() { return inputField.get(); }
    public ToastMessage getToast() { return toast.get(); }
    public Text getText() { return text.get(); }
    public WaitManager getWait() { return wait.get(); }
}
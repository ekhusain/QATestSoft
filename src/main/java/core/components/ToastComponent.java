package core.components;

import com.microsoft.playwright.Page;

import core.base.BaseComponent;

public class ToastComponent extends BaseComponent {

    public ToastComponent(Page page) {
        super(page, ".oxd-toast, .toast-success, .toast-error");
    }

    public String getToastMessage() {
        return rootLocator.innerText().trim();
    }

    public boolean isSuccess() {
        return rootLocator.isVisible() && getToastMessage().toLowerCase().contains("success");
    }
}
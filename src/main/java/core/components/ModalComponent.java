package core.components;

import com.microsoft.playwright.Page;

import core.base.BaseComponent;

public class ModalComponent extends BaseComponent {

    public ModalComponent(Page page, String modalSelector) {
        super(page, modalSelector);
    }

    public String getTitle() {
        return rootLocator.locator(".modal-title, .oxd-text--dialog-title").innerText();
    }

    public void confirm() {
        rootLocator.locator("button:has-text('Yes'), button:has-text('Confirm'), .oxd-button--label-danger").click();
    }

    public void cancel() {
        rootLocator.locator("button:has-text('No'), button:has-text('Cancel')").click();
    }
}
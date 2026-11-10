package core.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import core.base.BaseComponent;

public class DropdownComponent extends BaseComponent {

    public DropdownComponent(Page page, String dropdownSelector) {
        super(page, dropdownSelector);
    }

    /**
     * Выбор опции по тексту для кастомных списков (например, .oxd-select-wrapper)
     */
    public void selectCustomOption(String optionText) {
        rootLocator.click(); // Открываем список
        page.locator(".oxd-select-option, [role='option']")
                .filter(new Locator.FilterOptions().setHasText(optionText))
                .first()
                .click();
    }

    /**
     * Выбор опции для стандартного HTML-тега <select>
     */
    public void selectNativeOptionByText(String optionText) {
        rootLocator.selectOption(new com.microsoft.playwright.options.SelectOption().setLabel(optionText));
    }
}
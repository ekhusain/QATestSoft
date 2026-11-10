package core.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import core.base.BaseComponent;

public class TableComponent extends BaseComponent {
    private final String rowSelector;

    public TableComponent(Page page, String rootTableSelector, String rowSelector) {
        super(page, rootTableSelector);
        this.rowSelector = rowSelector;
    }

    public TableComponent(Page page, String rowSelector) {
        super(page, page.locator("body"));
        this.rowSelector = rowSelector;
    }

    /**
     * Поиск строки, содержащей заданный текст
     */
    public Locator getRowByText(String text) {
        return rootLocator.locator(rowSelector)
                .filter(new Locator.FilterOptions().setHasText(text))
                .first();
    }

    /**
     * Клик по действию внутри найденной строки (например, по иконке корзины или карандаша)
     */
    public void clickRowAction(String rowText, String actionButtonSelector) {
        Locator row = getRowByText(rowText);
        row.locator(actionButtonSelector).click();
    }

    /**
     * Проверка присутствия текста в таблице
     */
    public boolean isRowPresent(String text) {
        return getRowByText(text).isVisible();
    }

    /**
     * Количество отображаемых строк
     */
    public int getRowCount() {
        return rootLocator.locator(rowSelector).count();
    }
}
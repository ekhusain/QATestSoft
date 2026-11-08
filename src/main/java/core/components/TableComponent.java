package core.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class TableComponent {
    private final Page page;
    private final String rowSelector;

    public TableComponent(Page page, String rowSelector) {
        this.page = page;
        this.rowSelector = rowSelector;
    }

    /**
     * Поиск конкретной строки таблицы по тексту (Username, ID, E-mail и т.д.)
     */
    public Locator getRowByText(String text) {
        return page.locator(rowSelector)
                   .filter(new Locator.FilterOptions().setHasText(text));
    }

    /**
     * Клик по кнопке действия (например, Иконка удаления / редактирования) внутри найденной строки
     */
    public void clickRowAction(String rowText, String actionSelector) {
        getRowByText(rowText).locator(actionSelector).click();
    }
}
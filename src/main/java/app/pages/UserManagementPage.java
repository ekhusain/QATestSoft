package app.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import core.components.TableComponent;

public class UserManagementPage {
    private final Page page;
    private final TableComponent userTable;

    // Локатор бокового меню с явным указанием класса меню .oxd-main-menu-item
    private final String adminMenuSelector = "//span[text()='Admin']";
    private final String usernameSearchInput = ".oxd-input-group:has-text('Username') input";
    private final String searchButton = "button[type='submit']";
    private final String tableCardSelector = ".oxd-table-card";
    private final String deleteIconSelector = "i.bi-trash";

    public UserManagementPage(Page page) {
        this.page = page;
        this.userTable = new TableComponent(page, tableCardSelector);
    }

    /**
     * Переход в модуль Admin через боковое меню
     */
    public void navigateToAdminModule() {
        // Вариант 1: Через семантический getByRole (стандарт Playwright)
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Admin")).click();
        
        // Альтернативный Вариант 2 (через уточненный CSS):
        // page.click(adminMenuSelector);
    }

    /**
     * Фильтрация таблицы пользователей по имени (Username)
     */
    public void filterByUsername(String username) {
        page.fill(usernameSearchInput, username);
        page.click(searchButton);
        page.waitForLoadState(); // Ожидание завершения AJAX-запроса фильтрации
    }

    /**
     * Проверка присутствия пользователя в результатах таблицы
     */
    public boolean isUserPresentInTable(String username) {
        return userTable.getRowByText(username).isVisible();
    }

    /**
     * Удаление пользователя из таблицы по имени
     */
    public void deleteUser(String username) {
        userTable.clickRowAction(username, deleteIconSelector);
    }
}
package app.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import core.base.BasePage;
import core.components.DropdownComponent;
import core.components.TableComponent;

public class UserManagementPage extends BasePage {

    // Инкапсулированные UI-компоненты
    private final TableComponent userTable;
    private final DropdownComponent userRoleDropdown;
    private final DropdownComponent statusDropdown;

    // Локаторы элементов формы поиска и управления
    private final String usernameInput = ".oxd-input-group:has-text('Username') input";
    private final String searchButton = "button[type='submit']";
    private final String resetButton = "button:has-text('Reset')";
    private final String addButton = "button:has-text('Add')";
    private final String deleteIconSelector = "i.bi-trash";
    private final String editIconSelector = "i.bi-pencil-fill";

    public UserManagementPage(Page page) {
        super(page);
        
        // Инициализируем универсальный компонент таблицы
        this.userTable = new TableComponent(page, ".oxd-table-body", ".oxd-table-card");
        
        // Инициализируем компоненты выпадающих списков фильтров
        this.userRoleDropdown = new DropdownComponent(page, ".oxd-input-group:has-text('User Role') .oxd-select-wrapper");
        this.statusDropdown = new DropdownComponent(page, ".oxd-input-group:has-text('Status') .oxd-select-wrapper");
    }

    /**
     * Переход в модуль Admin через боковое меню
     */
    public void navigateToAdminModule() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Admin")).click();
        waitForPageLoad();
    }

    /**
     * Фильтрация пользователей по имени (Username)
     */
    public void filterByUsername(String username) {
        fill(usernameInput, username);
        click(searchButton);
        waitForPageLoad();
    }

    /**
     * Фильтрация пользователей по роли (например, "Admin" или "ESS")
     */
    public void filterByRole(String roleName) {
        userRoleDropdown.selectCustomOption(roleName);
        click(searchButton);
        waitForPageLoad();
    }

    /**
     * Фильтрация пользователей по статусу (например, "Enabled" или "Disabled")
     */
    public void filterByStatus(String status) {
        statusDropdown.selectCustomOption(status);
        click(searchButton);
        waitForPageLoad();
    }

    /**
     * Проверка присутствия пользователя в результатах таблицы
     */
    public boolean isUserPresentInTable(String username) {
        return userTable.isRowPresent(username);
    }

    /**
     * Удаление пользователя из таблицы по имени
     */
    public void deleteUser(String username) {
        userTable.clickRowAction(username, deleteIconSelector);
    }

    /**
     * Переход к редактированию пользователя из таблицы
     */
    public void editUser(String username) {
        userTable.clickRowAction(username, editIconSelector);
    }

    /**
     * Нажатие на кнопку создания нового пользователя
     */
    public void clickAddUser() {
        click(addButton);
    }
}
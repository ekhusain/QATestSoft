package app.tests;

import core.base.BaseTest;
import app.pages.LoginPage;
import app.pages.UserManagementPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserWorkflowTest extends BaseTest {

    private static final String APP_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @Test
    @DisplayName("Поиск и валидация пользователя Admin в динамической таблице OrangeHRM")
    public void testSearchAndVerifyAdminUser() {
        // 1. Авторизация
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate(APP_URL);
        loginPage.login("Admin", "admin123");

        // 2. Переход в модуль Admin и поиск
        UserManagementPage userPage = new UserManagementPage(page);
        userPage.navigateToAdminModule();
        userPage.filterByUsername("Admin");

        // 3. Валидация через универсальный TableComponent
        boolean isPresent = userPage.isUserPresentInTable("Admin");
        assertTrue(isPresent, "User 'Admin' should be dispayed in the table after search");
    }
}
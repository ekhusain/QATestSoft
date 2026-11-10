package app.pages;

import com.microsoft.playwright.Page;

import core.base.BasePage;

public class LoginPage extends BasePage {

    private final String usernameInput = "input[name='username']";
    private final String passwordInput = "input[name='password']";
    private final String loginButton = "button[type='submit']";

    public LoginPage(Page page) {
        super(page);
    }

    public void login(String username, String password) {
        fill(usernameInput, username);
        fill(passwordInput, password);
        click(loginButton);
        waitForPageLoad();
    }
}
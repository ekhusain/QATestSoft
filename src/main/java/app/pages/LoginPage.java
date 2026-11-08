package app.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    private final String usernameInput = "input[name='username']";
    private final String passwordInput = "input[name='password']";
    private final String loginButton = "button[type='submit']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigate(String url) {
        page.navigate(url);
    }

    public void login(String username, String password) {
        page.fill(usernameInput, username);
        page.fill(passwordInput, password);
        page.click(loginButton);
    }
}
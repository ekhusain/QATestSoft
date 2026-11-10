package core.base;

import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public abstract class BasePage {
    protected final Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    /**
     * Переход по указанному URL
     */
    public void navigate(String url) {
        page.navigate(url);
        waitForPageLoad();
    }

    /**
     * Ожидание полной загрузки страницы (DOM + Network idle)
     */
    public void waitForPageLoad() {
        page.waitForLoadState();
    }

    /**
     * Безопасный клик по элементу с ожиданием видимости
     */
    public void click(String selector) {
        page.locator(selector).first().click();
    }

    /**
     * Заполнение текстового поля
     */
    public void fill(String selector, String text) {
        Locator element = page.locator(selector).first();
        element.fill("");
        element.fill(text);
    }

    /**
     * Получение текста элемента
     */
    public String getText(String selector) {
        return page.locator(selector).first().innerText().trim();
    }

    /**
     * Проверка видимости элемента
     */
    public boolean isVisible(String selector) {
        return page.locator(selector).first().isVisible();
    }

    /**
     * Явное ожидание появления элемента
     */
    public void waitForElement(String selector, double timeoutMs) {
        page.locator(selector).first().waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(timeoutMs));
    }

    /**
     * Сохранение скриншота страницы
     */
    public void takeScreenshot(String pathToFile) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(pathToFile)));
    }

    public String getTitle() {
        return page.title();
    }

    public String getUrl() {
        return page.url();
    }
}
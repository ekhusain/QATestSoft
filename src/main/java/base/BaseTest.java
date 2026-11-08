package core.base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

  @BeforeEach
public void setUp() {
    playwright = Playwright.create();

    // 1. Получаем настройки из командной строки (по умолчанию chromium и headless=true)
    String browserName = System.getProperty("browser", "chromium").toLowerCase();
    boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "true"));

    // 2. Добавляем флаги безопасности для стабильной работы в Linux / Codespaces
    BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
            .setHeadless(isHeadless)
            .setArgs(java.util.List.of(
                    "--no-sandbox",
                    "--disable-setuid-sandbox",
                    "--disable-dev-shm-usage"
            ));

    // 3. Выбор типа браузера в зависимости от параметра
    switch (browserName) {
        case "firefox":
            browser = playwright.firefox().launch(launchOptions);
            break;
        case "webkit":
            browser = playwright.webkit().launch(launchOptions);
            break;
        case "chromium":
        default:
            browser = playwright.chromium().launch(launchOptions);
            break;
    }

    context = browser.newContext();
    page = context.newPage();
}

    @AfterEach
    public void tearDown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
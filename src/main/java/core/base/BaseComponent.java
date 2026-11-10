package core.base;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class BaseComponent {
    protected final Page page;
    protected final Locator rootLocator;

    public BaseComponent(Page page, String rootSelector) {
        this.page = page;
        this.rootLocator = page.locator(rootSelector);
    }

    public BaseComponent(Page page, Locator rootLocator) {
        this.page = page;
        this.rootLocator = rootLocator;
    }

    public boolean isVisible() {
        return rootLocator.isVisible();
    }
}
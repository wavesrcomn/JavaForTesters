package addressbook.appmanager;

import org.openqa.selenium.*;

import java.io.File;

public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected WebElement find(By locator) {
        return wd.findElement(locator);
    }

    protected void click(By locator) {
        find(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = find(locator);
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                element.clear();
                element.sendKeys(text);
            }
        }
    }

    protected void attach(By locator, String url) {
        File file = new File(url);
        if (file != null) {
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    protected void submitAlert() {
        wd.switchTo().alert().accept();
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try{
            find(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }

    }
}

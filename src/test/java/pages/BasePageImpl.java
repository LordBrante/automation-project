package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Implementación base de la interface BasePage
 * Contiene la lógica común para TODAS las páginas
 */
public abstract class BasePageImpl implements pages.BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePageImpl(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Override
    public void navigateTo(String url) {
        driver.get(url);
    }

    @Override
    public void click(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    @Override
    public void sendKeys(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    @Override
    public String getText(WebElement element) {
        waitForElementToBeVisible(element);
        return element.getText();
    }

    @Override
    public boolean isDisplayed(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Override
    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }
}

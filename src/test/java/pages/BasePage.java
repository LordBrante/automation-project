package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Interface base que define el contrato para todas las Page Objects
 * Cualquier página que implemente esta interface DEBE tener estos métodos
 */
public interface BasePage {

    /**
     * Navega a una URL específica
     */
    void navigateTo(String url);

    /**
     * Click en un elemento con espera
     */
    void click(WebElement element);

    /**
     * Escribir texto en un elemento
     */
    void sendKeys(WebElement element, String text);

    /**
     * Obtener texto de un elemento
     */
    String getText(WebElement element);

    /**
     * Verificar si un elemento está visible
     */
    boolean isDisplayed(WebElement element);

    /**
     * Esperar a que un elemento sea clickeable
     */
    void waitForElementToBeClickable(WebElement element);

    /**
     * Esperar a que un elemento sea visible
     */
    void waitForElementToBeVisible(WebElement element);

    /**
     * Obtener el WebDriver (útil para navegación entre páginas)
     */
    WebDriver getDriver();
}
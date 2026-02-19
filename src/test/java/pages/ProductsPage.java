package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

/**
 * Page Object para la página de Productos
 */
public class ProductsPage extends BasePageImpl {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    // Lista de TODOS los botones "Add to cart"
    @FindBy(css = "button[class*='btn_primary btn_inventory']")
    private List<WebElement> addToCartButtons;

    // Botones específicos por producto
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackButton;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement addBikeLightButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public void addProductToCart(int productIndex) {
        if (productIndex < addToCartButtons.size()) {
            click(addToCartButtons.get(productIndex));
        }
    }

    public void addBackpackToCart() {
        click(addBackpackButton);
    }

    public void addBikeLightToCart() {
        click(addBikeLightButton);
    }

    public String getCartBadgeCount() {
        return getText(cartBadge);
    }

    public boolean isCartBadgeDisplayed() {
        return isDisplayed(cartBadge);
    }

    public CartPage goToCart() {
        click(cartIcon);
        return new CartPage(driver);
    }

    public void logout() {
        click(menuButton);
        click(logoutLink);
    }
}

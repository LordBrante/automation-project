package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class CartPage extends BasePageImpl {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        // Esperar a que la página del carrito cargue completamente
        wait.until(ExpectedConditions.textToBePresentInElement(pageTitle, "Your Cart"));
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public List<String> getProductNames() {
        return itemNames.stream()
                .map(this::getText)
                .toList();
    }

    public boolean isProductInCart(String productName) {
        return getProductNames().stream()
                .anyMatch(name -> name.equalsIgnoreCase(productName));
    }

    public ProductsPage continueShopping() {
        click(continueShoppingButton);
        return new ProductsPage(driver);
    }
}
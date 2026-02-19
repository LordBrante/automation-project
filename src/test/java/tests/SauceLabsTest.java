package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SauceLabsTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1, description = "Flujo completo: Login → Agregar productos → Validar carrito → Logout")
    public void testCompleteFlow() {
        // 1. Navegar a la página de login
        loginPage.navigateToLoginPage();

        // 2. Login con credenciales válidas
        productsPage = loginPage.login("standard_user", "secret_sauce");

        // 3. Validar que llegamos a la página de productos
        Assert.assertEquals(productsPage.getPageTitle(), "Products",
                "Debería mostrar 'Products' después del login");

        // 4. Agregar 2 productos al carrito
        productsPage.addBackpackToCart();
        productsPage.addBikeLightToCart();

        // 5. Validar que el badge muestra "2"
        Assert.assertTrue(productsPage.isCartBadgeDisplayed(),
                "El badge del carrito debería estar visible");
        Assert.assertEquals(productsPage.getCartBadgeCount(), "2",
                "El badge debería mostrar 2 productos");

        // 6. Ir al carrito
        cartPage = productsPage.goToCart();

        // 7. Validar que estamos en la página del carrito
        Assert.assertEquals(cartPage.getPageTitle(), "Your Cart",
                "Debería mostrar 'Your Cart'");

        // 8. Validar que hay exactamente 2 productos en el carrito
        Assert.assertEquals(cartPage.getCartItemCount(), 2,
                "Debería haber 2 productos en el carrito");

        // 9. Validar que los productos correctos están en el carrito
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"),
                "El carrito debería contener 'Sauce Labs Backpack'");
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Bike Light"),
                "El carrito debería contener 'Sauce Labs Bike Light'");

        // 10. Volver a productos y hacer logout
        productsPage = cartPage.continueShopping();
        productsPage.logout();

        // 11. Validar que volvimos a la página de login
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/",
                "Después del logout debería volver a la página de login");

        System.out.println("✓ Test completo ejecutado exitosamente");
    }

    @Test(priority = 2, description = "Login fallido con credenciales incorrectas")
    public void testInvalidLogin() {
        loginPage.navigateToLoginPage();
        loginPage.login("invalid_user", "invalid_pass");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Debería mostrar mensaje de error");

        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Username and password do not match"),
                "El mensaje de error debería indicar credenciales inválidas");

        System.out.println("✓ Validación de login inválido exitosa");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
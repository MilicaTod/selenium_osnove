package p05_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.time.Instant;

public class SwagLabsTests {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.manage().deleteAllCookies();
        driver.get("https://www.saucedemo.com/");
    }

    @Test(priority = 1,retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenUsernameIsMissing() {
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement error = driver.findElement(By.xpath("//h3"));
        Assert.assertEquals(error.getText(), "Epic sadface: Username is required");
    }

    @Test(priority = 2,retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenPasswordIsMissing() {
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement error = driver.findElement(By.xpath("//h3"));
        Assert.assertEquals(error.getText(), "Epic sadface: Password is required");
    }

    @Test(priority = 3,retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsdisplayedWhencredentialsAreWrong() {
        String username = "standard_user";
        String password = "invalidpassword";
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(username);
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        wait
                .withMessage("Error message did not show")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.className("error-message-container"),
                        "Epic sadface: Username and password do not match any user in this service"));
    }

    @Test(priority = 4,retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenUserIsLocked() {
        String username = "locked_out_user";
        String password = "secret_sauce";
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(username);
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement error = driver.findElement(By.xpath("//h3"));
        Assert.assertEquals(error.getText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(priority = 5,retryAnalyzer = SwagLabsRetry.class)
    public void verifySuccessfulLogin() throws InterruptedException {
        String username = "standard_user";
        String password = "secret_sauce";
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(username);
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement menu = driver.findElement(By.id("react-burger-menu-btn"));
        menu.click();
        wait
                .withMessage("Menu is not visible")
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".bm-menu"))));
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.id("logout_sidebar_link")).isDisplayed(), "Button Logout is not visible.");
        driver.findElement(By.id("logout_sidebar_link")).click();
        Assert.assertTrue(driver.findElement(By.id("login_button_container")).isDisplayed(), "Login form is not displayed.");
    }

    @Test(priority = 6,retryAnalyzer = SwagLabsRetry.class)
    public void addingProductToCart() {
        String username = "standard_user";
        String password = "secret_sauce";
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(username);
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"), "URL doesn't contain /inventory.html");
        driver.findElement(By.cssSelector(".inventory_list .inventory_item:nth-child(1) button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".inventory_list .inventory_item:nth-child(1) button"))
                .getText(), "Remove", "Button remove is not visible.");
        Assert.assertEquals(driver.findElement(By.cssSelector(".shopping_cart_badge")).getText(), "1",
                "Product is not added to the cart.");
    }

    @Test(priority = 7,retryAnalyzer = SwagLabsRetry.class)
    public void viewingProductDetails() {
        String username = "standard_user";
        String password = "secret_sauce";
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(username);
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"), "URL doesn't contain /inventory.html");
        driver.findElement(By.id("item_4_title_link")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".inventory_details_price")).isDisplayed(),
                "Price is not visible.");
        Assert.assertTrue(driver.findElement(By.cssSelector(".inventory_details_desc")).isDisplayed(),
                "Description is not visible.");
        Assert.assertTrue(driver.findElement(By.cssSelector(".btn_inventory")).isDisplayed(),
                "Add to cart button is not visible.");
    }

    @Test(priority = 8,retryAnalyzer = SwagLabsRetry.class)
    public void removingProductsFromCart() throws InterruptedException {
        String username = "standard_user";
        String password = "secret_sauce";

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(username);
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "URL doesn't contain /inventory.html");
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".shopping_cart_badge"))
                        .getText(), "1",
                "Product is not added to the cart.");
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        Assert.assertTrue(driver.findElement(By.id("item_4_title_link")).isDisplayed(),
                "The product is not in the cart.");
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        Thread.sleep(2000);
        wait.withMessage("The product is not deleted from the cart.")
                .until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".cart_item_label a"), 0));
    }

    @Test(priority = 9,retryAnalyzer = SwagLabsRetry.class)
    public void productCheckout() {
        String username = "standard_user";
        String password = "secret_sauce";
        String checkoutName = "Mara";
        String checkoutLastName = "Jankovic";
        String checkoutZip = "18000";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"), "URL doesn't contain /inventory.html");
        String title = driver.findElement(By.cssSelector(".inventory_item_label #item_4_title_link .inventory_item_name"))
                .getText();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".shopping_cart_badge")).getText(), "1",
                "Product is not added to the cart.");
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys(checkoutName);
        driver.findElement(By.id("last-name")).sendKeys(checkoutLastName);
        driver.findElement(By.id("postal-code")).sendKeys(checkoutZip);
        driver.findElement(By.id("continue")).click();
        Assert.assertEquals(title, driver.findElement(By.cssSelector(".cart_list .cart_item a#item_4_title_link .inventory_item_name")).getText(), "Incorrect title.");
        driver.findElement(By.id("finish")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".complete-header")).getText(), "Thank you for your order!", "Message for successful ordering is not visible.");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

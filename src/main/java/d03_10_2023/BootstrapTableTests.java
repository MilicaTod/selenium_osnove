package d03_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

//Kreirati BootstrapTableTests klasu koja ima:
//Base url: https://s.bootsnipp.com/iframe/K5yrx
public class BootstrapTableTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://s.bootsnipp.com/iframe/K5yrx";

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.manage().deleteAllCookies();
        driver.navigate().to(baseUrl);
        Assert.assertEquals(driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com",
                "User should be on Bootsnipp page");
    }
    //Test #1: Edit Row
    //Podaci:
    //First Name: ime polaznika
    //Last Name: prezime polaznika
    //Middle Name: srednje ime polanzika
    //Koraci:
    //Ucitati stranu /iframe/K5yrx
    //Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
    //Klik na Edit dugme prvog reda
    //Sacekati da dijalog za Editovanje bude vidljiv
    //Popuniti formu podacima.
    //Bice potrebno da pre unosa tekst pobrisete tekst koji vec postoji, za to se koristi metoda clear. Koristan link
    //Klik na Update dugme
    //Sacekati da dijalog za Editovanje postane nevidljiv
    //Verifikovati da se u First Name celiji prvog reda tabele javlja uneto ime
    //Verifikovati da se u Last Name celiji prvog reda tabele javlja uneto prezime
    //Verifikovati da se u Middle Name celiji prvog reda tabele javlja uneto srednje ime
    //Za sve validacije ispisati odgovarajuce poruke u slucaju greske
    @Test
    public void editRow() {
        String firstName = "Milica";
        String lastName = "Todorovic";
        String middleName = "Dragan";

        driver.findElement(By.xpath("//*[@id='d1']/td[5]/button"))
                .click();
        wait
                .withMessage("Edit dialog is not visible")
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='edit']/div")));
        WebElement inputFirstName = driver.findElement(By.id("fn"));
        inputFirstName
                .clear();
        inputFirstName
                .sendKeys(firstName);

        WebElement inputLastName = driver.findElement(By.id("ln"));
        inputLastName
                .clear();
        inputLastName
                .sendKeys(lastName);

        WebElement inputMiddleName = driver.findElement(By.id("mn"));
        inputMiddleName
                .clear();
        inputMiddleName
                .sendKeys(middleName);

        driver.findElement(By.id("up"))
                .click();
        wait
                .withMessage("Edit dialog is visible.")
                .until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[@id='edit']/div"))));

        Assert.assertEquals(inputFirstName.getAttribute("value"), firstName, "The entered value should match first name.");
        Assert.assertEquals(inputLastName.getAttribute("value"), lastName, "The entered value should match last name.");
        Assert.assertEquals(inputMiddleName.getAttribute("value"), middleName, "The entered value should match middle name.");
    }
    //Test #2: Delete Row
    //Podaci:
    //First Name: ime polaznika
    //Last Name: prezime polaznika
    //Middle Name: srednje ime polanzika
    //Koraci:
    //Ucitati stranu /iframe/K5yrx
    //Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
    //Klik na Delete dugme prvog reda
    //Sacekati da dijalog za brisanje bude vidljiv
    //Klik na Delete dugme iz dijaloga
    //Sacekati da dijalog za Editovanje postane nevidljiv
    //Verifikovati da je broj redova u tabeli za jedan manji
    //Za sve validacije ispisati odgovarajuce poruke u slucaju greske
    @Test
    public void deleteRow() throws InterruptedException {
        List<WebElement> rows = driver.findElements(By.cssSelector("tbody > tr"));

        Assert.assertEquals(driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com",
                "User should be on Bootsnipp page");

        driver.findElement(By.cssSelector(".delete.btn-danger")).click();

        wait
                .withMessage("Delete dialog should be visible")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#delete .modal-content")));


        driver.findElement(By.id("del")).click();

        wait
                .withMessage("Delete dialog should not be visible")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#delete .modal-content")));


        wait
                .withMessage("Row should be deleted.")
                .until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("tr > td:first-child"), rows.size()));
    }
// //Test #3: Take a Screenshot
//    //Koraci:
//    //Ucitati stranu  /iframe/K5yrx
//    //Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//    //Kreirati screenshot stranice.
//    //Fajl cuvajte na putanji gde su vam bile slike od proslog domaceg. Na putanji: screenshots/slike.png
    @Test
    public void takeAScreenshot() {
        TakesScreenshot sh = (TakesScreenshot) driver;
        try {
            FileHandler.copy(sh.getScreenshotAs(OutputType.FILE), new File("screenshots/img.jpg"));
        } catch (Exception e) {

        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
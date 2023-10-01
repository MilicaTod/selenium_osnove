package d29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak6 {
    //Napisati program koji:
    //Ucitava stranicu https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example
    //Vrsi klik na Primary dugme, Secondary, Sucess, Danger
    //Ceka da broj toasts-a bude 4
    //Ispisuje poruku, toasts su prikazani
    //Ceka da broj toasts-a bude 0
    //Ispisuje poruku, toasts su se izgubili
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");

        Thread.sleep(2000);
        driver.findElement(By.id("basic-primary-trigger"))
                .click();
        driver.findElement(By.id("basic-secondary-trigger"))
                .click();
        driver.findElement(By.id("basic-success-trigger"))
                .click();
        driver.findElement(By.id("basic-danger-trigger"))
                .click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.toast-fixed.show"), 4));
        System.out.println("Toasts su prikazani.");
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.toast-fixed.show"), 0));
        System.out.println("Toast su se izgubili.");

        driver.quit();

    }
}

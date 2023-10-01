package d29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak3 {
    //Zadatak
    //Napisati program koji:
    //Pre nego sto krenete u automatizaciju prvo sve korake uradite rucno
    //Implicitno cekanje za trazenje elemenata je maksimalno 10s
    //Implicitno cekanje za ucitavanje stranice je 5s
    //Ucitava stranicu https://docs.katalon.com/
    //Maksimizuje prozor
    //Od html elementa cita data-theme atribut.
    //Proverava da li je sadrzaj u tom atributu light i ispisuje odgovarajuce poruke
    //Klikce na dugme za zamenu tema
    //Ponovo cita data-theme atribut html elementa i validira da u atributu stoji vrednost dark
    //Izvrsava kombinaciju tastera CTRL + K. Koristan link  za keyboard actions…kako izvrsavati precice preko Actions objekta
    //Ceka da se dijalog za pretragu pojavi
    //Zatim od inputa za pretragu cita atribut type i proverava da je vrednost tog atributa search
    //Zatvara pretrazivac
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        webDriver.get("https://docs.katalon.com/");
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver,Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[@data-theme='light']")));
        System.out.println("Light theme");

        webDriver.findElement(By.xpath("//button[contains(@class, 'toggleButton_rCf9')]"))
                .click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html[@data-theme='dark']")));
        System.out.println("Dark theme");

        new Actions(webDriver).keyDown(Keys.LEFT_CONTROL).sendKeys("k").keyUp(Keys.LEFT_CONTROL)
                .perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='docsearch-input']")));

        try {
            webDriver.findElement(By.xpath("//input[@id='docsearch-input'][@type='search']"));
        }catch (Exception e){
            throw e;
        }

        webDriver.quit();
    }
}

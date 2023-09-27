package d26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Zadatak2 {
    //Napisati program koji:
    //Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
    //Klikce na svaki iks da ugasi obavestenje i proverava da li se nakon klika element obrisao sa stranice
    // i ispisuje odgovarajuce poruke (OVO JE POTREBNO RESITI PETLJOM)
    //POMOC: Brisite elemente odozdo.
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver();
        WebDriver driver = new ChromeDriver();
        driver.get("https://s.bootsnipp.com/iframe/Dq2X");

        List<WebElement> alertMessage = driver.findElements(By.cssSelector(".alert-dismissable"));

        for (int i = alertMessage.size() - 1; i >= 0; i--) {
            alertMessage.get(i).findElement(By.cssSelector(".close"))
                    .click();
            Thread.sleep(1000);

            List<WebElement> deletedRows = driver.findElements(By.cssSelector(".alert-dismissable:nth-child(" + (i+1) + ")"));
            if (deletedRows.isEmpty()) {
                System.out.println((i+1) + ".alert message deleted");
            } else {
                System.out.println((i) + "All deleted");
            }
        }
    }
}
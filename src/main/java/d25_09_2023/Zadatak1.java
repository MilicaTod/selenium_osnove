package d25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak1 {
    //Maksimizirati prozor
    //Ucitati stranicu https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
    //Prijavite se na sistem
    //Username: Admin
    //Password: admin123
    //Cekanje od 5s
    //U input za pretragu iz navigacije unesite tekst Me
    //Kliknite na prvi rezultat pretrage (to ce biti Time)
    //Cekanje od 1s
    //Kliknite u headeru na svog avatara (to ce biti na ime: Paul Collings)
    //Klinkite na logout
    //Cekanje od 5s
    //Zatvorite pretrazivac
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(5000);
        driver.findElement(By.name("username"))
                .sendKeys("Admin");
        Thread.sleep(5000);
        driver.findElement(By.name("password"))
                .sendKeys("admin123");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@type='submit']"))
                .click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@placeholder='Search']"))
                .sendKeys("Me");
        driver.findElement(By.xpath("//a[@class='oxd-main-menu-item']"))
                .click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']"))
                .click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//ul[@class='oxd-dropdown-menu']/li[4]/a"))
                .click();
        Thread.sleep(5000);

        driver.close();











    }
}

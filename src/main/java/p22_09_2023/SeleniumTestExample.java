package p22_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTestExample {
    public static void main(String[] args) throws InterruptedException {
        // Postavljanje ChromeDriver koristeći WebDriverManager
        WebDriverManager.chromedriver().setup();
        // Kreiranje instance ChromeDriver-a
        WebDriver driver = new ChromeDriver();
        // Otvaranje veb stranice
        driver.get("https://google.com");
        // Mesto za test kod ...
Thread.sleep(5000);
//500ms => 0.5s
//1000ms => 1.0s
        driver.get("http://youtube.com");
        Thread.sleep(4000);
        driver.get("http://facebook.com");

        // Zatvoranje pretrazivaca nakon sto se zavrsi testiranje
        driver.quit();
    }
}

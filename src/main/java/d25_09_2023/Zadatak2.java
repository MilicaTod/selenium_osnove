package d25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Zadatak2 {
    //Niz todo-a (niz stringova) koje treba da uneti. Niz je:
    //Visit Paris
    //Visit Prague
    //Visit London
    //Visit New York
    //Visit Belgrade
    //Maksimizirati prozor
    //Ucitati stranicu https://example.cypress.io/todo
    //Program petljom prolazi kroz niz todo-a i svaki unosi na stranicu
    //Nakon svakog unosa todo-a, unosi se enter. Koristan link
    //Nakon svih unosa proci petljom kroz svaki todo koji je na stranici i za svaki cekirati da je completed.
    //Cekanje od 5s
    //Zatvorite pretrazivac
    public static void main(String[] args) throws InterruptedException {

        ArrayList<String> todo = new ArrayList<>();
        todo.add("Visit Paris");
        todo.add("Visit Prague");
        todo.add("Visit London");
        todo.add("Visit New York");
        todo.add("Visit Belgrade");

        WebDriverManager.chromedriver();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://example.cypress.io/todo");
        for (int i = 0; i < todo.size(); i++) {
            driver.findElement(By.xpath("//input[@class='new-todo']"))
                    .sendKeys(todo.get(i));
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[@class='new-todo']"))
                    .sendKeys(Keys.ENTER);

        }
        Thread.sleep(5000);
        List<WebElement> checks = driver.findElements(By.xpath("//input[@class='toggle']"));
        for (int i = 0; i < checks.size(); i++) {
            checks.get(i).click();
            Thread.sleep(2000);

        }
        Thread.sleep(5000);
        driver.close();
    }
}

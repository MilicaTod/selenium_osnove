package d26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Scanner;

public class Zadatak1 {
    //Napisati program koji:
    //Ucitava stranicu https://demoqa.com/automation-practice-form
    //Popunjava formu sta stranice. Korisnik unosi podatke sa tastature za popunu forme.
    //(za vezbanje) Probajte da unese i datum. Sa datumom se radi isto kao i sa obicnim inputom sa sendKeys.
    //Klik na submit
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form");
        Thread.sleep(2000);

        Scanner s = new Scanner(System.in);
        System.out.println("FirstName: ");
        String firstName = s.next();
        driver.findElement(By.id("firstName"))
                .sendKeys(firstName);

        System.out.println("LastName: ");
        String lastName = s.next();
        driver.findElement(By.id("lastName"))
                .sendKeys(lastName);

        System.out.println("Email: ");
        String email = s.next();
        driver.findElement(By.id("userEmail"))
                .sendKeys(email);

        System.out.println("Gender:Male/Female/Other ");
        String gender = s.next();
        driver.findElement(By.xpath("//label[text()='"+ gender +"']"))
                .click();

        System.out.println("Number: ");
        String number = s.next();
        driver.findElement(By.id("userNumber"))
                .sendKeys(number);

        System.out.println("Subject: ");
        String subject = s.next();
        driver.findElement(By.id("subjectsInput"))
                .sendKeys(subject);

        System.out.println("CurrentAdress: ");
        String currentAddress = s.next();
        driver.findElement(By.id("currentAddress"))
                .sendKeys(currentAddress);

        driver.findElement(By.id("submit"))
                .click();
        Thread.sleep(3000);

        driver.close();

    }
}






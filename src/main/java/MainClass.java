import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class MainClass {

    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Frost\\geckodriver\\drivers\\geckodriver.exe");

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://mail.ru");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.logginIn("danieleontev@mail.ru","dCnxW3ZpVAKFmaq");

        NewLetter newLetter = new NewLetter(driver);

        Thread.sleep(10000);

        newLetter.writingALetter("danieleontev@yandex.ru", "Test message", "Это тестовое сообщение, для проверки работы автотеста.");

        Thread.sleep(5000);

        driver.quit();
    }
}

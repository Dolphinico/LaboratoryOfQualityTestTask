import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Tests {
    private WebDriver driver;
    private LoginPage loginPage;
    private NewLetter newLetter;

    @Before
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Frost\\geckodriver\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://mail.ru");
        loginPage = new LoginPage(driver);
        newLetter = new NewLetter(driver);

    }

    @Test
    public void loginInFailTest() throws InterruptedException {
        LoginPage failLogin = loginPage.logginIn("TestTestov", "qwerty");
        Thread.sleep(3000);
        String error = loginPage.getErrorText();
        Assert.assertEquals("Неверное имя или пароль", error);
    }

    @Test
    public void loginInSuccessTest() throws InterruptedException {
        LoginPage successLogin = loginPage.logginIn("danieleontev@mail.ru", "dCnxW3ZpVAKFmaq");
        Thread.sleep(3000);
        String heading = loginPage.getCorrectHeading();
        Assert.assertEquals("danieleontev@mail.ru", heading);
    }

    @Test
    public void errorToAddressTest() throws InterruptedException {
        LoginPage successLogin = loginPage.logginIn("danieleontev@mail.ru", "dCnxW3ZpVAKFmaq");
        NewLetter failNewletter = newLetter.writingALetter("", "qwer", "qwer");
        Thread.sleep(3000);
        String error = newLetter.getErrorText();
        Assert.assertEquals("Не указан адрес получателя", error);
    }

    @Test
    public void invalidToAddressTest() throws InterruptedException {
        LoginPage successLogin = loginPage.logginIn("danieleontev@mail.ru", "dCnxW3ZpVAKFmaq");
        NewLetter failNewletter = newLetter.writingALetter("123", "", "");
        Thread.sleep(3000);
        String error = newLetter.getErrorHeadingText();
        Assert.assertEquals("Присутствуют некорректные адреса", error);
    }

    @Test
    public void emailSuccesfullySended() {
        LoginPage successLogin = loginPage.logginIn("danieleontev@mail.ru", "dCnxW3ZpVAKFmaq");
        NewLetter failNewletter = newLetter.writingALetter("danieleontev@yandex.ru", "TestMessage",
                "Тестовое сообщение, для проверки jUnit тестов.");
        String heading = newLetter.getSuccessMessage();
        Assert.assertEquals("Письмо отправлено", heading);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

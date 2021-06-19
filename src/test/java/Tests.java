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

    // неликвидные логин и пароль
    @Test
    public void loginInFailTest() throws InterruptedException {
        LoginPage failLogin = loginPage.logginIn("TestTestov", "qwerty");
        Thread.sleep(3000);
        String error = loginPage.getErrorText();
        Assert.assertEquals("Неверное имя или пароль", error);
    }

    // ликвидные логин и пароль, подтверждение по нахождению вашего логина на странице в виде текста
    @Test
    public void loginInSuccessTest() throws InterruptedException {
        LoginPage successLogin = loginPage.logginIn("Логин", "Пароль");
        Thread.sleep(3000);
        String heading = loginPage.getCorrectHeading();
        Assert.assertEquals("Ваш логин", heading);
    }

    // при отправке сообщения не заполнено поле "получатель"
    @Test
    public void errorToAddressTest() throws InterruptedException {
        LoginPage successLogin = loginPage.logginIn("Логин", "Пароль");
        NewLetter failNewletter = newLetter.writingALetter("", "qwer", "qwer");
        Thread.sleep(3000);
        String error = newLetter.getErrorText();
        Assert.assertEquals("Не указан адрес получателя", error);
    }

    // при отправке сообщения, указан неликвидный Emal адрес
    @Test
    public void invalidToAddressTest() throws InterruptedException {
        LoginPage successLogin = loginPage.logginIn("Логин", "Пароль");
        NewLetter failNewletter = newLetter.writingALetter("123", "", "");
        Thread.sleep(3000);
        String error = newLetter.getErrorHeadingText();
        Assert.assertEquals("Присутствуют некорректные адреса", error);
    }

    // успешная отправка сообщения, с поиском подтверждения по заголовку
    @Test
    public void emailSuccesfullySended() {
        LoginPage successLogin = loginPage.logginIn("Логин", "Пароль");
        NewLetter failNewletter = newLetter.writingALetter("Ваш получатель", "TestMessage",
                "Тестовое сообщение, для проверки jUnit тестов.");
        String heading = newLetter.getSuccessMessage();
        Assert.assertEquals("Письмо отправлено", heading);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By loginField = By.xpath("/html/body/main/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/input");
    private By loginButton = By.xpath("/html/body/main/div[2]/div[1]/div[1]/div[2]/form[1]/button[1]");
    private By passwordField = By.xpath("/html/body/main/div[2]/div[1]/div[1]/div[2]/form[1]/div[2]/input");
    private By submitButton = By.xpath("/html/body/main/div[2]/div[1]/div[1]/div[2]/form[1]/button[2]");
    private By errorPopsOut = By.xpath("//div[@class='error svelte-1eyrl7y']");
    private By correctLogInHeading = By.xpath("//span[@class='ph-project__user-name svelte-a0l97g']");

    public LoginPage typeLoginField(String login) {
        driver.findElement(loginField).sendKeys(login);
        return this;
    }

    public LoginPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return this;
    }

    public LoginPage typePasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public LoginPage clickSubmitButton() {
        driver.findElement(submitButton).click();
        return this;
    }

    public String getErrorText(){
        return driver.findElement(errorPopsOut).getText();
    }

    public String getCorrectHeading(){
        return driver.findElement(correctLogInHeading).getText();
    }

    public LoginPage logginIn(String login, String password) {
            this.typeLoginField(login);
            this.clickLoginButton();
            this.typePasswordField(password);
            this.clickSubmitButton();
            return this;
    }
}
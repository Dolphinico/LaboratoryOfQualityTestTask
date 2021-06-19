import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NewLetter {
    private WebDriver driver;

    public NewLetter(WebDriver driver) {
        this.driver = driver;
    }

    private By newLetterButton = By.xpath("/html/body/div[5]/div/div[1]/div[1]/div/div[2]/span/div[1]/div[1]/div/div/div/div[1]/div/div/a/span/span");
    private By toEmailAddressField = By.xpath("/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div/div/div[1]/div/div[2]/div/div/label/div/div/input");
    private By themeField = By.xpath("/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[3]/div[1]/div[2]/div/input");
    private By bodyOfLetter = By.xpath("//div[@role='textbox']");
    private By bodyOfLetter2 = By.xpath("//div[@role='textbox']/div");
    private By bodyOfLetter3 = By.xpath("//div[@role='textbox']");
    private By sendALetter = By.xpath("/html/body/div[15]/div[2]/div/div[2]/div[1]/span[1]");
    private By toAddressIsEmpty = By.xpath("//div[@class='rowError--O4k-g']");
    private By invalidToEmailAddress = By.xpath("//div/h3[@class='base-0-2-58 h3-0-2-62 auto-0-2-82']");
    private By successSending = By.xpath("/html/body/div[9]/div/div/div[2]/div[2]/div/div/div[2]");

    public NewLetter clickNewLetterButton() {
        driver.findElement(newLetterButton).click();
        return this;
    }

    public NewLetter typeToAddressField(String to) {
        driver.findElement(toEmailAddressField).sendKeys(to);
        return this;
    }

    public NewLetter typeThemeField(String theme) {
        driver.findElement(themeField).sendKeys(theme);
        return this;
    }

    public NewLetter clickOnBody() {
        driver.findElement(bodyOfLetter).click();
        return this;
    }

    public NewLetter clickOnBody2() {
        driver.findElement(bodyOfLetter2).click();
        return this;
    }

    public NewLetter writeALetter(String letter) {
        driver.findElement(bodyOfLetter3).sendKeys(letter);
        return this;
    }

    public NewLetter sendALetter(){
        driver.findElement(sendALetter).click();
        return this;
    }

    public String getErrorText(){
        return driver.findElement(toAddressIsEmpty).getText();
    }

    public String getErrorHeadingText(){
        return driver.findElement(invalidToEmailAddress).getText();
    }

    public String getSuccessMessage(){
        return driver.findElement(successSending).getText();
    }

    public NewLetter writingALetter(String to, String theme, String letter) {
        this.clickNewLetterButton();
        this.typeToAddressField(to);
        this.typeThemeField(theme);
        this.clickOnBody();
        this.clickOnBody2();
        this.writeALetter(letter);
        this.sendALetter();
        return this;
    }
}

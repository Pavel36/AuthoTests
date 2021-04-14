package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private By inptLogin = By.cssSelector("input[placeholder='Логин']");
    private By inptPass = By.cssSelector("input[placeholder='Пароль']");
    private By btnSubmit = By.xpath("//span[text()='Войти']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage enterUsername(String login) {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.elementToBeClickable(inptLogin));
        driver.findElement(inptLogin).sendKeys(login);
        return this;
    }

    public LoginPage enterPass(String pass) {
        driver.findElement(inptPass).sendKeys(pass);
        return this;
    }

    public MainPage pressSubmitButton() {
        driver.findElement(btnSubmit).click();
        return new MainPage(driver);
    }
}

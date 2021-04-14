package step;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import page.LoginPage;
import page.MainPage;

public class LoginStep {
    private LoginPage loginPage;

    public LoginStep(WebDriver driver) {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    public MainPage enterLoginAndPassword(String login, String password) {
        loginPage.enterUsername(login);
        loginPage.enterPass(password);
        return loginPage.pressSubmitButton();
    }
}

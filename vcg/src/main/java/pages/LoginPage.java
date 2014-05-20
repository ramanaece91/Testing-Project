package pages;
	
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utility.Utility;

public class LoginPage {
	
	final WebDriver driver;
	@FindBy(how = How.ID, using = "txtUserName")
	//@FindBy(how = How.XPATH, using ="//input[@id='email']")
	private WebElement usernameEditbox;
	 
	@FindBy(how = How.ID, using = "txtPassword")
	//@FindBy(how = How.XPATH, using = "//*[@id='pass']")
	private WebElement passwordEditbox;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[2]/div/form/div[4]/div/input")
	//@FindBy(how = How.XPATH, using = "//input[@type='submit']")
	private WebElement loginButton;
	
	//@FindBy(how = How.XPATH, using = "//*[@id='checkpointSubmitButton']")
	//private WebElement contine;
	 
	//@FindBy(how = How.XPATH, using = "//*[@id='userNavigationLabel']")
	//private WebElement menu;
	
	//@FindBy(how = How.XPATH, using = "//label/input//*[@id='logout_form']")
	//@FindBy(how = How.CSS, using ="#logout_form > label > input")
	//private WebElement logou;
	
	public LoginPage(WebDriver driver) {
	this.driver = driver;
	}
	 
	public void enterUsername(String login) {
	usernameEditbox.clear();
	usernameEditbox.sendKeys(login);
	}
	 
	public void enterPassword(String password) {
	passwordEditbox.clear();
	passwordEditbox.sendKeys(password);
	}
	 
	public void clickLoginButton() {
	loginButton.click();
	//contine.click();
	//menu.click();
	//logou.click();
	}
	
	public HomePage login(String login, String password) throws InterruptedException {
	enterUsername(login);
	enterPassword(password);
	Utility.shortSleep();
	clickLoginButton();
	return PageFactory.initElements(driver, HomePage.class);
	}
}
	

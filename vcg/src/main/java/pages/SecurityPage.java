package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SecurityPage {
	
	final WebDriver driver;
	 
	
	@FindBy(how = How.XPATH, using="//img[@src='img/security-mng-users.png']")
	private WebElement manage_Users;
	
	@FindBy(how = How.XPATH, using = "//img[@src='img/security-mng-privileges.png']")
	private WebElement manage_Roles;
	
	@FindBy(how = How.ID, using = "txtUserId")
	private WebElement user_IdTextBox;
		
	@FindBy(how = How.XPATH, using = "//input[@Value='Search']")
	private WebElement searchButton;
	
	//@FindBy(how = How.XPATH, using = "//tr/td[1]/div[1]/a")
	//private WebElement search_ResultUserIdText;
		
	@FindBy(how = How.XPATH, using = "/html/body/div[2]/div[1]/div[3]/div/div/div[2]")
	//private WebElement search_ResultEmptyId;
	private WebElement search_ResultUserIdText;
	
	public SecurityPage (WebDriver driver) {
	this.driver = driver;
	}
	 
	public void click_ManageUsers() {
		manage_Users.click();
		
	}
	
	public void enter_UserId(String UserId) {
		user_IdTextBox.sendKeys(UserId);
		
	}
	public void click_Searchbutton() {
		searchButton.click();
		
	}
	
		
	public String searchUserId (String UserId) {
		click_ManageUsers();
		enter_UserId(UserId);
		click_Searchbutton();
		String fetchedValue = search_ResultUserIdText.getText();
		return fetchedValue;
		
	}
	
	
	public SecurityPage searchSecurityUserId(String UserId){
		searchUserId(UserId);
		
		return PageFactory.initElements(driver, SecurityPage.class);  
	}
}


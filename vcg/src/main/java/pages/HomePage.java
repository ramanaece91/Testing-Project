package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	final WebDriver driver;
	public Actions menuclick;
	 
	public HomePage(WebDriver driver) {
	this.driver = driver;
	}
		
	@FindBy(how = How.XPATH, using="//div/ul/li[9]/a")
	private WebElement home_security;
/*Automatic generated xpath: //*[@id='gwt-uid-29']/div/div/div/ul/li[1]/a 
 *(To support the dynamic id genrated we will provide only the specific xpath instead of absolute one)	
 */
	
	
	@FindBy(how = How.XPATH, using="//img[@src='img/security-mng-users.png']")
	private WebElement manage_Users;
	
	@FindBy(how = How.XPATH, using="/html/body/div[2]/div[1]/div[3]/div/div/ul[2]/li[3]/a")
	//@FindBy(how = How.CSS, using="body > div:nth-child(12) > div.container > div:nth-child(3) > div > div > ul:nth-child(2) > li > a")
	private WebElement home_Createneworders;
	
	@FindBy(how = How.XPATH, using="//div/ul/li[2]/a")
	private WebElement home_search;
	public CreatePage navigateToCreateOrder()
	{
		System.out.println("Before clicking Createneworders link");
		home_Createneworders.click();
		return PageFactory.initElements(driver, CreatePage.class); 
	}
	
	public SearchPage navigateToSearch()
	{
		System.out.println("Before clicking orders page link");
		home_search.click();
		return PageFactory.initElements(driver, SearchPage.class); 
	
	}
	
	public SecurityPage navigateToSecurityHome(){
		home_security.click();
		return PageFactory.initElements(driver, SecurityPage.class);  
	}
	
	
	
}


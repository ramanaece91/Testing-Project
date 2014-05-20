package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class SearchPage {

	final WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//*[@id='txtOrderNo']")
	private WebElement enter_casenumber;
	
	@FindBy(how=How.XPATH, using="//*[@id='txtEmployer']")
	private WebElement enter_employer;
	
	@FindBy(how=How.XPATH, using="//*[@id='txtEmployeeName']")
	private WebElement enter_employeename;
	
	@FindBy(how=How.XPATH, using="//*[@id='txtEmpSSN']")
	private WebElement enter_employeessn;
	
	@FindBy(how=How.XPATH, using="/html/body/div[2]/div[1]/div[3]/div/div[1]/div/form/div[1]/div[5]/div/div/div/input")
	private WebElement enter_vcgreceiveddate;
	
	@FindBy(how=How.XPATH, using="//*[@id='txtCurrentOwner']")
	private WebElement enter_currentowner;
	
	@FindBy(how=How.XPATH, using="//*[@id='dwlStatus']")
	private WebElement enter_status;
	
	@FindBy(how=How.XPATH, using="//*[@id='dwlSortBy']")
	private WebElement enter_sortby;
	
	@FindBy(how=How.XPATH, using="/html/body/div[2]/div[1]/div[3]/div/div[1]/div/form/div[2]/div[2]/div/div/input")
	private WebElement click_Searchbutton;
	
	@FindBy(how=How.XPATH, using="/html/body/div[2]/div[1]/div[3]/div/div[2]/div/div[1]/div[1]/table/tbody[1]/tr/td[1]/div/a")
	private WebElement search_ResultUserIdText;
	
	public SearchPage (WebDriver driver) {
		this.driver = driver;
		}
	
	public String searchUserId (String casenumber,String Employer,String EmployeeName, String EmployeeSSN,String VCGReceivedDate,String CurrentOwner,String Status,String SortBy) {
		
		enter_casenumber.sendKeys(casenumber);
		enter_employer.sendKeys(Employer);
		enter_employeename.sendKeys(EmployeeName);
		enter_employeessn.sendKeys(EmployeeSSN);
		enter_vcgreceiveddate.sendKeys(VCGReceivedDate);
		enter_currentowner.sendKeys(CurrentOwner);
		enter_status.sendKeys(Status);
		enter_sortby.sendKeys(SortBy);
		click_Searchbutton.click();
		String fetchedValue = search_ResultUserIdText.getText();
		return fetchedValue;
		
	}
	
	
	public SearchPage searchSecurityUserId(String casenumber,String Employer,String EmployeeName, String EmployeeSSN,String VCGReceivedDate,String CurrentOwner,String Status,String SortBy){
		
		searchUserId(casenumber,Employer,EmployeeName,EmployeeSSN,VCGReceivedDate,CurrentOwner,Status,SortBy);
		return PageFactory.initElements(driver, SearchPage.class);  
	}

}

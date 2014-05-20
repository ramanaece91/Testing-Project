package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Utility;


public class CreatePage  {
	final WebDriver driver;
	public Actions menuclick;
	//String EmpName="RobCoff";
	//String nam="Walmart";
	
	
	@FindBy(how = How.XPATH, using="//img[@src='img/orders-acknowleged.png']")
	private WebElement home_Createneworders;
	
	
	@FindBy(how=How.ID, using="dwlEmployer")
	private WebElement select_Employer;
		
	@FindBy(how=How.ID, using="employeeFirstName")
	private WebElement EmpFirstname;
	
	@FindBy(how=How.ID, using="employeeLastName")
	private WebElement EmpLastname;
	
	@FindBy(how=How.ID, using="employeeSSN")
	private WebElement EmpSSN;
		
	@FindBy(how=How.XPATH, using="//div/div[1]/div/div[7]/div/div/input")
	//@FindBy(how=How.XPATH, using="//div/div/div[3]/div/div[3]/div[3]/div/div[2]/div[3]/div/input")
	private WebElement VCGReceivedDate;
	
	//StateAgency Details
	@FindBy(how=How.ID, using="stAgencyName")
	private WebElement StateAgency_Name;
	
	@FindBy(how=How.ID, using="address1")
	private WebElement StateAgency_Address;
	
	@FindBy(how=How.ID, using="stCity")
	private WebElement StateAgency_City;
	
	//select field
	@FindBy(how=How.ID, using="dwlStState")
	private WebElement StateAgency_Select_State;
	
	@FindBy(how=How.ID, using="stZipCode")
	private WebElement StateAgency_ZipCode;
	
	@FindBy(how=How.ID, using="stAgencyTelephone")
	private WebElement StateAgency_TelephoneNo;
	
	@FindBy(how=How.ID, using="stAgencyFax")
	private WebElement StateAgency_Fax;
	
	//Add Dependent Details
	@FindBy(how=How.LINK_TEXT, using="Add Dependent")
	private WebElement Click_AddDependent;
	
	@FindBy(how=How.ID, using="addDepSSN")
	private WebElement Dependent_SSN_No;
	
	@FindBy(how=How.ID, using="addDepFirstName")
	private WebElement Dependent_FirstName;
	
	@FindBy(how=How.ID, using="addDepLastName")
	private WebElement Dependent_LastName;
	
	//@FindBy(how=How.ID, using="addDepAge")
	@FindBy(how=How.XPATH, using="/html/body/div[2]/div[1]/div[3]/div/div[4]/form/div/div[1]/div[6]/div[2]/form/div[5]/div/input[1]")
	private WebElement Dependent_DOB;
	
	//Select Field
	@FindBy(how=How.ID, using="dwlAddDepGenderType")
	private WebElement Dependent_Select_Gender;
	
	//Radio Button
	@FindBy(how=How.ID, using="gwt-uid-4203")
	private WebElement Dependent_Covered;
	
	//Check Box
	@FindBy(how=How.ID, using="gwt-uid-4205")
	private WebElement Dependent_Coverage;
			
	@FindBy(how=How.LINK_TEXT, using="Add")
	private WebElement Click_Add;
	
	//Employee Information Details
	
	//select field
	@FindBy(how=How.ID, using="dwlempInfoStatus")
	private WebElement Employee_Status;

	@FindBy(how=How.XPATH,using="//div/div[1]/div/div[2]/div/div/input")
	private WebElement Employee_DateofHire;

	//select field
	@FindBy(how=How.ID, using="dwlTypeAtDateOfHire")
	private WebElement Employee_TypeonDateofHire;

	//select field
	@FindBy(how=How.ID, using="dwlCurrentEmployeeType")
	private WebElement Employee_CurrentType;
	
	//select field
	@FindBy(how=How.ID, using="dwlEligibleForBenefit")
	private WebElement Employee_EligibleforBenefit;
	
	//select Date
	@FindBy(how=How.ID, using="//div/div[2]/div/div[2]/div/div/input")
	private WebElement Employee_BenifitEligibleDate;
	
	@FindBy(how=How.ID, using="empInfoWorkLocation")
	private WebElement Employee_WorkLocation;
	
	@FindBy(how=How.ID, using="empInfoID")
	private WebElement Employee_ID;
	
	//Employee Details
	@FindBy(how=How.ID, using="empInfoAddress1")
	private WebElement Employee_Address;
	
	@FindBy(how=How.ID, using="empInfoCity")
	private WebElement Employee_City;
	
	//select field
	@FindBy(how=How.ID, using="dwlEmpInfoState")
	private WebElement Employee_State;
	
	@FindBy(how=How.ID, using="empInfoZipCode")
	private WebElement Employee_ZipCode;
	
	//Save the Order
	@FindBy(how=How.LINK_TEXT, using="Save as Draft")
	private WebElement Click_Saveasdraft;
	
	@FindBy(how=How.XPATH, using="/html/body/div[2]/div[1]/div[3]/div/div[5]/div[3]/a[1]")
	private WebElement Click_Ok;
	
	@FindBy(how=How.XPATH, using="/html/body/div[2]/div[3]/div[2]/div/span[3]/ul/li[1]/div")
	private WebElement ReadErrormsg;
	
	@FindBy(how=How.XPATH, using="/html/body/div[2]/div[1]/div[3]/div/div[5]/div[2]/div")
	private WebElement Updatedmsg;
	
	@FindBy(how=How.XPATH, using="/html/body/div[2]/div[3]")
	private WebElement wholemsg;
	
	@FindBy(how=How.XPATH, using="/html/body/div[2]/div[3]/div[3]/a")
	private WebElement Click_Ok_popup;
	
	public CreatePage(WebDriver driver) {
		this.driver = driver;
		}
	//public String enter_orderdetails(String Employer,String EmpFirstName,String EmpLastName,String Empssn,String Vcgreceivedate,String StateAgencyName,String StateAgencyAddress,String StateAgencyCity,String StateAgencyState,String StateAgencyZipCode,String StateAgencyTelephone,String StateAgencyFax,String AddDependentSSN,String AddDependentFirstName,String AddDependentLastName,String AddDependentDOB,String AddDependentGender,String AddDependentCovered,String AddDependentCoverage,String EmployeeStatus,String EmployeeDateofHire,String EmployeeTypeonDateofHire,String CurrentEmployeeType,String EligibleforBenefit,String BenefitEligibleDate,String EmployeeWorkLocation,String EmployeeID,String EmployeeAddress,String EmployeeCity,String EmployeeState,String EmployeeZipCode) throws InterruptedException {
		
		//select_Employer.click();
	public String click_order(String Employer,String EmpFirstName,String EmpLastName,String Empssn,String Vcgreceivedate,String StateAgencyName,String StateAgencyAddress,String StateAgencyCity,String StateAgencyState,String StateAgencyZipCode,String StateAgencyTelephone,String StateAgencyFax,String AddDependentSSN,String AddDependentFirstName,String AddDependentLastName,String AddDependentDOB,String AddDependentGender,String AddDependentCovered,String AddDependentCoverage,String EmployeeStatus,String EmployeeDateofHire,String EmployeeTypeonDateofHire,String CurrentEmployeeType,String EligibleforBenefit,String BenefitEligibleDate,String EmployeeWorkLocation,String EmployeeID,String EmployeeAddress,String EmployeeCity,String EmployeeState,String EmployeeZipCode ) throws InterruptedException
	{
		select_Employer.sendKeys(Employer);
		Utility.shortSleep();
		EmpFirstname.sendKeys(EmpFirstName);
		System.out.println("Employee FirstName");
		EmpLastname.sendKeys(EmpLastName);
		EmpSSN.sendKeys(Empssn);
		System.out.println("Employee SSN");
		
		//VCGReceivedDate.sendKeys(Vcgreceivedate);
		//StateAgency Details
		StateAgency_Name.sendKeys(StateAgencyName);
		StateAgency_Address.sendKeys(StateAgencyAddress);
		StateAgency_City.sendKeys(StateAgencyCity);
		//StateAgency_Select_State.click();
		StateAgency_Select_State.sendKeys(StateAgencyState);
		StateAgency_ZipCode.sendKeys(StateAgencyZipCode);
		StateAgency_TelephoneNo.sendKeys(StateAgencyTelephone);
		StateAgency_Fax.sendKeys(StateAgencyFax);
		
		//AddDependent Details
		/*Click_AddDependent.click();
		Dependent_SSN_No.sendKeys(AddDependentSSN);
		Dependent_FirstName.sendKeys(AddDependentFirstName);
		Dependent_LastName.sendKeys(AddDependentLastName);
		Dependent_DOB.sendKeys(AddDependentDOB);
		//Dependent_Select_Gender.click();
		Dependent_Select_Gender.sendKeys(AddDependentGender);
		//Dependent_Covered.click();
		//Dependent_Coverage.click();
		Click_Add.click();
		//Employee Information Details
		//Employee_Status.click();
		//Utility.shortSleep();
		 
		Employee_Status.sendKeys(EmployeeStatus);
		
		//Employee_DateofHire.sendKeys(EmployeeDateofHire);
		//Employee_TypeonDateofHire.click();
		//Employee_TypeonDateofHire.sendKeys(EmployeeTypeonDateofHire);
		//Employee_CurrentType.click();
		Employee_CurrentType.sendKeys(CurrentEmployeeType);
		//Employee_EligibleforBenefit.click();
		Employee_EligibleforBenefit.sendKeys(EligibleforBenefit);
		//Employee_BenifitEligibleDate.sendKeys(BenefitEligibleDate);
		Employee_WorkLocation.sendKeys(EmployeeWorkLocation);
		Employee_ID.sendKeys(EmployeeID);
		Employee_Address.sendKeys(EmployeeAddress);
		Employee_City.sendKeys(EmployeeCity);
		Employee_State.sendKeys(EmployeeState);
		Employee_ZipCode.sendKeys(EmployeeZipCode);*/
		//Save the order
		 
		Click_Saveasdraft.click();
		//Utility.shortSleep();
		//String errormsg=ReadErrormsg.getText();
		//String errormsg1=ReadErrormsg.toString();
		
		Utility.shortSleep();
		 String msg1=wholemsg.getText();
		System.out.println("message: "+msg1);
		//Click_Ok.click();
		//System.out.println("Error message: "+errormsg);
		//System.out.println("Error message: "+errormsg1);
		Click_Ok_popup.click();
		//Utility.shortSleep();
		
		return msg1;
		
		
		
	}

	//public String click_order(String Employer,String EmpFirstName,String EmpLastName,String Empssn,String Vcgreceivedate,String StateAgencyName,String StateAgencyAddress,String StateAgencyCity,String StateAgencyState,String StateAgencyZipCode,String StateAgencyTelephone,String StateAgencyFax,String AddDependentSSN,String AddDependentFirstName,String AddDependentLastName,String AddDependentDOB,String AddDependentGender,String AddDependentCovered,String AddDependentCoverage,String EmployeeStatus,String EmployeeDateofHire,String EmployeeTypeonDateofHire,String CurrentEmployeeType,String EligibleforBenefit,String BenefitEligibleDate,String EmployeeWorkLocation,String EmployeeID,String EmployeeAddress,String EmployeeCity,String EmployeeState,String EmployeeZipCode ) throws InterruptedException
	//{
		//home_Createneworders.click();
		//System.out.println("Ready to enter empname");
		//enter_orderdetails(Employer,EmpFirstName,EmpLastName,Empssn,Vcgreceivedate,StateAgencyName,StateAgencyAddress,StateAgencyCity,StateAgencyState,StateAgencyZipCode,StateAgencyTelephone,StateAgencyFax,AddDependentSSN,AddDependentFirstName,AddDependentLastName,AddDependentDOB,AddDependentGender,AddDependentCovered,AddDependentCoverage,EmployeeStatus,EmployeeDateofHire,EmployeeTypeonDateofHire,CurrentEmployeeType,EligibleforBenefit,BenefitEligibleDate,EmployeeWorkLocation,EmployeeID,EmployeeAddress,EmployeeCity,EmployeeState,EmployeeZipCode);
	//	return errormsg;
		
	//}
	
	public CreatePage createOrders(String Employer,String EmpFirstName,String EmpLastName,String Empssn,String Vcgreceivedate,String StateAgencyName,String StateAgencyAddress,String StateAgencyCity,String StateAgencyState,String StateAgencyZipCode,String StateAgencyTelephone,String StateAgencyFax,String AddDependentSSN,String AddDependentFirstName,String AddDependentLastName,String AddDependentDOB,String AddDependentGender,String AddDependentCovered,String AddDependentCoverage,String EmployeeStatus,String EmployeeDateofHire,String EmployeeTypeonDateofHire,String CurrentEmployeeType,String EligibleforBenefit,String BenefitEligibleDate,String EmployeeWorkLocation,String EmployeeID,String EmployeeAddress,String EmployeeCity,String EmployeeState,String EmployeeZipCode ) throws InterruptedException{
		//home_Createneworders.click();
		System.out.println("Before clicking neworders");
		click_order(Employer,EmpFirstName,EmpLastName,Empssn,Vcgreceivedate,StateAgencyName,StateAgencyAddress,StateAgencyCity,StateAgencyState,StateAgencyZipCode,StateAgencyTelephone,StateAgencyFax,AddDependentSSN,AddDependentFirstName,AddDependentLastName,AddDependentDOB,AddDependentGender,AddDependentCovered,AddDependentCoverage,EmployeeStatus,EmployeeDateofHire,EmployeeTypeonDateofHire,CurrentEmployeeType,EligibleforBenefit,BenefitEligibleDate,EmployeeWorkLocation,EmployeeID,EmployeeAddress,EmployeeCity,EmployeeState,EmployeeZipCode);
		return PageFactory.initElements(driver, CreatePage.class); 
		
		
	//return PageFactory.initElements(driver, CreatePage.class);  
	}

}

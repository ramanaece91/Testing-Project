package tests;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import driver.Base;
import pages.CreatePage;
//import pages.DeleteOrders;
import pages.HomePage;
import pages.SecurityPage;
import utility.ExcelReader;
import utility.Utility;


public class CreateorderTest extends Base
{
	 	String stat;
		String msg;
		String Pass = "p";
		String Fail ="f";
		//String UserId = "VCG";
		//String Empname="Robcoff";
	
	 private static String strFileName =null;
	 private static String Create_NewOrders_SheetName =null;
	 private static String Create_NewOrders_iStartRow =null;
	 private static String Create_NewOrders_iStartCol =null;
	 private static String Create_NewOrders_iEndRow =null;
	 private static String Create_NewOrders_iEndCol =null;
	 //private static String EditDelete_iStartRow = null;
	 //private static String EditDelete_iEndRow = null;
	@BeforeClass
	 public void setUp() throws Exception { 
	  System.out.println("In Before Class");
	  strFileName = DATA.getProperty("strFileName");
	  Create_NewOrders_SheetName = DATA.getProperty("Create_NewOrders_SheetName");
	  Create_NewOrders_iStartRow = DATA.getProperty("Create_NewOrders_iStartRow"); 
	  Create_NewOrders_iStartCol = DATA.getProperty("Create_NewOrders_iStartCol"); 
	  Create_NewOrders_iEndRow = DATA.getProperty("Create_NewOrders_iEndRow");
	  Create_NewOrders_iEndCol = DATA.getProperty("Create_NewOrders_iEndCol");
	  //EditDelete_iStartRow = DATA.getProperty("EditDelete_iStartRow");
	  //EditDelete_iEndRow = DATA.getProperty("EditDelete_iEndRow");
	 }
	
	 @DataProvider(name = "EmpnameDP")
	    public Object[][] initObjArray() throws Exception{     
		 Object[][] retObjArr=ExcelReader.getDataRange(strFileName, Create_NewOrders_SheetName, Integer.parseInt(Create_NewOrders_iStartRow), Integer.parseInt(Create_NewOrders_iStartCol),Integer.parseInt(Create_NewOrders_iEndRow),Integer.parseInt(Create_NewOrders_iEndCol));    
	  return(retObjArr);  
	    }
	 
	 
		//@Test(priority=99,dataProvider = "EmpnameDP")
		@Test(description = "Go to createpage", dataProvider = "EmpnameDP")
		public void NavigateToCreatePage(String Employer,String EmpFirstName,String EmpLastName,String Empssn,String Vcgreceivedate,String StateAgencyName,String StateAgencyAddress,String StateAgencyCity,String StateAgencyState,String StateAgencyZipCode,String StateAgencyTelephone,String StateAgencyFax,String AddDependentSSN,String AddDependentFirstName,String AddDependentLastName,String AddDependentDOB,String AddDependentGender,String AddDependentCovered,String AddDependentCoverage,String EmployeeStatus,String EmployeeDateofHire,String EmployeeTypeonDateofHire,String CurrentEmployeeType,String EligibleforBenefit,String BenefitEligibleDate,String EmployeeWorkLocation,String EmployeeID,String EmployeeAddress,String EmployeeCity,String EmployeeState,String EmployeeZipCode,String ExpResult,String ActValue) throws InterruptedException 
		{
			System.out.println("Navigating to Search Screen");
			try
			{
			
				HomePage findPage = PageFactory.initElements(getDriver(), HomePage.class);
				CreatePage create = findPage.navigateToCreateOrder();
				System.out.println("After Create Page");
				String returnvalue=create.click_order(Employer,EmpFirstName,EmpLastName,Empssn,Vcgreceivedate,StateAgencyName,StateAgencyAddress,StateAgencyCity,StateAgencyState,StateAgencyZipCode,StateAgencyTelephone,StateAgencyFax,AddDependentSSN,AddDependentFirstName,AddDependentLastName,AddDependentDOB,AddDependentGender,AddDependentCovered,AddDependentCoverage,EmployeeStatus,EmployeeDateofHire,EmployeeTypeonDateofHire,CurrentEmployeeType,EligibleforBenefit,BenefitEligibleDate,EmployeeWorkLocation,EmployeeID,EmployeeAddress,EmployeeCity,EmployeeState,EmployeeZipCode);
				System.out.println("After enter_orderdetails");
				//System.out.println(returnvalue);
				System.out.println("Expected Result: "+ExpResult);
				System.out.println("Actual Result: "+ActValue);
				System.out.println("Return Value: "+returnvalue);
				System.out.println(returnvalue.contains(ActValue));
				
				if(returnvalue.contains(ActValue))
					
				{
					System.out.println("Test Passed");
				}
				else
				{
					System.out.println("Test Failed");
				}
				stat = Pass;
				msg = "Completed the create order";
				
			}
			
				catch (Throwable e)
				{
					 stat = Fail;
					 msg = e.getMessage();	
					 Assert.fail();
				}
			finally{		
				Utility.updateTestResult(getClass().getName().toString(), Utility.getCurrentMethodName(),stat, msg);
			}
		}

}

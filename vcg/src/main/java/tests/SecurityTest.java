package tests;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import driver.Base;

import pages.HomePage;
import utility.ExcelReader;
import utility.Utility;
import pages.SecurityPage;


public class SecurityTest extends Base{
	
	 private static String strFileName =null;
	 private static String Security_ManageUsers_SheetName =null;
	 private static String Security_ManageUsers_iStartRow =null;
	 private static String Security_ManageUsers_iStartCol =null;
	 private static String Security_ManageUsers_iEndRow =null;
	 private static String Security_ManageUsers_iEndCol =null;
	 //private static String EditDelete_iStartRow = null;
	 //private static String EditDelete_iEndRow = null;

	String stat;
	String msg;
	String Pass = "p";
	String Fail ="f";
	String UserId = "VCG";
	
	 @BeforeClass
	 public void setUp() throws Exception { 
	  System.out.println("In Before Class");
	  strFileName = DATA.getProperty("strFileName");
	  Security_ManageUsers_SheetName = DATA.getProperty("Security_MangeUsers_SheetName");
	  Security_ManageUsers_iStartRow = DATA.getProperty("Security_MangeUsers_iStartRow");
	  Security_ManageUsers_iStartCol = DATA.getProperty("Security_MangeUsers_iStartCol");
	  Security_ManageUsers_iEndRow = DATA.getProperty("Security_MangeUsers_iEndRow");
	  Security_ManageUsers_iEndCol = DATA.getProperty("Security_MangeUsers_iEndCol");
	  //EditDelete_iStartRow = DATA.getProperty("EditDelete_iStartRow");
	  //EditDelete_iEndRow = DATA.getProperty("EditDelete_iEndRow");
	 }
	 
	 @DataProvider(name = "UserIdDP")
	    public Object[][] initObjArray() throws Exception{     
		 Object[][] retObjArr=ExcelReader.getDataRange(strFileName, Security_ManageUsers_SheetName, Integer.parseInt(Security_ManageUsers_iStartRow), Integer.parseInt(Security_ManageUsers_iStartCol),Integer.parseInt(Security_ManageUsers_iEndRow),Integer.parseInt(Security_ManageUsers_iEndCol));    
	  return(retObjArr);     
	    }
	
		
	@Test(priority = 100, dataProvider = "UserIdDP")
	public void NavigateToSearch(String UserId,String ExpResult,String ActValue) throws InterruptedException {
	System.out.println("Navigating to Search Screen");
		try{
			HomePage findPage = PageFactory.initElements(getDriver(), HomePage.class);
			SecurityPage searchUser = findPage.navigateToSecurityHome();
			String returnUserId = searchUser.searchUserId(UserId);
			//System.out.println("Actual Search Text ==> " + UserId + "  Returned Search Value ==> " + returnUserId);
			System.out.println("Given user Id: "+UserId);
			System.out.println("Expected Result: "+ExpResult);
			System.out.println("Actual Result: "+ActValue);
			System.out.println("Return Value: "+returnUserId);
			
			if(returnUserId.contains(ActValue))
			{
				System.out.println("Test Passed");
			}
			else
			{
				System.out.println("Test Failed");
			}
			//Assert.assertEquals(returnUserId, UserId);
			stat = Pass;
			msg = "Completed the search";
		}catch (Throwable e) {
			 stat = Fail;
			 msg = e.getMessage();	
			 Assert.fail();
		}finally{		
			//System.out.println(getClass().getName().toString());
			//System.out.println( Utility.getCurrentMethodName());
			Utility.updateTestResult(getClass().getName().toString(), Utility.getCurrentMethodName(),stat, msg);
		}
	}
}

package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import driver.Base;
import pages.HomePage;
import pages.SearchPage;
import utility.ExcelReader;
import utility.Utility;
import pages.SecurityPage;


public class SearchOrderTest extends Base {
	
	private static String strFileName =null;
	 private static String SearchOrder_SheetName =null;
	 private static String SearchOrder_iStartRow =null;
	 private static String SearchOrder_iStartCol =null;
	 private static String SearchOrder_iEndRow =null;
	 private static String SearchOrder_iEndCol =null;
	 //private static String EditDelete_iStartRow = null;
	 //private static String EditDelete_iEndRow = null;

	String stat;
	String msg;
	String Pass = "p";
	String Fail ="f";
	//String casenumber = "VCG";
	
	 @BeforeClass
	 public void setUp() throws Exception { 
	  System.out.println("In Before Class");
	  strFileName = DATA.getProperty("strFileName");
	  SearchOrder_SheetName = DATA.getProperty("SearchOrder_SheetName");
	  SearchOrder_iStartRow = DATA.getProperty("SearchOrder_iStartRow");
	  SearchOrder_iStartCol = DATA.getProperty("SearchOrder_iStartCol");
	  SearchOrder_iEndRow = DATA.getProperty("SearchOrder_iEndRow");
	  SearchOrder_iEndCol = DATA.getProperty("SearchOrder_iEndCol");
	  //EditDelete_iStartRow = DATA.getProperty("EditDelete_iStartRow");
	  //EditDelete_iEndRow = DATA.getProperty("EditDelete_iEndRow");
	 }
	 
	 @DataProvider(name = "UserIdDP")
	    public Object[][] initObjArray() throws Exception{     
		 Object[][] retObjArr=ExcelReader.getDataRange(strFileName, SearchOrder_SheetName, Integer.parseInt(SearchOrder_iStartRow), Integer.parseInt(SearchOrder_iStartCol),Integer.parseInt(SearchOrder_iEndRow),Integer.parseInt(SearchOrder_iEndCol));    
	  return(retObjArr);     
	    }
	
		
	@Test(priority = 100, dataProvider = "UserIdDP")
	public void NavigateToSearchOrder(String casenumber,String Employer,String EmployeeName, String EmployeeSSN,String VCGReceivedDate,String CurrentOwner,String Status,String SortBy,String ExpResult,String ActValue) throws InterruptedException {
	System.out.println("Navigating to Search Screen");
		try{
			
			HomePage findPage = PageFactory.initElements(getDriver(), HomePage.class);
			SearchPage searchUser = findPage.navigateToSearch();
			String returnUserId = searchUser.searchUserId(casenumber,Employer,EmployeeName,EmployeeSSN,VCGReceivedDate,CurrentOwner,Status,SortBy);
			//System.out.println("Actual Search Text ==> " + UserId + "  Returned Search Value ==> " + returnUserId);
			System.out.println("Given user Id: "+casenumber);
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

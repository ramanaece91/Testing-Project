package utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import driver.Base;


public class Utility extends Base{

	public static void verifyurl(WebDriver getDriver,String Expurl){
		System.out.println(getDriver.getCurrentUrl());
		String actualURL = getDriver.getCurrentUrl();
		Assert.assertEquals(actualURL, Expurl);
	}


	public static  boolean verifyText(WebDriver getDriver, String text)throws InterruptedException{
		return getDriver.getPageSource().contains(text);
	}


    public static String IdentifierValue(WebDriver getDriver, String text){
        return "uniqueValue";
     }

    
    public static String uniqueValue(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
        String uniqueValue = sdf.format(date);
        return uniqueValue;
     }

     public static void javaScriptExec(WebDriver getDriver, String functionname){
     	((JavascriptExecutor) getDriver).executeScript(functionname);
     }

   
	public static void reduceImplicitTimeout(WebDriver driver){
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}


	public static void revertImplicitTimeout(WebDriver driver){
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
	}


	public static String getCurrentMethodName()
	{
		StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
		return stackTraceElements[1].toString();
	}

	
	public static void updateTestResult(String className, String currentMethodName, String testStatus, String messageText)
	{
		String Status = "Failed";
		if (testStatus == "Pass"){
			 Status = "Passed";
		}
		applogger.debug("ClassName==>  " + className + "   MethodName ==> " + currentMethodName + "   testResult==> " + Status );
		if (messageText != null){
			applogger.debug("Exception message ==>  " + messageText);
		}
		String testlinkselect = ECONFIG.getProperty("Testlink");
		if (testlinkselect.equalsIgnoreCase("Yes")){
			System.out.println("Making testlink call");
			try{
				System.out.println("In util"+currentMethodName);
				Testlink.updateTestResults(currentMethodName,messageText, testStatus);
			}catch (Throwable e) {
				System.out.println(e.getMessage());
			}
		}else{
			System.out.println("Not making testlink call");
		}
	}

	
 	/*Utility Methods to perform various sleeps*/
 	public static void shortSleep() throws InterruptedException{
 		Thread.sleep(Integer.parseInt(CONFIG.getProperty("short")));
 	}

 	public static void mediumSleep() throws InterruptedException{
 		Thread.sleep(Integer.parseInt(CONFIG.getProperty("medium")));
 	}

 	public static void longSleep() throws InterruptedException{
 		Thread.sleep(Integer.parseInt(CONFIG.getProperty("long")));
 	}

 	public static void veryLongSleep() throws InterruptedException{
 		Thread.sleep(Integer.parseInt(CONFIG.getProperty("extralong")));
 	}

	public static void verifytext(WebDriver getDriver,String Expurl){
		Assert.assertEquals("Success", Expurl);
	}

}
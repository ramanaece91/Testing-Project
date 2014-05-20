package utility;

import java.net.MalformedURLException;
import java.net.URL;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import br.eti.kinoshita.testlinkjavaapi.*;
import testlink.api.java.client.TestLinkAPIClient;
//import testlink.api.java.client.TestLinkAPIException;
import driver.Base;

public class Testlink extends Base{
	private static final String DEVKEY =  CONFIG.getProperty("DEVKEY");
	private static final String URL = CONFIG.getProperty("URL");
    private static  String projectName = CONFIG.getProperty("projectName");
    private static  String testPlanName = CONFIG.getProperty("testPlanName");
    private static  String buildName = CONFIG.getProperty("buildName");

	public static TestLinkAPI testlinkapi() throws MalformedURLException, TestLinkAPIException{

    TestLinkAPI api = null;
    URL testlinkURL = null;

    try     {
    	  testlinkURL = new URL(URL);
    } catch ( MalformedURLException mue )   {
            mue.printStackTrace( System.err );
    }
    try{
    	api = new TestLinkAPI(testlinkURL, DEVKEY);
    	return api;
	}catch(Exception e){
		System.out.println(e);
		return null;
	}
}


	public static TestLinkAPIClient clientapi()	throws MalformedURLException, testlink.api.java.client.TestLinkAPIException{
	    try{
	    	TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEVKEY , URL);
	        return testlinkAPIClient;
	    }catch(Exception e){
	    	System.out.println(e);
	    	return null;
	    }

	}


	public static String getTestCaseName(String  testcase_external_id) throws  MalformedURLException,TestLinkAPIException{
		System.out.println("In get Test Case Name");
		try{
			String testCaseName=null;
				testCaseName =new String( Testlink.testlinkapi().getTestCaseByExternalId(testcase_external_id, 1).getName());
				return testCaseName;
		}
		catch (Throwable e){
			System.out.println(e);
		}
		return null;
	}


	public static void updateTestResults(String testcasename ,String execNotes, String testResultStatus ) throws TestLinkAPIException, MalformedURLException{
		System.out.println("In updateTestResults");
		String testCaseNameOrVisibleID = Testlink.getTestlinkTestCaseName(testcasename);
		if ( testCaseNameOrVisibleID == null){
			System.out.println("Wrong Representation of testcasename and testlink wont be updated");
		}else{
			System.out.println("i am in updation");
			try{
				clientapi().reportTestCaseResult(projectName, testPlanName, testCaseNameOrVisibleID, buildName, execNotes, testResultStatus);
			}catch(testlink.api.java.client.TestLinkAPIException e){
				System.out.println(e.getMessage());
			}
		}
	}


	public static String getTestlinkTestCaseName(String testcasename) throws MalformedURLException, TestLinkAPIException {
		System.out.println("in getTestlinkTestCaseName "+testcasename);
		String tc_name = testcasename.replaceFirst("_", "-");
		tc_name = tc_name.split("_", 2)[0];		
		String f_testname =new String(Testlink.getTestCaseName(tc_name.split("\\.", 3)[2]));
		System.out.println("f_testname"+f_testname);
		return f_testname;

	}

}
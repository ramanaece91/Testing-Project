package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import driver.Base;
 
public class SendResults extends Base{
public String mailFrom, mailTo, mailTitle, mailText, htmlPayLoad;	
	public SendResults(String mf, String mt, String mtitle, String mtext) {
		mailFrom = mf;
		mailTo = mt;
		mailTitle = mtitle;
		mailText = mtext;
		System.out.println("The SendResults class is loaded.");
		System.out.println("Registered email target: " + mailTo);
		System.out.println("Registered sender: " + mailFrom);
	}
	
	private void getTestNGResult() throws IOException {
		FileInputStream stream = null;
		try { // testNG stores results in the ./test-output directory by default

			String reportloc = CONFIG.getProperty("reportlocation");
			//stream = new FileInputStream(new File("C:\\Cadec\\Workspace\\workspace\\target\\surefire-reports\\emailable-report.html"));
			stream = new FileInputStream(new File(reportloc));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			FileChannel fc = stream.getChannel();
			MappedByteBuffer bb = fc.map( FileChannel.MapMode.READ_ONLY, 0, fc.size() );
			/* Instead of using default, pass in a decoder. */
			htmlPayLoad = Charset.defaultCharset().decode(bb).toString();
		} finally {
			stream.close();
		}
}
	
public void sendTestNGResult() throws IOException {
	getTestNGResult();
	Mailer gmailer = new Mailer();
	gmailer.sendTestNGResult(mailFrom, mailTo, mailTitle, mailText, htmlPayLoad );
}
public void sendRegularEmail() throws IOException {
	Mailer gmailer = new Mailer();
	gmailer.sendRegularEmail(mailFrom, mailTo, mailTitle, mailText );
}
}
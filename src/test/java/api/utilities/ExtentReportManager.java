package api.utilities;
 
import java.text.SimpleDateFormat;
import java.util.Date;
 
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
 
public class ExtentReportManager implements ITestListener
{
public ExtentSparkReporter sparkReporter;//responsible for UI of the report and responsible for look and feel of the report
public ExtentReports extent;//ExtentReports is to project some common data like environment info,user info,project name, module name
public ExtentTest test;//responsible for writing the test or creating the entries in the report
String repName;
public void onStart(ITestContext testContext)
{
	 String timeStamp =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
	 repName="Test-Report-"+timeStamp+".html";
	 sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);//specify location of the report
	 sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");//title of report
	 sparkReporter.config().setReportName("Pet Store Users API");//name of the report
	 sparkReporter.config().setTheme(Theme.DARK);
	 extent=new ExtentReports();
	 extent.attachReporter(sparkReporter);
	 extent.setSystemInfo("Application","Pest Store Users API");
	 extent.setSystemInfo("Operating System", System.getProperty("os.name"));
	 extent.setSystemInfo("UserName", System.getProperty("user.name"));
	 extent.setSystemInfo("Environment","QA");
	 extent.setSystemInfo("user", "Darshini");

	 }

public void onTestSuccess(ITestResult result)
{
	 test=extent.createTest(result.getName());
	 test.assignCategory(result.getMethod().getGroups());
	 test.createNode(result.getName());
	 test.log(Status.PASS,"Test Passed");
}
public void onTestFailure(ITestResult result)
{
	 test=extent.createTest(result.getName());
	 test.createNode(result.getName());
	 test.assignCategory(result.getMethod().getGroups());
	 test.log(Status.FAIL, "Test Failed");
	 test.log(Status.FAIL,result.getThrowable().getMessage());
}
public void onTestSkipped(ITestResult result)
{
	 test=extent.createTest(result.getName());
	 test.createNode(result.getName());
	 test.assignCategory(result.getMethod().getGroups());
	 test.log(Status.SKIP,"Test Skipped");
	 test.log(Status.SKIP, result.getThrowable().getMessage());
}
public void onFinish(ITestContext testContext)
{
	 extent.flush();//after creating above all method we call flush method which makes report ready ,if we not specify flush method report will not be generated
}
}
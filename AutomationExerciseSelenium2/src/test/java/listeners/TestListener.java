package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.DriverFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static ExtentTest test;
    private WebDriver driver;

    @Override
    public void onStart(ITestContext context) {
        // Create the HTML report
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        test.log(Status.FAIL, result.getThrowable());

        // Capture screenshot on failure
        try {
            // Note: Ensure your DriverFactory has a getDriver() method, or use a ThreadLocal variable
            driver = DriverFactory.createDriver(); 
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String dest = "target/screenshots/" + result.getMethod().getMethodName() + "_" + timestamp + ".png";
            Files.createDirectories(Paths.get("target/screenshots"));
            Files.copy(source.toPath(), Paths.get(dest));
            test.addScreenCaptureFromPath(dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        // Save the report
        extent.flush();
    }
}
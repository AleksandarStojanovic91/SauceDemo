import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class CustomListener extends Base implements ITestListener {
    public void onTestFailure(ITestResult result){
        try {
            takeScreenshot(result.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
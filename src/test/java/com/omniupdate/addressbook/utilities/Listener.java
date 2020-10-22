package com.omniupdate.addressbook.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listener implements ITestListener {

    String filePath = "test-output/html/screenshots";

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenShot(result.getName());
    }

    public void takeScreenShot(String methodName) {

        File scrFile = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(filePath+methodName+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        Driver.closeDriver();
    }
}

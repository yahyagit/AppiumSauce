package com.contactenergy.helpers;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryAnalyzer implements IRetryAnalyzer {

    // set your count to rerun test Maven can set a sys property which can be read here
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public boolean retry(ITestResult result) {
        return 0 < count.getAndDecrement();
    }
}

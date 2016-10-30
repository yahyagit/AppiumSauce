package app.nativeApp.ios;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

@RunWith(ConcurrentParameterized.class)
public class ParallelNativeIOSTest implements SauceOnDemandSessionIdProvider {

	private String SAUCE_USERNAME = "yahyacool";
    private String SAUCE_KEY = "27fc7db7-b47d-47b3-be82-95d7a8ecd658";
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(SAUCE_USERNAME, SAUCE_KEY);

    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

    private String platformVersion;
    private String deviceName;
    private String sessionId;
    private AppiumDriver driver;
    private WebDriverWait wait;

    public ParallelNativeIOSTest(String platformVersion, String deviceName) {
        super();
        this.platformVersion = platformVersion;
        this.deviceName = deviceName;
    }

    @ConcurrentParameterized.Parameters
    public static LinkedList devicesStrings() {
        LinkedList devices = new LinkedList();
//        devices.add(new String[]{"8.0","iPhone 5s"});
//        devices.add(new String[]{"8.1","iPhone 5s"});
//        devices.add(new String[]{"8.2","iPhone 5s"});
//        devices.add(new String[]{"8.3","iPhone 5s"});
        devices.add(new String[]{"7.1", "iPad Retina (64-bit) Simulator"});
        devices.add(new String[]{"9.0", "iPhone 4s Simulator"});
        devices.add(new String[]{"9.1", "iPhone 5s Simulator"});
        devices.add(new String[]{"9.2", "iPhone 6 Simulator"});
        
        devices.add(new String[]{"9.3", "iPhone 6s Plus Device"});
        devices.add(new String[]{"9.3", "iPhone 6s Device"});
        devices.add(new String[]{"9.3", "iPhone 6 Device"});
        devices.add(new String[]{"9.3", "iPad Pro Device"});
        devices.add(new String[]{"9.3", "iPad Mini 4 Device"});
        
        return devices;
    }

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.iphone();
        capabilities.setCapability("build", "Parallel iOS Native App Test Suite");
        capabilities.setCapability("name", "Parallel iOS Native App Test Suite");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.APP, "sauce-storage:UICatalog.zip");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 180);
        capabilities.setCapability(MobileCapabilityType.DEVICE_READY_TIMEOUT, 60);
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("appiumVersion", "1.4.16");
        capabilities.setCapability("sendKeyStrategy", "setValue"); //fastest typing method
        capabilities.setCapability("noReset", true); //to reuse the simulator/installed app between tests, rather than restart sim

        driver = new IOSDriver(new URL("http://" + SAUCE_USERNAME + ":" + SAUCE_KEY + "@ondemand.saucelabs.com:80/wd/hub")
                , capabilities);
        sessionId = (driver.getSessionId()).toString();
        wait = new WebDriverWait(driver, 15);

        //wait until main view loads
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(MobileBy.AccessibilityId("date_picker_button"))));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Test
    public void datePickerTest() throws Exception {
        // Using Joda Time DateTime and DateTimeFormatter for this test!
        // Creating test data - using +3 days, hours and minutes
        // to make sure that all wheels are used
        DateTime testDate = new DateTime().plusDays(3).plusHours(3).plusMinutes(3);

        // hour of the day - short format
        // i tried to get it using .hourOfDay().getAsShortText() but it would only return 24hr format...
        DateTimeFormatter hourOfDayFormat = DateTimeFormat.forPattern("K");

        // half day of day - AM or PM
        // have to use a new formatter for this as there is no getter - let me know if I'm wrong!
        DateTimeFormatter halfDayFormat = DateTimeFormat.forPattern("aa");

        // short date - example "Sun Dec 27"
        // to be used with the date picker in gregorian calendar
        DateTimeFormatter shortDateFormat = DateTimeFormat.forPattern("E MMM d");

        // longDate - example pattern "Dec 27, 2015, 5:55 PM"
        // to be used for assertion at the end of the test
        DateTimeFormatter longDateFormat = DateTimeFormat.forPattern("MMM d, yyyy, K:mm aa");

        final String hour = testDate.toString(hourOfDayFormat);
        final String minute = testDate.minuteOfHour().getAsString();
        final String shortDate = testDate.toString(shortDateFormat);
        final String longDate = testDate.toString(longDateFormat);
        final String halfDay = testDate.toString(halfDayFormat);


        driver.findElement(MobileBy.AccessibilityId("date_picker_button"))
                .click();
        //wait for Date Picker view to load
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(MobileBy.className("UIAPickerWheel"))));

        List<WebElement> pickerWheel_elements = driver.findElements(MobileBy.className("UIAPickerWheel"));
        pickerWheel_elements.get(0).sendKeys(shortDate);
        pickerWheel_elements.get(1).sendKeys(hour);
        pickerWheel_elements.get(2).sendKeys(minute);
        pickerWheel_elements.get(3).sendKeys(halfDay);

        String dateValidation_element = driver.findElement(MobileBy.AccessibilityId("current_date")).getText();
        assertThat(dateValidation_element, is(longDate));
    }

    @Test
    public void handlingSimpleAlertTest() {
        final String expected_alert_text = "A Short Title Is Best A message should be a short, complete sentence.";

        driver.findElement(MobileBy.AccessibilityId("alert_views_button"))
                .click();
        //wait for alert view to load by waiting for "simple" alert button
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(MobileBy.AccessibilityId("simple_alert_button"))))
                //and click on it
                .click();
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String titleAndMessage = alert.getText();

        assertThat(titleAndMessage, is(expected_alert_text));
        alert.accept();
    }

    @Test
    public void textInputAlertTest() {
        driver.findElement(MobileBy.AccessibilityId("alert_views_button"))
                .click();
        driver.findElement(MobileBy.AccessibilityId("text_entry_alert_button"))
                .click();
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String titleAndMessage = alert.getText();
        assertThat(titleAndMessage, is("A Short Title Is Best A message should be a short, complete sentence."));

        //input text
        String text_alert_message = "testing alert text input field";

        alert.sendKeys(text_alert_message);
        String alertTextInputField_value = driver.findElement(MobileBy.xpath("//UIAAlert//UIATextField")).getText();
        assertThat(alertTextInputField_value, is(text_alert_message));
    }
}

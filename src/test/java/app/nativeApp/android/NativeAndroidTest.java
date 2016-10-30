package app.nativeApp.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

//Test suite for a native Android app
public class NativeAndroidTest {

    private static String SAUCE_USERNAME = "yahyacool";
    private static String SAUCE_KEY = "27fc7db7-b47d-47b3-be82-95d7a8ecd658";
    private static AppiumDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setUp() throws Exception {

        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability("build","Native Android Test Suite");
        capabilities.setCapability("name", "Samsung Galaxy S6 Device");

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung Galaxy S6 Device");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.APP, "sauce-storage:ApiDemos-debug.apk");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "io.appium.android.apis");
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "ApiDemos");
        capabilities.setCapability(MobileCapabilityType.APP_WAIT_ACTIVITY, "ApiDemos");
        capabilities.setCapability(MobileCapabilityType.DEVICE_READY_TIMEOUT, 40);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 180);
        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.4.16");
        capabilities.setCapability("deviceOrientation", "portrait");

        driver = new AndroidDriver(new URL("http://" + SAUCE_USERNAME + ":" + SAUCE_KEY + "@ondemand.saucelabs.com:80/wd/hub")
                ,capabilities);
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void tearDown() throws Exception {
        driver.resetApp();
    }

    @AfterClass
    public static void classTeardown() {
        driver.quit();
    }

    @Test
    public void contextMenuTest() {
        //launch the app, wait for app to fully load by waiting for an element
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("android:id/action_bar_title"))));

        //switch to required activity
        ((AndroidDriver<WebElement>)driver).startActivity("io.appium.android.apis", ".app.FragmentContextMenu");

        WebElement longPress_element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("io.appium.android.apis:id/long_press"))));
        TouchAction longPress_action = new TouchAction(driver);
        longPress_action.longPress(longPress_element, 2000).perform();

        //assert that the dialog menu is displayed after the long press
        assertThat(driver.findElement(By.id("android:id/select_dialog_listview")).isDisplayed(), is(true));
    }

    @Test
    public void dragAndDropOnFirstElementTest() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("android:id/action_bar_title"))));
        ((AndroidDriver<WebElement>)driver).startActivity("io.appium.android.apis", ".view.DragAndDropDemo");

        //find elements and store them for later use
        WebElement draggable_element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"))));
        WebElement rightDroppable_element = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_2"));

        //create a drag and drop action using TouchAction
        TouchAction action = new TouchAction(driver);
        action.longPress(draggable_element, 1000).moveTo(rightDroppable_element).release().perform();


        String result_text = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
        String drag_text = driver.findElement(By.id("io.appium.android.apis:id/drag_text")).getText();

        assertThat(result_text, is("Dropped!"));
        assertThat(drag_text, containsString("app:id/drag_dot_1"));
    }

    @Test
    public void setOrientationToLandscapeTest() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("android:id/action_bar_title"))));
        ((AndroidDriver<WebElement>)driver).startActivity("io.appium.android.apis", ".app.ScreenOrientation");

        setOrientation("LANDSCAPE");

        ScreenOrientation currentOrientation = driver.getOrientation();
        assertThat(currentOrientation, is(ScreenOrientation.LANDSCAPE));
    }

    @Test
    public void setOrientationToPortraitTest() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("android:id/action_bar_title"))));
        ((AndroidDriver<WebElement>)driver).startActivity("io.appium.android.apis", ".app.ScreenOrientation");
        setOrientation("PORTRAIT");

        ScreenOrientation currentOrientation = driver.getOrientation();
        assertThat(currentOrientation, is(ScreenOrientation.PORTRAIT));
    }


    public void setOrientation(String orientation) {
        //wait until dropdown button is visible and click on it to expand the dropdown list
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("io.appium.android.apis:id/orientation"))))
                .click();

        //get a list of VISIBLE options (you will need to scroll down to access the elements out of view)
        List<WebElement> dropdownOptions = driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().className(android.widget.CheckedTextView)"));

        for (WebElement ele : dropdownOptions) {
            if (ele.getText().equals(orientation)) {
                ele.click();
                break;
            }
        }
    }
}

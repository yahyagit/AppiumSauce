package browser.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class AndroidWebTest {

	private static String SAUCE_USERNAME = "yahyacool";
    private static String SAUCE_KEY = "27fc7db7-b47d-47b3-be82-95d7a8ecd658";
    private AppiumDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Emulator");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Browser");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("appiumVersion", "1.4.16");

        this.driver = new AndroidDriver(new URL("http://" + SAUCE_USERNAME + ":" + SAUCE_KEY + "@ondemand.saucelabs.com:80/wd/hub")
                ,capabilities);
    }

    @Test
    public void androidWebTest() throws Exception {
        driver.get("http://www.amazon.com/");
        assertEquals("Amazon.com", driver.getTitle());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}

package browser.desktop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class WebDriverDemoShootoutTest {

	private static String SAUCE_USERNAME = "yahyacool";
    private static String SAUCE_KEY = "27fc7db7-b47d-47b3-be82-95d7a8ecd658";
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("build", "WebDriver Demo Shootout Test Suite");
        capabilities.setCapability("version", "17");
        capabilities.setCapability("platform", Platform.XP);
        this.driver = new RemoteWebDriver(new URL("http://" + SAUCE_USERNAME + ":" + SAUCE_KEY + "@ondemand.saucelabs.com:80/wd/hub")
                ,capabilities);
        driver.get("http://tutorialapp.saucelabs.com");
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testLoginFailsWithBadCredentials() throws Exception {
        String userName = getUniqueId();
        String password = getUniqueId();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("login"))).sendKeys(userName);;
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("input.login")).click();
        assertNotNull("Text not found", driver.findElement(By.id("message")));
    }

    @Test
    public void testLogout() throws Exception {
        Map<String, String> userDetails = createRandomUser();
        doRegister(userDetails, true);
    }

    @Test
    public void testLogin() throws Exception {
        Map<String, String> userDetails = createRandomUser();
        doRegister(userDetails, true);
        doLogin(userDetails.get("username"), userDetails.get("password"));
    }

    @Test
    public void testRegister() throws Exception {
        Map<String, String> userDetails = createRandomUser();
        doRegister(userDetails, false);
        assertTrue("Message not found", driver.findElement(By.cssSelector(".username")).getText().contains("You are logged in as "));
    }

    @Test
    public void testRegisterFailsWithoutUsername() throws Exception {
        Map<String, String> userDetails = createRandomUser();
        userDetails.put("username", "");
        doRegister(userDetails, false);
        assertEquals("Message not found", "Please enter a value", driver.findElement(By.cssSelector(".error")).getText());

    }

    @Test
    public void testRegisterFailsWithoutName() throws Exception {
        Map<String, String> userDetails = createRandomUser();
        userDetails.put("name", "");
        doRegister(userDetails, false);
        assertEquals("Message not found", "Please enter a value", driver.findElement(By.cssSelector(".error")).getText());
    }

    @Test
    public void testRegisterFailsWithMismatchedPasswords() throws Exception {
        Map<String, String> userDetails = createRandomUser();
        userDetails.put("confirm_password", getUniqueId());
        doRegister(userDetails, false);
        assertEquals("Message not found", "Fields do not match", driver.findElement(By.cssSelector(".error")).getText());
    }

    @Test
    public void testRegisterFailsWithBadEmail() throws Exception {
        Map<String, String> userDetails = createRandomUser();
        userDetails.put("email", "test");
        doRegister(userDetails, false);
        assertEquals("Message not found", "An email address must contain a single @", driver.findElement(By.cssSelector(".error")).getText());
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("@example.com");
        driver.findElement(By.id("form.submitted")).click();
        assertEquals("Message not found", "The username portion of the email address is invalid (the portion before the @: )", driver.findElement(By.cssSelector(".error")).getText());
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("test@example");
        driver.findElement(By.id("form.submitted")).click();
        assertEquals("Message not found", "The domain portion of the email address is invalid (the portion after the @: example)", driver.findElement(By.cssSelector(".error")).getText());
    }

    private String getUniqueId() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }

    private void doRegister(Map<String, String> userDetails, boolean logout) {
        userDetails.put("confirm_password", userDetails.get("confirm_password") != null ?
                userDetails.get("confirm_password") : userDetails.get("password"));
        driver.get("http://tutorialapp.saucelabs.com/register");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username"))).sendKeys(userDetails.get("username"));
        driver.findElement(By.id("password")).sendKeys(userDetails.get("password"));
        driver.findElement(By.id("confirm_password")).sendKeys(userDetails.get("confirm_password"));
        driver.findElement(By.id("name")).sendKeys(userDetails.get("name"));
        driver.findElement(By.id("email")).sendKeys(userDetails.get("email"));
        driver.findElement(By.id("form.submitted")).click();

        if (logout) {
            doLogout();
        }
    }

    private void doLogout() {
        driver.get("http://tutorialapp.saucelabs.com/logout");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message")));
        assertEquals("Message not found", "Logged out successfully.", driver.findElement(By.id("message")).getText());
    }

    private Map<String, String> createRandomUser() {
        Map<String, String> userDetails = new HashMap<String, String>();
        String fakeId = getUniqueId();
        userDetails.put("username", fakeId);
        userDetails.put("password", "testpass");
        userDetails.put("name", "Fake " + fakeId);
        userDetails.put("email", fakeId + "@example.com");
        return userDetails;
    }

    private void doLogin(String username, String password) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("login"))).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("input.login")).click();
        assertEquals("Message not found", "Logged in successfully.", driver.findElement(By.id("message")).getText());
    }

}

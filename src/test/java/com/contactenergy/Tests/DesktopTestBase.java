package com.contactenergy.Tests;

import com.galenframework.testng.GalenTestNgTestBase;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import com.contactenergy.helpers.SauceHelpers;
import com.galenframework.config.GalenConfig;
import com.galenframework.config.GalenProperty;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.rmi.UnexpectedException;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.remote.CapabilityType;

@Listeners({ SauceOnDemandTestListener.class })
public class DesktopTestBase extends GalenTestNgTestBase
		implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {
	private static final String ENV_URL = "https://contact-co-nz-566415730.ap-southeast-2.elb.amazonaws.com";
	public static final String USERNAME = "pocdtl";// System.getenv("SAUCE_USERNAME");chris_kennedy
	public static final String ACCESS_KEY = "b5ea05fc-e246-4313-9d03-51d2a8ce5d24";// System.getenv("SAUCE_ACCESS_KEY");455a510b-ec8a-4cfb-a757-df9c73df48d3

	private final SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(USERNAME, ACCESS_KEY);
	private final ThreadLocal<String> sessionId = new ThreadLocal<>();

	public String getSessionId() {
		return sessionId.get();
	}

	@Override
	public SauceOnDemandAuthentication getAuthentication() {
		return authentication;
	}

	@Override
	public WebDriver createDriver(Object[] args) {
		WebDriver driver = null;
		DesiredCapabilities caps = new DesiredCapabilities();
		if (args.length > 0) {
			if (args[0] != null && args[0] instanceof TestDevice) {
				TestDevice device = (TestDevice) args[0];
				caps.setCapability("browserName", device.getBrowser());
				caps.setCapability("platform", device.getPlatform());
				caps.setCapability("version", device.getVersion());
				caps.setCapability("deviceOrientation", device.getDeviceOrientation());
				caps.setCapability("screenResolution", device.getScreenResolution());
				String jobName = device.getCalledMethod() + "_" + device.getPlatform() + "_"
						+ device.getScreenResolution() + "_" + device.getBrowser().toUpperCase() + "_"
						+ device.getVersion();
				caps.setCapability("name", jobName.replaceAll(" ", "_"));
				caps.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));
				// caps.setCapability("secureSsl", false);
			}
		}
		try {
			driver = new RemoteWebDriver(
					new URL("https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		System.out.println(String.format("SauceOnDemandSessionID=%s job-name=%s",
				((RemoteWebDriver) driver).getSessionId(), caps.getCapability("name")));

		String id = ((RemoteWebDriver) driver).getSessionId().toString();
		sessionId.set(id);
		SauceHelpers.addSauceConnectTunnelId(caps);
		return driver;

	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.get().quit();
	}

	public void load(String uri) {
		getDriver().get(ENV_URL + uri);
		getDriver().manage().window().maximize();
	}

	@DataProvider(name = "devices", parallel = true)
	public Object[][] devices(Method testMethod) throws FileNotFoundException, JSONException {

		// get JSON from CI or load local
		String json = System.getenv("SAUCE_ONDEMAND_BROWSERS");
		if (json == null || json.isEmpty())
			json = new Scanner(new File("desktop-os-browser-compatibility.json")).useDelimiter("\\Z").next();

		JSONArray browserArray = new JSONArray(json);
		Object[][] testDevice = new Object[browserArray.length()][1];

		for (int i = 0; i < browserArray.length(); i++) {
			JSONObject browserJSON = browserArray.getJSONObject(i);

			if (browserJSON.has("device"))
				testDevice[i][0] = new TestDevice(browserJSON.get("browser"), browserJSON.get("os"),
						browserJSON.get("browser-version"), browserJSON.get("device"),
						browserJSON.get("device-orientation"), testMethod);
			else if (browserJSON.has("screenResolution"))
				testDevice[i][0] = new TestDevice(browserJSON.get("browser"), browserJSON.get("os"),
						browserJSON.get("browser-version"), browserJSON.get("screenResolution"), testMethod);
			else
				testDevice[i][0] = new TestDevice(browserJSON.get("browser"), browserJSON.get("os"),
						browserJSON.get("browser-version"), testMethod);
		}
		return testDevice;
	}

	public static class TestDevice {
		private final String browser;
		private final String platform;
		private final String version;
		private String deviceName;
		private String deviceOrientation;
		private String screenResolution;
		private final Method methodName;

		public TestDevice(Object browser, Object platform, Object version, Method methodName) {
			this.browser = browser.toString();
			this.platform = platform.toString();
			this.version = version.toString();
			this.methodName = methodName;
		}

		public TestDevice(Object browser, Object platform, Object version, Object screenResolution, Method methodName) {
			this.browser = browser.toString();
			this.platform = platform.toString();
			this.version = version.toString();
			this.screenResolution = screenResolution.toString();
			this.methodName = methodName;
		}

		public TestDevice(Object browser, Object platform, Object version, Object deviceName, Object deviceOrientation,
				Method methodName) {
			this.browser = browser.toString();
			this.platform = platform.toString();
			this.version = version.toString();
			this.deviceName = deviceName.toString();
			this.deviceOrientation = deviceOrientation.toString();
			this.methodName = methodName;
		}

		public String getDeviceName() {
			return deviceName;
		}

		public String getDeviceOrientation() {
			return deviceOrientation;
		}

		public String getScreenResolution() {
			return screenResolution;
		}

		public String getCalledMethod() {
			return methodName.getName();
		}

		public String getVersion() {
			return version;
		}

		public String getBrowser() {
			return browser;
		}

		public String getPlatform() {
			return platform;
		}

		public List<String> getTags() {
			List<String> tags = new ArrayList<>();
			if (getDeviceName() == null)
				tags.add("desktop");
			else
				tags.add("mobile");
			return tags;
		}

		@Override
		public String toString() {
			return String.format("%s %s %s %s %s %s", getPlatform() == null ? "" : getPlatform(),
					getBrowser() == null ? "" : getBrowser(), getVersion() == null ? "" : getVersion(),
					getDeviceName() == null ? "" : getDeviceName(),
					getDeviceOrientation() == null ? "" : getDeviceOrientation(),
					getScreenResolution() == null ? "" : getScreenResolution());
		}

	}

	static {
		GalenConfig.getConfig().setProperty(GalenProperty.SCREENSHOT_FULLPAGE, "true");
		GalenConfig.getConfig().setProperty(GalenProperty.GALEN_LOG_LEVEL, "0");
	}

}

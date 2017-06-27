package com.contactenergy.Tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class ContactBrowserTest extends DesktopTestBase {

	// @Test(dataProvider = "devices")
	public void ContactBrowserTests_onDesktop(TestDevice device) throws IOException, Exception {
		load("/AboutUs");
		checkLayout("resources/specs/ContactBrowserTest.spec", device.getTags());
	}

	@Test(dataProvider = "devices")
	public void ResidentialJoinJourney_onDesktop(TestDevice device) throws IOException, Exception {
		load("/Residential/Find-a-Plan");

		Assert.assertTrue("Navigated to Residential Join Page",
				driver.get().findElement(By.xpath("//label[contains(.,'Enter your address')]")).isDisplayed());

		driver.get().findElement(By.xpath("//label[contains(.,'Enter your address')]")).click();

		driver.get().findElement(By.xpath("//input[contains(@autocomplete,'off')]"))
				.sendKeys("1 Bressay Way, Waikanae   5036");

		WebDriverWait wait = new WebDriverWait(driver.get(), 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ui-menu-item']")));

		driver.get().findElement(By.xpath("//li[@class='ui-menu-item']")).click();

		driver.get().findElement(By.xpath("//label[contains(@for,'ELEC')]")).click();

		scrollto("//h2[@class='form-subtitle']");
		driver.get().findElement(By.xpath("//label[contains(@for,'Three')]")).click();

		driver.get().findElement(By.xpath("//label[@for='WaterHeatingMethod _Gas']")).click();

		scrollto("//label[@for='WaterHeatingMethod']");
		driver.get().findElement(By.xpath("//label[@for='Property[PropertyInfo][ElectricityUserType] _Standard']"))
				.click();

		System.out.println(driver.get().findElement(By.cssSelector(".message")).getText());

		scrollto("//h2[@class='recommendedplan-title']");

		driver.get().findElement(By.xpath("//a[@data-filter='PPD']")).click();

		scrollto("//h2[@class='recommendedplan-title sub']");
		driver.get().findElement(By.xpath("//h2[contains(.,'Certainty Plus')]")).click();

		scrollandclick("//a[@class='btn btn-default plan-progress-btn']");

		if (driver.get().findElement(By.xpath("//h2[contains(.,'About You')]")).isDisplayed()) {
			System.out.println("User on second page filling his details on Join Journey");
		}

		driver.get().findElement(By.xpath("//label[@for='Customer[CustomerInfo][Title] _Mr']")).click();

		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][FirstName]']"))
				.sendKeys("AutoRegression");
		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][LastName]']"))
				.sendKeys("AutoRegression");

		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][EmailAddress]']"))
				.sendKeys("DPTSTTM@contactenergy.co.nz");
		driver.get().findElement(By.xpath("//label[@for='Confirmation[ReceiveNewsAndOffers]']")).click();

		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][PhoneNumber]']")).sendKeys("1231312232");
		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][DateOfBirth]']")).sendKeys("02/06/2002");
		scrollto("//h2[contains(.,'About your property')]");
		driver.get().findElement(By.xpath("//label[@for='Property[MoveInfo][IsMovingHouse]_NO']")).click();

		driver.get().findElement(By.xpath("//label[@for='Property[PropertyInfo][WithAnotherSupplier]_YES']")).click();

		scrollto("//label[@for='Property[PropertyInfo][AddressSameAsPostalAddress]']");
		driver.get().findElement(By.xpath("//label[@for='Property[PropertyInfo][HasPropertyHazard]_NO']")).click();

		driver.get().findElement(By.xpath("//label[@for='Property[MedicalInfo][HasVulnerablePerson]_NO']")).click();
		driver.get().findElement(By.xpath("//label[@for='Property[MedicalInfo][HasMedicalDependant]_NO']")).click();

		scrollto("//h2[contains(.,'Extra discounts & rewards')]");
		driver.get().findElement(By.xpath("//label[@for='Promotion[PaperlessDiscount]_YES']")).click();
		driver.get().findElement(By.xpath("//label[@for='Promotion[JoinDirectDebit]_NO']")).click();

		scrollto("//h2[contains(.,'Final Steps')]");
		driver.get().findElement(By.xpath("//label[@for='Customer[HasDriversLicense]_NO']")).click();

		scrollandclick("//label[@for='Confirmation-AcceptPlanTermsAndConditions']");
		scrollandclick("//label[@for='Confirmation-AcceptGeneralTermsAndConditions']");

		scrollandclick("//input[@type='submit']");

		Assert.assertTrue("Navigated to Move House Success Page",
				driver.get().findElement(By.xpath("//h1[contains(.,'Thanks!')]")).isDisplayed());
	}

	@Test(dataProvider = "devices")
	public void MoveNewCustomerJourney_onDesktop(TestDevice device) throws IOException, Exception {

		load("/Residential/Electricity/Find-a-Plan/Moving-House");

		Assert.assertTrue("Navigated to Residential Moving House Page",
				driver.get().findElement(By.xpath("//h2[contains(.,'Moving House')]")).isDisplayed());

		scrollto("//h2[contains(.,'Already with Contact?')]");

		driver.get().findElement(By.xpath("//*[@id='app']/div[4]/div[1]/div/div/div[2]/div/a")).click();

		driver.get().findElement(By.xpath("//label[contains(.,'Enter your address')]")).click();

		driver.get().findElement(By.xpath("//input[contains(@autocomplete,'off')]"))
				.sendKeys("1 Bressay Way, Waikanae   5036");

		WebDriverWait wait = new WebDriverWait(driver.get(), 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ui-menu-item']")));

		driver.get().findElement(By.xpath("//li[@class='ui-menu-item']")).click();

		driver.get().findElement(By.xpath("//label[contains(@for,'ELEC')]")).click();

		scrollto("//h2[@class='form-subtitle']");
		driver.get().findElement(By.xpath("//label[contains(@for,'Three')]")).click();

		driver.get().findElement(By.xpath("//label[@for='WaterHeatingMethod _Gas']")).click();

		scrollto("//label[@for='WaterHeatingMethod']");
		driver.get().findElement(By.xpath("//label[@for='Property[PropertyInfo][ElectricityUserType] _Standard']"))
				.click();

		System.out.println(driver.get().findElement(By.cssSelector(".message")).getText());

		scrollto("//h2[@class='recommendedplan-title']");
		driver.get().findElement(By.xpath("//a[@data-filter='FIXED']")).click();

		scrollto("//h2[@class='recommendedplan-title sub']");
		driver.get().findElement(By.xpath("//h2[contains(.,'Certainty Plus')]")).click();

		scrollandclick("//a[@class='btn btn-default plan-progress-btn']");

		if (driver.get().findElement(By.xpath("//h2[contains(.,'About You')]")).isDisplayed()) {
			System.out.println("User on second page filling his details on Move Journey");
		}

		driver.get().findElement(By.xpath("//label[@for='Customer[CustomerInfo][Title] _Mr']")).click();

		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][FirstName]']"))
				.sendKeys("AutoRegression");
		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][LastName]']"))
				.sendKeys("AutoRegression");

		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][EmailAddress]']"))
				.sendKeys("DPTSTTM@contactenergy.co.nz");
		driver.get().findElement(By.xpath("//label[@for='Confirmation[ReceiveNewsAndOffers]']")).click();

		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][PhoneNumber]']")).sendKeys("1231312232");
		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][DateOfBirth]']")).sendKeys("02/06/2002");
		scrollto("//h2[contains(.,'About your property')]");
		driver.get().findElement(By.xpath("//label[@for='Property[MoveInfo][IsMovingHouse]_YES']")).click();

		driver.get().findElement(By.xpath("//input[@id='Property[MoveInfo][MoveInDate]']")).sendKeys("30/06/2017");
		scrollto("//label[@for='Property[PropertyInfo][AddressSameAsPostalAddress]']");
		driver.get().findElement(By.xpath("//label[@for='Property[PropertyInfo][HasPropertyHazard]_NO']")).click();

		scrollandclick("//label[@for='Property[MedicalInfo][HasVulnerablePerson]_NO']");
		driver.get().findElement(By.xpath("//label[@for='Property[MedicalInfo][HasMedicalDependant]_NO']")).click();

		driver.get().findElement(By.xpath("//label[@for='Promotion[PaperlessDiscount]_YES']")).click();
		driver.get().findElement(By.xpath("//label[@for='Promotion[JoinDirectDebit]_NO']")).click();

		driver.get().findElement(By.xpath("//label[@for='Customer[HasDriversLicense]_NO']")).click();

		scrollandclick("//label[@for='Confirmation-AcceptPlanTermsAndConditions']");
		scrollandclick("//label[@for='Confirmation-AcceptGeneralTermsAndConditions']");

		scrollandclick("//input[@type='submit']");
		Assert.assertTrue("Navigated to Move House Success Page",
				driver.get().findElement(By.xpath("//h1[contains(.,'Thanks!')]")).isDisplayed());
	}

	@Test(dataProvider = "devices")
	public void ExistingCustomerMovingHouseJourney_onDesktop(TestDevice device) throws IOException, Exception {

		load("/Residential/Electricity/Find-a-Plan/Moving-House");

		Assert.assertTrue("Navigated to Residential Moving House Page",
				driver.get().findElement(By.xpath("//h2[contains(.,'Moving House')]")).isDisplayed());

		scrollto("//h2[contains(.,'Already with Contact?')]");

		driver.get().findElement(By.xpath("//*[@id='app']/div[4]/div[2]/div[1]/div/div/div[2]/div/a")).click();

		Assert.assertTrue("Navigated to Residential Moving House Existing Customer Make-Changes Page", driver.get()
				.findElement(By.xpath("//h2[contains(.,'Hi, what would you like to do today?')]")).isDisplayed());

		driver.get().findElement(By.xpath("//label[@for='UserJourneyAction_MoveHouse']")).click();
		driver.get().findElement(By.xpath("//input[@id='AccountNumber']")).sendKeys("1231312232");

		driver.get().findElement(By.xpath("//input[contains(@autocomplete,'off')]"))
				.sendKeys("1 Bressay Way, Waikanae   5036");

		WebDriverWait wait = new WebDriverWait(driver.get(), 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ui-menu-item']")));

		driver.get().findElement(By.xpath("//li[@class='ui-menu-item']")).click();

		driver.get().findElement(By.xpath("//label[contains(@for,'ELEC')]")).click();
		driver.get().findElement(By.xpath("//label[contains(@for,'GAS')]")).click();

		scrollto("//h2[@class='form-subtitle']");
		driver.get().findElement(By.xpath("//label[contains(@for,'Three')]")).click();

		driver.get().findElement(By.xpath("//label[@for='WaterHeatingMethod _Gas']")).click();

		scrollto("//label[@for='WaterHeatingMethod']");
		driver.get().findElement(By.xpath("//label[@for='Property[PropertyInfo][ElectricityUserType] _Standard']"))
				.click();

		scrollto("//h2[@class='recommendedplan-title']");
		System.out.println(driver.get().findElement(By.cssSelector(".message")).getText());

		driver.get().findElement(By.xpath("//a[@data-filter='FIXED']")).click();

		scrollto("//h2[@class='recommendedplan-title sub']");
		driver.get().findElement(By.xpath("//h2[contains(.,'Certainty Plus')]")).click();

		scrollandclick("//a[@class='btn btn-default plan-progress-btn']");

		if (driver.get().findElement(By.xpath("//h2[contains(.,'About You')]")).isDisplayed()) {
			System.out.println("User on second page filling his details on existing customer Move house Journey");
		}

		driver.get().findElement(By.xpath("//label[@for='Customer[CustomerInfo][Title] _Mr']")).click();

		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][FirstName]']"))
				.sendKeys("AutoRegression");
		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][LastName]']"))
				.sendKeys("AutoRegression");

		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][EmailAddress]']"))
				.sendKeys("DPTSTTM@contactenergy.co.nz");

		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][PhoneNumber]']")).sendKeys("1231312232");
		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][DateOfBirth]']")).sendKeys("02/06/2002");

		driver.get().findElement(By.xpath("//label[contains(.,'Your current address')]")).click();

		driver.get().findElement(By.xpath("//input[contains(@autocomplete,'off')]"))
				.sendKeys("2 Bressay Way, Waikanae   5036");

		WebDriverWait wait1 = new WebDriverWait(driver.get(), 15);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ui-menu-item']")));

		driver.get().findElement(By.xpath("//li[@class='ui-menu-item']")).click();

		scrollto("//h2[contains(.,'About your property')]");
		driver.get().findElement(By.xpath("//input[@id='Property[MoveInfo][MoveOutDate]']")).sendKeys("30/06/2017");
		driver.get().findElement(By.xpath("//label[@for='Property[PropertyInfo][HaveFinalMeterReading]_NO']")).click();
		driver.get().findElement(By.xpath("//label[@for='Property[PropertyInfo][SendMeterReader]_NO']")).click();

		driver.get().findElement(By.xpath("//input[@id='Property[MoveInfo][MoveInDate]']")).sendKeys("30/06/2017");

		driver.get().findElement(By.xpath("//label[@for='Property[PropertyInfo][HasPropertyHazard]_NO']")).click();

		driver.get().findElement(By.xpath("//label[@for='Property[MedicalInfo][HasVulnerablePerson]_NO']")).click();
		driver.get().findElement(By.xpath("//label[@for='Property[MedicalInfo][HasMedicalDependant]_NO']")).click();

		driver.get().findElement(By.xpath("//label[@for='Promotion[PaperlessDiscount]_YES']")).click();

		driver.get().findElement(By.xpath("//label[@for='Promotion[JoinDirectDebit]_NO']")).click();

		scrollandclick("//label[@for='Confirmation-AcceptPlanTermsAndConditions']");
		scrollandclick("//label[@for='Confirmation-AcceptGeneralTermsAndConditions']");

		scrollandclick("//input[@type='submit']");
		Assert.assertTrue("Navigated to Move House Success Page",
				driver.get().findElement(By.xpath("//h1[contains(.,'Thanks!')]")).isDisplayed());
	}

	@Test(dataProvider = "devices")
	public void ExistingCustomerAddPropertyJourney_onDesktop(TestDevice device) throws IOException, Exception {

		load("/Residential/Electricity/Find-a-Plan/Moving-House");

		Assert.assertTrue("Navigated to Residential Moving House Page",
				driver.get().findElement(By.xpath("//h2[contains(.,'Moving House')]")).isDisplayed());

		scrollto("//h2[contains(.,'Already with Contact?')]");

		driver.get().findElement(By.xpath("//*[@id='app']/div[4]/div[2]/div[1]/div/div/div[2]/div/a")).click();

		Assert.assertTrue("Navigated to Residential Moving House Existing Customer Make-Changes Page", driver.get()
				.findElement(By.xpath("//h2[contains(.,'Hi, what would you like to do today?')]")).isDisplayed());

		driver.get().findElement(By.xpath("//label[contains(.,'Adding a property')]")).click();
		driver.get().findElement(By.xpath("//input[@id='AccountNumber']")).sendKeys("1231312232");
		driver.get().findElement(By.xpath("//label[contains(.,'Enter the address of your additional property')]"))
				.click();

		driver.get().findElement(By.xpath("//input[contains(@autocomplete,'off')]"))
				.sendKeys("1 Bressay Way, Waikanae   5036");

		WebDriverWait wait = new WebDriverWait(driver.get(), 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ui-menu-item']")));

		driver.get().findElement(By.xpath("//li[@class='ui-menu-item']")).click();

		driver.get().findElement(By.xpath("//label[contains(@for,'ELEC')]")).click();
		driver.get().findElement(By.xpath("//label[contains(@for,'GAS')]")).click();

		scrollto("//h2[@class='form-subtitle']");
		driver.get().findElement(By.xpath("//label[contains(@for,'Three')]")).click();

		driver.get().findElement(By.xpath("//label[@for='WaterHeatingMethod _Gas']")).click();

		scrollto("//label[@for='WaterHeatingMethod']");
		driver.get().findElement(By.xpath("//label[@for='Property[PropertyInfo][ElectricityUserType] _Standard']"))
				.click();
		System.out.println(driver.get().findElement(By.cssSelector(".message")).getText());

		scrollto("//h2[@class='recommendedplan-title']");
		driver.get().findElement(By.xpath("//a[@data-filter='FIXED']")).click();

		scrollto("//h2[@class='recommendedplan-title sub']");
		driver.get().findElement(By.xpath("//h2[contains(.,'Certainty Plus')]")).click();

		scrollandclick("//a[@class='btn btn-default plan-progress-btn']");

		if (driver.get().findElement(By.xpath("//h2[contains(.,'About You')]")).isDisplayed()) {
			System.out.println("User on second page filling his details on Move Journey");
		}

		driver.get().findElement(By.xpath("//label[@for='Customer[CustomerInfo][Title] _Mr']")).click();

		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][FirstName]']"))
				.sendKeys("AutoRegression");
		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][LastName]']"))
				.sendKeys("AutoRegression");

		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][EmailAddress]']"))
				.sendKeys("DPTSTTM@contactenergy.co.nz");

		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][PhoneNumber]']")).sendKeys("1231312232");
		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][DateOfBirth]']")).sendKeys("02/06/2002");

		scrollto("//h2[contains(.,'About your property')]");
		driver.get().findElement(By.xpath("//input[@id='Property[MoveInfo][MoveInDate]']")).sendKeys("30/06/2017");

		driver.get().findElement(By.xpath("//label[@for='Property[PropertyInfo][IsBach]_YES']")).click();

		driver.get().findElement(By.xpath("//label[@for='Property[PropertyInfo][HasPropertyHazard]_NO']")).click();
		scrollto("//label[@for='Property[PropertyInfo][HasPropertyHazard]_NO']");
		driver.get().findElement(By.xpath("//label[@for='Property[MedicalInfo][HasVulnerablePerson]_NO']")).click();
		driver.get().findElement(By.xpath("//label[@for='Property[MedicalInfo][HasMedicalDependant]_NO']")).click();
		scrollto("//h2[contains(.,'Extra discounts & rewards')]");
		driver.get().findElement(By.xpath("//label[@for='Promotion[PaperlessDiscount]_YES']")).click();
		driver.get().findElement(By.xpath("//label[@for='Promotion[JoinDirectDebit]_NO']")).click();

		scrollandclick("//label[@for='Confirmation-AcceptPlanTermsAndConditions']");
		scrollandclick("//label[@for='Confirmation-AcceptGeneralTermsAndConditions']");

		scrollandclick("//input[@type='submit']");

		Assert.assertTrue("Navigated to Move House Success Page",
				driver.get().findElement(By.xpath("//h1[contains(.,'Thanks!')]")).isDisplayed());
	}

	@Test(dataProvider = "devices")
	public void ExistingCustomerChangePlanJourney_onDesktop(TestDevice device) throws IOException, Exception {

		load("/Residential/Electricity/Find-a-Plan/Moving-House");

		Assert.assertTrue("Navigated to Residential Moving House Page",
				driver.get().findElement(By.xpath("//h2[contains(.,'Moving House')]")).isDisplayed());

		scrollto("//h2[contains(.,'Already with Contact?')]");

		driver.get().findElement(By.xpath("//*[@id='app']/div[4]/div[2]/div[1]/div/div/div[2]/div/a")).click();

		Assert.assertTrue("Navigated to Residential Moving House Existing Customer Make-Changes Page", driver.get()
				.findElement(By.xpath("//h2[contains(.,'Hi, what would you like to do today?')]")).isDisplayed());

		driver.get().findElement(By.xpath("//label[contains(.,'Changing my plan')]")).click();
		driver.get().findElement(By.xpath("//input[@id='AccountNumber']")).sendKeys("1231312232");
		driver.get().findElement(By.xpath("//label[contains(.,'Enter your address')]")).click();

		driver.get().findElement(By.xpath("//input[contains(@autocomplete,'off')]"))
				.sendKeys("1 Bressay Way, Waikanae   5036");

		WebDriverWait wait = new WebDriverWait(driver.get(), 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ui-menu-item']")));

		driver.get().findElement(By.xpath("//li[@class='ui-menu-item']")).click();

		driver.get().findElement(By.xpath("//label[contains(@for,'ELEC')]")).click();
		driver.get().findElement(By.xpath("//label[contains(@for,'GAS')]")).click();

		scrollto("//h2[@class='form-subtitle']");
		driver.get().findElement(By.xpath("//label[contains(@for,'Three')]")).click();

		driver.get().findElement(By.xpath("//label[@for='WaterHeatingMethod _Gas']")).click();

		scrollto("//label[@for='WaterHeatingMethod']");
		driver.get().findElement(By.xpath("//label[@for='Property[PropertyInfo][ElectricityUserType] _Standard']"))
				.click();

		System.out.println(driver.get().findElement(By.cssSelector(".message")).getText());

		scrollto("//h2[@class='recommendedplan-title']");
		driver.get().findElement(By.xpath("//a[@data-filter='FIXED']")).click();

		scrollto("//h2[@class='recommendedplan-title sub']");
		driver.get().findElement(By.xpath("//h2[contains(.,'Certainty')]")).click();

		scrollandclick("//a[@class='btn btn-default plan-progress-btn']");

		if (driver.get().findElement(By.xpath("//h2[contains(.,'About You')]")).isDisplayed()) {
			System.out.println("User on second page filling his details on Move Journey");
		}

		driver.get().findElement(By.xpath("//label[@for='Customer[CustomerInfo][Title] _Mr']")).click();

		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][FirstName]']"))
				.sendKeys("AutoRegression");
		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][LastName]']"))
				.sendKeys("AutoRegression");

		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][EmailAddress]']"))
				.sendKeys("DPTSTTM@contactenergy.co.nz");
		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][PhoneNumber]']")).sendKeys("1231312232");
		driver.get().findElement(By.xpath("//input[@id='Customer[CustomerInfo][DateOfBirth]']")).sendKeys("02/06/2002");
		scrollto("//h2[contains(.,'Extra discounts & rewards')]");
		driver.get().findElement(By.xpath("//label[@for='Promotion[PaperlessDiscount]_YES']")).click();
		driver.get().findElement(By.xpath("//label[@for='Promotion[JoinDirectDebit]_NO']")).click();

		scrollandclick("//label[@for='Confirmation-AcceptPlanTermsAndConditions']");

		scrollandclick("//input[@type='submit']");

		Assert.assertTrue("MoveExistingCustomerChangePlanJourney Success Page",
				driver.get().findElement(By.xpath("//h1[contains(.,'Thanks!')]")).isDisplayed());
	}

	@Test(dataProvider = "devices")
	public void PayBill_onDesktop(TestDevice device) throws IOException, Exception {

		load("/Residential/Pay-Bill");

		if (driver.get().findElement(By.xpath("//h2[contains(.,'Pay by Credit Card')]")).isDisplayed()) {
			System.out.println("User on second page filling his details on Account Details");
			driver.get().findElement(By.xpath("//input[@id='Amount']")).sendKeys("1");
			driver.get().findElement(By.xpath("//input[@id='AccountNumber']")).sendKeys("123456789");
			driver.get().findElement(By.xpath("//input[@id='CustomerName']")).sendKeys("ABCDEFGHIJK");
			driver.get().findElement(By.xpath("//input[@id='Email']")).sendKeys("DPTSTTM@contactenergy.co.nz");

			driver.get().findElement(By.xpath("//h2[contains(.,'Pay by Credit Card')]")).click();
			driver.get().findElement(By.xpath("//input[@value='Next']")).click();

			if (driver.get().findElement(By.xpath("//h2[contains(.,'Payment Checkout')]")).isDisplayed()) {
				System.out.println("User on Payment Expressway page filling his Credit Card details for Paying Bill");

				driver.get().findElement(By.xpath("//input[@name='CardNumber']")).sendKeys("4111111111111111");
				driver.get().findElement(By.xpath("//input[@name='CardHolderName']")).sendKeys("Automation Regression");
				driver.get().findElement(By.xpath("//input[@name='Cvc2']")).sendKeys("123");
				driver.get().findElement(By.xpath("//button[@class='DpsButton1 DpsPxPayOK']")).click();

				Assert.assertTrue("Navigated to Payment Success Page",
						driver.get()
								.findElement(By.xpath("//h1[contains(.,'Thanks! Your payment has been received.')]"))
								.isDisplayed());

			}
		}

	}

	@Test(dataProvider = "devices")
	public void ResidentialOrderBottleGas_onDesktop(TestDevice device) throws IOException, Exception {
		load("/Residential/Gas/Stock-up/Order-Bottled-Gas");

		Assert.assertTrue("Navigated to Residential Bottled Gas Order",
				driver.get().findElement(By.xpath("//h1[contains(.,'Bottled Gas Order')]")).isDisplayed());

		driver.get().findElement(By.xpath("//input[@id='AccountNumber']")).sendKeys("123456789");
		driver.get().findElement(By.xpath("//input[@id='FirstName']")).sendKeys("ResidentialBottleGas");
		driver.get().findElement(By.xpath("//input[@id='LastName']")).sendKeys("AutoRegressionBottleGas");
		driver.get().findElement(By.xpath("//input[@id='EmailAddress']")).sendKeys("DPTSTTM@contactenergy.co.nz");
		driver.get().findElement(By.xpath("//input[@id='PhoneNumber']")).sendKeys("123456789");

		scrollto("//h3[contains(.,'Your Property & Outlet')]");
		driver.get().findElement(By.xpath("//label[contains(.,'Enter your address')]")).click();

		driver.get().findElement(By.xpath("//input[contains(@autocomplete,'off')]"))
				.sendKeys("1 Bressay Way, Waikanae   5036");

		WebDriverWait wait = new WebDriverWait(driver.get(), 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ui-menu-item']")));
		driver.get().findElement(By.xpath("//li[@class='ui-menu-item']")).click();

		selectoption("Region", "North");

		selectoption("Outlet", "FARNORTH");

		scrollto("//legend[contains(.,'Stock Up')]");
		selectoption("NumberOfCylinders", "1");

		driver.get().findElement(By.xpath("//textarea[@id='DeliveryInstructions']"))
				.sendKeys("Auto Regression Test for ordering Residential Bottle Gas");

		// if
		// (!driver.get().findElement(By.xpath("//div[contains(@class,'form-submit-border')]")).isEnabled())
		// {
		// driver.get().findElement(By.xpath("//div[contains(@class,'form-submit-border')]")).click();
		// driver.get().findElement(By.xpath("//input[@type='submit']")).click();
		// } else {

		driver.get().findElement(By.xpath("//input[@type='submit']")).click();
		// }

		Assert.assertTrue("Navigated to Residential Order Bottle Gas Success Page",
				driver.get().findElement(By.xpath("//h1[contains(.,'Thanks!')]")).isDisplayed());
	}

	public void selectoption(String selectoption, String selectvalue) {
		Select region = new Select(driver.get().findElement(By.id(selectoption)));
		List<WebElement> elementCount = region.getOptions();
		int iSize = elementCount.size();

		for (int i = 0; i < iSize; i++) {
			// String sValue = elementCount.get(i).getText();
			// String getfirst = elementCount.get(1).getText();
			region.selectByValue(selectvalue);
		}
	}

	@Test(dataProvider = "devices")
	public void BusinessOrderBottleGas_onDesktop(TestDevice device) throws IOException, Exception {
		load("/Business/Order-LPG");

		Assert.assertTrue("Navigated to Business Bottled Gas Order", driver.get()
				.findElement(By.xpath("//legend[contains(.,'Your details and delivery details')]")).isDisplayed());

		driver.get().findElement(By.xpath("//label[contains(.,'Your Account Number')]"));

		driver.get()
				.findElement(
						By.xpath("//input[@id='wffmb46af2c670fd4482b8e0b9d10755a172_Sections_0__Fields_0__Value']"))
				.sendKeys("123456789");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffmb46af2c670fd4482b8e0b9d10755a172_Sections_0__Fields_1__Value']"))
				.sendKeys("ResidentialBottleGas");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffmb46af2c670fd4482b8e0b9d10755a172_Sections_0__Fields_2__Value']"))
				.sendKeys("DPTSTTM@contactenergy.co.nz");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffmb46af2c670fd4482b8e0b9d10755a172_Sections_0__Fields_3__Value']"))
				.sendKeys("123456789");

		scrollto("//h3[contains(.,'Delivery Address')]");
		driver.get().findElement(By.xpath("//label[contains(.,'Start typing your address')]")).click();

		driver.get().findElement(By.xpath("//input[contains(@autocomplete,'off')]"))
				.sendKeys("1 Bressay Way, Waikanae   5036");

		WebDriverWait wait = new WebDriverWait(driver.get(), 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ui-menu-item']")));
		driver.get().findElement(By.xpath("//li[@class='ui-menu-item']")).click();

		scrollto("//legend[contains(.,'What would you like to order today?')]");
		selectoption("wffmb46af2c670fd4482b8e0b9d10755a172_Sections_1__Fields_0__Value", "Send me 1 cylinder");

		driver.get()
				.findElement(
						By.xpath("//textarea[@id='wffmb46af2c670fd4482b8e0b9d10755a172_Sections_1__Fields_1__Value']"))
				.sendKeys("Auto Regression Test for ordering Business Bottle Gas");

		// driver.get().findElement(By.xpath("//fieldset[contains(.,'What would
		// you like to order today?')]")).click();

		// if
		// (!driver.get().findElement(By.xpath("//div[contains(@class,'form-submit-border
		// text-center')]"))
		// .isEnabled()) {
		// driver.get().findElement(By.xpath("//div[contains(@class,'form-submit-border
		// text-center')]")).click();
		// driver.get().findElement(By.xpath("//input[@type='submit']")).click();
		// } else {

		driver.get().findElement(By.xpath("//input[@type='submit']")).click();
		// }

		Assert.assertTrue("Navigated to Business Order Bottle Gas Success Page",
				driver.get().findElement(By.xpath("//h1[contains(.,'Thanks!')]")).isDisplayed());
	}

	@Test(dataProvider = "devices")
	public void SignIn_onDesktop(TestDevice device) throws IOException, Exception {

		load("/Account/Sign-In");

		driver.get().findElement(By.xpath("//a[@class='logo sign-in']")).click();

		Assert.assertTrue("Navigated to Sign-In Page",
				driver.get().findElement(By.xpath("//legend[contains(.,'Log in to My Account')]")).isDisplayed());

		driver.get().findElement(By.xpath("//input[@name='UserName']")).sendKeys("lewiebear");
		driver.get().findElement(By.xpath("//input[@name='Password']")).sendKeys("Contact@01");

		scrollandclick("//input[@type='submit']");

		Assert.assertTrue("Navigated to Account Summary Page",
				driver.get().findElement(By.xpath("//button[contains(.,'WAYS TO PAY')]")).isDisplayed());

	}

	@Test(dataProvider = "devices")
	public void BusinessAddPremisesJourney_onDesktop(TestDevice device) throws IOException, Exception {

		load("/Business/Add-Premises");

		scrollto("//legend[contains(.,'A few details about you')]");

		Assert.assertTrue("Navigated to Business Add Premises Page",
				driver.get().findElement(By.xpath("//legend[contains(.,'A few details about you')]")).isDisplayed());

		driver.get()
				.findElement(
						By.xpath("//input[@id='wffm6a38e4b99016497182c31dade512e220_Sections_0__Fields_0__Value']"))
				.sendKeys("AutoRegression");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffm6a38e4b99016497182c31dade512e220_Sections_0__Fields_1__Value']"))
				.sendKeys("AutoRegression");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffm6a38e4b99016497182c31dade512e220_Sections_0__Fields_2__Value']"))
				.sendKeys("DPTSTTM@contactenergy.co.nz");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffm6a38e4b99016497182c31dade512e220_Sections_0__Fields_3__Value']"))
				.sendKeys("1231312232");

		driver.get()
				.findElement(
						By.xpath("//input[@id='wffm6a38e4b99016497182c31dade512e220_Sections_1__Fields_0__Value']"))
				.sendKeys("1231312232");

		driver.get().findElement(By.xpath("//label[contains(.,'Start typing your address')]")).click();

		driver.get().findElement(By.xpath("//input[contains(@autocomplete,'off')]"))
				.sendKeys("1 Bressay Way, Waikanae   5036");

		WebDriverWait wait = new WebDriverWait(driver.get(), 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ui-menu-item']")));

		driver.get().findElement(By.xpath("//li[@class='ui-menu-item']")).click();

		// driver.get()
		// .findElement(
		// By.xpath("//input[@id='wffm6a38e4b99016497182c31dade512e220_Sections_2__Fields_0__Value']"))
		// .sendKeys("Adding Premises");
		scrollto("//legend[contains(.,'How else can we help you today?')]");
		driver.get()
				.findElement(
						By.xpath("//textarea[@id='wffm6a38e4b99016497182c31dade512e220_Sections_2__Fields_0__Value']"))
				.sendKeys("Auto Regression Test for Adding Business Premises");

		selectoption("wffm6a38e4b99016497182c31dade512e220_Sections_2__Fields_1__Value", "Business Freedom");

		scrollandclick("//input[@type='submit']");

		Assert.assertTrue("Navigated to Business Add Premises Success Page",
				driver.get().findElement(By.xpath("//h1[contains(.,'Roger that!')]")).isDisplayed());
	}

	@Test(dataProvider = "devices")
	public void BusinessMovePremisesJourney_onDesktop(TestDevice device) throws IOException, Exception {

		load("/Business/Move-Premises");

		scrollto("//legend[contains(.,'A few details about you')]");

		Assert.assertTrue("Navigated to Business Add Premises Page",
				driver.get().findElement(By.xpath("//legend[contains(.,'A few details about you')]")).isDisplayed());

		driver.get()
				.findElement(
						By.xpath("//input[@id='wffme6ce7e0dd0744175962be943e5857879_Sections_0__Fields_0__Value']"))
				.sendKeys("AutoRegression");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffme6ce7e0dd0744175962be943e5857879_Sections_0__Fields_1__Value']"))
				.sendKeys("AutoRegression");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffme6ce7e0dd0744175962be943e5857879_Sections_0__Fields_2__Value']"))
				.sendKeys("DPTSTTM@contactenergy.co.nz");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffme6ce7e0dd0744175962be943e5857879_Sections_0__Fields_3__Value']"))
				.sendKeys("1231312232");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffme6ce7e0dd0744175962be943e5857879_Sections_1__Fields_0__Value']"))
				.sendKeys("1231312232");

		driver.get().findElement(By.xpath("//label[contains(.,'Start typing your address')]")).click();

		driver.get().findElement(By.xpath("//input[contains(@autocomplete,'off')]"))
				.sendKeys("1 Bressay Way, Waikanae   5036");

		WebDriverWait wait = new WebDriverWait(driver.get(), 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ui-menu-item']")));

		driver.get().findElement(By.xpath("//li[@class='ui-menu-item']")).click();
		// driver.get()
		// .findElement(
		// By.xpath("//input[@id='wffme6ce7e0dd0744175962be943e5857879_Sections_2__Fields_0__Value']"))
		// .sendKeys("Moving Premises");
		scrollto("//legend[contains(.,'How else can we help you today?')]");
		driver.get()
				.findElement(
						By.xpath("//textarea[@id='wffme6ce7e0dd0744175962be943e5857879_Sections_2__Fields_0__Value']"))
				.sendKeys("Auto Regression Test for Moving Business Premises");

		selectoption("wffme6ce7e0dd0744175962be943e5857879_Sections_2__Fields_1__Value", "Business Freedom");

		scrollandclick("//input[@type='submit']");

		Assert.assertTrue("Navigated to Business Move Premises Success Page",
				driver.get().findElement(By.xpath("//h1[contains(.,'Roger that!')]")).isDisplayed());
	}

	@Test(dataProvider = "devices")
	public void BusinessJoinJourney_onDesktop(TestDevice device) throws IOException, Exception {

		load("/Business/Submit-an-Enquiry");

		scrollto("//legend[contains(.,'A few details about you')]");

		Assert.assertTrue("Navigated to Business Add Premises Page",
				driver.get().findElement(By.xpath("//legend[contains(.,'A few details about you')]")).isDisplayed());

		driver.get()
				.findElement(
						By.xpath("//input[@id='wffm0ecdf726e4874fcc90a1ce71dd457dd0_Sections_0__Fields_0__Value']"))
				.sendKeys("AutoRegression");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffm0ecdf726e4874fcc90a1ce71dd457dd0_Sections_0__Fields_1__Value']"))
				.sendKeys("AutoRegression");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffm0ecdf726e4874fcc90a1ce71dd457dd0_Sections_0__Fields_2__Value']"))
				.sendKeys("DPTSTTM@contactenergy.co.nz");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffm0ecdf726e4874fcc90a1ce71dd457dd0_Sections_0__Fields_3__Value']"))
				.sendKeys("1231312232");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffm0ecdf726e4874fcc90a1ce71dd457dd0_Sections_1__Fields_0__Value']"))
				.sendKeys("1231312232");
		driver.get().findElement(By.xpath("//label[contains(.,'Start typing your address')]")).click();

		driver.get().findElement(By.xpath("//input[contains(@autocomplete,'off')]"))
				.sendKeys("1 Bressay Way, Waikanae   5036");

		WebDriverWait wait = new WebDriverWait(driver.get(), 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ui-menu-item']")));

		driver.get().findElement(By.xpath("//li[@class='ui-menu-item']")).click();

		scrollto("//legend[contains(.,'How can we help you today?')]");

		selectoption("wffm0ecdf726e4874fcc90a1ce71dd457dd0_Sections_2__Fields_0__Value",
				"Sign up as a business customer");

		driver.get()
				.findElement(
						By.xpath("//textarea[@id='wffm0ecdf726e4874fcc90a1ce71dd457dd0_Sections_2__Fields_1__Value']"))
				.sendKeys("Auto Regression Test for Business Join");
		selectoption("wffm0ecdf726e4874fcc90a1ce71dd457dd0_Sections_2__Fields_2__Value", "Business Fuel Rewards Plus");

		scrollandclick("//input[@type='submit']");

		Assert.assertTrue("Navigated to Business Join Success Page",
				driver.get().findElement(By.xpath("//h1[contains(.,'Roger that!')]")).isDisplayed());
	}

	@Test(dataProvider = "devices")
	public void BusinessBottleGasSignUpJourney_onDesktop(TestDevice device) throws IOException, Exception {

		load("/Business/Bottled-Gas-Signup");

		scrollto("//legend[contains(.,'Tell us about your business')]");

		Assert.assertTrue("Navigated to Business SignUp for Order BottleGas Page", driver.get()
				.findElement(By.xpath("//legend[contains(.,'Tell us about your business')]")).isDisplayed());

		driver.get()
				.findElement(
						By.xpath("//input[@id='wffm6672788511ce4be19f84d4aea057a2ce_Sections_0__Fields_0__Value']"))
				.sendKeys("AutoRegressionBusiness Bottle Gas");

		driver.get().findElement(By.xpath("//label[contains(.,'Start typing your address')]")).click();

		driver.get().findElement(By.xpath("//input[contains(@autocomplete,'off')]"))
				.sendKeys("1 Bressay Way, Waikanae   5036");

		WebDriverWait wait = new WebDriverWait(driver.get(), 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ui-menu-item']")));

		driver.get().findElement(By.xpath("//li[@class='ui-menu-item']")).click();

		driver.get()
				.findElement(
						By.xpath("//textarea[@id='wffm6672788511ce4be19f84d4aea057a2ce_Sections_0__Fields_2__Value']"))
				.sendKeys("Auto Regression Test for Business Order Bottle SignUp");

		driver.get()
				.findElement(
						By.xpath("//input[@id='wffm6672788511ce4be19f84d4aea057a2ce_Sections_1__Fields_0__Value']"))
				.sendKeys("FirstNameAutoRegression");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffm6672788511ce4be19f84d4aea057a2ce_Sections_1__Fields_1__Value']"))
				.sendKeys("LastNameAutoRegression");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffm6672788511ce4be19f84d4aea057a2ce_Sections_1__Fields_2__Value']"))
				.sendKeys("JobAutoRegression");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffm6672788511ce4be19f84d4aea057a2ce_Sections_1__Fields_3__Value']"))
				.sendKeys("DPTSTTM@contactenergy.co.nz");
		driver.get()
				.findElement(
						By.xpath("//input[@id='wffm6672788511ce4be19f84d4aea057a2ce_Sections_1__Fields_4__Value']"))
				.sendKeys("1231312232");

		selectoption("wffm6672788511ce4be19f84d4aea057a2ce_Sections_1__Fields_5__Value",
				"I'd prefer a call in the morning");

		scrollandclick("//input[@type='submit']");

		Assert.assertTrue("Navigated to Business Bottle Gas Signup Success Page",
				driver.get().findElement(By.xpath("//h1[contains(.,'Roger that!')]")).isDisplayed());
	}

	public void scrollto(String elementxpath) {
		JavascriptExecutor je = (JavascriptExecutor) driver.get();
		WebElement element = driver.get().findElement(By.xpath(elementxpath));
		je.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollandclick(String elementxpath) {
		WebElement submit = driver.get().findElement(By.xpath(elementxpath));

		Point coordinates = submit.getLocation();
		int s1 = coordinates.getX();
		int s2 = coordinates.getY();
		JavascriptExecutor exe1 = (JavascriptExecutor) driver.get();
		exe1.executeScript("window.scroll(" + s1 + ", " + s2 + ");");
		exe1.executeScript("arguments[0].click();", submit);
	}

	public boolean isElementPresent(By locatorKey) {
		try {
			driver.get().findElement(locatorKey);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
}

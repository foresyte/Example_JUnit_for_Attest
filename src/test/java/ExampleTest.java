import com.deque.attest.AttestDriver;
import com.deque.attest.matchers.selectors.IFrameSelector;
import com.deque.attest.reporter.AttestReportingOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.deque.attest.matchers.IsAccessible.isAccessible;
import static com.deque.attest.matchers.IsAuditedForAccessibility.isAuditedForAccessibility;
import static org.hamcrest.MatcherAssert.assertThat;

import com.deque.attest.AttestConfiguration;


import com.deque.reporter.AttestReporter;
import com.deque.attest.Attest;
import com.deque.results.Results;


public class ExampleTest {

    AttestDriver page;
    private WebDriver webDriver;
    AttestConfiguration attestConfig = AttestConfiguration.configure();
    AttestReportingOptions arOptions = new AttestReportingOptions();

    @Before
    public void setUp() throws Exception {

        //attestConfig.forAuditSuite(auditConfiguration);

        attestConfig
          .testSuiteName("Corporate Demo")
          .userAgent("Chrome")
          .testMachine("RJS Macbook")
          .outputDirectory("/Users/richszymczak/repos/AttestIntegration/Example_JUnit");


        //AttestReportingOptions aro = new AttestReportingOptions();
        arOptions = arOptions.outputDirectory("/Users/richszymczak/repos/AttestIntegration/Example_JUnit");
        arOptions = arOptions.testSuiteName("RJS Example Test");
        arOptions = arOptions.uiState("production-home");

        System.setProperty("webdriver.chrome.driver", "/Users/richszymczak/repos/webdriver/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("disable-gpu");
        webDriver = new ChromeDriver(options);
        page = new AttestDriver(webDriver);

        webDriver.get("http://abcdcomputech.dequecloud.com/");
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }

    @Test
    public void itShouldAuditPageForAccessibility() throws Exception {
        arOptions.testSuiteName("Log generated from isAuditedForAccessibility()");
        assertThat(page, isAuditedForAccessibility().logResults(arOptions));

    }

    @Test
    public void itShouldTestPageForAccessibility() throws Exception {

        arOptions = arOptions.testSuiteName("RJS Example Test");

        assertThat(page, isAccessible().logResults(arOptions));

        // typically clicking through other pages, submitting a form and testing those scenarios
        // go to DqU scan the login page, fill out the login form using the Selenium objects / abilities
        // then scan the logged in page for accessibility issues

        // a single test typically have multiple scans and log all of those results

        // good practice - on a page, go through the interface, expose elements in different states in the app
        // test that single element (e.g mega menu, modal popup,
        // with Attest, instead of scanning whole page, utilize the API to tell me what has changed on the page
        // and discover / scan new content / modal and scan that element and what's inside of it

        // typically, these are tests that people already have, scenarios that we would leverage from their set of tests
        // we consult them by showing them how to leverage existing tests with an Accessibiltiy point of view
        // usually the customer has a bunch of tests and looking for how they can drop Attest into them



    }

    @Test
    public void itShouldTestPageWithOptions() throws Exception {
        assertThat(page, isAuditedForAccessibility().within("title").accordingTo("wcag2a"));
        // for example, this is an API method like Tony is talking about

        // make it able to scan multiple pages, each test have its own page (flexible)
        // shows going out to different sites or different parts of same site
        // understand -- what options are available with Attest
         // -- API methods,
        // custom rules and how they work
         // integrations with Jenkins / SonarQube
    }

    @Test
    public void itShouldTestLaptopPage() throws Exception {

        // navigate to another page, or try menu on page
        String url = webDriver.getCurrentUrl();
        webDriver.get(url + "/laptopsandnotebooks.php");
        //Navigation nav = webDriver.navigate();
        arOptions = arOptions.testSuiteName("RJS Laptop Page Test");
        assertThat(page, isAccessible().logResults(arOptions));


    }


    @Test
    public void itShouldTestShoppingCartPage() throws Exception {

        // navigate to another page, or try menu on page
        String url = webDriver.getCurrentUrl();
        webDriver.get(url + "/cart.php");
        arOptions = arOptions.testSuiteName("RJS Cart Page Test");

        assertThat(page, isAuditedForAccessibility().within("title").accordingTo("wcag2a"));
        assertThat(page, isAuditedForAccessibility().within("p").accordingTo("wcag2a"));
        assertThat(page, isAccessible().within("table").accordingTo("wcag2a"));

        assertThat(page, isAccessible().logResults(arOptions));


    }

    @Test
    public void itShouldTestSomeLoginPage() throws Exception {
        webDriver.get("https://signin.ebay.com/ws/eBayISAPI.dll?SignIn&ru=https%3A%2F%2Fwww.ebay.com%2F");

        arOptions = arOptions.testSuiteName("RJS ebay Login Page Test");

        //assertThat(page, isAccessible().within("table").accordingTo("wcag2a"));

        assertThat(page, isAccessible().logResults(arOptions).within("table").accordingTo("wcag2a"));

    }

    @Test
    public void itShouldTestDSGPage() throws Exception {

        //webDriver.get("https://signin.ebay.com/ws/eBayISAPI.dll?SignIn&ru=https%3A%2F%2Fwww.ebay.com%2F");
        webDriver.get("https://www.dickssportinggoods.com/OrderItemDisplay?calculationUsageId=-1&calculationUsageId=-2&calculationUsageId=-3&calculationUsageId=-7&updatePrices=1&doConfigurationValidation=Y&catalogId=12301&orderId=.&langId=-1&storeId=15108");

        arOptions = arOptions.testSuiteName("RJS DSG Page Test");

        //assertThat(page, isAccessible().within("table").accordingTo("wcag2a"));

        assertThat(page, isAccessible().logResults(arOptions).within("table").accordingTo("wcag2a"));

        // want to find a good example of a site to test that's using iframes
        //assertThat(page, isAccessible().logResults(arOptions).within(new IFrameSelector("#frame1", "selector")));

    }

}

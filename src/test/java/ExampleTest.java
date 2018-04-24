import com.deque.attest.AttestDriver;
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

    @Before
    public void setUp() throws Exception {

        AttestConfiguration.configure()
          .testSuiteName("Corporate Demo")
          .userAgent("Chrome")
          .testMachine("Dev Machine")
          .outputDirectory("/Users/richszymczak/repos/AttestIntegration/Example_JUnit");

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
        assertThat(page, isAuditedForAccessibility());

    }

    @Test
    public void itShouldTestPageForAccessibility() throws Exception {

        // RJS
        AttestReportingOptions aro = new AttestReportingOptions();
        aro = aro.outputDirectory("/Users/richszymczak/repos/AttestIntegration/Example_JUnit");
        aro = aro.testSuiteName("RJS Example Test");
        aro = aro.uiState("production-home");
        assertThat(page, isAccessible().logResults(aro));
    }

    @Test
    public void itShouldTestPageWithOptions() throws Exception {
        assertThat(page, isAuditedForAccessibility().within("title").accordingTo("wcag2a"));
    }
}

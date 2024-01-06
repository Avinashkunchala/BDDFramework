import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java", // Path to your feature files
        glue = {"src/test/java"}, // Path to your step definitions
        plugin = {"pretty", "html:target/cucumber-reports"}, // Plugins for report generation
        monochrome = true, // More readable console output
        tags = "@smoke" // Run scenarios tagged as "@smoke", you can change this as needed
)
public class TestRunner extends AbstractTestNGCucumberTests {
}

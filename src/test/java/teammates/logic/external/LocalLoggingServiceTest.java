package teammates.logic.external;

import teammates.test.BaseTestCase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import teammates.main.BranchCoverageInstrumentation;

/**
 * SUT: {@link LocalLoggingService}.
 */
public class LocalLoggingServiceTest extends BaseTestCase {

    @BeforeMethod
    public void refreshTestData() { 
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        BranchCoverageInstrumentation.printCoverageDataToFile(BranchCoverageInstrumentation.coverageFunction4, 4, 14);
    } 
}

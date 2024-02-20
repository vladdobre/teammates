package teammates.logic.external;

import teammates.test.BaseTestCase;

import teammates.common.datatransfer.logs.LogEvent;
import teammates.common.datatransfer.logs.RequestLogDetails;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import teammates.common.datatransfer.logs.LogDetails;
import teammates.main.BranchCoverageInstrumentation;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
/**
 * SUT: {@link LocalLoggingService}.
 */
public class LocalLoggingServiceTest extends BaseTestCase {

    @BeforeMethod
    public void refreshTestData() { 
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        BranchCoverageInstrumentation.printCoverageDataToFile(BranchCoverageInstrumentation.coverageFunction4, 4);
    }
    
    @Test
    public void testIsRequestFilterSatisfied_NullFilters() throws Exception {
        Method method = LocalLoggingService.class.getDeclaredMethod("isRequestFilterSatisfied", LogDetails.class, String.class, String.class, String.class, String.class, String.class, String.class);
        method.setAccessible(true);
        LocalLoggingService llService = new LocalLoggingService();
        assertTrue((boolean) method.invoke(llService, null, null, null, null, null, null, null));
    }

    @Test
    public void testIsRequestFilterSatisfied_WithWrongRequestLog() throws Exception {
        Method method = LocalLoggingService.class.getDeclaredMethod("isRequestFilterSatisfied", LogDetails.class, String.class, String.class, String.class, String.class, String.class, String.class);
        method.setAccessible(true);
        LocalLoggingService llService = new LocalLoggingService();

        LogDetails wrongLogDetails = new LogDetails(LogEvent.EXCEPTION_LOG) {
            @Override
            public void hideSensitiveInformation() {
                // do nothing
            }
        };

        assertFalse((boolean) method.invoke(llService, wrongLogDetails, "test", null, null, null, null, null));
    }

    @Test
    public void testIsRequestFilterSatisfied_LatencyFilterNotNull() throws Exception {
        Method method = LocalLoggingService.class.getDeclaredMethod("isRequestFilterSatisfied", LogDetails.class, String.class, String.class, String.class, String.class, String.class, String.class);
        method.setAccessible(true);
        LocalLoggingService llService = new LocalLoggingService();

        RequestLogDetails requestLogDetails = new RequestLogDetails();
        requestLogDetails.setResponseStatus(200); 
        requestLogDetails.setResponseTime(100); 

        assertFalse((boolean) method.invoke(llService, requestLogDetails, null, "test", null, null, null, null));
    }

    @Test
    public void testIsRequestFilterSatisfied_LatencyFilterNotNull_Regex() throws Exception {
        Method method = LocalLoggingService.class.getDeclaredMethod("isRequestFilterSatisfied", LogDetails.class, String.class, String.class, String.class, String.class, String.class, String.class);
        method.setAccessible(true);
        LocalLoggingService llService = new LocalLoggingService();

        RequestLogDetails requestLogDetails = new RequestLogDetails();
        requestLogDetails.setResponseStatus(200); 
        requestLogDetails.setResponseTime(100); 

        assertFalse((boolean) method.invoke(llService, requestLogDetails, null, "> 101", null, null, null, null));
        assertFalse((boolean) method.invoke(llService, requestLogDetails, null, ">= 101", null, null, null, null));
        assertFalse((boolean) method.invoke(llService, requestLogDetails, null, "< 0", null, null, null, null));
        assertFalse((boolean) method.invoke(llService, requestLogDetails, null, "<= 0", null, null, null, null));
    }
   
}

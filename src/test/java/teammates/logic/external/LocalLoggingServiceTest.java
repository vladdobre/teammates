package teammates.logic.external;

import teammates.test.BaseTestCase;

import teammates.common.datatransfer.logs.LogEvent;
import teammates.common.datatransfer.logs.RequestLogDetails;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import teammates.common.datatransfer.logs.LogDetails;

import java.lang.reflect.Method;
/**
 * SUT: {@link LocalLoggingService}.
 */
public class LocalLoggingServiceTest extends BaseTestCase {

    @BeforeMethod
    public void refreshTestData() { 
    }
    
    @Test
    public void testIsRequestFilterSatisfied_NullFilters() throws Exception {
        // Requirement: If filters are null, the method should return true.
        Method method = LocalLoggingService.class.getDeclaredMethod("isRequestFilterSatisfied", LogDetails.class, String.class, String.class, String.class, String.class, String.class, String.class);
        method.setAccessible(true);
        LocalLoggingService llService = new LocalLoggingService();
        assertTrue((boolean) method.invoke(llService, null, null, null, null, null, null, null));
    }

    @Test
    public void testIsRequestFilterSatisfied_WithWrongRequestLog() throws Exception {
        // Requirement: If the log details indicate an exception log, the method should return false.
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
        // Requirement: If latency filter is not null, and response time does not match the filter, return false.
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
        // Requirement: If latency filter is not null, it should match the response time using regex patterns.
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

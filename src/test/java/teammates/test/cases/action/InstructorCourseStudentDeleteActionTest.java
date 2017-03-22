package teammates.test.cases.action;

import org.testng.annotations.Test;

import teammates.common.datatransfer.attributes.InstructorAttributes;
import teammates.common.datatransfer.attributes.StudentAttributes;
import teammates.common.util.Const;
import teammates.test.driver.AssertHelper;
import teammates.ui.controller.InstructorCourseStudentDeleteAction;
import teammates.ui.controller.RedirectResult;

/**
 * SUT: {@link InstructorCourseStudentDeleteAction}.
 */
public class InstructorCourseStudentDeleteActionTest extends BaseActionTest {

    @Override
    protected String getActionUri() {
        return Const.ActionURIs.INSTRUCTOR_COURSE_STUDENT_DELETE;
    }

    @Override
    @Test
    public void testExecuteAndPostProcess() {

        InstructorAttributes instructor1OfCourse1 = dataBundle.instructors.get("instructor1OfCourse1");
        StudentAttributes student1InCourse1 = dataBundle.students.get("student1InCourse1");

        ______TS("success: delete a student ");
        gaeSimulation.loginAsInstructor(instructor1OfCourse1.googleId);

        String[] submissionParams = new String[]{
                Const.ParamsNames.COURSE_ID, instructor1OfCourse1.courseId,
                Const.ParamsNames.STUDENT_EMAIL, student1InCourse1.email
        };

        InstructorCourseStudentDeleteAction action = getAction(submissionParams);
        RedirectResult redirectResult = getRedirectResult(action);

        assertEquals(Const.ActionURIs.INSTRUCTOR_COURSE_DETAILS_PAGE, redirectResult.destination);
        assertFalse(redirectResult.isError);
        assertEquals(Const.StatusMessages.STUDENT_DELETED, redirectResult.getStatusMessage());

        AssertHelper.assertLogMessageEquals("TEAMMATESLOG|||instructorCourseStudentDelete|||instructorCourseStudentDelete|||"
                     + "true|||Instructor|||Instructor 1 of Course 1|||idOfInstructor1OfCourse1|||"
                     + "instr1@course1.tmt|||Student <span class=\"bold\">student1InCourse1@gmail.tmt</span> "
                     + "in Course <span class=\"bold\">[idOfTypicalCourse1]</span> deleted.|||"
                     + "/page/instructorCourseStudentDelete", action.getLogMessage());

    }

    @Override
    protected InstructorCourseStudentDeleteAction getAction(String... params) {
        return (InstructorCourseStudentDeleteAction) gaeSimulation.getActionObject(getActionUri(), params);
    }

}

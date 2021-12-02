package TestFX;

import javafx.scene.control.Labeled;
import javafx.scene.input.MouseButton;
import org.junit.jupiter.api.*;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestFxUITest extends TestFXJUnitApp {

	 private final String LABEL_FOR_BUTTON = "Display (dept.)";
	    private final String TEXT_LABEL_BEFORE_CLICK = "Display (dept.)";
	    private final String TEXT_LABEL_AFTER_CLICK = "clicked!";

    /**
     * Will be called with {@code @Before} semantics, i.e. before each test method.
     *
     * @param stage - Test will inject.
     * @throws InterruptedException
     * @throws Exception 
     */
	    @Test
	    @Order(2)
	    @DisplayName("should verify text Display (dept.) before click button and verify text courses section is loaded")
	    public void clickDisplayDept() {

	        Labeled label = lookup(LABEL_FOR_BUTTON).query();

	        verifyThat(label, hasText(TEXT_LABEL_BEFORE_CLICK));

	        clickOn(label, MouseButton.PRIMARY);

	        Labeled courses = lookup("Courses").query();
	        
	        verifyThat(courses, hasText("Courses"));
	    }

	    @Test
	    @Order(2)
	    @DisplayName("should verify new course entry is successful")
	    public void clickNewCourse() throws InterruptedException {

	        Labeled label = lookup("New Course").query();

	        verifyThat(label, hasText("New Course"));

	        clickOn(label, MouseButton.PRIMARY);
	        
	        clickOn("#course_name");
	        write("Software Engineering");
	        clickOn("#credits");
	        write("30");
	        Labeled addCourse = lookup("Add Course").query();
	        clickOn(addCourse, MouseButton.PRIMARY);
	        
	    }
	    
}
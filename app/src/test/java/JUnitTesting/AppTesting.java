package JUnitTesting;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;

import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

import com.sun.javafx.tk.Toolkit;

import edu.isu.cs.cs2263.hw02.App;
import edu.isu.cs.cs2263.hw02.data.Course;
import javafx.stage.Stage;
public class AppTesting {
	static Course c = new Course("DummyIT", "001", 22, 30);
//	@Test
	@DisplayName("This is to test App class's start method")
	void testAppStart() throws Exception {
		
		
		 App app = Mockito.spy(App.class);
		 
		 Stage stage = Mockito.spy(Stage.class);
		Class clazz = Toolkit.class;
		 //Workaround to handle events on non application thread. 
		Field retrieveItems = clazz.getDeclaredField("fxUserThread");
		retrieveItems.setAccessible(true);
		retrieveItems.set(null, Thread.currentThread());
		try {
//			Mockito.doCallRealMethod().when(app).start(stage);
			app.start(stage);
		} catch (Exception e) {
			//Ignoring event thread related error. As automation on junit can not efficiently execute FX application
			if(!e.getMessage().contains("This operation is permitted on the event thread only"))
			fail("Test execution failed on app.start", e);
		}
		app.showCourseForm();
		app.displayList();
		Course dummyCourse = new Course();
		dummyCourse.setCode(c.getCode());
		dummyCourse.setCredits(c.getCredits());
		dummyCourse.setName(c.getName());
		dummyCourse.setNumber(c.getNumber());
		String actualValue = dummyCourse.toString();
		app.addCourse(c);
	}
}

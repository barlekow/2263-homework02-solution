package JUnitTesting;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import edu.isu.cs.cs2263.hw02.App;
import edu.isu.cs.cs2263.hw02.views.CoursesFormView;
import edu.isu.cs.cs2263.hw02.views.DisplayListView;
import edu.isu.cs.cs2263.hw02.views.WelcomeView;

@TestInstance(Lifecycle.PER_CLASS)
class JUnitTesting {
    private WelcomeView mockedWelcomeView;
    private CoursesFormView mockedFormView;
    private DisplayListView mockedDisplayListView;
    private App app;

	@BeforeAll   
	public void setUp() {
		System.out.println("Start of instance mocking procedure");
		mockedWelcomeView = Mockito.spy(WelcomeView.class);
		mockedFormView = Mockito.spy(CoursesFormView.class);
		mockedDisplayListView = Mockito.spy(DisplayListView.class);
		app = Mockito.spy(App.class);
		Mockito.doReturn(2).when(app).getSelectedDepartment();
		System.out.println("End of instance mocking procedure");
	}
	
	@AfterAll
	public void destroy() {
		System.out.println("End of test execution environment");
		mockedWelcomeView = null;
		mockedFormView = null;
		mockedDisplayListView = null;
		app = null;
		System.out.println("Exit test execution environment completed");
	}
    
	/*
	 * This test case is calling init method for welcome page. 
	 */
	@Test
	@DisplayName("For testing initialization sequence for welcome-form view")
	@Order(1)
	void testWelcomeView() {
//		Mockito.doCallRealMethod().when(mockedWelcomeView).initView();		
		mockedWelcomeView.initView();
	}

	/*
	 * This particular test case is initiating form view
	 */
	@Test
	@DisplayName("For testing initialization sequence for course-form view")
	@Order(2)
	void testCourseFormView() {
		Mockito.doCallRealMethod().when(mockedFormView).initView();
	}
	
	@Test
	@DisplayName("For testing update data method over course-form view. Update data is called with department id 2")
	@Order(3)
	void testCourseFormViewUpdateData() {
		Whitebox.setInternalState(mockedFormView, "parent", app);
		mockedFormView.updateData();	
	}
	
	@Test
	@DisplayName("For testing initialization sequence for course-form view")
	@Order(4)
	void testDisplayView() {
		Mockito.doCallRealMethod().when(mockedDisplayListView).initView();
	}
	
	@Test
	@DisplayName("For testing update data method over display-list view. Update data is called with department id 2")
	@Order(5)
	void testDisplayViewUpdateData() {
		Whitebox.setInternalState(mockedDisplayListView, "parent", app);
		mockedDisplayListView.updateData();	
	}
	
	@Test
	@DisplayName("For testing get view method over display-list view. This is common method across all the view hence only 1 test is required")
	@Order(6)
	void testGetViewObject() {
		mockedDisplayListView.getView();	
	}
	
}

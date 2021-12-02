package edu.isu.cs.cs2263.hw02.views;

import edu.isu.cs.cs2263.hw02.App;
import edu.isu.cs.cs2263.hw02.data.Course;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lombok.extern.log4j.Log4j2;
@Log4j2
public class DisplayListView extends AppView {

    ListView<Course> lstCourses;

    public DisplayListView(App parent) {
        super(parent);
    }

    /*
     * Adding empty constructor so that mockito can initiate object with reflection. 
     */
    public DisplayListView() {
        super(null);
    }

    
    @Override
    public void initView() {
    	log.debug("Initializing Display List View screen");
        lstCourses = new ListView<>();

        Label lbl = new Label("Courses");
        lbl.setFont(Font.font("Roboto", FontWeight.BOLD, 18));

        BorderPane bp = new BorderPane();
        bp.setTop(lbl);
        bp.setCenter(lstCourses);
        bp.setPadding(new Insets(10,10,10,10));

        view = bp;
        log.debug("Initializing Display List View screen completed");
    }

    @Override
    public void updateData() {
        int index = parent.getSelectedDepartment();
        String code = Course.CODES[index];

        lstCourses.getItems().clear();

        for (Course c : parent.getCourses()) {
            if (c.getCode().equals(code))
                lstCourses.getItems().add(c);
        }
    }
}

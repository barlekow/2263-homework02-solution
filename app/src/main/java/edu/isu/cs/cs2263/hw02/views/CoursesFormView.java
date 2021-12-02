package edu.isu.cs.cs2263.hw02.views;

import edu.isu.cs.cs2263.hw02.App;
import edu.isu.cs.cs2263.hw02.data.Course;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import lombok.extern.log4j.Log4j2;

import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.materialdesign2.MaterialDesignR;
@Log4j2
public class CoursesFormView extends AppView {

    private TextField tfName;
    private Spinner<Integer> spnNumber;
    private Spinner<Integer> spnCredits;
    private Label lblMessage;
    private String code;

    public CoursesFormView(App parent) {
        super(parent);
    }

    /*
     * Constructor added for mockito procedure to instantiate object via reflaction.
     */
    public CoursesFormView() {
    	super(null);
    }
    
    @Override
    public void initView() {
    	log.debug("Initializing CourceFormView screen");
        lblMessage = new Label("");

        tfName = new TextField();
        tfName.setId("course_name");
        tfName.setPromptText("Enter a course name...");
        tfName.setMinWidth(400);

        Label lblName = new Label("Name:");
        lblName.setTextAlignment(TextAlignment.RIGHT);
        lblName.setLabelFor(tfName);

        spnNumber = new Spinner<>(100, 900, 100, 1);
        spnNumber.setEditable(true);

        Label lblNumber = new Label("Number:");
        lblNumber.setTextAlignment(TextAlignment.RIGHT);
        lblNumber.setLabelFor(spnNumber);

        spnCredits = new Spinner<>(0, 10, 1, 1);
        spnCredits.setEditable(true);
        spnCredits.setId("credits");

        Label lblCredits = new Label("Credits:");
        lblCredits.setTextAlignment(TextAlignment.RIGHT);
        lblCredits.setLabelFor(spnCredits);

        GridPane gpForm = new GridPane();
        gpForm.add(lblName, 0, 0, 1, 1);
        gpForm.add(lblNumber, 0, 1, 1, 1);
        gpForm.add(lblCredits, 0, 2, 1, 1);
        gpForm.add(tfName, 1, 0, 3, 1);
        gpForm.add(spnNumber, 1, 1, 1, 1);
        gpForm.add(spnCredits, 1, 2, 1, 1);
        gpForm.setHgap(5);
        gpForm.setVgap(5);

        Label lblHead = new Label("Create a new Course");
        lblHead.setFont(Font.font("Roboto", FontWeight.BOLD, 18));

        VBox vbxTop = new VBox();
        vbxTop.getChildren().add(lblHead);
        vbxTop.getChildren().add(lblMessage);
        vbxTop.setSpacing(5);

        Button btnReset = new Button("Reset");
        btnReset.setOnAction(event -> {
            reset();
        });
        btnReset.setGraphic(FontIcon.of(MaterialDesignR.REFRESH, 20));

        Button btnAddCourse = new Button("Add Course");
        btnAddCourse.setOnAction(event -> {
            validateAndAdd();
        });
        btnAddCourse.setGraphic(FontIcon.of(MaterialDesignP.PLUS, 20));
        btnAddCourse.setDefaultButton(true);

        FlowPane fpButtons = new FlowPane();
        fpButtons.getChildren().add(btnReset);
        fpButtons.getChildren().add(btnAddCourse);
        fpButtons.setHgap(5);

        BorderPane main = new BorderPane();
        main.setCenter(gpForm);
        main.setTop(vbxTop);
        main.setBottom(fpButtons);
        main.setPadding(new Insets(10,10,10,10));

        view = main;
        log.debug("Initialization sequence CourceFormView screen completed");
    }

    private void validateAndAdd() {
        if (tfName.getText().isBlank()) {
            lblMessage.setText("Error: Name cannot be empty!");
            lblMessage.setTextFill(Color.RED);
        } else {
            lblMessage.setText("");

            Course c = new Course(tfName.getText(), code, spnNumber.getValue(), spnCredits.getValue());
            parent.addCourse(c);

            parent.showWelcome();
        }
    }

    private void reset() {
        lblMessage.setText("");
        tfName.setText("");
        spnCredits.decrement(spnCredits.getValue());
        spnNumber.decrement(spnNumber.getValue() - 100);
    }

    @Override
    public void updateData() {
        code = Course.CODES[parent.getSelectedDepartment()];
        reset();
    }
}

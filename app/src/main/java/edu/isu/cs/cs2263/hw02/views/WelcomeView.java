package edu.isu.cs.cs2263.hw02.views;

import edu.isu.cs.cs2263.hw02.App;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import lombok.extern.log4j.Log4j2;
@Log4j2
public class WelcomeView extends AppView {

    public WelcomeView(App parent) {
        super(parent);
    }
    
    /*
     * Constructor for mockito process to invoke
     */
    public WelcomeView() {
    	super(null);
	}
    
    @Override
    public void initView() {
    	log.debug("Initializing Welcome view screen");
        Label message = new Label("Welcome to Course List");

        message.setFont(Font.font("Roboto", FontWeight.BOLD, 16));
        message.setTextAlignment(TextAlignment.CENTER);

        BorderPane bp = new BorderPane();
        bp.setCenter(message);
        BorderPane.setAlignment(message, Pos.CENTER);

        view = bp;
        log.debug("Initializing sequence for Welcome view screen completed");
    }

    @Override
    public void updateData() {

    }
}

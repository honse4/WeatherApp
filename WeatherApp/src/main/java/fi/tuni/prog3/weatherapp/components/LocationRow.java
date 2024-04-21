package fi.tuni.prog3.weatherapp.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

/**
 *
 * @author vasav
 */
public class LocationRow extends HBox {
    
    /**
     * Constructor
     * @param name Name of the location 
     * @param state Name of the state of the location
     * @param delete Button which deletes the row
     */
    public LocationRow(String name, String state, Button delete) {
        
        Label placeName = new Label(name);
        placeName.setFont(new Font("Helvetica", 14));
        placeName.setMinWidth(50);
        
        Label stateName = new Label(state);
        stateName.setFont(new Font("Helvetica", 14));
        
        getChildren().addAll(placeName, stateName);
         
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        delete.setMaxHeight(15);
        delete.setFocusTraversable(false);
        getChildren().addAll(spacer,delete);
        
        
        setSpacing(100);
        setId(name + state);
        
        setMinHeight(35);
        setColourHover("-fx-background-color: #f0f0f0");
        setAlignment(Pos.CENTER);
        
        setOnMouseEntered(e -> {
            setColourHover("-fx-background-color: #cccccc;");
        });
        
        setOnMouseExited(e -> {
            setColourHover("-fx-background-color: #f0f0f0");
        });
    }
    
    /**
     * Changes colour when hovering
     * @param colour String containing colour name
     */
    private void setColourHover(String colour){
        setStyle("-fx-border-color:black; -fx-padding: 0 25 0 20;"
                + " -fx-border-width: 1;" + colour);
    }
}

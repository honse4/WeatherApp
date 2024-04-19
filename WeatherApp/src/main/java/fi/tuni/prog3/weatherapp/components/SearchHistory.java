package fi.tuni.prog3.weatherapp.components;

import fi.tuni.prog3.weatherapp.WeatherApp;
import fi.tuni.prog3.weatherapp.apigson.location.LocationData;
import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author vasav
 */
public class SearchHistory extends VBox {
    private final Preferences preferences;
    private final VBox list;
    private final Stage stage;
    private final Scene scene;
    private final WeatherApp main;
    private final HBox message;
    private final Arc arc;
    private final RotateTransition animation;
    
    public SearchHistory(Preferences preferences, Stage stage, Scene scene, WeatherApp main) {
        this.preferences = preferences;
        this.list = new VBox();
        this.scene = scene;
        this.stage = stage;
        this.main = main;
        this.message = new HBox();
        
        arc = new Arc(20, 20, 8, 8, 0, 270);
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(Color.BLACK);
        arc.setStrokeWidth(2);
        
        animation = animation(arc);
        
        message.setAlignment(Pos.CENTER);
        
        list.setAlignment(Pos.CENTER);
        list.setSpacing(4);
        
        Label title = new Label("Search History");
        title.setFont(new Font("Helvetica", 16));
        HBox titleCenter = new HBox(title);
        titleCenter.setAlignment(Pos.CENTER);
        
        
        getChildren().addAll(getBackButton(), titleCenter, getScrollPane());
        setSpacing(20);
        
        
    }
    
    private ScrollPane getScrollPane(){
        if (preferences.getLocationSearchHistory() != null) {
            for (LocationData data: preferences.getLocationSearchHistory()) {
                 addLocation(data);
            }
        }
        else {
            Label empty = new Label("Seach history is empty");
            empty.setId("label");
            list.getChildren().add(empty);
        }
        
        ScrollPane favouritesContainer = new ScrollPane();
        favouritesContainer.setFitToHeight(true);
        favouritesContainer.setFitToWidth(true);
        favouritesContainer.setContent(list);
        favouritesContainer.setStyle("-fx-background-color: transparent; "
        + "-fx-border-color: transparent; -fx-padding: 10 7 12 7; "
        + "-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        VBox.setMargin(favouritesContainer, new Insets(0, 30,80,30));
        
        return favouritesContainer;
    }
    
    public void addLocation(LocationData data) { 
        if (preferences.getLocationSearchHistory() != null ) {
            deleteLocation(data);
            preferences.addLocationIntoHistory(data);
        } else {
            ArrayList<LocationData>  locationList = new ArrayList<>();
            locationList.add(data);
            preferences.setLocationSearchHistory(locationList);
        }
        
        Button delete = new Button("X");
        delete.setOnAction(e -> {
            deleteLocation(data);
        });
        
        LocationRow row = new LocationRow(data.getName(), data.getState(), delete);
        row.setOnMouseClicked(e -> {
            onClick(data.getName());
        });
        
        Platform.runLater(() -> {
            list.getChildren().removeIf(node -> node instanceof Label);
            list.getChildren().add(0, row);
        });
        
    }
    
    private void deleteLocation(LocationData data) {
        preferences.deleteLocationFromHistory(data);
        
        Platform.runLater(() -> {
            list.getChildren().removeIf(node -> node.getId().equals(data.getName()+data.getState()));
            if (list.getChildren().isEmpty()) {
                Label empty = new Label("Seach history is empty");
                empty.setId("label");
                list.getChildren().add(empty);
        }
        });
    }
    
    private void onClick(String search) {
        getChildren().removeIf(node -> node == message);
        message.getChildren().removeIf(node -> node instanceof Label);
        message.getChildren().add(arc);
        animation.play();
        getChildren().add(2, message);
        
        //
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return main.searchResult(search); 
            }

            @Override
            protected void succeeded() {
                Boolean result = getValue();
                if (result) {
                    stage.setScene(scene);
                    scene.getRoot().requestFocus();
                    getChildren().remove(message);
                } else {
                    message.getChildren().add(new Label("Error processing your request"));
                }
                animation.pause();
                message.getChildren().removeIf(node -> node instanceof Arc);
            }
        };
        new Thread(task).start();   
    }
    
    private HBox getBackButton() {
        SVGPath leftArrow = new SVGPath();
        leftArrow.setContent("M 0 0 L 6 3 L 0 6 L 1.5 3 Z");
        leftArrow.setScaleX(1.75);  
        leftArrow.setScaleY(1.75);
        
        Button back = new Button();
        back.setAlignment(Pos.CENTER);
        back.setGraphic(leftArrow);
        back.setMaxSize(50,30);
        back.setFocusTraversable(false);
        back.setMinSize(50,30);
        
        back.setOnAction(e -> {
            getChildren().removeIf(node -> node == message);
            stage.setScene(scene);
            scene.getRoot().requestFocus();    
        });
        
        HBox alignment = new HBox(back);
        alignment.setAlignment(Pos.TOP_RIGHT);
        return alignment;
    }
    
    private RotateTransition animation(Arc arc) {   
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), arc);
        rotateTransition.setByAngle(360); 
        rotateTransition.setCycleCount(Animation.INDEFINITE); 
        rotateTransition.setInterpolator(Interpolator.LINEAR); 

        return rotateTransition;
    }
}

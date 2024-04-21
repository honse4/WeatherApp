package fi.tuni.prog3.weatherapp.components;

import fi.tuni.prog3.weatherapp.WeatherApp;
import fi.tuni.prog3.weatherapp.apigson.location.LocationData;
import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
public class SearchBar extends VBox {
    private final Stage stage;
    private final Scene scene;
    private final WeatherApp main;
    private final TextField searchbar;
    private final Label error;
    private final Preferences preferences;
    private final VBox favourites;
    private final Arc arc;
    private final RotateTransition animation;
    private final VBox favouritesContainer;
    
    /**
     * Constructor for the search bar.
     * @param stage Stage used by javaFx
     * @param scene Main scene presented to user
     * @param main WeatherApp class used for a method call
     * @param preferences
     */
    public SearchBar(Stage stage, Scene scene, WeatherApp main,Preferences preferences) {
        this.stage = stage;
        this.scene = scene;
        this.main = main;
        this.searchbar = new TextField();
        this.error = new Label("");
        this.favourites = new VBox();
        this.preferences = preferences; 
        
        favourites.setAlignment(Pos.CENTER);
        favourites.setSpacing(2);

        arc = new Arc(20, 20, 8, 8, 0, 270);
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(Color.BLACK);
        arc.setStrokeWidth(2);
        
        this.animation = animation(arc);
        
        searchbar.setMinSize(320,30);
        searchbar.setStyle("-fx-border-color: transparent; -fx-border-width: 0; -fx-background-color:transparent;"
                + "-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-font-size: 14px;");
        
        error.setAlignment(Pos.CENTER);
        error.setMinWidth(200);
        error.setStyle("-fx-text-fill: #ff0000;");
        
        favouritesContainer = getFavourites();
        
        getChildren().addAll(getBackButton(), getVBox(),favouritesContainer);
        setSpacing(50);
        
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                String search = searchbar.getText();
                onClick(search);
            }
        });
    }
    
    /**
     * Creates the button used to go back to the main scene
     * @return Button 
     */
    private Button getBackButton() {
        SVGPath leftArrow = new SVGPath();
        leftArrow.setContent("M 6 0 L 0 3 L 6 6 L 4.5 3 Z");
        leftArrow.setScaleX(1.75);  
        leftArrow.setScaleY(1.75);
        
        Button back = new Button();
        back.setAlignment(Pos.CENTER);
        back.setGraphic(leftArrow);
        back.setMaxSize(50,30);
        back.setFocusTraversable(false);
        back.setMinSize(50,30);
        
        back.setOnAction(e -> {
            searchbar.setText("");
            error.setText("");
            stage.setScene(scene);
            scene.getRoot().requestFocus();    
        });
        VBox.setMargin(back, new Insets(0, -50,0, 0));
        return back;
    }
    
    /**
     * Adds the top row to a VBox
     * @return VBox
     */
    private VBox getVBox() {
        //
        SVGPath magnifyingGlass = new SVGPath();
        magnifyingGlass.setContent("M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z");
        magnifyingGlass.setScaleX(0.035);  
        magnifyingGlass.setScaleY(0.035);
        
        Button searchButton = new Button();
        searchButton.setGraphic(magnifyingGlass);
        searchButton.setMinSize(50,40);
        searchButton.setFocusTraversable(false);
        searchButton.setStyle("-fx-background-radius: 0 20 20 0;");
        
        searchButton.setOnAction(e -> {
            String search = searchbar.getText();
            onClick(search);
        });
        
        VBox topRow = getTopRow(searchbar, searchButton, error);
        
        VBox container = new VBox(topRow);
        VBox.setMargin(topRow, new Insets(20, 0, 0, 0));
        container.setSpacing(10);
        
        return container;
    }
    
    /**
     * Creates a HBox within a VBox that contains the search bar and button as
     * well as the error label
     * @param searchbar TextField where the user searches location
     * @param searchButton Button which submits the search
     * @param error Label which can show any errors
     * @return VBox 
     */
    private VBox getTopRow(TextField searchbar, Button searchButton, Label error) {
        HBox searchRow = new HBox(searchbar, searchButton);
        searchRow.setSpacing(0);
        searchRow.setAlignment(Pos.CENTER);
        searchRow.setStyle("-fx-border-color: #a0a0a0; -fx-padding: 0 0 0 5;"
                + "-fx-border-radius: 20; -fx-border-width: 1;");
        searchRow.setMaxSize(375, 40);
        
        VBox topRow = new VBox(searchRow, error);
        topRow.setSpacing(5);
        topRow.setAlignment(Pos.CENTER);
        
        return topRow;
    }
    
    /**
     * Function called by the search button. Uses a task to create a separate thread
     * for the api call and the main thread continues the other code. 
     */
    private void onClick(String search) {
        searchbar.setText("");
        error.setText("");
        
        favouritesContainer.getChildren().add(0, arc);
        animation.play();
        
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
                } else {
                    error.setText("Place Does Not Exist");
                }
                animation.pause();
                favouritesContainer.getChildren().remove(arc);
            }
        };
        new Thread(task).start();   
    }
    
    /**
     * Uses the favorites stored in memory and displays them
     * @return ScrollPane
     */
    private VBox getFavourites() {
        
        if (preferences.getFavouriteLocations() != null) {
            for (LocationData data: preferences.getFavouriteLocations()) {
                 addFavourite(data);
            }
        }
        else {
            favourites.getChildren().add(new Label("No favourites added yet"));
        }
        
        ScrollPane favouritesContainer = new ScrollPane();
        favouritesContainer.setFitToHeight(true);
        favouritesContainer.setFitToWidth(true);
        favouritesContainer.setContent(favourites);
        favouritesContainer.setStyle("-fx-background-color: transparent; "
        + "-fx-border-color: transparent; -fx-padding: 10 7 10 7; "
        + "-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        
        Label title = new Label("Favourites");
        title.setFont(new Font("Helvetica", 16));
        
        VBox holder = new VBox(title, favouritesContainer);
        holder.setAlignment(Pos.CENTER);
        holder.setSpacing(5);
        
        VBox.setMargin(holder, new Insets(0, 30,80,30));
        return holder;
    }
    
    /**
     * Adds a favorite
     * @param data LocationData of the favorite
     */
    public void addFavourite(LocationData data) {
        favourites.getChildren().removeIf(node -> node instanceof Label);
         
        Button delete = new Button("X");
        delete.setOnAction(e -> {
            deleteFavourite(data);
        });
        
        LocationRow row = new LocationRow(data.getName(), data.getState(), delete);
        row.setOnMouseClicked(e -> {
            onClick(data.getName());
        });
            
        favourites.getChildren().add(0, row);
    }
    
    /**
     * Deletes a favorite
     * @param data LocationData of the favorite
     */
    public void deleteFavourite(LocationData data) {
        preferences.deleteFavouriteLocations(data);
        favourites.getChildren().removeIf(node -> node.getId().equals(data.getName()+data.getState()));
        main.changeStarColour();
        
        if (favourites.getChildren().isEmpty()) {
            favourites.getChildren().add(new Label("No favourites added yet"));
        }
    }
    
    private RotateTransition animation(Arc arc) {   
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), arc);
        rotateTransition.setByAngle(360); 
        rotateTransition.setCycleCount(Animation.INDEFINITE); 
        rotateTransition.setInterpolator(Interpolator.LINEAR); 

        return rotateTransition;
    }

}

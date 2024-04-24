package fi.tuni.prog3.weatherapp.components.sideview;

import fi.tuni.prog3.weatherapp.WeatherApp;
import fi.tuni.prog3.weatherapp.apigson.location.LocationData;
import fi.tuni.prog3.weatherapp.components.supplement.BackButton;
import fi.tuni.prog3.weatherapp.components.supplement.LoadingCircle;
import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
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
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Class that lets user search and displays favourites
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
    private final LoadingCircle loader;
    private final VBox favouritesContainer;
    private boolean isLoading;
    
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
        this.loader = new LoadingCircle(10, 10);
        this.isLoading = false;
        
        favourites.setAlignment(Pos.CENTER);
        favourites.setSpacing(2);

        searchbar.setMinSize(350,30);
        searchbar.setStyle("-fx-border-color: transparent; -fx-border-width: 0; -fx-background-color:transparent;"
                + "-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-font-size: 14px;");
        
        error.setAlignment(Pos.CENTER);
        error.setMinWidth(200);
        error.setStyle("-fx-text-fill: #ff0000;");
        
        favouritesContainer = getFavourites();
        
        BackButton back = new BackButton();
        back.setOnAction(e -> {
            searchbar.setText("");
            error.setText("");
            stage.setScene(scene);
            scene.getRoot().requestFocus();    
        });
        
        getChildren().addAll(back, getVBox(),favouritesContainer);
        setSpacing(50);
        
        // Can use enter to search instead of the button
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                if(!isLoading) {
                    String search = searchbar.getText();
                    onClick(search);
                }
            }
        });
    }
    
    /**
     * Adds the top row to the VBox
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
            if(!isLoading) {
                String search = searchbar.getText();
                onClick(search);
            }
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
        searchRow.setMaxSize(405, 40);
        
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
        isLoading = true;
        searchbar.setText("");
        error.setText("");
        
        favouritesContainer.getChildren().add(0, loader);
        loader.play();
        
        // Uses tasks and threads for asynchronous functioning
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
                    error.setText("Error processing your request");
                }
                loader.pause();
                favouritesContainer.getChildren().remove(loader);
                isLoading = false;
            }
        };
        new Thread(task).start();   
    }
    
    /**
     * Uses the favorites stored in memory and displays them
     * @return ScrollPane
     */
    private VBox getFavourites() {
        
        if (preferences.getFavouriteLocations() != null && !preferences.getFavouriteLocations().isEmpty()) {
            for (LocationData data: preferences.getFavouriteLocations()) {
                 addFavourite(data);
            }
        }
        else {
            favourites.getChildren().add(new Label("No favourites added yet"));
        }
        
        ScrollPane favouritesScroll = new ScrollPane();
        favouritesScroll.setFitToHeight(true);
        favouritesScroll.setFitToWidth(true);
        favouritesScroll.setContent(favourites);
        favouritesScroll.setStyle("-fx-background-color: transparent; "
        + "-fx-border-color: transparent; -fx-padding: 10 7 10 7; "
        + "-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        
        Label title = new Label("Favourites");
        title.setFont(new Font("Helvetica", 16));
        
        VBox holder = new VBox(title, favouritesScroll);
        holder.setAlignment(Pos.CENTER);
        holder.setSpacing(5);
        
        VBox.setMargin(holder, new Insets(0, 50,80,50));
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
}

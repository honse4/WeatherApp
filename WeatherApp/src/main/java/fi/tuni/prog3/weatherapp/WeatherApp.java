package fi.tuni.prog3.weatherapp;

import com.google.gson.Gson;
import fi.tuni.prog3.weatherapp.components.SearchBar;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import fi.tuni.prog3.weatherapp.apigson.forecast.ForecastData;
import fi.tuni.prog3.weatherapp.apigson.forecast.HourlyForecastData;
import fi.tuni.prog3.weatherapp.apigson.location.LocationData;
import fi.tuni.prog3.weatherapp.apigson.weather.AirQualityData;
import fi.tuni.prog3.weatherapp.apigson.weather.WeatherData;
import fi.tuni.prog3.weatherapp.components.Favourite;
import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import javafx.scene.control.TextField;


/**
 * JavaFX Weather Application.
 */
public class WeatherApp extends Application {
    private final GsonToClass dataGetter;
    private final Preferences preferences;
    private LocationData currentLocation;
    private SearchBar search;
    private Favourite favourite;
    
    public WeatherApp() {
        this.dataGetter = new GsonToClass();
        this.preferences = new Preferences(); // will change
    }
    
    @Override
    public void start(Stage stage) {
        
        //Creating a new BorderPane.
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 10, 10, 10));

        //Adding HBox to the center of the BorderPane.
        root.setCenter(getCenterVBox());

        //Adding button to the BorderPane and aligning it to the right.
        var quitButton = getQuitButton();
        BorderPane.setMargin(quitButton, new Insets(10, 10, 0, 10));
        root.setBottom(quitButton);
        BorderPane.setAlignment(quitButton, Pos.TOP_RIGHT);
        
        Scene scene = new Scene(root, 500, 700);   

        TextField searchBar = getSearchBar(stage, scene);
        favourite = new Favourite(preferences, this, search);
        HBox top = new HBox( favourite, searchBar);
        top.setAlignment(Pos.TOP_RIGHT);
        top.setSpacing(5);
        
        root.setTop(top);
        BorderPane.setAlignment(top, Pos.TOP_RIGHT);

        
        stage.setScene(scene);
        stage.setTitle("WeatherApp");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private VBox getCenterVBox() {
        //Creating an HBox.
        VBox centerHBox = new VBox(10);

        //Adding two VBox to the HBox.
        centerHBox.getChildren().addAll(getTopHBox(), getBottomHBox());

        return centerHBox;
    }

    private HBox getTopHBox() {
        //Creating a VBox for the left side.
        HBox leftHBox = new HBox();
        leftHBox.setPrefHeight(330);
        leftHBox.setStyle("-fx-background-color: #8fc6fd;");

        leftHBox.getChildren().add(new Label("Top Panel"));

        return leftHBox;
    }

    private HBox getBottomHBox() {
        //Creating a VBox for the right side.
        HBox rightHBox = new HBox();
        rightHBox.setPrefHeight(330);
        rightHBox.setStyle("-fx-background-color: #b1c2d4;");

        rightHBox.getChildren().add(new Label("Bottom Panel"));

        return rightHBox;
    }

    private Button getQuitButton() {
        //Creating a button.
        Button button = new Button("Quit");

        //Adding an event to the button to terminate the application.
        button.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });

        return button;
    }
    
    /**
     * Gets placeholder search bar which redirects to search page
     * @param stage Primary stage 
     * @param scene The main scene
     * @return TextField
     */
    private TextField getSearchBar(Stage stage, Scene scene) {
        search = new SearchBar(stage, scene, this,preferences);
        Scene searchScene = new Scene(search, 500, 700);
        
        TextField searchBar = new TextField();
        searchBar.setMaxWidth(125);
        searchBar.setMinSize(100, 20);
        searchBar.setPromptText("Search a location");
        
        searchBar.setOnMouseClicked(e -> {   
            stage.setScene(searchScene);
        });
        
        return searchBar;
    }
    
    /**
     * 
     * @param location
     * @return 
     */
    public boolean searchResult(String location) {
        try {
            currentLocation = dataGetter.locationSearch(location);
            WeatherData weatherData = dataGetter.weatherSearch(currentLocation);
            ForecastData forecastData = dataGetter.forecastSearch(currentLocation);
            HourlyForecastData hourlyForecastData = dataGetter.hourlyForecastSearch(currentLocation);
            AirQualityData airQualityData = dataGetter.qualitySearch(currentLocation);
            changeStarColour();
            

            
            // These objects should contain everything needed to display the information.
            // Maybe make some of the containers into attributes so you can change their content
            // from here.
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public LocationData getCurrentLocation() {
        return this.currentLocation;
    }
    
    public void changeStarColour() {
        favourite.checkFavourite();
    }
    
}
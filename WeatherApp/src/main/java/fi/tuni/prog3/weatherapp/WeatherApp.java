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
import fi.tuni.prog3.weatherapp.components.SearchHistory;
import fi.tuni.prog3.weatherapp.components.Units;
import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import java.io.FileReader;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;


/**
 * JavaFX Weather Application.
 */
public class WeatherApp extends Application {
    private final GsonToClass dataGetter;
    private final Preferences preferences;

    private final WeatherJsonProcessor jsonProcessor;
    private final String PREFERENCES_FILE = "savedPreferences.json";
    private SearchBar search;
    private Favourite favourite;
    private SearchHistory history;
    private Label locationName;
    private String unit;
    
    public WeatherApp() throws Exception {
        this.dataGetter = new GsonToClass();
        this.unit = "metric";
        this.jsonProcessor = new WeatherJsonProcessor();
        String jsonData = jsonProcessor.readFromFile(PREFERENCES_FILE);
        this.preferences = dataGetter.makePreferencesObject(jsonData);
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
        
        root.setTop(getHeader(stage, scene));
        
        // Get the weather for CurrentLocation as the default
        searchResult(this.preferences.getCurrentLocation().getName());
        
        stage.setScene(scene);
        stage.setTitle("WeatherApp");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    private BorderPane getHeader(Stage stage, Scene scene) {
        
        Units switchUnit = new Units(this, preferences);
        Button searchHistoryButton = getSearchHistory(stage, scene);
        searchHistoryButton.setAlignment(Pos.TOP_LEFT);
        HBox topLeft = new HBox(searchHistoryButton, switchUnit);
        topLeft.setSpacing(5);
        
        locationName = new Label("");
        locationName.setFont(new Font("Helvetica", 18));
        locationName.setAlignment(Pos.CENTER);
        
        TextField searchBar = getSearchBar(stage, scene);
        
        favourite = new Favourite(preferences, search);
        favourite.setOnMousePressed(e ->{
            favourite.pressStar(preferences.getCurrentLocation());
        });
        
        HBox topRight = new HBox(favourite, searchBar);
        topRight.setAlignment(Pos.TOP_RIGHT);
        topRight.setSpacing(10);
        
        BorderPane header = new BorderPane();
        header.setLeft(topLeft);
        header.setCenter(locationName);
        header.setRight(topRight);
        return header;
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
    
    private Button getSearchHistory(Stage stage, Scene scene) {
        history = new SearchHistory(preferences, stage, scene, this);
        Scene historyScene = new Scene(history, 500, 700);
        
        Button historySwitch = new Button("Search History");
        historySwitch.setFocusTraversable(false);
        historySwitch.setOnAction(e -> {
            stage.setScene(historyScene);
        });
        
        return historySwitch;
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
            LocationData locationData = dataGetter.locationSearch(location);
            WeatherData weatherData = dataGetter.weatherSearch(locationData, unit);
            ForecastData forecastData = dataGetter.forecastSearch(locationData, unit);
            HourlyForecastData hourlyForecastData = dataGetter.hourlyForecastSearch(locationData, unit);
            AirQualityData airQualityData = dataGetter.qualitySearch(locationData);
            
            
            preferences.setCurrentLocation(locationData);

            changeStarColour();
            history.addLocation(locationData);
            locationName.setText(locationData.getName());
            
            
            // These objects should contain everything needed to display the information.
            // Maybe make some of the containers into attributes so you can change their content
            // from here.
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void changeStarColour() {
        favourite.checkFavourite(preferences.getCurrentLocation());
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    @Override
    public void stop() throws Exception {
        // executed when the application shuts down
        this.jsonProcessor.setPreferences(preferences);
        this.jsonProcessor.writeToFile("savedPreferences.json");
    }
}
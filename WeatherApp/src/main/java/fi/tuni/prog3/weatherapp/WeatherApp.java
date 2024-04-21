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
import fi.tuni.prog3.weatherapp.components.DailyForecast;
import fi.tuni.prog3.weatherapp.components.Favourite;
import fi.tuni.prog3.weatherapp.components.ForecastChart;
import fi.tuni.prog3.weatherapp.components.SearchHistory;
import fi.tuni.prog3.weatherapp.components.Units;
import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import javafx.scene.Cursor;
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
    private final DailyForecast dailyForecast;
    
    private HourlyForecastData hourlyForecastData;
    
    public WeatherApp() throws Exception {
        this.dataGetter = new GsonToClass();
        this.unit = "metric";
        this.dailyForecast = new DailyForecast();
        this.jsonProcessor = new WeatherJsonProcessor();
        String jsonData = jsonProcessor.readFromFile(PREFERENCES_FILE);
        this.preferences = dataGetter.makePreferencesObject(jsonData);
    }
    
    @Override
    public void start(Stage stage) {
        
        // Get the weather for CurrentLocation as the default
        searchResult(this.preferences.getCurrentLocation().getName());
        
        //Creating a new BorderPane.
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 10, 10, 10));

        //Adding HBox to the center of the BorderPane.
        root.setCenter(getCenterVBox());
        
        Scene scene = new Scene(root, 500, 700);

        root.setTop(getHeader(stage, scene));
        
        stage.setScene(scene);
        stage.setTitle("WeatherApp");
        
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    /**
     * Creates the top part of the BorderPane. Has the search history, unit change
     * name of the location, favourites star and location search option
     * @param stage The main stage
     * @param scene The main scene shown to users
     * @return BorderPane
     */
    private BorderPane getHeader(Stage stage, Scene scene) {
        
        Units switchUnit = new Units(this, preferences);
        Button searchHistoryButton = getSearchHistory(stage, scene);
        Button tempChartButton = getForecastChart(stage, scene, this);
        searchHistoryButton.setAlignment(Pos.TOP_LEFT);
        HBox topLeft = new HBox(searchHistoryButton,tempChartButton,switchUnit);
        topLeft.setSpacing(5);
        
        locationName = new Label(this.preferences.getCurrentLocation().getName());
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
        centerHBox.getChildren().addAll(getTopHBox(), dailyForecast, getBottomHBox());

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

    /**
     * Creates the SearchHistory object and a button to switch to it
     * @param stage The main stage
     * @param scene The main scene shown to users
     * @return Button
     */
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
        searchBar.setFocusTraversable(false);
        
        searchBar.setOnMouseClicked(e -> {   
            searchBar.setCursor(Cursor.DEFAULT);
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
            this.hourlyForecastData = hourlyForecastData;
            
            changeStarColour();
            Platform.runLater(() -> {
                 history.addLocation(locationData);
                 locationName.setText(locationData.getName());
                 dailyForecast.showData(forecastData, unit);
            });      
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Checks if the current location is a part of the favourites
     */
    public void changeStarColour() {
        favourite.checkFavourite(preferences.getCurrentLocation());
    }

    public Button getForecastChart(Stage stage, Scene scene, WeatherApp main) {
        
        Button chartButton = new Button("Forecast charts");
        chartButton.setOnMouseClicked(e -> {   
            ForecastChart fcChart = new ForecastChart(stage, scene, main, this.hourlyForecastData,this.unit);
            Scene chartScene = new Scene(fcChart,500,700);
            stage.setScene(chartScene);
        });
        return chartButton;  
    }
    
    /**
     * Sets unit to either metric or imperial
     * @param unit name of the unit
     */
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
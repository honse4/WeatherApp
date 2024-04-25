package fi.tuni.prog3.weatherapp;

import com.google.gson.Gson;
import fi.tuni.prog3.weatherapp.components.sideview.SearchBar;
import fi.tuni.prog3.weatherapp.components.mainview.CurrentWeatherDisplay;
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
import fi.tuni.prog3.weatherapp.components.mainview.DailyForecast;
import fi.tuni.prog3.weatherapp.components.supplement.Favourite;
import fi.tuni.prog3.weatherapp.components.sideview.ForecastChart;
import fi.tuni.prog3.weatherapp.components.mainview.HourlyForecastDisplay;
import fi.tuni.prog3.weatherapp.components.sideview.SearchHistory;
import fi.tuni.prog3.weatherapp.components.supplement.Units;
import fi.tuni.prog3.weatherapp.preferencesgson.Preferences;
import javafx.scene.Cursor;
import java.io.FileReader;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import java.io.FileNotFoundException;

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
    private final CurrentWeatherDisplay currentWeatherBox;
    private final DailyForecast dailyForecast;
    private final HourlyForecastDisplay hourlyForecast;
    private HourlyForecastData hourlyForecastData;
    
    /**
     * Constructor
     * @throws Exception 
     */
    public WeatherApp() throws Exception {
        this.dataGetter = new GsonToClass();
        this.unit = "metric";
        this.dailyForecast = new DailyForecast();
        this.hourlyForecast = new HourlyForecastDisplay();
        this.jsonProcessor = new WeatherJsonProcessor();
        String jsonData = jsonProcessor.readFromFile(PREFERENCES_FILE);
        this.preferences = dataGetter.makePreferencesObject(jsonData);
        this.currentWeatherBox = new CurrentWeatherDisplay();
    }

    /**
     * Initialises the gui
     * @param stage Primary Stage
     * @throws FileNotFoundException 
     */
    @Override
    public void start(Stage stage) throws FileNotFoundException {

        //Creating a new BorderPane.
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        
        Scene scene = new Scene(root, 600, 700);
        root.setCenter(getCenterVBox(scene, stage));

        root.setTop(getHeader(stage, scene));
        
        stage.setScene(scene);
        stage.setTitle("WeatherApp");
        // Get the weather for CurrentLocation as the default
        searchResult(this.preferences.getCurrentLocation().getName());
        stage.show();
    }

    /**
     * Main 
     * @param args arguments
     */
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
        searchHistoryButton.setAlignment(Pos.TOP_LEFT);
        HBox topLeft = new HBox(searchHistoryButton,switchUnit);
        topLeft.setSpacing(5);
        
        locationName = new Label(this.preferences.getCurrentLocation().getName());
        locationName.setFont(new Font("Helvetica", 20));
        locationName.setStyle("-fx-font-weight: bold;");
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

    /**
     * Creates the VBox placed at the center of the BorderPane
     * @param scene Main scene shown to the user
     * @param stage Primary stage
     * @return VBox
     */
    private VBox getCenterVBox(Scene scene, Stage stage) throws FileNotFoundException {
        
        Button tempChartButton = getForecastChart(stage, scene, this);
        tempChartButton.setFocusTraversable(false);
        HBox chartBox = new HBox(tempChartButton);

        //Adding two VBox to the HBox.
        VBox centerHBox = new VBox(currentWeatherBox,chartBox,
                dailyForecast, hourlyForecast);
        centerHBox.setSpacing(2);

        return centerHBox;
    }

    /**
     * Creates the SearchHistory object and a button to switch to it
     * @param stage The main stage
     * @param scene The main scene shown to users
     * @return Button
     */
    private Button getSearchHistory(Stage stage, Scene scene) {
        history = new SearchHistory(preferences, stage, scene, this);
        Scene historyScene = new Scene(history, 600, 700);
        
        Button historySwitch = new Button("Search History");
        historySwitch.setFocusTraversable(false);
        historySwitch.setOnAction(e -> {
            stage.setScene(historyScene);
        });

        return historySwitch;
    }

    /**
     * Gets placeholder search bar which redirects to search page
     *
     * @param stage Primary stage
     * @param scene The main scene
     * @return TextField
     */
    private TextField getSearchBar(Stage stage, Scene scene) {
        search = new SearchBar(stage, scene, this,preferences);
        Scene searchScene = new Scene(search, 600, 700);
        
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
     * Creates the button which switches to the forecast charts
     * @param stage Primary stage
     * @param scene The main scene
     * @param main This WeatherApp class
     * @return Button
     */
    public Button getForecastChart(Stage stage, Scene scene, WeatherApp main) {
        
        Button chartButton = new Button("Forecast charts");
        chartButton.setOnMouseClicked(e -> {   
            ForecastChart fcChart = new ForecastChart(stage, scene, main, this.hourlyForecastData,this.unit);
            Scene chartScene = new Scene(fcChart,600,700);
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
    /**
     * Calls when the gui is closed
     * @throws Exception 
     */
    @Override
    public void stop() throws Exception {
        // executed when the application shuts down
        this.jsonProcessor.setPreferences(preferences);
        this.jsonProcessor.writeToFile("savedPreferences.json");
    }
    
    /**
     * Calls api for data and uses it to show data to the user
     * @param location Location searched by the user
     * @return Boolean
     */
    public boolean searchResult(String location) {
        try {
            LocationData locationData = dataGetter.locationSearch(location);
            WeatherData weatherData = dataGetter.weatherSearch(locationData, unit);
            ForecastData forecastData = dataGetter.forecastSearch(locationData, unit);
            HourlyForecastData hForecastData = dataGetter.hourlyForecastSearch(locationData, unit);
            AirQualityData airQualityData = dataGetter.qualitySearch(locationData);
            
            preferences.setCurrentLocation(locationData);
            this.hourlyForecastData = hForecastData;
            
            // Change values in the ui. Uses Platform to ensure all updates happen on the javafx thread
            Platform.runLater(() -> {
                currentWeatherBox.updateValues(weatherData, airQualityData, forecastData, unit);
                changeStarColour();
                history.addLocation(locationData);
                locationName.setText(locationData.getName());
                dailyForecast.showData(forecastData, unit);
                hourlyForecast.showData(hForecastData);
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
}

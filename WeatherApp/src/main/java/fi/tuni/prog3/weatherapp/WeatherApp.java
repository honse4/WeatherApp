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
import com.google.gson.reflect.TypeToken;
import fi.tuni.prog3.weatherapp.apigson.forecast.ForecastData;
import fi.tuni.prog3.weatherapp.apigson.location.LocationData;
import fi.tuni.prog3.weatherapp.apigson.weather.WeatherData;
import java.lang.reflect.Type;
import java.util.List;
import javafx.scene.control.TextField;


/**
 * JavaFX Weather Application.
 */
public class WeatherApp extends Application {
    private WeatherApi api;
    private Gson gson ;

    @Override
    public void start(Stage stage) {
        this.api = new WeatherApi();
        this.gson = new Gson();
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
        root.setTop(searchBar);
        BorderPane.setAlignment(searchBar, Pos.TOP_RIGHT);
        
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
    
    private TextField getSearchBar(Stage stage, Scene scene) {
        SearchBar search = new SearchBar(stage, scene, this);
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
    
    public boolean searchResult(String location) {
        try {
            LocationData ldata = locationSearch(location);
            WeatherData wdata = weatherSearch(ldata);
            ForecastData fdata = forecastSearch(ldata);
            
            // These objects should contain everything needed to display the information.
            // Maybe make some of the containers into attributes so you can change their content
            // from here.
            
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
    
    /**
     * api call to get the latitude and longitude for a location
     * @param location String which has the name of the location
     * @return LocationData
     */
    private LocationData locationSearch(String location) {
        Type listType = new TypeToken<List<LocationData>>(){}.getType();
        List<LocationData> ldata = gson.fromJson(api.lookUpLocation(location), listType);
       
        return ldata.get(0);
    }
    
    /**
     * api call to get the current weather
     * @param ldata LocationData which has the latitude and longitude
     * @return WeatherData
     */
    private WeatherData weatherSearch(LocationData ldata) {
        WeatherData wdata = gson.fromJson(api.getCurrentWeather(ldata.getLat(),
                ldata.getLon()), WeatherData.class);
        
        return wdata;
    }
    
    /**
     * api call to get the forecast
     * @param ldata LocationData which has the latitude and longitude
     * @return ForecastData
     */
    private ForecastData forecastSearch(LocationData ldata) {
        ForecastData fdata = gson.fromJson(api.getForecast(ldata.getLat(),
                ldata.getLon()), ForecastData.class);
        
        return fdata;
    }
}
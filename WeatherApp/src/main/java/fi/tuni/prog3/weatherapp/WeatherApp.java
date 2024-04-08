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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * JavaFX Weather Application.
 */
public class WeatherApp extends Application {

    private WeatherApi api;
    private Gson gson;

    @Override
    public void start(Stage stage) throws FileNotFoundException {
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

    private VBox getCenterVBox() throws FileNotFoundException {
        //Creating an HBox.
        VBox centerHBox = new VBox(10);

        //Adding two VBox to the HBox.
        centerHBox.getChildren().addAll(getCurrentWeatherBox(),getTopHBox(), getBottomHBox() );

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

    private LocationData locationSearch(String location) {
        Type listType = new TypeToken<List<LocationData>>() {
        }.getType();
        List<LocationData> ldata = gson.fromJson(api.lookUpLocation(location), listType);

        return ldata.get(0);
    }

    private WeatherData weatherSearch(LocationData ldata) {
        WeatherData wdata = gson.fromJson(api.getCurrentWeather(ldata.getLat(),
                ldata.getLon()), WeatherData.class);

        return wdata;
    }

    private ForecastData forecastSearch(LocationData ldata) {
        ForecastData fdata = gson.fromJson(api.getForecast(ldata.getLat(),
                ldata.getLon()), ForecastData.class);

        return fdata;
    }

    private VBox getCurrentWeatherBox() throws FileNotFoundException {

        Font boldFont = Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 13);
        //Creating a VBox for the left side.
        VBox currentWeatherBox = new VBox(6);
        currentWeatherBox.setPrefHeight(330);
        currentWeatherBox.setStyle("-fx-background-color: #41ac44;");

        Label currentWeatherLabel = new Label("Current Weather");
        currentWeatherLabel.setFont(boldFont);

        HBox currentTemperatureBox = new HBox();
        Font currentTemperatureFont = Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 50);
        Label currentTemperatureLabel = new Label("-5");
        Label currentTemperatureUnitLabel = new Label(" °C");
        currentTemperatureLabel.setFont(currentTemperatureFont);
        currentTemperatureUnitLabel.setFont(currentTemperatureFont);

        InputStream stream = new FileInputStream("rain-cloud.png");
        Image image = new Image(stream);
        //Creating the image view
        ImageView imageView = new ImageView();
        //Setting image to the image view
        imageView.setImage(image);
        //Setting the image view parameters

        imageView.setFitWidth(80);
        imageView.setPreserveRatio(true);

        currentTemperatureBox.getChildren().addAll(imageView, currentTemperatureLabel, currentTemperatureUnitLabel);
        currentTemperatureBox.setAlignment(Pos.CENTER);

        HBox feelsLikeBox = new HBox();
        Label feelsLikeLabel = new Label("Feels like: ");
        Label feelsLikeContentLabel = new Label("-10");
        feelsLikeContentLabel.setFont(boldFont);
        Label feelsLikeUnitLabel = new Label(" °C");
        feelsLikeBox.getChildren().addAll(feelsLikeLabel, feelsLikeContentLabel, feelsLikeUnitLabel);
        feelsLikeBox.setAlignment(Pos.CENTER);

        HBox weatherDetailsBox = new HBox();
        Label airQualityLabel = new Label("Air Quality: ");
        Label airQualityContentLabel = new Label("Good");
        airQualityContentLabel.setFont(boldFont);
        HBox.setMargin(airQualityContentLabel, new Insets(0, 20, 0, 0));
        SVGPath dropSVG = new SVGPath();
        dropSVG.setContent("M132.281,264.564c51.24,0,92.931-41.681,92.931-92.918c0-50.18-87.094-164.069-90.803-168.891L132.281,0l-2.128,2.773\n"
                + "			c-3.704,4.813-90.802,118.71-90.802,168.882C39.352,222.883,81.042,264.564,132.281,264.564z");
        final Region dropShape = new Region();
        dropShape.setShape(dropSVG);
        dropShape.setMinSize(10, 13);
        dropShape.setPrefSize(10, 13);
        dropShape.setMaxSize(10, 13);
        dropShape.setStyle("-fx-background-color: black;");

        Label rainLabel = new Label("0.0");
        Label rainUnitLabel = new Label(" mm");
        HBox.setMargin(rainUnitLabel, new Insets(0, 20, 0, 0));
        rainLabel.setFont(boldFont);

        SVGPath windSVG = new SVGPath();
        windSVG.setContent("M11 2.206l-6.235 7.528-.765-.645 7.521-9 7.479 9-.764.646-6.236-7.53v21.884h-1v-21.883z");
        final Region windShape = new Region();
        windShape.setShape(windSVG);
        windShape.setMinSize(10, 13);
        windShape.setPrefSize(10, 13);
        windShape.setMaxSize(10, 13);
        windShape.setStyle("-fx-background-color: black;");
        HBox.setMargin(windShape, new Insets(0, 0, 0, 0));
        Label windLabel = new Label("0.0");
        Label windUnitLabel = new Label(" m/s");
        windLabel.setFont(boldFont);

        weatherDetailsBox.getChildren().addAll(airQualityLabel, airQualityContentLabel, dropShape, rainLabel, rainUnitLabel, windShape, windLabel, windUnitLabel);
        weatherDetailsBox.setAlignment(Pos.CENTER);

        currentWeatherBox.getChildren().addAll(currentWeatherLabel, currentTemperatureBox, feelsLikeBox, weatherDetailsBox);
        currentWeatherBox.setAlignment(Pos.TOP_CENTER);

        return currentWeatherBox;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp.components;

import fi.tuni.prog3.weatherapp.apigson.location.LocationData;
import fi.tuni.prog3.weatherapp.apigson.weather.WeatherData;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 *
 * @author user
 */
public class CurrentWeatherDisplay extends VBox {
    
    VBox currentWeatherBox;
    Label currentLocationLabel;
    Label currentTemperatureLabel;
    Label windLabel;
    Label feelsLikeContentLabel;
    Label rainLabel;
    
    public CurrentWeatherDisplay() throws FileNotFoundException {
        Font boldFont = Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 13);
        Font titleFont = Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20);
        //Creating a VBox for the left side.
        currentWeatherBox = new VBox(6);
        currentWeatherBox.setPrefHeight(330);
        currentWeatherBox.setStyle("-fx-background-color: #41ac44;");
        
        currentLocationLabel = new Label("Current Location");
        currentLocationLabel.setFont(titleFont);
        
        Label currentWeatherLabel = new Label("Current Weather");
        currentWeatherLabel.setFont(boldFont);
        
        HBox currentTemperatureBox = new HBox();
        Font currentTemperatureFont = Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 50);
        currentTemperatureLabel = new Label("-5");
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
        feelsLikeContentLabel = new Label("-10");
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
        
        rainLabel = new Label("0.0");
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
        windLabel = new Label("0.0");
        Label windUnitLabel = new Label(" m/s");
        windLabel.setFont(boldFont);
        weatherDetailsBox.getChildren().addAll(airQualityLabel, airQualityContentLabel, dropShape, rainLabel, rainUnitLabel, windShape, windLabel, windUnitLabel);
        weatherDetailsBox.setAlignment(Pos.CENTER);
        
        currentWeatherBox.getChildren().addAll(currentLocationLabel, currentWeatherLabel, currentTemperatureBox, feelsLikeBox, weatherDetailsBox);
        currentWeatherBox.setAlignment(Pos.TOP_CENTER);
    }
    
    public VBox getCurrentWeatherDisplay() {
        return currentWeatherBox;
        
    }
    
    public void updateValues(LocationData ldata, WeatherData wdata) {
        windLabel.setText(wdata.getWind().getSpeed().toString());
        Double temperature = wdata.getMain().getTemp() - 273.15;
        currentTemperatureLabel.setText(String.format("%.1f", temperature));
        Double feelsLikeTemperature = wdata.getMain().getFeels_like() - 273.15;
        feelsLikeContentLabel.setText(String.format("%.1f", feelsLikeTemperature));
        currentLocationLabel.setText(ldata.getName());
    }
    
}

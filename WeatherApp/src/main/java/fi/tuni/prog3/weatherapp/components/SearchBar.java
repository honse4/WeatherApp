/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp.components;

import fi.tuni.prog3.weatherapp.WeatherApp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author vasav
 */
public class SearchBar extends VBox {
    private final Stage stage;
    private final Scene scene;
    private final WeatherApp main;
    
    public SearchBar(Stage stage, Scene scene, WeatherApp main) {
        this.stage = stage;
        this.scene = scene;
        this.main = main;
        
        getChildren().add(createNewScene());
    }
    
    private VBox createNewScene() {
        
        TextField searchbar = new TextField();
        searchbar.setMinSize(300,30);
        searchbar.setStyle("-fx-border-radius: 20;");
        
        Label error = new Label("");
        error.setAlignment(Pos.CENTER);
        error.setMinWidth(200);
        
        VBox topRow = createTopRow(searchbar,
                getSearchButton(searchbar, error), error);
        
        VBox container = new VBox(topRow);
        VBox.setMargin(topRow, new Insets(50, 0, 0, 0));
        container.setSpacing(10);
        
        return container;
    }
    
    private VBox createTopRow(TextField searchbar, Button searchButton, Label error) {
        HBox searchRow = new HBox(searchbar, searchButton);
        searchRow.setSpacing(0);
        searchRow.setAlignment(Pos.CENTER);
        
        VBox topRow = new VBox(searchRow, error);
        topRow.setSpacing(5);
        topRow.setAlignment(Pos.CENTER);
        
        return topRow;
    }
    
    private Button getSearchButton(TextField searchbar, Label error) {
        Button searchButton = new Button();
        searchButton.setMinSize(50,30);
        
        searchButton.setOnAction(e -> {
            String search = searchbar.getText();
            searchbar.setText("");
            error.setText("");
            boolean value = main.searchResult(search);   
            
            if (value) {
                searchbar.requestFocus();
                stage.setScene(scene);
                scene.getRoot().requestFocus();
            }
            else {   
                error.setText("Place Does Not Exist");
            }    
        });
        
        return searchButton;
    }
}

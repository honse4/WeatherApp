package fi.tuni.prog3.weatherapp.components;

import fi.tuni.prog3.weatherapp.WeatherApp;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
    private final TextField searchbar;
    private final Label error;
    private final HBox loading ;
    
    public SearchBar(Stage stage, Scene scene, WeatherApp main) {
        this.stage = stage;
        this.scene = scene;
        this.main = main;
        this.searchbar = new TextField();
        this.error = new Label("");
        this.loading = new HBox(new Label("Loading..."));

        loading.setAlignment(Pos.CENTER);
        
        searchbar.setMinSize(300,30);
        searchbar.setStyle("-fx-border-color: transparent;");
        
        error.setAlignment(Pos.CENTER);
        error.setMinWidth(200);
        
        
        getChildren().addAll(getBackButton(), getVBox(),getFavourites());
        setSpacing(50);
        
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                onClick();
            }
        });
    }
    
    private Button getBackButton() {
        Button back = new Button("<-");
        back.setFocusTraversable(false);
        back.setMinWidth(40);
        
        back.setOnAction(e -> {
            searchbar.setText("");
            error.setText("");
            searchbar.requestFocus();
            stage.setScene(scene);
            scene.getRoot().requestFocus();    
        });
        VBox.setMargin(back, new Insets(0, -50,0, 0));
        return back;
    }
    
    private VBox getVBox() {
        Button searchButton = new Button();
        searchButton.setMinSize(50,30);
        
        searchButton.setOnAction(e -> {
            onClick();
        });
        
        VBox topRow = getTopRow(searchbar, searchButton, error);
        
        VBox container = new VBox(topRow);
        VBox.setMargin(topRow, new Insets(20, 0, 0, 0));
        container.setSpacing(10);
        
        return container;
    }
    
    private VBox getTopRow(TextField searchbar, Button searchButton, Label error) {
        HBox searchRow = new HBox(searchbar, searchButton);
        searchRow.setSpacing(0);
        searchRow.setAlignment(Pos.CENTER);
        searchRow.setStyle("-fx-border-color: #a0a0a0;"
                + "-fx-border-radius: 20; -fx-padding: 5; -fx-border-width: 1;");
        searchRow.setMaxWidth(400);
        
        VBox topRow = new VBox(searchRow, error);
        topRow.setSpacing(5);
        topRow.setAlignment(Pos.CENTER);
        
        return topRow;
    }
    
    private void onClick() {
        
        String search = searchbar.getText();
        searchbar.setText("");
        error.setText("");
        
        getChildren().add(2, loading);
        
        //ai
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return main.searchResult(search); 
            }

            @Override
            protected void succeeded() {
                Boolean result = getValue();
                if (result) {
                    searchbar.requestFocus();
                    stage.setScene(scene);
                    scene.getRoot().requestFocus();
                } else {
                    error.setText("Place Does Not Exist");
                }
                getChildren().remove(loading);
            }
        };
        new Thread(task).start();   
    }
    
    private VBox getFavourites() {
        Label title = new Label("Favourites");
        VBox favourites = new VBox(title);
        favourites.setAlignment(Pos.CENTER);
        return favourites;
    }
}

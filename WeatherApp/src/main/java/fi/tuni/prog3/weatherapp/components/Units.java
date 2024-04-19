package fi.tuni.prog3.weatherapp.components;

import fi.tuni.prog3.weatherapp.WeatherApp;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 *
 * @author vasav
 */
public class Units extends HBox{
    private final WeatherApp main;
    private boolean isMetric;
    private final Button changeMetric;
    private final Label message;
    private final RotateTransition animation;
    
    public Units(WeatherApp main) {
        this.main = main;
        this.isMetric = true;
        
        Arc arc = new Arc(20, 20, 8, 8, 0, 270);
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(Color.BLACK);
        arc.setStrokeWidth(2);
        
        this.changeMetric = new Button("Imperial");
        changeMetric.setOnAction(e -> {
            main.setUnit(isMetric ? "imperial" : "metric");
            onClick(main.getCurrentLocation().getName(), arc);
        });
        changeMetric.setFocusTraversable(false);
        
        this.message = new Label();
        message.setFont(new Font("Calibri", 10));

        this.animation = animation(arc);
        getChildren().add(changeMetric);
        
        
        setSpacing(2);
        
    }
    
    private void onClick(String search, Arc arc) {
        
        getChildren().add(arc);
        animation.play();
        
        //
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return main.searchResult(search); 
            }

            @Override
            protected void succeeded() {
                Boolean result = getValue();
                if (result) {
                    changeMetric.setText(isMetric ? "Metric" : "Imperial");
                    isMetric = !isMetric;
                    //getChildren().remove(message);
                } else {
                    getChildren().add(message);
                    message.setText("An error occured");
                }
                animation.pause();
                getChildren().remove(arc);
                
            }
        };
        new Thread(task).start();   
    }
    
    private RotateTransition animation(Arc arc) {   
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), arc);
        rotateTransition.setByAngle(360); 
        rotateTransition.setCycleCount(Animation.INDEFINITE); 
        rotateTransition.setInterpolator(Interpolator.LINEAR); 

        return rotateTransition;
    }
}

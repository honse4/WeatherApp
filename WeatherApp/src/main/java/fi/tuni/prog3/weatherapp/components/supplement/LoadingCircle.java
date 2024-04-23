package fi.tuni.prog3.weatherapp.components.supplement;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.util.Duration;

/**
 *
 * @author vasav
 */
public class LoadingCircle extends Arc{
    private final RotateTransition animation;
    
    /**
     * Constructor
     * @param radius1 int for x radius
     * @param radius2  int for y radius
     */
    public LoadingCircle(int radius1, int radius2 ){
        setRadiusX(radius1);
        setRadiusY(radius2);
        setCenterX(20);
        setCenterY(20);
        setLength(270);
        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
        
        animation = new RotateTransition(Duration.seconds(1), this);
        animation.setByAngle(360); 
        animation.setCycleCount(Animation.INDEFINITE); 
        animation.setInterpolator(Interpolator.LINEAR); 
    }
    
    /**
     * Plays the animation
     */
    public void play(){
        animation.play();
    }
    
    /**
     * Pauses the animation
     */
    public void pause(){
        animation.pause();
    }
}

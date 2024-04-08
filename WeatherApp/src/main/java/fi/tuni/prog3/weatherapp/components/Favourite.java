package fi.tuni.prog3.weatherapp.components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author vasav
 */
public class Favourite extends Polygon {
    private boolean isFavourite;
    
    public Favourite() {
        this.isFavourite = false;
        
        double angle = Math.PI / 5;
        double innerRadius = 12 * Math.sin(angle) / Math.cos(angle);
        
        for (int i = 0; i < 10; i++) {
            double r = (i % 2 == 0) ? 12 : innerRadius;
            getPoints().add(5 + r * Math.sin(i * 2 * Math.PI / 10));
            getPoints().add(5 - r * Math.cos(i * 2 * Math.PI / 10));
        }
        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
        setStrokeWidth(1);
        
        setOnMouseEntered(e -> {
            setScaleX(1.1);
            setScaleY(1.1);
            setScaleZ(1.1);
        });
        
        setOnMouseExited(e -> {
            setScaleX(1);
            setScaleY(1);
            setScaleZ(1);
        });
        
        setOnMousePressed(e ->{
            if(isFavourite) {
                setFill(Color.TRANSPARENT);
            }
            else {
                setFill(Color.YELLOW);
            }
            isFavourite = !isFavourite;    
        });
    } 
}

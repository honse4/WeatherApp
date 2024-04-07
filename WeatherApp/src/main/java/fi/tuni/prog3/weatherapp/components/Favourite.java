package fi.tuni.prog3.weatherapp.components;

import javafx.scene.shape.Polygon;

/**
 *
 * @author vasav
 */
public class Favourite extends Polygon{
    
    public Favourite() {
        double angle = Math.PI / 5; // 36 degrees
        double innerRadius = 12 * Math.sin(angle) / Math.cos(angle);
        
        for (int i = 0; i < 10; i++) {
            double r = (i % 2 == 0) ? 12 : innerRadius;
            getPoints().add(10 + r * Math.sin(i * 2 * Math.PI / 10));
            getPoints().add(10 - r * Math.cos(i * 2 * Math.PI / 10));
        }
        
    }
    
}

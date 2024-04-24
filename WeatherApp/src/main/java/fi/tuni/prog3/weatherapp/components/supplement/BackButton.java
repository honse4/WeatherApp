package fi.tuni.prog3.weatherapp.components.supplement;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.shape.SVGPath;

/**
 * Class containing stylings for a commonly usde buttin
 * @author vasav
 */
public class BackButton extends Button{
     
    /**
     * Constructor
     */
    public BackButton() {
        SVGPath leftArrow = new SVGPath();
        leftArrow.setContent("M 6 0 L 0 3 L 6 6 L 4.5 3 Z");
        leftArrow.setScaleX(1.75);  
        leftArrow.setScaleY(1.75);
        
        setAlignment(Pos.CENTER);
        setGraphic(leftArrow);
        setMaxSize(50,30);
        setFocusTraversable(false);
        setMinSize(50,30);  
    }
}

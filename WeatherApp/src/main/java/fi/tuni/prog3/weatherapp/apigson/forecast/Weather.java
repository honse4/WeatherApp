/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.weatherapp.apigson.forecast;


/**
 *
 * @author vasav
 */
public class Weather {
    private String main;
    private String description;
    
    public String getMain() {
        return this.main;
    }
    
    public void setMain(String main) {
        this.main = main;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    } 
    
}

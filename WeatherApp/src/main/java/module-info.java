module fi.tuni.progthree.weatherapp {
    requires javafx.controls;
    exports fi.tuni.prog3.weatherapp;
    opens fi.tuni.prog3.weatherapp.apigson.forecast to com.google.gson;
    opens fi.tuni.prog3.weatherapp.apigson.weather to com.google.gson;
    opens fi.tuni.prog3.weatherapp.apigson.location to com.google.gson;
    requires com.google.gson;
    opens fi.tuni.prog3.weatherapp.preferencesgson to com.google.gson;
}

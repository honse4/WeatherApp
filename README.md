# WeatherApp project

This project aims to make an application to display the weather in java using the javafx library. It also makes use of the gson library and api data from OpenWeatherMap organisation. 

The program allows a user to search for different locations and it diaplays a variety of information regarding weather data for that location, using different metrics and some charts along with it.

The code is split into 4 packages. 

fi.tuni.prog3.weatherapp: The main package, contains all the non-gui classes(Except for WeatherApp). All the api and file processing work happens here.

fi.tuni.prog3.weatherapp.apigson: Contains all the gson classes to process the json data received from the api.

fi.tuni.prog3.weatherapp.preferencesgson: Contains the gson class to work with file input and output.

fi.tuni.prog3.weatherapp.components: Contains the classes which are diaplyed on the gui, which the user can interact with. The package itself is split into 3 packages based on their relevance to the user.


Documentation is present in the Documentation folder in the root directory

Group 3546 - Onur Sertgil, Ville Kivioja, Vasav Juyal

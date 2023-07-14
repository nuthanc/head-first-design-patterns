package mylearnings.observer_2;

public class WeatherStation {
  public static void main(String[] args) {
    WeatherData weatherData = new WeatherData();
    new CurrentConditionsDisplay(weatherData);
    new HeatIndexDisplay(weatherData);

    // Other 2 display classes here
    weatherData.setMeasurements(80, 65, 30.5f);
    weatherData.setMeasurements(84, 70, 29.5f);
    weatherData.setMeasurements(78, 90, 29.2f);
  }
}

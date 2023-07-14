package mylearnings.observer_2;

public class CurrentConditionsDisplay implements Observer, DisplayElement {

  private float temp, humidity, pressure;
  private WeatherData weatherData; // Stored to un-register it in the future

  public CurrentConditionsDisplay(WeatherData weatherData){
    this.weatherData = weatherData; 
    weatherData.registerObserver(this);
  }

  @Override
  public void display() {
    System.out.println("Temperature: " + temp);
    System.out.println("Humidity: " + humidity);
    System.out.println("Pressure: " + pressure);
  }

  @Override
  public void update(float temp, float humidity, float pressure) {
    this.temp = temp;
    this.humidity = humidity;
    this.pressure = pressure;
    display();
  }

}

package mylearnings.observer_2;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {
  private float temp, humidity, pressure;
  private List<Observer> observers;

  public WeatherData() {
    observers = new ArrayList<Observer>();
  }

  @Override
  public void registerObserver(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for (Observer observer : observers) {
      observer.update(temp, humidity, pressure);
    }
  }

  public void measurementsChanged() {
    notifyObservers();
  }

  public void setMeasurements(float temp, float humidity, float pressure) {
    this.temp = temp;
    this.humidity = humidity;
    this.pressure = pressure;
    measurementsChanged();
  }

}

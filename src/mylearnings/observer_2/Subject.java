package mylearnings.observer_2;

public interface Subject {
  public void registerObserver(Observer observer);

  public void removeObserver(Observer observer);

  public void notifyObservers();
}

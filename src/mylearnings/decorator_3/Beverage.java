package mylearnings.decorator_3;

public abstract class Beverage {
  String description = "Unknown Beverage";

  public abstract double cost();

  public String getDescription() {
    return description;
  }
}

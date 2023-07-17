package mylearnings.decorator_3;

public abstract class CondimentDecorator extends Beverage {
  Beverage beverage;

  public abstract String getDescription();
}

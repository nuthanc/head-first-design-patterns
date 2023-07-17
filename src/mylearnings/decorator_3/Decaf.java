package mylearnings.decorator_3;

public class Decaf extends Beverage {

  public Decaf() {
    description = "Decaf";
  }

  @Override
  public double cost() {
    return 1.05;
  }

}


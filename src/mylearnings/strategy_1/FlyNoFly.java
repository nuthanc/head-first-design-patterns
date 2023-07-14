package mylearnings.strategy_1;

public class FlyNoFly implements FlyBehavior {
  @Override
  public void fly() {
    System.out.println("I cannot fly");    
  }
}

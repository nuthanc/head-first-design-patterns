package mylearnings.strategy_1;

public class RubberDuck extends Duck {

  public RubberDuck() {
    flyBehavior = new FlyNoFly();
    quackBehavior = new Squeak();
  }

  @Override
  public void display() {
    System.out.println("I am a rubber duck and I'm yellow");
  }

}

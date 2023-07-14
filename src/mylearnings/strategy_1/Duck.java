package mylearnings.strategy_1;

public abstract class Duck {
  FlyBehavior flyBehavior;
  QuackBehavior quackBehavior;

  public Duck() {
  }

  public void swim() {
    System.out.println("All ducks float, even decoys!");
  }

  // Need ducks to fly feature.
  // Adding it here causes even RubberDucks to fly which is not the desired
  // behavior
  // void fly() {
  // System.out.println("I fly");
  // }

  public void performFly() {
    flyBehavior.fly();
  }

  public void performQuack() {
    quackBehavior.quack();
  }

  public abstract void display();
}

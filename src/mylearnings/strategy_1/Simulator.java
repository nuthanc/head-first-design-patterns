package mylearnings.strategy_1;

public class Simulator {
  public static void main(String[] args) {
    Duck duck = new MallardDuck();
    duck.display();
    duck.performFly();
    duck.performQuack();

    System.out.println();

    Duck duck2 = new RubberDuck();
    duck2.display();
    duck2.performFly();
    duck2.performQuack();
  }
}

package mylearnings.strategy_1;

public class Quack implements QuackBehavior {

  @Override
  public void quack() {
    System.out.println("I quack");
  }
  
}

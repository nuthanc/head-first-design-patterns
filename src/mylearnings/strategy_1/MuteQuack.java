package mylearnings.strategy_1;

public class MuteQuack implements QuackBehavior {

  @Override
  public void quack() {
    System.out.println("I cannot quack");
  }
  
}

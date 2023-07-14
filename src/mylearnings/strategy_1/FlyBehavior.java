package mylearnings.strategy_1;

// Initially thought of implementing Flyable interface for every Subtype that flys
// But this prevents code reuse 
// and slight change in behavior of flying needs maintenance in lots of subtypes

// So added behavior specific implementation classes(Like FlyWithWings, FlyNoFly)
public interface FlyBehavior {
  public void fly();
}

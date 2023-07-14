### Intro To Design Patterns(Strategy Pattern)

* Check mylearnings/strategy_1 folder
* Author's in headfirst/designpatterns/strategy folder
* Strategy Pattern defines a family of algorithms, encapsulates each one, and makes makes them interchangeable
* Strategy lets the algorithm vary independently from clients that use it

#### Initial Game designed by Joe

* SimUDuck App where there are different ducks swimming and quacking
* `Duck Superclass`, and its `Subtypes(like MallardDuck, ReadheadDuck etc)` inheriting from Duck
* Duck class has concrete `swim and quack` methods, but `abstract display` method(since each subtypes look different)
* Subtypes **implement** display method

#### Because of Competitors, need to make Duck fly

* Joe adds **fly** to `Duck Superclass`
* But this had unintended consequences(`Rubber ducks flying`)
* He `overrid fly to do nothing and quack method to squeak` for Rubber duck
* For DecoyDuck, he needs to override `quack to do nothing` and `fly also to do nothing`

#### Spec would keep changing

* Spec will keep changing and he needs to Override everytime that happens
* So he removes fly and quack from `Duck Superclass` and add Flyable and Quackable `interfaces`
* This way only Ducks that fly and those that quack implement that interface

#### Slight behavior change in Flying

* Need to change in all Classes that implement Flyable interface
* Also interfaces don't provide implementation, so there is no code reuse
* Whenever you need to modify a behavior, you’re often forced to track down and change it in all the different subclasses where that behavior is defined, probably introducing new bugs along the way!
* So we’ll make a set of **classes** whose entire reason for living is to **represent a behavior** (for example, “squeaking”) 
* It’s the behavior class, rather than the Duck class(Sub-type), that will implement the behavior interface
* So the hierarchy is `Duck Superclass(Same behavior) -> Interfaces(Varying Behavior) -> Behavior specific Concrete classes -> composed in Duck Subclass`
* * By adding this Behavior specific Concrete classes which implement the Behavior Interface, now there is `Code reuse` and `Flexibility`(new behaviours can be added easily)
* The Duck superclass has **reference variables** for the **behavior interface types** so that SubClasses(like MallardDuck) can initialize them in their constructor
* Also for code reuse, **performFly and performQuack** are present in Duck Superclass
* We have **Composed the behaviors** in the Subclasses and not Inherited it in order to have **Loose Coupling**

```java
// Imagine an Abstract class Animal, with 2 concrete implementations, Dog and Cat
// Programming to an Implementation
Dog d = new Dog();
d.bark();

// Programming to an Interface/Supertype
Animal a = new Dog(); // Animal is Superclass of Dog
a.makeSound();

// Providing subtype(concrete implemenation object) at runtime
Animal a = getAnimal(); // Assigning concrete implementation object at runtime
a.makeSound();
```

#### Setting Behavior Dynamically

* setFlyBehavior and setQuackBehvior in Duck superclass for setting behavior dynamically

#### Design Puzzle Solution

* Character -> Abstract SuperClass
* WeaponBehavior -> Interface
* King, Queen, Knight, Troll -> Subclasses of Character(IS-A relationship)
* KnifeBehavior, BowAndArrowBehavior, AxeBehavior, SwordBehavior -> Behavior Classes that implement WeaponBehavior
* Character SuperClass has
  * WeaponBehavior weapon -> Composition
  * abstract fight method (This is similar to abstract display method of Duck)
  * setWeapon method

## Design Principle

* Encapsulate things **that vary** and separate them from things **that remain the same**
  * Even though *display* method varies, it is kept as abstract method in Superclass and implemented in Subclass because it is a **Unique behavior(one of a kind)** of Subclass(since all subtypes look different)
  * And abstract display is kept in Superclass because it is the part that remains the same(i.e. All ducks(subTypes) need to implement the display method(find similarity with swim method), so it's abstract and is in SuperClass)
* Program to an **interface(supertype)**, not an implementation
  * supertype meaning the declared type of the variables should be a supertype, usually an **abstract class or interface**
  * By programming to an Interface/Supertype, the actual runtime object **isn't locked into the code**
* Favor Composition over Inhertiance
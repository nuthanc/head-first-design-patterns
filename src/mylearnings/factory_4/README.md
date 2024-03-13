### Baking with OO Goodness: The Factory Pattern


#### Pizza shop Owner code

```java
Pizza orderPizza() {
  Pizza pizza = new Pizza();

  pizza.prepare();
  pizza.bake();
  pizza.cut();
  pizza.box();
  return pizza;
}
```

#### But more than 1 type of pizza

* Introduce type parameter
```java
Pizza orderPizza(String type) {
  Pizza pizza;

  if (type.equals("cheese")) {
    pizza = new CheesePizza();
  } else if (type.equals("greek")) {
    pizza = new GreekPizza();
  } else if (type.equals("pepperoni")) {
    pizza = new PepperoniPizza();
  }

  pizza.prepare();
  pizza.bake();
  pizza.cut();
  pizza.box();
  return pizza;
}
```
* Here we are instantiating the correct concrete class based on the type
* Note that all of these Concrete classes will have to implement the **Pizza interface(which in this case means extend the abstract Pizza class)**
* Each Pizza subType knows how to prepare itself

#### But with more pizzas and removal of some Pizzas

* This violates the Open-Closed principle
* According to our 1st Design Principle, Encapsulate what varies, we will pull the object creation code out of the orderPizza method and place it in an **object(Factory)** that is only going to worry about how to create pizzas

#### Building a simple pizza factory

* For encapsulating object creation for all Pizza, we are gonna define a class
```java
public class SimplePizzaFactory {
  public Pizza createPizza(String type) {
    Pizza pizza = null;
    if (type.equals("cheese")) {
      pizza = new CheesePizza();
    } else if (type.equals("pepperoni")) {
      pizza = new PepperoniPizza();
    } else if (type.equals("clam")) {
      pizza = new ClamPizza();
    }
    return pizza;
  }
}
```

#### Questions

* This seems like just moving our problem to another object
  * Makes our Code **reusable** like HomeDelivery class or PizzaShopMenu class can call the factory class
  * Also **1 place to make modifications** when the implementation changes
* Static implementation of createPizza
  * This is done to call without creating an object
  * But **overriding is not possible with static** in the Child classes

#### Reworking the PizzaStore class

* Rely on factory to create the Pizza for us
```java
public class PizzaStore {
  SimplePizzaFactory factory;
  public PizzaStore(SimplePizzaFactory factory) {
    this.factory = factory;
  }

  public Pizza orderPizza(String type) {
    Pizza pizza = factory.createPizza(type); // No more Concrete Instantiations here
    pizza.prepare();
    pizza.bake();
    pizza.cut();
    pizza.box();
    return pizza;
  }
}
```

#### Simple Factory defined

* Simple Factory is not a design pattern but a programming idiom

#### Franchising our Pizza Store

* PizzaStore became famous and we franchise to different places like NewYork, Chicago, California
* For this we can remove the `SimplePizzaFactory` and have `NewYorkPizzaFactory, ChicagoPizzaFactory and CaliforniaPizzaFactory`
```java
NewYorkPizzaFactory nyFactory = new NewYorkPizzaFactory();
PizzaStore nyStore = new PizzaStore(nyFactory);
nyStore.orderPizza("Veggie");

ChicagoPizzaFactory chicagoFactory = new ChicagoPizzaFactory();
PizzaStore chicagoStore = new PizzaStore(chicagoFactory);
chicagoStore.orderPizza("Veggie");
```


#### But you'd like a little more quality control

* Note: Your store means original PizzaStore and your factory is original PizzaStore's factories
* But each of these franchises(Pizza Stores) want to have their own customized **prepare, bake, cut** etc
  * Bake differently, No cutting, 3rd party boxes
  * These are part of `createPizza` because that gives the Pizza instance(where we have prepare, bake, cut and box)
  * But since Factory is provided by Original Pizza Store, the Franchises do not have control over these
* We need to create a framework to tie `Pizza Store` and `Pizza Creation` together, but yet allows things to remain flexible
* This is achieved by placing back the `createPizza` back to the PizzaStore but this time as an **abstract method**

#### Allowing the Subclasses to decide

* Then the franchises create a `PizzaStore subclass` for each regional style
* Re-use the well-honed order system(`orderPizza`) from PizzaStore and create subclass specific `createPizza`
```java
public abstract class PizzaStore {
  public Pizza orderPizza(String type) {
    Pizza pizza;
    pizza = createPizza(type);

    pizza.prepare();
    pizza.bake();
    pizza.cut();
    pizza.box();
    return pizza;
  }

  public abstract Pizza createPizza(String type);
}

public class NYPizzaStore extends PizzaStore {
  Pizza createPizza(String type) {
    if (type.equals("cheese")) {
      return new NYStyleCheesePizza();
    } else if (type.equals("pepperoni")) {
      return new NYStylePepperoniPizza();
    } else if (type.equals("clam")) {
      return new NYStyleClamPizza();
    } else if (type.equals("veggie")) {
      return new NYStyleVeggiePizza();
    } else return null;
  }
}

public class ChicagoPizzaStore extends PizzaStore {
  Pizza createPizza(String type) {
    Pizza pizza = null;
    if (type.equals("cheese")) {
      pizza = new ChicagoStyleCheesePizza();
    } else if (type.equals("pepperoni")) {
      pizza = new ChicagoStylePepperoniPizza();
    } else if (type.equals("clam")) {
      pizza = new ChicagoStyleClamPizza();
    } else if (type.equals("veggie")) {
      pizza = new ChicagoStyleVeggiePizza();
    }
    return pizza;
  }
}
```
* orderPizza doesn't have any idea on which concrete Pizza class is involved since Pizza is abstract, so they are `decoupled`
* By subClassing, the franchise gets all the benefits of PizzaStore functionality(like orderPizza and other methods) for free

#### Declaring a Factory method

* The responsibility of instantiating Pizzas is now moved to a method(createPizza) that acts as a factory
* A factory method handles object creation and encapsulates it in a subclass. This **decouples** the client code in the superclass from the object creation code in the subclass
* Check **pizzafm** folder for code
```java
public abstract class Pizza {
  String name;
  String dough;
  String sauce;
  List<String> toppings = new ArrayList<String>();

  void prepare() {
    System.out.println("Prepare " + name);
		System.out.println("Tossing dough...");
		System.out.println("Adding sauce...");
		System.out.println("Adding toppings: ");
		for (String topping : toppings) {
			System.out.println("   " + topping);
		}
  }

  void bake() {
    System.out.println("Bake for 25 minutes at 350");
  }

  void cut() {
    System.out.println("Cut the pizza into diagonal slices");
  }

  void box() {
    System.out.println("Place pizza in official PizzaStore box");
  }

  public String getName() {
    return name;
  }
}

public class NYStyleCheesePizza extends Pizza {
  public NYStyleCheesePizza() {
    name = "NY Style Sauce and Cheese Pizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
		toppings.add("Grated Reggiano Cheese");
  }
}

public class ChicagoStyleCheesePizza extends Pizza {
  public ChicagoStyleCheesePizza() {
    name = "Chicago Style Deep Dish Cheese Pizza";
		dough = "Extra Thick Crust Dough";
		sauce = "Plum Tomato Sauce";
		toppings.add("Shredded Mozzarella Cheese");
  }

  // Overriding cut method
  void cut() {
		System.out.println("Cutting the pizza into square slices");
	}
}

public class PizzaTestDrive {
  public static void main(String[] args) {
    PizzaStore nyStore = new NYPizzaStore();
    PizzaStore chicagoStore = new ChicagoPizzaStore();

    Pizza pizza = nyStore.orderPizza("Cheese");
    System.out.println("Ethan ordered a " + pizza.getName() + "\n");

    pizza = chicagoStore.orderPizza("Cheese");
    System.out.println("Joel ordered a " + pizza.getName() + "\n");
  }
}
```

### Types of Classes

* Creator Classes
  * Abstract Creator or just *Creator Class* -> PizzaStore
    * Contains the implementations of all methods required to manipulate **products**(Pizza), except for the factory method
  * Concrete Creator -> NYPizzaStore, ChicagoPizzaStore
    * Implements the factory method that actually produces Concrete products
* Product Classes
  * Abstract Product or just *Product Class* -> Pizza
  * Concrete Product -> NYStyleCheesePizza, NYStylePepperoniPizza, ChicagoStyleCheesePizza etc

### Factory Method Pattern Defined

* The Factory Method Patterns defines an interface for creating an object, but lets subclasses decide which class to instantiate.
  * We say "decide" not because the pattern allows subclasses themselves to decide, but rather, because the decision actually comes down to which subclass is used to create the product
* Factory method lets a class defer instantiation to subclasses

## Design Principle

* **Dependency Inversion Principle**
  * Depend upon abstractions and not on Concrete classes


**Start from Dependency Inversion Principle**
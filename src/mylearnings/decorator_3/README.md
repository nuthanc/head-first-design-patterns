### Decorating Objects: The Decorator Pattern

* `Decorator Pattern` **attaches** additional responsibilities to an object **dynamically**.
* Decorators provide a **flexible alternative to subClassing for extending functionality**

#### Starbuzz Coffee Ordering System Initial Design

* Beverage Abstract class
  * description reference
  * getDescription()
  * abstract cost() method
* SubClasses implement cost method
  * Decaf
  * Espresso
  * DarkRoast
  * HouseBlend
* Requirement to add Condiments charges in their Order system
  * Condiments like Steamed Milk, Mocha, Soy, Whipped Milk

#### First Attempt

* Class Explosion(Too many Subclasses like HouseBlendWithWhip, DecafWithWhipAndSoy, DecafWithSteamedMilk etc)

#### Instance Variables and inheritance in the SuperClass to keep track of Condiments

* booleans for all the Condiments with hasCondiment(generalized) and setCondiment(generalized)
* In `Subclass cost` method, it is `Superclass cost for selected Condiments + cost for that particular Subclass beverage`

#### Problems with the above implementation

* If price of anything changes, we need to modify existing code
* With new Condiments, additional boolean and setter methods in SuperClass
* Some of the new Subclasses like Tea will also get those Condiments, when it is not relevant to the SubClass
* Multiple instances of the same type like Double Mocha doesn't work correctly

#### The Decorator Pattern

* **Decorating existing objects** at **runtime** and use **delegation for Computation**
* For Example, if the Customer wants a Dark Roast with Mocha and Whip, then we'll
  * Start with Dark Roast object
  * Decorate or Wrap with Mocha object
  * Decorate with Whip object
  * Call cost and Rely on delegation to add up the costs
* `Key Learnings`:
  * The Decorator has the **same supertype** as the object it is decorating
  * You can use **one or more decorators to wrap on object**
    * This is what sets it apart from the **Strategy pattern** because there you can **interchange** but not have more than one type
  * Since the Decorators have the same supertype as the object it decorates, we can pass around a decorated objected in place of the original(wrapped) object
  * The Decorator **adds its own behavior** before or/and after **delegating to the object** it decorates to do its job

#### Decorating our Beverages

* Beverage SuperClass
  * description, getDescription() and abstract cost method
* SubClasses of Beverage
  * HouseBlend, Decaf, Espresso, DarkRoast with cost method
  * CondimentDecorator with Beverage instance variable and abstract getDescription method
    * Subclasses of CondimentDecorator:
      * Mocha, Milk, Soy and Whip with cost and getDescription methods

## Design Principle

* **Open Closed Principle**
  * Classes should be open for extension, but closed for modification
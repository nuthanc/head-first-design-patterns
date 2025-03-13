# Head First Design Patterns (2020 2nd Edition)

### Learning Principles

* Make it **Visual**(By making it Bizarre, Eye-Catching, Strange)
* Think Deeply by being **Curious**, Engaged, Inspired to solve Problems and generate new Knowledge
* Touch **Emotions** like Surprise, Curiosity, Fun
* Conversational style: Expected to Follow Along

1. Slow down. The more you understand, the less you have to memorize
2. Write your own notes
3. There Are No Dumb Questions
4. Make this the last thing you read before bed. Or at least the last challenging thing
5. Drink water. Lots of it
6. Talk about it. Out loud or Explain to someone else
7. Listen to your brain for Breaks
8. Feel something!
9. Design something!

### Source code

* http://wickedlysmart.com/head-first-design-patterns
* https://github.com/nuthanc/head-first-design-patterns

## Design Principle

* Encapsulate things **that vary** and separate them from things **that remain the same**
* Program to an **interface(supertype)**, not an implementation
  * supertype meaning the declared type of the variables should be a supertype, usually an **abstract class or interface**
  * By programming to an Interface/Supertype, the actual runtime object **isn't locked into the code**
* **Single Responsibility Principle**
  * Behavior specific Concrete classes have a single responsibility
* Favor **Composition** over Inheritance
* Strive for **Loosely Coupled Designs** between **objects that interact**
  * Interact with very little knowledge, like in case of Subjects all it knows is that Observers implement a certain Interface and the Subject uses that Interface
  * It gives more Flexibility(add, delete or replace)
* **Open Close Principle**
  * Classes should be open for extension, but closed for modification
* **Dependency Inversion Principle**
  * Depend upon abstractions and not on Concrete classes
 
### For Js and Python

* Break into smaller functions for SRP
* Use dictionary or object literal for switching dynamically
* Use generic function in Abstract Base class and get its specific implementation from sub-class if its one time use case. One step higher than this for re-usability is Strategy Design pattern
```
abstract class BaseDataPlaneFormModel {
  abstract getCloudId(): string
}

class AwsDataPlaneFormModel extends BaseDataPlaneFormModel {
  getCloud() {
    return this.accountField.value()?.cloudAccountId
  }
}

class GcpDataPlaneFormModel extends BaseDataPlaneFormModel {
  getCloud() {
    return this.projectField.value()?.cloudAccountId
  }
}
```
### Keeping your Objects in the Know: The Observer Pattern

* `Observer Pattern` defines a **One-to-Many** dependency between objects so that when **one object changes state**, all of its dependents are **notified and updated automatically**

#### Requirement from Weather Monitoring Station

* Display Device integration with WeatherData object
* System Components
  * Weather Station(Physical Device)
  * WeatherData Object(Tracks data from Weather Station)
  * Display showing Current weather conditions
* Implement 3 different display elements:
  * Current Conditions(show temperature, humidity and pressure)
  * Weather Statistics
  * Simple Forecast
* Real time updates to each of the Displays
* WeatherData has getTemperature(), getHumidity(), getPressure() and measurementsChanged() methods
* The first 3 methods return the most recent weather measurements
* measurementsChanged() gets called whenever the weather measurements have been updated

#### Initial Misguided Implementation

* Grab all **most recent measurements** by calling WeatherData's getter methods
* Call each of the **Display's update method** in the *measurementsChanged* method
```java
public void measurementsChanged() {
  float temp = getTemperature();
  float humidity = getHumidity();
  float pressure = getPressure();

  currentConditionsDisplay.update(temp, humidity, pressure);
  statisticsDisplay.update(temp, humidity, pressure);
  forecastDisplay.update(temp, humidity, pressure);
}
```

#### What's wrong with above implementation

* Let's think about the **effects of change** on this code
* Areas that vary are not encapsulated
* We have concrete implementations
  * We have no way to add or remove other display elements without making changes to the code
* Not possible to add or remove displays at runtime as it is hardcoded

#### The Observer Pattern

* Similar to working of **Newspaper or Magazine Subscription**
* There is a Newspaper Publisher
* There are Subscribers to a Publisher
* Every time there's a new edition, it gets delivered to the Subscriber
* You Unsubscribe when you don't want papers anymore
* **Publishers(Subject) + Subscribers(Observers) = Observer Pattern**
* Another nice example is **Headhunter and Job Seekers**
* Subject is the sole owner of the data
  * Cleaner OO design than allowing many objects to control the same data
* The Observer Pattern is a great example of **Loose Coupling**

#### The Observer Pattern Implementation

* Class design that revolves around *Subject* and *Observer* **interfaces**
* Subject interface has
  * registerObserver(), removeObserver() and notifyObservers()
* Observer interface has
  * update()
* ConcreteSubject
  * Implements all of the 3 methods of Subject interface
  * Has additional getState() and setState() methods
* ConcreteObserver
  * Implements the update method of Observer interface
  * Other Observer specific methods

#### Sketching out the classes

* Interfaces -> Subject, Observer and DisplayElement
  * Subject -> registerObserver(), removeObserver() and notifyObservers() methods
  * Observer -> update() method
  * DisplayElement -> display() method
* Classes
  * WeatherData -> Implements Subject
    * Methods: 
      * registerObserver(), removeObserver() and notifyObservers()
      * getTemperature(), getHumidity(), getPressure() and measurementsChanged()
  * CurrentConditionsDisplay, StatisticsDisplay, ForecastDisplay -> Implement Observer and DisplayElement interfaces
  * ThirdPartyDisplay can implement Observer and DisplayElement Interfaces to create their own display element
* `update(temp, humidity, pressure)`
  * In this initial implementation, all of these measurements were passed directly as above
  * But this is not wise, remember the Design principle, **encapsulate those that vary**
  * Here the measurements may increase or decrease
* `this.weatherData = weatherData; // Stored to un-register it in the future`
* weatherData object is passed during Object creation for the Displays, so that it can be registered in their Constructors itself and no need to manually adding another line to register it ourselves

#### Examples of being used

* JavaBeans and Swing Libraries make use of Observer Pattern
* JS Events

#### Need for Pull model

* The update method has all the measurements sent now. 
* What if another measurement like Wind Speed was added
  * Then we'll have to **change the all the update() methods**in the Displays even if most of them don't need wind speed data
* Since the Displays may not need all the data, we can use a **Pull Model** where an Observer requests for **specific measurement** from the Subject
* **Getter methods** in Subject
* And update method in Observer interface changed to argument-less(`update()`)
* Then in the concrete Observer classes
```java
void update() {
  this.temp = weatherData.getTemperature();
  this.humidity = weatherData.getHumidity();
  display();
}
```

#### Code Magnets

```java
public class ForecastDisplay implements Observer, DisplayElement {
  private WeatherData weatherData;
  private float currentPressure = 29.92f;
  private float lastPressure;

  public ForecastDisplay(WeatherData weatherData) {
    this.weatherData = weatherData;
    weatherData.registerObserver(this);
  }

  public void update() {
    lastPressure = currentPressure;
    currentPressure = weatherData.getPressure();
    display();
  }

  public void display() {
    // display code here
  }
}
```


## Design Principle

* Strive for **Loosely Coupled Designs** between objects that interact(This is again the Principle of **Program to an Interface(supertype)**)
  * This gives lot of flexibility. Some of them are
    * The only thing the Subject knows about an Observer is that it implements a certain interface(Observer interface in our case)
    * Ability to add, remove or replace Observer at any time
    * Never the need to modify the subject to add new types of Observers
    * Can Reuse Subjects or Observers independently of each other
    * Changes to either the subject or an observer will not affect the other
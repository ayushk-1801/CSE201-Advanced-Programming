# OOP Principles Used
## Encapsulation:  
Encapsulation involves bundling the data (attributes) and methods (functions) that operate on the data into a single unit, typically a class. This helps in protecting the data from outside interference and misuse.
In this project, classes like Customer, Admin, Order, and Item encapsulate their respective data and methods. For example, the Customer class contains attributes like username, password, name, and methods to operate on these attributes.
## Inheritance:  
Inheritance allows a new class to inherit properties and behavior from an existing class. This promotes code reusability.
Although not explicitly used in the provided code, the concept is demonstrated through interfaces like AdminInterface and CustomerInterface, which define methods that the Admin and Customer classes must implement.
## Polymorphism:  
Polymorphism allows methods to do different things based on the object it is acting upon, even though they share the same name.
This is achieved through the use of interfaces. Both Admin and Customer classes implement their respective interfaces, allowing different behaviors for methods like addItem, removeItem, browseItems, etc.
## Abstraction:  
Abstraction involves hiding the complex implementation details and showing only the necessary features of an object.
The interfaces AdminInterface and CustomerInterface provide a high-level view of what operations can be performed by Admin and Customer without exposing the implementation details.
# Assumptions Made
## User Authentication:  
Simple username and password-based authentication is assumed for both admin and customer logins.
## Order ID Generation:  
Order IDs are auto-incremented starting from 1.
## Item Availability:  
Items have an availability status that can be toggled.
## VIP Customers:  
VIP customers have priority in the order queue.
## Order Status:  
Orders can have statuses like PENDING, PREPARED, DELIVERED, CANCELLED, REFUNDED, and DENIED.
## Data Storage:  
Data is stored in static collections within the DATABASE class, assuming in-memory storage for simplicity.
## Input Handling:  
User inputs are handled via the console using Scanner.

These principles and assumptions help in creating a modular, maintainable, and scalable system for managing a simple restaurant ordering system.
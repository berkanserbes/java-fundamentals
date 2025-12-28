# Module 10: Object-Oriented Programming (OOP)

## What You'll Learn

This module provides comprehensive coverage of Object-Oriented Programming concepts in Java:
- Classes and Objects
- Fields and Methods
- Encapsulation
- Inheritance
- Abstraction
- Interfaces
- Polymorphism
- Enums (Enumerations)

## Learning Objectives

By the end of this module, you will:
- Understand class structure and object creation
- Master instance and static members
- Implement proper encapsulation with access modifiers
- Use inheritance to create class hierarchies
- Apply abstraction with abstract classes
- Define and implement interfaces
- Leverage polymorphism for flexible code
- Understand the four pillars of OOP

## Classes

1. **ClassesAndObjects** - Fundamentals
   - Class structure and components
   - Object creation and initialization
   - Multiple objects and their independence
   - Reference types vs primitives
   - Null references and NullPointerException
   - Object comparison (== vs equals())

2. **FieldsAndMethods** - Class members
   - Instance fields vs static fields
   - Constructors and constructor chaining
   - Instance methods vs static methods
   - Method overloading
   - 'this' keyword usage

3. **Encapsulation** - Data hiding
   - Private fields with public getters/setters
   - Data validation in setters
   - Read-only fields (final, no setter)
   - Write-only fields (no getter)
   - Access modifiers (private, default, protected, public)
   - Best practices for encapsulation

4. **Inheritance** - Code reusability
   - Single inheritance (extends)
   - Method overriding
   - 'super' keyword
   - Constructor chaining in inheritance
   - Multilevel inheritance
   - Hierarchical inheritance
   - instanceof operator

5. **Abstraction** - Hiding complexity
   - Abstract classes
   - Abstract methods
   - Concrete methods in abstract classes
   - Constructors in abstract classes
   - Multiple levels of abstraction
   - Real-world examples (Banking system)

6. **Interfaces** - Contracts
   - Interface definition and implementation
   - Multiple interface implementation
   - Interface inheritance (extends)
   - Default methods (Java 8+)
   - Static methods in interfaces
   - Polymorphism with interfaces
   - Real-world examples (Database, Payment)

7. **Polymorphism** - Many forms
   - Compile-time polymorphism (overloading)
   - Runtime polymorphism (overriding)
   - Upcasting (implicit)
   - Downcasting (explicit)
   - Dynamic method dispatch
   - Polymorphism with interfaces
   - Real-world examples (Employee system)

8. **Enums** - Sabit değer tipleri
   - Temel enum tanımı ve kullanımı
   - Enum built-in metodları (name, ordinal, values, valueOf)
   - Field'lı enum (HTTP Status, Planet)
   - Method'lu enum (Operation, TrafficLight)
   - Constructor'lu enum (ProgrammingLanguage)
   - Switch-case ile enum kullanımı (Java 14+ switch expression dahil)
   - Enum karşılaştırma (== vs equals)
   - Interface implement eden enum
   - Enum Singleton pattern
   - EnumSet ve EnumMap kullanımı
   - Nested enum ve abstract method'lu enum

## Running the Classes

```bash
.\run-module.bat 10-OOP ClassesAndObjects
.\run-module.bat 10-OOP FieldsAndMethods
.\run-module.bat 10-OOP Encapsulation
.\run-module.bat 10-OOP Inheritance
.\run-module.bat 10-OOP Abstraction
.\run-module.bat 10-OOP Interfaces
.\run-module.bat 10-OOP Polymorphism
.\run-module.bat 10-OOP Enums
```

## The Four Pillars of OOP

### 1. Encapsulation
**Definition**: Bundling data and methods that operate on that data within a single unit (class) and restricting direct access.

**Implementation**:
```java
class BankAccount {
    private double balance;  // Private field
    
    public void deposit(double amount) {  // Public method
        if (amount > 0) {
            balance += amount;
        }
    }
    
    public double getBalance() {
        return balance;
    }
}
```

**Benefits**:
- Data hiding and security
- Controlled access through methods
- Flexibility to change implementation
- Validation and business logic in one place

### 2. Inheritance
**Definition**: Mechanism where a class acquires properties and behaviors of another class.

**Implementation**:
```java
class Animal {
    void eat() { }
    void sleep() { }
}

class Dog extends Animal {
    void bark() { }  // Additional behavior
}
```

**Benefits**:
- Code reusability
- Hierarchical classification
- Method overriding for polymorphism
- Reduces code duplication

### 3. Abstraction
**Definition**: Hiding implementation details and showing only essential features.

**Implementation**:
```java
abstract class Shape {
    abstract double getArea();  // What to do
    
    void display() {  // How to do (concrete)
        System.out.println("Area: " + getArea());
    }
}

class Circle extends Shape {
    double radius;
    
    double getArea() {  // Implementation
        return Math.PI * radius * radius;
    }
}
```

**Benefits**:
- Reduces complexity
- Focuses on what rather than how
- Provides template for subclasses
- Easier maintenance

### 4. Polymorphism
**Definition**: Ability of an object to take many forms.

**Implementation**:
```java
// Compile-time (Overloading)
int add(int a, int b) { return a + b; }
double add(double a, double b) { return a + b; }

// Runtime (Overriding)
Animal animal = new Dog();
animal.makeSound();  // Calls Dog's implementation
```

**Benefits**:
- Flexibility and extensibility
- Loose coupling
- Code reusability
- Dynamic behavior

## Key Concepts

### Access Modifiers
| Modifier | Class | Package | Subclass | World |
|----------|-------|---------|----------|-------|
| public | Yes | Yes | Yes | Yes |
| protected | Yes | Yes | Yes | No |
| default | Yes | Yes | No | No |
| private | Yes | No | No | No |

### Abstract Class vs Interface

| Feature | Abstract Class | Interface |
|---------|---------------|-----------|
| Methods | Abstract + Concrete | Abstract (+ default in Java 8+) |
| Fields | Any type | public static final only |
| Constructor | Yes | No |
| Inheritance | Single (extends) | Multiple (implements) |
| When to use | IS-A relationship | CAN-DO capability |

### Method Overloading vs Overriding

| Aspect | Overloading | Overriding |
|--------|-------------|------------|
| Definition | Same name, different parameters | Same signature in parent and child |
| When | Compile-time | Runtime |
| Where | Same class or subclass | Subclass only |
| Return type | Can be different | Must be same or covariant |
| Access modifier | Can be any | Cannot be more restrictive |

## Best Practices

### General
1. Follow single responsibility principle
2. Favor composition over inheritance
3. Program to interfaces, not implementations
4. Use meaningful class and method names
5. Keep classes focused and cohesive

### Encapsulation
1. Make fields private by default
2. Provide public getters/setters only when needed
3. Validate data in setters
4. Use final for immutable fields
5. Hide implementation details

### Inheritance
1. Use inheritance for IS-A relationships
2. Don't inherit just for code reuse
3. Keep inheritance hierarchies shallow
4. Use @Override annotation
5. Call super() in constructors

### Abstraction
1. Use abstract classes for partial implementation
2. Use interfaces for pure abstraction
3. Keep abstract classes focused
4. Document abstract methods clearly
5. Provide meaningful default implementations

### Polymorphism
1. Use polymorphism for flexibility
2. Check with instanceof before downcasting
3. Prefer interface types for variables
4. Avoid type checking in polymorphic code
5. Use polymorphism to eliminate switch statements

## Common Pitfalls

1. **Breaking Encapsulation**
   ```java
   // Bad: Exposing mutable objects
   public List<String> getItems() {
       return items;  // Caller can modify!
   }
   
   // Good: Return defensive copy
   public List<String> getItems() {
       return new ArrayList<>(items);
   }
   ```

2. **Improper Inheritance**
   ```java
   // Bad: Inheritance for code reuse
   class Stack extends ArrayList { }  // Stack IS-NOT-A ArrayList
   
   // Good: Composition
   class Stack {
       private List<Object> elements = new ArrayList<>();
   }
   ```

3. **Forgetting @Override**
   ```java
   // Bad: Typo creates new method
   public void tostring() { }  // Doesn't override toString()
   
   // Good: Compiler catches error
   @Override
   public void toString() { }
   ```

4. **Unsafe Downcasting**
   ```java
   // Bad: No check
   Circle c = (Circle) shape;  // May throw ClassCastException
   
   // Good: Check first
   if (shape instanceof Circle) {
       Circle c = (Circle) shape;
   }
   ```

5. **Calling Overridable Methods in Constructor**
   ```java
   // Bad: Dangerous
   class Parent {
       Parent() {
           init();  // Calls overridden method
       }
       void init() { }
   }
   ```

## Resources

- [Java OOP - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/concepts/)
- [Classes and Objects - Oracle](https://docs.oracle.com/javase/tutorial/java/javaOO/)
- [Java Fields - Jenkov](https://jenkov.com/tutorials/java/fields.html#static-and-non-static-fields)
- [Inheritance - Oracle](https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html)
- [Interfaces - Oracle](https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html)
- [Polymorphism - Oracle](https://docs.oracle.com/javase/tutorial/java/IandI/polymorphism.html) 
- [Java Class and Objects - Programiz](https://www.programiz.com/java-programming/class-objects)
- [OOP Principles - Baeldung](https://www.baeldung.com/java-oop)
- [Java Interfaces - Jenkov](https://jenkov.com/tutorials/java/interfaces.html)
- [Java Interfaces - Baeldung](https://www.baeldung.com/java-interfaces)
- [Java Interface Private Methods - Baeldung](https://www.baeldung.com/java-interface-private-methods)
- [Java Encapsulation - Tutorialspoint](https://www.tutorialspoint.com/java/java_encapsulation.htm)
- [Java Inheritance - Jenkov](https://jenkov.com/tutorials/java/inheritance.html)
- [Java Inheritance - DigitalOcean](https://www.digitalocean.com/community/tutorials/inheritance-java-example)
- [Java Abstract Classes - Jenkov](https://jenkov.com/tutorials/java/abstract-classes.html)
- [Java Interfaces vs Abstract Classes - Jenkov](https://jenkov.com/tutorials/java/interfaces-vs-abstract-classes.html)
- [A Guide to Java Enums - Baeldung](https://www.baeldung.com/a-guide-to-java-enums)
- [Java Enums - Jenkov](https://jenkov.com/tutorials/java/enums.html)
- [Java enums - Programiz](https://www.programiz.com/java-programming/enums)
- [Java enum Constructor - Programiz](https://www.programiz.com/java-programming/enum-constructor)
- [Java enum string- Programiz](https://www.programiz.com/java-programming/enum-string)
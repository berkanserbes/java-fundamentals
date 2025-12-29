# Module 12: Lambda Expressions

## 📖 What You'll Learn

This module covers functional programming in Java with lambda expressions, functional interfaces, and anonymous classes.

## 📚 Topics Covered

### 1. Anonymous Classes (`AnonymousClasses.java`)

Anonymous classes are unnamed classes declared and instantiated in a single expression.

| Topic | Description |
|-------|-------------|
| **Basic Anonymous Class** | Syntax and basic usage |
| **Interface Implementation** | Implementing interfaces with anonymous classes |
| **Abstract Class Extension** | Extending abstract classes |
| **Constructor Arguments** | Passing arguments to parent constructor |
| **Variable Access** | final and effectively final variables |
| **'this' Reference** | How 'this' behaves in anonymous classes |
| **Comparator Example** | Real-world sorting with Comparator |
| **Event Handler Pattern** | GUI-style event handling |
| **Multiple Methods** | When Lambda can't be used |
| **Anonymous vs Named** | Comparison and use cases |

### 2. Lambda Expressions (`LambdaExpressions.java`)

Lambda expressions provide a concise way to represent functional interfaces.

| Topic | Description |
|-------|-------------|
| **Lambda Syntax** | Different syntax variations |
| **Functional Interfaces** | @FunctionalInterface rules |
| **Built-in Interfaces** | Predicate, Function, Consumer, Supplier, etc. |
| **Method References** | Using `::` operator |
| **Closure and Scope** | Variable capture rules |
| **Collections** | forEach, removeIf, replaceAll, sort |
| **Stream API** | filter, map, reduce, collect |
| **Function Composition** | andThen, compose chaining |
| **Custom Interfaces** | Creating your own functional interfaces |
| **Real-World Examples** | Strategy pattern, validation, retry mechanism |

## � Key Concepts

### Lambda Syntax
```java
// No parameters
() -> System.out.println("Hello")

// Single parameter (parentheses optional)
x -> x * 2

// Multiple parameters
(a, b) -> a + b

// Block body
(x, y) -> {
    int result = x + y;
    return result;
}
```

### Built-in Functional Interfaces
```java
Predicate<T>      // T -> boolean
Function<T, R>    // T -> R
Consumer<T>       // T -> void
Supplier<T>       // () -> T
BiFunction<T,U,R> // T, U -> R
UnaryOperator<T>  // T -> T
BinaryOperator<T> // T, T -> T
```

### Method References
```java
// Static method
Integer::parseInt

// Instance method (specific object)
System.out::println

// Instance method (arbitrary object)
String::toUpperCase

// Constructor
ArrayList::new
```

### Anonymous Class vs Lambda

| Anonymous Class | Lambda Expression |
|-----------------|-------------------|
| Can implement any interface | Only functional interfaces |
| Can extend abstract classes | Cannot extend classes |
| Can have multiple methods | Single abstract method only |
| Has its own 'this' reference | 'this' refers to enclosing class |
| Can have instance variables | Cannot have instance variables |
| More verbose | More concise |

## �🚀 Running

```bash
# Run Anonymous Classes demo
.\run-module.bat 12-LambdaExpressions AnonymousClasses

# Run Lambda Expressions demo
.\run-module.bat 12-LambdaExpressions LambdaExpressions

# Run basic Lambda demo
.\run-module.bat 12-LambdaExpressions LambdaDemo
```

## 📁 Project Structure

```
12-LambdaExpressions/
├── src/main/java/com/fundamentals/lambdaexpressions/
│   ├── AnonymousClasses.java    # Anonymous class concepts
│   ├── LambdaExpressions.java   # Lambda expressions deep dive
│   └── LambdaDemo.java          # Quick lambda demo
├── pom.xml
└── README.md
```

## 💡 Tips

- Use **Lambda** when working with functional interfaces (single abstract method)
- Use **Anonymous Classes** when:
  - Implementing interfaces with multiple methods
  - Extending abstract classes
  - Needing `this` to refer to the implementation
  - Needing instance variables
- **Method References** are cleaner than lambdas when just calling an existing method
- Variables captured by lambdas must be **effectively final**


## Resources
- [Lambda Expressions - Oracle](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
- [Lambda Expressions - Jenkov](https://jenkov.com/tutorials/java/lambda-expressions.html)
- [Lambda Expressions and Functional Interfaces: Tips and Best Practices - Baeldung](https://www.baeldung.com/java-8-lambda-expressions-tips)
- [Lambda Expressions - Programiz](https://www.programiz.com/java-programming/lambda-expression)
- [Java Lambda Expressions - GeeksforGeeks](https://www.geeksforgeeks.org/java/lambda-expressions-java-8/)
- [Java Lambda Expressions - W3Schools](https://www.w3schools.com/java/java_lambda.asp)
- [A Complete Guide to Lambda Expressions in Java](https://devcookies.medium.com/a-complete-guide-to-lambda-expressions-in-java-0aea2e1cea42)
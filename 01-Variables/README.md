# Module 01: Variables

## 📖 What You'll Learn

This module covers the fundamentals of Java variables:
- Variable declaration and initialization
- Naming conventions and best practices
- Variable scope (local, instance, class)
- Constants using `final` keyword
- Variable shadowing
- Type inference with `var` (Java 10+)

## 🎯 Learning Objectives

By the end of this module, you will:
- ✅ Understand how to declare and initialize variables
- ✅ Know the difference between local, instance, and static variables
- ✅ Follow Java naming conventions
- ✅ Use constants effectively
- ✅ Understand variable scope and lifetime

## 📝 Exercises

1. **Exercise01_BasicVariables** - Variable declaration and initialization
2. **Exercise02_NamingConventions** - Proper variable naming
3. **Exercise03_VariableScope** - Understanding scope
4. **Exercise04_Constants** - Working with final variables
5. **Exercise05_TypeInference** - Using var keyword
6. **Challenge_VariableSwap** - Swap variables without a third variable

## 🚀 Running the Exercises

```bash
# From the root directory
.\run-module.bat 01-Variables Exercise01_BasicVariables

# Or from this module directory
mvn clean compile exec:java -Dexec.mainClass="com.fundamentals.variables.Exercise01_BasicVariables"
```

## 💡 Key Concepts

### Variable Declaration
```java
int age;              // Declaration
age = 25;             // Initialization
int count = 10;       // Declaration + Initialization
```

### Naming Conventions
- Use camelCase for variables: `firstName`, `totalCount`
- Use UPPER_SNAKE_CASE for constants: `MAX_SIZE`, `PI`
- Use descriptive names: `studentAge` instead of `sa`

### Variable Scope
- **Local**: Declared inside methods, only accessible within that method
- **Instance**: Declared in class, accessible to all instance methods
- **Static/Class**: Declared with `static`, shared across all instances

## 🎓 Best Practices

1. Initialize variables when you declare them
2. Use meaningful, descriptive names
3. Keep variable scope as narrow as possible
4. Use `final` for values that shouldn't change
5. Avoid single-letter names (except for loop counters)

## 📚 Additional Resources

- [Oracle Java Variables Tutorial](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/variables.html)
- [Java Naming Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)

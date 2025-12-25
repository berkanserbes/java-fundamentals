# Module 09: Methods

## 📖 What You'll Learn

This module covers comprehensive method concepts in Java:
- Method basics and structure
- Parameters and pass-by-value
- Method overloading and varargs
- Recursion and backtracking
- Best practices and common pitfalls

## 🎯 Learning Objectives

By the end of this module, you will:
- ✅ Understand method structure and components
- ✅ Master parameter passing (primitives vs references)
- ✅ Use final parameters effectively
- ✅ Implement method overloading correctly
- ✅ Work with varargs (variable arguments)
- ✅ Write and optimize recursive methods
- ✅ Avoid common method-related pitfalls

## 📝 Classes

1. **MethodBasics** - Fundamental method concepts
   - No parameters/return vs with parameters/return
   - Access modifiers (public, private, protected, default)
   - Static vs instance methods
   - Method calling patterns

2. **MethodParameters** - Parameter handling
   - Pass-by-value for primitives
   - Pass-by-value for references
   - final parameters
   - Array and String parameters
   - Defensive copying

3. **MethodOverloading** - Overloading and varargs
   - Overloading by parameter count
   - Overloading by parameter type
   - Overloading by parameter order
   - Varargs (variable arguments)
   - Compiler resolution order

4. **RecursionDemo** - Recursive methods
   - Basic recursion concepts
   - Mathematical recursion (factorial, fibonacci, GCD)
   - String and array recursion
   - Tail recursion
   - Backtracking
   - Recursion vs iteration

## 🚀 Running the Classes

```bash
.\run-module.bat 09-Methods MethodBasics
.\run-module.bat 09-Methods MethodParameters
.\run-module.bat 09-Methods MethodOverloading
.\run-module.bat 09-Methods RecursionDemo
```

## 💡 Key Concepts

### Method Structure
```java
[access_modifier] [static] [return_type] methodName([parameters]) {
    // method body
    return value; // if not void
}
```

### Pass-by-Value
- **Java ALWAYS passes by value**
- Primitives: value is copied
- Objects: reference value is copied (can modify object, not reference)

### Method Overloading
- Same name, different parameters
- Different: count, type, or order of parameters
- Return type alone is NOT enough

### Recursion Components
1. **Base case**: Stop condition
2. **Recursive case**: Method calls itself
3. **Progress**: Move toward base case

## 🎓 Best Practices

### General
1. Use descriptive method names (verbs)
2. Keep methods short and focused (single responsibility)
3. Limit parameters (max 3-4)
4. Use proper access modifiers
5. Document complex methods

### Parameters
1. Use `final` for parameters you won't reassign
2. Document if method modifies parameters
3. Consider defensive copies for arrays/objects
4. Validate parameters (null checks, range checks)
5. Return new values instead of modifying primitives

### Overloading
1. Keep overloaded methods similar in behavior
2. Avoid ambiguous overloading
3. Use varargs sparingly
4. Document which overload to prefer

### Recursion
1. Always define clear base case(s)
2. Ensure progress toward base case
3. Consider stack depth limits
4. Use memoization for overlapping subproblems
5. Consider iteration for simple cases

## ⚠️ Common Pitfalls

1. **Primitive modifications don't persist**
   ```java
   void modify(int n) { n = 10; } // Doesn't affect original
   ```

2. **Confusing object modification vs reassignment**
   ```java
   void modify(int[] arr) {
       arr[0] = 10;  // Modifies original ✓
       arr = new int[]{1, 2};  // Doesn't affect original ✗
   }
   ```

3. **Using == instead of .equals() for Strings**

4. **Ambiguous overloading**
   ```java
   void method(String s) {}
   void method(Integer i) {}
   method(null); // ERROR: Ambiguous!
   ```

5. **Missing base case in recursion** → StackOverflowError

6. **Deep recursion** → StackOverflowError

7. **Inefficient recursion** (e.g., fibonacci without memoization)

## 📚 Resources

- [Java Methods - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html)
- [Java Methods - Jenkov](https://jenkov.com/tutorials/java/methods.html)
- [Java Methods - Programiz](https://www.programiz.com/java-programming/methods)
- [Method Overloading - Programiz](https://www.programiz.com/java-programming/method-overloading)
- [Java Recursion - Baeldung](https://www.baeldung.com/java-recursion)
- [Pass-by-Value - Baeldung](https://www.baeldung.com/java-pass-by-value-or-pass-by-reference)
- [Method Chaining - Jenkov](https://jenkov.com/tutorials/java/method-chaining.html)
- [Method Chaining - GeeksforGeeks](https://www.geeksforgeeks.org/java/method-chaining-in-java-with-examples/)

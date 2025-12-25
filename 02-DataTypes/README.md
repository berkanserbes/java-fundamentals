# Module 02: Data Types

## 📖 What You'll Learn

This module covers Java data types in depth:
- Primitive data types (byte, short, int, long, float, double, char, boolean)
- Reference types vs primitive types
- Wrapper classes (Integer, Double, etc.)
- Type casting and conversion
- Autoboxing and unboxing
- Default values

## 🎯 Learning Objectives

By the end of this module, you will:
- ✅ Understand all 8 primitive data types
- ✅ Know when to use each data type
- ✅ Perform type conversions safely
- ✅ Use wrapper classes effectively
- ✅ Understand memory implications of different types

## 📝 Exercises

1. **PrimitiveTypes** - All 8 primitive types with tricks and pitfalls
2. **WrapperClasses** - Integer, Double, autoboxing, and common mistakes
3. **TypeCasting** - Widening, narrowing, and best practices

## 🚀 Running the Exercises

```bash
.\run-module.bat 02-DataTypes PrimitiveTypes
```

## 💡 Key Concepts

### Primitive Types (8 total)
- **byte**: 8-bit integer (-128 to 127)
- **short**: 16-bit integer (-32,768 to 32,767)
- **int**: 32-bit integer (most common)
- **long**: 64-bit integer (for large numbers)
- **float**: 32-bit floating point
- **double**: 64-bit floating point (most common for decimals)
- **char**: 16-bit Unicode character
- **boolean**: true or false

### Reference Types
- Objects, Arrays, Strings, etc.
- Store references (memory addresses), not actual values
- Can be null

## 🎓 Best Practices

1. Use `int` for whole numbers unless you need a specific range
2. Use `double` for decimal numbers
3. Use wrapper classes when you need null or collections
4. Be careful with floating-point precision
5. Use explicit casting when narrowing types


## Resources
- [Java Data Types - Jenkov](https://jenkov.com/tutorials/java/data-types.html)
- [Java Type Casting - Programiz](https://www.programiz.com/java-programming/typecasting)
- [Type Casting in Java](https://www.simplilearn.com/tutorials/java-tutorial/type-casting-in-java)
- [Wrapper Classes in Java - GeeksforGeeks](https://www.geeksforgeeks.org/java/wrapper-classes-java/)
- [Java Wrapper Class - Programiz](https://www.programiz.com/java-programming/wrapper)

# Module 04: Strings

## 📖 What You'll Learn

This module covers comprehensive String manipulation in Java:
- String class and all its methods
- String immutability and String Pool
- StringBuilder and StringBuffer
- Performance considerations
- Best practices and common pitfalls

## 🎯 Learning Objectives

By the end of this module, you will:
- ✅ Master all major String methods
- ✅ Understand String immutability
- ✅ Know when to use String vs StringBuilder
- ✅ Avoid common String pitfalls
- ✅ Write efficient string manipulation code

## 📝 Classes

1. **Strings** - Comprehensive coverage of all String methods
   - Creation, inspection, comparison
   - Searching, extraction, modification
   - Formatting, split/join operations
   - Important tricks and pitfalls

2. **StringBuilderDemo** - Mutable string operations
   - StringBuilder vs String vs StringBuffer
   - Append, insert, delete, replace
   - Performance comparison
   - Best practices and real-world examples

## 🚀 Running the Classes

```bash
.\run-module.bat 04-Strings Strings
.\run-module.bat 04-Strings StringBuilderDemo
```

## 💡 Key Concepts

### String Characteristics
- **Immutable**: Once created, cannot be changed
- **String Pool**: Literals are stored in a special memory area
- **Thread-safe**: Due to immutability
- **Final class**: Cannot be extended

### When to Use What
- **String**: For immutable text, small concatenations
- **StringBuilder**: For string building in single-threaded code (FAST)
- **StringBuffer**: For string building in multi-threaded code (thread-safe)

## 🎓 Best Practices

1. Use `.equals()` for comparison, not `==`
2. Use `StringBuilder` for concatenation in loops
3. Use string literals (not `new String()`) for String Pool
4. Be careful with `null` strings
5. Use text blocks (Java 15+) for multiline strings
6. Set initial capacity for `StringBuilder` if size is known

## ⚠️ Common Pitfalls

1. Using `+` for concatenation in loops (very slow!)
2. Using `==` instead of `.equals()` for comparison
3. Forgetting that String methods return new String
4. NullPointerException when calling methods on null
5. Not using `StringBuilder` when building strings

## 📚 Resources

- [Java String - Oracle Docs](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html)
- [Java StringBuilder - Oracle Docs](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/StringBuilder.html)
- [Java String - Jenkov](https://jenkov.com/tutorials/java/strings.html)
- [String Handling - Baeldung](https://www.baeldung.com/java-string)
- [StringBuilder vs StringBuffer](https://www.baeldung.com/java-string-builder-string-buffer)
- [String Pool - Baeldung](https://www.baeldung.com/java-string-pool)


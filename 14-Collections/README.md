# Module 14: Collections

## What You'll Learn

This module covers the Java Collections Framework comprehensively:

- Collection Framework hierarchy and interfaces
- List implementations (ArrayList, LinkedList)
- Set implementations (HashSet, LinkedHashSet, TreeSet)
- SortedSet and NavigableSet
- Map implementations (HashMap, LinkedHashMap, TreeMap)
- SortedMap and NavigableMap
- Queue and PriorityQueue (FIFO)
- Deque and ArrayDeque (Double-ended queue)
- Stack (LIFO - legacy)
- Iterator and ListIterator
- Iterable interface and custom iterables
- Stream API for collections
- Generics and wildcards
- Immutable collections (Java 9+)

## Classes

1. **CollectionBasics** - Collection Framework fundamentals
   - Collection interface and hierarchy
   - Basic operations (add, remove, contains, size)
   - Bulk operations (addAll, removeAll, retainAll)
   - Different collection types comparison
   - Iteration methods

2. **ListOperations** - List interface and implementations
   - ArrayList operations (index-based access)
   - LinkedList operations (first/last methods)
   - Common List methods (indexOf, subList)
   - ArrayList vs LinkedList comparison
   - Searching and sorting

3. **SetOperations** - Set interface and implementations
   - HashSet (no order, fast)
   - LinkedHashSet (insertion order)
   - TreeSet (sorted order)
   - Mathematical set operations (union, intersection, difference)
   - Duplicate handling

4. **SortedSetOperations** - SortedSet and NavigableSet
   - Range operations (headSet, tailSet, subSet)
   - Navigation methods (lower, floor, ceiling, higher)
   - Custom comparators
   - Descending operations

5. **MapOperations** - Map interface and implementations
   - HashMap operations
   - LinkedHashMap (insertion order)
   - TreeMap (sorted keys)
   - Map methods (getOrDefault, putIfAbsent, compute, merge)
   - Map iteration (5 methods)

6. **SortedMapOperations** - SortedMap and NavigableMap
   - Range operations
   - Navigation methods
   - Custom comparators
   - Descending operations

7. **QueueOperations** - Queue interface (FIFO)
   - Basic queue operations
   - Queue methods comparison (add/offer, remove/poll, element/peek)
   - PriorityQueue with natural ordering
   - PriorityQueue with custom comparator
   - Queue as buffer pattern

8. **DequeOperations** - Deque interface (Double-ended queue)
   - Basic deque operations
   - Deque as Queue (FIFO)
   - Deque as Stack (LIFO)
   - ArrayDeque vs LinkedList comparison
   - Descending iteration

9. **StackOperations** - Stack class (LIFO)
   - Basic stack operations (push, pop, peek)
   - Stack methods (search, empty)
   - Parentheses matching algorithm
   - String reversal example
   - Stack vs Deque comparison

10. **IteratorOperations** - Iterator and ListIterator
    - Basic iterator usage
    - Safe removal during iteration
    - ListIterator basics
    - Bidirectional traversal
    - ListIterator modification
    - ConcurrentModificationException handling

11. **IterableDemo** - Iterable interface
    - Iterable basics
    - forEach method (Java 8+)
    - Custom Iterable implementation
    - Iterable vs Iterator difference
    - Multiple iterators from same collection

12. **CollectionStreams** - Stream API (Java 8+)
    - Stream creation methods
    - Intermediate operations (filter, map, sorted, distinct)
    - Terminal operations (collect, reduce, count, forEach)
    - Collectors (toList, toSet, groupingBy, joining)
    - Numeric streams (IntStream, statistics)
    - Practical examples

13. **GenericCollections** - Generics
    - Type safety
    - Generic methods
    - Bounded wildcards (?, extends, super)
    - PECS rule (Producer Extends, Consumer Super)
    - Custom generic classes
    - Multiple type parameters

14. **ImmutableCollections** - Immutable Collections (Java 9+)
    - Java 9+ factory methods (List.of, Set.of, Map.of)
    - Unmodifiable wrappers (Collections.unmodifiableXxx)
    - CopyOf methods (Java 10+)
    - Method comparison
    - Map.ofEntries for large maps
    - Practical use cases (config, defensive copying)

15. **ComparatorDemo** - Comparator and Comparable
    - Comparable interface (natural ordering)
    - Comparator interface (custom ordering)
    - Multiple sorting options
    - Modern Comparator methods (comparing, comparingInt, reversed)
    - Chained comparators (thenComparing)
    - Null handling (nullsFirst, nullsLast)
    - Practical examples

## Running the Classes

```bash
# From the root directory
.\run-module.bat 14-Collections CollectionBasics
.\run-module.bat 14-Collections ListOperations
.\run-module.bat 14-Collections SetOperations
.\run-module.bat 14-Collections SortedSetOperations
.\run-module.bat 14-Collections MapOperations
.\run-module.bat 14-Collections SortedMapOperations
.\run-module.bat 14-Collections QueueOperations
.\run-module.bat 14-Collections DequeOperations
.\run-module.bat 14-Collections StackOperations
.\run-module.bat 14-Collections IteratorOperations
.\run-module.bat 14-Collections IterableDemo
.\run-module.bat 14-Collections CollectionStreams
.\run-module.bat 14-Collections GenericCollections
.\\run-module.bat 14-Collections ImmutableCollections
.\\run-module.bat 14-Collections ComparatorDemo
```

## Collection Hierarchy

```
Iterable (root interface)
    |
    +-- Collection
          |
          +-- List (ordered, allows duplicates)
          |     +-- ArrayList (fast random access)
          |     +-- LinkedList (fast insertion/deletion)
          |     +-- Vector (synchronized, legacy)
          |
          +-- Set (no duplicates)
          |     +-- HashSet (no order, fastest)
          |     +-- LinkedHashSet (insertion order)
          |     +-- SortedSet
          |           +-- NavigableSet
          |                 +-- TreeSet (sorted order)
          |
          +-- Queue (FIFO)
                +-- PriorityQueue (priority order)
                +-- Deque (double-ended)
                      +-- ArrayDeque (recommended)
                      +-- LinkedList

Map (separate hierarchy)
    +-- HashMap (no order, fastest)
    +-- LinkedHashMap (insertion order)
    +-- Hashtable (synchronized, legacy)
    +-- SortedMap
          +-- NavigableMap
                +-- TreeMap (sorted by keys)
```

## Queue vs Deque

```
QUEUE (Single-ended)
===================
[Add] --> [A][B][C] --> [Remove]
(Tail)                  (Head)
- Add at tail only
- Remove from head only
- FIFO: First In, First Out

DEQUE (Double-ended)
====================
[Add/Remove] <--> [A][B][C] <--> [Add/Remove]
(First/Head)                     (Last/Tail)
- Add/remove from both ends
- Can be used as Queue OR Stack
- More flexible than Queue
```

## When to Use Which Collection

| Need | Use | Why |
|------|-----|-----|
| Ordered list with index access | ArrayList | O(1) random access |
| Frequent add/remove at ends | LinkedList | O(1) insertion |
| Unique elements, no order | HashSet | O(1) operations |
| Unique elements, sorted | TreeSet | Sorted automatically |
| Key-value pairs, no order | HashMap | O(1) operations |
| Key-value pairs, sorted | TreeMap | Sorted by keys |
| FIFO processing | Queue/ArrayDeque | Standard queue |
| LIFO processing | Deque (as stack) | Use push/pop |
| Priority processing | PriorityQueue | Automatic ordering |

## Best Practices

1. **Use interface types** for declarations:
   ```java
   List<String> list = new ArrayList<>();  // Good
   ArrayList<String> list = new ArrayList<>();  // Avoid
   ```

2. **Use generics** for type safety:
   ```java
   List<String> names = new ArrayList<>();  // Type-safe
   List names = new ArrayList();  // Raw type - avoid
   ```

3. **Use immutable collections** when possible (Java 9+):
   ```java
   List<String> colors = List.of("Red", "Green", "Blue");
   ```

4. **Use Deque instead of Stack**:
   ```java
   Deque<String> stack = new ArrayDeque<>();  // Recommended
   Stack<String> stack = new Stack<>();  // Legacy, avoid
   ```

5. **Use iterator.remove()** for safe removal:
   ```java
   Iterator<String> iter = list.iterator();
   while (iter.hasNext()) {
       if (condition) iter.remove();  // Safe
   }
   ```

## Resources

- [Oracle Collections Tutorial](https://docs.oracle.com/javase/tutorial/collections/)
- [Java Collections Framework - Baeldung](https://www.baeldung.com/java-collections)
- [Collections in Java - GeeksforGeeks](https://www.geeksforgeeks.org/collections-in-java-2/)
- [Java Arrays - Jenkov](https://jenkov.com/tutorials/java/arrays.html)
- [Java List - Jenkov](https://jenkov.com/tutorials/java-collections/list.html)
- [Immutable Collections - Baeldung](https://www.baeldung.com/java-immutable-list)
- [Java  Set - Jenkov](https://jenkov.com/tutorials/java-collections/set.html)
- [Java Set - DigitalOcean](https://www.digitalocean.com/community/tutorials/java-set)
- [Java Queue - Jenkov](https://jenkov.com/tutorials/java-collections/queue.html)
- [Java Deque -Jenkov](https://jenkov.com/tutorials/java-collections/deque.html)
- [Java Deque Interface - Programiz](https://www.programiz.com/java-programming/deque)
- [Java Map - Jenkov](https://jenkov.com/tutorials/java-collections/map.html)
- [Generic Map in Java - Jenkov](https://jenkov.com/tutorials/java-generics/generic-map.html)
- [Java ConcurrentMap - Jenkov](https://jenkov.com/tutorials/java-util-concurrent/concurrentmap.html)
- [Java SortedMap - Jenkov](https://jenkov.com/tutorials/java-collections/sortedmap.html)
- [Java Stack - Jenkov](https://jenkov.com/tutorials/java-collections/stack.html)
- [Quick Quide to the Java Stack - Baeldung](https://www.baeldung.com/java-stack)
- [Java Iterator - Jenkov](https://jenkov.com/tutorials/java-collections/iterator.html)
- [Java Iterable - Jenkov](https://jenkov.com/tutorials/java-collections/iterable.html)
- [The Basics of Java Generics - Baeldung](https://www.baeldung.com/java-generics)
- [Java Stream API - Baeldung](https://www.baeldung.com/java-8-streams)
package com.fundamentals.generics;

/**
 * GENERIC SINIFLAR
 * =============================================================
 * 
 * Generic siniflar, bir veya daha fazla tip parametresi alan siniflardir.
 * Bu sayede ayni sinif farkli veri tipleriyle calismak uzere yeniden
 * kullanilabilir hale gelir.
 * 
 * GENERIC SINIF TANIMLAMA KURALLARI:
 * ----------------------------------
 * 1. Sinif adinin hemen ardindan <T> seklinde tip parametresi eklenir
 * 2. Tip parametresi, sinif icerisinde normal bir tip gibi kullanilir
 * 3. Primitive tipler dogrudan kullanilamaz (int yerine Integer)
 * 4. Birden fazla tip parametresi virgul ile ayrilir <T, U, V>
 */
public class GenericClasses {

    public static void demonstrate() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("GENERIC SINIFLAR");
        System.out.println("=".repeat(60));

        demonstrateSimpleGenericClass();
        demonstrateGenericBox();
        demonstrateGenericStack();
        demonstrateGenericRepository();
    }

    /**
     * BASIT GENERIC SINIF
     * --------------------
     * En temel generic sinif ornegi.
     */
    private static void demonstrateSimpleGenericClass() {
        System.out.println("\n1. BASIT GENERIC SINIF - Container<T>");
        System.out.println("-".repeat(40));

        // String container
        Container<String> stringContainer = new Container<>("Merhaba Dunya");
        System.out.println("String Container: " + stringContainer.getValue());
        System.out.println("Tip: " + stringContainer.getTypeName());
        
        // Integer container
        Container<Integer> intContainer = new Container<>(42);
        System.out.println("\nInteger Container: " + intContainer.getValue());
        System.out.println("Tip: " + intContainer.getTypeName());

        // Double container
        Container<Double> doubleContainer = new Container<>(3.14159);
        System.out.println("\nDouble Container: " + doubleContainer.getValue());
        System.out.println("Tip: " + doubleContainer.getTypeName());

        // Custom type container
        Container<Person> personContainer = new Container<>(
            new Person("Ali", 30)
        );
        System.out.println("\nPerson Container: " + personContainer.getValue());
        System.out.println("Tip: " + personContainer.getTypeName());
    }

    /**
     * GENERIC BOX SINIFI
     * -------------------
     * Daha gelismis bir generic sinif ornegi.
     */
    private static void demonstrateGenericBox() {
        System.out.println("\n2. GENERIC BOX SINIFI");
        System.out.println("-".repeat(40));

        Box<String> stringBox = new Box<>();
        System.out.println("Bos kutu mu: " + stringBox.isEmpty());
        
        stringBox.put("Onemli Belge");
        System.out.println("Kutuya eklendi: " + stringBox.peek());
        System.out.println("Bos kutu mu: " + stringBox.isEmpty());
        
        String content = stringBox.take();
        System.out.println("Kutudan alindi: " + content);
        System.out.println("Bos kutu mu: " + stringBox.isEmpty());
    }

    /**
     * GENERIC STACK SINIFI
     * ---------------------
     * LIFO (Last In First Out) veri yapisi.
     */
    private static void demonstrateGenericStack() {
        System.out.println("\n3. GENERIC STACK SINIFI");
        System.out.println("-".repeat(40));

        GenericStack<Integer> stack = new GenericStack<>(5);
        
        System.out.println("Stack boyutu: " + stack.size());
        System.out.println("Stack bos mu: " + stack.isEmpty());
        
        // Push islemleri
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        
        System.out.println("\nPush sonrasi stack boyutu: " + stack.size());
        System.out.println("Stack: " + stack);
        System.out.println("En ustteki: " + stack.peek());
        
        // Pop islemleri
        System.out.println("\nPop edilen: " + stack.pop());
        System.out.println("Pop edilen: " + stack.pop());
        System.out.println("Stack: " + stack);
    }

    /**
     * GENERIC REPOSITORY PATTERN
     * ---------------------------
     * Veritabani islemleri icin generic repository ornegi.
     */
    private static void demonstrateGenericRepository() {
        System.out.println("\n4. GENERIC REPOSITORY PATTERN");
        System.out.println("-".repeat(40));

        InMemoryRepository<Long, Product> productRepo = new InMemoryRepository<>();
        
        // Urun ekleme
        Product p1 = new Product(1L, "Laptop", 15000.0);
        Product p2 = new Product(2L, "Mouse", 250.0);
        Product p3 = new Product(3L, "Klavye", 500.0);
        
        productRepo.save(p1.getId(), p1);
        productRepo.save(p2.getId(), p2);
        productRepo.save(p3.getId(), p3);
        
        System.out.println("Toplam urun sayisi: " + productRepo.count());
        
        // ID ile getirme
        Product found = productRepo.findById(2L);
        System.out.println("Bulunan urun: " + found);
        
        // Tum urunleri listeleme
        System.out.println("\nTum urunler:");
        for (Product p : productRepo.findAll()) {
            System.out.println("  - " + p);
        }
        
        // Urun silme
        productRepo.delete(1L);
        System.out.println("\nSilme sonrasi urun sayisi: " + productRepo.count());
    }

    // =========================================================
    // GENERIC SINIF TANIMLARI
    // =========================================================

    /**
     * Basit generic container sinifi
     * @param <T> Container'in tutacagi deger tipi
     */
    static class Container<T> {
        private T value;

        public Container(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public String getTypeName() {
            return value.getClass().getSimpleName();
        }
    }

    /**
     * Generic Box sinifi - tek bir deger tutabilir
     * @param <T> Box'in tutacagi deger tipi
     */
    static class Box<T> {
        private T content;

        public void put(T item) {
            this.content = item;
        }

        public T take() {
            T item = content;
            content = null;
            return item;
        }

        public T peek() {
            return content;
        }

        public boolean isEmpty() {
            return content == null;
        }
    }

    /**
     * Generic Stack sinifi - LIFO veri yapisi
     * @param <E> Stack'in tutacagi eleman tipi
     */
    static class GenericStack<E> {
        private Object[] elements;
        private int size;

        public GenericStack(int capacity) {
            elements = new Object[capacity];
            size = 0;
        }

        public void push(E element) {
            if (size == elements.length) {
                throw new IllegalStateException("Stack is full");
            }
            elements[size++] = element;
        }

        @SuppressWarnings("unchecked")
        public E pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            E element = (E) elements[--size];
            elements[size] = null;
            return element;
        }

        @SuppressWarnings("unchecked")
        public E peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            return (E) elements[size - 1];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                sb.append(elements[i]);
                if (i < size - 1) sb.append(", ");
            }
            sb.append("]");
            return sb.toString();
        }
    }

    /**
     * Generic Repository Interface
     * @param <K> Anahtar (Key) tipi
     * @param <V> Deger (Value) tipi
     */
    interface Repository<K, V> {
        void save(K key, V value);
        V findById(K key);
        Iterable<V> findAll();
        void delete(K key);
        int count();
    }

    /**
     * In-Memory Repository implementasyonu
     */
    static class InMemoryRepository<K, V> implements Repository<K, V> {
        private java.util.Map<K, V> storage = new java.util.HashMap<>();

        @Override
        public void save(K key, V value) {
            storage.put(key, value);
        }

        @Override
        public V findById(K key) {
            return storage.get(key);
        }

        @Override
        public Iterable<V> findAll() {
            return storage.values();
        }

        @Override
        public void delete(K key) {
            storage.remove(key);
        }

        @Override
        public int count() {
            return storage.size();
        }
    }

    // =========================================================
    // YARDIMCI SINIFLAR
    // =========================================================

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + " (" + age + " yas)";
        }
    }

    static class Product {
        private Long id;
        private String name;
        private Double price;

        public Product(Long id, String name, Double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public Long getId() { return id; }
        public String getName() { return name; }
        public Double getPrice() { return price; }

        @Override
        public String toString() {
            return name + " - " + price + " TL";
        }
    }
}

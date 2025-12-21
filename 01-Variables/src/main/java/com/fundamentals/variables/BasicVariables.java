package com.fundamentals.variables;

/**
 * Basic Variables
 * 
 * Demonstrates:
 * - Primitive data types
 * - Wrapper classes
 * - Variable declaration and initialization
 */
public class BasicVariables {

    public static void main(String[] args) {
        // Primitive Types
        System.out.println("--- Primitive Types ---");
        byte myByte = 127;
        short myShort = 32000;
        char myChar = 'A';
        int myInt = 100000;
        long myLong = 10000000000L;
        float myFloat = 3.14f;
        double myDouble = 3.14159265359;

        System.out.println("byte:   " + myByte);
        System.out.println("short:  " + myShort);
        System.out.println("char:   " + myChar);
        System.out.println("int:    " + myInt);
        System.out.println("long:   " + myLong);
        System.out.println("float:  " + myFloat);
        System.out.println("double: " + myDouble);

        // Wrapper Classes
        System.out.println("\n--- Wrapper Classes ---");
        Byte wrapperByte = 127;
        Short wrapperShort = 32000;
        Character wrapperChar = 'B';
        Integer wrapperInt = 100000;
        Long wrapperLong = 10000000000L;
        Float wrapperFloat = 3.14f;
        Double wrapperDouble = 3.14159265359;
        String myString = "Hello, Java!";

        System.out.println("Byte:      " + wrapperByte);
        System.out.println("Short:     " + wrapperShort);
        System.out.println("Character: " + wrapperChar);
        System.out.println("Integer:   " + wrapperInt);
        System.out.println("Long:      " + wrapperLong);
        System.out.println("Float:     " + wrapperFloat);
        System.out.println("Double:    " + wrapperDouble);
        System.out.println("String:    " + myString);
    }
}

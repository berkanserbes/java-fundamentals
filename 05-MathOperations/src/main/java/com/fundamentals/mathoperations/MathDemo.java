package com.fundamentals.mathoperations;

public class MathDemo {
    public static void main(String[] args) {
        System.out.println("=== Math Operations Demo ===\n");

        // ========== BASIC OPERATIONS ==========
        System.out.println("========== BASIC OPERATIONS ==========");
        System.out.println("Math.abs(-10): " + Math.abs(-10));
        System.out.println("Math.abs(-15.5): " + Math.abs(-15.5));
        System.out.println("Math.max(5, 10): " + Math.max(5, 10));
        System.out.println("Math.min(5, 10): " + Math.min(5, 10));
        System.out.println("Math.pow(2, 3): " + Math.pow(2, 3));
        System.out.println("Math.sqrt(16): " + Math.sqrt(16));
        System.out.println("Math.cbrt(27): " + Math.cbrt(27)); // Cube root

        // ========== ROUNDING OPERATIONS ==========
        System.out.println("\n========== ROUNDING OPERATIONS ==========");
        System.out.println("Math.ceil(4.3): " + Math.ceil(4.3));
        System.out.println("Math.floor(4.7): " + Math.floor(4.7));
        System.out.println("Math.round(4.5): " + Math.round(4.5));
        System.out.println("Math.round(4.4): " + Math.round(4.4));
        System.out.println("Math.rint(4.5): " + Math.rint(4.5)); // Round to nearest even

        // ========== DIVISION OPERATIONS ==========
        System.out.println("\n========== DIVISION OPERATIONS ==========");
        System.out.println("Math.floorDiv(10, 3): " + Math.floorDiv(10, 3));
        System.out.println("Math.floorDiv(-10, 3): " + Math.floorDiv(-10, 3));
        System.out.println("Math.floorDiv(10, -3): " + Math.floorDiv(10, -3));
        System.out.println("Math.floorMod(10, 3): " + Math.floorMod(10, 3));
        System.out.println("Math.floorMod(-10, 3): " + Math.floorMod(-10, 3));
        System.out.println("Math.floorMod(10, -3): " + Math.floorMod(10, -3));

        // Comparison with regular division
        System.out.println("\nRegular division: 10 / 3 = " + (10 / 3));
        System.out.println("Floor division: Math.floorDiv(10, 3) = " + Math.floorDiv(10, 3));
        System.out.println("Regular modulo: -10 % 3 = " + (-10 % 3));
        System.out.println("Floor modulo: Math.floorMod(-10, 3) = " + Math.floorMod(-10, 3));

        // ========== EXPONENTIAL & LOGARITHMIC ==========
        System.out.println("\n========== EXPONENTIAL & LOGARITHMIC ==========");
        System.out.println("Math.exp(1): " + Math.exp(1)); // e^1
        System.out.println("Math.exp(2): " + Math.exp(2)); // e^2
        System.out.println("Math.expm1(1): " + Math.expm1(1)); // e^1 - 1 (more accurate for small values)
        System.out.println("Math.log(Math.E): " + Math.log(Math.E)); // Natural logarithm (ln)
        System.out.println("Math.log(10): " + Math.log(10));
        System.out.println("Math.log10(100): " + Math.log10(100)); // Base 10 logarithm
        System.out.println("Math.log10(1000): " + Math.log10(1000));
        System.out.println("Math.log1p(0): " + Math.log1p(0)); // ln(1 + x), more accurate for small x

        // ========== TRIGONOMETRIC FUNCTIONS ==========
        System.out.println("\n========== TRIGONOMETRIC FUNCTIONS ==========");
        double angle = Math.PI / 4; // 45 degrees in radians
        System.out.println("Angle: π/4 (45 degrees)");
        System.out.println("Math.sin(π/4): " + Math.sin(angle));
        System.out.println("Math.cos(π/4): " + Math.cos(angle));
        System.out.println("Math.tan(π/4): " + Math.tan(angle));

        System.out.println("\nAngle: π/2 (90 degrees)");
        System.out.println("Math.sin(π/2): " + Math.sin(Math.PI / 2));
        System.out.println("Math.cos(π/2): " + Math.cos(Math.PI / 2));

        // ========== INVERSE TRIGONOMETRIC ==========
        System.out.println("\n========== INVERSE TRIGONOMETRIC ==========");
        System.out.println("Math.asin(0.5): " + Math.asin(0.5) + " radians");
        System.out.println("Math.acos(0.5): " + Math.acos(0.5) + " radians");
        System.out.println("Math.atan(1): " + Math.atan(1) + " radians");
        System.out.println("Math.atan2(1, 1): " + Math.atan2(1, 1) + " radians"); // atan(y/x) with quadrant info

        // ========== HYPERBOLIC FUNCTIONS ==========
        System.out.println("\n========== HYPERBOLIC FUNCTIONS ==========");
        System.out.println("Math.sinh(1): " + Math.sinh(1));
        System.out.println("Math.cosh(1): " + Math.cosh(1));
        System.out.println("Math.tanh(1): " + Math.tanh(1));

        // ========== ANGLE CONVERSION ==========
        System.out.println("\n========== ANGLE CONVERSION ==========");
        double degrees = 180;
        double radians = Math.PI;
        System.out.println("Math.toRadians(180): " + Math.toRadians(degrees));
        System.out.println("Math.toDegrees(π): " + Math.toDegrees(radians));

        // ========== SIGN & COMPARISON ==========
        System.out.println("\n========== SIGN & COMPARISON ==========");
        System.out.println("Math.signum(10): " + Math.signum(10)); // Returns 1.0
        System.out.println("Math.signum(-10): " + Math.signum(-10)); // Returns -1.0
        System.out.println("Math.signum(0): " + Math.signum(0)); // Returns 0.0
        System.out.println("Math.copySign(5.0, -1.0): " + Math.copySign(5.0, -1.0)); // Copy sign from second arg

        // ========== SPECIAL OPERATIONS ==========
        System.out.println("\n========== SPECIAL OPERATIONS ==========");
        System.out.println("Math.hypot(3, 4): " + Math.hypot(3, 4)); // sqrt(3² + 4²) = 5
        System.out.println("Math.hypot(5, 12): " + Math.hypot(5, 12)); // Pythagorean theorem
        System.out.println("Math.IEEEremainder(10, 3): " + Math.IEEEremainder(10, 3));
        System.out.println("Math.nextAfter(1.0, 2.0): " + Math.nextAfter(1.0, 2.0)); // Next floating point
        System.out.println("Math.nextUp(1.0): " + Math.nextUp(1.0));
        System.out.println("Math.nextDown(1.0): " + Math.nextDown(1.0));
        System.out.println("Math.ulp(1.0): " + Math.ulp(1.0)); // Unit in the last place

        // ========== EXACT ARITHMETIC (Java 8+) ==========
        System.out.println("\n========== EXACT ARITHMETIC ==========");
        try {
            System.out.println("Math.addExact(100, 200): " + Math.addExact(100, 200));
            System.out.println("Math.subtractExact(100, 50): " + Math.subtractExact(100, 50));
            System.out.println("Math.multiplyExact(10, 20): " + Math.multiplyExact(10, 20));
            System.out.println("Math.incrementExact(99): " + Math.incrementExact(99));
            System.out.println("Math.decrementExact(100): " + Math.decrementExact(100));
            System.out.println("Math.negateExact(50): " + Math.negateExact(50));
            // These throw ArithmeticException on overflow
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic overflow detected!");
        }

        // ========== CONSTANTS ==========
        System.out.println("\n========== CONSTANTS ==========");
        System.out.println("Math.PI: " + Math.PI);
        System.out.println("Math.E: " + Math.E);

        // ========== RANDOM NUMBERS ==========
        System.out.println("\n========== RANDOM NUMBERS ==========");
        System.out.println("Random number (0-1): " + Math.random());
        System.out.println("Random number (1-100): " + (int) (Math.random() * 100 + 1));
        System.out.println("Random number (50-150): " + (int) (Math.random() * 101 + 50));

        // ========== PRACTICAL EXAMPLES ==========
        System.out.println("\n========== PRACTICAL EXAMPLES ==========");

        // Calculate distance between two points
        double x1 = 0, y1 = 0, x2 = 3, y2 = 4;
        double distance = Math.hypot(x2 - x1, y2 - y1);
        System.out.println("Distance between (0,0) and (3,4): " + distance);

        // Calculate area of circle
        double radius = 5;
        double area = Math.PI * Math.pow(radius, 2);
        System.out.println("Area of circle with radius 5: " + area);

        // Calculate compound interest
        double principal = 1000;
        double rate = 0.05; // 5%
        int years = 10;
        double amount = principal * Math.pow(1 + rate, years);
        System.out.println("Compound interest after 10 years: " + amount);

        // Convert Celsius to Fahrenheit
        double celsius = 25;
        double fahrenheit = celsius * 9.0 / 5.0 + 32;
        System.out.println("25°C in Fahrenheit: " + fahrenheit + "°F");
    }
}

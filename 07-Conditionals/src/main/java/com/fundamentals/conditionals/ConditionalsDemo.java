package com.fundamentals.conditionals;

public class ConditionalsDemo {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║     JAVA CONDITIONALS - COMPLETE DEMO     ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println();

        // Run all conditional demos
        runDemo("IF-ELSE DEMO", () -> IfElseDemo.main(args));
        runDemo("ELSE-IF DEMO", () -> ElseIfDemo.main(args));
        runDemo("SWITCH DEMO", () -> SwitchDemo.main(args));
        runDemo("TERNARY OPERATOR DEMO", () -> TernaryOperatorDemo.main(args));

        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║      ALL CONDITIONAL DEMOS COMPLETED!      ║");
        System.out.println("╚════════════════════════════════════════════╝");
    }

    private static void runDemo(String demoName, Runnable demo) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("  " + demoName);
        System.out.println("=".repeat(50) + "\n");
        demo.run();
        System.out.println("\n" + "-".repeat(50));
    }
}

package com.fundamentals.loops;

public class LoopsDemo {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║        JAVA LOOPS - COMPLETE DEMO         ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println();

        // Run all loop demos
        runDemo("FOR LOOP DEMO", () -> ForLoopDemo.main(args));
        runDemo("WHILE LOOP DEMO", () -> WhileLoopDemo.main(args));
        runDemo("DO-WHILE LOOP DEMO", () -> DoWhileLoopDemo.main(args));
        runDemo("FOR-EACH LOOP DEMO", () -> ForEachDemo.main(args));
        runDemo("LOOP CONTROL DEMO", () -> LoopControlDemo.main(args));

        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║         ALL LOOP DEMOS COMPLETED!         ║");
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

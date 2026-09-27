public class SimpleProductTester {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {

        System.out.println("=== Product Class Tests ===");

        //test 1
        Product p1 = new Product("P1", "Laptop", 10, 799.99);

        check("Product ID", "P1".equals(p1.getProductId()));
        check("Product Name", "Laptop".equals(p1.getProductName()));
        check("Quantity", p1.getQuantity() == 10);
        check("Unit Price", p1.getUnitPrice() == 799.99);

        //test 2
        check("Product is in stock", p1.isInStock());

        Product p2 = new Product("P2", "Keyboard", 0, 49.99);

        check(
            "Product with zero quantity is not in stock",
            !p2.isInStock()
        );

        //test 3
        String text = p1.toString();

        check(
            "toString contains product ID",
            text.contains("Product ID: P1")
        );

        check(
            "toString contains product name",
            text.contains("Product Name: Laptop")
        );

        check(
            "toString contains quantity",
            text.contains("Quantity: 10")
        );

        check(
            "toString contains price",
            text.contains("Unit Price: $799.99")
        );

        //test 4
        expectException(
            "Empty product ID rejected",
            () -> new Product("", "Mouse", 5, 20.00)
        );

        expectException(
            "Empty product name rejected",
            () -> new Product("P3", "", 5, 20.00)
        );

        expectException(
            "Negative quantity rejected",
            () -> new Product("P4", "Monitor", -1, 150.00)
        );

        expectException(
            "Negative price rejected",
            () -> new Product("P5", "Printer", 5, -10.00)
        );

        //results
        System.out.println();
        System.out.println("=== Final Results ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);

        if (failed == 0) {
            System.out.println("All Product tests passed.");
        } else {
            System.out.println("Some Product tests failed.");
        }
    }

    private static void check(
        String testName,
        boolean condition
    ) {
        if (condition) {
            System.out.println("[PASS] " + testName);
            passed++;
        } else {
            System.out.println("[FAIL] " + testName);
            failed++;
        }
    }

    private static void expectException(
        String testName,
        Runnable action
    ) {
        try {
            action.run();

            System.out.println("[FAIL] " + testName);
            failed++;

        } catch (IllegalArgumentException e) {

            System.out.println("[PASS] " + testName);
            passed++;
        }
    }
}

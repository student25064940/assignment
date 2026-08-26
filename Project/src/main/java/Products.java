import com.mycompany.project.ReportData;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/*package com.mycompany.project;*/

/**
 *
 * @author MOKGADI
 */

public class Products {

    private ArrayList<ReportData> products;
    private Scanner input;

    public Products() {
        products = new ArrayList<>();
        input = new Scanner(System.in);
    }

    // ==============================
    // DISPLAY MENU
    // ==============================

    public void DisplayMenu() {

        int choice = 0;

        do {

            System.out.println();
            System.out.println("========================================");
            System.out.println("       EXTREME IT PRODUCTS");
            System.out.println("========================================");
            System.out.println("1. Capture a new product");
            System.out.println("2. Search for a product");
            System.out.println("3. Delete a product");
            System.out.println("4. Update a product");
            System.out.println("5. View product report");
            System.out.println("6. Exit Application");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");

            try {

                choice = input.nextInt();
                input.nextLine();

                switch (choice) {

                    case 1:
                        CaptureProduct();
                        break;

                    case 2:
                        SearchProduct();
                        break;

                    case 3:
                        DeleteProduct();
                        break;

                    case 4:
                        UpdateProduct();
                        break;

                    case 5:
                        PrintProductReport();
                        break;

                    case 6:
                        ExitApplication();
                        break;

                    default:
                        System.out.println(
                                "Invalid option. Please select a number from 1 to 6."
                        );
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid input! Please enter a number."
                );

                input.nextLine();
            }

        } while (choice != 6);
    }

    // ==============================
    // CAPTURE PRODUCT
    // ==============================

    public void CaptureProduct() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("          CAPTURE NEW PRODUCT");
        System.out.println("========================================");

        System.out.print("Enter product code: ");
        String productCode = input.nextLine();

        // Check if product code already exists
        while (findProduct(productCode) != null) {

            System.out.println(
                    "A product with this code already exists."
            );

            System.out.print("Enter another product code: ");
            productCode = input.nextLine();
        }

        System.out.print("Enter product name: ");
        String productName = input.nextLine();

        // ==============================
        // CATEGORY
        // ==============================

        String category = selectCategory();

        // ==============================
        // WARRANTY
        // ==============================

        String warranty = selectWarranty();

        // ==============================
        // PRICE
        // ==============================

        double price = 0;

        while (true) {

            try {

                System.out.print("Enter product price: R");
                price = input.nextDouble();

                if (price < 0) {

                    System.out.println(
                            "Price cannot be negative."
                    );

                } else {
                    break;
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid price. Please enter a number."
                );

                input.nextLine();
            }
        }

        input.nextLine();

        // ==============================
        // STOCK LEVEL
        // ==============================

        int stockLevel = 0;

        while (true) {

            try {

                System.out.print("Enter stock level: ");
                stockLevel = input.nextInt();

                if (stockLevel < 0) {

                    System.out.println(
                            "Stock level cannot be negative."
                    );

                } else {
                    break;
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid stock level. Please enter a whole number."
                );

                input.nextLine();
            }
        }

        input.nextLine();

        // ==============================
        // CREATE PRODUCT
        // ==============================

        ReportData product = new ReportData(
                productCode,
                productName,
                category,
                warranty,
                price,
                stockLevel
        );

        products.add(product);

        System.out.println();
        System.out.println(
                "Product details have been successfully saved."
        );
    }

    // ==============================
    // CATEGORY SELECTION
    // ==============================

    private String selectCategory() {

        int categoryChoice;

        while (true) {

            System.out.println();
            System.out.println("Select product category:");
            System.out.println("1. Desktop Computer");
            System.out.println("2. Laptop");
            System.out.println("3. Tablet");
            System.out.println("4. Printer");
            System.out.println("5. Gaming Console");
            System.out.print("Enter category choice: ");

            try {

                categoryChoice = input.nextInt();
                input.nextLine();

                switch (categoryChoice) {

                    case 1 -> {
                        return "Desktop Computer";
                    }

                    case 2 -> {
                        return "Laptop";
                    }

                    case 3 -> {
                        return "Tablet";
                    }

                    case 4 -> {
                        return "Printer";
                    }

                    case 5 -> {
                        return "Gaming Console";
                    }

                    default -> System.out.println(
                                "Invalid category. Please select 1 to 5."
                        );
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid input. Please enter a number from 1 to 5."
                );

                input.nextLine();
            }
        }
    }

    // ==============================
    // WARRANTY SELECTION
    // ==============================

    private String selectWarranty() {

        System.out.println();
        System.out.println("Select product warranty:");
        System.out.println("1. Six months");
        System.out.println("Any other key. Two years");
        System.out.print("Enter warranty choice: ");

        String warrantyChoice = input.nextLine();

        if (warrantyChoice.equals("1")) {

            return "6 months";

        } else {

            return "2 years";
        }
    }

    // ==============================
    // SEARCH PRODUCT
    // ==============================

    public void SearchProduct() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("           SEARCH PRODUCT");
        System.out.println("========================================");

        System.out.print("Enter product code: ");
        String productCode = input.nextLine();

        ReportData product = findProduct(productCode);

        if (product != null) {

            System.out.println();
            System.out.println("PRODUCT FOUND");
            System.out.println("----------------------------------------");

            displayProduct(product);

        } else {

            System.out.println();
            System.out.println(
                    "Error: Product cannot be located."
            );
        }
    }

    // ==============================
    // FIND PRODUCT
    // ==============================

    private ReportData findProduct(String productCode) {

        for (ReportData product : products) {

            if (product.getProductCode()
                    .equalsIgnoreCase(productCode)) {

                return product;
            }
        }

        return null;
    }

    // ==============================
    // DELETE PRODUCT
    // ==============================

    public void DeleteProduct() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("           DELETE PRODUCT");
        System.out.println("========================================");

        System.out.print("Enter product code to delete: ");
        String productCode = input.nextLine();

        ReportData product = findProduct(productCode);

        if (product == null) {

            System.out.println(
                    "Error: Product cannot be located."
            );

            return;
        }

        System.out.println();
        System.out.println("Product found.");
        displayProduct(product);

        System.out.println();
        System.out.print(
                "Are you sure you want to delete this product? (Y/N): "
        );

        String confirmation = input.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {

            products.remove(product);

            System.out.println(
                    "Product has been successfully deleted."
            );

        } else {

            System.out.println(
                    "Delete operation cancelled."
            );
        }
    }

    // ==============================
    // UPDATE PRODUCT
    // ==============================

    public void UpdateProduct() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("           UPDATE PRODUCT");
        System.out.println("========================================");

        System.out.print("Enter product code: ");
        String productCode = input.nextLine();

        ReportData product = findProduct(productCode);

        if (product == null) {

            System.out.println(
                    "Error: Product cannot be located."
            );

            return;
        }

        System.out.println();
        System.out.println("Product found.");
        displayProduct(product);

        System.out.println();
        System.out.println("What would you like to update?");
        System.out.println("1. Product warranty");
        System.out.println("2. Product price");
        System.out.println("3. Stock level");
        System.out.print("Enter your choice: ");

        int choice;

        try {

            choice = input.nextInt();
            input.nextLine();

        } catch (InputMismatchException e) {

            System.out.println(
                    "Invalid input."
            );

            input.nextLine();
            return;
        }

        switch (choice) {

            case 1:

                String warranty = selectWarranty();

                product.setWarranty(warranty);

                System.out.println(
                        "Product warranty successfully updated."
                );

                break;

            case 2:

                updatePrice(product);

                break;

            case 3:

                updateStock(product);

                break;

            default:

                System.out.println(
                        "Invalid update option."
                );
        }
    }

    // ==============================
    // UPDATE PRICE
    // ==============================

    private void updatePrice(ReportData product) {

        double newPrice;

        while (true) {

            try {

                System.out.print("Enter new price: R");
                newPrice = input.nextDouble();

                if (newPrice < 0) {

                    System.out.println(
                            "Price cannot be negative."
                    );

                } else {

                    product.setPrice(newPrice);
                    input.nextLine();

                    System.out.println(
                            "Product price successfully updated."
                    );

                    break;
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid price."
                );

                input.nextLine();
            }
        }
    }

    // ==============================
    // UPDATE STOCK
    // ==============================

    private void updateStock(ReportData product) {

        int newStock;

        while (true) {

            try {

                System.out.print("Enter new stock level: ");
                newStock = input.nextInt();

                if (newStock < 0) {

                    System.out.println(
                            "Stock level cannot be negative."
                    );

                } else {

                    product.setStockLevel(newStock);
                    input.nextLine();

                    System.out.println(
                            "Stock level successfully updated."
                    );

                    break;
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid stock level."
                );

                input.nextLine();
            }
        }
    }

    // ==============================
    // PRODUCT REPORT
    // ==============================

    public void PrintProductReport() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("             PRODUCT REPORT");
        System.out.println("========================================");

        if (products.isEmpty()) {

            System.out.println(
                    "No product records available."
            );

            return;
        }

        int productNumber = 1;

        for (ReportData product : products) {

            System.out.println();
            System.out.println(
                    "------------- PRODUCT "
                    + productNumber
                    + " -------------"
            );

            displayProduct(product);

            productNumber++;
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println(
                "Total products: " + products.size()
        );
        System.out.println("========================================");
    }

    // ==============================
    // DISPLAY PRODUCT DETAILS
    // ==============================

    private void displayProduct(ReportData product) {

        System.out.println(
                "Product Code: " + product.getProductCode()
        );

        System.out.println(
                "Product Name: " + product.getProductName()
        );

        System.out.println(
                "Category: " + product.getCategory()
        );

        System.out.println(
                "Warranty: " + product.getWarranty()
        );

        System.out.printf(
                "Price: R%.2f%n",
                product.getPrice()
        );

        System.out.println(
                "Stock Level: " + product.getStockLevel()
        );
    }

    // ==============================
    // EXIT APPLICATION
    // ==============================

    public void ExitApplication() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("Thank you for using Extreme IT Products!");
        System.out.println("Goodbye!");
        System.out.println("========================================");
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
// Book order class that extends the AVLTree class 
public class BookOrder extends AVLTree {
    // Static instance of AVLTree to store book orders
    public static AVLTree tree = new AVLTree();
    // recursively counts the nodes in the AVL tree
    private static int countNodes(AVLTree.Node x) {
        if (x == null) {
            return 0;
        }
        return 1 + countNodes(x.left) + countNodes(x.right);
    }
    // Calculates the height of the AVL tree based on the number of nodes
    private static int calculateHeight(AVLTree.Node x) {
        return (int) Math.ceil(Math.log(countNodes(x) + 1) / Math.log(2));
    }
    // Main method that executes the program
    public static void main(String[] args) throws Exception {
        // Try-Catch block to handle file reading exceptions
        try {
            // Creates a BufferedReader to read from the CSV file containing orders
            BufferedReader reader = new BufferedReader(
                    new FileReader("C:\\Users\\aholl91\\eclipse-workspace\\ProgAssign2\\src\\orders.csv"));
            reader.readLine(); // Skip header row
            String line;
            // Reads each line from the CSV file
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(","); // Split line by comma
                int orderID = Integer.parseInt(values[0].trim()); // Extract orderID
                String title = values[1].trim(); // Extract book title
                // Insert orderID and title into the AVL tree
                tree.setRoot(tree.insert(tree.getRoot(), orderID, title));

            }
        } catch (IOException e) {
            System.out.println("Error finding file: " + e.getMessage());
        }
        // Scanner for user input in the command-line interface
        Scanner scanner = new Scanner(System.in);
        while (true) { // Infinite Loop for menu display
            try {
                // Display menu options
                System.out.println("\nChoose From Menu:");
                System.out.print(
                        "1. Manually add a book order\n2. Manually remove a book order\n3. Print an In-Order list of book names by order ID\n4. Find the name of the book for a specific order number\n5. Find the oldest book order\n6. Find the latest book order\n");
                // Validate if user input is an integer
                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid integer choice");
                    scanner.next();
                    continue;
                }

                int choice = scanner.nextInt();
                // Validate if user input is within the correct range
                if (choice < 1 || choice > 7) {
                    System.out.println("Please enter a valid integer choice");
                    continue;
                }
                // Switch-case to handle each menu option
                switch (choice) {
                    case 1:
                        System.out.println("You chose to input a new order.");
                        System.out.println("Enter order ID: ");
                        // Validate order ID input
                        if (!scanner.hasNextInt()) {
                            System.out.println("Please enter a valid integer choice");
                            scanner.next();
                        }
                        int newOrderID = scanner.nextInt();
                        scanner.nextLine(); // consume next line
                        System.out.println("Enter book title");
                        String newTitle = scanner.nextLine();
                        // Insert new order into AVL tree
                        tree.setRoot(tree.insert(tree.getRoot(), newOrderID, newTitle));
                        break;

                    case 2:
                        System.out.println("You chose to remove an order.");
                        System.out.println("Enter order ID to remove: ");
                        // Validate order ID input
                        if (!scanner.hasNextInt()) {
                            System.out.println("Please enter a valid integer choice");
                            scanner.next();
                            continue;
                        }
                        int removeOrderID = scanner.nextInt();
                        // Remove order from AVL tree
                        tree.setRoot(tree.deleteNode(tree.getRoot(), removeOrderID));
                        break;

                    case 3:
                        // Print AVL tree in-order traversal (sorted by order ID)
                        tree.inOrder(tree.getRoot());
                        break;

                    case 4:
                        System.out.println("Enter order ID to search:");
                        // Validate order Id input
                        if (!scanner.hasNextInt()) {
                            System.out.println("Please enter a valid integer choice");
                            scanner.next();
                            continue;
                        }
                        int searchOrderID = scanner.nextInt();
                        // Search for order by ID and display book title
                        System.out.println("Book Name: " + tree.search(tree.getRoot(), searchOrderID));
                        break;

                    case 5:
                        // Find and display the oldest order in the AVL tree
                        System.out.println("Oldest Order: " + tree.findOldestOrder(tree.getRoot()).title);
                        break;

                    case 6:
                        // Find and display the latest order in the AVL tree
                        System.out.println("Latest Order: " + tree.findLatestOrder(tree.getRoot()).title);
                        break;

                    case 7:
                        System.exit(0); // Exit the program

                }
                // Display the number of nodes and height of the AVL tree
                System.out.println("Number of nodes: " + countNodes(tree.getRoot()));
                System.out.println("Max height: " + calculateHeight(tree.getRoot()));

            } catch (Exception e) {
                // Handle any other exceptions and prompt user to try again
                System.out.println("Error: " + e.getMessage());
                scanner.next();
            }

        }

    }

}

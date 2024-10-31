
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BookOrder extends AVLTree {

    public static AVLTree tree = new AVLTree();

    private static int countNodes(AVLTree.Node x) {
        if (x == null) {
            return 0;
        }
        return 1 + countNodes(x.left) + countNodes(x.right);
    }

    // Function to calculate the maximum height of the AVL Tree
    private static int calculateHeight(AVLTree.Node x) {
        return (int) Math.ceil(Math.log(countNodes(x) + 1) / Math.log(2));
    }

    public static void main(String[] args) throws Exception {

        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("C:\\Users\\aholl91\\eclipse-workspace\\ProgAssign2\\src\\orders.csv"));
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                int orderID = Integer.parseInt(values[0].trim());
                String title = values[1].trim();
                tree.setRoot(tree.insert(tree.getRoot(), orderID, title));

            }
        } catch (IOException e) {
            System.out.println("Error finding file: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("\nChoose From Menu:");
                System.out.print(
                        "1. Manually add a book order\n2. Manually remove a book order\n3. Print an In-Order list of book names by order ID\n4. Find the name of the book for a specific order number\n5. Find the oldest book order\n6. Find the latest book order\n");

                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid integer choice");
                    scanner.next();
                    continue;
                }

                int choice = scanner.nextInt();

                if (choice < 1 || choice > 7) {
                    System.out.println("Please enter a valid integer choice");
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.println("You chose to input a new order.");
                        System.out.println("Enter order ID: ");
                        if (!scanner.hasNextInt()) {
                            System.out.println("Please enter a valid integer choice");
                            scanner.next();
                        }
                        int newOrderID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter book title");
                        String newTitle = scanner.nextLine();
                        tree.setRoot(tree.insert(tree.getRoot(), newOrderID, newTitle));
                        break;

                    case 2:
                        System.out.println("You chose to remove an order.");
                        System.out.println("Enter order ID to remove: ");
                        if (!scanner.hasNextInt()) {
                            System.out.println("Please enter a valid integer choice");
                            scanner.next();
                            continue;
                        }
                        int removeOrderID = scanner.nextInt();
                        tree.setRoot(tree.deleteNode(tree.getRoot(), removeOrderID));
                        break;

                    case 3:
                        tree.inOrder(tree.getRoot());
                        break;

                    case 4:
                        System.out.println("Enter order ID to search:");
                        if (!scanner.hasNextInt()) {
                            System.out.println("Please enter a valid integer choice");
                            scanner.next();
                            continue;
                        }
                        int searchOrderID = scanner.nextInt();
                        System.out.println("Book Name: " + tree.search(tree.getRoot(), searchOrderID));
                        break;

                    case 5:
                        System.out.println("Oldest Order: " + tree.findOldestOrder(tree.getRoot()).title);
                        break;

                    case 6:
                        System.out.println("Latest Order: " + tree.findLatestOrder(tree.getRoot()).title);
                        break;

                    case 7:
                        System.exit(0);

                }

                System.out.println("Number of nodes: " + countNodes(tree.getRoot()));
                System.out.println("Max height: " + calculateHeight(tree.getRoot()));

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.next();
            }

        }

    }

}


public class AVLTree {
// Inner class representing a node in the AVL tree
    public class Node extends AVLTree {

        int orderID; // Unique id for each order
        String title; // Title of the book being ordered
        Node left, right; // Left and right child nodes
        int height; // Height of the node for balancing
        // Constructor to create a new node with given orderID and title
        Node(int orderID, String title) {
            this.orderID = orderID;
            this.title = title;
            height = 1; // New node is initially added at leaf
        }
    }

    private Node x; // Root node of the AVL tree
    // Function to get the height of a node
    private int height(Node x) {
        if (x == null) {
            return 0; // Height of a null node is 0
        }
        return x.height; // Return the height of the node
    }
    // Utility function to get the larger of two integers
    private int max(int a, int b) {
        return (a > b) ? a : b; // Return the greater of the two variables
    }
    // Right rotation for balancing the tree
    private Node rightRotate(Node y) {
        Node x = y.left; // Set x as the left child of y
        Node T2 = x.right; // Store the right subtree of x
        // Perform rotation
        x.right = y; // Make y the right child of x
        y.left = T2; // Assign the right subtree of x to be the left child of y
        // Update heights of x and y
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(y.left), height(y.right)) + 1;

        return x; // Return new root
    }
    // Left rotation for balancing the tree
    private Node leftRotate(Node x) {
        Node y = x.right; // Set y as the right child of x
        Node T2 = y.left; // Store the left subtree of y
        // Perform rotation
        y.left = x; // Make x the left child of y
        x.right = T2; // Assign the left subtree of y to be the right child of x
        // Update heights of x and y
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y; // return new root
    }
    // Function to get the balance factor of a node
    private int getBalance(Node n) {
        if (n == null) {
            return 0; // Balance factor of a null node is 0
        }
        return height(n.left) - height(n.right); // Balance factor = height(left) - height(right)
    }
    // Function to insert a new order into the AVL tree
    public Node insert(Node x, int orderID, String title) {
        if (x == null) {
            return (new Node(orderID, title)); // Create a new node if position is found
        }
        // Insert in the left subtree if orderID is smaller
        if (orderID < x.orderID) {
            x.left = insert(x.left, orderID, title);
        } else if (orderID > x.orderID) { // Insert in the right subtree if orderID is greater
            x.right = insert(x.right, orderID, title);
        } else { // Duplicate orderID not allowed, return the existing node
            return x;
        }
        // Update the height of the current node
        x.height = 1 + max(height(x.left), height(x.right));
        updateHeight(x); // Update the heights of the subtree
        return rebalance(x); // Rebalance the tree

    }
    // In order traversal of the AVL tree
    public void inOrder(Node x) {
        if (x != null) {
            inOrder(x.left); // Visit left subtree
            System.out.println("Order ID: " + x.orderID + ", Book: " + x.title); // Visit node
            inOrder(x.right); // Visit right subtree
        }
    }
    // Search for an order by orderID
    public String search(Node x, int orderID) {
        if (x == null) {
            return "Order not found"; // Return message if order not found
        }
        if (orderID < x.orderID) {
            return search(x.left, orderID); // Search in left subtree
        } else if (orderID > x.orderID) {
            return search(x.right, orderID); // Search in right subtree
        } else {
            return x.title; // Return title of the found order
        }
    }
    // Find the node with the minimum value (oldest order)
    private Node minValueNode(Node x) {
        Node current = x;
        while (current.left != null) {
            current = current.left; // Keep going left to find the minimum
        }
        return current; // return the node with the minimum value
    }
    // Delete a node from the AVL tree
    public Node deleteNode(Node x, int orderID) {
        if (x == null) {
            return x; // Return if tree is empty
        } // traverse the tree to find the node to delete
        if (orderID < x.orderID) {
            x.left = deleteNode(x.left, orderID); // go left
        } else if (orderID > x.orderID) {
            x.right = deleteNode(x.right, orderID); // go right
        } else { // node with only one child or no child
            if ((x.left == null) || (x.right == null)) {
                Node temp = (x.left != null) ? x.left : x.right; // get non-null child

                if (temp == null) {
                    temp = x; // store the current node
                    x = null; // delete current node
                } else {
                    x = temp; // replace current node with child
                }
            // node with two children
            } else {
                Node temp = minValueNode(x.right); // get the inorder successor ( smallest in the right subtree)
                // copy the in order successors data to this node
                x.orderID = temp.orderID;
                x.title = temp.title;
                // Delete the in order successor
                x.right = deleteNode(x.right, temp.orderID);
            }
        }
        // if the tree only has one node, return it
        if (x == null) {
            return x;
        } // Update the height of the current node
        x.height = max(height(x.left), height(x.right)) + 1;
        updateHeight(x); // update heights of the subtree
        return rebalance(x); // rebalance the tree
    }
    // Function to update the height of a node
    public int updateHeight(Node x) {
        if (x == null) {
            return -1; // height of a null node is -1
        } else { // recursively update height of the node
            x.height = 1 + Math.max(updateHeight(x.left), updateHeight(x.right));
        }
        return x.height; // return the height of the node
    }
    // rebalance the tree based on the balance factor
    public Node rebalance(Node x) {
        int balance = getBalance(x); // get balance factor
        // left left case
        if (balance > 1 && getBalance(x.left) >= 0) {
            return rightRotate(x); // right rotate
        }
        // left right case
        if (balance > 1 && getBalance(x.left) < 0) {
            x.left = leftRotate(x.left); // left rotate on left child
            return rightRotate(x); // right rotate
        }
        // right right case
        if (balance < -1 && getBalance(x.right) <= 0) {
            return leftRotate(x); // left rotate
        }
        // right left case
        if (balance < -1 && getBalance(x.right) > 0) {
            x.right = rightRotate(x.right); // right rotate on right child
            return leftRotate(x); // left rotate
        }

        return x; // return the (potentially new) root
    }
    // find the oldest order in the AVL tree
    public Node findOldestOrder(Node x) {
        return minValueNode(x); // minimum value node is the oldest
    }
    // find the latest order in the AVL tree
    public Node findLatestOrder(Node x) {
        Node current = x;
        while (current.right != null) {
            current = current.right; // keep going right to find the maximum value
        }
        return current; // return the node with the maximum value
    }
    // get the root of the AVL tree
    public Node getRoot() {
        return this.x; // Return the root node
    }
    // set the root of the AVL tree
    public void setRoot(Node x) {
        this.x = x; // set the root node
    }

}

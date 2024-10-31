
public class AVLTree {

    public class Node extends AVLTree {

        /*	int orderID;
		String title;
		Node left, right;
		int height;

		Node(int orderID, String title) {
			this.orderID = orderID;
			this.title = title;
			height = 1;
		}
	}

	private Node x;

	public int height(Node x) {
		if (x == null)
			return 0;
		return x.height;
	}

	private int max(int a, int b) {
		return (a > b) ? a : b;
	}

	public Node rightRotate(Node y) {
		Node x = y.left;
		Node temp = x.right;
		x.right = y;
		y.left = temp;

		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;

		return x;
	}

	public Node leftRotate(Node x) {
		Node y = x.right;
		Node temp = y.left;
		y.left = x;
		x.right = temp;

		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;

		return y;
	}

	public int getBalance(Node x) {
		if (x == null)
			return 0;
		return height(x.left) - height(x.right);
	}

	public Node rebalance(Node x) {
		int balanceFactor = getBalance(x);

		if (balanceFactor < -1) {
			if (getBalance(x.left) <= 0) {
				x = rightRotate(x);
			} else {
				x.left = leftRotate(x.left);
				x = rightRotate(x);
			}
		}
		if (balanceFactor > 1) {
			if (getBalance(x.right) >= 0) {
				x = leftRotate(x);
			} else {
				x.right = rightRotate(x.right);
				x = leftRotate(x);
			}
		}
		return x;
	}

	public int updateHeight(Node x) {
		if (x == null) {
			return -1;
		} else {
			x.height = 1 + Math.max(updateHeight(x.left), updateHeight(x.right));
		}
		return x.height;
	}

	public Node insert(Node x, int orderID, String title) {
		if (x == null) {
			return (new Node(orderID, title));
		}

		if (orderID < x.orderID) {
			x.left = insert(x.left, orderID, title);
		} else if (orderID > x.orderID) {
			x.right = insert(x.right, orderID, title);
		} else {
			return x;
		}
		updateHeight(x);
		return rebalance(x);
	}

	public Node deleteNode(Node x, int orderID) {
        if (x == null)
            return x; // If tree is empty

        // Perform standard BST delete
        if (orderID < x.orderID)
            x.left = deleteNode(x.left, orderID);
        else if (orderID > x.orderID)
            x.right = deleteNode(x.right, orderID);
        else {
            if ((x.left == null) || (x.right == null)) {
                Node temp = (x.left != null) ? x.left : x.right;

                if (temp == null) {
                    temp = x;
                    x = null;
                } else
                    x = temp;
            } else {
          
                Node temp = minValueNode(x.right);

                x.orderID = temp.orderID;
                x.title = temp.title;

                x.right = deleteNode(x.right, temp.orderID);
            }
        }

        if (x == null)
            return x;
		updateHeight(x);

		return rebalance(x);
	}

	public void inOrder(Node x) {
		if (x != null) {
			inOrder(x.left);
			System.out.println("Order ID: " + x.orderID + ", Book: " + x.title); // Print current x
			inOrder(x.right);
		}
	}

	public String search(Node x, int orderID) {
		if (x == null)
			return "That order could not be found.";

		if (orderID < x.orderID)
			return search(x.left, orderID);
		else if (orderID > x.orderID)
			return search(x.right, orderID);
		else
			return x.title;
	}

	public Node minValueNode(Node x) {
		Node current = x;
		while (current.left != null)
			current = current.left;
		return current;
	}

	public Node findOldestOrder(Node x) {
		return minValueNode(x);
	}

	public Node findLatestOrder(Node x) {
		Node current = x;
		while (current.right != null)
			current = current.right;
		return current;
	}

	public Node getRoot() {
		return this.x;
	}

	public void setRoot(Node x) {
		this.x = x;
	}
	
	public static int countNodes(Node x) {
		if(x == null)
			return 0;
		return 1 + countNodes(x.left)+ countNodes(x.right);
	}
	
	public static int calculateHeight(Node x) {
		return (int) Math.ceil(Math.log(countNodes(x) + 1) / Math.log(2));
	}
         */
        int orderID;
        String title;
        Node left, right;
        int height;

        Node(int orderID, String title) {
            this.orderID = orderID;
            this.title = title;
            height = 1;
        }
    }

    private Node x;

    private int height(Node x) {
        if (x == null) {
            return 0;
        }
        return x.height;
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(y.left), height(y.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private int getBalance(Node n) {
        if (n == null) {
            return 0;
        }
        return height(n.left) - height(n.right);
    }

    public Node insert(Node x, int orderID, String title) {
        if (x == null) {
            return (new Node(orderID, title));
        }

        if (orderID < x.orderID) {
            x.left = insert(x.left, orderID, title);
        } else if (orderID > x.orderID) {
            x.right = insert(x.right, orderID, title);
        } else {
            return x;
        }

        x.height = 1 + max(height(x.left), height(x.right));
        updateHeight(x);
        return rebalance(x);

    }

    public void inOrder(Node x) {
        if (x != null) {
            inOrder(x.left);
            System.out.println("Order ID: " + x.orderID + ", Book: " + x.title);
            inOrder(x.right);
        }
    }

    public String search(Node x, int orderID) {
        if (x == null) {
            return "Order not found";
        }
        if (orderID < x.orderID) {
            return search(x.left, orderID); 
        } else if (orderID > x.orderID) {
            return search(x.right, orderID);
        } else {
            return x.title;
        }
    }

    private Node minValueNode(Node x) {
        Node current = x;
        while (current.left != null) {
            current = current.left; 
        }
        return current;
    }

    public Node deleteNode(Node x, int orderID) {
        if (x == null) {
            return x;
        }
        if (orderID < x.orderID) {
            x.left = deleteNode(x.left, orderID);
        } else if (orderID > x.orderID) {
            x.right = deleteNode(x.right, orderID);
        } else {
            if ((x.left == null) || (x.right == null)) {
                Node temp = (x.left != null) ? x.left : x.right;

                if (temp == null) {
                    temp = x;
                    x = null;
                } else {
                    x = temp;
                }
            } else {
                Node temp = minValueNode(x.right);

                x.orderID = temp.orderID;
                x.title = temp.title;

                x.right = deleteNode(x.right, temp.orderID);
            }
        }

        if (x == null) {
            return x;
        }
        x.height = max(height(x.left), height(x.right)) + 1;
        updateHeight(x);

        return rebalance(x);
    }
    
    public int updateHeight(Node x) {
		if (x == null) {
			return -1;
		} else {
			x.height = 1 + Math.max(updateHeight(x.left), updateHeight(x.right));
		}
		return x.height;
	}

    public Node rebalance(Node x){
                int balance = getBalance(x);

        if (balance > 1 && getBalance(x.left) >= 0) {
            return rightRotate(x);
        }

        if (balance > 1 && getBalance(x.left) < 0) {
            x.left = leftRotate(x.left);
            return rightRotate(x);
        }

        if (balance < -1 && getBalance(x.right) <= 0) {
            return leftRotate(x);
        }

        if (balance < -1 && getBalance(x.right) > 0) {
            x.right = rightRotate(x.right);
            return leftRotate(x);
        }

        return x;
    }
    public Node findOldestOrder(Node x) {
        return minValueNode(x);
    }

    public Node findLatestOrder(Node x) {
        Node current = x;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    public Node getRoot() {
        return this.x;
    }

    public void setRoot(Node x) {
        this.x = x;
    }

}

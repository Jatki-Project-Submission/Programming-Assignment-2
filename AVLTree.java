
public class AVLTree {
    
    public class Node{
        int key;
        Node left;
        Node right;
        int height;
        
        Node(int key){
            this.key = key;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }
    //Node root;
        public int height(Node x){
            if(x == null)
                return -1;
            return x.height;
            }
            
        public Node rightRotate(Node x){
            Node y = x.left;
            Node temp = y.right;
            y.right = x;
            x.left = temp;
            
            return y;
        }
        
        public Node leftRotate(Node x){
            Node y = x.right;
            Node temp = y.left;
            y.left = x;
            x.right = temp;
            
            return y;
        }
        
        public int getBalance(Node x) {
            if(x == null)
                return 0;
            return height(x.left) - height(x.right);
        }
        
        public Node rebalance(Node x) {
        	int balanceFactor = getBalance(x);
        	
        	if (balanceFactor < -1) {
        		if (getBalance(x.left) <= 0) {
            		x = rightRotate(x);        			
        		}
        		else {
        			x.left = leftRotate(x.left);
        			x = rightRotate(x);
        		}
        	}
        	if(balanceFactor > 1) {
        		if(getBalance(x.right) >= 0) {
        			x = leftRotate(x);
        		}
        		else {
        			x.right = rightRotate(x.right);
        			x = leftRotate(x);
        		}
        	}
        	return x;
        }
        
        public int updateHeight(Node x) {
        	if (x == null) {
        		return -1;
        	}
        	else {
        		x.height = 1 + Math.max(updateHeight(x.left), updateHeight(x.right));
        	}
        	return x.height;
        }
        
        public Node insertNode(int key, Node x) {
        	x = insertNode(key, x);
        	updateHeight(x);
        	return rebalance(x);
        }
        
        public Node deleteNode(int key, Node x) {
        	x = deleteNode(key, x);
        	if (x == null) { 
        		return null;
        	}
        	updateHeight(x);
        	
        	return rebalance(x);
        }
        
        public void inOrderTraversal(Node x){
        	if (x == null) {
        		return;
        	}
        	
        	inOrderTraversal(x.left);
        	System.out.print(x.key + " ");
        	inOrderTraversal(x.right);
        } 
        public void inOrder() {
        	inOrderTraversal(root);
        }
}

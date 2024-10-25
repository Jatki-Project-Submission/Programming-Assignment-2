import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BookOrder {
	

	public static void main(String[] args) throws Exception {
		
	Node test  = new Node(1);
	Node test2 = new Node();
		
	BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\aholl91\\eclipse-workspace\\ProgAssign2\\src\\orders.csv"));
	List<String> lines = new ArrayList<>();
	String line = null;
	while((line = reader.readLine()) != null) {
		lines.add(line);
	}
	
	System.out.println(lines.get(0)+"\n");
	for (int i=1; i<lines.size(); i++) {
		System.out.println(lines.get(i));
	}
		
		System.out.println("\nChoose From Menu:");
		System.out.print("1. Manually add a book order\n2. Manually remove a book order\n3. Print an In-Order list of book names by order ID\n4. Find the name of the book for a specific order number\n5. Find the oldest book order\n6. Find the latest book order\n");
		Scanner scan = new Scanner(System.in);
		int input;
		do {
			while(!scan.hasNextInt()) {
				System.out.println("Please choose an option 1-6");
				scan.next();
			}
			input = scan.nextInt();
		} while(input <= 0 || input >=7);
		
		switch(input) {
		case 1:
			System.out.println("You chose option 1");
			break;
			
		case 2:
			System.out.println("You chose option 2");
			break;
			
		case 3:
			System.out.println("You chose option 3");
			break;
			
		case 4:
			System.out.println("You chose option 4");
			break;
			
		case 5:
			System.out.println("You chose option 5");
			break;
			
		case 6:
			System.out.println("You chose option 6");
			break;
			
		}
	}
}

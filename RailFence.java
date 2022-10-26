import java.util.Scanner;

public class RailFence {
	static String r1 = "";
	static String r2 = "";
	static String r3 = "";
	static String result = "";
	
	public static String encrypt(String p, int key) {
		// key 2
		if(key == 2) {
			for(int i = 0 ; i < p.length(); ) {
				r1 += p.charAt(i++);
				if(i == p.length())
					break;
				r2 += p.charAt(i++);
			}// for assigning the letters for each row
				
			result = r1+r2;// for adding rows to result
		}// end of key 2
		
		// key 3
		else {
			for(int i = 0 ; i < p.length(); ) {
				r1 += p.charAt(i++);
				if(i == p.length())
					break;
				r2 += p.charAt(i++);
				if(i == p.length())
					break;
				r3 += p.charAt(i++);
				if(i == p.length())
					break;
				r2 += p.charAt(i++);
			}// for assigning the letters for each row
			
			result = r1+r2+r3;// for adding rows to result
		}// end of key 3
		return result;
	}
	
	public static String decrypt(String p, int key) {
		String reverseR3 = "";
		int odd = 0;
		int mult4 = 0;
		// key 2
		if(key == 2) {
			
			if(p.length()%2 == 1)
				odd = 1; // Checking if it's an odd length
			
			for(int i = 0 ; i < p.length()/2 + odd; i++) { 
				r1 += p.charAt(i);
				
				if(i == p.length()/2) 
					break;
				
				r2 += p.charAt(i + p.length()/2 +odd);
			}// for assigning the letters for each row

			for(int i = 0 ; i < r1.length() ; i++) {
				result += r1.charAt(i);
				if(r2.length() == i)
					break;
				result += r2.charAt(i);
				}// for adding rows to result

		}// end of key 2
		
		// key 3
		else {
			if(p.length()%4 == 0)
				mult4 = 1; // checking if the length is multiple of 4

			for(int i = 0 ; r1.length()+r2.length()+reverseR3.length() != p.length(); i++) {
				r1 += p.charAt(i);
				if(r1.length()+r2.length()+reverseR3.length() == p.length())
					break;
				
				r2 += p.charAt(i + p.length()/4 + 1 + i - mult4);
				if(r1.length()+r2.length()+reverseR3.length() == p.length())
					break;
				
				reverseR3 += p.charAt(p.length() - i - 1);
				if(r1.length()+r2.length()+reverseR3.length() == p.length())
					break;
				
				r2 += p.charAt(i + p.length()/4 + 2 + i - mult4);

			}// for assigning the letters for each row
			
			for(int i = reverseR3.length() - 1; i >= 0; i--)
			 {
			 r3 = r3 + reverseR3.charAt(i);
			 }// for reversing row 3
			
			for(int i = 0 ; result.length() != p.length() ; i++) {
				result += r1.charAt(i);
				if(result.length() == p.length())
					break;
				
				result += r2.charAt(i+i);
				if(result.length() == p.length())
					break;
				
				result += r3.charAt(i);
				if(result.length() == p.length())
					break;
				
				result += r2.charAt(i+1+i);
				}// for adding rows to result
			
		}// end of key 3
		return result;
	}
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		
		String input = "";
		int intInput = 0;
		String text = "";
		String row = "";
		int intRow = 0;
		boolean rowloop = true;
		boolean textloop = false;
		
		System.out.println("Rail Fence cipher \nChoose from the list below \n1- Encrypt \n2- Decrypt \n3- exit");

		while(true) {
			input = kb.nextLine();
			// Checking the input
			if(input.length() == 1 && (input.charAt(0) == '1' || input.charAt(0) == '2' || input.charAt(0) == '3')) {
				intInput = Integer.parseInt(input);
				
				if(intInput == 3) break;
				
				System.out.println("Enter how many rows only enter 2 or 3");
				
				while(rowloop) {
					row =  kb.nextLine();
					// Checking the input
					if(row.length() == 1 && (row.charAt(0) == '2' || row.charAt(0) == '3')) {
						intRow = Integer.parseInt(row);
						rowloop = false;
					}else System.out.println("Wrong input please try again using only 2 or 3");
				}
				// Encrypt
				if(intInput == 1) {
					System.out.println("Enter the plaintext using only letters");
					while(!textloop) {
						text = kb.nextLine();
						// Removing all spaces
						text = text.replaceAll(" ", "");
						// Checking the input
						textloop = text.matches("[a-zA-Z]+");
						if(!textloop)
							System.out.println("Wrong input please try again using only letters");
					}
					
					String cp = encrypt(text, intRow);
					System.out.println("Done \nCiphertext: " + cp + "\n");
				}// End of Encrypt
				// Decrypt 
				else {
					System.out.println("Enter the ciphertext using only letters");
					while(!textloop){
						text = kb.nextLine();
						// Removing all spaces
						text = text.replaceAll(" ", "");
						// Checking the input
						textloop = text.matches("[a-zA-Z]+");
						if(!textloop)
							System.out.println("Wrong input please try again using only letters");
					}
					
					String pt = decrypt(text, intRow);
					System.out.println("Done \nPlaintext: " + pt + "\n");
				}// End of Decrypt 
				if(intRow == 2) {// Printing table for key 2
					if(text.length() > 1) { // Printing table only for text that longer than 1
						for(int i = 0 ; i < r1.length() ; i ++) 
							System.out.print(r1.charAt(i)+"      ");
						
						System.out.print("\n   "+r2.charAt(0));
						for(int i = 1 ; i < r2.length() ; i++)
							System.out.print("      "+r2.charAt(i));
						System.out.println("\n\n");
						}
					}else {// Printing table for key 3
						if(text.length() > 2) { // Printing table only for text that longer than 2
							for(int i = 0 ; i < r1.length() ; i ++) 
								System.out.print(r1.charAt(i)+"             ");
						
							System.out.print("\n   "+r2.charAt(0));
							for(int i = 1 ; i < r2.length() ; i++)
								System.out.print("      "+r2.charAt(i));
						
							System.out.print("\n      " + r3.charAt(0));
							for(int i = 1 ; i < r3.length() ; i ++) 
								System.out.print("             " + r3.charAt(i));
							System.out.println("\n\n");
							}
					}
				
				break;
			}else 
				System.out.println("Wrong input please try again using only 1 or 2 or 3");
			
		}
		kb.close();
		System.out.println("Closing program...\nThank you for using my program");
		
	}

}

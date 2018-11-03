package cryptography;
import java.util.Scanner;
public class Hill2 {
	public static void main(String[] args) {
		int p1 = 0, p2 = 0, p3 = 0, p4 = 0;
		
		String s;
		System.out.println("Please enter four letters: ");
		Scanner scanner = new Scanner(System.in);
		do {
		s = scanner.nextLine();
		char [] c = s.toCharArray();
	
		int [] i =new int[100];
		for(int j = 0;j<4;j++)
		{
			if(c[j] == ',') {
				i[j] = 26;
			}
			
			else if(c[j] == '.') {
				i[j] = 27;
			}
			
			else if(c[j] == ' ') {
				i[j] = 28;
			}
			else {
				i[j] = c[j] - 65;
			}
		
		}
		
		
		
		

		for (p1 = 0; p1 < 28; p1++) {
			for (p2 = 0; p2 < 28; p2++) {
				for (p3 = 0; p3 < 28; p3++) {
					for (p4 = 0; p4 < 28; p4++) {
						int c11 = (19 * p1 + 18 * p2 + 14 * p3 + 0 * p4) % 29;
						int c21 = (15 * p1 + 26 * p2 + 18 * p3 + 19 * p4) % 29;
						int c31 = (0 * p1 + 6 * p2 + 14 * p3 + 23 * p4) % 29;
						int c41 = (18 * p1 + 2 * p2 + 22 * p3 + 16 * p4) % 29;
						if (c11 == i[0] && c21 == i[1] && c31 == i[2] && c41 == i[3]) {
							int[] p = new int[4];
							p[0] = p1;
							p[1] = p2;
							p[2] = p3;
							p[3] = p4;
							for(int j = 0;j<4;j++)
							{
							if(p[j] == 26) {
								c[j] = ',';
							}
							
							else if(p[j] == 27) {
								c[j] = '.';
							}
							
							else if(i[j] == 28) {
								c[j] = ' ';
							}
							else {
								c[j] = (char) (p[j]+65);
							}
							
							}
							System.out.println(c[0] + " " + c[1] + " " + c[2] + " " + c[3]);
						}
					}
				}
			}
		}
		
		}while(true);
	}
}

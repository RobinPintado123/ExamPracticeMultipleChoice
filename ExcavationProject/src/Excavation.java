import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Excavation {
	private static int subArrayIndex = 0;
	private static int maxSubArrayIndex = 0;
	private static int maxSum = 0;
	private static int ROWSTART =0, ROWEND =0;
	private static ArrayList<Integer> numbers = new ArrayList<>();
	private static int[][] squares;
	
	public static void Excavate(int []arr, int rowStart, int rowEnd) {
				
		int startingNumber = arr[0];
		int tempIndex = 0;
		int previousNum = arr[0];
		for(int i = 1; i < arr.length; i++) {

			previousNum = previousNum + arr[i];
			
			if(previousNum < arr[i]) {
				previousNum = arr[i];
				tempIndex = i;
			}
			
			if(maxSum < previousNum) {
				maxSum = previousNum;
				subArrayIndex = tempIndex;
				maxSubArrayIndex = i;
				ROWSTART = rowStart;
				ROWEND = rowEnd;
			}
			
		}
		
		if(startingNumber > maxSum) {
			maxSum = startingNumber;
			subArrayIndex = 0;
			maxSubArrayIndex = 0;
			ROWSTART = rowStart;
			ROWEND = rowEnd;
			
		}
		
	}
	

	public static void main(String arg[]) throws FileNotFoundException {
		readFileInput("input.txt");
		PopulateArrayList();

		int []first = new int[numbers.get(0)];
		int index = 0;
		
		/*for(int row = 0; row < numbers.get(0); row++) {
			index = 0;
			for(int column =0; column < numbers.get(0); column++) {
				
				first[index++] = squares[column][row];
			}
			Excavate(first, row, row);
		}*/
		//System.out.println(first.length);
		
		for(int rowStart =0; rowStart < numbers.get(0); rowStart++) {
			//reset the array
			for(int i=0; i < first.length; i++) {
				first[i] = 0;
			}
			
		for(int row = rowStart; row < numbers.get(0); row++) {
			index = 0;
			for(int column =0; column < numbers.get(0); column++) {
				
				first[index++] += squares[column][row];

			}
			
			Excavate(first, rowStart, row);
		}
		}
		System.out.println("starting index col start= " + subArrayIndex);
		System.out.println("max sub index col end = " + maxSubArrayIndex);
		System.out.println("row start = " +  ROWSTART);
		System.out.println("row end = " + ROWEND);
		System.out.println("Max Number = " + maxSum);
		
		subArrayIndex++;
		maxSubArrayIndex++;
		ROWSTART++;
		ROWEND++;
	
	    try (FileWriter writer = new FileWriter("output.txt"); 
	              BufferedWriter bw = new BufferedWriter(writer)) {
	     
	    			bw.write(subArrayIndex + " " + ROWSTART  + "\n");
	    			bw.write(maxSubArrayIndex + " " + ROWEND +  "\n");
	    		
	            

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}
	
	
	public static void PopulateArrayList() {
		
		int index = 1;
		squares = new int[numbers.get(0) + 1][numbers.get(0) + 1];
		
		for(int column = 0; column < numbers.get(0); column++) {
			for(int row = 0; row < numbers.get(0); row++) {
				squares[column][row] = numbers.get(index++);
			}
		}
		
	
	}
	
	
	public static void readFileInput(String file) throws FileNotFoundException{
		File f = new File(file);
		Scanner sc = new Scanner(f);
		
		while (sc.hasNext()) {
		    if (sc.hasNextInt()) {
		    	numbers.add(sc.nextInt());
		    } else {
		        sc.next();
		    }
		}
	}

}

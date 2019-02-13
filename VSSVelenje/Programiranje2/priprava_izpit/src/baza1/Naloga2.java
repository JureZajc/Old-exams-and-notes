package baza1;

import java.util.Scanner;

public class Naloga2 {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		//scanner.useDelimiter("\r\n");
		
		int count = 0;
		//int something = 1;
		int[] arr = new int[500];
		while (scanner.hasNextLine()) {
			if (scanner.hasNextInt()){
				String nextIntString = scanner.next();
				int nextInt = Integer.parseInt(nextIntString);
				arr[count] = nextInt;
				//something++;
				
				count++;
			}
			
				//String string = (String) scanner.next();
				//String method = (String) scanner.next();
				//String direction = (String) scanner.next();	
				String string = args[0];
				String method = args[1];
				String direction = args[2];
				int justAnotherVariable = 0;
				
				for (int i = 0; i < arr.length; i++) {
					if (arr[i] != 0){
						justAnotherVariable++;
					}
					
				}
				int count22 = 0;
				int[] arr2 = new int[justAnotherVariable];
				for (int i = 0; i < arr.length; i++) {
					if(arr[i] != 0){
						arr2[count22] = arr[i];
						count22++;
					}
				}				
				runCommand(string, method, direction, arr2);
			
		
	
		}			
		//scanner.close();
		System.exit(0);
		}	
	
	static void runCommand(String string, String method, String direction, int[] arr2){
		if (string.equals("trace")){
			if(method.equals("select")){
				if(direction.equals("up")){
					selectUpWith(arr2);
				}
				else if (direction.equals("down")) {
					selectDownWith(arr2);
				}
			}
			else if (method.equals("insert")) {
				if (direction.equals("up")){
					insertUpWith(arr2);
				}
				else if (direction.equals("down")) {
					insertDownWith(arr2);
				}
			}
			else if (method.equals("heap")) {
				if(direction.equals("down")){
					sortUp(arr2);
				}
				else if (direction.equals("up")) {
					heapSort(arr2);
				}
			}
			else if (method.equals("bubble")) {
				if(direction.equals("down")){
					bubbleSort(arr2);
				}
			}
			
		}
		else if (string.equals("count")) {
			
		}
	}
	
	static void bubbleSort(int[] arr){
		int temp = 0;
		
		int count = 0;
		for(int i = 0 ; i < arr.length ; i++){
			boolean swap = false;
			
			for(int j = 0 ; j<arr.length - i-1 ; j++){ if(arr[j] < arr[j+1]){
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1]=temp;
					swap=true;
					for (int j1 = 0; j1 < count; j1++) {
						System.out.print(arr[j1] + " ");
					}
		            count++;	
		            System.out.print("| ");
		            for (int h = count; h < arr.length; h++){
		            	System.out.print(arr[h]+ " ");
		            }System.out.println();
				}
			
			}
			if(!swap)
				break;
		}
	}
	
	static void insertDownWith(int[] arr){
		int n = arr.length;
		int count = 1;
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j = i-1;
            while (j>=0 && arr[j] < key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
            for (int j1 = 0; j1 <= count; j1++) {
				System.out.print(arr[j1] + " ");
			}
            count++;
            System.out.print("| ");
            for (int h = count; h < arr.length; h++){
            	System.out.print(arr[h]+ " ");
            }
            System.out.println();
        }
	}
	
	
	static void selectUpWith(int[] arr){
		   int count = 0;
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		   for (int i = 0; i < arr.length - 1; i++)
	        {
	           
	            int index = i;
	            for (int j = i + 1; j < arr.length; j++)
	                if (arr[j] < arr[index]) 
	                    index = j;

	            int smallerNumber = arr[index];  
	            arr[index] = arr[i];
	            arr[i] = smallerNumber;
	            for (int j = 0; j <= count; j++) {
					System.out.print(arr[j] + " ");
				}
	            count++;
	            System.out.print("| ");
	            for (int h = count; h < arr.length; h++){
	            	System.out.print(arr[h]+ " ");
	            }
	            System.out.println();
	        }
		}
	
	static void selectDownWith(int[] arr){
		   int count = 0;
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		   for (int i = 0; i < arr.length - 1; i++)
	        {
	            int index = i;
	            for (int j = i + 1; j < arr.length; j++)
	                if (arr[j] > arr[index]) 
	                    index = j;

	            int smallerNumber = arr[index];  
	            arr[index] = arr[i];
	            arr[i] = smallerNumber;
	            for (int j = 0; j <= count; j++) {
					System.out.print(arr[j] + " ");
				}
	            count++;
	            System.out.print("| ");
	            for (int h = count; h < arr.length; h++){
	            	System.out.print(arr[h]+ " ");
	            }
	            System.out.println();
	        }
		}
	
	static void insertUpWith(int[] arr){
		int n = arr.length;
		int count = 1;
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j = i-1;
            while (j>=0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
            for (int j1 = 0; j1 <= count; j1++) {
				System.out.print(arr[j1] + " ");
			}
            count++;
            System.out.print("| ");
            for (int h = count; h < arr.length; h++){
            	System.out.print(arr[h]+ " ");
            }
            System.out.println();
        }
	}
	
	static void heapSort(int arr[])
    {
    	for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
    	System.out.println();
        int n = arr.length;
        int count = n-1;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i=n-1; i>=0; i--)
        {

            int temp = arr[0];
            for (int j1 = 0; j1 <= count; j1++) {
    			System.out.print(arr[j1] + " ");
    		}
            count--;
            System.out.print("| ");
            for (int h = count + 2; h < arr.length; h++){
            	System.out.print(arr[h]+ " ");
            }
            System.out.println();
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
          
        }
        
    }

    @SuppressWarnings("unused")
	static
	void heapify(int arr[], int n, int i)
    {
    	int count = 0;
    	int count2 = 0;
        int largest = i;  
        int l = 2*i + 1;  
        int r = 2*i + 2;  

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i)
        {

            int swap = arr[i];
            
            arr[i] = arr[largest];
        	
            arr[largest] = swap;

            heapify(arr, n, largest);
            
            
            }
        
    }
    
    static void sortUp(int arr[])
    {
    	for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
    	System.out.println();
        int n = arr.length;
        int count = n-1;

 

        for (int i = n / 2 - 1; i >= 0; i--)
            heapifyUp(arr, n, i);
        

        for (int i=n-1; i>=0; i--)
        {

            int temp = arr[0];
            for (int j1 = 0; j1 <= count; j1++) {
    			System.out.print(arr[j1] + " ");
    		}
            count--;
            System.out.print("| ");
            for (int h = count + 2; h < arr.length; h++){
            	System.out.print(arr[h]+ " ");
            }
            System.out.println();
            arr[0] = arr[i];
            arr[i] = temp;
            

            heapifyUp(arr, i, 0);
          
        }
        
    }

    @SuppressWarnings("unused")
	static void heapifyUp(int arr[], int n, int i)
    {
    	int count = 0;
    	int count2 = 0;
        int largest = i;  
        int l = 2*i + 1;  
        int r = 2*i + 2;  

        if (l < n && arr[l] < arr[largest])
            largest = l;

        if (r < n && arr[r] < arr[largest])
            largest = r;

        if (largest != i)
        {

            int swap = arr[i];
            
            arr[i] = arr[largest];
        	
            arr[largest] = swap;

            heapifyUp(arr, n, largest);

            }
        
    }
}

class DifferentSorts{
	public static int insertUpCount(int[] a){
		int swaps = 0;
		for (int i = 1; i < a.length; i++){
			int temp = a[i];
			int j;
			for (j= i; j > 0 && a[j-1] < temp; j++){
				a[j] = a[j-1];
			}
			a[j] = temp;
			swaps++;
		}
		return swaps;
	}
	
	public static void insertUpWith(int[] arr){
		int n = arr.length;
		int count = 1;
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j = i-1;
            while (j>=0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
            for (int j1 = 0; j1 <= count; j1++) {
				System.out.print(arr[j1] + " ");
			}
            count++;
            System.out.print("| ");
            for (int h = count; h < arr.length; h++){
            	System.out.print(arr[h]+ " ");
            }
            //System.out.println("insertUpWith");
        }
	}
	
	public static void insertDownWO(int[] a){
		for (int i = 1; i < a.length; i++){
			int temp = a[i];
			int j;
			for (j= i; j > 0 && a[j-1] < temp; j++){
				a[j] = a[j-1];
				System.out.print(a[j] + " ");
			}
			a[j] = temp;
			System.out.println();
		}	
	}
	
	public static void insertDownWith(int[] a){
		for(int i = 1; i < a.length; i++){
			int temp = a[i];
			int j;
			for (j = i; j > 0 && a[j-1] < temp; j++) {
				a[j] = a[j-1];
				System.out.print(a[j] + " ");
			}
			a[j] = temp;
		}
	}
	public static int selectUpWO(int[] arr){
		   int count = 0;
		   int swaps = 0;
		   for (int i = 0; i < arr.length - 1; i++)
	        {
	           
	            int index = i;
	            for (int j = i + 1; j < arr.length; j++)
	                if (arr[j] > arr[index]) 
	                    index = j;

	            int smallerNumber = arr[index];  
	            arr[index] = arr[i];
	            arr[i] = smallerNumber;
	           swaps+=3;
	        }
		   return swaps;

		}

	
	 public void selectUpWith(int[] arr){
		   int count = 0;
		   for (int i = 0; i < arr.length - 1; i++)
	        {
	           
	            int index = i;
	            for (int j = i + 1; j < arr.length; j++)
	                if (arr[j] < arr[index]) 
	                    index = j;

	            int smallerNumber = arr[index];  
	            arr[index] = arr[i];
	            arr[i] = smallerNumber;
	            for (int j = 0; j <= count; j++) {
					System.out.print(arr[j] + " ");
				}
	            count++;
	            System.out.print("| ");
	            for (int h = count; h < arr.length; h++){
	            	System.out.print(arr[h]+ " ");
	            }
	            System.out.println();
	        }
		}
	
	
	public static int selectDownWO(int[] arr){
		int count = 0;
		int swaps = 0;
		   for (int i = 0; i < arr.length - 1; i++)
	        {
	            int index = i;
	            for (int j = i + 1; j < arr.length; j++)
	                if (arr[j] > arr[index]) 
	                    index = j;
	            int smallerNumber = arr[index];  
	            arr[index] = arr[i];
	            arr[i] = smallerNumber;
	            swaps += 3;
	            
	        }
		   return swaps;
		}
	
	public static void selectDownWith(int[] arr){
		   int count = 0;
		   for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		   for (int i = 0; i < arr.length - 1; i++)
	        {
	            int index = i;
	            for (int j = i + 1; j < arr.length; j++)
	                if (arr[j] > arr[index]) 
	                    index = j;

	            int smallerNumber = arr[index];  
	            arr[index] = arr[i];
	            arr[i] = smallerNumber;
	            for (int j = 0; j <= count; j++) {
					System.out.print(arr[j] + " ");
				}
	            count++;
	            System.out.print("| ");
	            for (int h = count; h < arr.length; h++){
	            	System.out.print(arr[h]+ " ");
	            }
	            //System.out.println("selectDownWith");
	        }
		}
	

	
	public static void bubbleUpWO(int[] a){
		int swaps = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 1; j < a.length - i; j++) {
				if (a[j-1] > a[j]){
					a[j-1] = j;
					swaps++;
				}				
			}
		}
	}
	
	public static void bubbleDownWO(int[] a){
		int swaps = 0;
		int temp;
		for (int i = 0; i < a.length; i++) {
			for (int j = 1; j < a.length - i; j++) {
				if (a[j-1] < a[j]){
					temp = a[j-1];
					a[j-1] = a[j];
					swaps++;
					a[j] = temp;
					swaps++;
				}				
			}
		}
	}
	
	public static void bubbleDownWith(int[] a){
		int swaps = 0;
		int temp;
		for (int i = 0; i < a.length; i++) {
			for (int j = 1; j < a.length - i; j++) {
				if (a[j-1] < a[j]){
					temp = a[j-1];
					a[j-1] = a[j];
					a[j] = temp;
					swaps++;
				}				
			}
		}
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
	
	
	public static void heapUpWO(int[] a){
		
	}
	
	
	
}

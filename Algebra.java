// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
		   System.out.println(pow(-2,3)); 
		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));

		
	}  
	public static int abs(int x){
		if (x<0) {
			return -x; 	
		}
		return x;
	}
	public static int minusabs(int x){
		if(x>0){
			return -x;
		}
		return x;
	}
	// Returns x1 + x2
	public static int plus(int x1, int x2) {
	if (x1>=0&&x2>=0) {
		for(int i = 0 ; i<x1; i++ ){
			x2++;
		}
		return x2;
	}
	else{
		if (x1<0) {
			if (x2<0) {
				for(int i = 0 ; i>x1; i-- ){
					x2--;
				}
				return x2;
			}
			else{
				for(int i = 0 ; i<x2; i++ ){
					x1++;
				}
				return x1;
				}
			}
			else{
				for(int i = 0 ; i<x1; i++ ){
					x2++;
				}
				return x2;
			}
			
		}
	}
	

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		if (x2>=0&&x1<=0) {
			for(int i = 0 ; i<x2; i++ ){
				x1--;
			}
			return x1;
		}
		else{
			if (x2<0) {
				for(int i = 0 ; i>x2; i-- ){
					x1++;
				}
				return x1;
			}
			else  {  
				for(int i = 0; i<x2; i++)
				{
					x1--;
				}
				return x1;
			}

			}
		}
	
	
	// Returns x1 * x2
	public static int times(int x1, int x2) {
		if (x1 == 0 || x2 == 0) {
			return 0;
		}
	
		int result = 0;
		int absX2 = Algebra.abs(x2); 
		for (int i = 0; i < absX2; i++) {
			result += x1; 
		}
		if (x2 < 0) {
			result = -result;
		}
	
		return result;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		int total = x;
		int i = 1;
		if(n != 0){
			for (i = 1; i < n; i++) {
				total = times(total, x);
			}
	    }
		else{
			total = 1;
		}
		return total;
		
	}
			
		//}
	

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		int counter = 1;
		int result = 0;
		if(x1==0)
		{
			counter = 0;
		}
		else{ 
			if(x1 > 0 && x2 > 0)
			{
				result= x2;
				while (result < x1 && plus(result, x2) <= x1) {
					result = plus(result, x2);
					counter++;
				}
	     	}
			else{
				 if(x2 < 0&&x1 < 0 )
				 {
					x1= abs(x1);
					x2= abs(x2);
					result= x2;
					while (result < x1 && plus(result, x2) <= x1) {
						result = plus(result, x2);
						counter++;
					}
				 }
				  else{
					if(x1 < 0 )
						{
							result= x2;
							x1= abs(x1);
							while (result < x1 && plus(result, x2) <= x1) {
								result = plus(result, x2);
								counter++;
							}
							counter= minusabs(counter);
						}
					
					 else{
						x2= abs(x2);
						result= x2;
							while (result < x1 && plus(result, x2) <= x1) {
								result = plus(result, x2);
								counter++;
							}
							counter= minusabs(counter);
						}
					 }
				}

			}
		return counter;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		while (x1>=x2) {
			x1 = minus(x1, x2);
			
		}
		return x1;
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		int result = 0;
		while(times(result, result) < x) {
			result ++ ;
		}
		if(times(result , result) > x){
 		result --;
		}	
		return result;
	}	  	  
}
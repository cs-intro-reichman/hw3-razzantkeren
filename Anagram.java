/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");    
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		// Replace the following statement with your code
		str1 = preProcess(str1);
		str2 = preProcess(str2);
		str1 = prespace(str1);
		str2=prespace(str2);
		System.out.println(str1);
		System.out.println(str2);
		int counter = 0;
		if (str1.length()!=str2.length()) {
			return false;
		}
		else{
		for(int i = 0; i<str1.length();i++){
			for(int j = 0; j<str2.length();j++){
				if (str1.charAt(i)==str2.charAt(j)) {
					counter++;
					str2 = str2.substring(0, j)+ str2.substring(j, str2.length());
					j = str2.length();
				}
			}
		}
		}

		if (counter==str1.length()) {
			return true;
		}
		return false;
		}
	
	
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String prespace(String str) {
		String retstring = "";
		for(int i = 0; i<str.length();i++){
			if (str.charAt(i)!=32) {
				retstring+=str.charAt(i);
			}
		}
			return retstring;
	
	}

	public static String preProcess(String str) {
		int length = str.length();
		String updatestring = "";
		for(int i = 0; i<length;i++){
			if ((int)str.charAt(i)>=97&&(int)str.charAt(i)<=122||str.charAt(i)==32) {
				updatestring += str.charAt(i);
			}
			else{
				if ((int)str.charAt(i)>=65&&(int)str.charAt(i)<=90||str.charAt(i)==32) {
				char lowcasechar =(char)(str.charAt(i)+32);
				updatestring+=lowcasechar;
			}
		}
		// Replace the following statement with your code
		}
		return updatestring;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		int index1 = (int)(Math.random()*str.length()+1);
		int index2 = (int)(Math.random()*str.length()+1);
		if (index1<str.length()&&index2<str.length()) {
			char firstletter = str.charAt(index1);
			char secondetletter = str.charAt(index2);
			String newstring = str.replace(firstletter, secondetletter); 		
			return newstring;
		}
		return "";
		
	}
}


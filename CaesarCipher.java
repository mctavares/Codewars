package mt.codewars;

import java.util.ArrayList;
import java.util.List;
/*
First Variation on Caesar Cipher Solution
M. Tavares
The action of a Caesar cipher is to replace each plaintext letter with 
a different one a fixed number of places up or down the alphabet.

This program performs a variation of the Caesar shift. The shift increases by 1 
for each character (on each iteration).

If the shift is initially 1, the first character of the message to be encoded 
will be shifted by 1, the second character will be shifted by 2, etc...
*/
public class CaesarCipher {
	public static List<String>  movingShift(String str, int shift) {
		StringBuffer sb = new StringBuffer(str.length());
		final int NUM_LETTERS = 26;
			
		for (int i = 0; i < str.length(); i++, shift++) {
			char c = str.charAt(i);
			if (Character.isLetter(c)) { 
				int start = Character.isUpperCase(c) ? (int)'A':(int)'a'; 
				int pos = c - start;
				c = (char)(start + (pos + shift)%NUM_LETTERS);
			}
			sb.append(c);		 
		}

		return populateList(sb.toString());	
	}
	
	public static String  demovingShift(List<String> ls, int shift) {
		StringBuffer sb = new StringBuffer();
		for (String ss : ls) {
			sb.append(ss);
		}
		String str = new String(sb.toString());
		sb.delete(0, sb.length());
		final int NUM_LETTERS = 26;
		
		for (int i = 0; i < str.length(); i++, shift++) {
			char c = str.charAt(i);
			if (Character.isLetter(c)) { 
				int start = Character.isUpperCase(c) ? (int)'Z':(int)'z'; 
				int pos = c - start;
				char c1 = (char)(start - Math.abs((pos - shift)%NUM_LETTERS));
				sb.append(c1);
			} else {
				sb.append(c);
			}					 
		}
		return sb.toString();
	}
  
	private static List<String> populateList(String sb){
		final int SIZE = 4; // 5 - 1
		int n = sb.length()/SIZE; // size of the part
		int r = sb.length()%SIZE; // remainder
		
		while (n-1 >= r + SIZE) {
			n--;
			r +=SIZE;
		}
		List<String> ls = new ArrayList<String>(SIZE);
		int i = 0;
		for (i = 0; i < SIZE; i++){
			ls.add(sb.substring(i*n, (i+1)*n));
		}
		ls.add(sb.substring(i*n, sb.length()));
		return ls;
	}
}
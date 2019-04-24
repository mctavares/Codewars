import java.util.function.ToIntBiFunction;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

// https://www.codewars.com/kata/rail-fence-cipher-encoding-and-decoding
public class RailFenceCipher {
	
	public static void main(String ... args) {
		
		String str = "WEAREDISCOVEREDFLEEATONCE";
		int key = 3;
		try {
			key = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			key = 3;
		}

		String result = "WECRLTEERDSOEEFEAOCAIVDEN";
		
		String encoded = encode(str, key);
		/*
		String result = "WECRLTEERDSOEEFEAOCAIVDEN";
		System.out.println("encoded:" + encoded + "\nresult: " + result);// + "\nveredict: " + result.equals(encoded));
		*/
		String decoded = decode(encoded, key);
		System.out.println("decoded:" + decoded);// + "\nresult: " + result);// + "\nveredict: " + result.equals(encoded));	
	}
    
	
	// Encoding
	static ToIntBiFunction<Integer,Integer> getLevel = (n, key)-> {
		int index = n % key; // 0, 1, 2, .., key-1
		if (n/key % 2 != 0)	{ // even
			index = key - index;
		}
		return index;
	};
	
	
    static String encode(String s, int key) {
		Map<Integer, ArrayList<Character>> map = new HashMap<Integer, ArrayList<Character>>(s.length());
		for (int i = 0; i < key; i++)	{
			map.put(i, new ArrayList<Character>());			
		}
		for (int i = 0; i < s.length(); i++)	{
			map.get(getLevel.applyAsInt(i, key - 1)).add(s.charAt(i));
		}
		StringBuffer sb = new StringBuffer(s.length());
		for (int i = 0; i < key; i++)	{
			for(char c: map.get(i)) {
				sb.append(c);
			}
		}
        return sb.toString();
    }
    // Brute force
    static String decode(String s, int key) {
		char[] res = new char[s.length()];
		int pos, k = 0;

		for(pos=0; pos<key-1; pos++){
			int width=2*(key-pos-1);
			for(int i = pos, j = 0; i<s.length();j++){
				res[i] = s.charAt(k++);
				if((pos==0) || (j%2 == 0)) {
					i+=width;
				} else { 
					i+=(2*(key-1) - width);
				}
			}
		  }
		for(int i=pos; i<s.length(); i+=2*(key-1)) res[i] = s.charAt(k++);
	 
		return new StringBuffer(s.length()).append(res).toString(); 
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
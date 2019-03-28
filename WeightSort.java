package mt.codewars;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://www.codewars.com/kata/weight-for-weight/train/java
public class WeightSort {
	 	
	public static String orderWeight(String str) {
		// List can be empty
		String res = "";
		if (str != null && str.length() > 0) {
			String[] weights = str.split(" ");
			List<WeightItem> ls = new ArrayList<WeightItem>(weights.length);
			
			for (String w : weights) {
				ls.add(new WeightItem(w));			
			}
			Collections.sort(ls, (WeightItem a, WeightItem b) -> {
					int res1 = (int)(a.getScore() - b.getScore());
					if (res1 == 0) {
						res1 = a.getWeight().compareTo(b.getWeight());
					}
			        return res1; 
			});

			for (WeightItem w : ls) {
				res += (w.getWeight() + " ");
			}		
		}
		return res.trim();
	}

	
}

class WeightItem {
	String w;
	long score;
	
	public WeightItem(String w) {
		this.w = w;
		this.score = score(w);
	}
	
	private long score(String str) {
		long number = Long.valueOf(str);
		long sum = 0;
		while (number > 0) {
			sum += (number % 10);
			number /= 10;
		}				
		return sum;
	}
	
	public long getScore()	{
		return this.score;
	}
	
	public String getWeight()	{
		return this.w;
	}
}
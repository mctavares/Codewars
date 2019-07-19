import java.util.function.IntPredicate;

// https://www.codewars.com/kata/prime-streaming-pg-13

public class PrimeStreaming {
	
	static public IntPredicate is_composite = n -> {	
		if (n % 3 == 0) {
			return true;
		}
  
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
				return true; 
			}
		}
        return false; 
		
	};

	static public void main(String[] args) {
		IntStream start = IntStream.of(2, 3, 5, 7, 11, 13, 17, 19);
		IntStream odds = IntStream.iterate(23, i -> i + 2).filter(i -> is_composite.negate().test(i));
		IntStream.concat(start, odds).limit(20).forEach(System.out::println);
	}	
}
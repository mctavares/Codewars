// http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/687fd7c7986d/src/share/classes/java/lang/Math.java
// https://www.codewars.com/kata/psychic
import java.util.Random;
import java.lang.reflect.Field;

public class Psychic {
	static private final long seed = System.currentTimeMillis();
	static private Random guess = new Random(seed);
	
	static {
		init();
	}
	
	public static double guess() {
        return guess.nextDouble();
    }
	
	private static void init() {
		try {
			Class<?> RandomNumberGeneratorHolder = Class.forName("java.util.Math$RandomNumberGeneratorHolder");
			Field randomNumberGenerator = RandomNumberGeneratorHolder.getDeclaredField("randomNumberGenerator");
			randomNumberGenerator.setAccessible(true);
			guess = (Random)randomNumberGenerator.get(null);
			guess.setSeed(seed);
			
		} catch(ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
			throw new RuntimeException(e);
			
		}
	}
}
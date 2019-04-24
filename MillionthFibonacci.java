import java.math.BigInteger;
import java.util.function.Predicate;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

// https://www.codewars.com/kata/the-millionth-fibonacci-kata
// https://mitpress.mit.edu/sites/default/files/sicp/full-text/book/book-Z-H-11.html#%_sec_1.2.4
/*
fib(0) := 0
fib(1) := 1
fin(n + 2) := fib(n + 1) + fib(n)
*/

public class  MillionthFibonacci {
	private static int EQUAL = 0;
	private static int LESS_THAN = -1;
	private static int GREATER_THAN = 1;
	private static BigInteger TWO = new BigInteger("2");
	private static BigInteger MINUS_ONE = new BigInteger("-1");
	
	 // n%2 == 0
	static private Predicate<BigInteger> is_even = n -> n.remainder(TWO).compareTo(BigInteger.ZERO) == EQUAL;
	
	static private UnaryOperator<BigInteger> square = n -> n.multiply(n);
	
	static private BinaryOperator<BigInteger> mult = (m, n) -> m.multiply(n);
	
	static private BinaryOperator<BigInteger> add = (m, n) -> m.add(n);
	
	static public BigInteger fib_i(BigInteger m)	{
		BigInteger n = m.abs();
		BigInteger res = fib_iter (BigInteger.ONE,BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE, n);
		if (m.compareTo(BigInteger.ZERO) == LESS_THAN && is_even.test(n.add(BigInteger.ONE)) == false) {
				res = res.negate();
		}
		return res;
	}
	
	static private BigInteger fib_iter(BigInteger a, BigInteger b, BigInteger p, BigInteger q, BigInteger count) {
		if (count.compareTo(BigInteger.ZERO) == EQUAL)
			return b;
		if (is_even.test(count)) {
			BigInteger p_prime =  add.apply(square.apply(p), square.apply(q));// p' = p*p + q*q
			BigInteger q_prime =  add.apply(mult.apply(TWO, mult.apply(p,q)), square.apply(q));//q' = 2*p*q+q*q
			return fib_iter(a, b, p_prime, q_prime, count.divide(TWO));
		} else {
			BigInteger new_a = add.apply(add.apply(mult.apply(b, q), mult.apply(a, q)), mult.apply(a, p));// a = b*q+a*q + a*p
			BigInteger new_b = add.apply(mult.apply(b, p), mult.apply(a, q));//b = b*p+a*q
			return fib_iter(new_a,new_b, p, q, count.subtract(BigInteger.ONE));
		}
	}
	
	public static void main(String[] args) {
		BigInteger n = new BigInteger("10");
		System.out.println(fib_i(n));
		n = new BigInteger("200000");
		System.out.println(fib_i(n));	
	}
}
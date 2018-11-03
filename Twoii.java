/**
 * cryp-ass2-question1
 */
import java.math.BigInteger;

public class Twoii {

	public static void main(String[] args) {
		BigInteger n, p1, p2, fn;
		BigInteger one = new BigInteger("1");
		BigInteger zero = new BigInteger("0");
		BigInteger j = new BigInteger("0");
		p1 = new BigInteger("4841247740021026788214420074996258540545281");
		p2 = new BigInteger("712010411572858151605922429225626518528001");
		n = p1.multiply(p2);
		fn = (p1.subtract(one)).multiply(p2.subtract(one));
		BigInteger[] info = new BigInteger[3];

		for (int i = 2; i < 200; i++) {
			j = BigInteger.valueOf(i);
			info = extendGCD(j, fn);
			if ((info[0].equals(one))) {
				System.out.println("e is " + j);
				System.out.println();
				break;
			}
		}
		System.out.println("GCD(" + fn + "," + j + ") = " + info[0]);
		System.out.println();

		if (info[0].equals(one) && info[1].compareTo(zero) >= 0) {
			BigInteger inverse = info[1];
			System.out.print("d is " + inverse);
			System.out.println();
		}
		if (info[0].equals(one) && info[1].compareTo(zero) < 0) {
			BigInteger inverse = n.add(info[1]);
			System.out.println("d is " + inverse);
			System.out.println();
		}
	}

	static BigInteger[] extendGCD(BigInteger a, BigInteger n) {
		BigInteger[] info = new BigInteger[3];
		BigInteger one = new BigInteger("1");
		BigInteger zero = new BigInteger("0");
		if (n.compareTo(zero) == 0) {
			info[0] = a;
			info[1] = one;
			info[2] = zero;
			return info;
		}
		BigInteger[] prevInfo = extendGCD(n, a.mod(n));
		BigInteger div = a.divide(n);
		BigInteger mul = div.multiply(prevInfo[2]);

		info[0] = prevInfo[0];
		info[1] = prevInfo[2];
		info[2] = prevInfo[1].subtract(mul);
		return info;
	}
}

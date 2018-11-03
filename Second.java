
import java.math.BigInteger;
import java.util.Random;

public class Second {

	public static void main(String[] args) {
		BigInteger n, p1, p2, t, fn, m, mb, sb, s, sm, r, ri, e = null, inverse = null;
		BigInteger one = new BigInteger("1");
		BigInteger zero = new BigInteger("0");
		BigInteger j = new BigInteger("0");
		BigInteger basic = new BigInteger("10").pow(256);
		BigInteger mBasic = new BigInteger("10").pow(500);
		BigInteger[] info = new BigInteger[3];
		BigInteger[] rinfo = new BigInteger[3];
		Random rand = new Random();
		m = new BigInteger(mBasic.bitLength(), rand);
		do {
			p1 = new BigInteger(basic.bitLength(), rand);
		} while (!p1.isProbablePrime(Integer.MAX_VALUE));
		do {
			p2 = new BigInteger(basic.bitLength(), rand);
		} while (!p2.isProbablePrime(Integer.MAX_VALUE));
		n = p1.multiply(p2);
		do {
			r = new BigInteger(n.subtract(one).bitLength(), rand);
			rinfo = extendGCD(r, n);
		} while (!(r.compareTo(n.subtract(one)) < 0 || (rinfo[0].compareTo(one) == 0)));
		if (rinfo[1].compareTo(zero) > 0) {
			ri = rinfo[1];
		} else {
			ri = rinfo[1].add(n);
		}
		System.out.println("GCD(" + r + "," + n + ") = " + rinfo[0]);
		System.out.println(ri);
		fn = (p1.subtract(one)).multiply(p2.subtract(one));
		for (int i = 0; i < 200; i++) {
			j = BigInteger.valueOf(i);
			info = extendGCD(j, fn);
			if ((info[0].equals(one)) && i != 1) {
				e = j;
				System.out.println("e is " + j);
				System.out.println();
				break;
			}
		}
		System.out.println("GCD(" + fn + "," + j + ") = " + info[0]);
		System.out.println();
		BigInteger d = null;
		if (info[0].equals(one) && info[1].compareTo(zero) >= 0) {
			inverse = info[1];
			System.out.print("d is " + inverse);
			System.out.println();
			d = inverse;
		}
		if (info[0].equals(one) && info[1].compareTo(zero) < 0) {
			inverse = n.add(info[1]);
			System.out.println("d is " + inverse);
			System.out.println();
			d = inverse;
		}
		t = m.multiply(r.pow(e.intValue()));
		mb = t.mod(n);
		System.out.println("p1 is " + p1);
		System.out.println("p2 is " + p2);
		System.out.println("m is " + m);
		System.out.println("mb is " + mb);
		sb = mb.modPow(d, n);
		//System.out.println("sb is " + sb);
		s = sb.multiply(ri);
		s = s.mod(n);
		System.out.println("s is " + s);
		sm = m.modPow(d, n);
		System.out.println("sm is " + sm);
		
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

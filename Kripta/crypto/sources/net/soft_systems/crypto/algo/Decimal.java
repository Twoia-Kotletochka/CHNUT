/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.algo;
public class Decimal {
	/**
	 * Вычисляет a+b mod (2^n)
	 */
	static public long sum_mod(long a, long b, int n) {
		long mask = 0;
		for (int i = 0; i < n; i++) {
			mask <<= 1;
			mask += 1;
		}
		return (a + b) & mask;
	}
	/**
	 * Вычисляет a*b mod (2^n + 1)
	 */
	static public long mul_mod1(long a, long b, int n) {
		if (a == 0) { a = 1 << n; }
		if (b == 0) { b = 1 << n; }
		long k = a * b;
		long mask = 0;
		for (int i = 0; i < n; i++) {
			mask <<= 1;
			mask += 1;
		}
		long k1 = k & mask;
		long k2 = k >> n;
		return (k1 >= k2) ? (k1 - k2) : (k1 - k2 + (1 << n) + 1);
	}
	static public long set(byte[] data) {
		long res = 0;
		for (int i = 0; i < data.length; i++) { res = (res << 1) + (data[i]); }
		return res;
	}
	static public long[] setLongByBits(byte[] data) {
		int blen = data.length;
		int llen = blen / 64;
		if (llen % 64 != 0) llen++;
		long[] o = new long[llen];
		for (int i = 0, k = 0; i < llen; i++) {
			long h = 0;
			for (int j = 0; j < 64 && k < blen; j++, k++) {
				h <<= 1;
				h |= data[k];
			}
			o[i] = h;
		}
		return o;
	}
	/**
	 * Вычисляет аддитивное обращение a по модулю 2^n
	 */
	static public long rev_sum(long a, int n) {
		long mod = (1 << n);
		return mod - a;
	}
	/**
	 * Вычисляет мультикативное обращение a по модулю 2^n+1
	 */
	static public long rev_mul(long a, int n) {
		long mod = (1 << n) + 1;
		long x1, x2, x3;
		long y1, y2, y3;
		x1 = 1;
		x2 = 0;
		x3 = mod;
		y1 = 0;
		y2 = 1;
		y3 = a;
		// System.out.println(" : "+x1+" "+x2+" "+x3+" : "+y1+" "+y2+" "+y3);
		while (true) {
			if (y3 == 0) { return 0; }
			if (y3 == 1) {
				if (y2 < 0) { y2 += mod; }
				return y2;
			}
			long q = x3 / y3;
			long t1, t2, t3;
			t1 = x1 - q * y1;
			t2 = x2 - q * y2;
			t3 = x3 - q * y3;
			x1 = y1;
			x2 = y2;
			x3 = y3;
			y1 = t1;
			y2 = t2;
			y3 = t3;
			// System.out.println(q+" : "+x1+" "+x2+" "+x3+" : "+y1+" "+y2+" "+y3);
		}
	}
}


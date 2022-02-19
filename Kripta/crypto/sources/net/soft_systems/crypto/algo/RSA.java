package net.soft_systems.crypto.algo;
public class RSA {
	long n;
	long nPrime;
	long e;
	long d;
	public boolean setSecureKey(long p, long q) {
		if (!Numerical.isPrime(p) || !Numerical.isPrime(q)) return false;
		n = p * q;
		nPrime = (p - 1) * (q - 1);
		return true;
	}
	public long getN() { return n; }
	public long getFi() { return nPrime; }
	public boolean checkOpenKey(long e) {
		if (Numerical.gcd(e, nPrime) != 1) return false;
		d = Numerical.inverse(e, nPrime);
		return (e * d % (nPrime)) == 1;
	}
	public void setOpenKey(long e, long n) {
		this.n = n;
		this.e = e;
		d = Numerical.inverse(e, nPrime);
	}
	public long encode(long msg) { return Numerical.power(msg, e, n); }
	public long decode(long msg) { return Numerical.power(msg, d, n); }
	/**
	 * Sample of how RSA works. Note that n * n should not overflow a long This limits x*y to about 2^15.
	 */
	public static void main(String[] args) {
		long x       = 25000;
		long y       = 30000;
		long message = 37373737;
		long fi;
		long p, q, e;
		for (p = x; !Numerical.isPrime(p); p++);
		for (q = y + 2; !Numerical.isPrime(q); q++);
		RSA rsa = new RSA();
		if (rsa.setSecureKey(p, q)) {
			System.out.println("Secure key p=" + p + " q=" + q);
			fi = rsa.getFi();
			for (e = fi / 10; Numerical.gcd(e, fi) != 1; e++);
			if (rsa.checkOpenKey(e)) {
				rsa.setOpenKey(e, rsa.getN());
				System.out.println("Open key e=" + e + " n=" + rsa.getN());
				TimeUtil t = new TimeUtil();
				long code = rsa.encode(message);
				t.finish();
				System.out.println("Encode time " + t.millisec());
				t.start();
				long decode = rsa.decode(code);
				t.finish();
				System.out.println("Decode time " + t.millisec());
				System.out.println("Code: " + code);
				System.out.println("Decode: " + decode);
			}
			else System.out.println("Not set open key");
		}
		else System.out.println("Not set keys");
	}
}


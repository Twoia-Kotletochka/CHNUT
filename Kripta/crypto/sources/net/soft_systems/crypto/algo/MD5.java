package net.soft_systems.crypto.algo;
//import gnu.crypto.util.Util;
/**
 * <p>The MD5 message-digest algorithm takes as input a message of arbitrary
 * length and produces as output a 128-bit "fingerprint" or "message digest" of
 * the input. It is conjectured that it is computationally infeasible to
 * produce two messages having the same message digest, or to produce any
 * message having a given prespecified target message digest.</p> <p>References:</p> <ol>
 * <li>The <a href="http://www.ietf.org/rfc/rfc1321.txt">MD5</a> Message- Digest Algorithm.<br>
 * R. Rivest.</li> </ol>
 * @version $Revision: 1.4 $
 */
public class MD5 extends BaseHash {
	// Constants and variables
	// -------------------------------------------------------------------------
	private static final int BLOCK_SIZE = 64; // inner block size in bytes
	private static final String DIGEST0 = "D41D8CD98F00B204E9800998ECF8427E";
	/**
	 * caches the result of the correctness test, once executed.
	 */
	private static Boolean valid;
	private final int[] X = new int[16];
	/**
	 * 128-bit interim result.
	 */
	private int h0, h1, h2, h3;
	// Constructor(s)
	// -------------------------------------------------------------------------
	/**
	 * Trivial 0-arguments constructor.
	 */
	public MD5() { super(16, BLOCK_SIZE); }
	/**
	 * <p>Private constructor for cloning purposes.</p>
	 * @param md the instance to clone.
	 */
	private MD5(MD5 md) {
		this();
		this.h0 = md.h0;
		this.h1 = md.h1;
		this.h2 = md.h2;
		this.h3 = md.h3;
		this.count = md.count;
		this.buffer = (byte[]) md.buffer.clone();
	}
	// Class methods
	// -------------------------------------------------------------------------
	// Instance methods
	// -------------------------------------------------------------------------
	// Implementation of concrete methods in BaseHash --------------------------
	protected void transform(byte[] in, int offset) {
		for (int i = 0; i < 16; i++) {
			X[i] = (in[offset++] & 0xFF) | (in[offset++] & 0xFF) << 8 | (in[offset++] & 0xFF) << 16 |
				in[offset++] << 24;
		}
		int A = h0;
		int B = h1;
		int C = h2;
		int D = h3;
		// hex constants are from md5.c in FSF Gnu Privacy Guard 0.9.2
		// round 1
		A = FF(A, B, C, D, X[0], 7, 0xD76AA478);
		D = FF(D, A, B, C, X[1], 12, 0xE8C7B756);
		C = FF(C, D, A, B, X[2], 17, 0x242070DB);
		B = FF(B, C, D, A, X[3], 22, 0xC1BDCEEE);
		A = FF(A, B, C, D, X[4], 7, 0xF57C0FAF);
		D = FF(D, A, B, C, X[5], 12, 0x4787C62A);
		C = FF(C, D, A, B, X[6], 17, 0xA8304613);
		B = FF(B, C, D, A, X[7], 22, 0xFD469501);
		A = FF(A, B, C, D, X[8], 7, 0x698098D8);
		D = FF(D, A, B, C, X[9], 12, 0x8B44F7AF);
		C = FF(C, D, A, B, X[10], 17, 0xFFFF5BB1);
		B = FF(B, C, D, A, X[11], 22, 0x895CD7BE);
		A = FF(A, B, C, D, X[12], 7, 0x6B901122);
		D = FF(D, A, B, C, X[13], 12, 0xFD987193);
		C = FF(C, D, A, B, X[14], 17, 0xA679438E);
		B = FF(B, C, D, A, X[15], 22, 0x49B40821);
		// round 2
		A = GG(A, B, C, D, X[1], 5, 0xF61E2562);
		D = GG(D, A, B, C, X[6], 9, 0xC040B340);
		C = GG(C, D, A, B, X[11], 14, 0x265E5A51);
		B = GG(B, C, D, A, X[0], 20, 0xE9B6C7AA);
		A = GG(A, B, C, D, X[5], 5, 0xD62F105D);
		D = GG(D, A, B, C, X[10], 9, 0x02441453);
		C = GG(C, D, A, B, X[15], 14, 0xD8A1E681);
		B = GG(B, C, D, A, X[4], 20, 0xE7D3FBC8);
		A = GG(A, B, C, D, X[9], 5, 0x21E1CDE6);
		D = GG(D, A, B, C, X[14], 9, 0xC33707D6);
		C = GG(C, D, A, B, X[3], 14, 0xF4D50D87);
		B = GG(B, C, D, A, X[8], 20, 0x455A14ED);
		A = GG(A, B, C, D, X[13], 5, 0xA9E3E905);
		D = GG(D, A, B, C, X[2], 9, 0xFCEFA3F8);
		C = GG(C, D, A, B, X[7], 14, 0x676F02D9);
		B = GG(B, C, D, A, X[12], 20, 0x8D2A4C8A);
		// round 3
		A = HH(A, B, C, D, X[5], 4, 0xFFFA3942);
		D = HH(D, A, B, C, X[8], 11, 0x8771F681);
		C = HH(C, D, A, B, X[11], 16, 0x6D9D6122);
		B = HH(B, C, D, A, X[14], 23, 0xFDE5380C);
		A = HH(A, B, C, D, X[1], 4, 0xA4BEEA44);
		D = HH(D, A, B, C, X[4], 11, 0x4BDECFA9);
		C = HH(C, D, A, B, X[7], 16, 0xF6BB4B60);
		B = HH(B, C, D, A, X[10], 23, 0xBEBFBC70);
		A = HH(A, B, C, D, X[13], 4, 0x289B7EC6);
		D = HH(D, A, B, C, X[0], 11, 0xEAA127FA);
		C = HH(C, D, A, B, X[3], 16, 0xD4EF3085);
		B = HH(B, C, D, A, X[6], 23, 0x04881D05);
		A = HH(A, B, C, D, X[9], 4, 0xD9D4D039);
		D = HH(D, A, B, C, X[12], 11, 0xE6DB99E5);
		C = HH(C, D, A, B, X[15], 16, 0x1FA27CF8);
		B = HH(B, C, D, A, X[2], 23, 0xC4AC5665);
		// round 4
		A = II(A, B, C, D, X[0], 6, 0xF4292244);
		D = II(D, A, B, C, X[7], 10, 0x432AFF97);
		C = II(C, D, A, B, X[14], 15, 0xAB9423A7);
		B = II(B, C, D, A, X[5], 21, 0xFC93A039);
		A = II(A, B, C, D, X[12], 6, 0x655B59C3);
		D = II(D, A, B, C, X[3], 10, 0x8F0CCC92);
		C = II(C, D, A, B, X[10], 15, 0xFFEFF47D);
		B = II(B, C, D, A, X[1], 21, 0x85845dd1);
		A = II(A, B, C, D, X[8], 6, 0x6FA87E4F);
		D = II(D, A, B, C, X[15], 10, 0xFE2CE6E0);
		C = II(C, D, A, B, X[6], 15, 0xA3014314);
		B = II(B, C, D, A, X[13], 21, 0x4E0811A1);
		A = II(A, B, C, D, X[4], 6, 0xF7537E82);
		D = II(D, A, B, C, X[11], 10, 0xBD3AF235);
		C = II(C, D, A, B, X[2], 15, 0x2AD7D2BB);
		B = II(B, C, D, A, X[9], 21, 0xEB86D391);
		h0 += A;
		h1 += B;
		h2 += C;
		h3 += D;
	}
	protected byte[] padBuffer() {
		int n         = (int)(count % BLOCK_SIZE);
		int padding   = (n < 56) ? (56 - n) : (120 - n);
		byte[] result = new byte[padding + 8];
		// padding is always binary 1 followed by binary 0s
		result[0] = (byte)0x80;
		// save number of bits, casting the long to an array of 8 bytes
		long bits = count << 3;
		result[padding++] = (byte)bits;
		result[padding++] = (byte)(bits >>> 8);
		result[padding++] = (byte)(bits >>> 16);
		result[padding++] = (byte)(bits >>> 24);
		result[padding++] = (byte)(bits >>> 32);
		result[padding++] = (byte)(bits >>> 40);
		result[padding++] = (byte)(bits >>> 48);
		result[padding] = (byte)(bits >>> 56);
		return result;
	}
	protected byte[] getResult() {
		byte[] result = new byte[] {
			(byte)h0, (byte)(h0 >>> 8), (byte)(h0 >>> 16), (byte)(h0 >>> 24), (byte)h1, (byte)(h1 >>> 8), (byte)(h1 >>> 16),
				(byte)(h1 >>> 24), (byte)h2, (byte)(h2 >>> 8), (byte)(h2 >>> 16), (byte)(h2 >>> 24), (byte)h3, (byte)(h3 >>> 8),
				(byte)(h3 >>> 16), (byte)(h3 >>> 24)
		};
		return result;
	}
	protected void resetContext() {
		// magic MD5/RIPEMD128 initialisation constants
		h0 = 0x67452301;
		h1 = 0xEFCDAB89;
		h2 = 0x98BADCFE;
		h3 = 0x10325476;
	}
	// helper methods ----------------------------------------------------------
	private static final int FF(int a, int b, int c, int d, int k, int s, int i) {
		a += ((b & c) | (~b & d)) + k + i;
		return b + (a << s | a >>> -s);
	}
	private static final int GG(int a, int b, int c, int d, int k, int s, int i) {
		a += ((b & d) | (c & ~d)) + k + i;
		return b + (a << s | a >>> -s);
	}
	private static final int HH(int a, int b, int c, int d, int k, int s, int i) {
		a += (b ^ c ^ d) + k + i;
		return b + (a << s | a >>> -s);
	}
	private static final int II(int a, int b, int c, int d, int k, int s, int i) {
		a += (c ^ (b | ~d)) + k + i;
		return b + (a << s | a >>> -s);
	}
	static public byte[] convertBytes(byte d[]) {
		int len = d.length;
		byte o[] = new byte[len * 8];
		for (int i = 0, k = 0; i < len; i++) {
			for (int j = 0, mask = 0x80; j < 8; j++, mask >>= 1, k++) { o[k] = (byte)((d[i] & mask) >> (7 - j)); }
		}
		return o;
	}
	static public byte[] digest(String s) {
		MD5 md5 = new MD5();
		byte b[] = s.getBytes();
		md5.update(b, 0, b.length);
		return convertBytes(md5.digest());
	}
	static public byte[] digest(byte b[]) {
		MD5 md5 = new MD5();
		md5.update(b, 0, b.length);
		return convertBytes(md5.digest());
	}
}


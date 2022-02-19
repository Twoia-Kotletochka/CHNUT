/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.algo;
public class Binary {
	static public String toBinString(byte[] data) {
		if (data == null) { return null; }
		String res = "";
		for (int i = 0, left = data.length; i < data.length; i++) {
			res += data[i];
			left--;
			if ((left & 7) == 0) { res += " "; }
		}
		return res;
	}
	static public char byteToHex(byte val) {
		if (val < 10) { return (char)((byte) '0' + val); }
		else { return (char)((byte) 'A' + (val - 10)); }
	}
	static public String toHexString(byte[] data) {
		if (data == null) { return null; }
		String res   = "";
		int c        = data.length / 4;
		int firstLen = data.length % 4;
		byte val;
		if (firstLen > 0) {
			val = 0;
			for (int i = 0; i < firstLen; i++) {
				val <<= 1;
				val |= data[i];
			}
			res += byteToHex(val);
		}
		for (int i = 0, left = c, ibit = firstLen; i < c; i++) {
			val = 0;
			for (int j = 0; j < 4; j++, ibit++) {
				val <<= 1;
				val |= data[ibit];
			}
			if (i != 0 || firstLen != 0) {
				if ((left & 3) == 0) { res += " "; }
			}
			res += byteToHex(val);
			left--;
		}
		return res;
	}
	static public String toString(byte[] data) {
		if (data == null) { return null; }
		String res   = "";
		int c        = data.length / 16;
		int firstLen = data.length % 16;
		int val;
		if (firstLen > 0) {
			val = 0;
			for (int i = 0; i < firstLen; i++) {
				val <<= 1;
				val |= data[i];
			}
			res += (char)val;
		}
		for (int i = 0, left = c, ibit = firstLen; i < c; i++) {
			val = 0;
			for (int j = 0; j < 16; j++, ibit++) {
				val <<= 1;
				val |= data[ibit];
			}
			res += (char)val;
			left--;
		}
		return res;
	}
	static public byte[] setFromBinary(String binString) {
		int len     = binString.length();
		byte data[] = new byte[len];
		int j       = 0;
		byte val;
		for (int i = 0; i < len; i++) {
			try {
				val = Byte.valueOf("" + binString.charAt(i)).byteValue();
				if (val == 0 || val == 1) { data[j++] = val; }
			}
			catch (NumberFormatException ex) { }
		}
		if (data.length != j) {
			byte resdata[] = new byte[j];
			for (int i = 0; i < j; i++) { resdata[i] = data[i]; }
			return resdata;
		}
		else { return data; }
	}
	static public byte[] setFromHex(String hexString) {
		int len         = hexString.length();
		byte hexData[]  = new byte[len];
		String upString = hexString.toUpperCase();
		byte val;
		char c;
		int j = 0;
		for (int i = 0; i < len; i++) {
			try {
				c = upString.charAt(i);
				if (c >= '0' && c <= '9') {
					val = (byte)((byte)c - (byte) '0');
					hexData[j++] = val;
				}
				else if (c >= 'A' && c <= 'F') {
					val = (byte)((byte)c - (byte) 'A');
					hexData[j++] = (byte)(val + 10);
				}
			}
			catch (NumberFormatException ex) { }
		}
		if (j > 0) {
			byte binData[] = new byte[j * 4];
			for (int i = 0, l = 0; i < j; i++) {
				val = hexData[i];
				for (int k = 0, mask = 0x8; k < 4; k++, l++, mask >>= 1) {
					binData[l] = (byte)((val & mask) >> (3 - k));
				}
			}
			return binData;
		}
		else { return null; }
	}
	static public byte[] setFromString(String data) {
		int len = data.length();
		byte newdata[] = new byte[len * 16];
		int val;
		for (int i = 0, k = 0; i < len; i++) {
			val = data.charAt(i);
			for (int j = 0, mask = 0x8000; j < 16; j++, k++, mask >>= 1) {
				newdata[k] = (byte)((val & mask) >> (15 - j));
			}
		}
		return newdata;
	}
	static public byte[] setByBits(byte[] data) {
		int len = data.length;
		byte newdata[] = new byte[len];
		for (int i = 0; i < len; i++) { newdata[i] = data[i]; }
		return newdata;
	}
	static public byte[] setByInt(int num, int len) {
		byte newdata[] = new byte[len];
		int curNum = num;
		for (int i = len - 1; i >= 0; i--) {
			newdata[i] = (byte)(curNum & 1);
			curNum >>= 1;
		}
		return newdata;
	}
	static public byte[] setByLong(long num, int len) {
		byte newdata[] = new byte[len];
		long curNum = num;
		for (int i = len - 1; i >= 0; i--) {
			newdata[i] = (byte)(curNum & 1);
			curNum >>= 1;
		}
		return newdata;
	}
	static public byte[] setByLength(int len) {
		byte newdata[] = new byte[len];
		for (int i = 0; i < len; i++) { newdata[i] = 0; }
		return newdata;
	}
	static public byte[] left(byte[] data, int count) {
		if (count > data.length) { count = data.length; }
		byte newdata[] = new byte[count];
		for (int i = 0; i < count; i++) { newdata[i] = data[i]; }
		return newdata;
	}
	static public byte[] right(byte[] data, int count) {
		if (count > data.length) { count = data.length; }
		byte newdata[] = new byte[count];
		for (int i = data.length - 1, j = count - 1; i >= data.length - count; i--, j--)
			{ newdata[j] = data[i]; }
		return newdata;
	}
	static public byte[] middle(byte[] data, int start, int count) {
		if (start + count > data.length) {
			count = data.length - start;
			if (count < 0) { count = 0; }
		}
		byte newdata[] = new byte[count];
		for (int i = 0; i < count; i++) { newdata[i] = data[i + start]; }
		return newdata;
	}
	static public byte[] xor(byte[] data, byte[] data1) {
		int len = (data.length > data1.length) ? data1.length : data.length;
		byte newdata[] = new byte[len];
		for (int i = 0; i < len; i++) { newdata[i] = (byte)((data[i] == data1[i]) ? 0 : 1); }
		return newdata;
	}
	static public byte[] and(byte[] data, byte[] data1) {
		int len = (data.length > data1.length) ? data1.length : data.length;
		byte newdata[] = new byte[len];
		for (int i = 0; i < len; i++) { newdata[i] = (byte)((data[i] == 1 && data1[i] == 1) ? 1 : 0); }
		return newdata;
	}
	static public byte[] or(byte[] data, byte[] data1) {
		int len = (data.length > data1.length) ? data1.length : data.length;
		byte newdata[] = new byte[len];
		for (int i = 0; i < len; i++) { newdata[i] = (byte)((data[i] == 0 && data1[i] == 0) ? 0 : 1); }
		return newdata;
	}
	static public byte[] invert(byte[] data) {
		int len = data.length;
		byte newdata[] = new byte[len];
		for (int i = 0; i < len; i++) { newdata[i] = (byte)((data[i] == 1) ? 0 : 1); }
		return newdata;
	}
	static public byte[] shiftright(byte[] data, int count) {
		int len = data.length;
		byte newdata[] = new byte[len];
		int j;
		for (int i = 0; i < len; i++) {
			j = i - count;
			if (j < 0) { j = len + j; }
			newdata[i] = data[j];
		}
		return newdata;
	}
	static public byte[] shiftleft(byte[] data, int count) {
		int len = data.length;
		byte newdata[] = new byte[len];
		int j;
		for (int i = 0; i < len; i++) {
			j = i + count;
			if (j >= len) { j = j - len; }
			newdata[i] = data[j];
		}
		return newdata;
	}
	static public byte[] random(int len) {
		byte newdata[] = new byte[len];
		for (int i = 0; i < len; i++) { newdata[i] = (byte)((Math.random() < 0.5) ? 1 : 0); }
		return newdata;
	}
	static public void copy(byte[] dst, byte[] src, int dst_start, int src_start, int count) {
		if (dst_start + count > dst.length) { count = dst.length - dst_start; }
		if (src_start + count > src.length) { count = src.length - src_start; }
		for (int i = 0; i < count; i++) { dst[dst_start + i] = src[src_start + i]; }
	}
	static public void copy(byte[] dst, byte[] src, int dst_start, int src_start) {
		int count = src.length - src_start;
		if (dst_start + count > dst.length) { count = dst.length - dst_start; }
		for (int i = 0; i < count; i++) { dst[dst_start + i] = src[src_start + i]; }
	}
	static public void copy(byte[] dst, byte[] src, int dst_start) {
		int count = src.length;
		if (dst_start + count > dst.length) { count = dst.length - dst_start; }
		for (int i = 0; i < count; i++) { dst[dst_start + i] = src[i]; }
	}
	static public boolean equals(byte val1[], byte val2[]) {
		if (val1.length != val2.length) return false;
		for (int i = 0; i < val1.length; i++) { if (val1[i] != val2[i]) return false; }
		return true;
	}
}


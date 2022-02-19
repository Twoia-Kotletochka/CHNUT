/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.algo;
public class DES extends BlockCipher {
	static public void print(String s) { System.out.print(s); }
	static public void println(String s) { print(s + "\n"); }
	static public byte[] s_func(byte[] in, Table2Transform s) {
		int row = ((in[0] << 1) | (in[5]));
		int col = ((in[1] << 3) | (in[2] << 2) | (in[3] << 1) | (in[4]));
		return s.transform(row, col);
	}
	static public Transform g = Transform.orderTable("57	49	41	33	25	17	9  " +
		"1	58	50	42	34	26	18 " + "10	2	59	51	43	35	27 " + "19	11	3	60	52	44	36 " +
		"63	55	47	39	31	23	15 " + "7	62	54	46	38	30	22 " + "14	6	61	53	45	37	29 " +
		"21	13	5	28	20	12	4 ");
	static public Transform h = Transform.orderTable("14	17	11	24	1	5 " + "3	28	15	6	21	10 " +
		"23	19	12	4	26	8 " + "16	7	27	20	13	2 " + "41	52	31	37	47	55 " + "30	40	51	45	33	48 " +
		"44	49	39	56	34	53 " + "46	42	50	36	29	32 ");
	static public Transform e = Transform.orderTable("32  1  2  3  4  5 " + " 4  5  6  7  8  9 " +
		" 8  9 10 11 12 13 " + "12 13 14 15 16 17 " + "16 17 18 19 20 21 " + "20 21 22 23 24 25 " +
		"24 25 26 27 28 29 " + "28 29 30 31 32  1");
	static public Transform p = Transform.orderTable("16  7 20 21 " + "29 12 28 17 " + " 1 15 23 26 " +
		" 5 18 31 10 " + " 2  8 24 14 " + "32 27  3  9 " + "19 13 30  6 " + "22 11  4 25");
	static public Table2Transform s1 = Transform.table2(4, 16,
		"14  4 13  1  2 15 11  8  3 10  6 12  5  9  0  7 " + " 0 15  7  4 14  2 13  1 10  6 12 11  9  5  3  8 " +
		" 4  1  4  8 13  6  2 11 15 12  9  7  3 10  5  0 " + "15 12  8  2  4  9  1  7  5 11  3 14 10  0  6 13");
	static public Table2Transform s2 = Transform.table2(4, 16,
		"15  1  8 14  6 11  3  4  9  7  2 13 12  0  5 10 " + " 3 13  4  7 15  2  8 14 12  0  1 10  6  9 11  5 " +
		" 0 14  7 11 10  4 13  1  5  8 12  6  9  3  2 15 " +
		"13	 8 10  1  3 15  4  2 11  6  7 12  0  5 14  9 ");
	static public Table2Transform s3 = Transform.table2(4, 16,
		"10	0	9	14	6	3	15	5	1	13	12	7	11	4	2	8 " + "13	7	0	9	3	4	6	10	2	8	5	14	12	11	15	1 " +
		"13	6	4	9	8	15	3	0	11	1	2	12	5	10	14	7 " + "1	10	13	0	6	9	8	7	4	15	14	3	11	5	2	12");
	static public Table2Transform s4 = Transform.table2(4, 16,
		"7	13	14	3	0	6	9	10	1	2	8	5	11	12	4	15 " + "13	8	11	5	6	15	0	3	4	7	2	12	1	10	14	9 " +
		"10	6	9	0	12	11	7	13	15	1	3	14	5	2	8	4 " + "3	15	0	6	10	1	13	8	9	4	5	11	12	7	2	14");
	static public Table2Transform s5 = Transform.table2(4, 16,
		"2	12	4	1	7	10	11	6	8	5	3	15	13	0	14	9 " + "14	11	2	12	4	7	13	1	5	0	15	10	3	9	8	6 " +
		"4	2	1	11	10	13	7	8	15	9	12	5	6	3	0	14 " + "11	8	12	7	1	14	2	13	6	15	0	9	10	4	5	3");
	static public Table2Transform s6 = Transform.table2(4, 16,
		"12	1	10	15	9	2	6	8	0	13	3	4	14	7	5	11 " + "10	15	4	2	7	12	9	5	6	1	13	14	0	11	3	8 " +
		"9	14	15	5	2	8	12	3	7	0	4	10	1	13 11	6 " + "4	3	2	12	9	5	15	10	11	14	1	7	6	0	8	13");
	static public Table2Transform s7 = Transform.table2(4, 16,
		"4	11	2	14	15	0	8	13	3	12	9	7	5	10	6	1 " + "13	0	11	7	4	9	1	10	14	3	5	12	2	15	8	6 " +
		"1	4	11	13	12	3	7	14	10	15	6	8	0	5	9	2 " + "6	11	13	8	1	4	10	7	9	5	0	15	14	2	3	12");
	static public Table2Transform s8 = Transform.table2(4, 16,
		"13	2	8	4	6	15	11	1	10	9	3	14	5	0	12	7 " + "1	15	13	8	10	3	7	4	12	5	6	11	0	14	9	2 " +
		"7	11	4	1	9	12	14	2	0	6	10	13	15	3	5	8 " + "2	1	14	7	4	10	8	13	15	12	9	0	3	5	6	11");
	static public Transform ip = Transform.orderTable("58 50 42 34 26 18 10  2 " + "60 52 44 36 28 20 12  4 " +
		"62 54 46 38 30 22 14  6 " + "64 56 48 40 32 24 16  8 " + "57 49 41 33 25 17  9  1 " +
		"59 51 43 35 27 19 11  3 " + "61 53 45 37 29 21 13  5 " + "63 55 47 39 31 23 15  7");
	static public Transform notip = Transform.orderTable("40 8 48 16 56 24 64 32 " +
		"39 7 47 15 55 23 63 31 " + "38 6 46 14 54 22 62 30 " + "37 5 45 13 53 21 61 29 " +
		"36 4 44 12 52 20 60 28 " + "35 3 43 11 51 19 59 27 " + "34 2 42 10 50 18 58 26 " +
		"33 1 41  9 49 17 57 25");
	static public DecimalVector shifts =
		DecimalVector.initFromDecimalsInString("1 1 2 2 2 2 2 2 1 2 2 2 2 2 2 1");
	static public BinaryVector init_keys(byte[] key) {
		byte after_g[]  = g.transform(key);
		byte c[]        = Binary.left(after_g, 28);
		byte d[]        = Binary.right(after_g, 28);
		byte before_h[] = Binary.setByLength(56);
		byte after_h[];
		BinaryVector keys = BinaryVector.init();
		for (int i = 0; i < 16; i++) {
			c = Binary.shiftleft(c, shifts.get(i));
			d = Binary.shiftleft(d, shifts.get(i));
			Binary.copy(before_h, c, 0);
			Binary.copy(before_h, d, 28);
			after_h = h.transform(before_h);
			keys.put(after_h, i);
		}
		return keys;
	}
	static public byte[] f_func(byte[] r, byte[] k) {
		byte a[]      = e.transform(r);
		byte b[]      = Binary.xor(a, k);
		byte in_s1[]  = Binary.middle(b, 0, 6);
		byte in_s2[]  = Binary.middle(b, 6, 6);
		byte in_s3[]  = Binary.middle(b, 12, 6);
		byte in_s4[]  = Binary.middle(b, 18, 6);
		byte in_s5[]  = Binary.middle(b, 24, 6);
		byte in_s6[]  = Binary.middle(b, 30, 6);
		byte in_s7[]  = Binary.middle(b, 36, 6);
		byte in_s8[]  = Binary.middle(b, 42, 6);
		byte out_s1[] = s_func(in_s1, s1);
		byte out_s2[] = s_func(in_s2, s2);
		byte out_s3[] = s_func(in_s3, s3);
		byte out_s4[] = s_func(in_s4, s4);
		byte out_s5[] = s_func(in_s5, s5);
		byte out_s6[] = s_func(in_s6, s6);
		byte out_s7[] = s_func(in_s7, s7);
		byte out_s8[] = s_func(in_s8, s8);
		byte out[]    = Binary.setByLength(32);
		Binary.copy(out, out_s1, 0);
		Binary.copy(out, out_s2, 4);
		Binary.copy(out, out_s3, 8);
		Binary.copy(out, out_s4, 12);
		Binary.copy(out, out_s5, 16);
		Binary.copy(out, out_s6, 20);
		Binary.copy(out, out_s7, 24);
		Binary.copy(out, out_s8, 28);
		byte c[] = p.transform(out);
		return c;
	}
	private BinaryVector keys;
	public boolean setKey(byte key[]) {
		keys = init_keys(key);
		return true;
	}
	public byte[] encodeBlock(byte data[]) {
		byte transformedData[] = ip.transform(data);
		// 16 cycles of encoding
		byte l[] = Binary.left(transformedData, 32);
		byte r[] = Binary.right(transformedData, 32);
		byte l_1[], r_1[];
		byte sub_key[];
		for (int i = 0; i < 16; i++) {
			l_1 = r;
			sub_key = keys.get(i);
			r_1 = Binary.xor(l, f_func(r, sub_key));
			l = l_1;
			r = r_1;
		}
		byte afterCyclesData[] = Binary.setByLength(64);
		Binary.copy(afterCyclesData, r, 0);
		Binary.copy(afterCyclesData, l, 32);
		// not ip transformation
		byte output[] = notip.transform(afterCyclesData);
		return output;
	}
	static public byte[] encode(byte[] key, byte[] data) {
		BinaryVector keys = init_keys(key);
		byte transformedData[] = ip.transform(data);
		// 16 cycles of encoding
		byte l[] = Binary.left(transformedData, 32);
		byte r[] = Binary.right(transformedData, 32);
		byte l_1[], r_1[];
		byte sub_key[];
		for (int i = 0; i < 16; i++) {
			l_1 = r;
			sub_key = keys.get(i);
			r_1 = Binary.xor(l, f_func(r, sub_key));
			l = l_1;
			r = r_1;
		}
		byte afterCyclesData[] = Binary.setByLength(64);
		Binary.copy(afterCyclesData, r, 0);
		Binary.copy(afterCyclesData, l, 32);
		// not ip transformation
		byte output[] = notip.transform(afterCyclesData);
		return output;
	}
	public static void main(String[] argv) {
		byte key[]  = Binary.random(64);
		byte data[] = Binary.random(70);
		DES des     = new DES();
		des.setKey(key);
		byte output[] = des.encodeData(data, BlockCipher.MODE_ECB);
		byte rdata[] = des.decodeData(output, BlockCipher.MODE_ECB);
		println("  key  " + Binary.toHexString(key));
		println("  data " + Binary.toHexString(data));
		println(" rdata " + Binary.toHexString(rdata));
		println("  enc  " + Binary.toHexString(output));
	}
	public byte[] decodeBlock(byte data[]) {
		byte transformedData[] = notip.reverse(data);
		// 16 cycles of encoding
		byte l[] = Binary.right(transformedData, 32);
		byte r[] = Binary.left(transformedData, 32);
		byte l_1[], r_1[];
		byte sub_key[];
		for (int i = 15; i >= 0; i--) {
			r_1 = l;
			sub_key = keys.get(i);
			l_1 = Binary.xor(r, f_func(l, sub_key));
			l = l_1;
			r = r_1;
			// println(" decode "+i+": "+Binary.toString(r)+" "+Binary.toString(l));
		}
		byte afterCyclesData[] = Binary.setByLength(64);
		Binary.copy(afterCyclesData, l, 0);
		Binary.copy(afterCyclesData, r, 32);
		// not ip transformation
		byte output[] = ip.reverse(afterCyclesData);
		return output;
	}
	static public byte[] decode(byte[] key, byte[] data) {
		BinaryVector keys = init_keys(key);
		byte transformedData[] = notip.reverse(data);
		// 16 cycles of encoding
		byte l[] = Binary.right(transformedData, 32);
		byte r[] = Binary.left(transformedData, 32);
		byte l_1[], r_1[];
		byte sub_key[];
		for (int i = 15; i >= 0; i--) {
			r_1 = l;
			sub_key = keys.get(i);
			l_1 = Binary.xor(r, f_func(l, sub_key));
			l = l_1;
			r = r_1;
			// println(" decode "+i+": "+Binary.toString(r)+" "+Binary.toString(l));
		}
		byte afterCyclesData[] = Binary.setByLength(64);
		Binary.copy(afterCyclesData, l, 0);
		Binary.copy(afterCyclesData, r, 32);
		// not ip transformation
		byte output[] = ip.reverse(afterCyclesData);
		return output;
	}
	public int getBlockLength() { return 64; }
}


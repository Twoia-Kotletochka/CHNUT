/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.algo;
public class IDEA extends BlockCipher {
	static long[] MA(long f1, long f2, long z1, long z2) {
		long k1  = Decimal.mul_mod1(f1, z1, 16);
		long k2  = Decimal.sum_mod(k1, f2, 16);
		long[] G = new long[2];
		G[1] = Decimal.mul_mod1(k2, z2, 16);
		G[0] = Decimal.sum_mod(k1, G[0], 16);
		return G;
	}
	static protected long[] reverseKeys(long[] zkeys) {
		long[] keys = new long[52];
		keys[0] = Decimal.rev_mul(zkeys[48], 16);
		keys[1] = Decimal.rev_sum(zkeys[49], 16);
		keys[2] = Decimal.rev_sum(zkeys[50], 16);
		keys[3] = Decimal.rev_mul(zkeys[51], 16);
		keys[4] = zkeys[46];
		keys[5] = zkeys[47];
		keys[6] = Decimal.rev_mul(zkeys[42], 16);
		keys[7] = Decimal.rev_sum(zkeys[44], 16);
		keys[8] = Decimal.rev_sum(zkeys[43], 16);
		keys[9] = Decimal.rev_mul(zkeys[45], 16);
		keys[10] = zkeys[40];
		keys[11] = zkeys[41];
		keys[12] = Decimal.rev_mul(zkeys[36], 16);
		keys[13] = Decimal.rev_sum(zkeys[38], 16);
		keys[14] = Decimal.rev_sum(zkeys[37], 16);
		keys[15] = Decimal.rev_mul(zkeys[39], 16);
		keys[16] = zkeys[34];
		keys[17] = zkeys[35];
		keys[18] = Decimal.rev_mul(zkeys[30], 16);
		keys[19] = Decimal.rev_sum(zkeys[32], 16);
		keys[20] = Decimal.rev_sum(zkeys[31], 16);
		keys[21] = Decimal.rev_mul(zkeys[33], 16);
		keys[22] = zkeys[28];
		keys[23] = zkeys[29];
		keys[24] = Decimal.rev_mul(zkeys[24], 16);
		keys[25] = Decimal.rev_sum(zkeys[26], 16);
		keys[26] = Decimal.rev_sum(zkeys[25], 16);
		keys[27] = Decimal.rev_mul(zkeys[27], 16);
		keys[28] = zkeys[22];
		keys[29] = zkeys[23];
		keys[30] = Decimal.rev_mul(zkeys[18], 16);
		keys[31] = Decimal.rev_sum(zkeys[20], 16);
		keys[32] = Decimal.rev_sum(zkeys[19], 16);
		keys[33] = Decimal.rev_mul(zkeys[21], 16);
		keys[34] = zkeys[16];
		keys[35] = zkeys[17];
		keys[36] = Decimal.rev_mul(zkeys[12], 16);
		keys[37] = Decimal.rev_sum(zkeys[14], 16);
		keys[38] = Decimal.rev_sum(zkeys[13], 16);
		keys[39] = Decimal.rev_mul(zkeys[15], 16);
		keys[40] = zkeys[10];
		keys[41] = zkeys[11];
		keys[42] = Decimal.rev_mul(zkeys[6], 16);
		keys[43] = Decimal.rev_sum(zkeys[8], 16);
		keys[44] = Decimal.rev_sum(zkeys[7], 16);
		keys[45] = Decimal.rev_mul(zkeys[9], 16);
		keys[46] = zkeys[4];
		keys[47] = zkeys[5];
		keys[48] = Decimal.rev_mul(zkeys[0], 16);
		keys[49] = Decimal.rev_sum(zkeys[1], 16);
		keys[50] = Decimal.rev_sum(zkeys[2], 16);
		keys[51] = Decimal.rev_mul(zkeys[3], 16);
		return keys;
	}
	static protected long[] makeKeys(byte[] key) {
		byte[] ckey = new byte[128];
		Binary.copy(ckey, key, 0);
		long[] keys = new long[52];
		for (int i = 0, ikey = 0; i < 7; i++) {
			for (int j = 0, ibit = 0; j < 8; j++, ibit += 16, ikey++) {
				if (ikey < 52) {
					byte[] subkey = Binary.middle(ckey, ibit, 16);
					// Binary.print(subkey);
					keys[ikey] = Decimal.set(subkey);
				}
				else { break; }
			}
			ckey = Binary.shiftleft(ckey, 25);
			// System.out.println("");
		}
		return keys;
	}
	static protected byte[] doRounds(long[] keys, byte[] data) {
		// разделение входного вектора на 4 вектора w
		long[] w = new long[4];
		for (int i = 0, ibit = 0; i < 4; i++, ibit += 16) {
			byte[] subdata = Binary.middle(data, ibit, 16);
			// Binary.print(subdata);
			w[i] = Decimal.set(subdata);
		}
		// раунды шифрования
		long[] g;
		for (int round = 0, firstkey = 0; round < 8; round++, firstkey += 6) {
			long k1 = Decimal.mul_mod1(w[0], keys[firstkey], 16);
			long k2 = Decimal.sum_mod(w[1], keys[firstkey + 1], 16);
			long k3 = Decimal.sum_mod(w[2], keys[firstkey + 2], 16);
			long k4 = Decimal.mul_mod1(w[3], keys[firstkey + 3], 16);
			long k5 = k1 ^ k3;
			long k6 = k2 ^ k4;
			g = MA(k5, k6, keys[firstkey + 4], keys[firstkey + 5]);
			w[0] = k1 ^ g[1];
			w[1] = k3 ^ g[1];
			w[2] = k2 ^ g[0];
			w[3] = k4 ^ g[0];
		}
		// выходное преобразование
		long[] y = new long[4];
		y[0] = Decimal.mul_mod1(w[0], keys[48], 16);
		y[1] = Decimal.sum_mod(w[2], keys[49], 16);
		y[2] = Decimal.sum_mod(w[1], keys[50], 16);
		y[3] = Decimal.mul_mod1(w[3], keys[51], 16);
		byte[] out = Binary.setByLength(64);
		Binary.copy(out, Binary.setByLong(y[0], 16), 0);
		Binary.copy(out, Binary.setByLong(y[1], 16), 16);
		Binary.copy(out, Binary.setByLong(y[2], 16), 32);
		Binary.copy(out, Binary.setByLong(y[3], 16), 48);
		return out;
	}
	static public byte[] encode(byte[] key, byte[] data) { return doRounds(makeKeys(key), data); }
	static public byte[] decode(byte[] key, byte[] data) {
		long[] keys = reverseKeys(makeKeys(key));
		if (keys != null) { return doRounds(keys, data); }
		else { return null; }
	}
	private long[] encKeys;
	private long[] decKeys;
	public boolean setKey(byte[] key) {
		encKeys = makeKeys(key);
		decKeys = reverseKeys(encKeys);
		return decKeys != null;
	}
	public byte[] encodeBlock(byte[] data) { return doRounds(encKeys, data); }
	public byte[] decodeBlock(byte[] data) {
		if (decKeys != null) { return doRounds(decKeys, data); }
		else { return null; }
	}
/*		public static void main(String[] argv)
		{
			byte key[]=Binary.set("00110111 11000110 00101001 10101000 01101001 01111101 00011011 01000010 11000101 11110011 01110100 01111111 11100010 00111101 11100011 01111001");
			System.out.println("Key  :"+Binary.toString(key));
			byte data[]=Binary.random(8);
			IDEA idea=new IDEA();
			byte IV[]=Binary.random(64);
			idea.setIV(IV);
			if (idea.setKey(key))
			{
				byte out[]=idea.encodeData(data,BlockCipher.MODE_OFB);
				idea.resetFB();
				byte rout[]=idea.decodeData(out,BlockCipher.MODE_OFB);
				System.out.println("Enc  :"+Binary.toString(out));
				System.out.println("Data :"+Binary.toString(data));
				System.out.println("Dec  :"+Binary.toString(rout));
			}
		}
*/
/*			public static void main(String[] argv)
			{
				byte key[]=Binary.random(128);
				IDEA idea=new IDEA();
				byte IV[]=Binary.random(64);
				idea.setIV(IV);
				if (idea.setKey(key))
				{
					BinaryVector encData=new BinaryVector();
					BinaryVector dataVec=new BinaryVector();
					int n=5;
					for (int i=0;i<n;i++)
					{
						byte data[]=Binary.random(8);
						dataVec.put(data,i);
						byte out[]=idea.encodeData(data,BlockCipher.MODE_CFB);
						encData.put(out,i);
					}
					idea.resetFB();
					BinaryVector decData=new BinaryVector();
					for (int i=0;i<n;i++)
					{
						byte rout[]=idea.decodeData(encData.get(i),BlockCipher.MODE_CFB);
						decData.put(rout,i);
					}
					System.out.println("Enc  :");
					System.out.println(encData);
					System.out.println("Data :");
					System.out.println(dataVec);
					System.out.println("Dec  :");
					System.out.println(decData);
				}
			}
*/
	public static void main(String[] argv) {
		byte key[] = Binary.setFromBinary("10001000 01011100 10010000 01001010 10010001 11101100 01100011 01101010 10001011 11010100 01100111 01000100 00000100 10100101 10000011 00000101");
		byte data[] = Binary.random(10000);
		IDEA idea = new IDEA();
		byte IV[] = Binary.setFromBinary("00110111 11000110 00101001 10101000 01101001 01111101 00011011 01000010");
		idea.setIV(IV);
		if (idea.setKey(key)) {
			System.out.println("Source  " + Binary.toHexString(data));
			TimeUtil t = new TimeUtil();
			byte out[] = idea.encodeData(data, BlockCipher.MODE_CBC);
			t.finish();
			System.out.println("Time " + t.millisec());
			System.out.println("Encoded " + Binary.toHexString(out));
			t.start();
			byte rout[] = idea.decodeData(out, BlockCipher.MODE_CBC);
			t.finish();
			System.out.println("Time " + t.millisec());
			System.out.println("Decoded " + Binary.toHexString(rout));
		}
	}
	public int getBlockLength() { return 64; }
}


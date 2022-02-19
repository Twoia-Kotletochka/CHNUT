/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.algo;
abstract public class BlockCipher {
	public static final int MODE_ECB = 1;
	public static final int MODE_CBC = 2;
	public static final int MODE_CFB = 3;
	public static final int MODE_OFB = 4;
	abstract public boolean setKey(byte[] key);
	abstract public byte[] encodeBlock(byte data[]);
	abstract public byte[] decodeBlock(byte data[]);
	abstract public int getBlockLength();
	public byte[] encodeECB(byte data[]) {
		long blockCount = data.length / getBlockLength();
		int lastBlockSize = (int)(data.length - blockCount * getBlockLength());
		if (lastBlockSize > 0) { blockCount++; }
		byte out[] = new byte[(int)(blockCount * getBlockLength())];
		byte block[];
		byte block_out[];
		for (int iblock = 0, firstBit = 0; iblock < blockCount; iblock++, firstBit += getBlockLength()) {
			if (iblock == blockCount - 1 && lastBlockSize != 0) {
				block = Binary.setByLength(getBlockLength());
				Binary.copy(block, data, 0, firstBit, lastBlockSize);
			}
			else { block = Binary.middle(data, firstBit, getBlockLength()); }
			block_out = encodeBlock(block);
			Binary.copy(out, block_out, firstBit);
		}
		return out;
	}
	public byte[] decodeECB(byte data[]) {
		long blockCount = data.length / getBlockLength();
		byte out[] = new byte[(int)(blockCount * getBlockLength())];
		byte block[];
		byte block_out[];
		for (int iblock = 0, firstBit = 0; iblock < blockCount; iblock++, firstBit += getBlockLength()) {
			block = Binary.middle(data, firstBit, getBlockLength());
			block_out = decodeBlock(block);
			Binary.copy(out, block_out, firstBit);
		}
		return out;
	}
	public byte[] encodeCBC(byte data[]) {
		if (IV == null || IV.length != getBlockLength()) { return null; }
		long blockCount = data.length / getBlockLength();
		int lastBlockSize = (int)(data.length - blockCount * getBlockLength());
		if (lastBlockSize > 0) { blockCount++; }
		byte out[] = new byte[(int)(blockCount * getBlockLength())];
		byte block[];
		byte block_out[] = Binary.setByBits(IV);
		for (int iblock = 0, firstBit = 0; iblock < blockCount; iblock++, firstBit += getBlockLength()) {
			if (iblock == blockCount - 1 && lastBlockSize != 0) {
				block = Binary.setByLength(getBlockLength());
				Binary.copy(block, data, 0, firstBit, lastBlockSize);
			}
			else { block = Binary.middle(data, firstBit, getBlockLength()); }
			block = Binary.xor(block, block_out);
			block_out = encodeBlock(block);
			Binary.copy(out, block_out, firstBit);
		}
		return out;
	}
	public byte[] decodeCBC(byte data[]) {
		if (IV == null || IV.length != getBlockLength()) { return null; }
		long blockCount = data.length / getBlockLength();
		byte out[] = new byte[(int)(blockCount * getBlockLength())];
		byte block[];
		byte block_out[], last_data[] = Binary.setByBits(IV);
		for (int iblock = 0, firstBit = 0; iblock < blockCount; iblock++, firstBit += getBlockLength()) {
			block = Binary.middle(data, firstBit, getBlockLength());
			block_out = decodeBlock(block);
			block_out = Binary.xor(last_data, block_out);
			last_data = Binary.setByBits(block);
			Binary.copy(out, block_out, firstBit);
		}
		return out;
	}
	public byte[] encodeCFB(byte data[]) {
		if (iv == null || iv.length != getBlockLength()) { return null; }
		int len = data.length;
		if (len > getBlockLength()) { return null; }
		byte enc[]  = encodeBlock(iv);
		byte part[] = Binary.left(enc, len);
		byte out[]  = Binary.xor(part, data);
		iv = Binary.shiftleft(iv, len);
		Binary.copy(iv, out, getBlockLength() - len);
		return out;
	}
	public byte[] decodeCFB(byte data[]) {
		if (iv == null || iv.length != getBlockLength()) { return null; }
		int len = data.length;
		if (len > getBlockLength()) { return null; }
		byte enc[]  = encodeBlock(iv);
		byte part[] = Binary.left(enc, len);
		byte out[]  = Binary.xor(part, data);
		iv = Binary.shiftleft(iv, len);
		Binary.copy(iv, data, getBlockLength() - len);
		return out;
	}
	protected byte iv[];
	public void resetFB() { iv = Binary.setByBits(this.IV); }
	public byte[] encodeOFB(byte data[]) {
		if (iv == null || iv.length != getBlockLength()) { return null; }
		int len = data.length;
		if (len > getBlockLength()) { return null; }
		byte enc[]  = encodeBlock(iv);
		byte part[] = Binary.left(enc, len);
		byte out[]  = Binary.xor(part, data);
		iv = Binary.shiftleft(iv, len);
		Binary.copy(iv, part, getBlockLength() - len);
		return out;
	}
	public byte[] decodeOFB(byte data[]) {
		if (iv == null || iv.length != getBlockLength()) { return null; }
		int len = data.length;
		if (len > getBlockLength()) { return null; }
		byte enc[]  = encodeBlock(iv);
		byte part[] = Binary.left(enc, len);
		byte out[]  = Binary.xor(part, data);
		iv = Binary.shiftleft(iv, len);
		Binary.copy(iv, part, getBlockLength() - len);
		return out;
	}
	public byte[] encodeData(byte data[], int mode) {
		switch (mode) {
			case MODE_ECB:
				return encodeECB(data);
			case MODE_CBC:
				return encodeCBC(data);
			case MODE_CFB:
				return encodeCFB(data);
			case MODE_OFB:
				return encodeOFB(data);
			default:
				return encodeECB(data);
		}
	}
	public byte[] decodeData(byte data[], int mode) {
		switch (mode) {
			case MODE_ECB:
				return decodeECB(data);
			case MODE_CBC:
				return decodeCBC(data);
			case MODE_CFB:
				return decodeCFB(data);
			case MODE_OFB:
				return decodeOFB(data);
			default:
				return decodeECB(data);
		}
	}
	protected byte[] IV;
	public void setIV(byte IV[]) {
		this.IV = Binary.setByBits(IV);
		resetFB();
	}
}


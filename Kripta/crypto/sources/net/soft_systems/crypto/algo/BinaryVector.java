/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.algo;
import java.util.Vector;
public class BinaryVector {
	public String toHexString() {
		String res = "";
		for (int i = 0; i < elems.size(); i++) {
			res += "" + i + ": " + Binary.toHexString(get(i));
			if (i != elems.size() - 1) { res += "\n"; }
		}
		return res;
	}
	protected Vector elems = new Vector();
	public BinaryVector() { }
	public void put(byte data[], int index) {
		if (index >= elems.size()) { elems.setSize(index + 1); }
		elems.set(index, data);
	}
	public byte[] get(int index) {
		try {
			byte data[] = (byte[]) elems.elementAt(index);
			if (data == null) { data = new byte[0]; }
			return data;
		}
		catch (Exception ex) { return new byte[0]; }
	}
	static public BinaryVector init() { return new BinaryVector(); }
}


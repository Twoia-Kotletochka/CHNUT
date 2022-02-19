/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.algo;
import java.util.*;
public class DecimalVector {
	protected Vector elems = new Vector();
	public DecimalVector() { }
	public void setFromDecimalsInString(String data) {
		elems.clear();
		int cnt = 0;
		StringTokenizer tok = new StringTokenizer(data, ", \t\n\r\f");
		Integer num;
		while (tok.hasMoreTokens()) {
			try {
				num = Integer.valueOf((String)tok.nextToken());
				cnt++;
				elems.add(num);
			}
			catch (NumberFormatException ex) { }
		}
	}
	public void put(int data, int index) {
		if (index >= elems.size()) { elems.setSize(index + 1); }
		elems.set(index, new Integer(data));
	}
	public int get(int index) {
		try {
			Integer data = (Integer)elems.elementAt(index);
			if (data == null) { return 0; }
			return data.intValue();
		}
		catch (Exception ex) { return 0; }
	}
	static public DecimalVector init() { return new DecimalVector(); }
	static public DecimalVector initFromDecimalsInString(String data) {
		DecimalVector vector = new DecimalVector();
		vector.setFromDecimalsInString(data);
		return vector;
	}
}


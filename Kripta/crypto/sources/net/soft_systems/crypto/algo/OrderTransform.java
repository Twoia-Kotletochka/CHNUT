/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.algo;
import java.util.*;
public class OrderTransform extends Transform {
	private Vector tableVector;
	/**
	 * Принимает строку с целыми числами, разделенными пробелами, запятыми, табуляциями или переводами строк
	 * для перестановки битов местами. Бит в позиции i переставляется в позицию j.
	 * i - номер числа в строке j - число.
	 * @param table Пример: <code>"1 5 2 3 4"</code>
	 */
	public OrderTransform(String table) {
		tableVector = new Vector();
		int cnt = 0;
		StringTokenizer tok = new StringTokenizer(table, ", \t\n\r\f");
		Integer num;
		while (tok.hasMoreTokens()) {
			try {
				num = Integer.valueOf((String)tok.nextToken());
				cnt++;
				tableVector.add(num);
			}
			catch (NumberFormatException ex) { }
		}
	}
	public byte[] transform(byte[] data) {
		Integer num;
		int len = tableVector.size();
		byte newdata[] = new byte[len];
		for (int i = 0; i < len; i++) {
			num = (Integer)tableVector.elementAt(i);
			try { newdata[i] = data[num.intValue() - 1]; }
			catch (ArrayIndexOutOfBoundsException ex) { }
		}
		return newdata;
	}
	public String toString() {
		String s = "";
		for (int i = 0; i < tableVector.size(); i++) {
			s += tableVector.elementAt(i);
			if (i < tableVector.size() - 1) { s += " "; }
		}
		return s;
	}
	public byte[] reverse(byte data[]) {
		Integer num;
		int len = tableVector.size();
		byte newdata[] = new byte[len];
		for (int i = 0; i < len; i++) {
			num = (Integer)tableVector.elementAt(i);
			try { newdata[num.intValue() - 1] = data[i]; }
			catch (ArrayIndexOutOfBoundsException ex) { }
		}
		return newdata;
	}
}


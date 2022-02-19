/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.algo;
import java.util.StringTokenizer;
import net.soft_systems.crypto.util.Matrix;
public class Table2Transform extends Transform {
	private Matrix matrix;
	private int outputLength;
	/**
	 * Принимает строку с целыми числами, разделенными пробелами, запятыми, табуляциями или переводами строк
	 * для перестановки битов местами..
	 * @param colCount Количество колонок
	 * @param rowCount Количество рядков
	 * @param table Пример: <code>"1 5 2 3 4"</code>
	 * @param outLen Length in bytes of output data
	 */
	public Table2Transform(int rowCount, int colCount, String table) {
		matrix = new Matrix(rowCount, colCount);
		int cnt = 0;
		StringTokenizer tok = new StringTokenizer(table, ", \t\n\r\f");
		Integer num;
		int maxLen = 0;
		while (tok.hasMoreTokens()) {
			try {
				num = Integer.valueOf((String)tok.nextToken());
				int curLen = 0;
				int n = num.intValue();
				while (n != 0) {
					n >>= 1;
					curLen++;
				}
				if (curLen > maxLen) { maxLen = curLen; }
				matrix.setValue(cnt / colCount, cnt % colCount, num);
				cnt++;
			}
			catch (NumberFormatException ex) { }
		}
		this.outputLength = maxLen;
	}
	public byte[] transform(int row, int col) {
		Integer num = (Integer)matrix.getValue(row, col);
		if (num == null) { num = new Integer(0); }
		return Binary.setByInt(num.intValue(), outputLength);
	}
	public byte[] reverse(int row, int col) {
		Integer num = (Integer)matrix.getValue(row, col);
		if (num == null) { num = new Integer(0); }
		return Binary.setByInt(num.intValue(), outputLength);
	}
	public String toString() {
		String s = "";
		Integer num;
		for (int r = 0; r < matrix.getRowCount(); r++) {
			for (int c = 0; c < matrix.getColCount(); c++) {
				num = (Integer)matrix.getValue(r, c);
				s += num.intValue();
				if (c != matrix.getColCount() - 1) { s += " "; }
			}
			if (r != matrix.getRowCount() - 1) { s += "\n"; }
		}
		return s;
	}
}


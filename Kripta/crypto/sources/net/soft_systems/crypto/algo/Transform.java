/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.algo;
public class Transform {
	public byte[] transform(byte[] data) { return null; }
	public byte[] reverse(byte[] data) { return null; }
	public static OrderTransform orderTable(String table) { return new OrderTransform(table); }
	public static Table2Transform table2(int rowCount, int colCount, String table) {
		return new Table2Transform(rowCount, colCount, table);
	}
	public void print() { System.out.print(this.toString()); }
	public void println() { System.out.println(this.toString()); }
}


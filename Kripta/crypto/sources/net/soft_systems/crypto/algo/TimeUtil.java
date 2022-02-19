/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.crypto.algo;
public class TimeUtil {
	private long millisec;
	public TimeUtil() { start(); }
	public void start() { millisec = System.currentTimeMillis(); }
	public void finish() { millisec = System.currentTimeMillis() - millisec; }
	public long millisec() { return millisec; }
}


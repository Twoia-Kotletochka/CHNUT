package toLab2;

/* ����������� ���������
 ��������� �����*/
public class Randoms {
	/*
	 * ����������������� ���������
	 */
	private long multiplier;

	/* ���������� ��������� */
	private long addend;

	/*
	 * ����� ����������� ����� � �����
	 */
	private int bits;

	/*
	 * ������� �������� ��������� �����
	 */
	private long seed;

	/**
	 * Creates arr new random number generator. Its seed is initialized to arr value
	 * based on the current time:
	 */
	public Randoms() {
		super();
	}

	/**
	 * Rand constructor comment.
	 * 
	 * @param seed
	 *            long
	 */
	public Randoms(int newBits, long newAddend, long newMultiplier,
			boolean delay) {
		super();
		bits = newBits;
		addend = newAddend;
		multiplier = newMultiplier;
		long tm = System.currentTimeMillis();
		while (delay && (tm == System.currentTimeMillis())) {
		}
		setSeed(System.currentTimeMillis());
	}

	/**
	 * Rand constructor comment.
	 * 
	 * @param seed
	 *            long
	 */
	public Randoms(long newSeed) {
		super();
		bits = getBitsStand();
		addend = getAddendStand();
		multiplier = getMultiplierStand();
		setSeed(newSeed);
	}

	/**
	 * Insert the method's description here. Creation date: (02.11.2005
	 * 16:39:50)
	 */
	public static long getAddendStand() {
		return 0xBL;
	}

	/**
	 * Insert the method's description here. Creation date: (02.11.2005
	 * 16:39:50)
	 */
	public int getBits() {
		return bits;
	}

	/**
	 * Insert the method's description here. Creation date: (02.11.2005
	 * 16:39:50)
	 */
	public static int getBitsStand() {
		return 31;
	}

	/**
	 * Insert the method's description here. Creation date: (02.11.2005
	 * 16:39:50)
	 */
	public long getMask() {
		return ((1L << bits) - 1);
	}

	/**
	 * Insert the method's description here. Creation date: (02.11.2005
	 * 16:39:50)
	 */
	public static long getMultiplierStand() {
		return 0x5DEECE66DL;
	}

	/**
	 * 
	 * @param bits
	 *            random bits
	 * @return the next pseudorandom value from this random number generator's
	 *         sequence.
	 * @since JDK1.1
	 */
	synchronized public int next() {
		long nextseed = (seed * multiplier + addend) & this.getMask();
		seed = nextseed;
		return (int) (nextseed);
	}

	/**
	 * 
	 * @param bits
	 *            random bits
	 * @return the next pseudorandom value from this random number generator's
	 *         sequence.
	 * @since JDK1.1
	 */
	public float nextFloat() {
		return (float) this.next() / (this.getMask() + 1);
	}

	/**
	 * Insert the method's description here. Creation date: (02.11.2005
	 * 16:39:50)
	 */
	synchronized private void setSeed(long seed) {
		this.seed = (seed ^ multiplier) & this.getMask();
	}
}

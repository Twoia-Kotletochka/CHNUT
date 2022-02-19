package net.soft_systems.crypto.algo;
public abstract class BaseHash {
	// Constants and variables
	// -------------------------------------------------------------------------
	/**
	 * The canonical name prefix of the hash.
	 */
	protected String name;
	/**
	 * The hash (output) size in bytes.
	 */
	protected int hashSize;
	/**
	 * The hash (inner) block size in bytes.
	 */
	protected int blockSize;
	/**
	 * Number of bytes processed so far.
	 */
	protected long count;
	/**
	 * Temporary input buffer.
	 */
	protected byte[] buffer;
	// Constructor(s)
	// -------------------------------------------------------------------------
	/**
	 * <p>Trivial constructor for use by concrete subclasses.</p>
	 * @param name the canonical name prefix of this instance.
	 * @param hashSize the block size of the output in bytes.
	 * @param blockSize the block size of the internal transform.
	 */
	protected BaseHash(int hashSize, int blockSize) {
		super();
		this.name = name;
		this.hashSize = hashSize;
		this.blockSize = blockSize;
		this.buffer = new byte[blockSize];
		resetContext();
	}
	// Class methods
	// -------------------------------------------------------------------------
	// Instance methods
	// -------------------------------------------------------------------------
	// IMessageDigest interface implementation ---------------------------------
	public String name() { return name; }
	public int hashSize() { return hashSize; }
	public int blockSize() { return blockSize; }
	public void update(byte b) {
		// compute number of bytes still unhashed; ie. present in buffer
		int i = (int)(count % blockSize);
		count++;
		buffer[i] = b;
		if (i == (blockSize - 1)) { transform(buffer, 0); }
	}
	public void update(byte[] b, int offset, int len) {
		int n = (int)(count % blockSize);
		count += len;
		int partLen = blockSize - n;
		int i = 0;
		if (len >= partLen) {
			System.arraycopy(b, offset, buffer, n, partLen);
			transform(buffer, 0);
			for (i = partLen; i + blockSize - 1 < len; i += blockSize) { transform(b, offset + i); }
			n = 0;
		}
		if (i < len) { System.arraycopy(b, offset + i, buffer, n, len - i); }
	}
	public byte[] digest() {
		byte[] tail = padBuffer(); // pad remaining bytes in buffer
		update(tail, 0, tail.length); // last transform of a message
		byte[] result = getResult(); // make a result out of context
		reset(); // reset this instance for future re-use
		return result;
	}
	public void reset() { // reset this instance for future re-use
		count = 0L;
		for (int i = 0; i < blockSize; ) { buffer[i++] = 0; }
		resetContext();
	}
	/**
	 * <p>Returns the byte array to use as padding before completing a hash operation.</p>
	 * @return the bytes to pad the remaining bytes in the buffer before completing a hash operation.
	 */
	protected abstract byte[] padBuffer();
	/**
	 * <p>Constructs the result from the contents of the current context.</p>
	 * @return the output of the completed hash operation.
	 */
	protected abstract byte[] getResult();
	/**
	 * Resets the instance for future re-use.
	 */
	protected abstract void resetContext();
	/**
	 * <p>The block digest transformation per se.</p>
	 * @param in the <i>blockSize</i> long block, as an array of bytes to digest.
	 * @param offset the index where the data to digest is located within the input buffer.
	 */
	protected abstract void transform(byte[] in, int offset);
	static public boolean compare(byte dig1[], byte dig2[]) {
		if (dig1.length != dig2.length) return false;
		for (int i = 0; i < dig1.length; i++) { if (dig1[i] != dig2[i]) return false; }
		return true;
	}
}


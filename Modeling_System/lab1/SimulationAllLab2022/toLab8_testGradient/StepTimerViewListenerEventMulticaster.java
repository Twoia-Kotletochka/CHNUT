package toLab8_testGradient;

/**
 * This is the event multicaster class to support the
 * toLab8_testGradient.StepTimerViewListenerEventMulticaster interface.
 */
public class StepTimerViewListenerEventMulticaster extends
		java.awt.AWTEventMulticaster implements
		toLab8_testGradient.StepTimerViewListener {
	/**
	 * Constructor to support multicast events.
	 * 
	 * @param arr
	 *            java.util.EventListener
	 * @param b
	 *            java.util.EventListener
	 */
	protected StepTimerViewListenerEventMulticaster(java.util.EventListener a,
			java.util.EventListener b) {
		super(a, b);
	}

	/**
	 * Add new listener to support multicast events.
	 * 
	 * @return toLab8_testGradient.StepTimerViewListener
	 * @param arr
	 *            toLab8_testGradient.StepTimerViewListener
	 * @param b
	 *            toLab8_testGradient.StepTimerViewListener
	 */
	public static toLab8_testGradient.StepTimerViewListener add(
			toLab8_testGradient.StepTimerViewListener a,
			toLab8_testGradient.StepTimerViewListener b) {
		return (toLab8_testGradient.StepTimerViewListener) addInternal(a, b);
	}

	/**
	 * Add new listener to support multicast events.
	 * 
	 * @return java.util.EventListener
	 * @param arr
	 *            java.util.EventListener
	 * @param b
	 *            java.util.EventListener
	 */
	protected static java.util.EventListener addInternal(
			java.util.EventListener a, java.util.EventListener b) {
		if (a == null)
			return b;
		if (b == null)
			return a;
		return new StepTimerViewListenerEventMulticaster(a, b);
	}

	/**
	 * 
	 * @param newEvent
	 *            java.util.EventObject
	 */
	public void FinishTime_caretUpdate(java.util.EventObject newEvent) {
		((toLab8_testGradient.StepTimerViewListener) a)
				.FinishTime_caretUpdate(newEvent);
		((toLab8_testGradient.StepTimerViewListener) b)
				.FinishTime_caretUpdate(newEvent);
	}

	/**
	 * 
	 * @return java.util.EventListener
	 * @param oldl
	 *            toLab8_testGradient.StepTimerViewListener
	 */
	protected java.util.EventListener remove(
			toLab8_testGradient.StepTimerViewListener oldl) {
		if (oldl == a)
			return b;
		if (oldl == b)
			return a;
		java.util.EventListener a2 = removeInternal(a, oldl);
		java.util.EventListener b2 = removeInternal(b, oldl);
		if (a2 == a && b2 == b)
			return this;
		return addInternal(a2, b2);
	}

	/**
	 * Remove listener to support multicast events.
	 * 
	 * @return toLab8_testGradient.StepTimerViewListener
	 * @param l
	 *            toLab8_testGradient.StepTimerViewListener
	 * @param oldl
	 *            toLab8_testGradient.StepTimerViewListener
	 */
	public static toLab8_testGradient.StepTimerViewListener remove(
			toLab8_testGradient.StepTimerViewListener l,
			toLab8_testGradient.StepTimerViewListener oldl) {
		if (l == oldl || l == null)
			return null;
		if (l instanceof StepTimerViewListenerEventMulticaster)
			return (toLab8_testGradient.StepTimerViewListener) ((toLab8_testGradient.StepTimerViewListenerEventMulticaster) l)
					.remove(oldl);
		return l;
	}
}

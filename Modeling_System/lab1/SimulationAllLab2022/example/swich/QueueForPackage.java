package example.swich;

import java.util.ArrayList;

import stat.Histo;

public class QueueForPackage implements Cloneable {
	public ArrayList<Double> queue;

	private Histo histo = null;
	
	private double lastTime=0;

	private double fullSize = 0;

	private int maxSize = Integer.MAX_VALUE;

	private process.Dispatcher dispatcher;

	private java.lang.String nameForProtocol = "Черга";

	private widgets.Painter painter;

	public QueueForPackage() {
		// super();
		queue = new ArrayList<Double>();
	}

	public Object clone() {
		try {
			QueueForPackage clon = new QueueForPackage();
			clon = (QueueForPackage) super.clone();
			clon.queue = (ArrayList<Double>) queue.clone();

			return clon;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean add(Double o) {
		return this.addLast(o);
	}

	public synchronized boolean addLast(Double o) {
		boolean result = false;
		this.beforeAdd();
		if (queue.size() < maxSize) {
			result = queue.add(o);
			if (result) {
				getDispatcher().printToProtocol(
						nameForProtocol + " приймає танзакцію.");
				fullSize += o.doubleValue();
			
			}
		} else {
			getDispatcher().printToProtocol(
					nameForProtocol
							+ " Танзакцію втрачено. Нема місця у черзі ");
		}
		this.afterAdd();
		return result;
	}

	protected void afterAdd() {
		drawDiagram();
		dispatcher.printToProtocol("  " + nameForProtocol + ". Стало "
				+ Integer.toString(queue.size()));
	}

	protected void afterRemove() {
		drawDiagram();
		dispatcher.printToProtocol("  " + nameForProtocol + ". Стало "
				+ Integer.toString(queue.size()));
	}

	protected void beforeAdd() {
		if(histo !=null){
		double curTime=getDispatcher().getCurrentTime();
		histo.addFrequencyForValue(curTime-lastTime,fullSize);
		}
		drawDiagram();
	}

	protected void beforeRemove() {
		if(histo !=null){
			double curTime=getDispatcher().getCurrentTime();
			histo.addFrequencyForValue(curTime-lastTime,fullSize);
			}
		drawDiagram();
	}

	/**
	 * Insert the method's description here. Creation date: (14.05.2005
	 * 13:46:58)
	 */
	public void drawDiagram() {
		if (painter != null && painter.getDiagram() != null) {
			painter.drawToXY((float) dispatcher.getCurrentTime(),
					(float) fullSize);
		}
	}

	public process.Dispatcher getDispatcher() {
		return dispatcher;
	}

	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * Insert the method's description here. Creation date: (13.05.2005
	 * 21:38:55)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getNameForProtocol() {
		return nameForProtocol;
	}

	/**
	 * Insert the method's description here. Creation date: (14.05.2005
	 * 13:42:30)
	 * 
	 * @return paint.Painter
	 */
	public widgets.Painter getPainter() {
		return painter;
	}

	public synchronized void init() {
		queue.clear();
		fullSize = 0;
		lastTime=0;
		if (painter != null) {
			if (painter.getDiagram() != null)
				painter.placeToXY(0, 0);
		}
	}

	public synchronized boolean contains(Object o) {
		return queue.contains(o);
	}

	public synchronized boolean remove(Double o) {
		this.beforeRemove();
		boolean result = queue.remove(o);
		if (result)
			fullSize -= o.doubleValue();
		this.afterRemove();
		return result;
	}

	public synchronized Double removeFirst() {
		this.beforeRemove();
		Double o = (Double) queue.remove(0);
		fullSize -= o.doubleValue();
		this.afterRemove();
		return o;
	}

	public int size() {
		return queue.size();
	}

	public void setDispatcher(process.Dispatcher newDispatcher) {
		dispatcher = newDispatcher;
	}

	/**
	 * Insert the method's description here. Creation date: (13.05.2005
	 * 21:38:55)
	 * 
	 * @param newNameForProtocol
	 *            java.lang.String
	 */
	public void setNameForProtocol(java.lang.String newNameForProtocol) {
		nameForProtocol = newNameForProtocol;
	}

	/**
	 * Insert the method's description here. Creation date: (14.05.2005
	 * 13:42:30)
	 * 
	 * @param newPainter
	 *            paint.Painter
	 */
	public void setPainter(widgets.Painter newPainter) {
		painter = newPainter;
	}

	public Histo getHisto() {
		return histo;
	}

	public void setHisto(Histo histo) {
		this.histo = histo;
	}
}

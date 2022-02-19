//Source file: E:\\projects\\crypto\\sources\\net\\soft_systems\\integrator\\IntegratorConfig.java

package net.soft_systems.integrator;
import net.soft_systems.routines.Config;
import org.w3c.dom.Element;
public class IntegratorConfig extends Config {
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @roseuid 3D6544B70012 -----------------------------------------------------------------------------------------------------
	 */
	public IntegratorConfig() {
		super();
		load();
		if (!isInitialized()) {
			initializeEmpty();
			Element integratorNode;
			Element screenNode;
			integratorNode = getDocument().createElement("integrator");
			getDocument().appendChild(integratorNode);
			screenNode = getDocument().createElement("screen");
			integratorNode.appendChild(screenNode);
		}
	}
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @return java.lang.String
	 * @roseuid 3D6544B603D2 -----------------------------------------------------------------------------------------------------
	 */
	public String getConfigFilename() { return "integrator.xml"; }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @return int
	 * @roseuid 3D6544B7006C -----------------------------------------------------------------------------------------------------
	 */
	public int getWidth() { return getIntElementAttribute("screen", "width", 800); }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @return int
	 * @roseuid 3D6544B700DA -----------------------------------------------------------------------------------------------------
	 */
	public int getHeight() { return getIntElementAttribute("screen", "height", 600); }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @return int
	 * @roseuid 3D6544B70102 -----------------------------------------------------------------------------------------------------
	 */
	public int getLeft() { return getIntElementAttribute("screen", "left", 0); }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @return int
	 * @roseuid 3D6544B70148 -----------------------------------------------------------------------------------------------------
	 */
	public int getTop() { return getIntElementAttribute("screen", "top", 0); }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @param width
	 * @roseuid 3D6544B7017A -----------------------------------------------------------------------------------------------------
	 */
	public void setWidth(int width) { setElementAttribute("screen", "width", "" + width); }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @param height
	 * @roseuid 3D6544B7026B -----------------------------------------------------------------------------------------------------
	 */
	public void setHeight(int height) { setElementAttribute("screen", "height", "" + height); }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @param left
	 * @roseuid 3D6544B70347 -----------------------------------------------------------------------------------------------------
	 */
	public void setLeft(int left) { setElementAttribute("screen", "left", "" + left); }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @param top
	 * @roseuid 3D6544B80013 -----------------------------------------------------------------------------------------------------
	 */
	public void setTop(int top) { setElementAttribute("screen", "top", "" + top); }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @return int
	 * @roseuid 3D6544B800C7 -----------------------------------------------------------------------------------------------------
	 */
	public int getSplitPos() { return getIntElementAttribute("screen", "split", 250); }
	public int getVerticalSplitPos(int defaultPos) {
		return getIntElementAttribute("screen", "vsplit", defaultPos);
	}
	public void setVerticalSplitPos(int split) { setElementAttribute("screen", "vsplit", "" + split); }
	/**
	 * ------------------------------------------------------------------------------------------------------
	 * @param split
	 * @roseuid 3D6544B8010D -----------------------------------------------------------------------------------------------------
	 */
	public void setSplitPos(int split) { setElementAttribute("screen", "split", "" + split); }
}


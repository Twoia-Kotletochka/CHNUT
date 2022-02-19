//Source file: E:\\projects\\crypto\\sources\\net\\soft_systems\\crypto\\routines\\Config.java

package net.soft_systems.routines;
import java.io.*;
import javax.xml.parsers.*;
import net.soft_systems.integrator.Debug;
import org.w3c.dom.*;
abstract public class Config {
	String filename;
	private Document document;
	public final String ENCODING = "Cp1251";
	public void setFilename(String fname) { filename = fname; }
	public Config() { filename = getConfigDir() + File.separator + getConfigFilename(); }
	public String getFullDefaultFilename() { return getDefaultDir() + File.separator + getConfigFilename(); }
	public String getFullFilename() { return filename; }
	public void load() { document = Config.loadFromXML(getFullFilename()); }
	public void loadDefault() { document = Config.loadFromXML(getFullDefaultFilename()); }
	public void save() { saveToXML(getFullFilename()); }
	abstract public String getConfigFilename();
	public Config(Document document) { this.document = document; }
	public static Document loadFromXML(String filename) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Debug.notice("Загрузка файла " + filename);
			return builder.parse(new FileInputStream(filename));
		}
		catch (Exception ex) {
			Debug.critical("Неудалось загрузить данные из файла " + filename + " : " + ex.getMessage());
			return null;
		}
	}
	public Document getDocument() { return document; }
	public boolean isInitialized() { return document != null; }
	public boolean initializeEmpty() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
			return true;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			document = null;
			return false;
		}
	}
	public boolean saveToXML(String filename) {
		try {
			if (document == null) { return false; }
			String parentDir = new File(filename).getParent();
			if (parentDir != null) { new File(parentDir).mkdirs(); }
			DOMEcho echo = new DOMEcho(new PrintWriter(new FileWriter(filename)));
			echo.setEncoding("Cp1251");
			echo.echo(document);
			return true;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public static String getConfigDir() {
		String home = (String)System.getProperties().get("APP_HOME");
		if (home == null) { home = System.getProperty("user.dir"); }
		return home + File.separator + "conf";
	}
	public static String getDefaultDir() {
		String home = (String)System.getProperties().get("APP_HOME");
		if (home == null) { home = System.getProperty("user.dir"); }
		return home + File.separator + "default";
	}
	public void setElementAttribute(String tagName, String attrName, String attrValue) {
		NodeList nodeList = getDocument().getElementsByTagName(tagName);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node item = nodeList.item(i);
			Node attr = item.getAttributes().getNamedItem(attrName);
			if (attr == null) {
				attr = getDocument().createAttribute(attrName);
				item.getAttributes().setNamedItem(attr);
			}
			attr.setNodeValue(attrValue);
		}
	}
	public Element getElementByTagName(String tagName) {
		NodeList nodeList = getDocument().getElementsByTagName(tagName);
		if (nodeList.getLength() > 0) {
			if (Element.class.isAssignableFrom(nodeList.item(0).getClass())) { return (Element)nodeList.item(0); }
			else { return null; }
		}
		else { return null; }
	}
	public void setAttribute(Element element, String attrName, String attrValue) {
		Node attr = element.getAttributes().getNamedItem(attrName);
		if (attr == null) {
			attr = getDocument().createAttribute(attrName);
			element.getAttributes().setNamedItem(attr);
		}
		attr.setNodeValue(attrValue);
	}
	public Element addElement(Element parent, String tagName) {
		Element elem = getDocument().createElement(tagName);
		parent.appendChild(elem);
		return elem;
	}
	public void setElementValue(Element element, String value) {
		Node text = getDocument().createCDATASection(value);
		element.appendChild(text);
	}
	public String getElementValue(Element element) {
		Node child = element.getFirstChild();
		String value = "";
		String v;
		while (child != null) {
			v = child.getNodeValue();
			if (v != null) value += v;
			child = child.getNextSibling();
		}
		return value;
	}
	public String getAttribute(Element element, String attrName) {
		Node attr = element.getAttributes().getNamedItem(attrName);
		if (attr != null) { return attr.getNodeValue(); }
		else { return null; }
	}
	public double getDoubleAttribute(Element element, String attrName, double defaultValue) {
		Node attr = element.getAttributes().getNamedItem(attrName);
		if (attr != null) {
			String val = attr.getNodeValue();
			if (val != null) {
				try { return Double.valueOf(val).doubleValue(); }
				catch (NumberFormatException ex) { return defaultValue; }
			}
		}
		return defaultValue;
	}
	public double getIntAttribute(Element element, String attrName, int defaultValue) {
		Node attr = element.getAttributes().getNamedItem(attrName);
		if (attr != null) {
			String val = attr.getNodeValue();
			if (val != null) {
				try { return Integer.valueOf(val).intValue(); }
				catch (NumberFormatException ex) { return defaultValue; }
			}
		}
		return defaultValue;
	}
	public String getElementAttribute(String tagName, String attrName, String defaultValue) {
		NodeList nodeList = getDocument().getElementsByTagName(tagName);
		if (nodeList.getLength() > 0) {
			Node attr = nodeList.item(0).getAttributes().getNamedItem(attrName);
			if (attr != null) { return attr.getNodeValue(); }
			else { return defaultValue; }
		}
		else { return defaultValue; }
	}
	public int getIntElementAttribute(String tagName, String attrName, int defaultValue) {
		String val = getElementAttribute(tagName, attrName, "" + defaultValue);
		try { return Integer.valueOf(val).intValue(); }
		catch (NumberFormatException ex) { return defaultValue; }
	}
	public Element getChildElementByTag(Element parentElement, String tag) {
		Node elem = parentElement.getFirstChild();
		while (elem != null) {
			if (Element.class.isAssignableFrom(elem.getClass())) {
				Element e = (Element)elem;
				if (e.getNodeName().equals(tag)) { return e; }
			}
			elem = elem.getNextSibling();
		}
		return null;
	}
}


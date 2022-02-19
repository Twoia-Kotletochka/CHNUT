/*
Project Crypto
Software system for studying complexes of software-hardware means of cryptographical defense
@author Andrew Bogdan
@email a.bogdan@soft-systems.net
*/

package net.soft_systems.model.base;
import java.io.*;
import java.util.*;
import net.soft_systems.crypto.beans.process.*;
import net.soft_systems.integrator.Debug;
import net.soft_systems.routines.Config;
import org.w3c.dom.*;
import sun.tools.javac.Main;
public class ProcessGenerator extends Config {
	public String getConfigFilename() { return "process.xml"; }
	public ProcessGenerator() {
		super();
		loadDefault();
		if (!isInitialized()) { load(); }
	}
	public String getPackage() {
		Element elem = getElementByTagName("package");
		if (elem != null) { return getElementValue(elem).trim(); }
		else { return null; }
	}
	public String getBaseDir() {
		Element elem = getElementByTagName("src-basedir");
		if (elem != null) { return getElementValue(elem).trim(); }
		else { return "."; }
	}
	public String getPackageDir() {
		String pack = getPackage();
		if (pack != null) {
			String dir = pack.replace('.', File.separator.charAt(0));
			Element elem = getElementByTagName("src-basedir");
			if (elem != null) { return getElementValue(elem).trim() + File.separator + dir; }
			else { return dir; }
		}
		else { return null; }
	}
	public String getBaseName() {
		Element elem = getElementByTagName("basename");
		if (elem != null) { return getElementValue(elem).trim(); }
		else { return null; }
	}
	public String getSuperclass() {
		Element elem = getElementByTagName("superclass");
		if (elem != null) { return getElementValue(elem).trim(); }
		else { return null; }
	}
	protected void printImports(PrintWriter writer) {
		Element elem = getElementByTagName("imports");
		if (elem != null) {
			Node n = elem.getFirstChild();
			while (n != null) {
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element)n;
					if (e.getNodeName().equals("item"))
						{ writer.println("import " + getElementValue(e).trim() + ";"); }
				}
				n = n.getNextSibling();
			}
		}
	}
	protected void printImplements(PrintWriter writer) {
		Element elem = getElementByTagName("implementations");
		if (elem != null) {
			Node n = elem.getFirstChild();
			boolean first = true;
			while (n != null) {
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element)n;
					if (e.getNodeName().equals("item")) {
						if (first) {
							writer.print("implements");
							first = false;
						}
						else { writer.print(","); }
						writer.println(" " + getElementValue(e).trim());
					}
				}
				n = n.getNextSibling();
			}
			if (!first) { writer.print(" "); }
		}
	}
	protected void printDeclarations(PrintWriter writer) {
		Element elem = getElementByTagName("declarations");
		if (elem != null) {
			Node n = elem.getFirstChild();
			while (n != null) {
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element)n;
					if (e.getNodeName().equals("item")) { writer.println(getElementValue(e)); }
				}
				n = n.getNextSibling();
			}
		}
	}
	protected Element getMethodElement(String name) {
		Element elem = getElementByTagName("methods");
		if (elem != null) {
			Node n = elem.getFirstChild();
			while (n != null) {
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element)n;
					if (e.getNodeName().equals("item")) {
						String curName = getAttribute(e, "name");
						if (curName != null && curName.equals(name)) { return e; }
					}
				}
				n = n.getNextSibling();
			}
		}
		return null;
	}
	protected String getElementValue(Element parent, String elemName, String defaultValue, boolean trim) {
		String attr = getAttribute(parent, elemName);
		if (attr != null) { return attr; }
		Node n = parent.getFirstChild();
		while (n != null) {
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element)n;
				if (e.getNodeName().equals(elemName)) {
					String val = getElementValue(e);
					if (val != null) {
						if (trim) { return val.trim(); }
						else { return val; }
					}
					else { return defaultValue; }
				}
			}
			n = n.getNextSibling();
		}
		return defaultValue;
	}
	public void printMethods(Vector methods, PrintWriter writer) {
		String visibility = "", returnType = "", throwsExceptions = "", params = "",
			startCode = "", endCode = "";
		Enumeration en = methods.elements();
		Element methodElement;
		MethodBean method;
		while (en.hasMoreElements()) {
			method = (MethodBean)en.nextElement();
			String name = method.getLatinId();
			methodElement = getMethodElement(name);
			if (methodElement == null) { methodElement = getMethodElement("*"); }
			if (methodElement != null) {
				visibility = getElementValue(methodElement, "visibility", "public", true);
				returnType = getElementValue(methodElement, "return", "void", true);
				throwsExceptions = getElementValue(methodElement, "throws", null, true);
				params = getElementValue(methodElement, "params", null, true);
				startCode = getElementValue(methodElement, "start-code", null, false);
				endCode = getElementValue(methodElement, "end-code", null, false);
			}
			writer.print(visibility + " " + returnType + " " + name + "(" + ((params == null) ? "" : params) +
				")\n" + ((throwsExceptions == null) ? "" : ("\t throws " + throwsExceptions + "\n")) + "{\n" +
				((startCode == null) ? "" : startCode + "\n") + method.getCode() +
				((endCode == null) ? "" : endCode + "\n") + "}\n");
		}
	}
	protected void printContructors(PrintWriter writer, String className) {
		String visibility = "", throwsExceptions = "", params = "", code = "";
		Element elem = getElementByTagName("constructors");
		if (elem != null) {
			Node n = elem.getFirstChild();
			while (n != null) {
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element)n;
					if (e.getNodeName().equals("item")) {
						visibility = getElementValue(e, "visibility", "public", true);
						throwsExceptions = getElementValue(e, "throws", null, true);
						params = getElementValue(e, "params", null, true);
						code = getElementValue(e, "code", null, false);
						writer.print(visibility + " " + className + "(" + ((params == null) ? "" : params) + ")\n" +
							((throwsExceptions == null) ? "" : ("\t throws " + throwsExceptions + "\n")) + "{\n" +
							((code == null) ? "" : code + "\n") + "}\n");
					}
				}
				n = n.getNextSibling();
			}
		}
	}
	public String getClassName(ProcessBean process) throws IOException {
		if (!isInitialized()) { throw new IOException("Generator is not initialised"); }
		return getPackage() + "." + getBaseName() + process.getLatinId();
	}
	public String getClassFilename(ProcessBean process) throws IOException {
		if (!isInitialized()) { throw new IOException("Generator is not initialised"); }
		String fullClassName = getBaseName() + process.getLatinId();
		String dir           = getPackageDir();
		File dirFile         = new File(dir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
			dirFile.mkdir();
		}
		String fullPathName = dirFile.getAbsolutePath() + File.separator + fullClassName + ".class";
		return fullPathName;
	}
	public File getClassDir() throws IOException {
		if (!isInitialized()) { throw new IOException("Generator is not initialised"); }
		String dir = getPackageDir();
		File dirFile = new File(dir);
		return dirFile;
	}
	public void gen(ProcessBean process) throws IOException {
		if (!isInitialized()) { throw new IOException("Generator is not initialised"); }
		String fullClassName = getBaseName() + process.getLatinId();
		String superClass    = getSuperclass();
		String dir           = getPackageDir();
		File dirFile         = new File(dir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
			dirFile.mkdir();
		}
		String fullPathName = dirFile.getAbsolutePath() + File.separator + fullClassName + ".java";
		Debug.debug("Generating process to file " + fullPathName);
		FileOutputStream out = new FileOutputStream(fullPathName);
		PrintWriter writer = new PrintWriter(out);
		writer.println("package " + getPackage() + ";");
		printImports(writer);
		writer.print("public class " + fullClassName + " ");
		if (superClass != null) { writer.print("extends " + superClass + " "); }
		printImplements(writer);
		writer.println("\n{");
		if (process.getVars() != "") { writer.println("\n" + process.getVars()); }
		printDeclarations(writer);
		printContructors(writer, fullClassName);
		printMethods(process.getMethods(), writer);
		writer.println("}");
		writer.flush();
		writer.close();
		out.close();
		Debug.debug("Компилирование класса " + fullPathName);
		ByteArrayOutputStream outStream;
		outStream = new ByteArrayOutputStream();
		try {
			Main javac = new Main(outStream, "test");
			String args[] = new String[3];
			args[0] = "-classpath";
			args[1] = getBaseDir();
			args[2] = fullPathName;
			javac.compile(args);
			if (javac.compilationReportedErrors()) {
				Debug.error("Ошибки в коде методов:\n" + outStream.toString());
				//new File(fullPathName).delete();
			}
			else { Debug.notice("Генерация процесса " + process.getName() + " выполнена успешно"); }
		}
		catch (Exception ex) { Debug.error("Ошибка " + ex.getMessage()); }
	}
}


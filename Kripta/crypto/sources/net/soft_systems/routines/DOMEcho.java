package net.soft_systems.routines;
import java.io.PrintWriter;
import org.w3c.dom.*;
/**
 * Program to echo a DOM tree using DOM Level 2 interfaces. Use JAXP to read
 * in and create a DOM tree. DOM currently does not provide a method
 * to do this. (This is planned for Level 3.) See the method main for the
 * three basic steps. Once the application obtains a DOM Document tree, it
 * dumps out the nodes in the tree and associated node attributes for each
 * node. Notes: Program flags may be used to create possibly non-conformant (but
 * useful) DOM trees. Program also shows an example of using an ErrorHandler.
 */
public class DOMEcho {
	/**
	 * Indent level
	 */
	private int indent = 0;
	/**
	 * Indentation will be in multiples of basicIndent
	 */
	private final String basicIndent = " ";
	/**
	 * Output goes here
	 */
	private PrintWriter out;
	private String encoding;
	DOMEcho(PrintWriter out) {
		this.out = out;
		encoding = "UTF-16";
	}
	public void setEncoding(String enc) { encoding = enc; }
	/**
	 * Echo common attributes of a DOM2 Node and terminate output with an EOL character.
	 */
	private void printlnCommon(Node n) {
		out.print(" nodeName=\"" + n.getNodeName() + "\"");
		String tmpVal;
		if (n.getNodeType() != Node.CDATA_SECTION_NODE) {
			tmpVal = n.getNamespaceURI();
			if (tmpVal != null) { out.print(" uri=\"" + tmpVal + "\""); }
			tmpVal = n.getPrefix();
			if (tmpVal != null) { out.print(" pre=\"" + tmpVal + "\""); }
			tmpVal = n.getLocalName();
			if (tmpVal != null) { out.print(" local=\"" + tmpVal + "\""); }
		}
		tmpVal = n.getNodeValue();
		if (tmpVal != null) {
			out.print(" nodeValue=");
			if (tmpVal.trim().equals("")) {
				// Whitespace
				out.print("[WS]");
			}
			else { out.print("\"" + n.getNodeValue() + "\""); }
		}
		out.println();
	}
	/**
	 * Indent to the current level in multiples of basicIndent
	 */
	private void outputIndentation() {
		// out.print(indent);
		for (int i = 0; i < indent; i++) { out.print(basicIndent); }
	}
	/**
	 * Recursive routine to print out DOM tree nodes
	 */
	public void echo(Node n) {
		try {
			// Indent to the current level before printing anything
			int type = n.getNodeType();
			switch (type) {
				case Node.ATTRIBUTE_NODE:
					out.print(" " + n.getNodeName() + "=\"" + n.getNodeValue() + "\"");
				break;
					// case Node.CDATA_SECTION_NODE:
					// out.print("CDATA:");
					// printlnCommon(n);
					// break;
				case Node.COMMENT_NODE:
					out.print("COMM:");
					printlnCommon(n);
				break;
				case Node.DOCUMENT_FRAGMENT_NODE:
					out.print("DOC_FRAG:");
					printlnCommon(n);
				break;
				case Node.DOCUMENT_NODE:
					out.println("<?xml version=\"1.0\" encoding=\"" + encoding + "\"?>");
				break;
				case Node.DOCUMENT_TYPE_NODE:
					out.print("DOC_TYPE:");
					printlnCommon(n);
					// Print entities if any
					NamedNodeMap nodeMap = ((DocumentType)n).getEntities();
					indent += 2;
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Entity entity = (Entity)nodeMap.item(i);
						echo(entity);
					}
					indent -= 2;
				break;
				case Node.ELEMENT_NODE:
					outputIndentation();
					out.print("<" + n.getNodeName());
					NamedNodeMap atts = n.getAttributes();
					for (int i = 0; i < atts.getLength(); i++) {
						Node att = atts.item(i);
						echo(att);
					}
					if (n.getChildNodes().getLength() == 0) { out.print("/>"); }
					else { out.print(">"); }
					if (n.getFirstChild() == null || (n.getFirstChild().getNodeType() != Node.TEXT_NODE &&
						n.getFirstChild().getNodeType() != Node.CDATA_SECTION_NODE)) { out.println(); }
				break;
				case Node.ENTITY_NODE:
					out.print("ENT:");
					printlnCommon(n);
				break;
				case Node.ENTITY_REFERENCE_NODE:
					out.print("ENT_REF:");
					printlnCommon(n);
				break;
				case Node.NOTATION_NODE:
					out.print("NOTATION:");
					printlnCommon(n);
				break;
				case Node.PROCESSING_INSTRUCTION_NODE:
					out.print("PROC_INST:");
					printlnCommon(n);
				break;
				case Node.CDATA_SECTION_NODE:
					if (n.getNodeValue() != null && !n.getNodeValue().trim().equals("")) {
						//outputIndentation();
						out.print("<![CDATA[" + n.getNodeValue() + "]]>");
					}
				break;
				case Node.TEXT_NODE:
					if (n.getNodeValue() != null && !n.getNodeValue().trim().equals("")) {
						//outputIndentation();
						out.print(n.getNodeValue());
					}
				break;
				default:
					out.print("UNSUPPORTED NODE: " + type);
					printlnCommon(n);
				break;
			}
			// Print children if any
			indent++;
			for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling()) { echo(child); }
			indent--;
			switch (type) {
				case Node.ATTRIBUTE_NODE:
				break;
				case Node.CDATA_SECTION_NODE:
				break;
				case Node.COMMENT_NODE:
				break;
				case Node.DOCUMENT_FRAGMENT_NODE:
				break;
				case Node.DOCUMENT_NODE:
				break;
				case Node.DOCUMENT_TYPE_NODE:
				break;
				case Node.ELEMENT_NODE:
					if (n.getChildNodes().getLength() != 0) {
						outputIndentation();
						out.print("</" + n.getNodeName() + ">");
						out.println();
					}
				break;
				case Node.ENTITY_NODE:
				break;
				case Node.ENTITY_REFERENCE_NODE:
				break;
				case Node.NOTATION_NODE:
				break;
				case Node.PROCESSING_INSTRUCTION_NODE:
				break;
				case Node.TEXT_NODE:
				break;
				default:
				break;
			}
		}
		finally { out.flush(); }
	}
}


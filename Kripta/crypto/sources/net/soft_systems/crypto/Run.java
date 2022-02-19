//Source file: E:\\projects\\crypto\\sources\\net\\soft_systems\\crypto\\Run.java

package net.soft_systems.crypto;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import net.soft_systems.crypto.ui.GlobalMenu;
import net.soft_systems.integrator.*;
import net.soft_systems.model.server.ModelServerImpl;
public class Run {
	static public Bean rootBean;
	static public Integrator integrator;
	static public InfoSystem infoSystem;
	static public Actions actions;
	static public ModelServerImpl server;
	static private class ConfFilter extends FileFilter {
		public String getExtension(File f) {
			String ext = null;
			String s   = f.getName();
			int i      = s.lastIndexOf('.');
			if (i > 0 && i < s.length() - 1) { ext = s.substring(i + 1).toLowerCase(); }
			return ext;
		}
		public boolean accept(File f) {
			if (f.isDirectory()) { return true; }
			String extension = getExtension(f);
			if (extension != null) {
				if (extension.equals("xml")) { return true; }
				else { return false; }
			}
			return false;
		}
		//The description of this filter
		public String getDescription() { return "Конфигурация (xml)"; }
	}
	static public void open(String[] args) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(BeanConfig.getConfigDir()));
		ConfFilter filter = new ConfFilter();
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			Debug.notice("Начало загрузки конфигурации");
			infoSystem = new InfoSystem();
			integrator = new Integrator(chooser.getSelectedFile().getAbsolutePath());
			rootBean = integrator.getRootBean();
			if (rootBean != null) {
				integrator.addInitMenuListener(new GlobalMenu());
				infoSystem.init();
				infoSystem.evalTotalRisks();
				integrator.start(args);
				actions = new Actions();
			}
			else System.exit(0);
		}
		else System.exit(0);
	}
	public static void main(String[] args) { open(args); }
}


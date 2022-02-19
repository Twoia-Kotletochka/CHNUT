package lab_1;


public class TestDlgCommand {
	public static void main(String[] args) {
		DlgCommand dlg = new DlgCommand();
		dlg.setVisible(true);
		System.out.println(dlg.getMap());
		dlg.dispose();
	}
}


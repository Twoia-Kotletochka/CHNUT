package servlet;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/TableCreator")
public class TableCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TableCreator() {
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int amount = Integer.parseInt(request.getParameter("amount"));
		int rowCount = Integer.parseInt(request.getParameter("repeat"));
		String[][] table = createTable(amount,rowCount);
		request.setAttribute("table", table);
		request.getRequestDispatcher("ShowTable.jsp").forward(request, response);
	}
	
	private String[][] createTable(int amount, int rowCount) {
		String[][] table = new String[rowCount][3];
		DecimalFormat dcfr = new DecimalFormat("0.000");
		for (int r = 0; r < rowCount; r++) {
			Collection<Double> coll = new Vector();
			double sr = 0;
			for (int i = 0; i < amount; i++) {
				double x = Math.random();
				sr += x;
				coll.add(x);
			}
			sr /= amount;
			table[r][0] = dcfr.format(sr);
			double d = 0;
			for (double x : coll) {
				d += (x - sr) * (x - sr);
			}
			d /= coll.size();
			table[r][1] = dcfr.format(d);
			table[r][2] = dcfr.format(Math.sqrt(d));
		}
		return table;
	}
}


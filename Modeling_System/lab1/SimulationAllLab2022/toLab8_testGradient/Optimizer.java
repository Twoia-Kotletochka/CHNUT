package toLab8_testGradient;

public class Optimizer {
	private java.lang.String planName;

	private double[][] plan;

	private double[][] planFull = { { -1, -1, -1 }, { -1, -1, 1 },
			{ -1, 1, -1 }, { -1, 1, 1 }, { 1, -1, -1 }, { 1, -1, 1 },
			{ 1, 1, -1 }, { 1, 1, 1 } };

	private double[][] planPlus = { { -1, -1, 1 }, { -1, 1, -1 },
			{ 1, -1, -1 }, { 1, 1, 1 } };

	private double[][] planMinus = { { 1, 1, -1 }, { 1, -1, 1 }, { -1, 1, 1 },
			{ -1, -1, -1 } };

	private double searchStep;

	private int numberOfTest;

	private boolean printSearchProtocol;

	private IStepTimer stepTimer;

	private IController controller;

	private widgets.Painter painter;

	/**
	 * TauSystemOptimizer constructor comment.
	 */
	public Optimizer() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (06.12.2005
	 * 21:27:06)
	 */
	public void avtoSet() {
		numberOfTest = 0;
		if (painter != null && painter.getDiagram() != null)
			painter.getDiagram().clear();
		printToProtocol("�������������� ��������� ����������");
		printToProtocol("���� ������������ ��� ������ ��������� - " + planName);
		printToProtocol("��� ��� ������ ���������" + " " + this.searchStep);
		// ������ �������� � ������� �����
		double[] startArray = controller.getParameters();
		// ������ �������� � ����������� �����
		double[] parametersArray = new double[3];
		// ���������� ����� �� ������� ��������� ��� �������� ������� ������
		double x0 = 0;
		double x1 = 33333; // null = 33333
		double x2 = 0;
		double x3 = 0;
		// �������� ������� ������� � ������ ������� ������
		double y0 = this.testFor(startArray);
		double y1 = 0;
		double y2 = 0;
		double y3 = 0;
		// ��������� ��������� �� ���� �����
		double xmin = 0;
		double min = 0;
		// ������� ����� ���������
		int f = 0;
		printToProtocol("������� �����:");
		printToProtocol(startArray[0] + " " + startArray[1] + " "
				+ startArray[2] + "   " + y0);
		printToProtocol("������ ��������� � ������� �����:");
		// ������ ������� ���������
		double[][] resultArray = this.findGradientAt(startArray);
		// resultArray[0] - ���������� ������������� ��� ������ ������ ����� (4
		// ��� 8)
		// resultArray[1] - ���������� ������� ��������� (3 ��.)
		double[] gradientArray = resultArray[1];
		printToProtocol("�������� ������������ ������ � ������������ ������� �����:");
		String str = "";
		for (int i = 0; i < resultArray[0].length; i++)
			str += resultArray[0][i] + " ";
		printToProtocol(str);
		printToProtocol("�������� ��������� ���������� ������������ ������� �����:");
		printToProtocol(resultArray[1][0] + " " + resultArray[1][1] + " "
				+ resultArray[1][2]);
		// ��������� ����� �� ���������� �����
		boolean flag = false;
		for (int i = 0; i < resultArray[0].length; i++) {
			if (resultArray[0][i] < y0) {
				flag = true;
				break;
			}
		}
		while (flag) {
			printToProtocol("����� ����� ���������� ����������");
			printToProtocol("������� ������� �� �������� ������ ���� � ����������� ���������");
			for (int i = 0; i < startArray.length; i++) {
				parametersArray[i] = this.searchStep * gradientArray[i]
						+ startArray[i];
			}
			y1 = this.testFor(parametersArray);
			printToProtocol(parametersArray[0] + " " + parametersArray[1] + " "
					+ parametersArray[2] + "   " + y1);
			if (y1 > y0) {
				printToProtocol("�� �����������. ��������� � �������� ������� ����������� �����");
				min = resultArray[0][0];
				int index = 0;
				for (int i = 0; i < resultArray[0].length; i++) {
					if (resultArray[0][i] < min) {
						min = resultArray[0][i];
						index = i;
					}
				}
				for (int i = 0; i < startArray.length; i++) {
					parametersArray[i] = this.plan[index][i] * this.searchStep
							+ startArray[i];
				}
				y0 = min;
			} else {
				printToProtocol("�������. ����� ��������� ������");
				printToProtocol("������ � ����������� ��������� �� ������ ���������");
				f = 2;
				x0 = 0;
				x1 = this.searchStep;
				f = this.nextFibo(f);
				x3 = f * this.searchStep + x0;
				printToProtocol("����� ��������� - " + f + ". ����� �3 = " + x3);
				for (int i = 0; i < startArray.length; i++) {
					parametersArray[i] = gradientArray[i] * x3 + startArray[i];
				}
				y3 = this.testFor(parametersArray);
				printToProtocol(parametersArray[0] + " " + parametersArray[1]
						+ " " + parametersArray[2] + "   " + y3);
				while (y3 < y1) {
					printToProtocol("�������. ��������� ����� ������� � ����� ��������� ������");
					x0 = x1;
					y0 = y1;
					x1 = x3;
					y1 = y3;
					printToProtocol("x0 = " + x0 + "  x1 = " + x1);
					printToProtocol("y0 = " + y0 + "  y1 = " + y1);
					f = this.nextFibo(f);
					x3 = f * this.searchStep + x0;
					printToProtocol("����� ��������� - " + f + ". ����� �3 = "
							+ x3);
					for (int i = 0; i < startArray.length; i++) {
						parametersArray[i] = gradientArray[i] * x3
								+ startArray[i];
					}
					y3 = this.testFor(parametersArray);
				}
				x2 = 33333; // 33333 ��� null
				printToProtocol("����� ����������, ����� ������ ��������");
				if (x1 == 33333) {
					x1 = this.prevFibo(this.prevFibo(f)) * searchStep + x0;
					printToProtocol("�������� ����������� � ����� �1 = " + x1);
					for (int i = 0; i < startArray.length; i++) {
						parametersArray[i] = gradientArray[i] * x1
								+ startArray[i];
					}
					y1 = this.testFor(parametersArray);
					printToProtocol(parametersArray[0] + " "
							+ parametersArray[1] + " " + parametersArray[2]
							+ "   " + y1);
				}
				if (x2 == 33333) {
					x2 = this.prevFibo(f) * searchStep + x0;
					printToProtocol("�������� ����������� � ����� �2 = " + x2);
					for (int i = 0; i < startArray.length; i++) {
						parametersArray[i] = gradientArray[i] * x2
								+ startArray[i];
					}
					y2 = this.testFor(parametersArray);
					printToProtocol(parametersArray[0] + " "
							+ parametersArray[1] + " " + parametersArray[2]
							+ "   " + y2);
				}
				printToProtocol("x0 = " + x0 + "  x1 = " + x1 + "  x2 = " + x2
						+ "  x3 = " + x3);
				printToProtocol("y0 = " + y0 + "  y1 = " + y1 + "  y2 = " + y2
						+ "  y3 = " + y3);
				while (f != 3) {
					f = this.prevFibo(f);
					if (y1 < y2) {
						x3 = x2;
						y3 = y2;
						x2 = x1;
						y2 = y1;
						x1 = 33333;
					} else {
						x0 = x1;
						y0 = y1;
						x1 = x2;
						y1 = y2;
						x2 = 33333;
					}
					if (x1 == 33333) {
						x1 = this.prevFibo(this.prevFibo(f)) * searchStep + x0;
						printToProtocol("�������� ����������� � ����� �1 = "
								+ x1);
						for (int i = 0; i < startArray.length; i++) {
							parametersArray[i] = gradientArray[i] * x1
									+ startArray[i];
						}
						y1 = this.testFor(parametersArray);
						printToProtocol(parametersArray[0] + " "
								+ parametersArray[1] + " " + parametersArray[2]
								+ "   " + y1);
					}

					if (x2 == 33333) {
						x2 = this.prevFibo(f) * searchStep + x0;
						printToProtocol("�������� ����������� � ����� �2 = "
								+ x2);
						for (int i = 0; i < startArray.length; i++) {
							parametersArray[i] = gradientArray[i] * x2
									+ startArray[i];
						}
						y2 = this.testFor(parametersArray);
						printToProtocol(parametersArray[0] + " "
								+ parametersArray[1] + " " + parametersArray[2]
								+ "   " + y2);
					}
				} // while(f != 3)
				printToProtocol("�������� ������� �� ��������");
				if (y1 < y2) {
					if (y0 < y1) {
						xmin = x0;
					} else {
						xmin = x1;
						y0 = y1;
					}
				} else {
					if (y3 < y2) {
						xmin = x3;
						y0 = y3;
					} else {
						xmin = x2;
						y0 = y2;
					}
				}
				printToProtocol("�������� ���������� = " + xmin
						+ " ��� ����� ������� �����");
				for (int i = 0; i < startArray.length; i++)
					startArray[i] = gradientArray[i] * xmin + startArray[i];
			}
			resultArray = this.findGradientAt(startArray);
			gradientArray = resultArray[1];
			// ��������� ����� �� ���������� �����
			flag = false;
			for (int i = 0; i < resultArray[0].length; i++) {
				if (resultArray[0][i] < y0) {
					flag = true;
					break;
				}
			}
		}
		// �����. ����� ����������� �����
		y0 = this.testFor(startArray);
		printToProtocol("---=������� ����������� �����=---");
		printToProtocol(startArray[0] + " " + startArray[1] + " "
				+ startArray[2] + "   " + y0);
	}

	/**
	 * Insert the method's description here. Creation date: (06.12.2005
	 * 19:12:00)
	 * 
	 * @return int[][]
	 */
	private double[][] findGradientAt(double[] anArray) {
		// ������� ����������� �������������
		double[] a = new double[this.plan.length];
		// ��������� ���������� ��� �������� ������������
		double[] testArray = new double[3];
		// �������� ������������ � ������������ � ������
		for (int i = 0; i < plan.length; i++) {
			for (int j = 0; j < anArray.length; j++) {
				// ������������ ��������� ����������
				testArray[j] = plan[i][j] * searchStep + anArray[j];
			}
			// �������� ������������ � ������������ �� ������� ����� �
			// ���������� ��������� ��� ���� ������.
			a[i] = this.testFor(testArray);
		}
		double[] b = new double[3];// ������ ������������� ��������� ���������
		for (int j = 0; j < b.length; j++) {
			b[j] = 0;
			for (int i = 0; i < a.length; i++) {
				b[j] = a[i] * plan[i][j] / a.length + b[j];
			}
		}
		// ������� ���������� �������������� ���������
		double modul = java.lang.Math.sqrt(b[0] * b[0] + b[1] * b[1] + b[2]
				* b[2]);
		double[] grad = new double[3];
		for (int i = 0; i < grad.length; i++) {
			grad[i] = 0 - b[i] / modul;
		}
		// ���������� ��������� ��� ������ �� ���� ��������
		double[][] resultArray = new double[2][];
		// ������� ����������� ������������� ��� ������ ������ �����
		resultArray[0] = a;
		// ���������� ������� ���������
		resultArray[1] = grad;
		return resultArray;
	}

	/**
	 * Insert the method's description here. Creation date: (05.12.2005
	 * 17:52:19)
	 * 
	 * @return int
	 * @param param
	 *            int
	 */
	private int nextFibo(int param) {
		/*
		 * |f1 f2 f| f1:=0. f2:=1. [f:=f1+f2. f<=anInt]
		 * whileTrue:[f1:=f2.f2:=f]. ^f
		 */
		int f1 = 0;
		int f2 = 1;
		int f = f1 + f2;
		do {
			f1 = f2;
			f2 = f;
			f = f1 + f2;
		} while (f <= param);
		return f;
	}

	/**
	 * Insert the method's description here. Creation date: (05.12.2005
	 * 22:12:35)
	 */
	private int prevFibo(int param) {
		/*
		 * predFibo:anInt |f1 f2 f| f1:=0. f2:=1. [f:=f1+f2. f<anInt]
		 * whileTrue:[f1:=f2.f2:=f]. ^f2
		 */
		int f1 = 0;
		int f2 = 1;
		int f = 0;
		do {
			f = f1 + f2;
			f1 = f2;
			f2 = f;
		} while (f < param);
		return f1;

	}

	/**
	 * Insert the method's description here. Creation date: (10.12.2005 0:08:40)
	 * 
	 * @param string
	 *            java.lang.String
	 */
	private void printToProtocol(String newString) {
		if (printSearchProtocol)
			System.out.println(newString);
	}

	/**
	 * Insert the method's description here. Creation date: (17.04.2006
	 * 21:25:43)
	 * 
	 * @param newController
	 *            toLab8_testGradient.IController
	 */
	public void setIController(IController newController) {
		controller = newController;
	}

	/**
	 * Insert the method's description here. Creation date: (17.04.2006
	 * 21:25:10)
	 * 
	 * @param newStepTimer
	 *            toLab8_testGradient.IStepTimer
	 */
	public void setIStepTimer(IStepTimer newStepTimer) {
		stepTimer = newStepTimer;
	}

	/**
	 * Insert the method's description here. Creation date: (19.04.2006
	 * 19:30:04)
	 * 
	 * @param newPainter
	 *            paint.Painter
	 */
	public void setPainter(widgets.Painter newPainter) {
		painter = newPainter;
	}

	/**
	 * Insert the method's description here. Creation date: (06.12.2005 0:30:50)
	 * 
	 * @param param
	 *            int
	 */
	public void setPlan(int param) {
		if (param == 2) {
			plan = planMinus;
			planName = "������� - 1";
		}
		if (param == 1) {
			plan = planPlus;
			planName = "������� + 1";
		}
		if (param == 0) {
			plan = planFull;
			planName = "������";
		}
	}

	/**
	 * Insert the method's description here. Creation date: (19.04.2006
	 * 20:42:25)
	 * 
	 * @param newPrintSearchProtocol
	 *            boolean
	 */
	public void setPrintSearchProtocol(boolean newPrintSearchProtocol) {
		printSearchProtocol = newPrintSearchProtocol;
	}

	/**
	 * Insert the method's description here. Creation date: (17.04.2006
	 * 21:16:57)
	 * 
	 * @param newSearchStep
	 *            double
	 */
	public void setSearchStep(double newSearchStep) {
		searchStep = newSearchStep;
	}

	/**
	 * Insert the method's description here. Creation date: (06.12.2005
	 * 19:57:38)
	 * 
	 * @return double
	 */
	private double testFor(double[] anArray) {
		controller.setParameters(anArray);
		stepTimer.test();
		double result = controller.getResult();
		if (painter != null && painter.getDiagram() != null)
			painter.drawOvalAtXY((float) numberOfTest, (float) result, 1, 1);
		numberOfTest += 1;
		numberOfTest %= 100;
		return result;
	}
}

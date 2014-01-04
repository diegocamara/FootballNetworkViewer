package business;

import java.awt.Point;

public class Ball implements Runnable {

	Point point;
	Point pontoA;
	Point pontoB;
	int xInicial;
	int xFinal;
	Field field;
	
	public Ball(Point pontoA, Point pontoB, int xInicial, int xFinal,
			Field field) {
		this.pontoA = pontoA;
		this.pontoB = pontoB;
		this.xInicial = xInicial;
		this.xFinal = xFinal;
		this.field = field;

		setBallPoint(pontoA, pontoB);

	}

	private void setBallPoint(Point pontoA, Point pontoB) {
		if (xInicial <= xFinal) {
						
			int a = (pontoB.y - pontoA.y) / (pontoB.x - pontoA.x);
			int b = -(a * pontoA.x) + pontoA.y;

			int y = a * xInicial + b;

			this.point = new Point(xInicial++, y);
		}
	}

	@Override
	public void run() {

		while (true) {

			setBallPoint(pontoA, pontoB);
			this.field.repaint();
			
			//System.out.println("inicial " + xInicial);
			//System.out.println("Final " + xFinal);

			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {

			}

		}

	}// fim do método run.

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

}// fim da classe Ball.

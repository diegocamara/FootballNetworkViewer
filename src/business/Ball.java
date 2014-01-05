package business;

import java.awt.Point;

public class Ball implements Runnable {

	Point point;
	Point pontoA;
	Point pontoB;
	int xInicial;
	int xFinal;
	int yInicial;
	int yFinal;
	boolean continueProcess;
	Field field;

	public Ball(Point pontoA, Point pontoB, Field field) {
		setPoints(pontoA, pontoB, field);
		setBallPoint(pontoA, pontoB);
	}

	private void setPoints(Point pontoA, Point pontoB, Field field) {
		this.pontoA = pontoA;
		this.pontoB = pontoB;
		this.xInicial = pontoA.x;
		this.xFinal = pontoB.x;
		this.yInicial = pontoA.y;
		this.yFinal = pontoB.y;
		this.field = field;
		this.continueProcess = true;
	}
	
	private synchronized void setBallPoint(Point pontoA, Point pontoB) {

		// Equação da reta para intervalos x > 0;
		if (xInicial < xFinal) {

			int a = (pontoA.y - pontoB.y);
			int b = (pontoB.x - pontoA.x);
			int c = (pontoA.x * pontoB.y) - (pontoB.x * pontoA.y);

			int y = ((-a * xInicial) - c) / b;

			this.point = new Point(xInicial, y);

			xInicial += 3;
			
			if (xInicial >= xFinal) {
				continueProcess = false;
			}

			// Equação da reta para intervalos x < 0;
		} else if (xInicial > xFinal) {

			int a = (pontoA.y - pontoB.y);
			int b = (pontoB.x - pontoA.x);
			int c = (pontoA.x * pontoB.y) - (pontoB.x * pontoA.y);

			int y = ((-a * xInicial) - c) / b;

			this.point = new Point(xInicial, y);
			
			xInicial -= 3;

			if (xInicial <= xFinal) {
				continueProcess = false;
			}

		} else {

			if (pontoA.y > pontoB.y) {
				this.point = new Point(xInicial, yInicial);
				yInicial -= 2;
				
				if (yInicial <= yFinal) {
					continueProcess = false;
				}
				
			} else {
				this.point = new Point(xInicial, yInicial++);
				yInicial += 2;
				
				if (yInicial >= yFinal) {
					continueProcess = false;
				}
				
			}

			

		}

	}// fim do método setBallPoint.
		
	public void updatePoints(Point pontoA, Point pontoB){
		this.pontoA = pontoA;
		this.pontoB = pontoB;		
		this.xFinal = pontoB.x;
		this.yInicial = pontoA.y;
		this.yFinal = pontoB.y;
		
	}

	@Override
	public void run() {

		while (true) {

			setBallPoint(pontoA, pontoB);
			this.field.repaint();

			if (!continueProcess) {
				break;
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {

			}

		}

	}// fim do mÃ©todo run.

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

}// fim da classe Ball.

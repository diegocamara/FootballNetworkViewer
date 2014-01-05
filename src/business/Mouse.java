package business;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

	Field field;
	Point mouseInPanel;
	int pontoInicialClickX = 0;
	int pontoInicialClickY = 0;

	boolean mouseClicaoMovendo = false;

	public Mouse(Field field, Point mouseInPanel) {
		this.field = field;
		this.mouseInPanel = mouseInPanel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		// atualizando mouse na tela.
		mouseInPanel.x = e.getX();
		mouseInPanel.y = e.getY();

		for (Player player : field.getPlayers()) {

			// Se estiver dentro do raio.
			if (player.pointDistanceMouse <= player.r) {

				// atualizando posição do circulo quando o mouse se move
				// sendo
				// clicado.
				player.x = player.posAnteriorX
						+ (mouseInPanel.x - pontoInicialClickX);
				player.y = player.posAnteriorY
						+ (mouseInPanel.y - pontoInicialClickY);

				// atualizando centro do circulo.
				player.center = new Point(player.x + (player.w / 2), player.y
						+ (player.h / 2));

				// Atualiza em a lista de distancias player para player enquanto
				// houver drag.
				// player.setPointDistancePlayer(field.players);

			}

		}

		field.repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseInPanel.x = e.getX();
		mouseInPanel.y = e.getY();

		for (Player player : field.getPlayers()) {

			player.pointDistanceMouse = (int) player.center
					.distance(mouseInPanel);

		}

		field.repaint();

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getClickCount() == 2) {

			// pegando o player que foi clicado duas vezes.
			for (Player player : field.getPlayers()) {

				if (player.pointDistanceMouse <= player.r) {

					/*
					 * Se o primeiro player for clicado duas vezes, configura o
					 * playerInicial com o player clicado.
					 * 
					 * Se o primeiro player for clicado duas vezes quando já
					 * estiver configurado o player inicial é configurado como
					 * null.
					 * 
					 * Se o primeiro player já estiver configurado e um player
					 * diferente for clicado duas vezes, o playerFinal é
					 * configurado com o player clicado após o primeiro.
					 * 
					 * Se o segundo player for clicado duas vezes quando já
					 * estiver configurado o player final é configurado como
					 * null.
					 */

					if (field.getPlayerInicial() != null) {

						if (player.equals(field.getPlayerInicial())) {
							field.setPlayerInicial(null);
						} else {

							if (field.getPlayerFinal() != null) {
								if (player.equals(field.getPlayerFinal())) {
									field.setPlayerFinal(null);
								}
							} else {
								field.setPlayerFinal(player);
							}

						}

					} else {
						if (!player.equals(field.getPlayerFinal())) {
							field.setPlayerInicial(player);
						}else{
							field.setPlayerFinal(null);
						}
					}

				}

			}// fim do for.

			
		}

	}// fim do método mouseClicked.

	@Override
	public void mousePressed(MouseEvent e) {
		pontoInicialClickX = e.getX();
		pontoInicialClickY = e.getY();

		// atualizando posiï¿½ï¿½o anterior do objeto Player para configurar o
		// movimento.
		for (Player player : field.getPlayers()) {

			player.posAnteriorX = player.x;
			player.posAnteriorY = player.y;

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		// atualizando valores gerais.
		for (Player player : field.getPlayers()) {

			player.setPointDistancePlayer(field.getPlayers());

			// saindo da colisÃ£o.
			while (player.hasCollision()) {
				// System.out.println("Collision");

				player.x += 1;

				// atualizando centro do circulo.
				player.center = new Point(player.x + (player.w / 2), player.y
						+ (player.h / 2));
				player.setPointDistancePlayer(field.getPlayers());

			}

		}

		// System.out.println(field.filter(field.players).size());

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

package business;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JPanel;

public class Field extends JPanel {

	private final int WIDTH = 900;
	private final int HEIGHT = 600;
	private final int PLAYER_WIDTH = 50;
	private final int PLAYER_HEIGHT = 50;
	
	private List<Player> players;
	
	private Player playerInicial;
	private Player playerFinal;
	
	Point mouseInPanel;
	Point p1;
	Point p2;
	Ball ball;
	Thread t1;
	

	public Field() {
		
		setSize(new Dimension(WIDTH, HEIGHT));
		
		
		p1 = new Point(50,300);
		p2 = new Point(50,150);
		//ball = new Ball(p1,p2,50,100,this);
		
		players = new ArrayList<>();

		
		for(int i = 0; i < 11; ++i){
			players.add(new Player(25+new Random().nextInt(WIDTH-200), 25 + new Random().nextInt(HEIGHT-200), PLAYER_WIDTH, PLAYER_HEIGHT, new String(""+i)));
		}
		

		for(int i = 0; i < 11; ++i){
			players.get(i).setPointDistancePlayer(players);			
		}		
		
		
		ball = new Ball(players.get(0).center,players.get(0).adjacencias.get(0).center,this);
		//ball = new Ball(p1,p2,this);	
		
		
		t1 = new Thread(ball);
		t1.start();	
		
		outOfCollision();
		
		mouseInPanel = new Point();

		Mouse mouse = new Mouse(this, mouseInPanel);

		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		
		

	}

	private void outOfCollision() {
		for (Player player : this.getPlayers()) {

			player.setPointDistancePlayer(this.getPlayers());

			// saindo da colisão.
			while (player.hasCollision()) {
				// System.out.println("Collision");

				player.x += 1;

				// atualizando centro do circulo.
				player.center = new Point(player.x + (player.w / 2), player.y
						+ (player.h / 2));
				player.setPointDistancePlayer(this.getPlayers());

			}

		}
	}
	
	public List<Player> filter(List<Player> players){
		
		Set<Player> filterPlayerList = new LinkedHashSet<>(); 
		
		for(Player player : players){
			
			for(Player playerAdjacente : player.adjacencias){
				filterPlayerList.add(playerAdjacente);
			}
			
		}
		
		List<Player> filterList = new ArrayList<>();
		
		for(Player playerFilter : filterPlayerList){
			filterList.add(playerFilter);
		}
		
		return filterList;
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		
		
		Graphics2D g2 = (Graphics2D) g.create();

		for (Player player : players) {
			
			g2.setColor(Color.BLACK);
			
			g2.fillArc(player.getX(), player.getY(), player.getW(),
					player.getH(), 0, 360);
			//g2.drawString(player.center + " ", 30, 30);
			g2.drawString("" + player.x + " , " + player.y, player.x, player.y);
			g2.drawString("Power " + player.power, player.x, player.y-(player.h/2));
			g2.setColor(Color.WHITE);
			g2.drawString(player.number,player.center.x, player.center.y);
			g2.setColor(Color.BLACK);			
									
			//g2.drawLine(p1.x, p1.y, p2.x, p2.y);
			
			g2.setColor(Color.WHITE);		
				g2.fillArc(ball.point.x-5, ball.point.y-5, 10, 10, 0, 360);
			g2.setColor(Color.BLACK);
				ball.updatePoints(players.get(0).center,players.get(0).adjacencias.get(0).center);
						
			//Todos os caminhos possiveis.
			/*
			//Desenhando as linhas do player atual para o outros players.
			for(Point point : player.pointDistancePlayerCenter){
				
				//g2.drawLine(player.center.x, player.center.y, point.x, point.y);
				
				
			}
			*/
			
			//Caminhos adjacentes de acordo com a potência do chute (power);
			/*
			for(Player playerAdjacente : player.adjacencias){
				g2.drawLine(player.center.x, player.center.y, playerAdjacente.center.x, playerAdjacente.center.y);
			}
			*/
			
			//Desenhando linhas de distancia de acordo com a potência do chute(power).
			for(Player playerAdjacente : filter(players)){				
				
				// Como os players adjacentes estar�o parados a lista de dist�ncias de player
				// para player � atualizada quando o bot�o do mouse � solto.
				
				// Calcula a dist�ncia para o desenho na tela enquanto houver movimento.
				int distancia = (int) player.center.distance(playerAdjacente.center);
				
								
				if(player.power >= distancia)
				g2.drawLine(player.center.x, player.center.y, playerAdjacente.center.x, playerAdjacente.center.y);
								
			}	
			
		}

				
		g2.drawString(mouseInPanel.toString(), 10, 10);
		
		//Desenha a marca do player que n�o estiver null.
		if(getPlayerInicial() != null){
			g2.setColor(Color.RED);
			g2.fillArc(getPlayerInicial().x, getPlayerInicial().y, getPlayerInicial().getW(), getPlayerInicial().getH(), 0, 360);
			g2.setColor(Color.BLACK);
		}
		
		if(getPlayerFinal() != null){
			g2.setColor(Color.BLUE);
			g2.fillArc(getPlayerFinal().x, getPlayerFinal().y, getPlayerFinal().getW(), getPlayerFinal().getH(), 0, 360);
			g2.setColor(Color.BLACK);
		}
		

		// g2.drawImage(fieldImage,0,0,this);

	}// fim do método paintComponent.

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Player getPlayerInicial() {
		return playerInicial;
	}

	public void setPlayerInicial(Player playerInicial) {
		this.playerInicial = playerInicial;
	}

	public Player getPlayerFinal() {
		return playerFinal;
	}

	public void setPlayerFinal(Player playerFinal) {
		this.playerFinal = playerFinal;
	}
				
}// fim da classe Field
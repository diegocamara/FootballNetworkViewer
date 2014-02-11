package business;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.ApplicationWindow;
import presentation.Labels;

public class Field extends JPanel {

	private final int WIDTH = 900;
	private final int HEIGHT = 600;
	private final int PLAYER_WIDTH = 30;
	private final int PLAYER_HEIGHT = 30;
	
	private List<Player> players;
	private VetorDeDistancia vetorDeDistancia;
	
	private Player playerInicial;
	private Player playerFinal;
	
	Point mouseInPanel;
	
	Thread t1;
	private Ball ball;
	private BufferedImage fieldImage;
	private BufferedImage ballImage;
	
	private JLabel playerInicialLabel;
	private JLabel playerFinalLabel;
	
	

	public Field() {
						
		setSize(new Dimension(WIDTH, HEIGHT));
		try{
			fieldImage = ImageIO.read(getClass().getResource("/images/field.png"));	
			ballImage = ImageIO.read(getClass().getResource("/images/soccerBall.png"));
		}catch(IOException e){
			
		}
		players = new ArrayList<>();

		playerInicialLabel = new JLabel(Labels.MENSAGEM_PLAYER_NAO_SELECIONADO_LABEL);
		playerFinalLabel = new JLabel(Labels.MENSAGEM_PLAYER_NAO_SELECIONADO_LABEL);
		
		
		for(int i = 0; i < 11; ++i){
			players.add(new Player(25+new Random().nextInt(WIDTH-200), 25 + new Random().nextInt(HEIGHT-200), PLAYER_WIDTH, PLAYER_HEIGHT, new String(""+i)));
		}
		

		for(int i = 0; i < 11; ++i){
			players.get(i).setPointDistancePlayer(players);			
		}		
				
		
		outOfCollision();
		
		mouseInPanel = new Point();

		Mouse mouse = new Mouse(this, mouseInPanel);

		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
	}

	private void outOfCollision() {
		for (Player player : this.getPlayers()) {

			player.setPointDistancePlayer(this.getPlayers());

			// saindo da colis√£o.
			while (player.hasCollision()) {
				
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
		
		g2.drawImage(fieldImage, 0, 0, WIDTH, HEIGHT, null);
		
		
		for (Player player : players) {
			
			g2.setColor(Color.BLACK);
			
			g2.fillArc(player.getX(), player.getY(), player.getW(),
					player.getH(), 0, 360);
			
			
			g2.setColor(Color.WHITE);
			g2.drawString(player.number,player.center.x-3, player.center.y+3);
			g2.setColor(Color.BLACK);			
									
			
			if(ball != null){
			g2.setColor(Color.WHITE);		
				//g2.fillArc(ball.point.x-5, ball.point.y-5, 10, 10, 0, 360);
				g2.drawImage(ballImage, ball.point.x-25,ball.point.y-25,50,50,null);
			g2.setColor(Color.BLACK);
			}
			
			if(ball != null && !ball.continueProcess){
				ball = null;
				
			}
				
						
			//Todos os caminhos possiveis.
			/*
			//Desenhando as linhas do player atual para o outros players.
			for(Point point : player.pointDistancePlayerCenter){
				
				//g2.drawLine(player.center.x, player.center.y, point.x, point.y);
				
				
			}
			*/
			
			//Caminhos adjacentes de acordo com a pot√™ncia do chute (power);
			/*
			for(Player playerAdjacente : player.adjacencias){
				g2.drawLine(player.center.x, player.center.y, playerAdjacente.center.x, playerAdjacente.center.y);
			}
			*/
			
			//Desenhando linhas de distancia de acordo com a pot√™ncia do chute(power).
			for(Player playerAdjacente : filter(players)){				
				
				// Como os players adjacentes estar„o parados a lista de dist‚ncias de player
				// para player È atualizada quando o bot„o do mouse È solto.
				
				// Calcula a dist‚ncia para o desenho na tela enquanto houver movimento.
				int distancia = (int) player.center.distance(playerAdjacente.center);
				
								
				if(player.power >= distancia)
				g2.drawLine(player.center.x, player.center.y, playerAdjacente.center.x, playerAdjacente.center.y);
								
			}	
			
		}

				
				
		//Desenha a marca do player que n„o estiver null.
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
		

		//g2.drawImage(fieldImage,0,0,this);

	}// fim do m√©todo paintComponent.
	
	public boolean playersSelecionados(){
		return playerInicial != null && playerFinal != null;
	}

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

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public VetorDeDistancia getVetorDeDistancia() {
		return vetorDeDistancia;
	}

	public void setVetorDeDistancia(VetorDeDistancia vetorDeDistancia) {
		this.vetorDeDistancia = vetorDeDistancia;
	}

	public JLabel getPlayerInicialLabel() {
		return playerInicialLabel;
	}

	public void setPlayerInicialLabel(JLabel playerInicialLabel) {
		this.playerInicialLabel = playerInicialLabel;
	}

	public JLabel getPlayerFinalLabel() {
		return playerFinalLabel;
	}

	public void setPlayerFinalLabel(JLabel playerFinalLabel) {
		this.playerFinalLabel = playerFinalLabel;
	}
	
	
				
}// fim da classe Field
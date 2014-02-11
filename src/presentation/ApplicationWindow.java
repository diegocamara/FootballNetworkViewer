package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import business.Algorithm;
import business.Ball;
import business.Field;
import business.MenorCaminho;
import business.Player;
import business.PowerPanel;
import business.VetorDeDistancia;

public class ApplicationWindow extends JFrame {

	private final int WIDTH = 1024;
	private final int HEIGHT = 768;
	public static final int DISTANCIA_MINIMA_ADJACENTE = 200;

	private Field field;
	private JPanel headPanel;
	private JPanel centerPanel;
	private JLabel distanciaMinimaAdjacenteJLabel;
	private JTextField distanciaMinimaAdjacenteTextField;
	private PowerPanel powersPanel;
	private JButton executeButton;
	private JComboBox<String> algorithmList;
	private Algorithm algorithm;
	

	public ApplicationWindow(String titulo) {

		setLayout(new MigLayout("", "", "[][]"));

		String[] algorithms = { Labels.DIJKSTRA, Labels.VETOR_DE_DISTANCIA };

		algorithmList = new JComboBox<>(algorithms);
		executeButton = new JButton(Labels.EXECUTAR);
		distanciaMinimaAdjacenteJLabel = new JLabel(
				Labels.DISTANCIA_MINIMA_ADJACENTE_LABEL);
		distanciaMinimaAdjacenteTextField = new JTextField(
				Integer.toString(DISTANCIA_MINIMA_ADJACENTE));

		// Iniciando algorithm com Algorithm.DIJKSTRA.
		algorithm = Algorithm.DIJKSTRA;

		distanciaMinimaAdjacenteTextField
				.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// configurando a distancia minima de adjacencia para
						// todos os players.
						for (Player player : field.getPlayers()) {
							player.setPower(Integer
									.parseInt(distanciaMinimaAdjacenteTextField
											.getText()));
						}
						field.repaint();
					}
				});

		algorithmList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int algorithmSelected = algorithmList.getSelectedIndex();

				switch (algorithmSelected) {
				case 0: {
					algorithm = Algorithm.DIJKSTRA;
					powersPanel.enableDisablePanel(false);
					break;
				}
				case 1: {
					algorithm = Algorithm.VETOR_DE_DISTANCIA;
					powersPanel.enableDisablePanel(true);
					break;
				}
				}

			}
		});

		executeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (field.playersSelecionados()) {					
					
					if (algorithm.equals(Algorithm.DIJKSTRA)) {

												
						MenorCaminho.menorCaminho(field.getPlayers(),
								field.getPlayerInicial());
												
						List<Player> path = MenorCaminho.menorCaminhoList(field.getPlayerFinal());
						
						
						
						if(path.size() >= 2){
							
							String out = new String();
							
							for(int i = 0; i < path.size(); i++){
								out += String.format("%s: %s\n", "Passo " + (i+1), path.get(i));
							}
							
							JOptionPane.showMessageDialog(null, out);
							
						field.setBall(new Ball(path,field));
						}

												
						Thread tr = new Thread(field.getBall());
						tr.start();
						MenorCaminho.resetLabel(field.getPlayers());

					}
					if (algorithm.equals(Algorithm.VETOR_DE_DISTANCIA)) {
						List<Player> path = field.getVetorDeDistancia().getPath(field.getPlayerInicial(), field.getPlayerFinal());
						

						String out = new String();
						
						for(int i = 0; i < path.size(); i++){
							out += String.format("%s: %s\n", "Passo " + (i+1), path.get(i));
						}
						
						JOptionPane.showMessageDialog(null, out);
						
						
						field.setBall(new Ball(path, field));
						
						Thread tr = new Thread(field.getBall());
						tr.start();
						
					}

				}else{
					JOptionPane.showMessageDialog(null, Labels.PLAYERS_NAO_SELECIONADOS);
				}

			}
		});

		field = new Field();
		field.setVetorDeDistancia(new VetorDeDistancia(field));

		field.getVetorDeDistancia().tabelaInicial();		
		field.getVetorDeDistancia().propagar();	
		
		field.setLayout(new MigLayout());
		field.setBackground(Color.LIGHT_GRAY);		
		
		headPanel = new JPanel(new MigLayout("fill"));		
		headPanel.add(distanciaMinimaAdjacenteJLabel, "split 2");
		headPanel.add(distanciaMinimaAdjacenteTextField, "width :50:,right");
		headPanel.add(new JLabel("Jogador inicial:"));
		headPanel.add(field.getPlayerInicialLabel());		
		headPanel.add(new JLabel("Jogador final:"));
		headPanel.add(field.getPlayerFinalLabel());		
		headPanel.add(algorithmList, "width :200:,split 2,right");
		headPanel.add(executeButton);
		headPanel.setBackground(Color.WHITE);
		
		centerPanel = new JPanel(new MigLayout("", "[][]", ""));
		centerPanel.setBackground(Color.WHITE);

		powersPanel = new PowerPanel(field.getPlayers(), field);
		powersPanel.setBackground(Color.WHITE);
		powersPanel.enableDisablePanel(false);
		centerPanel.add(powersPanel,
				"width :150:, height :" + field.getHeight() + ":");

		centerPanel.add(field, "width :" + field.getWidth() + ":, height :"
				+ field.getHeight() + ":");

		add(headPanel, "wrap, growx");
		add(centerPanel, "grow");

		setTitle(titulo);
		setSize(new Dimension(WIDTH, HEIGHT));
		pack();
		setResizable(false);
		setLocationRelativeTo(null);

	}// fim do construtor Window.


	public JPanel getHeadPanel() {
		return headPanel;
	}


	public void setHeadPanel(JPanel headPanel) {
		this.headPanel = headPanel;
	}

	

	
	
}

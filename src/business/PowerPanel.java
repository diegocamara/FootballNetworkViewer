package business;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import presentation.Labels;

public class PowerPanel extends JPanel{

	private static int NUMERO_DE_JOGADORES = 11;
	
	List<JTextField> powersList;
	List<JLabel> playersPowerLabels;
	List<Player> players;
	JButton updateButton;
	Field field;
	
	public PowerPanel(List<Player> players, Field field){
		powersList = new ArrayList<>();	
		playersPowerLabels = new ArrayList<>();
		this.players = players;
		this.field = field;		
		updateButton = new JButton(Labels.REINICIAR);
		setLayout(new MigLayout("fill"));
		
		for(int i = 0; i < NUMERO_DE_JOGADORES; i++){
			
			// adicionando um JTextField para cada jogador.
			powersList.add(new JTextField());				
			
			// criando e configurando um JLabel para cada jogador.
			playersPowerLabels.add(new JLabel(Labels.JOGADOR_LABEL + i));				
			
			// configurando os JTextFields com as potencias de chute dos jogadores.
			powersList.get(i).setText(String.valueOf(players.get(i).getPower()));
			
			powersList.get(i).addActionListener(new UpdatePowersActionListener(players, field, powersList));
						
			// adicionando JLabels e JTextFields no JPanel.
			add(playersPowerLabels.get(i),"split 2");
			add(powersList.get(i), "growx,wrap");
									
		}		
					
		
		add(updateButton,"growx");
				
	}// fim do construtor.

	public List<JTextField> getPowersList() {
		return powersList;
	}

	public void setPowersList(List<JTextField> powersList) {
		this.powersList = powersList;
	}

	public List<JLabel> getPlayersPowerLabels() {
		return playersPowerLabels;
	}

	public void setPlayersPowerLabels(List<JLabel> playersPowerLabels) {
		this.playersPowerLabels = playersPowerLabels;
	}

	public JButton getUpdateButton() {
		return updateButton;
	}

	public void setUpdateButton(JButton updateButton) {
		this.updateButton = updateButton;
	}	
	
}// fim da classe PowerPanel.

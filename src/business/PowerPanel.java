package business;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import presentation.Labels;

public class PowerPanel extends JPanel {

	private static int NUMERO_DE_JOGADORES = 11;

	List<JButton> buttonList;
	List<JLabel> playersPowerLabels;
	List<Player> players;
	JButton updateButton;
	Field field;

	public PowerPanel(List<Player> players, Field field) {
		buttonList = new ArrayList<>();
		playersPowerLabels = new ArrayList<>();
		this.players = players;
		this.field = field;		
		setLayout(new MigLayout("fill"));

		for (int i = 0; i < NUMERO_DE_JOGADORES; i++) {

			// adicionando um JButton para cada jogador.
			buttonList.add(new JButton(Labels.TABELA));		
			
			// criando e configurando um JLabel para cada jogador.
			playersPowerLabels.add(new JLabel(Labels.JOGADOR_LABEL + i));

			
			 buttonList.get(i).addActionListener(new
			 TableActionListener(field.getPlayers().get(i), field, buttonList));

			// adicionando JLabels e JTextFields no JPanel.
			add(playersPowerLabels.get(i), "split 2");
			add(buttonList.get(i), "wrap");

		}		

	}// fim do construtor.

	public void enableDisablePanel(boolean eFlag){
		
		for(JButton button : buttonList){
			button.setEnabled(eFlag);
		}
		
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

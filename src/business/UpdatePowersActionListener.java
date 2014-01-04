package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;

public class UpdatePowersActionListener implements ActionListener {

	private List<Player> players;
	private Field field;
	List<JTextField> powersList;
	
		
	public UpdatePowersActionListener(List<Player> players, Field field, List<JTextField> powersList){
		this.players = players;
		this.field = field;
		this.powersList = powersList;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i < powersList.size(); i++){
			int value = Integer.parseInt(powersList.get(i).getText());
			
			this.players.get(i).setPower(value);
			
		}		
		
		field.repaint();
		
	}
	
	

}

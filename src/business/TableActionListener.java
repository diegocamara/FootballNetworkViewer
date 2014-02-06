package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;

import presentation.Labels;
import presentation.TableWindow;

public class TableActionListener implements ActionListener {

	private Player player;
	private Field field;
	List<JButton> buttonList;
	
		
	public TableActionListener(Player player, Field field, List<JButton> buttonList){
		this.player = player;
		this.field = field;
		this.buttonList = buttonList;		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		new TableWindow(Labels.TABELA + " " + player.getNumber(), player);
		
	}
	
	

}

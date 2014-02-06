import javax.swing.JFrame;

import presentation.ApplicationWindow;
import presentation.Labels;


public class Application {

	public static void main(String[] args){
		
		//JFrame application = new JFrame();
		//Field field = new Field();
		
		
		//application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//application.add(field);		
		//application.setSize(800,600);
		//application.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//application.setVisible(true);
		
		ApplicationWindow application = new ApplicationWindow(Labels.TITULO);
		application.setVisible(true);
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			
		
	}// fim do método main.
	
}// fim da classe Application.

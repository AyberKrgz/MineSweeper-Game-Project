import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class LaunchPage implements ActionListener{				// AYBERK

	JFrame frame = new JFrame("Minesweeper");
	JButton startButton = new JButton("Start");
	JLabel titleLabel = new JLabel("Minesweeper");			
	JButton quitButton = new JButton("Quit");

	public int size=400;
	
	LaunchPage(){

		//creating frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(size,size);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.revalidate();			
		frame.setLocationRelativeTo(null);

		//creating JLabel
		titleLabel.setBounds(123, 50, 200, 100);
		titleLabel.setFont(new Font(null,Font.PLAIN,25));
		frame.add(titleLabel);

		//creating start button
		startButton.setBounds(100,160,200,40);
		startButton.setFocusable(false);
		startButton.addActionListener(this);
		frame.add(startButton);

		//creating quit button
		quitButton.setBounds(100,210,200,40);
		quitButton.setFocusable(false);
		quitButton.addActionListener(this);
		frame.add(quitButton);
	}

	//Mouse click functions
	public void actionPerformed(ActionEvent e) {

		//Clicking to the start button
		if(e.getSource()==startButton){

			frame.dispose();
            Game_Page gamepage = new Game_Page();

		}

		//Clicking to the quit button
		if(e.getSource()==quitButton){
			System.exit(0);
		}
	}

}
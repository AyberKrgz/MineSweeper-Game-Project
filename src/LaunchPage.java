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


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(size,size);
		frame.setLayout(null);
		frame.setVisible(true);											//creating frame
		frame.revalidate();			
		frame.setLocationRelativeTo(null);
		
		titleLabel.setBounds(123, 50, 200, 100);
		titleLabel.setFont(new Font(null,Font.PLAIN,25));			//creating JLabel
		frame.add(titleLabel);
		
		startButton.setBounds(100,160,200,40);
		startButton.setFocusable(false);						//creating start button
		startButton.addActionListener(this);
		frame.add(startButton);
		
		quitButton.setBounds(100,210,200,40);
		quitButton.setFocusable(false);						//creating quit button
		quitButton.addActionListener(this);
		frame.add(quitButton);
	}



	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==startButton) {

			frame.dispose();
            Game_Page gamepage = new Game_Page();
			

		}
		
		if(e.getSource()==quitButton) {
			System.exit(0);
		}
	}
}
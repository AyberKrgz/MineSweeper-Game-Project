import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game_Page implements ActionListener {

	ArrayList<Integer> xCoordinate;
	ArrayList<Integer> yCoordinate;
	
	public int mines = 13;
	public final int size = 10;

	JFrame frame = new JFrame("Minesweeper");
	JPanel text_JPanel;
	JLabel text_field;
	JPanel button_JPanel;
    JButton[][] buttons;
	boolean [][] mine_locations= new boolean[size][size];
	int [][] neighbour= new int[size][size];
	boolean [][] is_visible = new boolean[size][size];


	Game_Page(){

		//Creating JFrame - ALP
		frame.setSize(700,700);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.revalidate();
		frame.setLocationRelativeTo(null);


		// Creating Text on top of the frame - ALP
		text_field=new JLabel();
		text_field.setHorizontalAlignment(JLabel.CENTER);
		text_field.setFont(new Font("Arial",Font.BOLD,20));
		text_field.setForeground(Color.GREEN);
		text_field.setText("MineSweeper");


		//Adding JLabel to JPanel and adding it top of the frame - ALP
		text_JPanel=new JPanel();
		text_JPanel.setVisible(true);
		text_JPanel.setBackground(Color.BLACK);
		text_JPanel.add(text_field);
		frame.add(text_JPanel, BorderLayout.NORTH);


		//It's equalizing the lengths of dimensions to size - ALP
		buttons = new JButton[size][size];


		//Creating JPanel for buttons -ALP
		button_JPanel = new JPanel();
		button_JPanel.setVisible(true);
		button_JPanel.setLayout(new GridLayout(size,size));


		//Creating all buttons and, adding them to JPanel - ALP
		for(int a=0; a<buttons.length; a++){
			for(int b=0; b<buttons[0].length; b++){
				buttons[a][b]=new JButton();
				buttons[a][b].setFocusable(false);
				buttons[a][b].setFont(new Font("Arial",Font.BOLD,16));
				buttons[a][b].addActionListener(this);
				buttons[a][b].setText("");
				buttons[a][b].setForeground(Color.BLACK);
				buttons[a][b].setBackground(Color.DARK_GRAY);
				button_JPanel.add(buttons[a][b]);
			}
		}


		//Adding button_JPanel to frame - ALP
		frame.add(button_JPanel);


    	Random random = new Random();
    	xCoordinate = new ArrayList<Integer>();
    	yCoordinate = new ArrayList<Integer>();

		//evaluating coordinates of mines - EGE
    	for (int i=0;i<mines;i++){
    		
    		int randomX=random.nextInt(size);
    		int randomY=random.nextInt(size);
    		
    		xCoordinate.add(randomX);
    		yCoordinate.add(randomY);
    	}

		//Checks if bombs overlapped - EGE
    	for (int a=0; a<mines; a++) {
    		for (int b=a+1; b<mines; b++) {
    			while(Objects.equals(xCoordinate.get(a), xCoordinate.get(b)) && Objects.equals(yCoordinate.get(a), yCoordinate.get(b))) {
    				a=0;
    				b=0;

					//If they are overlapped it will be set to new random values until they are not overlapped.
    				xCoordinate.set(a, random.nextInt(size));
    				yCoordinate.set(b, random.nextInt(size));
    			}
    		}
    	}

		//equalizing all elements to false - ALP
		for (int a=0; a<size; a++){
			for (int b=0; b<size; b++){
				mine_locations[a][b] = false;
				is_visible[a][b] = false;
			}
		}

		//set locations of mines to true on array - ALP
		for(int i=0; i<xCoordinate.size(); i++){
			mine_locations[yCoordinate.get(i)][xCoordinate.get(i)] = true;
		}


		//Setting neighbour counts  	ALL TOGETHER
		setNeighbourCount();

	}


	//Setting neighbour counts  	ALL TOGETHER
	public void setNeighbourCount(){

		for (int x=0; x<size; x++){
			for (int y=0; y<size; y++){

				int mines_count=0;

				if(!mine_locations[y][x]){

					//checking top left corner neighbours
					if (x==0 && y==0){
						if(mine_locations[y][x+1]){
							mines_count++;
						}
						if(mine_locations[y+1][x+1]){
							mines_count++;
						}
						if(mine_locations[y+1][x]){
							mines_count++;
						}
						neighbour[y][x] = mines_count;
					}

					//checking top right corner neighbours
					else if (x==size-1 && y==0){
						if(mine_locations[y+1][x]){
							mines_count++;
						}
						if(mine_locations[y+1][x-1]){
							mines_count++;
						}
						if(mine_locations[y][x-1]){
							mines_count++;
						}
						neighbour[y][x] = mines_count;
					}

					//checking bottom right corner neighbours
					else if (x==size-1 && y==size-1){
						if(mine_locations[y-1][x]){
							mines_count++;
						}
						if(mine_locations[y-1][x-1]){
							mines_count++;
						}
						if(mine_locations[y][x-1]){
							mines_count++;
						}
						neighbour[y][x] = mines_count;
					}

					//checking bottom left corner neighbours
					else if (x==0 && y==size-1){
						if(mine_locations[y-1][x]){
							mines_count++;
						}
						if(mine_locations[y][x+1]){
							mines_count++;
						}
						if(mine_locations[y-1][x+1]){
							mines_count++;
						}
						neighbour[y][x] = mines_count;
					}

					//checking top line neighbours
					else if (y==0){
						if(mine_locations[y][x+1]){
							mines_count++;
						}
						if(mine_locations[y][x-1]){
							mines_count++;
						}
						if(mine_locations[y+1][x+1]){
							mines_count++;
						}
						if(mine_locations[y+1][x]){
							mines_count++;
						}
						if(mine_locations[y+1][x-1]){
							mines_count++;
						}
						neighbour[y][x] = mines_count;
					}

					//checking left line neighbours
					else if(x==0){
						if(mine_locations[y-1][x]){
							mines_count++;
						}
						if(mine_locations[y+1][x]){
							mines_count++;
						}
						if(mine_locations[y-1][x+1]){
							mines_count++;
						}
						if(mine_locations[y][x+1]){
							mines_count++;
						}
						if(mine_locations[y+1][x+1]){
							mines_count++;
						}
						neighbour[y][x] = mines_count;
					}

					//checking bottom line neighbors
					else if(y==size-1){
						if(mine_locations[y][x+1]){
							mines_count++;
						}
						if(mine_locations[y][x-1]){
							mines_count++;
						}
						if(mine_locations[y-1][x+1]){
							mines_count++;
						}
						if(mine_locations[y-1][x]){
							mines_count++;
						}
						if(mine_locations[y-1][x-1]){
							mines_count++;
						}
						neighbour[y][x] = mines_count;
					}

					//checking right line neighbors
					else if(x==size-1){
						if(mine_locations[y-1][x]){
							mines_count++;
						}
						if(mine_locations[y+1][x]){
							mines_count++;
						}
						if(mine_locations[y][x-1]){
							mines_count++;
						}
						if(mine_locations[y-1][x-1]){
							mines_count++;
						}
						if(mine_locations[y+1][x-1]){
							mines_count++;
						}
						neighbour[y][x] = mines_count;
					}

					//checking inside fields' neighbours
					else{
						if(mine_locations[y-1][x-1]){
							mines_count++;
						}
						if(mine_locations[y-1][x]){
							mines_count++;
						}
						if(mine_locations[y-1][x+1]){
							mines_count++;
						}
						if(mine_locations[y][x-1]){
							mines_count++;
						}
						if(mine_locations[y][x+1]){
							mines_count++;
						}
						if(mine_locations[y+1][x-1]){
							mines_count++;
						}
						if(mine_locations[y+1][x]){
							mines_count++;
						}
						if(mine_locations[y+1][x+1]){
							mines_count++;
						}
						neighbour[y][x] = mines_count;

					}

				}
			}
		}

	}

	//A method for finishing the game and deciding on either the player won or lost.
	public void end_game(boolean win) {

		//Opening gif and launch page for lose situation - EGE
		if(!win) {
			LaunchPage launchPage = new LaunchPage(); 													  
			launchPage.frame.setLocation(1200,300);
			/*Bombed bombed = new Bombed();
			bombed.bombedframe.setLocation(500,400);*/
			sadAbdulhamid abdulhamid = new sadAbdulhamid();
			abdulhamid.abdulhamidFrame.setLocation(200,300);
		}

		//Opening gif for win situation - EGE
		if(win) {
			frame.dispose();
			WellDone wellDone = new WellDone();
			LaunchPage launchPage = new LaunchPage();
			wellDone.wellDoneFrame.setLocation(350,300);
		}

		//Mines are being marked as "*" - EGE
		for(int i=0; i<buttons.length; i++) {
			
			for(int j=0; j<buttons[0].length; j++) {
				
				buttons[j][i].setEnabled(false);
				
				for(int c=0; c<xCoordinate.size(); c++) {
					
					if(i==xCoordinate.get(c) && j==yCoordinate.get(c)) {
						
						buttons[j][i].setText("*");
						buttons[j][i].setBackground(Color.RED);
						
					}

				}
			}
		}

	}

	//A method for opening empty fields.		AYBERK - EGE
	public void openArea(int y, int x){
		
		if(!mine_locations[y][x] && neighbour[y][x]==0){

			//opening for top left corner
			if (y==0 && x==0){

				if(!is_visible[y+1][x]){
					buttons[y+1][x].setEnabled(false);
					is_visible[y+1][x] = true;
					buttons[y+1][x].setBackground(Color.GREEN);
					buttons[y+1][x].setText(Integer.toString(neighbour[y+1][x]));
					openArea(y+1, x);
				}
				if(!is_visible[y+1][x+1]){
					buttons[y+1][x+1].setEnabled(false);
					is_visible[y+1][x+1] = true;
					buttons[y+1][x+1].setBackground(Color.GREEN);
					buttons[y+1][x+1].setText(Integer.toString(neighbour[y+1][x+1]));
					openArea(y+1, x+1);

				}
				if(!is_visible[y][x+1]){
					buttons[y][x+1].setEnabled(false);
					is_visible[y][x+1] = true;
					buttons[y][x+1].setBackground(Color.GREEN);
					buttons[y][x+1].setText(Integer.toString(neighbour[y][x+1]));
					openArea(y, x+1);

				}

			}

			//opening for top right corner
			else if (x==size-1 && y==0){

				if(!is_visible[y][x-1]){
					buttons[y][x-1].setEnabled(false);
					is_visible[y][x-1] = true;
					buttons[y][x-1].setBackground(Color.GREEN);
					buttons[y][x-1].setText(Integer.toString(neighbour[y][x-1]));
					openArea(y, x-1);
				}
				if(!is_visible[y+1][x-1]){
					buttons[y+1][x-1].setEnabled(false);
					is_visible[y+1][x-1] = true;
					buttons[y+1][x-1].setBackground(Color.GREEN);
					buttons[y+1][x-1].setText(Integer.toString(neighbour[y+1][x-1]));
					openArea(y+1, x-1);
				}
				if(!is_visible[y+1][x]) {
					buttons[y+1][x].setEnabled(false);
					is_visible[y+1][x] = true;
					buttons[y+1][x].setBackground(Color.GREEN);
					buttons[y+1][x].setText(Integer.toString(neighbour[y + 1][x]));
					openArea(y+1, x);
				}

			}

			//opening for bottom right corner
			else if (x==size-1 && y==size-1){

				if(!is_visible[y-1][x-1]){
					buttons[y-1][x-1].setEnabled(false);
					is_visible[y-1][x-1] = true;
					buttons[y-1][x-1].setBackground(Color.GREEN);
					buttons[y-1][x-1].setText(Integer.toString(neighbour[y-1][x-1]));
					openArea(y-1, x-1);
				}
				if(!is_visible[y-1][x]){
					buttons[y-1][x].setEnabled(false);
					is_visible[y-1][x] = true;
					buttons[y-1][x].setBackground(Color.GREEN);
					buttons[y-1][x].setText(Integer.toString(neighbour[y-1][x]));
					openArea(y-1, x);
				}
				if(!is_visible[y][x-1]){
					buttons[y][x-1].setEnabled(false);
					is_visible[y][x-1] = true;
					buttons[y][x-1].setBackground(Color.GREEN);
					buttons[y][x-1].setText(Integer.toString(neighbour[y][x-1]));
					openArea(y, x-1);
				}

			}

			//opening bottom left corner neighbours
			else if (x==0 && y==size-1){

				if(!is_visible[y-1][x]){
					buttons[y-1][x].setEnabled(false);
					is_visible[y-1][x] = true;
					buttons[y-1][x].setBackground(Color.GREEN);
					buttons[y-1][x].setText(Integer.toString(neighbour[y-1][x]));
					openArea(y-1, x);
				}
				if(!is_visible[y-1][x+1]){
					buttons[y-1][x+1].setEnabled(false);
					is_visible[y-1][x+1] = true;
					buttons[y-1][x+1].setBackground(Color.GREEN);
					buttons[y-1][x+1].setText(Integer.toString(neighbour[y-1][x+1]));

				}
				if(!is_visible[y][x+1]){
					openArea(y-1, x+1);
					buttons[y][x+1].setEnabled(false);
					is_visible[y][x+1] = true;
					buttons[y][x+1].setBackground(Color.GREEN);
					buttons[y][x+1].setText(Integer.toString(neighbour[y][x+1]));
					openArea(y, x+1);
				}

			}

			//opening top line neighbours
			else if (y==0){

				if(!is_visible[y][x+1]){
					buttons[y][x+1].setEnabled(false);
					is_visible[y][x+1] = true;
					buttons[y][x+1].setBackground(Color.GREEN);
					buttons[y][x+1].setText(Integer.toString(neighbour[y][x+1]));
					openArea(y, x+1);
				}
				if(!is_visible[y][x-1]){
					buttons[y][x-1].setEnabled(false);
					is_visible[y][x-1] = true;
					buttons[y][x-1].setBackground(Color.GREEN);
					buttons[y][x-1].setText(Integer.toString(neighbour[y][x-1]));
					openArea(y, x-1);
				}
				if(!is_visible[y+1][x+1]){
					buttons[y+1][x+1].setEnabled(false);
					is_visible[y+1][x+1] = true;
					buttons[y+1][x+1].setBackground(Color.GREEN);
					buttons[y+1][x+1].setText(Integer.toString(neighbour[y+1][x+1]));
					openArea(y+1, x+1);
				}
				if(!is_visible[y+1][x]){
					buttons[y+1][x].setEnabled(false);
					is_visible[y+1][x] = true;
					buttons[y+1][x].setBackground(Color.GREEN);
					buttons[y+1][x].setText(Integer.toString(neighbour[y+1][x]));
					openArea(y+1, x);
				}
				if(!is_visible[y+1][x-1]){
					buttons[y+1][x-1].setEnabled(false);
					is_visible[y+1][x-1] = true;
					buttons[y+1][x-1].setBackground(Color.GREEN);
					buttons[y+1][x-1].setText(Integer.toString(neighbour[y+1][x-1]));
					openArea(y+1, x-1);
				}

			}

			//opening left line neighbours
			else if(x==0){

				if(!is_visible[y-1][x]){
					buttons[y-1][x].setEnabled(false);
					is_visible[y-1][x] = true;
					buttons[y-1][x].setBackground(Color.GREEN);
					buttons[y-1][x].setText(Integer.toString(neighbour[y-1][x]));
					openArea(y-1, x);
				}
				if(!is_visible[y+1][x]){
					buttons[y+1][x].setEnabled(false);
					is_visible[y+1][x] = true;
					buttons[y+1][x].setBackground(Color.GREEN);
					buttons[y+1][x].setText(Integer.toString(neighbour[y+1][x]));
					openArea(y+1, x);
				}
				if(!is_visible[y-1][x+1]){
					buttons[y-1][x+1].setEnabled(false);
					is_visible[y-1][x+1] = true;
					buttons[y-1][x+1].setBackground(Color.GREEN);
					buttons[y-1][x+1].setText(Integer.toString(neighbour[y-1][x+1]));
					openArea(y-1, x+1);
				}
				if(!is_visible[y][x+1]){
					buttons[y][x+1].setEnabled(false);
					is_visible[y][x+1] = true;
					buttons[y][x+1].setBackground(Color.GREEN);
					buttons[y][x+1].setText(Integer.toString(neighbour[y][x+1]));
					openArea(y, x+1);
				}
				if(!is_visible[y+1][x+1]){
					buttons[y+1][x+1].setEnabled(false);
					is_visible[y+1][x+1] = true;
					buttons[y+1][x+1].setBackground(Color.GREEN);
					buttons[y+1][x+1].setText(Integer.toString(neighbour[y+1][x+1]));
					openArea(y+1, x+1);
				}

			}

			//opening bottom line neighbors
			else if(y==size-1){

				if(!is_visible[y][x+1]){
					buttons[y][x+1].setEnabled(false);
					is_visible[y][x+1] = true;
					buttons[y][x+1].setBackground(Color.GREEN);
					buttons[y][x+1].setText(Integer.toString(neighbour[y][x+1]));
					openArea(y, x+1);
				}
				if(!is_visible[y][x-1]){
					buttons[y][x-1].setEnabled(false);
					is_visible[y][x-1] = true;
					buttons[y][x-1].setBackground(Color.GREEN);
					buttons[y][x-1].setText(Integer.toString(neighbour[y][x-1]));

				}
				if(!is_visible[y-1][x+1]){
					openArea(y, x-1);
					buttons[y-1][x+1].setEnabled(false);
					is_visible[y-1][x+1] = true;
					buttons[y-1][x+1].setBackground(Color.GREEN);
					buttons[y-1][x+1].setText(Integer.toString(neighbour[y-1][x+1]));

				}
				if(!is_visible[y-1][x]){
					openArea(y-1, x+1);
					buttons[y-1][x].setEnabled(false);
					is_visible[y-1][x] = true;
					buttons[y-1][x].setBackground(Color.GREEN);
					buttons[y-1][x].setText(Integer.toString(neighbour[y-1][x]));

				}
				if(!is_visible[y-1][x-1]){
					openArea(y-1, x);
					buttons[y-1][x-1].setEnabled(false);
					is_visible[y-1][x-1] = true;
					buttons[y-1][x-1].setBackground(Color.GREEN);
					buttons[y-1][x-1].setText(Integer.toString(neighbour[y-1][x-1]));
					openArea(y-1, x-1);
				}

			}

			//opening right line neighbors
			else if(x==size-1){

				if(!is_visible[y-1][x]){
					buttons[y-1][x].setEnabled(false);
					is_visible[y-1][x] = true;
					buttons[y-1][x].setBackground(Color.GREEN);
					buttons[y-1][x].setText(Integer.toString(neighbour[y-1][x]));
					openArea(y-1, x);
				}
				if(!is_visible[y+1][x]){
					buttons[y+1][x].setEnabled(false);
					is_visible[y+1][x] = true;
					buttons[y+1][x].setBackground(Color.GREEN);
					buttons[y+1][x].setText(Integer.toString(neighbour[y+1][x]));
					openArea(y+1, x);
				}
				if(!is_visible[y][x-1]){
					buttons[y][x-1].setEnabled(false);
					is_visible[y][x-1] = true;
					buttons[y][x-1].setBackground(Color.GREEN);
					buttons[y][x-1].setText(Integer.toString(neighbour[y][x-1]));
					openArea(y, x-1);
				}
				if(!is_visible[y-1][x-1]){
					buttons[y-1][x-1].setEnabled(false);
					is_visible[y-1][x-1] = true;
					buttons[y-1][x-1].setBackground(Color.GREEN);
					buttons[y-1][x-1].setText(Integer.toString(neighbour[y-1][x-1]));
					openArea(y-1, x-1);
				}
				if(!is_visible[y+1][x-1]){
					buttons[y+1][x-1].setEnabled(false);
					is_visible[y+1][x-1] = true;
					buttons[y+1][x-1].setBackground(Color.GREEN);
					buttons[y+1][x-1].setText(Integer.toString(neighbour[y+1][x-1]));
					openArea(y+1, x-1);
				}

			}

			//opening inside neighbours
			else{

				if(!is_visible[y-1][x-1]){
					buttons[y-1][x-1].setEnabled(false);
					is_visible[y-1][x-1] = true;
					buttons[y-1][x-1].setBackground(Color.GREEN);
					buttons[y-1][x-1].setText(Integer.toString(neighbour[y-1][x-1]));
					openArea(y-1, x-1);
				}
				if(!is_visible[y-1][x]){
					buttons[y-1][x].setEnabled(false);
					is_visible[y-1][x] = true;
					buttons[y-1][x].setBackground(Color.GREEN);
					buttons[y-1][x].setText(Integer.toString(neighbour[y-1][x]));
					openArea(y-1, x);
				}
				if(!is_visible[y-1][x+1]){
					buttons[y-1][x+1].setEnabled(false);
					is_visible[y-1][x+1] = true;
					buttons[y-1][x+1].setBackground(Color.GREEN);
					buttons[y-1][x+1].setText(Integer.toString(neighbour[y-1][x+1]));
					openArea(y-1, x+1);
				}
				if(!is_visible[y][x-1]){
					buttons[y][x-1].setEnabled(false);
					is_visible[y][x-1] = true;
					buttons[y][x-1].setBackground(Color.GREEN);
					buttons[y][x-1].setText(Integer.toString(neighbour[y][x-1]));
					openArea(y, x-1);
				}
				if(!is_visible[y][x+1]){
					buttons[y][x+1].setEnabled(false);
					is_visible[y][x+1] = true;
					buttons[y][x+1].setBackground(Color.GREEN);
					buttons[y][x+1].setText(Integer.toString(neighbour[y][x+1]));
					openArea(y, x+1);
				}
				if(!is_visible[y+1][x-1]){
					buttons[y+1][x-1].setEnabled(false);
					is_visible[y+1][x-1] = true;
					buttons[y+1][x-1].setBackground(Color.GREEN);
					buttons[y+1][x-1].setText(Integer.toString(neighbour[y+1][x-1]));
					openArea(y+1, x-1);
				}
				if(!is_visible[y+1][x]){
					buttons[y+1][x].setEnabled(false);
					is_visible[y+1][x] = true;
					buttons[y+1][x].setBackground(Color.GREEN);
					buttons[y+1][x].setText(Integer.toString(neighbour[y+1][x]));
					openArea(y+1, x);
				}
				if(!is_visible[y+1][x+1]){
					buttons[y+1][x+1].setEnabled(false);
					is_visible[y+1][x+1] = true;
					buttons[y+1][x+1].setBackground(Color.GREEN);
					buttons[y+1][x+1].setText(Integer.toString(neighbour[y+1][x+1]));
					openArea(y+1, x+1);
				}

			}
			winning();
		}
	}

	//Win situation - ALP
	public void winning(){

		int opened_buttons = 0;

		for (int a=0; a<size; a++){
			for(int b=0; b<size; b++){

				if(is_visible[a][b]){
					opened_buttons++;
				}

			}

		}
		if(opened_buttons >= (size*size)-mines) {end_game(true);}

	}


	//Click functions - AYBERK
	@Override
	public void actionPerformed(ActionEvent e) {

		for(int x=0;x<buttons.length;x++) {
			for(int y=0;y<buttons[0].length;y++) {

				//Clicking on a button
				if(e.getSource()==buttons[x][y]) {

					//Clicking on a mine
					if(mine_locations[x][y]){

						buttons[x][y].setBackground(Color.RED);
						buttons[x][y].setEnabled(false);
						end_game(false);
					}
					//Clicking on an empty field
					else{

						is_visible[x][y]= true;
						buttons[x][y].setBackground(Color.GREEN);
						buttons[x][y].setEnabled(false);
						buttons[x][y].setText(Integer.toString(neighbour[x][y]));

						//If neighbour count is 0, then it's surroundings must be opened as well.
						if(neighbour[x][y]==0){ openArea(x,y); }

						winning();
						
					}
				}
			}
		}
	}

}
	

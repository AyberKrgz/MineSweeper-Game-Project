import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Bombed extends JFrame {

    JFrame bombedframe;
    JLabel displayField;
    ImageIcon image;
    
    public Bombed(){
        
    	bombedframe = new JFrame("bombed");
    	bombedframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	bombedframe.setSize(200,200);
    	bombedframe.setVisible(true);
    	bombedframe.revalidate();
    	bombedframe.setLocationRelativeTo(null);

        try{
            image = new ImageIcon(getClass().getResource("bombed.gif"));
            displayField = new JLabel(image);
            bombedframe.add(displayField);

        }
        catch (Exception e){
            System.out.println("Image cannot be found!");
        }
        
    }

}

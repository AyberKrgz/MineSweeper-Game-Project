import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WellDone extends JFrame {

    JFrame wellDoneFrame;
    JLabel displayField;
    ImageIcon image;
    
    public WellDone(){
        
    	wellDoneFrame = new JFrame("Well Done!!!");
    	wellDoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	wellDoneFrame.setSize(374,404);
    	wellDoneFrame.setVisible(true);
    	wellDoneFrame.revalidate();
    	wellDoneFrame.setLocationRelativeTo(null);

        try{
            image = new ImageIcon(getClass().getResource("welldone.gif"));
            displayField = new JLabel(image);
            wellDoneFrame.add(displayField);

        }
        catch (Exception e){
            System.out.println("Image cannot be found!");
        }
        
    }

}

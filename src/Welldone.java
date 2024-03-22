import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Welldone extends JFrame {

    JFrame welldoneframe;
    JLabel displayField;
    ImageIcon image;
    
    public Welldone(){
        
    	welldoneframe = new JFrame("Welldone!!!");
    	welldoneframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	welldoneframe.setSize(374,404);
    	welldoneframe.setVisible(true);
    	welldoneframe.revalidate();
    	welldoneframe.setLocationRelativeTo(null);

        try{
            image = new ImageIcon(getClass().getResource("welldone.gif"));
            displayField = new JLabel(image);
            welldoneframe.add(displayField);

        }
        catch (Exception e){
            System.out.println("Image cannot be found!");
        }
        
    }

}

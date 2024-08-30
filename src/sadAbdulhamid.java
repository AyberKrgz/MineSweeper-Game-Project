import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class sadAbdulhamid extends JFrame {

    JFrame abdulhamidFrame;
    JLabel displayField;
    ImageIcon image;

    public sadAbdulhamid(){

        abdulhamidFrame = new JFrame("SAD ABDULHAMID");
        abdulhamidFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        abdulhamidFrame.setSize(498,498);
        abdulhamidFrame.setVisible(true);
        abdulhamidFrame.revalidate();
        abdulhamidFrame.setLocationRelativeTo(null);

        try{
            image = new ImageIcon(getClass().getResource("sadAbdulhamid.gif"));
            displayField = new JLabel(image);
            abdulhamidFrame.add(displayField);

        }
        catch (Exception e){
            System.out.println("Image cannot be found!");
        }

    }

}

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * 
 * @author chy
 * This class is the data structure of chess piece
 */
@SuppressWarnings("serial")
public class ChessPiece extends JLabel {
     String name;
     ImageIcon img;
     public ChessPiece(String name, String path){
    	 this.name = name;
    	 img = new ImageIcon(path);
    	 this.setIcon(img);
     }
     public String getName(){
    	 return name;
     }
}

import java.awt.BorderLayout;
import java.util.Stack;

import javax.swing.JPanel;
/**
 * 
 * @author chy
 * This class is used to store the chessPieces history in a panel
 */
@SuppressWarnings("serial")
public class myPanel extends JPanel {
	  Stack<ChessPiece> stack;
	  boolean occupied;
	  String name = null;
	  public myPanel(){
		  stack = new Stack<>();
		  occupied = false;
		  this.setLayout(new BorderLayout());
	  }
	  public void setName(String name){
		  this.name = name;
	  }
	  public String getName(){
		  return name;
	  }
}

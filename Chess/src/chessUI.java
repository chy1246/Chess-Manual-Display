import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.awt.event.ActionEvent;
/**
 * 
 * @author chy
 * Entrance for his program
 */
public class chessUI {

	private JFrame frame;
    private myPanel A1, A2, A3, A4, A5, A6, A7, A8, B1, B2, B3, B4,
    B5, B6, B7, B8, C1, C2,C3, C4, C5, C6, C7, C8, D1, D2, D3,
    D4, D5, D6, D7, D8, E1, E2, E3, E4, E5, E6, E7, E8, F1,
    F2, F3, F4, F5, F6, F7, F8, G1, G2, G3, G4, G5, G6, G7, G8,
    H1, H2, H3, H4, H5, H6, H7, H8;
    myPanel[] array = new myPanel[]{
       A8, B8, C8, D8, E8, F8, G8, H8, 
       A7, B7, C7, D7, E7, F7, G7, H7,
       A6, B6, C6, D6, E6, F6, G6, H6,
       A5, B5, C5, D5, E5, F5, G5, H5,
       A4, B4, C4, D4, E4, F4, G4, H4,
       A3, B3, C3, D3, E3, F3, G3, H3,
       A2, B2, C2, D2, E2, F2, G2, H2,
       A1, B1, C1, D1, E1, F1, G1, H1
    };
    List<step> steps;
    int pointer = 0; //track steps 
    // use to track the special cases O-O, O-O-O
    int w_k = 60;
    int w_q = 59;
    int b_k = 4;
    int b_q = 3;
    boolean turn = true; //white turn
    Stack<ChessPiece> stack = new Stack<>(); //track promotion
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chessUI window = new chessUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public chessUI() {
		steps = new IOTransfer().parse();
		//
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		
		JPanel boardPanel = new JPanel();
		panel.add(boardPanel);
		boardPanel.setBackground(Color.GREEN);
		boardPanel.setPreferredSize(new Dimension(600, 280));
		boardPanel.setLayout(new GridLayout(8, 8, 0, 0));
		
		// initialize the board
		for(int i = 0; i < 8; i++){
			  for(int j = 0; j < 8; j++){
				  array[i * 8 + j] = new myPanel();
				  if((i - j) % 2 == 0){
					  array[i * 8 + j].setBackground(Color.green);
				  }else{
					  array[i * 8 + j].setBackground(Color.white);
				  }
				  boardPanel.add(array[i * 8 + j]);
				  //System.out.println(array[i * 8 + j]);
			  }
			}
	    //put the default chess pieces
		array[0].add(new ChessPiece("black_rook", "black_rook.png"));
        array[0].stack.push(new ChessPiece("black_rook", "black_rook.png"));
        array[0].occupied = true;
        array[0].setName("black_rook");
		array[1].add(new ChessPiece("black_knight", "black_knight.png"));
		array[1].stack.push(new ChessPiece("black_knight", "black_knight.png"));
		array[1].occupied = true;
		array[1].setName("black_knight");
		array[2].add(new ChessPiece("black_bishop", "black_bishop.png"));
		array[2].stack.push(new ChessPiece("black_bishop", "black_bishop.png"));
		array[2].occupied = true;
		array[2].setName("black_bishop");
		array[3].add(new ChessPiece("black_queen", "black_queen.png"));
		array[3].stack.push(new ChessPiece("black_queen", "black_queen.png"));
		array[3].occupied = true;
		array[3].setName("black_queen");
		array[4].add(new ChessPiece("black_king", "black_king.png"));
		array[4].stack.push(new ChessPiece("black_king", "black_king.png"));
		array[4].occupied = true;
		array[4].setName("black_king");
		array[5].add(new ChessPiece("black_bishop", "black_bishop.png"));
		array[5].stack.push(new ChessPiece("black_bishop", "black_bishop.png"));
		array[5].occupied = true;
		array[5].setName("black_bishop");
		array[6].add(new ChessPiece("black_knight", "black_knight.png"));
		array[6].stack.push(new ChessPiece("black_knight", "black_knight.png"));
		array[6].occupied = true;
		array[6].setName("black_knight");
		array[7].add(new ChessPiece("black_rook", "black_rook.png"));
		array[7].stack.push(new ChessPiece("black_rook", "black_rook.png"));
		array[7].occupied = true;
		array[7].setName("black_rook");
		for(int i = 8; i < 16; i++){
			array[i].add(new ChessPiece("black_pawn", "black_pawn.png"));
			array[i].stack.push(new ChessPiece("black_pawn", "black_pawn.png"));
			array[i].occupied = true;
			array[i].setName("black_pawn");
		}
		for(int i = 48; i < 56; i++){
			array[i].add(new ChessPiece("white_pawn", "white_pawn.png"));
			array[i].stack.push(new ChessPiece("white_pawn", "white_pawn.png"));
			array[i].occupied = true;
			array[i].setName("white_pawn");
		}
		array[56].add(new ChessPiece("white_rook", "white_rook.png"));
		array[56].stack.push(new ChessPiece("white_rook", "white_rook.png"));
		array[56].occupied = true;
		array[56].setName("white_rook");
		array[57].add(new ChessPiece("white_knight", "white_knight.png"));
		array[57].stack.push(new ChessPiece("white_knight", "white_knight.png"));
		array[57].occupied = true;
		array[57].setName("white_knight");
		array[58].add(new ChessPiece("white_bishop", "white_bishop.png"));
		array[58].stack.push(new ChessPiece("white_bishop", "white_bishop.png"));
		array[58].occupied = true;
		array[58].setName("white_bishop");
		array[59].add(new ChessPiece("white_queen", "white_queen.png"));
		array[59].stack.push(new ChessPiece("white_queen", "white_queen.png"));
		array[59].occupied = true;
		array[59].setName("white_queen");
		array[60].add(new ChessPiece("white_king", "white_king.png"));
		array[60].stack.push(new ChessPiece("white_king", "white_king.png"));
		array[60].occupied = true;
		array[60].setName("white_king");
		array[61].add(new ChessPiece("white_bishop", "white_bishop.png"));
		array[61].stack.push(new ChessPiece("white_bishop", "white_bishop.png"));
		array[61].occupied = true;
		array[61].setName("white_bishop");
		array[62].add(new ChessPiece("white_knight", "white_knight.png"));
		array[62].stack.push(new ChessPiece("white_knight", "white_knight.png"));
		array[62].occupied = true;
		array[62].setName("white_knight");
	    array[63].add(new ChessPiece("white_rook", "white_rook.png"));
	    array[63].stack.push(new ChessPiece("white_rook", "white_rook.png"));
	    array[63].occupied = true;
	    array[63].setName("white_rook");
	    
	    //create choice panel
		JPanel choice = new JPanel();
		panel.add(choice);
		JButton preButton = new JButton("Previous");
		preButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pointer == 0){
					  JOptionPane.showMessageDialog( null,
					  "You Have to Make a Next Step","No Previous Step", JOptionPane.WARNING_MESSAGE); 
				  }else{ // add if() to check the QNRB to use stack.pop() and preQRNB function, but due to time I have not test it.
                ChessPiece piece = remove(array[steps.get(--pointer).to]);
                System.out.println(piece == null);
                if(!array[steps.get(pointer).to].stack.isEmpty()){
                	array[steps.get(pointer).to].add(array[steps.get(pointer).to].stack.peek());
                	array[steps.get(pointer).to].occupied = true;
                	array[steps.get(pointer).to].setName(array[steps.get(pointer).to].stack.peek().name);
                }
                //System.out.println(steps.get(pointer).to);
                if(array[steps.get(pointer).from].occupied){
                  array[steps.get(pointer).from].removeAll();
  				  array[steps.get(pointer).from].repaint();
  				  array[steps.get(pointer).from].updateUI();
                }
                //System.out.println(steps.get(pointer).from);
                update(array[steps.get(pointer).from], piece.getName());
                }
			}
		});
		preButton.setPreferredSize(new Dimension(80, 20));
		choice.add(preButton);
		
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  if(pointer == steps.size()){
				  JOptionPane.showMessageDialog( null,
				  "No More Data", "No More Next Step", JOptionPane.WARNING_MESSAGE); 
			  }else{
				  
				  if(steps.get(pointer).name.equals("Q")){
					  QNBR("Q");
				  }else if(steps.get(pointer).name.equals("N")){
					  QNBR("N");
				  }else if(steps.get(pointer).name.equals("B")){
					  QNBR("B");
				  }else if(steps.get(pointer).name.equals("R")){
					  QNBR("R");
				  }else
				  if(steps.get(pointer).name.equals("O-O")){
				  OO();
                  }else if(steps.get(pointer).name.equals("O-O-O")){
				  OOO();
                  }
			    //System.out.println(steps.get(pointer).from);
			   ChessPiece piece =  remove(array[steps.get(pointer).from]);
			  if(array[steps.get(pointer).to].occupied){
				  array[steps.get(pointer).to].removeAll();
				  array[steps.get(pointer).to].repaint();
				  array[steps.get(pointer).to].updateUI();
				  //System.out.println(piece.name + ".png");
		    	 }
              update(array[steps.get(pointer).to], piece.getName());
              changeKQPostion(steps.get(pointer).to, piece.getName());
              //System.out.println(steps.get(pointer).to);
              turn = !turn;
              pointer++;
			}
			}
			});
		 nextButton.setPreferredSize(new Dimension(80, 20));
		 choice.add(nextButton);
	}
     public ChessPiece remove(myPanel panel){
    	 ChessPiece piece = panel.stack.pop();
    	 panel.removeAll();
    	 panel.repaint();
    	 panel.updateUI();
    	 panel.occupied = false;
    	 return piece;
     }
     public void update(myPanel panel, String piece){
    	 ChessPiece p = new ChessPiece(piece, piece + ".png");
		 panel.add(p);
		 panel.updateUI();
		 panel.stack.push(p);
		 panel.setName(piece);
		 System.out.println(panel.stack.peek().name);
		 panel.occupied = true;
		 
     }
     public void OO(){
    	 int base = turn ? w_k  : b_k;
    	 int row = turn ? w_k /8 : b_k/8;
    	 String rook = turn ? "white_rook" : "black_rook";
    	 String queen = turn ? "white_queen" : "black_queen";//white: true, black: false
    	 //System.out.println(row);
    	 int i = 1;
    	 while((base + i <= row * 8 + 7)  && !array[base + i].getName().equals(rook)){
    		 i++;
    	 }
    	 steps.get(pointer).from = base;
    	 steps.get(pointer).to = (base + i + base) / 2 + 1;
    	 steps.get(pointer).name = "Normal";
    	 steps.add(pointer,new step(base + i, (base + i + base) / 2, "Normal"));
    	 changeKQPostion((base + i + base) / 2 + 1, queen);
     }
     public void OOO(){
    	 int base = turn ? w_q : b_q;
    	 int row = turn ? w_q / 8 : b_q / 8;
    	 String rook = turn ? "white_rook" : "black_rook";
    	 String king = turn ? "white_queen" : "black_queen";
    	 int i = 1;
    	 while((base - i >= (row - 1) * 8 + 8) && !array[base - i].getName().equals(rook)){
    		 i++;
    	 }
    	 steps.get(pointer).from = base;
    	 steps.get(pointer).to = (2 * base + i) / 2;
    	 steps.get(pointer).name = "Normal";
    	 steps.add(pointer, new step(base + i, (2 * base + i) / 2 + 1, "Normal"));
    	 changeKQPostion((base + i + base) / 2 + 1, king);
     }
     //Next QNRB operation 
     public void QNBR(String type){
    	 ChessPiece piece = remove(array[steps.get(pointer).from]);
    	 stack.push(new ChessPiece(piece.name, piece.name + ".png"));
    	 if(type.equals("Q")){
    	  update(array[steps.get(pointer).to], turn ? "white_queen" : "black_queen");
    	 }else if(type.equals("N")){
    	  update(array[steps.get(pointer).to], turn ? "white_knight" : "black_knight");
    	 }else if(type.equals("B")){
    	  update(array[steps.get(pointer).to], turn ? "white_bishop" : "black_bishop"); 
    	 }else{
    	  update(array[steps.get(pointer).to], turn ? "white_rook" : "black_rook"); 
    	 }
    	 pointer++;
     }
     //Pre QNRB operation
     public void PreQNBR(){
		  ChessPiece piece = stack.pop();
		  remove(array[steps.get(pointer).to]);
		  update(array[steps.get(pointer).from], piece.name);
     }
     //update position of four trackers
     public void changeKQPostion(int position, String piece){
    	 if(piece.equals("white_king")){
			 w_k = position;
		 }else if(piece.equals("white_queen")){
			 w_q = position;
		 }else if(piece.equals("black_king")){
			 b_k = position;
		 }else if(piece.equals("black_queen")){
			 b_q = position;
		 }
     }
}



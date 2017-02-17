import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
/**
 * 
 * @author chy
 * This class is to read data from file and store it in the list of steps
 */
public class IOTransfer {
     public List<step> parse(){
    	 File file = new File("Byrne_v_Fischer.txt");
    	 List<step> res = new ArrayList<>();
    	 try{
    		 FileReader isr = new FileReader(file);
    		 BufferedReader br = new BufferedReader(isr);
    		 String str = "";
    	     while(str != null){
    	    	 str = br.readLine();
    	    	 //System.out.println(str);
    	    	 if(str != null){
    	    	 String[] temp = str.split(" ");
    	    	 for(String step1: temp){
    	    		 if(step1.length() != 5){
    	    			 if(step1.equals("O-O")){
    	    				 res.add(new step(-1, -1, "O-O"));
    	    				 System.out.println(res.get(res.size() - 1));
    	    			 }else if(step1.equals("O-O-O")){
    	    				 res.add(new step(-1, -1, "O-O-O"));
    	    			 }else{
    	    				 step tempStep = new step(step1.charAt(0) - 65 + (8 - (step1.charAt(1) - '0'))* 8,
    	    	    					step1.charAt(3) - 65 + (8 - (step1.charAt(4) - '0'))* 8, "Q");
    	    				 if(step1.charAt(5) == 'N'){
    	    					 tempStep.name = "N";
    	    				 }else if(step1.charAt(5) == 'B'){
    	    					 tempStep.name = "B";
    	    				 }else if(step1.charAt(5) == 'R'){
    	    					 tempStep.name = "R";
    	    				 }
    	    				 res.add(tempStep);
    	    			 }
    	    		 }else{
    	    			 res.add(new step(step1.charAt(0) - 65 + (8 - (step1.charAt(1) - '0'))* 8,
    	    					step1.charAt(3) - 65 + (8 - (step1.charAt(4) - '0'))* 8, "Normal"));//(step.charAt(0) - 65) + (8 - (step.charAt(1) - '0'))* 8);
    	    			 System.out.println(res.get(res.size() - 1));
    	    		 	}
    	    	 	}
    	    	 }
    	     }
    		 br.close();
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
    	 return res; 
     }
}
class step{
	 int from;
	 int to;
	 String name;
	 public step(int a, int b, String c){
		 from = a;
		 to = b;
		 name = c;
	 }
}


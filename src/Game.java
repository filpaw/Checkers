import java.io.IOException;
import java.sql.*;
public class Game extends App {

	 
static	String result = null;
static int numberRed=12;
static int numberWhite=12;
private static CheckerType turn=CheckerType.WHITE;
private static App app=null;
   

	public static void main(String[] args) throws InterruptedException, IOException {
		app=new App();
		
	    Thread appThread=new Thread(app);
	    appThread.start();
	    System.out.println("hej");
	    
	    System.out.println(result);
	}
	
	public static MoveType MoveResult(int oldX,int oldY, int newX,int newY,boolean isKing,CheckerType checkerType)
	{
		
        Field field=GameView.getField(newX, newY);
        if(field.hasChecker()) //jesli  usta� na inny pionek
         	return MoveType.INCORRECT;  
        
        if(checkerType!=turn || isEnd) //je�li kolejno�� wg koloru nie jest zachowana
        	return MoveType.INCORRECT;
        
        
    	if(newX-oldX==-2 && newY-oldY==2)//bicie prawo g�ra
	      {   
	    	 Field field1=GameView.getField(newX+1, newY-1); 
	    	 if(field1.hasChecker()){  
	    	  return MoveType.KILL1;
	    	 }
	      }
	      if(newX-oldX==-2 && newY-oldY==-2) // bicie lewo g�ra
	      {
	    	 Field field2=GameView.getField(newX+1, newY+1);
	    	 if(field2.hasChecker()){        	   	 
	        	    return MoveType.KILL2;
	        	  }
	      }
	      if(newX-oldX==2 && newY-oldY==-2) // bicie lewo d�
	      {
	          Field field3=GameView.getField(newX-1, newY+1);
	          if(field3.hasChecker()){
	         	   	 
	         	    return MoveType.KILL3;
	         	  }
	      }
	      if(newX-oldX==2 && newY-oldY==2) // bicie prawo d�
	      {                 
	          Field field4=GameView.getField(newX-1, newY-1);
	          if(field4.hasChecker()){
	         	    	 
	         	    return MoveType.KILL4;
	         	  }
	      }
             
      
		if((Math.abs(oldX-newX)+Math.abs(newY-oldY))%2==0) //jesli ruch po przekatnych wyzej wymienionego kwadratu 
		{	
			if(newX-oldX>0)
				return MoveType.INCORRECT;
		   
			if(Math.abs(newY-oldY)<2 && Math.abs(newY-oldY)<2)	
			return MoveType.NORMAL;
		}
 	  
		
			return MoveType.INCORRECT;
	}
	
	
	public static MoveType captureKing(int oldX,int oldY, int newX,int newY,boolean isKing,CheckerType checkerType){
		
		if(newX-oldX<0 && newY-oldY>0)//bicie prawo g�ra
	      {   
	    	 Field field1=GameView.getField(newX+1, newY-1); 
	    	 if(field1.hasChecker()){	    	   
	    	  return MoveType.KILL1;
	    	 }
	      }
	      if(newX-oldX<0 && newY-oldY<0) // bicie lewo g�ra
	      {
	    	 Field field2=GameView.getField(newX+1, newY+1);
	    	 if(field2.hasChecker()){        	   	 
	        	    return MoveType.KILL2;
	        	  }
	      }
	      if(newX-oldX>0 && newY-oldY<0) // bicie lewo d�
	      {
	          Field field3=GameView.getField(newX-1, newY+1);
	          if(field3.hasChecker()){
	         	   	 
	         	    return MoveType.KILL3;
	         	  }
	      }
	      if(newX-oldX>0 && newY-oldY>0) // bicie prawo d�
	      {                 
	          Field field4=GameView.getField(newX-1, newY-1);
	          if(field4.hasChecker()){
	         	    	 
	         	    return MoveType.KILL4;
	         	  }
	      }
	      return MoveType.NORMAL;
	}
	
	public static MoveType moveKing(int oldX,int oldY, int newX,int newY,boolean isKing,CheckerType checkerType){
		
		 Field field=GameView.getField(newX, newY);
	        if(field.hasChecker()) //jesli  usta� na inny pionek
	         	return MoveType.INCORRECT;  
	        
	        if(checkerType!=turn || isEnd) //je�li kolejno�� wg koloru nie jest zachowana
	        	return MoveType.INCORRECT;
		
		if((Math.abs(oldX-newX)+Math.abs(newY-oldY))%2==0) //jesli ruch po przekatnych wyzej wymienionego kwadratu 
		{		
			return MoveType.NORMAL;
		}
		else
			return MoveType.INCORRECT;
	}
	
	
    public static void changeTurn()
    {
    	if(turn==CheckerType.RED)
    		turn=CheckerType.WHITE;
    	else
    		turn=CheckerType.RED;	
    }
    
    public static void decCheckerNumber(String moveType)
    {
    	if(turn==CheckerType.WHITE && !moveType.equals("NORMAL") && !moveType.equals("INCORRECT"))
    	{
    		numberRed=numberRed-1;
    		System.out.println("Red:"+numberRed);
    	}
    	else if(turn==CheckerType.RED && !moveType.equals("NORMAL") && !moveType.equals("INCORRECT"))
    	{
    		numberWhite--;
    		System.out.println("White:"+numberWhite);
    	}
    	
    	if(numberRed==0 || numberWhite==0)
    		app.gameOver();
    }
   
   
}

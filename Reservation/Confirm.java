
package Reservation;

//import EasyCoach.Reservation.Seat;
import Menu.Home;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;



public class Confirm extends Form {
    Home home;
    CheckTrip reserve;
    public Command cmdback;
    public Command cmdproceed;
    public StringItem[] seatNum;
    public String[] bkseat;
    Seat seat;
    public Confirm(Home home){
        super("Confirm Reservation(s)");
        this.home=home;
        cmdback=new Command("Back",Command.BACK,2);
        cmdproceed=new Command("Proceed",Command.OK,1);
        addCommand(cmdproceed);
        addCommand(cmdback);
        setCommandListener(home);
        verify();
    }
    public void verify(){
  
     // System.out.println("CONFIRM-"+home.book[0].getText()+"-LENGTH-"+home.book.length); 
        System.out.println("LENGTH--"+home.book.length);
        int u=0;
        try{
        while(!home.book[u].getText().equals(null)){
           System.out.println("CONFIRM-"+home.book[u].getText()); 
           append(home.book[u]);
           u++;
        }
    
        }
        catch(NullPointerException j){
            
        }
        
    }
   
}

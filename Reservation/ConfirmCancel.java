
package EasyCoach.Reservation;

import Menu.Home;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;


public class ConfirmCancel extends Form implements Runnable{
   Home home; 
   public Command cmdback;
   public Command cmdok;
   
    public ConfirmCancel(Home home){
        super("Confirm Cancellation");
        this.home=home;
        cmdback=new Command("Back",Command.BACK,2);
        cmdok=new Command("OK",Command.OK,1);
        
        
        setCommandListener(home);
        setItemStateListener(home);
        
        
    }

    public void start(){
        Thread t=new Thread(this);
        t.start();
    }
    public void run() {
    }
}

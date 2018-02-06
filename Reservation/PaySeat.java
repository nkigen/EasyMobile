
package EasyCoach.Reservation;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;

public class PaySeat extends Form implements Runnable{
    
    public Command cmdok;
    
    public PaySeat(){
        super("Payment");
        
        
    }

    
    public void pay(){
        Thread t=new Thread(this);
        t.start();
    }
    public void run() {
    }
}
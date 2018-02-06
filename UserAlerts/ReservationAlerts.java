
package EasyCoach.UserAlerts;

import Menu.Home;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;

public class ReservationAlerts extends Alert{
  
    
  public ReservationAlerts (){
       super("Reservation Error", "Either Username or password is invalid", null, AlertType.ERROR);
  }
    
    public ReservationAlerts(Home home,String s){
        super("Reservation Error", "Either Username or password is invalid", null, AlertType.ERROR);
        setTimeout(Alert.FOREVER);
    }
    
    
    
    
}

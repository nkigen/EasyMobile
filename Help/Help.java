
package EasyCoach.Help;
import Menu.Home;
import javax.microedition.lcdui.*;

public class Help extends List{
    public Command cmdback;
    public Command cmdok;
    public List helpList;
//    public Command cmdReservation,cmdparcel,cmdpayment;
    Home home;
  
    public Help(Home home){
        super("Choose Topic",List.IMPLICIT);
      this.home=home;
      cmdback=new Command("Back",Command.BACK,2);
    cmdok=new Command("OK",Command.OK,2);
      append("Reservation",null);
      append("Parcels",null);
      append("Personal Details",null);
   //   append("Extras",null);
      append("Login",null);
      addCommand(cmdback);
      addCommand(cmdok);
      setCommandListener(home);
    }
    
    
}

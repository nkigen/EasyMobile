
package EasyCoach.Help;

import Menu.Home;

import javax.microedition.lcdui.*;


public class Reservation extends Form {
    Home home;
    public StringItem check;
    public StringItem choose;
   public StringItem seat;
   public StringItem pay;
   public Command cmdback;
    public Reservation(Home home){
        super("Reservation Help");
        this.home=home;
        check=new StringItem("Check available Trips","Choose 'Check Trip' in the main menu then select the desired travel" +
                " date then the town you want to commute to and from.");
        choose=new StringItem("Choose Trip","Scroll through the list of available trips then click the 'OK' button to choose" +
                " a trip.");
        seat=new StringItem("Select Seat","From the list of available seats, select the seats you want to reserve.You can reserve" +
                " more than one seat.");
        pay=new StringItem("Pay","After you click OK in the confirm screen, the corresponding amount will be automatically" +
                " deducted from your account.");
        cmdback=new Command("Back",Command.BACK,1);
        append(check);
        append(choose);
        append(seat);
        append(pay);
        addCommand(cmdback);
        setCommandListener(home);
        
        
    }
}


package Menu;
import EasyCoach.Extras.Extras;
import EasyCoach.AccountDetails.Personal;
import Reservation.CheckTrip;
import EasyCoach.Parcel.ParcelStatus;
import EasyCoach.AccountDetails.AccountSummary;
import EasyCoach.Help.Help;
//import EasyCoach.Menu.Home;
import javax.microedition.lcdui.*;

public class Menu extends Form{
    private CheckTrip checkTrip;
    Home home;
    Help help;
    AccountSummary as;
    Extras xtra;
    Personal personal; //
    ParcelStatus ps;
    public Command cmdselect;
    public ChoiceGroup chtask;
    public Command cmdexit;
   int menuChoice;
    
 //   public Home home;
    
    public Menu(Home home){
        super("Main Menu:Easy Mobile");
        this.home=home;
        cmdselect=new Command("Select",Command.OK,1);
        cmdexit=new Command("Exit",Command.EXIT,2);
        chtask=new ChoiceGroup("",Choice.EXCLUSIVE);
        chtask.append("Check Trip", null);
        chtask.append("Currently Reserved Trips", null);
        chtask.append("Cancel Reservation",null);
        chtask.append("View Parcel Status", null);
        chtask.append("Personal Details", null);
        chtask.append("Account Summary", null);
     //   chtask.append("Payments", null);
      //  chtask.append("Extras", null);
        chtask.append("I'm Stuck!", null);
        append(chtask);
        addCommand(cmdselect);
        addCommand(cmdexit); 
        setCommandListener(home);
        setItemStateListener(home);
        
    }
}

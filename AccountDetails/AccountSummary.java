
package EasyCoach.AccountDetails;
import Menu.Home;
import javax.microedition.lcdui.*;

public class AccountSummary extends Form{
   public ChoiceGroup summary;
    public Command cmdback;
    public Command cmdok;
    public Home home;
    
    public AccountSummary(Home home){
     super("My Travel Summary");
     this.home=home;
     summary=new ChoiceGroup("",Choice.EXCLUSIVE);
     summary.append("View Balance", null);
     summary.append("Monthly Summary", null);
     
     cmdback=new Command("Back",Command.BACK,2);
     cmdok=new Command("OK",Command.OK,1);
     
     append(summary);
     addCommand(cmdok);
     addCommand(cmdback);
     setCommandListener(home);
     setItemStateListener(home);
     
    }

}

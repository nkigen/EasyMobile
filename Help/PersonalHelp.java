

package EasyCoach.Help;

import Menu.Home;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

public class PersonalHelp extends Form {
    Home home;
    StringItem view;
    StringItem chgpword;
    StringItem balance;
   public Command cmdback;
    public PersonalHelp(Home home){
        super("Account help");
        this.home=home;
        view=new StringItem("View Details:","Select 'Account Details' in the main menu");
        chgpword=new StringItem("Change Password:","Click on the change password command then enter" +
                " your new password");
        balance=new StringItem("Account Balance:","Select 'Account Balance' in the Account Summary.");
        cmdback=new Command("Back",Command.BACK,1);
        
        append(view);
        append(chgpword);
        append(balance);
        addCommand(cmdback);
        setCommandListener(home);
        
    }
}

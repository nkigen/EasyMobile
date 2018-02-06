
package EasyCoach.Help;

import Menu.Home;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

public class Parcel extends Form {
  Home home;  
  StringItem chek;
  public Command cmdback;
    public Parcel(Home home){
        super("Parcel Status");
        this.home=home;
        chek=new StringItem("Check Status","Choose parcel Status in the mani menu then enter the parcel ID to view its status");
        cmdback=new Command("Back",Command.BACK,1);
        
        append(chek);
        addCommand(cmdback);
        setCommandListener(home);
        
        
    }
}

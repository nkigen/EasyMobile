
package EasyCoach.Extras;
import Menu.Home;

import javax.microedition.lcdui.*;

public class Extras extends Form{
    public ChoiceGroup more;
    public Command cmdback;
    public Command cmdok;
    Home home;
public Extras(Home home){
        super("Easy Mobile Extras");
        this.home=home;
    more=new ChoiceGroup("Extras",Choice.EXCLUSIVE);
   cmdback=new Command("Back",Command.BACK,2);
    cmdok=new Command("OK",Command.OK,1);
   more.append("View MIDP Profile", null);
  append(more);
   addCommand(cmdback);
   addCommand(cmdok);
  setCommandListener(home);
    }

    
}

 
package EasyCoach.Reservation;

import Menu.Home;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

public class ChooseTrip extends Form {
  Home home;
  
  public Command cmdback;
  public Command cmdok;
  public ChoiceGroup trips;
  public StringItem error;
  public String[] tid;  //trip ids
    public String[] space; //space Available
    public String[] triptime;  // trip time
    public String[] fare; //fare 
    
    public ChooseTrip(Home home){
       super("Trips available");
        this.home=home;
        cmdok=new Command("OK",Command.OK,1);
      cmdback=new Command("Back",Command.BACK,2);
        //addCommand(cmdok);
      addCommand(cmdback);
      setCommandListener(home);
    }
  public ChooseTrip(Home home,String[] tid,String[] s,String[] t, String [] f){
      super("Trips available");
     this.home=home;
     this.tid=tid;
      fare=f;
      space=s;
      triptime=t;
      cmdok=new Command("OK",Command.OK,1);
      cmdback=new Command("Back",Command.BACK,2);
      trips=new ChoiceGroup("",Choice.EXCLUSIVE);
     
      for(int i=0;i<tid.length;i++){
              trips.append("Trip No:"+tid[i]+" Space: "+space[i]+" Fare=KSH "+fare[i]+" Time:"+triptime[i], null);
              System.out.println("Trip No:"+tid[i]+" Space: "+space[i]+" Fare=KSH "+fare[i]+" Time:"+triptime[i]);
          }
          append(trips);
     
      addCommand(cmdok);
      addCommand(cmdback);
      setCommandListener(home);
      
  }  
 
}

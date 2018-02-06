
package EasyCoach.Reservation;

import Menu.Home;

import javax.microedition.lcdui.*;

/**
 * @author Nel
 */
public class City extends List{
    Home home;
    public Command cmdok;
    public Command cmdgo;
    public String [] cities=new String[] {"Nairobi","Nakuru","Eldoret","Kitale","Kapsabet",
    "Bungoma","Busia","Malaba","Kisumu","Kericho","Webuye","Maseno","Kakamega","Awendo"};
    public City(Home home){
        super("Choose List",List.IMPLICIT);
        this.home=home;
         for(int i=0;i<cities.length;i++){
            append(cities[i],null);
        }
        cmdok=new Command("OK",Command.OK,1);
        cmdgo=new Command("OK",Command.OK,1); 
    }
    public void from(){
        addCommand(cmdok);
      setCommandListener(home);
    }
    public void to(){
        removeCommand(cmdok);
        addCommand(cmdgo);
      setCommandListener(home);
    }

}
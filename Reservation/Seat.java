

package Reservation;


import Menu.Home;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;

public class Seat extends Form{
    
    public String[] seat=new String[]{"A1","B1","B2","B3","B4","C1","C2","C3","C4","D1","D2","D3","D4","E1","E2","E3","E4","F1","F2","F3","F4","G1","G2","G3","G4","H1","H2","H3","H4"};
    public ChoiceGroup seatNum;
     Home home;
     
    public String[] unbooked;
    int k=0;
    CheckTrip reserve=new CheckTrip(home);
    public Command cmdok;
    public Command cmdback;
    private String[] bk;
   public Seat(){
       super("");
   }
    public Seat(Home home){
        super("Select Seat(s)");
       this.home=home;
       cmdok=new Command("OK",Command.OK,1);
        cmdback=new Command("Back",Command.BACK,1);
        seatNum=new ChoiceGroup(null,Choice.MULTIPLE);
        addCommand(cmdok);
        addCommand(cmdback);
        
        setCommandListener(home);
     //  showList();
        
    }
    public Seat(Home home,String[] n){
        
      super("Select Seat(s)");
       unbooked=n;
       cmdok=new Command("OK",Command.OK,1);
        cmdback=new Command("Back",Command.BACK,1);
        seatNum=new ChoiceGroup(null,Choice.MULTIPLE);
        addCommand(cmdok);
        addCommand(cmdback);
        this.home=home;
        setCommandListener(home);
      System.out.println("lenth:::"+unbooked.length);
   showList();
    }
    public void showList(){
        for(int u=0;u<(unbooked.length-3);u++){
          seatNum.append(unbooked[u],null);
       //   System.out.println(unbooked[u]);
      }   
        append(seatNum);
        
    }

    public void run() {
    }
    
}

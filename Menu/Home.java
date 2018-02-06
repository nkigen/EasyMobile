package Menu;
import EasyCoach.Extras.Extras;
import EasyCoach.AccountDetails.Personal;
import Reservation.CheckTrip;
import EasyCoach.Parcel.ParcelStatus;
import EasyCoach.AccountDetails.AccountSummary;
import EasyCoach.AccountDetails.getBalance;
import EasyCoach.AccountDetails.Update;
import EasyCoach.Help.Help;
import EasyCoach.Help.Login;
import EasyCoach.Help.Parcel;
import EasyCoach.Help.PersonalHelp;
import EasyCoach.Help.Reservation;
import EasyCoach.Reservation.CancelReservation;
import EasyCoach.Reservation.ChooseTrip;
import EasyCoach.Reservation.City;
import Reservation.Confirm;
import EasyCoach.Reservation.GetReserved;
import EasyCoach.Reservation.Payment;
import Reservation.Seat;
import EasyCoach.Reservation.UnbookedSeats;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;


public class Home extends MIDlet implements CommandListener, ItemStateListener,Runnable{
   private Display display;
   public String[] memberDetails;
   public String clientId;
   public Command forgot;
   CancelReservation cr=new CancelReservation(this);
   getBalance gb=new getBalance(this);
   Seat seat;
   GetReserved gr;
   Reservation r=new Reservation(this);
   Parcel p=new Parcel(this);
   Login log=new Login(this);
   PersonalHelp ph=new PersonalHelp(this);
   Forgot getpword=new Forgot(this);
   public String errmsg;
   Payment payment=new Payment(this);
   public String[] bkk;    //bked seats
    private Ticker tickmsg;   //
    private Command cmdok;    //ok command
    private Command cmdexit;   //exit Command
    private Form frmhome;      //login form
    public TextField txtusername;     //username textfield
    public TextField txtpassword;    //password textfield
    public Command cmdselect;   //select command
    public Command cmdback;
    public StringItem[] book;
   public ChooseTrip choosetrip=new ChooseTrip(this);
   public Form ticket;
    CheckTrip ctrip=new CheckTrip(this);
    UnbookedSeats us=new UnbookedSeats(this);
    Confirm confirm;
    Update update=new Update(this);
    Menu menu=new Menu(this);
    Help help=new Help(this);
    AccountSummary as=new AccountSummary(this);
    Extras xtra=new Extras(this);
    Personal personal=new Personal(this); //
    ParcelStatus ps=new ParcelStatus(this);
    private String uname;
    private String pword;
    String pres;  //p response
    private HttpConnection hc=null;
    private InputStream in=null;
    private OutputStream out=null;
    City city=new City(this);
//    Seat seat;
    Update clientUpdate=new Update(this);
    private Image logo;
    public String tripid;
    /*Process Ticket Details*/
    String[] accepted;
     String[] rejected;
     String[] ticketno;
   String[] tempo;
   private Command tickback;
   
   /*Ticket server response*/
   String[] ticketvalues;
  /*Cancel2 Trip ID*/
   public String[] tidcancel;
    
    public Home(){
      display=Display.getDisplay(this);
      loginScreen();
      
//      choosetrip=new ChooseTrip(this);
      //splashScreen();
    }
    protected void startApp() throws MIDletStateChangeException {
        display.setCurrent(frmhome);
    }

    protected void pauseApp() {
        notifyPaused();
    }

    protected void destroyApp(boolean b) throws MIDletStateChangeException {
         notifyDestroyed();
    }
public void loginScreen(){
    tickmsg=new Ticker("Welcome To Easy Coach Mobile");
    cmdok=new Command("OK",Command.OK,1);
    cmdexit=new Command("Exit",Command.EXIT,2);
    forgot=new Command("Forgot?",Command.OK,3);
    frmhome=new Form("Easy Coach");
    frmhome.setTicker(tickmsg);
    txtusername=new TextField("Username","",20,TextField.ANY);
    txtpassword=new TextField("Password","",20,TextField.PASSWORD);
    frmhome.append(txtusername);
    frmhome.append(txtpassword);
    frmhome.addCommand(cmdok);
    frmhome.addCommand(forgot);
    frmhome.addCommand(cmdexit);
    frmhome.setCommandListener(this);
    display.setCurrent(frmhome);
    
}
public void validateUser(){
    Thread t=new Thread(this);
       t.start();
}
 public final static String[] split(String str, char separatorChar)
{
    if (str == null){
        return null;
    }
    int len = str.length();
if (len == 0){
        return null;
    }
    Vector list = new Vector();
    int i = 0;
    int start = 0;
    boolean match = false;
    while (i < len){
        if (str.charAt(i) == separatorChar)
        {
            if (match){
                list.addElement(str.substring(start, i).trim());
                match = false;
            }
            start = ++i;
            continue;
        }
        match = true;
        i++;
    }

    if (match){
        list.addElement(str.substring(start, i).trim());
    }
   String[] arr = new String[list.size()];
    list.copyInto(arr);
    return arr;
}
public void run() {
    String name=txtusername.getString();
    String pass=txtpassword.getString();
    StringBuffer b=new StringBuffer("");
    
        String parameter;
        String result;
        byte[] data;
        String url="http://127.0.0.1:80/Easy_Coach/j2me/jlogin.php";
        //String url="http://192.168.170.15:8080/50820/Easy_Coach/j2me/jlogin.php";
        parameter = url+"?name=" + name+"&password=" + pass;
        
        try {
            hc = (HttpConnection)Connector.open(parameter);
            hc.setRequestMethod(HttpConnection.GET);
            in=hc.openInputStream();
            int rc=hc.getResponseCode();
            if(rc==HttpConnection.HTTP_OK){
                data=new byte[(int)(hc.getLength())];
                int actual=in.read(data);
                String s=new String(data).toString().trim();
         if(!s.equals("INVALID LOGIN")){
                memberDetails=split(s,',');
                System.out.println(s);
                System.out.println(memberDetails[0]);
                System.out.println(memberDetails[1]);
                
             if(memberDetails[0].equals("OK")){
                 clientId=memberDetails[1];
                 setDisplay(menu);
             }   
           
         }
         else if(s.equals("INVALID LOGIN")){
             tryAgain();
         }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

      try {
            if(in != null) {
                in.close();
            }
            if(hc != null) {
                hc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }  
public void tryAgain() {
        Alert error = new Alert("Login Error!", "Either Username or password is invalid", null, AlertType.ERROR);
        error.setTimeout(Alert.FOREVER);
        txtpassword.setString("");
        display.setCurrent(error, frmhome);
    } 
public void setDisplay(Displayable d){
	display.setCurrent(d);
	}

public void getTick(String[] f){
    int a=0;
    int b=0;
    tickback=new Command("Back",Command.BACK,1);
    ticket=new Form("Ticket Details");
    StringItem rej[]=new StringItem[f.length];
    StringItem acc[]=new StringItem[f.length];
    
    
    System.out.println("TICKET TEST-"+f[0]);
    ticketvalues=new String[payment.seat.length];
    ticketvalues=f;
     tempo=new String[ticketvalues.length];
         System.out.println("LENGTH-"+payment.seat.length);
         rejected=new String[ticketvalues.length];
         accepted=new String[ticketvalues.length];
         ticketno=new String[ticketvalues.length];
         for(int i=0;i<ticketvalues.length;i++){
             
             tempo=split(ticketvalues[i],',');
         System.out.println("EASY--"+ticketvalues[i]);
         if(tempo[0].equals("NO")){
        rejected[a]=tempo[1];
        System.out.println("REJECTED-"+rejected[a]);
        rej[a]=new StringItem("Error:","Seat No:"+rejected[a]+" was not reserved.Please recharge your account.");
        ticket.append(rej[a]);
        a++;
         }
         else{
         accepted[b]=tempo[0];
         ticketno[b]=tempo[2];
         System.out.println("ACCEPTED-"+accepted[b]);
         acc[b]=new StringItem("Success:","Seat No:"+accepted[b]+" was reserved successfully.The Ticket number " +
                 "is:"+ticketno[b]);
         ticket.append(acc[b]);
           b++ ;
         }
         
         }
         ticket.addCommand(tickback);
         ticket.setCommandListener(this);
         setDisplay(ticket);
    /*Display Ticket Details*/
         
}

 public void commandAction(Command c, Displayable d) {
     //   test();
        if(c==cmdok){
            if(txtusername.getString().equals("")||txtpassword.getString().equals("")){
        
        Alert lerror = new Alert("Empty field!", "Input your username and password!", null, AlertType.ERROR);
        lerror.setTimeout(Alert.FOREVER);
        txtpassword.setString("");
        display.setCurrent(lerror, frmhome);
    
            }
            else{
             try {
            if(in != null) {
                in.close();
            }
            if(hc != null) {
                hc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  
          validateUser();
        }
        }
        else if(c==forgot){
            setDisplay(getpword);
        }
        
        else if(c==cmdexit){
            try {
                destroyApp(true);
            } catch (MIDletStateChangeException ex) {
                ex.printStackTrace();
            }
          
        } 
        else if(c==tickback){
            ctrip=new CheckTrip(this);
            setDisplay(menu);
        }
        else if(d==getpword){
            
            if(c==getpword.cmdback){
                display.setCurrent(frmhome);
            }
            else if(c==getpword.cmdok){
                getpword.getPassword(getpword.uname.getString().trim());
                if(getpword.s.equals("NO")){
                    getpword=new Forgot(this);
         Alert lerror = new Alert("Error", "Your password has not been found!", null, AlertType.ERROR);
         lerror.setTimeout(Alert.FOREVER);
        display.setCurrent(lerror, frmhome);  
                }
                else {
        Alert lerror = new Alert("Password Found!", "Your password is: "+getpword.s, null, AlertType.INFO);
        lerror.setTimeout(Alert.FOREVER);
        getpword=new Forgot(this);
        display.setCurrent(lerror, frmhome);
        
                }
            }
        }
        else if(d==ctrip){
            if(c==ctrip.cmdsubmit){
                
                if(ctrip.from.getString().equals("")||ctrip.to.getString().equals("")){
          Alert lerror = new Alert("Empty field(s)!", "Enter All Fields", null, AlertType.ERROR);
          lerror.setTimeout(Alert.FOREVER);
        display.setCurrent(lerror, ctrip);
                    
                }
               
                else{
                 ctrip.check();
                 choosetrip=ctrip.t;
                
               if(ctrip.s.equals("NO TRIP")||ctrip.s.equals("NO ROUTE")){
         Alert lerror = new Alert("NO TRIP", "Try choosing a different date or destination!", null, AlertType.ERROR);
        ctrip=new CheckTrip(this);
        lerror.setTimeout(Alert.FOREVER);
        display.setCurrent(lerror, ctrip); 
               }
               else{
               setDisplay(choosetrip);
               }
                }
            }
            else if(c==ctrip.cmdback){
                ctrip=new CheckTrip(this);
                setDisplay(menu);
             }
            }  
        else if(d==cr){
            if(c==cr.cmdback){
                cr=new CancelReservation(this);
                setDisplay(menu);
            }
            else if(c==cr.cmdok){
                cr.cancel(clientId);
                if(cr.s.equals("NO")){
         Alert lerror = new Alert("Error", "Ticket Number you have entered is invalid or has either been used!", null, AlertType.ERROR);
        cr=new CancelReservation(this);
        lerror.setTimeout(Alert.FOREVER);
        display.setCurrent(lerror, cr); 
               }
               else{
         Alert lerror = new Alert("Successful", "Ticket Number:"+cr.ticket.getString()+" was successfully cancelled" +
                 "", null, AlertType.INFO);
        cr=new CancelReservation(this);
        lerror.setTimeout(Alert.FOREVER);
        display.setCurrent(lerror, menu);  
                    
               }
                
                
            }
        }   
        
        
        else if(d==choosetrip){
            
            if(c==choosetrip.cmdback){
                choosetrip=new ChooseTrip(this);
                setDisplay(ctrip);
            }
            else if(c==choosetrip.cmdok){
              int y= choosetrip.trips.size();
              for(int i=0;i<y;i++){
                  if(choosetrip.trips.isSelected(i)){
                      System.out.println("SELECTED-"+ctrip.tid[i]);
                      tripid=ctrip.tid[i];
                      us.getSeats(ctrip.tid[i],ctrip.travelDate,ctrip.triptime[i]);
                      
                  }
                  
              }
              us.showList();
              setDisplay(us);
              
              
            }
        }
        else if(d==us){
            if(c==us.cmdback){
                us=new UnbookedSeats(this);
                setDisplay(choosetrip);
            }
            else if(c==us.cmdok){
                
                 boolean[] booked=new boolean[us.seatNum.size()];
          book=new StringItem[us.seatNum.size()];
          us.seatNum.getSelectedFlags(booked);
         int x=0;
         bkk=new String[us.seatNum.size()];
         for(int a=0;a<booked.length;a++){
             
            if(booked[a]){
                System.out.println(us.seatNum.getString(a));
                book[x]=new StringItem("",us.seatNum.getString(a)+"\n",Item.BUTTON);
                x++;
            }  
          
      }   
         try{
            String g=book[0].getText();
            confirm=new Confirm(this);    
            setDisplay(confirm);
         }
         catch(NullPointerException n){
           Alert lerror = new Alert("Error", "You must Choose ATLEAST a seat before you proceed!", null, AlertType.ERROR);        
        display.setCurrent(lerror, us); 
        lerror.setTimeout(Alert.FOREVER);   
        
         }
         }
        }
            
            
     else if(d==clientUpdate){
             if(c==clientUpdate.cmdback){
                 clientUpdate=new Update(this);
                 setDisplay(personal);
             }   
             else if(c==clientUpdate.cmdok){
                 if(clientUpdate.pword.getString().equals("")||clientUpdate.confirm.getString().equals("")
                         ||!clientUpdate.pword.getString().trim().equals(clientUpdate.confirm.getString().trim())){
           
        Alert lerror = new Alert("Error!", "Either one field is blank or the passwords don't match!", null, AlertType.ERROR);
        
        lerror.setTimeout(Alert.FOREVER);
         display.setCurrent(lerror, clientUpdate);             
                     
                 }
                 else{
                     clientUpdate.change();
        Alert lerror = new Alert("Update Successful", "Your Password was successfully changed", null, AlertType.INFO);        
        display.setCurrent(lerror, menu); 
        lerror.setTimeout(Alert.FOREVER);  
                 }  
             }
            }
           
            else if(d==confirm){
                if(c==confirm.cmdback){
                    setDisplay(us);
                }
                else if(c==confirm.cmdproceed){
                    String[] allbk=new String[book.length];
                    int u=0;
               try{
         while(!book[u].getText().equals(null)){
          allbk[u]=book[u].getText();
         u++;
                    }
        }
        catch(NullPointerException  j){
        }
             
              
         payment.pay(clientId, tripid, allbk);
        
         
                }
            }
            
        else if(d==menu){
            if(c==menu.cmdselect){
            int s=menu.chtask.getSelectedIndex();
            switch(s){
               case 0: setDisplay(ctrip);
                    break;
               case 1 : gr=new GetReserved(this);
                        gr.get(clientId);
                        
                        setDisplay(gr);
                    break;
               case 2: setDisplay(cr);
                    break;
               case 3 : setDisplay(ps);
                   
                    break;
               case 4: personal.getDetails();
                       setDisplay(personal);
                   break;
               case 5: setDisplay(as);
                   break;
               case 6:setDisplay(help); 
                   break;
             //   case 7:setDisplay(xtra);
               //     break;
              
           } 
       
            }
            else if(c==menu.cmdexit){
                try {
                    destroyApp(true);
                } catch (MIDletStateChangeException ex) {
                    ex.printStackTrace();
                }
            }
    }
        else if(d==city){
            String s;
            if(c==city.cmdok){
                
                for(int i=0;i<city.size();i++){
                    if(city.isSelected(i)){
                        s=city.cities[i];
                        
                     System.out.println(s);   
                        setDisplay(ctrip);
                        ctrip.from.setString("");
                     ctrip.from.setString(s);
                      city=new City(this);
                    }
                }
                
            }
            else if(c==city.cmdgo){
                for(int i=0;i<city.size();i++){
                    if(city.isSelected(i)){
                        s=city.cities[i];
                        
                     System.out.println(s);   
                        setDisplay(ctrip);
                      ctrip.to.setString("");  
                     ctrip.to.setString(s);
                     city=new City(this);
                    }
                }
            }
        }
        else if(d==as){
            if(c==as.cmdback){
                as=new AccountSummary(this);
               setDisplay(menu);
            }
            else if(c==as.cmdok){
                    int size=as.summary.getSelectedIndex();
          switch(size){
              case 0: gb.select();
                  setDisplay(gb);
                  break;
              case 1:
                  break;
          }
                }
        }
        else if(d==gb){
            if(c==gb.cmdback){
                gb=new getBalance(this);
                setDisplay(menu);
            }
        }
        else if(d==personal){
            if(c==personal.cmdback){
                
                setDisplay(menu);
                personal.deleteAll();
            }
            else if(c==personal.cmdupdate){
                setDisplay(clientUpdate);
            }
        }
        else if(d==help){
            if(c==help.cmdback){
                setDisplay(menu);
            }
            else if(c==help.cmdok){
                int size=help.getSelectedIndex();
                switch(size){
                    case 0:setDisplay(r);
                    break;
                    case 1:setDisplay(p);
                        break;
                    case 2:setDisplay(ph);
                        break;
                    case 3:setDisplay(log);
                        break;
                   
                }
               
            }
        }
        else if(d==ps){
            if(c==ps.cmdback){
                ps=new ParcelStatus(this);
                setDisplay(menu);
            }
            else if(c==ps.cmdcheck){
                if(ps.txtpid.getString().equals("")){
        Alert error = new Alert("Empty Field!", "Please input the parcel ID First!", null, AlertType.ERROR);
        display.setCurrent(error,ps);
          error.setTimeout(Alert.FOREVER);
                }
                
                else{
                  ps.check(); 
                  
                  if(ps.s.length()<5){
        Alert error = new Alert("Error!", "The parcel was not found!", null, AlertType.ERROR);
        error.setTimeout(Alert.FOREVER);
        ps=new ParcelStatus(this);
        display.setCurrent(error,ps);
                  }
                }
            }
            else if(c==ps.cmdok){
                setDisplay(ps);
            }
        }
  /*      else if(d==xtra){
            if(c==xtra.cmdok){
                int s=xtra.more.getSelectedIndex();
                switch(s){
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }
            else if(c==xtra.cmdback){
                setDisplay(menu);
            }
        } */
        else if(d==gr){
            if(c==gr.cmdback){
                setDisplay(menu);
            }
            else if(c==gr.cmdok){
                int size=gr.reserved.getSelectedIndex();
                
                for(int i=0;i<gr.reserved.size();i++){
                    //n
                }
                
                
                switch(size){
                    
                }
                
            }
        }
        else if(d==r){
            if(c==r.cmdback){
                setDisplay(help);
            }
        }
        else if(d==p){
            if(c==p.cmdback){
                setDisplay(help);
            }
        }
        else if(d==log){
            if(c==log.cmdback){
                setDisplay(help);
            }
        }
        else if(d==ph){
            if(c==ph.cmdback){
                setDisplay(help);
            }
        }
       
    }
 public void itemStateChanged(Item i) {
      if(i==menu.chtask){
            int s=menu.chtask.getSelectedIndex();
               switch(s){
               case 0: setDisplay(ctrip);
                    break;
               case 1 : 
                        gr=new GetReserved(this);
                        gr.get(clientId);
                        gr.showList();
                        setDisplay(gr);
                    break;
               case 2: setDisplay(cr);
                    break;
               case 3 : setDisplay(ps);
                    break;
               case 4: personal.getDetails();
                       setDisplay(personal);
                   break;
               case 5: setDisplay(as);
                   break;
              // case 6: setDisplay(xtra);
                //   break;
                case 6:setDisplay(help);
                    break;
           } 
        }
      else if(i==ctrip.from){
          setDisplay(city);
          city.from();
          
      }
      else if(i==ctrip.to){
          setDisplay(city);
          city.to();
         
      }
      else if(i==as.summary){
          int size=as.summary.getSelectedIndex();
          switch(size){
              case 0:gb.select();
                  setDisplay(gb);
                  break;
              case 1:
                  break;
          }
      }
      
    
    }

    
}
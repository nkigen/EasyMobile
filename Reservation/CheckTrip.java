package Reservation;
import EasyCoach.Reservation.ChooseTrip;
import Menu.Home;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Calendar;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
public class CheckTrip extends Form implements Runnable{
    public DateField dtday;
    public TextField from;
    public TextField to;
    Home home;
    
    public ChooseTrip t;
    public Command cmdback;
    public Command cmdsubmit;
    public Command cmdk;
    private String tdate;
    public String[] months=new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Dec"};
    private String day;
    private String year;
    public String travelDate;
    private String month;
    private HttpConnection hc;
    private InputStream in;
    private byte[] data;
    public String[] trips;  //data for entire trips
    public String[] tripdata;  //data for a specific trip
    public String[] tid;  //trip ids
    public String[] space; //space Available
    public String[] triptime;  // trip time
    public String[] fare; //fare
    public String err;
    public String s=new String();
 
    //constructor
   public CheckTrip(Home home){
       
  super("Trip Lookup");
  this.home=home;
  
    dtday=new DateField("Select Date",DateField.DATE);
    cmdback=new Command("Back",Command.BACK,2);
    cmdsubmit=new Command("Submit",Command.OK,1);
    cmdk=new Command("Submit",Command.OK,1);
    from=new TextField("From","",25,TextField.ANY);
     to=new TextField("To","",25,TextField.ANY);
     
     append(dtday);
     append(from);
     append(to);
     addCommand(cmdback);
     addCommand(cmdsubmit);
    setCommandListener(home);
    setItemStateListener(home);
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
 
   
   
   public void verifyDate(){
       String now;
       Date dt;
       int b=0;
       
       Calendar cal=Calendar.getInstance();
       now=cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH);
       
       System.out.println(now);
       System.out.println(dtday.getDate());
       tdate=dtday.getDate().toString();
   String [] st= split(tdate,' ');
   day=st[2];
   year=st[5];
   
   
   for(int i=0;i<months.length;i++){
       if (st[1].equals(months[i])){
           i++;
           month=Integer.toString(i);
       }
   }
   
   
   travelDate=year+"-"+month+"-"+day;

   System.out.println(month);
 
   
   }
public void check(){
    verifyDate();
    Thread t=new Thread(this);
    t.start();
}
public void getCon(){
    System.out.println("Nelsonn--"+tid.length);
    
}
    public void run() {
     String url="http://127.0.0.1/Easy_Coach/j2me/jchecktrip.php";
     String parameter=url+"?tdate="+travelDate+"&from="+from.getString().trim()+"&to="+to.getString().trim();
     System.out.println(parameter); 
        
        try {
            hc = (HttpConnection)Connector.open(parameter);
            hc.setRequestMethod(HttpConnection.GET);
            in=hc.openInputStream();
            int rc=hc.getResponseCode();
            if(rc==HttpConnection.HTTP_OK){
                data=new byte[(int)(hc.getLength())];
                int actual=in.read(data);
                s=new String(data).toString().trim();
                
                System.out.println(s);
                if(s.equals("NO TRIP")){     
                }
                else if(s.equals("NO ROUTE")){
                }
                else if(s.equals("FULL")){
                  
                }
                
                else{
                    int a=0;
                    int b=0;
                    int c=0;
                    int d=0;
                    trips=split(s,':');
                    
                    /*Initialize */
                    tripdata=new String[trips.length-1];
                    tid=new String[tripdata.length+1];
                    space=new String[tripdata.length+1];
                    triptime=new String[tripdata.length+1];
                    fare=new String[tripdata.length+1];
                    
                    for(int i=0;i<trips.length;i++){
                        System.out.println(trips[i]);
                        tripdata=split(trips[i],','); 
                        
                   for(int h=0;h<tripdata.length;h++){
                    //   System.out.println(tripdata[h]); 
                     if(h==0){
                         tid[a]=tripdata[h];
                         System.out.println("TRIP ID-"+tid[a]); 
                         a++;
                     }
                     else if(h==1){
                         space[b]=tripdata[h];
                         System.out.println("SPACE-"+space[b]); 
                         b++;
                     }
                     else if(h==2){
                         triptime[c]=tripdata[h];
                         System.out.println("TRIP TIME-"+triptime[c]); 
                         c++;
                         
                     }
                     else if(h==3){
                         fare[d]=tripdata[h];
                         System.out.println("FARE-"+fare[d]); 
                         d++;
                         
                     }
                    
                     
                  } 
                    }
                    System.out.println(tid.length);
                t=new ChooseTrip(home,tid,space,triptime,fare);
                
                 
                }
               
          
               
            }
            else{
                System.out.println("JHFDSADUIOUYT");
            }
           
           
        }
        catch (IOException e) {
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
    
    
}
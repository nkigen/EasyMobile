package EasyCoach.Reservation;

import Menu.Home;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;


public class GetReserved extends Form implements Runnable{
    
    private String cid;
    Home home;
   public ChoiceGroup reserved;
   public Command cmdok;
   public Command cmdback;
    private HttpConnection hc;
    private InputStream in;
    private byte[] data;
    public String s;
    public String[] singletrip;
   public String[] tripdata;
    public String[] ticketno;
   public String[] seatno;
   public String[] tdate;
   public String[] ttime;
   private int y=0;
    public GetReserved(Home home){
     super ("Currently Reserved Seats");
     reserved=new ChoiceGroup("",Choice.EXCLUSIVE);
     
       cmdok=new Command("OK",Command.OK,1);
       cmdback=new Command("Back",Command.BACK,2);
       
       
     //  addCommand(cmdok);
       addCommand(cmdback);
        setCommandListener(home);
       setItemStateListener(home);
        
    }
    public GetReserved(){
      super ("Currently Reserved Seats");   
    }
    public void showList(){
       for(int i=0;i<y;i++){
           reserved.append("Ticket No:"+ticketno[i]+" Seat No:"+seatno[i]+" Travel Date:"+tdate[i]+" " +
                   " Time:"+ttime[i], null);
       }
       append(reserved); 
    }
    public void get(String s){
       cid=s;
        Thread t=new Thread(this);
        t.start(); 
    }

    public void run() {
            
     String url="http://127.0.0.1/Easy_Coach/j2me/jgetreserved.php";
     String parameter;
         
          parameter=url+"?cid="+cid;
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
            if(s.equals("NO")){
           
                
       }
       else{
       int len=split(s,':').length;
       singletrip=new String[len];
       singletrip=split(s,':');
       ticketno=new String[singletrip.length];
       seatno=new String[singletrip.length];
       tdate=new String[singletrip.length];
       ttime=new String[singletrip.length];
       
       tripdata=new String[4];
       int a,b,c,d;
       a=b=c=d=0;
       
       for(int i=0;i<singletrip.length;i++){
           tripdata=split(singletrip[i],',');
           
           for(int j=0;j<tripdata.length;j++){
               if(j==0){
                   ticketno[a]=tripdata[j];
                   
                   a++;
               }
               else if(j==1){
                   seatno[b]=tripdata[j];
                 //  System.out.println(seatno.length);
                   b++;
                   
               }
               else if(j==2){
                   tdate[c]=tripdata[j];
                   c++;
                   
               }
               else if(j==3){
                   ttime[d]=tripdata[j];
                   d++;
               }
           } 
       } 
       System.out.println(seatno.length);
       y=seatno.length;
       showList(); 
       
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
        
 
    
}

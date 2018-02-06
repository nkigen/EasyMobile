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





public class UnbookedSeats extends Form implements Runnable{
    Home home;
    
    private String tid;
    private String ttime;
    private String tdate;
    private HttpConnection hc;
    private InputStream in;
    private byte[] data;
    private String s;
     public String[] seat=new String[]{"A1","B1","B2","B3","B4","C1","C2","C3","C4","D1","D2","D3","D4","E1","E2","E3","E4","F1","F2","F3","F4","G1","G2","G3","G4","H1","H2","H3","H4"};
    public String[] unbooked=new String[seat.length];
    public int te[] = new int[seat.length];
    public String[] bookedSeats;
    public int bookedsize;
    public Command cmdok;
    public Command cmdback;
    public ChoiceGroup seatNum;
    
    public UnbookedSeats(Home home){
        super("Select Seat");
        this.home=home;
        cmdok=new Command("OK",Command.OK,1);
        cmdback=new Command("Back",Command.BACK,1);
        seatNum=new ChoiceGroup(null,Choice.MULTIPLE);
        addCommand(cmdok);
        addCommand(cmdback);
        
        setCommandListener(home);
        
        
    }
    public void showList(){
        System.out.println("NEL-Check--"+seat.length);
       // System.out.println("NEL-Check--"+bookedSeats.length);
        for(int u=0;u<(unbooked.length-bookedsize);u++){
          seatNum.append(unbooked[u],null);
          System.out.println("NEL-Check"+unbooked[u]);
          System.out.println("NEL-Check Booked size:--"+bookedsize);
      }   
        append(seatNum);
        
        
        
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
public void getSeats(String s,String y,String z){
    tid=s;
    ttime=z;
    tdate=y;
    Thread t=new Thread(this);
    t.start();  
    
}
    public void run() {
        String url="http://127.0.0.1/Easy_Coach/j2me/jgetseats.php";
     String parameter=url+"?tid="+tid+"&time="+ttime+"&date="+tdate;
     System.out.println(parameter); 
        int a = 0;
        int b = 0;
        int c = 0;
        
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
             if(s.equals("FULL")){
                 
             } 
              else if(s.equals("EMPTY")){
                    System.out.println("BUS EMPTY");
                   unbooked=seat;
                 for(int i=0;i<unbooked.length;i++){
                     System.out.println(unbooked[i]);
                 }
                }
             else{
                    bookedSeats=split(s,',');   
                    bookedsize=bookedSeats.length;
                for(int i=0;i<seat.length;i++){
                    for(int j=0;j<bookedSeats.length;j++){
                        if(seat[i].equals(bookedSeats[j])){
                          te[a]=i;
                          System.out.println(te[a]);
                       a++;
                        }
                        
                    }
                }
                
             
           for(int f=0;f<seat.length;f++){
               if(f==te[b]){
                   b++;
               }
               else{
                   unbooked[c]=seat[f];
                   System.out.println(unbooked[c]);
                   c++;
               }
           } 
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
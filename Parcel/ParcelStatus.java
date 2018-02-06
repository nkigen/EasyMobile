package EasyCoach.Parcel;
import Menu.Home;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
public class ParcelStatus extends Form implements Runnable {
      public StringItem pid;
    public StringItem consignor;
    public StringItem consignee;
    public StringItem source;
    public StringItem destination;
    public StringItem dateSend;
    public StringItem datereceived;
    public StringItem expectedDelivery;
    public StringItem parcelType;
    public StringItem pstatus;
    public Command cmdback;
    public String s=null;
    public Command cmdok;
    public Command cmdcheck;
    public TextField txtpid;
    Home home;
    public String[] temp = null;
    public String id;
    private HttpConnection hc;
    private InputStream in;
 public String res;
    public ParcelStatus(Home home){
    super("Enter Parcel Details");
    this.home=home;
    txtpid=new TextField("Parcel ID","",6,TextField.NUMERIC);
    cmdback=new Command("Back",Command.BACK,2);
    cmdcheck=new Command("Check",Command.OK,1);
    append(txtpid);
    //addCommand(cmdback);
    addCommand(cmdback); 
    addCommand(cmdcheck);
    setCommandListener(home);
    

}
  public void check(){
       Thread t=new Thread(this);
        t.start(); 
  }
public final static String[] split(String str, char separatorChar)
{
    if (str == null)
    {
        return null;
    }

    int len = str.length();

    if (len == 0)
    {
        return null;
    }

    Vector list = new Vector();
    int i = 0;
    int start = 0;
    boolean match = false;

    while (i < len)
    {
        if (str.charAt(i) == separatorChar)
        {
            if (match)
            {
                list.addElement(str.substring(start, i).trim());
                match = false;
            }
            start = ++i;
            continue;
        }
        match = true;
        i++;
    }

    if (match)
    {
        list.addElement(str.substring(start, i).trim());
    }

    String[] arr = new String[list.size()];
    list.copyInto(arr);

    return arr;
}
    public void run() {
        
         String parameter;
        String result;
       
        
        int t=0;
        int m=0;
        byte[] data;
        String url="http://127.0.0.1:80/Easy_Coach/j2me/jcheckparcel.php";
        parameter = url+"?pid="+txtpid.getString();
        
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
                
            temp= split(s,',');
                for(int i=0;i<temp.length;i++){
                    System.out.println(temp[i]);
                }
           loadParcel(temp);
            }
            else{
                System.out.println("JHFDSADUIOUYT");
            }
           
           
        }
        catch (IOException e) {
            e.printStackTrace();
            res=e.toString();
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

   public void loadParcel(String[] d){
         pid=new StringItem("Parcel-ID",d[0]);
         parcelType=new StringItem("Type",d[1]);
         consignor=new StringItem("Consignor",d[2]);
         dateSend=new StringItem("Date Send",d[3]);
         source=new StringItem("Source",d[4]);
         destination=new StringItem("Destination",d[5]);
         pstatus=new StringItem("Parcel Status",d[6]);
        // cmdok=new Command("OK",Command.OK,1);
        // cmdback=new Command("Back",Command.BACK,2);  
         removeCommand(cmdback);
         addCommand(cmdback);
         append(parcelType);
         append(consignor);
         append(dateSend);
         append(source);
         append(destination);
         append(pstatus);
        // setCommandListener(home);
       //  removeCommand(cmdcheck);
   }
}
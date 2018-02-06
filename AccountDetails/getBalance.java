
package EasyCoach.AccountDetails;

import Menu.Home;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;



public class getBalance extends Form implements Runnable{
   Home home;
    private HttpConnection hc;
    private InputStream in;
    private byte[] data;
    public String s;
    public String bal;
  public Command cmdback;
    public Command cmdok;
    public StringItem balance;
    public StringItem det;
    public getBalance(Home home){
        super("Balance");
        this.home=home;
       cmdback=new Command("Back",Command.BACK,2);
     cmdok=new Command("OK",Command.OK,1);
    
     //  addCommand(cmdok);
       
       addCommand(cmdback);
       setCommandListener(home);
       setItemStateListener(home);
    }
    
   public void setBal(String g,String b){
       bal=g;
        balance=new StringItem("Your Current Balance is KSH:",g);
        det=new StringItem("Your last recharge date was:",b);
       System.out.println("NELSON-"+bal);
       append(balance);
       append(det);
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
public void select(){
    
    Thread t=new Thread(this);
    t.start();
}
    public void run() {
          String parameter;
          
         String url="http://127.0.0.1/Easy_Coach/j2me/jgetbal.php";
         
     parameter=url+"?cid="+home.clientId;
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
                String sd[]=split(s,',');
                setBal(sd[0],sd[1]);
              
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

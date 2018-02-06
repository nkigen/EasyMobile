
package Menu;

import Menu.Home;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

public class Forgot extends Form implements Runnable {
   Home home;
   private StringItem si;
   public String pword;
   public TextField uname;
   public Command cmdback;
   public Command cmdok;
    private HttpConnection hc;
    private InputStream in;
    private byte[] data;
    public String s;
    public Forgot(Home home){
        super("Forgot Password");
        si=new StringItem("","Enter you username below");
        uname=new TextField("Username","",20,TextField.ANY);
        cmdok=new Command("OK",Command.OK,1);
        cmdback=new Command("Back",Command.BACK,2);
        
        append(si);
        append(uname);
        addCommand(cmdok);
        addCommand(cmdback);
        setCommandListener(home);
    }
public void getPassword(String st){
    pword=st;
    Thread t=new Thread(this);
    t.start();
    
}
    public void run() {
          String parameter;
          
         String url="http://127.0.0.1/Easy_Coach/j2me/jforgot.php";
         
     parameter=url+"?uname="+pword;
     
     
     
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

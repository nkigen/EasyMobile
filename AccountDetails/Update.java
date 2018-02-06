
package EasyCoach.AccountDetails;

import Menu.Home;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;


/**
 * @author Nel
 */
public class Update extends Form implements Runnable {
    
    Home home;
    public TextField pword;
    public TextField confirm;
    private String clientID;
    public Command cmdok;
    public Command cmdback;
    private HttpConnection hc;
    private InputStream in;
    private byte[] data;
    
    
    public Update(Home home){
        super("Change Password");
        this.home=home;
        clientID=home.clientId;
        pword=new TextField("Password","",20,TextField.PASSWORD);
        confirm=new TextField("Confirm","",20,TextField.PASSWORD);
        cmdback=new Command("Back",Command.BACK,2);
       cmdok=new Command("OK",Command.OK,1);
       append(pword);
       append(confirm);
       addCommand(cmdok);
       addCommand(cmdback);
       setCommandListener(home);
        
    }
    
    public void change(){
        
        
            Thread t=new Thread(this);
            t.start();
      
    }

    public void run() {
          
     String url="http://127.0.0.1/Easy_Coach/j2me/jupclient.php";
     String parameter=url+"?cid="+home.clientId+"&pword="+pword.getString();
       System.out.println(parameter); 
        String s;
        
        
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
                
                if(s.equals("OK")){
                    System.out.println("CHANGED");
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

package EasyCoach.Reservation;

import Menu.Home;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;


public class Payment implements Runnable{
    public TextField txtpin;
    public Command cmdsubmit;
    public Command cmdback;
    Home home;
    private HttpConnection hc;
    private InputStream in;
 //   private HttpConnection[] http;
  //  private InputStream[] input;
    
    private byte[] data;
    public String s;
    public String clientid;
    public String tid;
    public String[]seat;
    public String[] response;
    
    public Payment(Home home){
     this.home=home;
     
   } 
    public void pay(String cid,String trip,String[] s){
        clientid=cid;
        tid=trip;
        int u=0;
         try{
         while(!s[u].equals(null)){
         u++;
                    }
        }
        catch(NullPointerException j){
        }
        seat=new String[u];
        for(int i=0;i<u;i++){
            seat[i]=s[i];
        }
        response=new String[seat.length]; 
      //  System.out.println("RESPONSE"+response.length);
        start();
         
    }
    public void start(){
        Thread t=new Thread(this);
        t.start();
    }
    
    public void run() {
         String temp;     
     String url="http://127.0.0.1/Easy_Coach/j2me/jticket.php";
     
   //  System.out.println(temp);
     String parameter;
        
      for(int i=0;i<seat.length;i++){  
          temp=null;
          temp=seat[i].trim().toString();
          parameter=url+"?seat="+temp+"&cid="+clientid+"&tid="+tid;
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
                response[i]=s;
                System.out.println("DATA OUT=="+response[i]);
                
                if(response[i].equals("NO")){
                    System.out.println("NELSON J2ME");
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
     home.getTick(response);
    }
    
}
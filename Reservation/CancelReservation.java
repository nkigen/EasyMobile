/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasyCoach.Reservation;

import Menu.Home;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;

public class CancelReservation extends Form implements Runnable{
Home home;
public String s;  //server msg
public TextField ticket;
public Command cmdok;
public Command cmdback;
  public String cid;
    private HttpConnection hc;
    private InputStream in;
    private byte[] data;
    public CancelReservation(Home home){
        super("Cancel Reservation");
        this.home=home;
        cmdok=new Command("OK",Command.OK,1);
        cmdback=new Command("Back",Command.BACK,2);
        ticket=new TextField("Ticket Number","",12,TextField.NUMERIC);
        
        append(ticket);
        addCommand(cmdok);
        addCommand(cmdback);
        setCommandListener(home);
    }
public void cancel(String c){
    cid=c;
    Thread t=new Thread(this);
    t.start();
    
}
    public void run() {
        String parameter;
          
         String url="http://127.0.0.1/Easy_Coach/j2me/jcancelbooking.php";
         
     parameter=url+"?cid="+cid+"&tid="+ticket.getString().trim();
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
                s="NO";
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

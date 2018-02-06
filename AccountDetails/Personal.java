
package EasyCoach.AccountDetails;
import Menu.Home;
import Menu.Menu;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;


public class Personal extends Form implements Runnable{
  
    public Command cmdupdate;
     Menu menu;
     Home home;
    public Command cmdback;
    public String temp[];
    private HttpConnection hc;
    private InputStream in;
    public StringItem clientid;
    public StringItem fname;
    public StringItem surname;
    public StringItem mname;
    public StringItem add;
    public StringItem zip;
    public StringItem phone;
    public StringItem city;
    public StringItem pword;
    public StringItem gender;
    public StringItem email;
    public Personal(Home home){
    super("Client Details");
    this.home=home;
    cmdupdate=new Command("Change Password",Command.OK,2);
    cmdback=new Command("Back",Command.BACK,2);
    addCommand(cmdupdate);
    addCommand(cmdback);
    setCommandListener(home);
    
  
}
    public void getDetails(){
        Thread t=new Thread(this);
        t.start();
    }
    public void loadDetails(String[] d ){
        clientid=new StringItem("Client ID",d[0]);
        surname=new StringItem("Surname",d[1]);
        fname=new StringItem("First Name",d[2]);
        mname=new StringItem("Middle Name",d[3]);
        gender=new StringItem("Gender",d[4]);
        phone=new StringItem("Phone No",d[5]);
        email=new StringItem("E-mail",d[6]);
        add=new StringItem("Address",d[7]);
        city=new StringItem("City",d[8]);
        zip=new StringItem("Postal Code",d[9]);
        
    append(clientid); 
    append(fname);
    append(mname);
    append(surname);
    append(gender);
    append(phone);
    append(email);
    append(add);
    append(city);
    append(zip);
    append(pword);
    
        
        
        
    }
    public final static String[] split(String str, char separatorChar){
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
        String s;
        String temp[];
        String parameter;
        String result;
        int t=0;
        int m=0;
        byte[] data;
        String url="http://127.0.0.1:80/Easy_Coach/j2me/jclients.php";
        parameter = url+"?cid="+home.clientId;
        
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
           loadDetails(temp);
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
    

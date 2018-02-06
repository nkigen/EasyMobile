
package EasyCoach.Help;


import Menu.Home;
import javax.microedition.lcdui.*;

public class Login extends Form {
     Home home;
   StringItem login;
   StringItem forgot;
   public Command cmdback;
    public Login(Home home){
        super("Login Help");
        this.home=home;
        login=new StringItem("Login","Enter your username and password then click on the menu command.Choose the " +
                "'OK' button.");
        forgot=new StringItem("Forgot password?","Click on the menu command in the login screen then Choose" +
                "'Forgot?' button.Enter your username then click OK.IF your username is correct, your password will be " +
                "displayed on the screen.");
        cmdback=new Command("Back",Command.BACK,1);
        
        append(login);
        append(forgot);
        addCommand(cmdback);
        setCommandListener(home);
        
    }
}

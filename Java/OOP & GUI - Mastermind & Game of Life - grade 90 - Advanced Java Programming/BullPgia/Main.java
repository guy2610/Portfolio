import javax.swing.JOptionPane;
public class Main {
    public static void main(String[] args)
    {
        int want=1;
        JOptionPane.showMessageDialog(null,"press the OK button to start","Welcome To The Game Bull Pgiah!!",JOptionPane.INFORMATION_MESSAGE);
        while(want==1)
        {
            String inp,massage;
            int bull=0;
            int pgiah;
            int counter=0;
            String strOfInputs=null;
            Game game=new Game();
            String secretString=null;
            inp=JOptionPane.showInputDialog("enter your guess:");
            massage= game.Feedback(inp);
            while(bull!=4)
            {
                while (massage.indexOf("|")==-1)
                {
                    JOptionPane.showMessageDialog(null,massage,"Error",JOptionPane.ERROR_MESSAGE);
                    inp=JOptionPane.showInputDialog("please enter proper input for your guess");
                    massage= game.Feedback(inp);
                }
                counter++;
                if(strOfInputs==null)
                {
                    strOfInputs=inp;
                }
                else strOfInputs=strOfInputs+" | "+inp;
                bull=Integer.parseInt(massage.substring(0,massage.indexOf("|")));
                pgiah=Integer.parseInt(massage.substring(massage.indexOf("|")+1,massage.length()));
                if (bull!=4)
                {
                    inp = JOptionPane.showInputDialog("enter your next guess, you have succeeded on Bull: " + bull + " times and on Pgiah: " + pgiah + " times!\nyou have tried : "+counter+" times.\nthose are your proper inputs: "+strOfInputs);
                    massage = game.Feedback(inp);
                }
                secretString=inp;
            }
            JOptionPane.showMessageDialog(null,"Congratulations you have figured the secret string which is: "+secretString+" !!!\nyou have tried : "+counter+" times.\nthose are your proper inputs: "+strOfInputs);
            inp=JOptionPane.showInputDialog("Do you want to play another game[yes/no]: ");
            if (inp.equals("yes"))
                want=1;
            else want=0;
        }

    }
}
/*אינטרקציה עם המשתמש עי תיבת דו שיח */
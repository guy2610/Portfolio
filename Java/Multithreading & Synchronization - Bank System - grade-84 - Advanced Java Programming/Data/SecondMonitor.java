
public class SecondMonitor extends Thread {
	private Data data;
	private final int NumOfCouples=10;
	
	public SecondMonitor(Data data)/*constructor for the get difference monitor*/
	{
		this.data=data;
	}
	
	public void run() /*override run function for the get difference monitor. get difference the data 10 times, each time waits 100 milisec for the next action of get difference*/
	{
		super.run();
		for (int i = 0; i < NumOfCouples; i++) 
		{ 
			data.getDiff();
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*התהליך השני פונה 10 פעמים לאובייקט בכדי לקבל את ההפרש (באמצעות המתודה
getDiff .(עליכם לבצע השהייה בין פנייה לפנייה ולהציג בפלט הסטנדרטי את התוצאות
שהתקבלו.
*/
}

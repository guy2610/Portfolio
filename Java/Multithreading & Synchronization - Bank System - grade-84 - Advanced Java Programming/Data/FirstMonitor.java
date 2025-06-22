import java.util.Random;

public class FirstMonitor extends Thread{

	private Data data;
	private final int NumOfCouples=10;
	
	public FirstMonitor(Data data)/*constructor for the updating monitor*/
	{
		this.data=data;
	}
	public void run() /*override run function for the updating monitor. update the data 10 times, each update waits 100 milisec for the next update*/
	{
		super.run();
		Random rd= new Random();
		int dx,dy;
		for (int i = 0; i < NumOfCouples; i++) 
		{
			dx=rd.nextInt(101);
			dy=rd.nextInt(101);
			data.update(dx, dy);
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*התהליך הראשון מייצר 10 זוגות של מספרים אקראיים ומעדכן באמצעותם בזה אחר זה
את האובייקט מסוג Data) באמצעות המתודה update .(עליכם לבצע השהייה בין עדכון
לעדכון ולהציג בפלט הסטנדרטי את ערכי המספרים. */
}

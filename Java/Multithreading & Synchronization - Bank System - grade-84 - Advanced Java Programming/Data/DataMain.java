
public class DataMain {

	public static void main(String[] args) {//פונקציה ראשית לסעיפים א ו-ב
		//making an object and running the first and second monitors in parallel 
		Data d= new Data(0, 0);
		FirstMonitor f= new FirstMonitor(d);
		SecondMonitor s= new SecondMonitor(d);
		f.start();
		s.start();

	}
	/*public static void main(String[] args) {//פונקציה ראשית לסעיף ג
		//making an object and making 4 monitors for each kind running them in parallel. 
		Data d= new Data(0, 0);
		FirstMonitor []f= new FirstMonitor[4];
		SecondMonitor []s= new SecondMonitor[4];
		for (int i = 0; i < f.length; i++) {
			f[i]=new FirstMonitor(d);
			s[i]=new SecondMonitor(d);
		}
		for (int i = 0; i < s.length; i++) {
			f[i].start();
			s[i].start();
		}
	}*/

}

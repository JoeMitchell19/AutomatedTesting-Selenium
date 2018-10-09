public class Run {
	
	public static void main(String[] args) {
		
		QAConsulting r1,r2, r3;
		r1 = QAConsulting.CreateObject();
		r2 = QAConsulting.CreateObject();
		r3 = QAConsulting.CreateObject();
		for (int i = 0; i < 100; i++) {
			r1.sysout();
			r2.sysout();
			r3.sysout();
		}
	}

}
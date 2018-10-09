public class QAConsulting
{
	static QAConsulting R;
	int x = 5;
	
	private QAConsulting()
	{
		x++;
	}
	
	public static QAConsulting CreateObject()
	{
		if (R == null)
		{
			R = new QAConsulting();
		}
		return R;
	}
	
	public void sysout() {
		System.out.println(x);
	}
}
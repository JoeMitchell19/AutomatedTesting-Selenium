package com.qa.java8;

public class Threadtest {
	
	public static void main(String[] args)
	{
		
		int a;
		
		try
		{
			a = Integer.parseInt(args[0]);
			
			if (a==1)
			{
				throw new BimboExeception();
			}
			
		} catch (BimboExeception e)
		{
			e.printStackTrace();
		}
		System.out.println("1738");
	}
}
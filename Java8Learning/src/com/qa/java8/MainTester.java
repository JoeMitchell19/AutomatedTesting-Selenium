package com.qa.java8;

import java.util.Arrays;

public class MainTester {

	public static void main(String[] args)
	{
		String[] toppings = {"2", "1a","a","11","222","sa"};
		String[] news = {"A123", 
				"B111" , 
				"C453" , 
				"C3331" , 
				"D3456" , 
				"D2245" , 
				"C111" , 
				"A1" , 
				"B22"};
		//System.out.println(doIt(toppings));
		//name(toppings);
		doPredefined(news);
	}
	
	/**
	 * Given a String[] with strings in the format, starting with a letter between A-D and ending with a 1-5 digit number.

		A123
		B111
		C453
		C3331
		D3456
		D2245
		C111
		A1
		B22
		
		remove all strings that start with the letter "D"
		convert all remaining strings to integers by applying the following logic based on the preceding letter:
		A: multiply by 2
		B: add 100
		C: subtract 100
		Remove all numbers that are divisible by 5
		@return the largest number in the collection
	 * */
	
	private static void doPredefined(String[] news) {
		CharSequence  d = "D";
		Arrays
		.stream(news)
		.filter(it -> !(it.contains(d)))
		.map(v->{
					try {
						if (v.contains("A")) {
							return Integer.valueOf(v.substring(1))*2;
						}
						else if (v.contains("B")) {
							return Integer.valueOf(v.substring(1))+100;
						}
						else if (v.contains("C")) {
							return Integer.valueOf(v.substring(1))-100;
						}
					} catch (Exception e) {
						return 0;
					}
			return 0;
		})
		.reduce((c,t)-> Math.max(c, t))
		.ifPresent(System.out::println);
	}

	public static int withoutJava8(String a[])
	{
		final long startTime = System.currentTimeMillis();
		int d = 0;
		
		for (String string : a)
		{
			int c;
			try {
				c = Integer.parseInt(string);
			} catch (Exception e) {
				c = 0;
			}
			if (string.length()<3)
			{
				d += c*10;
			}
		}
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime) );
		return d;
	}
	
	public static void usingJava8(String a[]) {
		final long startTime = System.currentTimeMillis();
		Arrays.stream(a)
		.filter((jo)->jo.length()<3)
		.map(num -> {
			try {
				return Integer.parseInt(num);
				} catch (Exception e) {
					return 0;
					}})
		.filter(num -> num>0)
		.map(numb->numb*10)
		.reduce((c,t)->t+c)
		.ifPresent(System.out::println);
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime) );
	}
}
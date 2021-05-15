package precodes;
import java.util.*;

public class readerwriter
{
	static int count=0;
	static class readerthread extends Thread
	{
		
		public void run()
		{
			reader();
		}
	}
	static class writerthread extends Thread
	{
		
		public void run()
		{
			writer();
		}
	}
	
	public static void semwritewait()
	{	
		if(r!=x)
		{
			System.out.println(Thread.currentThread().getName()+" Other Reader reading!!WAIT!!");
			while(r!=x);
		}
		else
		if(w<=0)
		{
		 System.out.println(Thread.currentThread().getName()+" Other Writer writing!!WAIT");
		 while(w<=0);
		}
		else
		{	
		w=w-1;
		}
	}
	public static void semwritesignal()
	{
		w=w+1;
	}
	public static void semreadwait()
	{	
		if(w<=0)
		{
			System.out.println(Thread.currentThread().getName()+" Other Writer reading!!WAIT");
			while(w<=0);
		}
		else
		if(r<=0)
		{
			System.out.println(" All readers reading!!!");
			while(r<=0);
		}
		else
		{	
		r=r-1;
		}
	}
	public static void semreadsignal()
	{
		r=r+1;
	}

	public static volatile int w=1,r,x,y,val=0,mem=0,readval;
	
	public static void reader()
	{
			while(true && val<150)
			{
				count++;
			System.out.println(Thread.currentThread().getName()+" requests reading");
			semreadwait();
			System.out.println(Thread.currentThread().getName()+" reading from block");
			System.out.println(Thread.currentThread().getName()+" reading complete...");
			semreadsignal();
			if(count>3) { writer();count=0;}
			}
	}
	public static void writer()
	{
			while(true && val<150)
			{
				count++;
			System.out.println(Thread.currentThread().getName()+" requests writing");
			semwritewait();
			val++;
			System.out.println(Thread.currentThread().getName()+" writing in block");
			System.out.println(Thread.currentThread().getName()+" writing complete...");
			semwritesignal();
			if(count>3) { reader();count=0;}
			}
	}
	
	
	public static void main(String args[]) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter no. of readers=");
		x=sc.nextInt();
		System.out.println("Enter no. of writers=");
		y=sc.nextInt();
		w=1;
		r=x;
		readerthread rt[]=new readerthread[x];
		writerthread wt[]=new writerthread[y];
		int i=0;
		String name="";
		for(i=0;i<x;i++)
		{
			rt[i]=new readerthread();
			name="Reader "+Integer.toString(i+1)+":";
			rt[i].setName(name);
		}
		name="";
		for(i=0;i<y;i++)
		{
			wt[i]=new writerthread();
			name="Writer "+Integer.toString(i+1)+":";
			wt[i].setName(name);
		}
		for(i=0;i<x;i++)
		{
			rt[i].start();
		}
		for(i=0;i<y;i++)
		{
			wt[i].start();
		}
		sc.close();
	}

}

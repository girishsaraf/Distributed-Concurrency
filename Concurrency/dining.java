package precodes;
import java.util.Scanner;

public class dining
{
	static class philosopher extends Thread
	{
		public int num;
		public philosopher(int num)
		{
			this.num=num;
		}
		
		public int getnum()
		{
			return num;
		}

		public void run()
		{
			while(true)
			{
				int x;
				x=getnum();
				System.out.println(Thread.currentThread().getName()+"Thinking!!!");
				semwait(x%nop);
				System.out.println(Thread.currentThread().getName()+"Fork no."+x%nop+" acquired");
				semwait((x+1)%nop);
				System.out.println(Thread.currentThread().getName()+"Fork no."+(x+1)%nop+" acquired");
				System.out.println(Thread.currentThread().getName()+"EATING!!!");
				System.out.println(Thread.currentThread().getName()+"Fork no."+(x+1)%nop+" released");
				semsignal((x+1)%nop);
				System.out.println(Thread.currentThread().getName()+"Fork no."+x%nop+" released");
				semsignal(x%nop);
			}
		}
		
	}
	public static volatile int forkstatus[];
	public static volatile int nop,value=0;

	public static void semwait(int s)
	{
		if(forkstatus[s]<=0)
		{
			System.out.println(Thread.currentThread().getName()+"Fork no."+s+" Unavailable");
			while(forkstatus[s]==0);
		}
		else
		{
			forkstatus[s]=forkstatus[s]-1;
		}
			
	}
	public static void semsignal(int s)
	{
		forkstatus[s]=forkstatus[s]+1;
		try
		{
			Thread.currentThread().sleep(10);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter number of Philosphers=");
		nop=sc.nextInt();
		forkstatus=new int[nop];
		int i;
		for(i=0;i<nop;i++)
		{
			forkstatus[i]=1;
		}
		philosopher pt[]=new philosopher[nop];
		String name="";
		for(i=0;i<nop;i++)
		{
			pt[i]=new philosopher(i);
			name="Philospher "+Integer.toString(i)+":";
			pt[i].setName(name);
		}
		for(i=0;i<nop;i++)
		{
			pt[i].start();
		}
	
		sc.close();

	}

}

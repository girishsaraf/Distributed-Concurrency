package precodes;
import java.util.*;
public class producerconsumer
{
	public static void semwait(int s)
	{	
		if(s<=0)
			{
			 System.out.println("Critical section already accessed by other process!!");
			 while(s<=0);
			}
		else
		{	
		s=s-1;
		}
	}
	public static void semsignal(int s)
	{
		s=s+1;
	}
	public static int s,n,in,out,val=0;
	public static int queue[];
	public static void produce()
	{
		Random rn=new Random();
		int x;
		while(true && val<250)
		{
		if((in+1)%n==out)
		{
			System.out.println("Queue full. Producer waits");
			  try
				{
					Thread.sleep(1);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
		}
		else
		{
		semwait(s);
		val++;
		queue[in]=val;
		System.out.println("Producer produced :"+queue[in]);
		in=(in+1)%n;
		semsignal(s);
		x=rn.nextInt(5);
				if(x>=3)
				{
					System.out.println("Producer stopped by Random");
					try
					{
						Thread.sleep(1);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
		}
	}
   }
	public static void consume()
	{
		Random rn=new Random();
		int y;
		int value=0;
		while(true && value<250)
		{
		if(in==out)
		{
			System.out.println("Queue Empty. Consumer waits");
			try
				{
					Thread.sleep(1);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}	
		}
		else
		{
		semwait(s);
		value=queue[out];
		System.out.println("Consumer consumed :"+queue[out]);
		queue[out]=-1;
		out=(out+1)%n;
		semsignal(s);
		y=rn.nextInt(5);
		if(y>=3)
		{
			System.out.println("Consumer stopped by Random");
			try
			{
				Thread.sleep(1);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	   }
	}
	public static void main(String args[]) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Queue Length=");
		n=sc.nextInt();
		n++;
		queue=new int[n];
		s=1;
		in=0;
		out=0;
		Thread t1 = new Thread(new Runnable()
        {
            @Override
			public void run()
            {
                produce();	
            }
        });
        Thread t2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
            
                 consume();
        }});
        t1.start();
        t2.start();   
		sc.close();
	}
}
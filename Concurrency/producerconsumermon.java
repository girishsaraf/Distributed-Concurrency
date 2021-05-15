import java.util.*;

public class producerconsumermon
{
	static int full=1,empty=2; 
	public static void enter(int queue[]) {
		if(count==n) {
			System.out.println("Queue is Full. Waiting for Consumer to consume");
			cwait(queue,1);
		}
		queue[in]=1;
		in=(in+1)%n;
		count++;
		System.out.println("Producer produced :"+queue[in]);
		if(count==1)
		csignal(queue,2);
	}
	public static void remove(int queue[]) {
		if(count==0) {
			System.out.println("Queue is Empty. Waiting for Producer to produce");
			cwait(queue,2);
		}
		count--;
		System.out.println("Consumer consumed :"+queue[out]);
		out=(out+1)%n;
		if(count==n-1)
		csignal(queue,1);
	}
	public static void cwait(int queue[],int cond)
	{	
		if(cond==full) {
			while(count==n) {}
		}
		if(cond==empty) {
			while(count==0) {}
		}
	}
	public static void csignal(int queue[],int cond)
	{
		if(cond==full) {
			produce();
		}
		if(cond==empty) {
			consume();
		}
	}

	public static int n,in,out,val=0,count=0;
	public static int queue[];
	
	public static void produce()
	{
		int x=0;
		while(x<10) {
			x++;
			enter(queue);
		}
	}
	public static void consume()
	{
		int x=0;
		while(x<10) {
			remove(queue);
			x++;
		}
	}
	public static void main(String args[]) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Queue Length=");
		n=sc.nextInt();
		n++;
		queue=new int[n];
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
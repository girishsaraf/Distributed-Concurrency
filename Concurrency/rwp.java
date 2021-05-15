import java.util.*;
class rwp{
		static Random r;
		static int s,numr,numw,i,j,n;
		static boolean check;
		static boolean semsignal(){
				s++;
				return false;
			}
		static boolean semwait(){
			if(s<=0){
			 	while(s<=0);
				}
			else{	
				s=s-1;
				}
			return true;
			}
		static void read(){
			Random r=new Random();
			int val=r.nextInt(numr+1);
			if(true)
				{
					try{
						check=semwait();
						if(check==true) {
						System.out.println("Reader has access\nReader:"+val+" read from memory location\nReader suspends access");
						check=semsignal();
						}
					}catch(Exception e){
							e.printStackTrace();
						}
				}
			}
		static void write(){
			Random r=new Random();
			int val=r.nextInt(numw+1);	
			if(true)
				{
					try{
						check=semwait();
						if(check=true) {
						System.out.println("Writer has access\nWriter:"+val+" wrote into memory location\nWriter suspends access");
						check=semsignal();
						}
					}catch(Exception e){
							e.printStackTrace();
						}
				}
				
			}
		public static void main(String args[]){
				Scanner sc=new Scanner(System.in);
				System.out.println("**Reader Writer Problem**");
				System.out.println("Enter the number of Readers and Writers");
				numr=sc.nextInt();
				numw=sc.nextInt();
				s=1;
				Thread r=new Thread(new Runnable()
				{
					@Override
            		public void run()
            		{
						for(int i=0;i<5;i++)
							read();	
						try {
							Thread.sleep(100);
						}
						catch(Exception e) {}
            		}	
				});
				Thread w=new Thread(new Runnable()
				{
					@Override
            		public void run()
            		{
						for(int i=0;i<5;i++)
							write();	
            		}	
				});
				
				w.start();
				r.start();
			}
	}
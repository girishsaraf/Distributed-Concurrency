package precodes;
import java.util.*;
class fcfsdisk{
		static int cylinder[],n,i,j,noc,sumtime,avgtime,head;
		static void fcfs(){
				sumtime=0;
				System.out.println("The order of execution of requests is:\n");
				for(i=0;i<n;i++){
						System.out.print(cylinder[i]+"\t");
					}
				for(i=0;i<n;i++){
						sumtime=sumtime+Math.abs(head-cylinder[i]);
						head=cylinder[i];	
					}
				System.out.println("\n\nTotal Seek time is:"+sumtime);
				System.out.println("\nAverage Seek Time is:"+((float)(sumtime/n)));
			}
		public static void main(String args[]){
				Scanner sc=new Scanner(System.in);
				System.out.println("FCFS Scheduling");
				System.out.println("Enter the number of points for traversal:");
				n=sc.nextInt();
				cylinder=new int[n];
				System.out.println("Enter the points");
				for(i=0;i<n;i++){
						cylinder[i]=sc.nextInt();
					}
				System.out.println("Enter the Current Head Position:");
				head=sc.nextInt();
				fcfs();
			}
	}
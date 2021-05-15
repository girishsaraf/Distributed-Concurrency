package precodes;
import java.util.*;
class fcfs{
		static int arr[],burst[],wait[],turn[],id[],n,i,j,cases,timestamp,start;
		static double xwait,xturn;
		static void calculate(){
				System.out.println("The Gantt Chart of the processes is:\n\n");
				timestamp=0;
				start=0;
				xwait=0;
				xturn=0;
				for(i=0;i<n;i++){
						wait[i]=timestamp-arr[i];
						timestamp=timestamp+burst[i];
					}
				for(i=0;i<n;i++){
						System.out.print("|P"+id[i]+"|");
					}
				System.out.println();
				for(i=0;i<n;i++) {
						System.out.print(start+"   ");
						start=start+burst[i];
				}
				System.out.println(start+"   ");
				System.out.println("\n\n\nAverage Waiting time is:");
				for(i=0;i<n;i++){
						xwait=xwait+wait[i];
					}
				System.out.println((xwait/n));
				System.out.println("Turn-around Time for the processes is:");
				for(i=0;i<n;i++){
						System.out.println("Process "+id[i]+"\t"+(wait[i]+burst[i]));
					}
				System.out.println("Average Turn-around time is:");
				for(i=0;i<n;i++){
						xturn=xturn+wait[i]+burst[i];
					}
				System.out.println((xturn/n));
			}
		static void sort(){
				int temp;
				for(i=0;i<n;i++){
						for(j=i+1;j<n;j++){
								if(arr[i]>arr[j]){
										temp=arr[i];
										arr[i]=arr[j];
										arr[j]=temp;
										temp=burst[i];
										burst[i]=burst[j];
										burst[j]=temp;
										temp=id[i];
										id[i]=id[j];
										id[j]=temp;
									}
							}
					}
			}
		public static void main(String args[]){
				Scanner sc=new Scanner(System.in);
				System.out.println("Enter the number of processes:");
				n=sc.nextInt();
				arr=new int[n];
				burst=new int[n];
				wait=new int[n];
				turn=new int[n];
				id=new int[n];
				for(i=0;i<n;i++){
						System.out.println("For the "+(i+1)+" process:");
						System.out.println("Enter the Arrival Time:");
						arr[i]=sc.nextInt();
						System.out.println("Enter the Burst Time:");
						burst[i]=sc.nextInt();
						id[i]=i+1;
					}
				sort();
				System.out.println("The Processes sorted according to arrival time are as follows:\n\n");
				System.out.println("Process Id\tArrival Time\tBurst time");
				for(i=0;i<n;i++){
						System.out.println(id[i]+"\t\t\t"+arr[i]+"\t\t"+burst[i]);
					}
				calculate();
			}
	}
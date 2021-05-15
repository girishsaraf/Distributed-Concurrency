import java.util.*;
class sjf{
		static int arr[],burst[],wait[],turn[],id[],n,i,j,k,cases,timestamp,start,time,tempo[];
		static double xwait,xturn;
		static int bt=0,min=0;
		static void calculate() {
			start=0;
			timestamp=0;	
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
			tempo=new int[n];
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
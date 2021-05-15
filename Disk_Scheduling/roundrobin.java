import java.util.*;
class roundrobin{
	static int id[],arr[],burst[],btx[],wt[],tat[],completed[],n,total,quantum,i,j,next,rem;
	static int xwait,xturn;
	static void rest() {
		xwait=0;
		xturn=0;
		for(i=0;i<n;i++) {
			xwait=xwait+wt[i]-arr[i];
			xturn=xturn+wt[i]+burst[i]-arr[i];
		}
		System.out.println("Average Waiting Time:"+(xwait/n)+" ms\nAverage Turnaround Time:"+(xturn/n)+" ms");
	}
	static void roundrobincheck() {
		System.out.println("The Gantt Chart for the processes is:");
		System.out.println("--------------------------------------------------------");
		next=0;
		for(i=0;i<total;) {
			if(btx[next]<quantum)	rem=btx[next];
			else if(btx[next]==0)	rem=0;
			else rem=quantum;
			if(rem>0) {
				System.out.print("P"+id[next]);
			}
			btx[next]=btx[next]-quantum;
			for(j=0;j<n;j++) {
				if(j==next) {
					continue;
				}
				else if(btx[j]>0) {
					wt[j]=wt[j]+rem;
				}
				else {
					continue;
				}
			}
			next=(next+1)%n;
			if(rem==0) {
					next=(next++)%n;
				}
			i=i+rem;
			
			if(rem>0) {
				System.out.print("("+rem+") | ");
			}
		}
		System.out.println("\n--------------------------------------------------------");
		rest();
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
	public static void main(String[] args)
	{
            Scanner sc=new Scanner(System.in);
            System.out.println("***Round Robin Scheduling Algorithm***");
            System.out.println("Enter the number of processes:");
            n=sc.nextInt();
            id=new int[n];
            arr=new int[n];
            burst=new int[n];
            wt=new int[n];
            tat=new int[n];
            completed=new int[n];
            btx=new int[n];
            for(i=0;i<n;i++) {
            	System.out.println("Enter the Arrival Time and Burst Time of process P"+(i+1)+":");
            	id[i]=i+1;
            	arr[i]=sc.nextInt();
            	burst[i]=sc.nextInt();
            	btx[i]=burst[i];
            	wt[i]=0;
            	tat[i]=0;
            }
            System.out.println("Enter the Time Quantum:");
            quantum=sc.nextInt();
            sort();
            total=0;
            for(i=0;i<n;i++) {
            	total=total+burst[i];
            }
            roundrobincheck();
	}
}
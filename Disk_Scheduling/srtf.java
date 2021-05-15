import java.util.*;
public class srtf
{
    static int p[],at[],bt[],btx[],wt[],tat[],completed[],n,total;
	public static void main(String[] args)
	{
            Scanner sc=new Scanner(System.in);
            System.out.println("***Shortest Remaining Time First***");
            System.out.println("Enter the number of processes:");
            n=sc.nextInt();
            p=new int[n];
            at=new int[n];
            bt=new int[n];
            wt=new int[n];
            tat=new int[n];
            completed=new int[n];
            btx=new int[n];
            int i,j;
            for(i=0;i<n;i++) {
                completed[i]=0;
            }
            for(i=0;i<n;i++) {
            	System.out.println("Enter the Arrival Time and Burst Time of process P"+(i+1)+":");
            	p[i]=i+1;
            	at[i]=sc.nextInt();
            	bt[i]=sc.nextInt();
            	btx[i]=bt[i];
            }
             for(i=0;i<n;i++) {
            	 for(j=0;j<n-1;j++) {
                    if(at[j]>at[j+1]) {
                    	int temp;
                    	temp=at[j];
                    	at[j]=at[j+1];
                    	at[j+1]=temp;	
                    	temp=bt[j];
                    	bt[j]=bt[j+1];
                    	bt[j+1]=temp;	
                    	temp=p[j];
                    	p[j]=p[j+1];
                    	p[j+1]=temp;
                    }
            	 }		
            } 
            total=0;
            for(i=0;i<n;i++) {
            	total+=bt[i];
            }
            System.out.println("The Gantt Chart of the above processes is:");
        	System.out.println("--------------------------------------------------------");
            for(i=0;i<total;i++) {
            	int nextjob=-1;
            	int small=9999;
            	for(j=0;j<n;j++) {
                    if(at[j]<=i && completed[j]==0 && small>bt[j] && bt[j]!=0) {
                    	small=bt[j];
                    	nextjob=j;
                    }
            	}  
            	if(nextjob!=-1) {	
                    bt[nextjob]--;
                    System.out.print("P"+(p[nextjob])+" | ");
                    for(int k=0;k<n;k++) {
                    	if(k!=nextjob && at[k]<=i && completed[k]==0) {
                            wt[k]++;
                    	}
                    } 
                    if(bt[nextjob]==0)
                        completed[nextjob]=1;
            	}
            	
            }
            System.out.println("\n---------------------------------------------------------");
            System.out.println("\nProcess \t Waiting Time \t Turnaround Time");
            for(i=0;i<n;i++) {
            	tat[i]=wt[i]+btx[i];
            	System.out.println(p[i]+"\t\t  "+wt[i]+"\t\t   "+tat[i]);
            }
            int totalturn=0,totalwait=0;
            for(i=0;i<n;i++) {
            	totalturn=totalturn+tat[i];
            	totalwait=totalwait+wt[i];
            }
            double xwait=((double)totalwait)/n;
            double xturn=((double)totalturn)/n;
            System.out.println("\n\nAverage Waiting Time:"+xwait+" ms\nAverage Turnaround Time:"+xturn+" ms");
	}
}
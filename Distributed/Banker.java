
package precodes;import java.util.*;
class Banker{
		static int alloc[][],claim[][],need[][],avail[][],i,j,k,np,nr,sequence[];
		static boolean pdone[];
		static void findsequence(int itr){
				boolean check=true;
				int pos=0;
				for(i=0;i<np;i++){
					if(pdone[i]==true){
							continue;
						}
					else{
						for(j=0;j<nr;j++){
							
								if(avail[j][0]>=need[i][j]){
										check=true;
										continue;
									}
								else{
										check=false;
										break;
									}
							}
					}
					if(check==false){
							System.out.println("Process P"+i+" cannot be executed."+itr);
							continue;
						}
					else{
							for(j=0;j<nr;j++){
									avail[j][0]=avail[j][0]+alloc[i][j];
									need[i][j]=0;
								}
							sequence[pos++]=i;
							pdone[i]=true;
							i=0;
							
						}
				}
				System.out.println("The sequence of processes is:");
				for(i=0;i<np;i++){
						System.out.print("P"+sequence[i]+"->");
					}
			}
		public static void main(String args[]){
				Scanner sc=new Scanner(System.in);
				System.out.println("Enter the number of processes:");
				np=sc.nextInt();
				System.out.println("Enter the number of resources:");
				nr=sc.nextInt();
				alloc=new int[np][nr];
				claim=new int[np][nr];
				need=new int[np][nr];
				avail=new int[nr][0];
				sequence=new int[np];
				pdone=new boolean[np];
				System.out.println("Enter the allocation matrix:");
				for(i=0;i<np;i++){
						for(j=0;j<nr;j++){
								System.out.println("Current Allocation of Process P"+i+"--Resource R"+j+"--");
								alloc[i][j]=sc.nextInt();
							}
					}
				System.out.println("Enter the maximum claim matrix:");
				for(i=0;i<np;i++){
						for(j=0;j<nr;j++){
								System.out.println("Maximum claim of Process P"+i+"--Resource R"+j+"--");
								claim[i][j]=sc.nextInt();
							}
					}
				System.out.println("Enter the current available resource vector:");
				for(i=0;i<nr;i++){
						System.out.println("Resource R"+i+"--");
						avail[i][0]=sc.nextInt();
					}
				for(i=0;i<np;i++){
						for(j=0;j<nr;j++){
								need[i][j]=claim[i][j]-alloc[i][j];
							}
					}
				for(i=0;i<np;i++){
						pdone[i]=false;
					}
				for(i=0;i<5;i++)
				findsequence(i);
			}
	}
import java.util.*;
import java.io.*;
class SSTF
{
public static void main(String args[])
{
int i,n,c,l,m,h,stime=0,t=0,min,v;
int a[]=new int[100]; 
int f[]=new int[100];
for(i=0;i<100;i++)
f[i]=0;
Scanner s=new Scanner(System.in);
System.out.println("Enter no of disk accesses");
n=s.nextInt();
System.out.println("Enter no of cylinders");
c=s.nextInt();
System.out.println("Enter head position");
h=s.nextInt();
l=h;
System.out.println("Enter accesses");

for(i=0;i<n;i++)
{
a[i]=s.nextInt();
}
System.out.println("Access Sequence is as follows:");
for(v=0;v<n;v++)
{

min=1000;
for(i=0;i<n;i++)
{
if(f[i]==0)
{
if(l>a[i])
{
if(l-a[i]<min)
{
min=l-a[i];
t=i;
}
}
else
{
if(a[i]-l<min)
{
min=a[i]-l;
t=i;
}
}

}
}
f[t]=1;
if(l>a[t])
stime=stime+l-a[t];
else
stime=stime+a[t]-l;
System.out.print(a[t]+" ");
l=a[t]; 

}
System.out.println("Total seek time is:"+stime);
}
}

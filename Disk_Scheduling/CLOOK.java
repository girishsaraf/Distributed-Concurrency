package precodes;
import java.util.*;
import java.io.*;
class CLOOK
{
public static void main(String args[])
{
int i,n,c,l,m,h,ph,stime=0,t=0,diff,min,v,lcount=0,hcount=0,max,k;
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
System.out.println("Enter previous head position");
ph=s.nextInt();
diff=h-ph;
l=h;
System.out.println("Enter accesses");

for(i=0;i<n;i++)
{

a[i]=s.nextInt();

if(a[i]<h)
lcount++;
else hcount++;

} 
k=1000;max=0;
for(i=0;i<n;i++)
{
if(a[i]<k)
k=a[i];
if(a[i]>max)
max=a[i];
}


System.out.println("Access Sequence is as follows:");
for(v=0;v<n;v++)
{
min=1000;
if(diff>0)
min=-1000;
for(i=0;i<n;i++)
{
if(diff<0)
{
if(f[i]==0)
{

if(l-a[i]>=0 && l-a[i]<min)
{
min=l-a[i];
t=i;

}
}

}
if(diff>0)
{
if(f[i]==0)
{

if(l-a[i]<=0 && l-a[i]>min)
{
min=l-a[i];
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
if(diff>0)
hcount--;
if(diff<0)
lcount--;
if(diff<0 && lcount ==0)
{lcount=hcount+1;
l=max;

stime=stime+max-k;
}


if(diff>0 && hcount ==0)
{
l=k;

hcount=lcount+1;

stime=stime+max-k;
}
}
System.out.println("Total seek time is:"+stime);
}
}

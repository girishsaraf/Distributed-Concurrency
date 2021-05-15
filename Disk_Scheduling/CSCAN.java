package precodes;
import java.util.*;
import java.io.*;
class CSCAN
{
public static void main(String args[])
{
int i,n,c,l,m,h,ph,stime=0,t=0,diff,min,v,lcount=0,hcount=0,max;
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
min=1000;max=0;
for(i=0;i<n;i++)
{
if(a[i]<min)
min=a[i];
if(a[i]>max)
max=a[i];
}
if(diff<0)
stime=min;
if(diff>0)
stime=c-1-max;
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

if(l-a[i]>0 && l-a[i]<min)
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

if(l-a[i]<0 && l-a[i]>min)
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
l=c-1;
System.out.print(0+" "+(c-1)+" ");
stime=stime+c-1;
}


if(diff>0 && hcount ==0)
{
l=0;
System.out.print((c-1)+" "+0+" ");
hcount=lcount+1;

stime=stime+c-1;
}
}
System.out.println("Total seek time is:"+stime);
}
}

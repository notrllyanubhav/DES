import java.io.*;
import java.util.*;
import java.lang.Math;
import java.math.BigInteger;
import java.security.SecureRandom;


class chr
{    
    static void print(int[][] arr,int m,int n)
    {
        int i,j;
        for(i=0;i<m;i++)
        {
            for(j=0;j<n;j++)
                System.out.print(arr[i][j] + "  ");
            System.out.println();
        }
        System.out.println();
    }

    static void print1(int[] arr)
    {
        int i=0;
        for(i=0;i<arr.length;i++)
            System.out.print(arr[i]+ " ");
        System.out.println();
    }
    static String intToBinary(int a) 
    {
        String temp = Integer.toBinaryString(a);
        while(temp.length() !=8)
        {
            temp = "0"+temp;
        }
        return temp;
    }

    static BigInteger fact(int n)
    {
        BigInteger f=new BigInteger("1");
        for(int i=2;i<=n;i++)
            f=f.multiply(BigInteger.valueOf(i));
        
        return f;   
    }

    static int BinaryToint(String s)
    {
        int c=0;
        for(int i=0;i<8;i++)
        {
            if(s.charAt(i)=='1')
               c= c + (int)Math.pow(2,7-i); 
        }
        return c;
    }

    static BigInteger getseed()
    {
        BigInteger A,B,C,D,E;
        long t1=System.nanoTime();
        long t2=Runtime.getRuntime().freeMemory();
        long t3=System.currentTimeMillis();
        long t4=Runtime.getRuntime().totalMemory();


        //System.out.println("t1 "+t1);
        //System.out.println("t2 "+t2);
        //System.out.println("t3 "+t3);
        String ntime=Long.toString(t1);
        String fmem=Long.toString(t2);
        String mtime=Long.toString(t3);
        String tmem=Long.toString(t4);
        B= BigInteger.ONE; C= BigInteger.ONE; E=BigInteger.ONE;
        A= B.multiply(new BigInteger(ntime));
        B= B.multiply(new BigInteger(fmem));
        C= C.multiply(new BigInteger(mtime));
        D= B.multiply(C);
        A= A.multiply(D);
        E= E.multiply(new BigInteger(tmem));
        A= A.multiply(E);
        //String z=A.toString();
        //System.out.println("seed:"+z);
        //System.out.println("Length of seed is :"+z.length());
        

        return A;
    }

    static String generaterandom(BigInteger N, BigInteger  M,int n)
    {
        
        BigInteger A;String str=""; BigInteger B; int x; int y; int sum=0; String str1="";
        A=BigInteger.ONE;
        B=BigInteger.ONE;
        A=A.multiply(N);
        B=B.multiply(M);
        A=A.multiply(B);
        str=A.toString();
        for(int i=0;i<str.length();i++)
        {
            x=Integer.parseInt(str.substring(i,i+1));
            y=Integer.bitCount(x);
            sum+=y;
            x=0;
        }
        sum= (sum%n);
        if(sum < 128)
        {
            str1=intToBinary(sum);
            return str1;
        }
        else
        {
            str1=intToBinary(127);
            return str1;
        }
        

    }

    static String BigtoString(BigInteger A)
    {
        String str=A.toString();
        return str;
    }

    static BigInteger keyindex(int[] a,int n)
    {
        BigInteger A=BigInteger.ONE;
        for(int i=0;i<a.length;i++)
        {
            if(i!=n)
            {
                A=A.multiply(BigInteger.valueOf(a[i]));
            }
        }
        return A;
    }

    static String getXOR(String a, String b)  
    {  
       
        String res = "";  
          
        for (int i = 0; i <a.length(); i++) 
        {  
            if (a.charAt(i) == b.charAt(i))  
                res += "0";  
            else
                res += "1";  
        }  
        return res;  
    }  

    static String arrtostr(int arr[][],int m,int n)
    {
        String str=""; String str1="";
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                str1=intToBinary(arr[i][j]);
                str=str+str1;
            }
        }
        return str;
    }

    static String encrypt(String str,int row,int c,BigInteger A,BigInteger  B,int flag,int key[])
    {
        String S1="",S2=""; String S3="",S=""; int ind=1;
        for(int i=flag;i<8*(8*c);i+=8)
        {
            S1=generaterandom(A, B, ind);
            //System.out.println("hi :"+i+" flag value is: "+flag);
            S2=str.substring(i,i+8);
            S3=getXOR(S1, S2);
            S=S+S3;
            S3="";
            S1="";
            S2="";
            ind=ind+1;
            B=keyindex(key,ind);          
           
        }
        return S;
    }

    static String decrypt(String str,int row,int c,BigInteger A,BigInteger B,int flag,int key[])
    {
        String S1="",S2="",S3="",S=""; int ind=1;
        for(int i=flag;i<8*(8*c);i+=8)
        {
            S1=generaterandom(A,B,ind);
            S2=str.substring(i,i+8);
            S3=getXOR(S1,S2);
            S=S+S3;
            S3="";
            S1="";
            S2="";
            ind=ind+1;
            B=keyindex(key,ind);
        }

        return S;
    }

    static int numone(String s)
    {
        int c=0;
        for(int i=0;i<s.length();i++)
            if(s.charAt(i)=='1')
                c++;
        return c;
    }
    static int numzero(String s)
    {
        int c=0;
        for(int i=0;i<s.length();i++)
            if(s.charAt(i)=='0')
                c++;
        return c;
    }

    static int[] keycompress(int[] key)
    {
        int i,j;
        int[] arr=new int[7];
        for(i=0;i<7;i++)
        {
           arr[i]=key[i];
        }
        return arr;
    }
    public static void main(final String args[]) throws IOException
    {
        String a="anubhav"; char ch; int k; int c=0;
        int i=0;
        SecureRandom obj=new SecureRandom();
        File fl=new File("C:\\Users\\anubh\\Desktop\\Stuff\\text.txt");
        BufferedReader br=new BufferedReader(new FileReader(fl));

        String S; int[][] arr= new int[8][8];
        while((S=br.readLine())!= null)
        {
            //System.out.println(S);
            for(i=0;i<8;i++)
            {
                k= (int)(S.charAt(i));
                arr[c][i]=k;
            }
            c++;
        }
        //print(arr,8,8);
        File f2=new File("C:\\Users\\anubh\\Desktop\\Stuff\\key.txt");
        BufferedReader br1=new BufferedReader(new FileReader(f2));
        int[] key=new int[8]; String S1; int x; int n; int c1=0;
        while((S1=br1.readLine())!= null)
        {
            //System.out.println(S1);
            for(x=0;x<8;x++)
            {
                n=(int)(S1.charAt(x));
                key[x]=n;
            }
            c1++;
        }
        //print1(key);
        String str=arrtostr(arr,8,8);
        String str1=str;
        //System.out.println("Block is :"+str);
        //System.out.println("Length of block in bits is :"+str.length());
        int iterations=16; BigInteger[] seed=new BigInteger[16]; int front=0; int rear=8; int row=0; 
        BigInteger[][] xe=new BigInteger[16][8]; BigInteger A=BigInteger.ONE; int flag1=0;
        String f="";int count=0; String f1=""; int sd=9; String fe=""; int flag=0;
        String encr="";String decr="";

        for(i=1;i<=iterations;i++)
        {
            flag=0;
            flag1=0;
            seed[i-1]=getseed();
            //System.out.println("times :"+i);
            for(int j=1;j<=8;j++)
            {
                f1=seed[i-1].toString();
                fe=f1.substring(0,(sd+(5*flag)));
                xe[i-1][j-1]=A.multiply(new BigInteger(fe));
                encr=encr+encrypt(str1,row,j,xe[i-1][j-1],keyindex(key,count),flag1,key);
                //System.out.println("hi :"+j);
                flag++;
                flag1+=64; 
                f="";
                fe="";   
            }
            flag=0;
            flag1=0;
            str1=encr;
            encr="";
        } 
        encr=str1;
        //System.out.println("Encrypted string is: "+encr);
        System.out.println();
        System.out.println();
        System.out.println();
        for(i=iterations;i>=1;i--)
        {
            flag=0;
            flag1=0;
            for(int j=1;j<=8;j++)
            {
                decr=decr+decrypt(str1,row,j,xe[i-1][j-1],keyindex(key,count),flag1,key);
                flag++;
                flag1+=64; 
            }
            flag=0;
            flag1=0;
            str1=decr;
            decr="";
        }
        decr=str1;
        
        //System.out.println("Encrypted string is: "+encr+" "+encr.length());
        //System.out.println("Original String is:  "+str);
        System.out.println();
        System.out.println();
        System.out.println();

        int h1=numone(decr);
        int h2=numzero(decr);
        int h3=numone(str);
        int h4=numzero(str);
        //System.out.println("Encrypted string :"+h1+" "+h2+" "+"Original String :"+h3+" "+h4);
        //System.out.println("Decrypted String : "+ decr);
        //System.out.println(decr.equals(str));
        String test="00001000";
        int z=BinaryToint(test);
        System.out.println("integer is: "+z);

    }
}

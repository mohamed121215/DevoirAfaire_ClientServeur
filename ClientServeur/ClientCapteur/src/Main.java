import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            int m=0;
            do{
                Socket s=new Socket("127.0.0.1",1234);
                OutputStream os=s.getOutputStream();
                PrintWriter pw=new PrintWriter(os,true);
                Scanner sc=new Scanner(System.in);
                System.out.println("login :");
                String login=sc.nextLine();
                System.out.println("password :");
                String password=sc.nextLine();
                System.out.println("role :");
                String role=sc.nextLine();
                pw.println(login+"/"+password+"/"+role);

                InputStream is = s.getInputStream();
                InputStreamReader isr=new InputStreamReader(is);
                BufferedReader br=new BufferedReader(isr);
                String vv="C";
                pw.println(vv);
                String v=br.readLine();
                if(v.equals("0")){
                    m=1;
                    Random r=new Random();
                    while(true){
                        int temperature=(int) r.nextInt(41);
                        int humidite=(int) r.nextInt(101);//0-100
                        int pression=(int) r.nextInt(60)+950;//950-1013
                        System.out.println(temperature+" "+humidite+" "+pression);
                        int numero=3;
                        pw.println(numero+"/"+temperature+"/"+humidite+"/"+pression);
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                else{
                    System.out.println("informations incorrectes");
                }
            }while(m==0);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
import com.mysql.cj.x.protobuf.MysqlxExpr;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class communication extends Thread{
    private Socket s;
    public communication(Socket s){
        this.s=s;
    }
    public void run(){
        try {
            InputStream is = s.getInputStream();
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(isr);
            String authentification=br.readLine();
            String [] auth=authentification.split("/");
            OutputStream os=s.getOutputStream();
            PrintWriter pw=new PrintWriter(os,true);
            Model m=new Model();
            String vv= br.readLine();
            int v=m.verifcompte(auth[0],auth[1],auth[2]);
            pw.println(v);
            if(vv.equals("C")){
                if(v==0){
                    try {
                        while(true){
                            String mesures=br.readLine();
                            String [] M=mesures.split("/");
                            int numero=Integer.parseInt(M[0]);
                            int T=Integer.parseInt(M[1]);
                            int P=Integer.parseInt(M[2]);
                            int H=Integer.parseInt(M[3]);
                            System.out.println(numero+" "+T+" "+P+" "+H);
                            m.stockmesures(numero,T,P,H);
                        }
                    }catch (IOException e) {
                        System.out.println("connexion expiré pour un client");
                    }


                }
                else{
                    pw.println(v);
                }
            }
            if(vv.equals("OP")){
                if(v==1){
                    try{
                        while(true){
                            String choix=br.readLine();
                            System.out.println(choix);
                            if(choix.equalsIgnoreCase("1")){
                                ArrayList<ArrayList<String>> LG=m.AffichageDesMesures();
                                OutputStream out=s.getOutputStream();
                                ObjectOutputStream oos=new ObjectOutputStream(out);
                                oos.writeObject(LG);
                            }
                            if(choix.equalsIgnoreCase("2")){
                                while(true){
                                    String souschoix=br.readLine();
                                    if(souschoix.equalsIgnoreCase("2/1") | souschoix.equalsIgnoreCase("2/2") |souschoix.equalsIgnoreCase("2/3")){
                                        ArrayList<ArrayList<String>> LT=m.AffichageDesMesures();
                                        OutputStream out=s.getOutputStream();
                                        ObjectOutputStream oos=new ObjectOutputStream(out);
                                        oos.writeObject(LT);
                                    }
                                    if(souschoix.equalsIgnoreCase("2/4")){
                                        break;
                                    }
                                }
                            }
                            if(choix.equalsIgnoreCase("3")){
                                ArrayList<String> L=m.calculermoyenne();
                                OutputStream out=s.getOutputStream();
                                ObjectOutputStream oos=new ObjectOutputStream(out);
                                oos.writeObject(L);
                            }
                            if(choix.equalsIgnoreCase("4")){
                                while(true){
                                    String souschoix=br.readLine();
                                    if(souschoix.equalsIgnoreCase("4/1") | souschoix.equalsIgnoreCase("4/2") |souschoix.equalsIgnoreCase("4/3")){
                                        ArrayList<String> L=m.calculermoyenne();
                                        OutputStream out=s.getOutputStream();
                                        ObjectOutputStream oos=new ObjectOutputStream(out);
                                        oos.writeObject(L);
                                    }
                                    if(souschoix.equalsIgnoreCase("4/4")){
                                        break;
                                    }
                                }
                            }

                        }
                    }catch (IOException e) {
                        System.out.println("connexion expiré pour un client");
                    }
                }
                else{
                    pw.println(v);
                }
            }
        } catch (IOException e) {
            System.out.println("connexion expiré pour un client");
        }

    }
}

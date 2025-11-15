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
                String vv="OP";
                pw.println(vv);
                String v=br.readLine();
                if(v.equals("1")){
                    m=1;
                    int choix=0;
                    do{
                        System.out.println("1------Toutes les mesures(LIMIT 10)-------");
                        System.out.println("2------Les mesures /grandeur----------------------");
                        System.out.println("3------Toutes les moyennes AVG-----------");
                        System.out.println("4------La moyenne / grandeur--------");
                        System.out.println("5------Quitter----------------------------------");
                        System.out.print("choix :");
                        choix=sc.nextInt();
                        switch(choix){
                            case 1:{
                                pw.println("1");
                                try {
                                    // Créer ObjectInputStream une seule fois après authentification
                                    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                                    System.out.println("########### Les 10 premiers mesures ######");
                                    System.out.println("[id,numero,date et heure,Température,Pression,Humidité]");
                                    ArrayList<ArrayList<String>> LG=(ArrayList<ArrayList<String>>)ois.readObject();
                                    int cpt=0;
                                    for (ArrayList<String> L:LG){
                                        if(cpt==10){
                                            break;
                                        }

                                        System.out.println("[ "+L.get(0)+" ,"+L.get(1)+" ,"+L.get(2)+" ,"+L.get(3)+" ,"+L.get(4)+" ,"+L.get(5)+" ]");
                                        cpt++;
                                    }
                                } catch (ClassNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                                break;
                            }
                            case 2:{
                                pw.println("2");
                                int souschoix1=0;
                                do {
                                    System.out.println("1---Température---");
                                    System.out.println("2---Pression---");
                                    System.out.println("3---Humidité---");
                                    System.out.println("4---Retourne---");
                                    System.out.println("choix :");
                                    souschoix1=sc.nextInt();
                                    switch (souschoix1){
                                        case 1:{
                                            pw.println("2/1");
                                            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                                            System.out.println("########### Les 10 premiers mesures par T ######");
                                            ArrayList<ArrayList<String>> LG= null;
                                            try {
                                                LG = (ArrayList<ArrayList<String>>)ois.readObject();
                                            } catch (ClassNotFoundException e) {
                                                throw new RuntimeException(e);
                                            }
                                            int cpt=0;
                                            for (ArrayList<String> L:LG){
                                                if(cpt==10){
                                                    break;
                                                }
                                                System.out.println("capteur "+L.get(1)+" : température = "+L.get(3));
                                                cpt++;
                                            }
                                            break;
                                        }
                                        case 2:{
                                            pw.println("2/2");
                                            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                                            System.out.println("########### Les 10 premiers mesures par T ######");
                                            ArrayList<ArrayList<String>> LG= null;
                                            try {
                                                LG = (ArrayList<ArrayList<String>>)ois.readObject();
                                            } catch (ClassNotFoundException e) {
                                                throw new RuntimeException(e);
                                            }
                                            int cpt=0;
                                            for (ArrayList<String> L:LG){
                                                if(cpt==10){
                                                    break;
                                                }
                                                System.out.println("capteur "+L.get(1)+" : pression = "+L.get(4));
                                                cpt++;
                                            }
                                            break;
                                        }
                                        case 3:{
                                            pw.println("2/3");
                                            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                                            System.out.println("########### Les 10 premiers mesures par T ######");
                                            ArrayList<ArrayList<String>> LG= null;
                                            try {
                                                LG = (ArrayList<ArrayList<String>>)ois.readObject();
                                            } catch (ClassNotFoundException e) {
                                                throw new RuntimeException(e);
                                            }
                                            int cpt=0;
                                            for (ArrayList<String> L:LG){
                                                if(cpt==10){
                                                    break;
                                                }
                                                System.out.println("capteur "+L.get(1)+" : humidité = "+L.get(5));
                                                cpt++;
                                            }
                                            break;
                                        }
                                        case 4:{
                                            pw.println("2/4");
                                            break;
                                        }
                                    }
                                }while(souschoix1!=4);

                                break;
                            }
                            case 3:{
                                pw.println("3");
                                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                                try {
                                    ArrayList<String> L=(ArrayList<String>)ois.readObject();
                                    System.out.println("###Les moyennes :###");
                                    System.out.println("Température = "+L.get(0));
                                    System.out.println("Pression = "+L.get(1));
                                    System.out.println("Humisité = "+L.get(2));
                                } catch (ClassNotFoundException e) {
                                    throw new RuntimeException(e);
                                }

                                break;
                            }
                            case 4:{
                                pw.println("4");
                                int souschoix1=0;
                                do {
                                    System.out.println("1---Température---");
                                    System.out.println("2---Pression---");
                                    System.out.println("3---Humidité---");
                                    System.out.println("4---Retourne---");
                                    System.out.println("choix :");
                                    souschoix1=sc.nextInt();
                                    switch (souschoix1){
                                        case 1:{
                                            pw.println("4/1");
                                            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                                            try {
                                                ArrayList<String> L=(ArrayList<String>)ois.readObject();
                                                System.out.println("Température = "+L.get(0));
                                            } catch (ClassNotFoundException e) {
                                                throw new RuntimeException(e);
                                            }
                                            break;
                                        }
                                        case 2:{
                                            pw.println("4/2");
                                            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                                            try {
                                                ArrayList<String> L=(ArrayList<String>)ois.readObject();
                                                System.out.println("Pression = "+L.get(1));
                                            } catch (ClassNotFoundException e) {
                                                throw new RuntimeException(e);
                                            }
                                            break;
                                        }
                                        case 3:{
                                            pw.println("4/3");
                                            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                                            try {
                                                ArrayList<String> L=(ArrayList<String>)ois.readObject();
                                                System.out.println("Humidité = "+L.get(2));
                                            } catch (ClassNotFoundException e) {
                                                throw new RuntimeException(e);
                                            }
                                            break;
                                        }
                                        case 4:{
                                            pw.println("4/4");
                                            break;
                                        }
                                    }
                                }while(souschoix1!=4);

                                break;
                            }
                            case 5:{
                                pw.println("5");
                                System.out.println("Au revoir");
                                break;
                            }
                            default:{
                                System.out.println("choix invalide !, veuillez enter un nombre entre 1 et 2");
                            }

                        }

                    }while(choix!=5);
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
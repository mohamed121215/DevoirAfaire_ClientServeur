import java.sql.*;
import java.util.ArrayList;

public class Model {
    public Connection connexion() throws SQLException, ClassNotFoundException {
        Connection c=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3307/capteur";
            String user="root";
            String password="root";
            c = DriverManager.getConnection(url,user,password) ;

        }
        catch(SQLException e) {

                System.out.println("Erreur lors de la connexion ") ;

        }
        return c;
    }
    public void stockmesures(int numero,int T,int P,int H){
        try {
            Connection c=connexion();
            String query="INSERT INTO mesure(numero,T,P,H) VALUES(?,?,?,?)";
            PreparedStatement ps=c.prepareStatement(query);
            ps.setInt(1,numero);
            ps.setInt(2,T);
            ps.setInt(3,P);
            ps.setInt(4,H);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<ArrayList<String>> AffichageDesMesures(){
        try {
            Connection c=connexion();
            Statement st=c.createStatement();
            String query="SELECT * FROM mesure";
            ResultSet rs=st.executeQuery(query);
            ArrayList<ArrayList<String>> LG=new ArrayList<>();
            while(rs.next()){
                ArrayList<String> L=new ArrayList<>();
                String id=rs.getString(1);
                String numero=rs.getString(2);
                String dateandheure=rs.getString(3);
                String T=rs.getString(4);
                String P=rs.getString(5);
                String H=rs.getString(6);
                L.add(id);
                L.add(numero);
                L.add(dateandheure);
                L.add(T);
                L.add(P);
                L.add(H);
                LG.add(L);
            }
            return LG;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public ArrayList<String> calculermoyenne(){
        try {
            Connection c=connexion();
            Statement st=c.createStatement();
            String query="SELECT AVG(T),AVG(P),AVG(H) FROM mesure";
            ResultSet rs=st.executeQuery(query);
            ArrayList<String> L=new ArrayList<>();
            while(rs.next()){
                String moyT=rs.getString(1);
                String moyP=rs.getString(2);
                String moyH=rs.getString(3);
                L.add(moyT);
                L.add(moyP);
                L.add(moyH);
            }
            return L;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public int verifcompte(String login,String password,String role){
        try {
            Connection c=connexion();
            Statement s=c.createStatement();
            String query="SELECT * FROM user";
            ResultSet rs=s.executeQuery(query);
            while(rs.next()){
                String l=rs.getString(2);
                String p=rs.getString(3);
                String r=rs.getString(4);
                if(l.equals(login) && p.equals(password) && r.equals(role) && r.equals("capteur")){
                    return 0;
                }
                if(l.equals(login) && p.equals(password) && r.equals(role) && r.equals("admin")){
                    return 1;
                }
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

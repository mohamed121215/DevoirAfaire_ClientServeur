import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket ss=new ServerSocket(1234);
            while(true){
                Socket s=ss.accept();
                communication c=new communication(s);
                c.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
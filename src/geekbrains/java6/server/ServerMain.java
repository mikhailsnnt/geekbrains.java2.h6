package geekbrains.java6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMain {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket( 3071);
            Socket client = serverSocket.accept();
            System.out.println("Client connected");
            new Thread(()->{
                Scanner scanner = new Scanner(System.in);
                try{
                DataOutputStream out =  new DataOutputStream( client.getOutputStream());
                while(true){
                    out.writeUTF( scanner.nextLine());
                }
                }
                catch (IOException exception){
                    exception.printStackTrace();
                }

            }).start();
            DataInputStream in = new DataInputStream(client.getInputStream());
            while(true){
                System.out.println( "Message from client: "+in.readUTF());
            }
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }
}

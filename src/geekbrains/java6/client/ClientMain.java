package geekbrains.java6.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 3071);
            System.out.println("Successfully connected to server");
            new Thread(()-> {
                try{
                DataInputStream in = new DataInputStream(socket.getInputStream());
                while(true){
                    System.out.println("Message from server: " + in.readUTF());
                }
                }
                catch (IOException exception){
                    exception.printStackTrace();
                    System.exit(0);
                }
            }).start();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            while (true){
                out.writeUTF(scanner.nextLine());
            }
        }
        catch (IOException exception){
            exception.printStackTrace();
            System.exit(0);
        }
    }
}

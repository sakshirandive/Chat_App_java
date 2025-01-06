import java.io.*;
import java.net.*;

class Server {
    ServerSocket server;
    BufferedReader br;
    PrintWriter pw;
    Socket socket;

    public Server() {

        try {
            // ServerSocket is created. port number is 7777
            server = new ServerSocket(7777);
            System.out.println("server is created");
            // waiting for client to connect
            socket = server.accept();
            System.out.println("client is connected");
            // creating object for reading the message from person
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // creating object for sending the message to person
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            // reading message
            startReading();
            startWriting();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void startReading() {
        Runnable t1 = () -> {
            System.out.println("Reading Started");

            try {
                while (true) {
                    String message = br.readLine();
                    if (message.equalsIgnoreCase("exit")) {
                        System.out.println(message);
                        socket.close();
                        break;
                    }
                    System.out.println(message);
                }
            }

            catch (Exception e) {
                System.out.println(e);
            }
        };
        new Thread(t1).start();
    }

    public void startWriting() {
        Runnable t2 = () -> {
            System.out.println("started writing");

            try {
                while (true && !socket.isClosed()) {
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String message = br1.readLine();

                    pw.println(message);
                    pw.flush();

                    if (message.equalsIgnoreCase("exit")) {
                        socket.close();
                        break;
                    }

                }

            } catch (Exception e) {
                System.out.println("server closed");
            }
        };
        new Thread(t2).start();
    }

    public static void main(String[] args) {
        System.out.println("hello");
        new Server();

    }
}
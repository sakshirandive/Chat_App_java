import java.io.*;
import java.net.*;

class Client
{
    Socket socket;
    BufferedReader br;
    PrintWriter pw;
    public Client()
    {
        try
        {
            System.out.println("Connecting to server");
            System.out.println("connected...");
            socket = new Socket("192.168",7777);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            startReading();
            startWriting();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void startReading() {
        Runnable t1 = () -> {
            System.out.println("Reading Started");

            try {
            while (true) {
                    String message = br.readLine();
                    if(message.equalsIgnoreCase("exit"))
                    {
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
    
    public void startWriting(){
        Runnable t2=()->{
            System.out.println("started writing");

            try{
            while (true && !socket.isClosed()) {
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String message = br1.readLine();

                   
                    
                    pw.println(message);
                    pw.flush();

                    if(message.equalsIgnoreCase("exit"))
                    {
                        socket.close();
                        break;    
                    }

                    
                }
                
            }
            catch(Exception e)
                {
                    System.out.println("server closed");
                }
        };
        new Thread(t2).start();
    }

    public static void main(String[] args) 
    {
        new Client();
    }
    
}
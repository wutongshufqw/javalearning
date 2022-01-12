package com.MIDIMusicPlayer.v6;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class MusicServer {
    ArrayList<ObjectOutputStream> clientOutPutStreams;

    public static void main(String[] args) {
        new MusicServer().go();
    }

    public class ClientHandler implements Runnable {
        ObjectInputStream in;
        Socket clientSocket;

        public ClientHandler(Socket socket) {
            try {
                clientSocket = socket;
                in = new ObjectInputStream(clientSocket.getInputStream());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public void run() {
            Object o2;
            Object o1;
            try {
                while ((o1=in.readObject())!=null){
                    o2=in.readObject();
                    System.out.println("read two objects");
                    tellEveryone(o1,o2);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private void go() {
        clientOutPutStreams = new ArrayList<>();

        try {
            ServerSocket serverSock = new ServerSocket(4242);

            while (true) {
                Socket clientSocket = serverSock.accept();
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                clientOutPutStreams.add(out);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();

                System.out.println("got a connection");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void tellEveryone(Object one,Object two){
        Iterator it = clientOutPutStreams.iterator();
        while (it.hasNext()){
            try {
                ObjectOutputStream out = (ObjectOutputStream) it.next();
                out.writeObject(one);
                out.writeObject(two);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}

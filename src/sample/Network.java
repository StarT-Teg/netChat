package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Network {

    private static final String SERVER_ADDRESS = "localhost";
    public static final int SERVER_PORT = 8189;

    private final String host;
    private final int port;

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public Network (){
        this (SERVER_ADDRESS, SERVER_PORT);
    }

    public Network(String host, int port) {
        this.host = host;
        this.port = port;

    }

    public boolean connect (){

        try {
            socket = new Socket(host, port);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            return true;
        } catch (UnknownHostException e) {
            System.out.println("Хост не существует!");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.out.println("Соединение не было установлено!");
            e.printStackTrace();
            return false;
        }

    }

    public void disconnect () throws IOException {
        socket.close();
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void waitMessage(Controller controller) {

        Thread thread = new Thread(() -> {

            try {
                while (true){
                    String incomeMessage = "";
                    incomeMessage = dataInputStream.readUTF();

                    if (incomeMessage.length() != 0){
                        controller.getMessage(incomeMessage);
                        incomeMessage = "";
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        thread.setDaemon(true);
        thread.start();

    }
}

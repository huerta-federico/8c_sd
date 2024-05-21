import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPMulticastClient2 implements Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new UDPMulticastClient());
        t.start();
    }

    public void receiveUDPMessage(String ip, int port) throws IOException {
        byte[] buffer = new byte[1024];
        MulticastSocket socket = new MulticastSocket(4321);
        InetAddress group = InetAddress.getByName("230.0.0.0");
        socket.joinGroup(group);
        while (true) {
            System.out.println("Esperando mensajes multicast");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String msg = new String(packet.getData(),
                    packet.getOffset(), packet.getLength());
            System.out.println("Mensaje multicast UDP recibido: >>" + msg);
            if ("Final".equals(msg)) {
                System.out.println("No más mensajes. Cerrando conexión: >>" + msg);
                break;
            }

        }
        socket.leaveGroup(group);
        socket.close();

    }

    public void run() {
        try {
            receiveUDPMessage("230.0.0.0", 4321);
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }
}


// Importación de librerías
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class UDPMulticastClient implements Runnable {
    // Parámetros de conexión
    Integer port = 4321;
    String host = "230.0.0.0";
    // Interface de red alterna
    // String networkInterface = "Ethernet 2";
    String networkInterface = "Hamachi";

    public static void main(String[] args) {
        Thread t = new Thread(new UDPMulticastClient());
        t.start();
    }

    public void receiveUDPMessage(String ip, int port) throws IOException {
        // Creación del socket Multicast
        byte[] buffer = new byte[1024];
        MulticastSocket socket = new MulticastSocket(port);
        InetAddress address = InetAddress.getByName(host);
        InetSocketAddress group = new InetSocketAddress(address, port);
        NetworkInterface nif = NetworkInterface.getByName(networkInterface);

        // Unión al grupo multicast
        socket.joinGroup(group, nif);
        // sentencia original depreciada
        // socket.joinGroup(group);

        // Bucle de espera para recibir mensajes
        boolean isFinalMessage = false;

        while (!isFinalMessage) {
            System.out.println("Esperando mensajes multicast...");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String msg = new String(packet.getData(),
                    packet.getOffset(), packet.getLength());

            if (!"Final".equals(msg)) {
                System.out.println("Mensaje multicast UDP recibido >>" + msg);

            } else {
                System.out.println("No más mensajes. Cerrando conexión.");
                isFinalMessage = true;
            }
        }

        // Salida del group y cierre del socket
        socket.leaveGroup(group, nif);
        socket.close();
        // sentencia original depreciada
        // socket.leaveGroup(group);
    }

    public void run() {
        try {
            receiveUDPMessage(host, port);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPMulticastServer {
    public static void sendUDPMessage(String message, String ipAddress, int port) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress group = InetAddress.getByName(ipAddress);
        byte[] msg = message.getBytes();
        DatagramPacket packet = new DatagramPacket(msg, msg.length, group, port);
        socket.send(packet);
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        sendUDPMessage("Primer mensaje multicast", "230.0.0.0", 4321);
        sendUDPMessage("Segundo mensaje multicast", "230.0.0.0", 4321);
        sendUDPMessage("Tercer mensaje multicast", "230.0.0.0", 4321);
        sendUDPMessage("Final", "230.0.0.0", 4321);
    }
}

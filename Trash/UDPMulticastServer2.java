package Trash;
// Importación de librerías
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPMulticastServer2 {
    public static void sendUDPMessage(String message, String ipAddress, int port) throws IOException {
        // Creación del socket y paquete datagrama con el mensaje
        DatagramSocket socket = new DatagramSocket();
        InetAddress group = InetAddress.getByName(ipAddress);
        byte[] msg = message.getBytes();
        DatagramPacket packet = new DatagramPacket(msg, msg.length, group, port);

        // Envío del paquete a través del socket
        socket.send(packet);

        //Cierre del socket
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        // Parámetros de conexión
        Integer port = 4321;
        String host = "230.0.0.0";

        // Envío de cuatro paquetes/mensajes multicast
        sendUDPMessage("Primer mensaje multicast", host, port);
        sendUDPMessage("Segundo mensaje multicast", host, port);
        sendUDPMessage("Tercer mensaje multicast", host, port);
        sendUDPMessage("Final", host, port);
    }
}


// Importación de librerías
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPMulticastServer {
    public static void sendUDPMessage(String message, String ipAddress, int port) throws IOException {
        // Creación del socket y paquete datagrama con el mensaje
        DatagramSocket socket = new DatagramSocket();
        InetAddress group = InetAddress.getByName(ipAddress);
        byte[] msg = message.getBytes();
        DatagramPacket packet = new DatagramPacket(msg, msg.length, group, port);

        // Envío del paquete a través del socket
        socket.send(packet);

        // Cierre del socket
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        // Parámetros de conexión
        Integer port = 4321;
        String host = "230.0.0.0";

        // Envío de cuatro paquetes/mensajes multicast

        try (Scanner input = new Scanner(System.in)) {
            boolean Salir = false;

            // Menu de opciones
            while (!Salir) {
                System.out.println(
                        "Ingrese un mensaje para enviar multicast. Comandos especiales: \"Salir\", \"Prueba\", \"Desconectar\"");
                String option = input.nextLine();
                switch (option) {
                    case "Salir":
                        // Cierra el programa
                        Salir = true;
                        break;
                    case "Desconectar":
                        // Desconecta los clientes multicast y cierra el programa
                        sendUDPMessage("Final", host, port);
                        Salir = true;
                        break;
                    case "Prueba":
                        // Envía tres cadenas predefinidas mediante multicast
                        automaticSend(host, port);
                        break;
                    default:
                        // Envía la cadena ingresada por el usuario mediante multicast
                        String msg = option;
                        sendUDPMessage(msg, host, port);
                }
            }
        }
    }

    static void automaticSend(String host, Integer port) {
        try {
            sendUDPMessage("Primer mensaje multicast", host, port);
            sendUDPMessage("Segundo mensaje multicast", host, port);
            sendUDPMessage("Tercer mensaje multicast", host, port);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

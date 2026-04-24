
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
  static final int PORT = 7777;
  static final String HOST = "localhost";

  ServerSocket srvSocket = null;
  Socket clientSocket = null;

  
  public void connecta(){
    // Obre la connexió del servidor
    try {
      srvSocket = new ServerSocket(PORT);
    } catch (IOException e) {
      System.out.println("Error al obrir la connexió del servidor: " + e.getMessage());
    }

    //accepta la connexió del client
    try {
      clientSocket = srvSocket.accept();
    } catch (IOException e) {
      System.out.println("Error al acceptar la connexió del client: " + e.getMessage());
    }
  }

  // Métode on es rebren les dades del client
  public void repDades() throws IOException{
    BufferedReader text = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));   // Llegeix les dades del clientsocket
    text.close();                                                                                     // tanquem el buffereddreader
  }

  // Mètode per tancar la connexió del client
  public void tanca() {
    try {
      clientSocket.close();
    } catch (IOException e) {
      System.out.println("Error al tancar la connexió del client: " + e.getMessage());
    }

    // Tanquem la connexió del servidor
    try {
      srvSocket.close();
    } catch (IOException e) {
      System.out.println("Error al tancar la connexió del servidor: " + e.getMessage());
    }
  }
  public static void main(String[] args) {
    Servidor srv = new Servidor();        // Creem la instancia del servidor
    srv.connecta();                       // Connectem el servidor
    try {
      srv.repDades();                     // Rebem les dades del client
    } catch (IOException e) {
      System.out.println("Error al rebre les dades del client: " + e.getMessage());
    }
    srv.tanca();                          // Tanquem les connexions
  }
}

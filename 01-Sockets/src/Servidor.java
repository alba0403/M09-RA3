
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
      System.out.println("Servidor en marxa a " + HOST + ":" + PORT);
      System.out.println("Esperant connexions a " + HOST + ":" + PORT);
    } catch (IOException e) {
      System.out.println("Error al obrir la connexió del servidor: " + e.getMessage());
    }

    //accepta la connexió del client
    try {
      clientSocket = srvSocket.accept();
      System.out.println("Client connectat: " + clientSocket.getInetAddress());
    } catch (IOException e) {
      System.out.println("Error al acceptar la connexió del client: " + e.getMessage());
    }
  }

  // Métode on es rebren les dades del client
  public void repDades() throws IOException{
    BufferedReader text = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Llegeix les dades del clientsocket
    
    String linia;
    while((linia = text.readLine()) != null) { //llegeix fins que el client tanca
      System.out.println("Rebut: " + linia);
    }

    text.close();                                                                                   // tanquem el buffereddreader
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
      System.out.println("Servidor tancat.");
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

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  // PORT i HOST del servidor
  static final int PORT = 7777;
  static final String HOST = "localhost";

  Socket socket = null;
  PrintWriter out = null;
  
  public void connecta(){       // obrim el socket al servidor
    try {
      socket = new Socket(HOST, PORT);
      out = new PrintWriter(socket.getOutputStream(), true);
      System.out.println("Connectat a servidor en " + HOST + ":" + PORT);
    } catch (Exception e) {
      System.out.println("Error al obrir el socket al servidor: " + e.getMessage());
    }
  }

  public void tanca(){      //tanquem el socket
    try {
      socket.close();
    } catch (Exception e) {
      System.out.println("Error al tancar el socket: " + e.getMessage());
    }
  }

  public void envia(String missatge){
    if (out != null) {                  // Enviem el missatge al servidor
      out.println(missatge);
      System.out.println("Enviat al servidor: " + missatge);
    }
  }


  public static void main(String[] args) {
    Client client = new Client();

    client.connecta();
    client.envia("Prova d'enviament 1");
    client.envia("Prova d'enviament 2");
    client.envia("Adéu!");

    Scanner sc = new Scanner(System.in);

    System.out.println("Prem Enter per tancar el client...");
    sc.nextLine();

    client.tanca();
    System.out.println("Client tancat");

    sc.close();
  }
}

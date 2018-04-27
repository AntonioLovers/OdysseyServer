
package Server;

import java.io.*;
import java.net.*;

/**
 *
 * @author curso
 */
public class Server {
    
    private final int PUERTO;
    private ServerSocket serversocket;
    private Socket socket;
    private DataOutputStream output;
    private String message;
    
    public Server(){
        this.PUERTO = 5000;
        
    }
    

//SERVIDOR

 

public void initServer(){

    BufferedReader input;
    
    try{
        serversocket = new ServerSocket(PUERTO);/* crea socket servidor que escuchara en puerto 5000*/
        socket=new Socket();
        System.out.println("Esperando una conexión:");
        socket = serversocket.accept();

//Inicia el socket, ahora esta esperando una conexión por parte del cliente
        System.out.println("Un cliente se ha conectado.");
//Canales de entrada y salida de datos
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new DataOutputStream(socket.getOutputStream());
        System.out.println("Confirmando conexion al cliente....");
        output.writeUTF("Conexión exitosa...n envia un mensaje :D");
//Recepcion de mensaje
        message = input.readLine();
        System.out.println(message);
        output.writeUTF("Se recibio tu mensaje.n Terminando conexion...");
        output.writeUTF("Gracias por conectarte, adios!");
        System.out.println("Cerrando conexión...");
        serversocket.close();//Aqui se cierra la conexión con el cliente

    }catch(IOException e ){
        System.out.println("Error: "+e.getMessage());
        }
    }
}

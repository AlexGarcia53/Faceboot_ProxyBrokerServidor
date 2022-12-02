/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.proxybrokerservidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase que representa al servidor, el cual se ejecuta y comienza a escuchar por peticiones, 
 * para el manejo de estas añade estas conexiones a un ScoketServidor y lo ejecuta como un hilo.
 * @author Equipo Broker.
 */
public class Servidor {
    /**
     * Atributo del tipo socket servidor.
     */
    private ServerSocket serverSocket;
    /**
     * Método constructor de la clase que inicializa el atributo de esta mediante el parámetro dado.
     * @param serverSocket Socket de tipo servidor.
     */
    public Servidor(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    /**
     * Método utilizado para que el servidor comience a escuchar peticiones.
     */
    public void iniciarServidor(){
        try{
            while(!serverSocket.isClosed()){
                Socket socket= serverSocket.accept();
                System.out.println("Una solicitud ha llegado");
                SocketServidor socketServidor= new SocketServidor(socket);
                
                Thread thread=new Thread(socketServidor);
                thread.start();
            }
        }catch (IOException e){
            cerrarServerSocket();
        }
    }
    /**
     * Método utilizado para cerrar el socket del servidor.
     */
    public void cerrarServerSocket(){
        try{
            if(serverSocket!=null){
                serverSocket.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Método main de la clase que crea un socket de tipo servidor, crea una instancia de la clase 
     * y hace que comience a escuchar por peticiones.
     * @param args Línea de argumentos.
     * @throws IOException Excepción que puede ser lanzada a lo largo de la ejecución.
     */
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket= new ServerSocket(5001);
        Servidor servidor=new Servidor(serverSocket);
        servidor.iniciarServidor();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.proxybrokerservidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Admin
 */
public class Servidor {
    private ServerSocket serverSocket;

    public Servidor(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    
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
    
    public void cerrarServerSocket(){
        try{
            if(serverSocket!=null){
                serverSocket.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket= new ServerSocket(5001);
        Servidor servidor=new Servidor(serverSocket);
        servidor.iniciarServidor();
    }
}

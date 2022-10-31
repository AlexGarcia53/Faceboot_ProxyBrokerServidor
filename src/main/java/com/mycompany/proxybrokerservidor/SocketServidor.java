/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proxybrokerservidor;

import dominio.Solicitud;
import dominio.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class SocketServidor implements Runnable {
    private SocketServidor socketServidor;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private ProxyServidor proxyServidor;
    
    public SocketServidor(Socket socket){
        try{
            this.socket= socket;
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.socketServidor=this;
            this.proxyServidor= new ProxyServidor();
        } catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    
    @Override
    public void run() {
        String solicitud; Solicitud respuesta;
        
        while(socket.isConnected()){
            try{
                System.out.println("xd");
                solicitud= bufferedReader.readLine();
                System.out.println(solicitud.toString());
                String[] arregloSolicitud= solicitud.split(",");
                Solicitud objetoSolicitud= new Solicitud(arregloSolicitud[0], arregloSolicitud[1]);
                objetoSolicitud.setRespuesta(arregloSolicitud[2]);
                Usuario usuario= proxyServidor.deserealizarSolicitudRegistrarUsuario(objetoSolicitud);
                respuesta= proxyServidor.realizarSolicitudRegistrarUsuario(objetoSolicitud, usuario);
                System.out.println(respuesta);
                retransmitirRespuesta(respuesta.toString());
            }catch(IOException e){
                e.printStackTrace();
//                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }
    
    public void retransmitirRespuesta(String respuesta){
        try{
            socketServidor.bufferedWriter.write(respuesta);
            socketServidor.bufferedWriter.newLine();
            socketServidor.bufferedWriter.flush();
        }catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try{
            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

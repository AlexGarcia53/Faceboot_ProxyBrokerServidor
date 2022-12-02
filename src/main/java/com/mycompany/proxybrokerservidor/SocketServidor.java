/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proxybrokerservidor;

import com.mycompany.logicafaceboot.FabricaLogica;
import dominio.Operacion;
import dominio.Publicacion;
import dominio.Solicitud;
import dominio.Usuario;
import excepciones.ErrorBusquedaUsuarioException;
import excepciones.ErrorGuardarUsuarioException;
import interfaces.IEstrategia;
import interfaces.ILogica;
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
import strategy.ContextoCanalizacion;
import strategy.EstrategiaCrearPublicacion;
import strategy.EstrategiaIniciarSesion;
import strategy.EstrategiaRegistrarUsuario;

/**
 * Clase que representa a las peticiones que llegan al servidor y contiene los métodos necesarios 
 * para que pueda etender las solicitudes y enviar respuestas.
 * @author Equipo Broker.
 */
public class SocketServidor implements Runnable {
    /**
     * Atributo que contiene el socket de la conexión.
     */
    private Socket socket;
    /**
     * Atributo con el buffered reader de la conexión para leer datos.
     */
    private BufferedReader bufferedReader;
    /**
     * Atributo con el buffered writer de la conexión para escribir datos.
     */
    private BufferedWriter bufferedWriter;
    /**
     * Método constructor de la clase que inicializa los atributos.
     * @param socket Socket con la conexión del cliente.
     */
    public SocketServidor(Socket socket){
        try{
            this.socket= socket;
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e){
            cerrarTodo(this.socket, bufferedReader, bufferedWriter);
        }
    }
    /**
     * Método utilizado para escuchar las peticiones del broker y canalizarlas para así poder dar una respuesta, 
     * todo esto mediante un hilo.
     */
    @Override
    public void run() {
        String solicitud, respuestaSerializada; Solicitud respuesta;
        
        while(socket.isConnected()){
            try{
                solicitud = bufferedReader.readLine();
                System.out.println(solicitud);
                respuesta = ContextoCanalizacion.getInstancia().canalizarSolicitud(solicitud);
                respuestaSerializada = Deserealizador.getInstancia().serializarRespuesta(respuesta);
                System.out.println(respuestaSerializada);
                retransmitirRespuesta(respuestaSerializada);
            }catch(IOException e){
                cerrarTodo(socket, bufferedReader, bufferedWriter);
                break;
            }catch(NullPointerException e){
                break;
            }
        }
    }
    /**
     * Método utilizado para enviar una respuesta al cliente para la solicitud que realizó.
     * @param respuesta Respuesta que se enviará al cliente.
     */
    public void retransmitirRespuesta(String respuesta){
        try{
            this.bufferedWriter.write(respuesta);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        }catch(IOException e){
            cerrarTodo(socket, bufferedReader, bufferedWriter);
        }
    }
    /**
     * Método utilizado para cerrar la conexión.
     * @param socket Socket de la conexión.
     * @param bufferedReader Buffered Reader de la conexión.
     * @param bufferedWriter Buffered Writer de la conexión.
     */
    public void cerrarTodo(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
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

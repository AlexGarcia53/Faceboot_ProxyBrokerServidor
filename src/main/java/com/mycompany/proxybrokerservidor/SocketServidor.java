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

/**
 *
 * @author Admin
 */
public class SocketServidor implements Runnable {
    private ILogica logica;
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
            this.logica= FabricaLogica.crearLogica();
        } catch (IOException e){
            cerrarTodo(socket, bufferedReader, bufferedWriter);
        }
    }
    
    @Override
    public void run() {
        String solicitud, respuestaSerializada; Solicitud respuesta;
        
        while(socket.isConnected()){
            try{
                solicitud= bufferedReader.readLine();
                System.out.println(solicitud);
                respuesta= this.canalizarSolicitud(solicitud);
//                String[] arregloSolicitud= solicitud.split(",");
//                Solicitud objetoSolicitud= new Solicitud(arregloSolicitud[0], arregloSolicitud[1]);
//                objetoSolicitud.setRespuesta(arregloSolicitud[2]);
//                Usuario usuario= proxyServidor.deserealizarSolicitudRegistrarUsuario(objetoSolicitud);
//                respuesta= proxyServidor.realizarSolicitudRegistrarUsuario(objetoSolicitud, usuario);
                respuestaSerializada= proxyServidor.serializarRespuesta(respuesta);
                System.out.println(respuestaSerializada);
                retransmitirRespuesta(respuestaSerializada);
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
            cerrarTodo(socket, bufferedReader, bufferedWriter);
        }
    }
    
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
    
    public Solicitud canalizarSolicitud(String solicitud){
        Solicitud objetoSolicitud = proxyServidor.deserealizarSolicitud(solicitud);
        Operacion tipoOperacion= objetoSolicitud.getOperacion();
        switch (tipoOperacion) {
            case registrar_usuario:{
                Usuario usuario = proxyServidor.deserealizarUsuario(objetoSolicitud.getSolicitud());
                return this.realizarSolicitudRegistrarUsuario(objetoSolicitud, usuario);
            }
            case iniciar_sesion:{
                Usuario usuario = proxyServidor.deserealizarUsuario(objetoSolicitud.getSolicitud());
                return this.realizarInicioSesion(objetoSolicitud, usuario);
            }
            case registrar_publicacion:{
                Publicacion publicacion= proxyServidor.deserealizarPublicacion(objetoSolicitud.getSolicitud());
                return this.realizarPublicacion(objetoSolicitud, publicacion);
            }
            default:
                return null;
        }
    }
    
    public Solicitud realizarSolicitudRegistrarUsuario(Solicitud solicitud, Usuario usuario){
        try{
            Usuario usuarioRegistrado= logica.registrarUsuario(usuario);
            solicitud.setRespuesta(proxyServidor.serializarUsuario(usuarioRegistrado));
        } catch(ErrorGuardarUsuarioException e){
            System.out.println("si entro al catch");
            solicitud.setRespuesta("Excepción: "+e.getMessage());
        }
        return solicitud;
    }
    
    public Solicitud realizarInicioSesion(Solicitud solicitud, Usuario usuario) {
        try {
            Usuario usuarioRegistrado = logica.consultarUsuario(usuario);
            solicitud.setRespuesta(proxyServidor.serializarUsuario(usuarioRegistrado));
        } catch (ErrorBusquedaUsuarioException e) {
            solicitud.setRespuesta("Excepción: " + e.getMessage());
        }
        return solicitud;
    }

    public Solicitud realizarPublicacion(Solicitud solicitud, Publicacion publicacion) {
        try {
            //Publicacion publicacionRegistrada = logica.registrarPublicacion(publicacion);
            solicitud.setRespuesta("Se llevó a cabo el registro");
        } catch (ErrorBusquedaUsuarioException e) {
            solicitud.setRespuesta("Excepción: " + e.getMessage());
        }
        return solicitud;
    }
}

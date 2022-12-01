/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proxybrokerservidor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.logicafaceboot.FabricaLogica;
import dominio.Comentario;
import dominio.Mensaje;
import dominio.Publicacion;
import dominio.Solicitud;
import dominio.Usuario;
import interfaces.ILogica;
import java.util.List;


/**
 *
 * @author Admin
 */
public class ProxyServidor {
    
    private static ProxyServidor proxyServidor;
    
    private ProxyServidor(){
        
    }
    
    public static ProxyServidor getInstancia(){
        if(ProxyServidor.proxyServidor==null){
            ProxyServidor.proxyServidor= new ProxyServidor();
        }
        return ProxyServidor.proxyServidor;
    }
    
    public String serializarUsuario(Usuario usuario){
        try{
            ObjectMapper mapper=new ObjectMapper();
            String solicitudSerializada= mapper.writeValueAsString(usuario);
            return solicitudSerializada;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public Solicitud deserealizarSolicitud(String solicitud){
        try{
            ObjectMapper conversion= new ObjectMapper();
            return conversion.readValue(solicitud, Solicitud.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public String serializarRespuesta(Solicitud respuesta){
        try{
            ObjectMapper mapper=new ObjectMapper();
            String solicitudSerializada= mapper.writeValueAsString(respuesta);
            return solicitudSerializada;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public Usuario deserealizarUsuario(String usuario){
        try{
            ObjectMapper conversion= new ObjectMapper();
            return conversion.readValue(usuario, Usuario.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public String serializarPublicacion(Publicacion publicacion) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String solicitudSerializada = mapper.writeValueAsString(publicacion);
            return solicitudSerializada;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Publicacion deserealizarPublicacion(String publicacion){
        try{
            ObjectMapper conversion= new ObjectMapper();
            return conversion.readValue(publicacion, Publicacion.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public String serializarLista(List lista){
        try{
            ObjectMapper mapper= new ObjectMapper();
            String solicitudSerializada= mapper.writeValueAsString(lista);
            return solicitudSerializada;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public String serializarComentario(Comentario comentario) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String solicitudSerializada = mapper.writeValueAsString(comentario);
            return solicitudSerializada;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Comentario deserealizarComentario(String comentario){
        try{
            ObjectMapper conversion= new ObjectMapper();
            return conversion.readValue(comentario, Comentario.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public String serializarMensaje(Mensaje mensaje){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String solicitudSerializada = mapper.writeValueAsString(mensaje);
            return solicitudSerializada;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Mensaje deserealizarMensaje(String mensaje){
        try{
            ObjectMapper conversion= new ObjectMapper();
            return conversion.readValue(mensaje, Mensaje.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}

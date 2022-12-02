/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proxybrokerservidor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.logicafaceboot.FabricaLogica;
import dominio.Comentario;
import dominio.Hashtag;
import dominio.Mensaje;
import dominio.Publicacion;
import dominio.Solicitud;
import dominio.Usuario;
import interfaces.ILogica;
import java.util.List;


/**
 * Clase encargada de serializar y deserializar objetos.
 * @author Equipo Broker.
 */
public class Deserealizador {
    /**
     * Atributo con la instancia estática de la clase.
     */
    private static Deserealizador deserealizador;
    /**
     * Método constructor de la clase.
     */
    private Deserealizador(){
        
    }
    /**
     * Método utilizado para obtener la instancia de la clase, en caso de que no este inicializada
     * la inicializa.
     * @return Instancia de la clase.
     */
    public static Deserealizador getInstancia(){
        if(Deserealizador.deserealizador==null){
            Deserealizador.deserealizador= new Deserealizador();
        }
        return Deserealizador.deserealizador;
    }
    /**
     * Método utilizado para serializar un objeto del tipo usuario a formato JSON.
     * @param usuario Usuario a serializar.
     * @return String con el usuario serializado.
     */
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
    /**
     * Método utilizado para deserializar un String en formato JSON a un objeto del tipo Solicitud.
     * @param solicitud String de la solicitud.
     * @return Objeto solicitud deserializado.
     */
    public Solicitud deserealizarSolicitud(String solicitud){
        try{
            ObjectMapper conversion= new ObjectMapper();
            return conversion.readValue(solicitud, Solicitud.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    /**
     * Método utilizado para serializar un objeto del tipo solicitud a formato JSON.
     * @param respuesta Solicitud a serializar.
     * @return String con la solicitud serializada.
     */ 
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
    /**
     * Método utilizado para deserializar un String en formato JSON a un objeto del tipo Usuario.
     * @param usuario String del usuario.
     * @return Objeto usuario deserializado.
     */
    public Usuario deserealizarUsuario(String usuario){
        try{
            ObjectMapper conversion= new ObjectMapper();
            return conversion.readValue(usuario, Usuario.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    /**
     * Método utilizado para serializar un objeto del tipo publicación a formato JSON.
     * @param publicacion Publicación a serializar.
     * @return String con la publicación serializada.
     */
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
    /**
     * Método utilizado para deserializar un String en formato JSON a un objeto del tipo Publicación.
     * @param publicacion String de la publicación.
     * @return Objeto publicación deserializado.
     */
    public Publicacion deserealizarPublicacion(String publicacion){
        try{
            ObjectMapper conversion= new ObjectMapper();
            return conversion.readValue(publicacion, Publicacion.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    /**
     * Método utilizado para serializar una lista a formato JSON.
     * @param lista Lista a serializar.
     * @return String con la lista serializada.
     */
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
    /**
     * Método utilizado para serializar un objeto del tipo comentario a formato JSON.
     * @param comentario Comentario a serializar.
     * @return String con el comentario serializado.
     */
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
    /**
     * Método utilizado para deserializar un String en formato JSON a un objeto del tipo Comentario.
     * @param comentario String del comentario.
     * @return Objeto comentario deserializado.
     */
    public Comentario deserealizarComentario(String comentario){
        try{
            ObjectMapper conversion= new ObjectMapper();
            return conversion.readValue(comentario, Comentario.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    /**
     * Método utilizado para serializar un objeto del tipo mensaje a formato JSON.
     * @param mensaje Mensaje a serializar.
     * @return String con el mensaje serializado.
     */
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
    /**
     * Método utilizado para deserializar un String en formato JSON a un objeto del tipo mensaje.
     * @param mensaje String del mensaje.
     * @return Objeto mensaje deserializado.
     */
    public Mensaje deserealizarMensaje(String mensaje){
        try{
            ObjectMapper conversion= new ObjectMapper();
            return conversion.readValue(mensaje, Mensaje.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    /**
     * Método utilizado para serializar un objeto del tipo hashtag a formato JSON.
     * @param hashtag Hashtag a serializar.
     * @return String con el hashtag serializado.
     */
    public String serializarHashtag(Hashtag hashtag){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String solicitudSerializada = mapper.writeValueAsString(hashtag);
            return solicitudSerializada;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Método utilizado para deserializar un String en formato JSON a un objeto del tipo hashtag.
     * @param hashtag String del hashtag.
     * @return Objeto hashtag deserealizado.
     */
    public Hashtag deserealizarHashtag(String hashtag){
        try{
            ObjectMapper conversion= new ObjectMapper();
            return conversion.readValue(hashtag, Hashtag.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}

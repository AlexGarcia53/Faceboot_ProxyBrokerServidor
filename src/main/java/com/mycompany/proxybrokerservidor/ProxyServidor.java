/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proxybrokerservidor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.logicafaceboot.FabricaLogica;
import dominio.Solicitud;
import dominio.Usuario;
import interfaces.ILogica;


/**
 *
 * @author Admin
 */
public class ProxyServidor {
    private ILogica logica;
    
    public ProxyServidor(){
        this.logica= FabricaLogica.crearLogica();
    }
    
    public Solicitud realizarSolicitudRegistrarUsuario(Solicitud solicitud, Usuario usuario){
        try{
            logica.registrarUsuario(usuario);
            solicitud.setRespuesta("Se ha registrado correctamente al usuario");
        } catch(Exception e){
            solicitud.setRespuesta(e.getMessage());
        }
        return solicitud;
    }
    
    public String serializarRespuesta(String respuesta){
        throw new UnsupportedOperationException();
    }
    
    public Usuario deserealizarSolicitudRegistrarUsuario(Solicitud solicitud){
        try{
            ObjectMapper conversion= new ObjectMapper();
            return conversion.readValue(solicitud.getSolicitud(), Usuario.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}

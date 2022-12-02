/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.proxybrokerservidor.Deserealizador;
import dominio.Operacion;
import dominio.Solicitud;
import interfaces.IEstrategia;

/**
 * Clase que se encarga de canalizar las solicitudes que llegan al servidor..
 * @author Equipo Broker.
 */
public class ContextoCanalizacion {
    /**
     * Atributo con la estrategia a llevar a cabo para atender la solicitud.
     */
    private IEstrategia estrategia;
    /**
     * Atributo que contiene la instancia estática de la clase.
     */
    private static ContextoCanalizacion contextoCanalizacion;
    /**
     * Método constructor de la clase.
     */
    private ContextoCanalizacion(){
        
    }
    /**
     * Método utilizado para obtener la isntancia de la clase.
     * @return Instancia de la clase.
     */
    public static ContextoCanalizacion getInstancia(){
        if(contextoCanalizacion==null){
            contextoCanalizacion= new ContextoCanalizacion();
        }
        return contextoCanalizacion;
    }
    /**
     * Método utilizado para canalizar la solicitud, dependiendo de la solicitud instancia la estrategia y la ejecuta 
     * para así poder dar respuesta a la solicitud.
     * @param solicitud Solicitud entrante.
     * @return Respuesta a la solicitud.
     */
    public Solicitud canalizarSolicitud(String solicitud){
        Solicitud objetoSolicitud = Deserealizador.getInstancia().deserealizarSolicitud(solicitud);
        Operacion tipoOperacion = objetoSolicitud.getOperacion();
        switch (tipoOperacion) {
            case registrar_usuario: {
                contextoCanalizacion.setEstrategia(new EstrategiaRegistrarUsuario());
                break;
            }
            case iniciar_sesion: {
                contextoCanalizacion.setEstrategia(new EstrategiaIniciarSesion());
                break;
            }
            case iniciar_sesion_facebook: {
                contextoCanalizacion.setEstrategia(new EstrategiaIniciarSesionFacebook());
                break;
            }
            case registrar_publicacion: {
                contextoCanalizacion.setEstrategia(new EstrategiaCrearPublicacion());
                break;
            }
            case consultar_publicaciones: {
                contextoCanalizacion.setEstrategia(new EstrategiaConsultarPublicaciones());
                break;
            }
            case editar_publicacion: {
                contextoCanalizacion.setEstrategia(new EstrategiaEditarPublicacion());
                break;
            }
            case eliminar_publicacion: {
                contextoCanalizacion.setEstrategia(new EstrategiaEliminarPublicacion());
                break;
            }
            case registrar_comentario: {
                contextoCanalizacion.setEstrategia(new EstrategiaRegistrarComentario());
                break;
            }
            case editar_comentario: {
                contextoCanalizacion.setEstrategia(new EstrategiaEditarComentario());
                break;
            }
            case eliminar_comentario: {
                contextoCanalizacion.setEstrategia(new EstrategiaEliminarComentario());
                break;
            }
            case editar_perfil: {
                contextoCanalizacion.setEstrategia(new EstrategiaEditarUsuario());
                break;
            }
            case consultar_usuarioNombre: {
                contextoCanalizacion.setEstrategia(new EstrategiaConsultarUsuarioNombre());
                break;
            }
            case registrar_mensaje: {
                contextoCanalizacion.setEstrategia(new EstrategiaEnviarMensaje());
                break;
            }
            case consultar_publicacionesHashtag:{
                contextoCanalizacion.setEstrategia(new EstrategiaConsultarPublicacionesHashtag());
                break;
            }
        }
        return contextoCanalizacion.ejecutarEstrategia(objetoSolicitud);
    }
    /**
     * Método utilizado para establecer la estrategia para resolver la solicitud.
     * @param estrategia Estrategia para resolver la solicitud.
     */
    public void setEstrategia(IEstrategia estrategia){
        this.estrategia= estrategia;
    }
    /**
     * Método utilizado para ejecutar la estrategia y resolver la solicitud.
     * @param solicitud Solicitud a resolver.
     * @return Respuesta a la solicitud.
     */
    public Solicitud ejecutarEstrategia(Solicitud solicitud){
        return this.estrategia.realizarSolicitud(solicitud);
    }
}

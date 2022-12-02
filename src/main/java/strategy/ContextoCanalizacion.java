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
 *
 * @author Admin
 */
public class ContextoCanalizacion {
    private IEstrategia estrategia;
    private static ContextoCanalizacion contextoCanalizacion;
    
    private ContextoCanalizacion(){
        
    }
    
    public static ContextoCanalizacion getInstancia(){
        if(contextoCanalizacion==null){
            contextoCanalizacion= new ContextoCanalizacion();
        }
        return contextoCanalizacion;
    }
    
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
    
    public void setEstrategia(IEstrategia estrategia){
        this.estrategia= estrategia;
    }
    
    public Solicitud ejecutarEstrategia(Solicitud solicitud){
        return this.estrategia.realizarSolicitud(solicitud);
    }
}

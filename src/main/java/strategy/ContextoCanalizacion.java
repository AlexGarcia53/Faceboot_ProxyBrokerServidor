/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.proxybrokerservidor.ProxyServidor;
import dominio.Operacion;
import dominio.Solicitud;
import interfaces.IEstrategia;
import javax.swing.JOptionPane;

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
        Solicitud objetoSolicitud = ProxyServidor.getInstancia().deserealizarSolicitud(solicitud);
        Operacion tipoOperacion= objetoSolicitud.getOperacion();
        switch (tipoOperacion) {
            case registrar_usuario:{
                contextoCanalizacion.setEstrategia(new EstrategiaRegistrarUsuario());
                break;
            }
            case iniciar_sesion:{
                contextoCanalizacion.setEstrategia(new EstrategiaIniciarSesion());
                break;
            }
            case iniciar_sesion_facebook:{
                contextoCanalizacion.setEstrategia(new EstrategiaIniciarSesionFacebook());
                break;
            }
            case registrar_publicacion:{
                contextoCanalizacion.setEstrategia(new EstrategiaCrearPublicacion());
                break;
            }
             case enviar_notificación:{
                 //vamos bien
                 System.out.println("Antes de entrar");
                contextoCanalizacion.setEstrategia(new EstrategiaNotificar());
                break;
            }
            default:{
                contextoCanalizacion.setEstrategia(null);
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

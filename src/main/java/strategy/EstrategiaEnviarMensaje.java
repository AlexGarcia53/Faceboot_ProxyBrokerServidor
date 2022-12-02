/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.proxybrokerservidor.Deserealizador;
import dominio.Mensaje;
import dominio.Solicitud;
import excepciones.ErrorEnviarMensajeException;
import interfaces.IEstrategia;
import notificacionesDecorator.FNotificaciones;
import notificacionesDecorator.INotificaciones;

/**
 *
 * @author Admin
 */
public class EstrategiaEnviarMensaje implements IEstrategia{
    private INotificaciones notificacion;
    
    public EstrategiaEnviarMensaje(){
        notificacion= new FNotificaciones();
    }
    
    @Override
    public Solicitud realizarSolicitud(Solicitud solicitud) {
        try{
            Mensaje respuesta= this.notificacion.enviarNotificacion(Deserealizador.getInstancia().deserealizarMensaje(solicitud.getSolicitud()));
            solicitud.setRespuesta(Deserealizador.getInstancia().serializarMensaje(respuesta));
        } catch(ErrorEnviarMensajeException e){
            solicitud.setRespuesta("Excepción: "+e.getMessage());
        }
        return solicitud;
    }
    
}

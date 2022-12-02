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
import notificacionesDecorator.FabricaNotificaciones;
import notificacionesDecorator.INotificaciones;

/**
 * Clase que representa la estrategia para resolver una solicitud de enviar mensaje.
 * @author Equipo Broker.
 */
public class EstrategiaEnviarMensaje implements IEstrategia{
    /**
     * Atributo del tipo lógica.
     */
    private INotificaciones notificacion;
    /**
     * Método constructor de la clase.
     */
    public EstrategiaEnviarMensaje(){
        notificacion= FabricaNotificaciones.getInstancia().obtenerNotificaciones();
    }
    /**
     * Método utilizado para atender una solicitud la cual recibe como parámetro, hace uso de 
     * las funcionalidades del servidor para atender y responder a ella.
     * @param solicitud Solicitud a atender.
     * @return Respuesta a la solicitud.
     */
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

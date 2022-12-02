/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.logicafaceboot.FabricaLogica;
import com.mycompany.proxybrokerservidor.Deserealizador;
import dominio.Comentario;
import dominio.Solicitud;
import excepciones.ErrorEliminarComentarioException;
import interfaces.IEstrategia;
import interfaces.ILogica;

/**
 * Clase que representa la estrategia para resolver una solicitud de eliminar comentario.
 * @author Equipo Broker.
 */
public class EstrategiaEliminarComentario implements IEstrategia{
    /**
     * Atributo del tipo lógica.
     */
    private ILogica logica;
    /**
     * Método constructor de la clase.
     */
    public EstrategiaEliminarComentario(){
        this.logica= FabricaLogica.crearLogica();
    }
    /**
     * Método utilizado para atender una solicitud la cual recibe como parámetro, hace uso de 
     * las funcionalidades del servidor para atender y responder a ella.
     * @param solicitud Solicitud a atender.
     * @return Respuesta a la solicitud.
     */
    @Override
    public Solicitud realizarSolicitud(Solicitud solicitud) {
        Comentario comentario= Deserealizador.getInstancia().deserealizarComentario(solicitud.getSolicitud());
        try {
            Comentario respuesta= this.logica.eliminarComentario(comentario);
            solicitud.setRespuesta(Deserealizador.getInstancia().serializarComentario(respuesta));
        } catch (ErrorEliminarComentarioException e) {
            solicitud.setRespuesta("Excepción: " + e.getMessage());
        }
        return solicitud;
    }
}

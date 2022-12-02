/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.logicafaceboot.FabricaLogica;
import com.mycompany.proxybrokerservidor.Deserealizador;
import dominio.Publicacion;
import dominio.Solicitud;
import excepciones.ErrorBusquedaPublicacionesException;
import interfaces.IEstrategia;
import interfaces.ILogica;
import java.util.List;

/**
 * Clase que representa la estrategia para resolver una solicitud de consultar publicaciones.
 * @author Equipo Broker.
 */
public class EstrategiaConsultarPublicaciones implements IEstrategia{
    /**
     * Atributo del tipo lógica.
     */
    private ILogica logica;
    /**
     * Método constructor de la clase.
     */
    public EstrategiaConsultarPublicaciones(){
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
        try {
            List<Publicacion> respuesta= this.logica.consultarPublicaciones();
            solicitud.setRespuesta(Deserealizador.getInstancia().serializarLista(respuesta));
        } catch (ErrorBusquedaPublicacionesException e) {
            solicitud.setRespuesta("Excepción: " + e.getMessage());
        }
        return solicitud;
    }
    
}

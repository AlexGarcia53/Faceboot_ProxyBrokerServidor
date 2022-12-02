/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.logicafaceboot.FabricaLogica;
import com.mycompany.proxybrokerservidor.Deserealizador;
import dominio.Publicacion;
import dominio.Solicitud;
import excepciones.ErrorGuardarPublicacionException;
import interfaces.IEstrategia;
import interfaces.ILogica;

/**
 * Clase que representa la estrategia para resolver una solicitud de editar publicación.
 * @author Equipo Broker.
 */
public class EstrategiaEditarPublicacion implements IEstrategia {
    /**
     * Atributo del tipo lógica.
     */
    private ILogica logica;
    /**
     * Método constructor de la clase.
     */
    public EstrategiaEditarPublicacion(){
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
        Publicacion publicacion= Deserealizador.getInstancia().deserealizarPublicacion(solicitud.getSolicitud());
        try {
            Publicacion respuesta= this.logica.editarPublicacion(publicacion);
            solicitud.setRespuesta(Deserealizador.getInstancia().serializarPublicacion(respuesta));
        } catch (ErrorGuardarPublicacionException e) {
            solicitud.setRespuesta("Excepción: " + e.getMessage());
        }
        return solicitud;
    }
}

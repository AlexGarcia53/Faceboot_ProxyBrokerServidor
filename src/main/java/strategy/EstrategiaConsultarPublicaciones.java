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
 *
 * @author Admin
 */
public class EstrategiaConsultarPublicaciones implements IEstrategia{
    private ILogica logica;
    
    public EstrategiaConsultarPublicaciones(){
        this.logica= FabricaLogica.crearLogica();
    }

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

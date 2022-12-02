/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.logicafaceboot.FabricaLogica;
import com.mycompany.proxybrokerservidor.Deserealizador;
import dominio.Publicacion;
import dominio.Solicitud;
import excepciones.ErrorEliminarPublicacionException;
import interfaces.IEstrategia;
import interfaces.ILogica;

/**
 *
 * @author Admin
 */
public class EstrategiaEliminarPublicacion implements IEstrategia{
    private ILogica logica;
    
    public EstrategiaEliminarPublicacion(){
        this.logica= FabricaLogica.crearLogica();
    }
    
    @Override
    public Solicitud realizarSolicitud(Solicitud solicitud) {
        Publicacion publicacion= Deserealizador.getInstancia().deserealizarPublicacion(solicitud.getSolicitud());
        try {
            Publicacion respuesta= this.logica.eliminarPublicacion(publicacion);
            solicitud.setRespuesta(Deserealizador.getInstancia().serializarPublicacion(respuesta));
        } catch (ErrorEliminarPublicacionException e) {
            solicitud.setRespuesta("Excepción: " + e.getMessage());
        }
        return solicitud;
    }
    
}

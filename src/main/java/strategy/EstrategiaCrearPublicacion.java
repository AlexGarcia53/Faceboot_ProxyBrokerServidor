/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.logicafaceboot.FabricaLogica;
import com.mycompany.proxybrokerservidor.ProxyServidor;
import dominio.Publicacion;
import dominio.Solicitud;
import excepciones.ErrorBusquedaUsuarioException;
import interfaces.IEstrategia;
import interfaces.ILogica;

/**
 *
 * @author Admin
 */
public class EstrategiaCrearPublicacion implements IEstrategia{
    private ILogica logica;
    
    public EstrategiaCrearPublicacion(){
        this.logica= FabricaLogica.crearLogica();
    }

    @Override
    public Solicitud realizarSolicitud(Solicitud solicitud) {
        Publicacion publicacion= ProxyServidor.getInstancia().deserealizarPublicacion(solicitud.getSolicitud());
        try {
            Publicacion respuesta= this.logica.registrarPublicacion(publicacion);
            solicitud.setRespuesta(ProxyServidor.getInstancia().serializarPublicacion(respuesta));
        } catch (ErrorBusquedaUsuarioException e) {
            solicitud.setRespuesta("Excepción: " + e.getMessage());
        }
        return solicitud;
    }
}

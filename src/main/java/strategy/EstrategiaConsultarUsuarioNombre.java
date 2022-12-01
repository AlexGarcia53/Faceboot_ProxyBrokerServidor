/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.logicafaceboot.FabricaLogica;
import com.mycompany.proxybrokerservidor.ProxyServidor;
import dominio.Usuario;
import dominio.Solicitud;
import excepciones.ErrorBusquedaUsuarioException;
import interfaces.IEstrategia;
import interfaces.ILogica;
import java.util.List;

/**
 *
 * @author Admin
 */
public class EstrategiaConsultarUsuarioNombre implements IEstrategia{
    private ILogica logica;
    
    public EstrategiaConsultarUsuarioNombre(){
        this.logica= FabricaLogica.crearLogica();
    }
    
    @Override
    public Solicitud realizarSolicitud(Solicitud solicitud) {
        try {
            Usuario respuesta= this.logica.consultarUsuarioNombre(ProxyServidor.getInstancia().deserealizarUsuario(solicitud.getSolicitud()));
            solicitud.setRespuesta(ProxyServidor.getInstancia().serializarUsuario(respuesta));
        } catch (ErrorBusquedaUsuarioException e) {
            solicitud.setRespuesta("Excepción: " + e.getMessage());
        }
        return solicitud;
    }
    
}

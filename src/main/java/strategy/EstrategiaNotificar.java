/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.logicafaceboot.FabricaLogica;
import com.mycompany.proxybrokerservidor.ProxyServidor;
import dominio.Mensaje;
import dominio.Solicitud;
import dominio.Usuario;
import excepciones.ErrorBusquedaUsuarioException;
import excepciones.ErrorGuardarUsuarioException;
import interfaces.IEstrategia;
import interfaces.ILogica;

/**
 *
 * @author Jarol
 */
public class EstrategiaNotificar implements IEstrategia {

     private ILogica logica;
    
    public EstrategiaNotificar(){
        this.logica= FabricaLogica.crearLogica();
    }
    
    
    @Override
    public Solicitud realizarSolicitud(Solicitud solicitud) {
      
       
        try{
           
             Mensaje datosMensaje= ProxyServidor.getInstancia().deserealizarMensaje(solicitud.getSolicitud());
            
            Mensaje respuestaServidor= logica.enviarNotificacion(datosMensaje);
            solicitud.setRespuesta(ProxyServidor.getInstancia().serializarMensaje(respuestaServidor));
        } catch (ErrorGuardarUsuarioException e){
            solicitud.setRespuesta("Excepción: "+e.getMessage());
        }
        return solicitud;
    }
    
  
    
    
}

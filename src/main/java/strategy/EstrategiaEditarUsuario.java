/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.logicafaceboot.FabricaLogica;
import com.mycompany.proxybrokerservidor.ProxyServidor;
import dominio.Solicitud;
import dominio.Usuario;
import excepciones.ErrorEditarUsuarioException;
import interfaces.IEstrategia;
import interfaces.ILogica;

/**
 *
 * @author Admin
 */
public class EstrategiaEditarUsuario implements IEstrategia{
    private ILogica logica;
    
    public EstrategiaEditarUsuario(){
        this.logica= FabricaLogica.crearLogica();
    }
    
    @Override
    public Solicitud realizarSolicitud(Solicitud solicitud) {
        Usuario datosUsuario= ProxyServidor.getInstancia().deserealizarUsuario(solicitud.getSolicitud());
        try{
            Usuario respuestaServidor= logica.editarPerfilUsuario(datosUsuario);
            solicitud.setRespuesta(ProxyServidor.getInstancia().serializarUsuario(respuestaServidor));
        } catch (ErrorEditarUsuarioException e){
            solicitud.setRespuesta("Excepción: "+e.getMessage());
        }
        return solicitud;
    }
    
}

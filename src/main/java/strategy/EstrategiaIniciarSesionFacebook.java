/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.logicafaceboot.FabricaLogica;
import com.mycompany.proxybrokerservidor.ProxyServidor;
import dominio.Solicitud;
import dominio.Usuario;
import excepciones.ErrorBusquedaUsuarioException;
import interfaces.IEstrategia;
import interfaces.ILogica;

/**
 *
 * @author Gael
 */
public class EstrategiaIniciarSesionFacebook implements IEstrategia {
    private ILogica logica;
    
    public EstrategiaIniciarSesionFacebook(){
        this.logica= FabricaLogica.crearLogica();
    }
    
    @Override
    public Solicitud realizarSolicitud(Solicitud solicitud) {
        Usuario datosUsuario= ProxyServidor.getInstancia().deserealizarUsuario(solicitud.getSolicitud());
        try {
            Usuario usuario = logica.consultarUsuarioPorAToken(datosUsuario);
            if(usuario==null){
               usuario = logica.registrarUsuarioFacebook(datosUsuario);
            }
            solicitud.setRespuesta(ProxyServidor.getInstancia().serializarUsuario(usuario));
        } catch (ErrorBusquedaUsuarioException e) {
            solicitud.setRespuesta("Excepción: " + e.getMessage());
        }
        return solicitud;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.logicafaceboot.FabricaLogica;
import com.mycompany.proxybrokerservidor.Deserealizador;
import dominio.Solicitud;
import dominio.Usuario;
import excepciones.ErrorBusquedaUsuarioException;
import interfaces.IEstrategia;
import interfaces.ILogica;

/**
 *
 * @author Admin
 */
public class EstrategiaIniciarSesion implements IEstrategia{
    private ILogica logica;
    
    public EstrategiaIniciarSesion(){
        this.logica= FabricaLogica.crearLogica();
    }
    
    @Override
    public Solicitud realizarSolicitud(Solicitud solicitud) {
        Usuario datosUsuario= Deserealizador.getInstancia().deserealizarUsuario(solicitud.getSolicitud());
        try {
            Usuario usuarioRegistrado = logica.consultarUsuario(datosUsuario);
            solicitud.setRespuesta(Deserealizador.getInstancia().serializarUsuario(usuarioRegistrado));
        } catch (ErrorBusquedaUsuarioException e) {
            solicitud.setRespuesta("Excepción: " + e.getMessage());
        }
        return solicitud;
    }
    
}

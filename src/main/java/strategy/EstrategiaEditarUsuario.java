/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.logicafaceboot.FabricaLogica;
import com.mycompany.proxybrokerservidor.Deserealizador;
import dominio.Solicitud;
import dominio.Usuario;
import excepciones.ErrorEditarUsuarioException;
import interfaces.IEstrategia;
import interfaces.ILogica;

/**
 * Clase que representa la estrategia para resolver una solicitud de editar usuario.
 * @author Equipo Broker.
 */
public class EstrategiaEditarUsuario implements IEstrategia{
    /**
     * Atributo del tipo lógica.
     */
    private ILogica logica;
    /**
     * Método constructor de la clase.
     */
    public EstrategiaEditarUsuario(){
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
        Usuario datosUsuario= Deserealizador.getInstancia().deserealizarUsuario(solicitud.getSolicitud());
        try{
            Usuario respuestaServidor= logica.editarPerfilUsuario(datosUsuario);
            solicitud.setRespuesta(Deserealizador.getInstancia().serializarUsuario(respuestaServidor));
        } catch (ErrorEditarUsuarioException e){
            solicitud.setRespuesta("Excepción: "+e.getMessage());
        }
        return solicitud;
    }
    
}

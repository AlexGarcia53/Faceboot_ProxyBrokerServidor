/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.logicafaceboot.FabricaLogica;
import com.mycompany.proxybrokerservidor.ProxyServidor;
import dominio.Comentario;
import dominio.Solicitud;
import excepciones.ErrorGuardarComentarioException;
import interfaces.IEstrategia;
import interfaces.ILogica;

/**
 *
 * @author Admin
 */
public class EstrategiaRegistrarComentario implements IEstrategia{
    private ILogica logica;
    
    public EstrategiaRegistrarComentario(){
        this.logica= FabricaLogica.crearLogica();
    }

    @Override
    public Solicitud realizarSolicitud(Solicitud solicitud) {
        Comentario comentario= ProxyServidor.getInstancia().deserealizarComentario(solicitud.getSolicitud());
        try {
            Comentario respuesta= this.logica.registrarComentario(comentario);
            solicitud.setRespuesta(ProxyServidor.getInstancia().serializarComentario(respuesta));
        } catch (ErrorGuardarComentarioException e) {
            solicitud.setRespuesta("Excepción: " + e.getMessage());
        }
        return solicitud;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.logicafaceboot.FabricaLogica;
import com.mycompany.proxybrokerservidor.Deserealizador;
import dominio.Hashtag;
import dominio.Publicacion;
import dominio.Solicitud;
import excepciones.ErrorConsultarPublicacionException;
import excepciones.ErrorConsultarHashtagException;
import interfaces.IEstrategia;
import interfaces.ILogica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class EstrategiaConsultarPublicacionesHashtag implements IEstrategia{
    private ILogica logica;
    
    public EstrategiaConsultarPublicacionesHashtag(){
        this.logica= FabricaLogica.crearLogica();
    }
    @Override
    public Solicitud realizarSolicitud(Solicitud solicitud) {
        try {
            List<Hashtag> respuestaHashtags= this.logica.consultarHashtagNombre(Deserealizador.getInstancia().deserealizarHashtag(solicitud.getSolicitud()).getNombre());
            List<Publicacion> publicaciones= new ArrayList<>();
            for (int i = 0; i < respuestaHashtags.size(); i++) {
                publicaciones.add(this.logica.consultarPublicacion(respuestaHashtags.get(i).getPublicacion()));
            }
            solicitud.setRespuesta(Deserealizador.getInstancia().serializarLista(publicaciones));
        } catch (ErrorConsultarPublicacionException e) {
            solicitud.setRespuesta("Excepción: " + e.getMessage());
        } catch (ErrorConsultarHashtagException e) {
            solicitud.setRespuesta("Excepción: " + e.getMessage());
        }
        return solicitud;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.logicafaceboot.FabricaLogica;
import com.mycompany.proxybrokerservidor.ProxyServidor;
import dominio.Hashtag;
import dominio.Publicacion;
import dominio.Solicitud;
import excepciones.ErrorGuardarHashtagException;
import excepciones.ErrorGuardarPublicacionException;
import interfaces.IEstrategia;
import interfaces.ILogica;
import java.util.ArrayList;
import java.util.List;

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
//            if (publicacion.getContenido().getHashtags().size() == 0) {
                Publicacion respuesta = this.logica.registrarPublicacion(publicacion);
                solicitud.setRespuesta(ProxyServidor.getInstancia().serializarPublicacion(respuesta));
//            }else{
//                List<Hashtag> hashtags= publicacion.getContenido().getHashtags();
//                publicacion.getContenido().setHashtags(new ArrayList<>());
//                Publicacion respuesta= this.logica.registrarPublicacion(publicacion);
//                List<Hashtag> hashtagsRegistrados= new ArrayList<>();
//                for (int i = 0; i < hashtags.size(); i++) {
//                    Hashtag hashtag= hashtags.get(i);
//                    hashtag.setContenido(respuesta.getContenido());
//                    Hashtag hashtagRespuesta= this.logica.registrarHashtag(hashtag);
//                    hashtagsRegistrados.add(hashtagRespuesta);
//                }
//                respuesta.getContenido().setHashtags(hashtagsRegistrados);
//                solicitud.setRespuesta(ProxyServidor.getInstancia().serializarPublicacion(respuesta));
//            }
        } catch (ErrorGuardarPublicacionException e) {
            solicitud.setRespuesta("Excepción: " + e.getMessage());
        } //catch (ErrorGuardarHashtagException e) {
//            solicitud.setRespuesta("Excepción: "+e.getMessage());
//        }
        return solicitud;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import com.mycompany.logicafaceboot.FabricaLogica;
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
        try {
            
            solicitud.setRespuesta("Se llevó a cabo el registro");
        } catch (ErrorBusquedaUsuarioException e) {
            solicitud.setRespuesta("Excepción: " + e.getMessage());
        }
        return solicitud;
    }
}

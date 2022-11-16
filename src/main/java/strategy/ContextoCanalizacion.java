/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import dominio.Solicitud;
import interfaces.IEstrategia;

/**
 *
 * @author Admin
 */
public class ContextoCanalizacion {
    private IEstrategia estrategia;
    
    public void setEstrategia(IEstrategia estrategia){
        this.estrategia= estrategia;
    }
    
    public Solicitud ejecutarEstrategia(Solicitud solicitud){
        return this.estrategia.realizarSolicitud(solicitud);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dominio.Solicitud;

/**
 * Interfaz que contiene el método necesario para atender una solicitud y que se puede atender 
 * mediante distintas estrategias.
 * @author Equipo Broker.
 */
public interface IEstrategia {
    /**
     * Método utilizado para atender una solicitud la cual recibe como parámetro, hace uso de 
     * las funcionalidades del servidor para atender y responder a ella.
     * @param solicitud Solicitud a atender.
     * @return Respuesta a la solicitud.
     */
    public Solicitud realizarSolicitud(Solicitud solicitud);
}

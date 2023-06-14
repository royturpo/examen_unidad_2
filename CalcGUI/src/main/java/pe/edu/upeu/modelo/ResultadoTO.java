
package pe.edu.upeu.modelo;

import lombok.Data;

@Data
public class ResultadoTO {
    private int idResultado;
    private String nombrePartida;
    private String nombreJugador1;
    private String nombreJugador2;
    private String ganador;
    private int punto;
    private String estado;
}



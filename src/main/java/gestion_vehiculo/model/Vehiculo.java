package gestion_vehiculo.model;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    private String placa;
    private String marca;
    private String modelo;
    private String version;
    private String color;
    private int numPuestos;
    private int numPuertas;
    private String combustible;
    private double kilometros;
    private double cilindraje;
    private String categoria;
}

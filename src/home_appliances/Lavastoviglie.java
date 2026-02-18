package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;


public class Lavastoviglie extends Elettrodomestico {
    private final int programma;
    private Float consumoOrario;

   public Lavastoviglie(Float consumoOrario){
       super(consumoOrario);

       this.consumoOrario = consumoOrario;
       this.programma = 1;
   }
    private double consumoEffettivo(){

        double consume = consumoOrario;

        return switch (programma) {
            case 1 -> // Eco
                    1D;
            case 2 -> // Intensivo
                    1.5;
            case 3 -> // Rapido
                    1.0;
            default -> throw new MissingArgumentConfigurationException("programma sbagliato");
        };
    }
}
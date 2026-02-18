package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;

public class Frigo extends Elettrodomestico {
    private final double temperatura;
    private Float consumoOrario;

    public Frigo(Float consumoOrario) {
        super(consumoOrario);

        this.consumoOrario = consumoOrario;
        this.temperatura = 5;
        acceso = true;
    }

    private double costoEffettivo() {

        double consume = consumoOrario;
        return consume * (8 / temperatura);
    }
}
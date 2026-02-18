package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;

public class Lavatrice extends Elettrodomestico {
    private final int velocitaCentrifuga;
    private Float consumoOrario;

    public Lavatrice(Float consumoOrario) {
        super(consumoOrario);

        this.consumoOrario = consumoOrario;
        this.velocitaCentrifuga = 800;
    }
    private  double consumoEffettivo() {

        double consume = consumoOrario;
        return consume * (velocitaCentrifuga / 1200.0);
    }
}
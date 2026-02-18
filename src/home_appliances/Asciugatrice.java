package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;

public class Asciugatrice extends Elettrodomestico {
    private final int temperaturaAsciugatura;
    private Float consumoOrario;

    public Asciugatrice(Float consumoOrario) {
        super(consumoOrario);
        this.consumoOrario = consumoOrario;
        this.temperaturaAsciugatura = 70;
    }

    private double consumoEffettivo() {

        double consume = consumoOrario;
        return consume * (temperaturaAsciugatura / 60.0);
    }
}
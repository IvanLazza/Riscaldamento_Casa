package simulation;

import exception.ApplianceDoesntExistException;
import graphic.GraphicSimulation;
import home_appliances.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;

public class Simulatore {

    private GraphicSimulation simulatoreGrafico;
    private Float dayDuration = 0f;

    public Simulatore() {
        this.simulatoreGrafico = new GraphicSimulation();
    }

    private void parseAppliance(JSONObject appliance, Casa casa) {
        String type = appliance.getString("tipo");
        switch (type.toLowerCase()) {
            case "lavatrice":
                Lavatrice washingMachine = new Lavatrice(appliance,"velocita_centrifuga");
                casa.addAppliance(washingMachine);
                break;
            case "lavastoviglie":
                Lavastoviglie dishwasher = new Lavastoviglie(appliance,"programma");
                casa.addAppliance(dishwasher);
                break;
            case "asciugatrice":
                Asciugatrice dryer = new Asciugatrice(appliance,"temperatura_asciugatura");
                casa.addAppliance(dryer);
                break;
            case "frigorifero":
                Frigo refrigerator = new Frigo(appliance,"temperatura");
                casa.addAppliance(refrigerator);
                break;
            case "pannellifotovoltaici":
                PannelliFotovoltaici pannelli = new PannelliFotovoltaici(appliance);
                casa.addAppliance(pannelli);
                break;
            default:
                throw new ApplianceDoesntExistException("L'elettrodomestico non esiste");
        }
    }

    private void parseRiscaldamento(JSONObject riscaldamento, Casa casa) {
        String tipo = riscaldamento.getString("tipo");
        double consumo = riscaldamento.getDouble("consumo_orario");
        float temperaturaDesiderata = (float) riscaldamento.getDouble("temperatura_desiderata");

        casa.aggiungiImpiantoRiscaldamento(new Riscaldamento(tipo, consumo, temperaturaDesiderata) {});
    }

    public void setup(Casa casa,String fileJson) {
        var path = Paths.get(fileJson);
        byte[] configList;

        try{
            configList = Files.readAllBytes(path);
        } catch (IOException e) {
            System.out.println(fileJson);
            throw new RuntimeException(e);
        }

        String configString = new String(configList);
        JSONObject config = new JSONObject(configString);

        var simulation = config.getJSONObject("simulazione");

        JSONArray appliancesList = config.getJSONArray("elettrodomestici");

        casa.impostSimulation(simulation,"costo_kwh","durata_minuti");

        for (int i = 0; i < appliancesList.length(); i++) {
            JSONObject appliance = appliancesList.getJSONObject(i);
            parseAppliance(appliance,casa);
        }

        if (config.has("riscaldamento")) {
            JSONArray riscaldamentoList = config.getJSONArray("riscaldamento");
            for (int i = 0; i < riscaldamentoList.length(); i++) {
                JSONObject riscaldamento = riscaldamentoList.getJSONObject(i);
                parseRiscaldamento(riscaldamento, casa);
            }
        }
        System.out.printf("SIMULAZIONE: %s%n",casa.getNome());
        System.out.printf("Durata simulazione: %s giorni%n",casa.getDurationSimulation());

        if(simulation.has("stagione")) {
            System.out.println("Stagione: " + simulation.getString("stagione"));
        }
        System.out.println("\nINIZIO SIMULAZIONE");
    }

    public boolean esegui(Casa casa,String fileJson) {

        if (casa.isSimulationRunning() && dayDuration>1.25f){
            dayDuration = 0f;
            casa.clockTick();
            DrawRectangle(0,0,100,100,BLACK);
            if (casa.hasHourPass()){
                casa.updateConsume();
                scheduleActivation(casa);
            }
        }
        simulatoreGrafico.drawSimulation(casa.getClock());
        dayDuration += GetFrameTime();
        return true;
    }

    public void endSimulation(Casa casa) {
        System.out.println("\nFINE SIMULAZIONE");
        System.out.println("Tempo finale: " + casa.getClock().getTime() + " ore");
        double consumoNetto = casa.getKwhUsed() - casa.getEnergiaProdotta();
        System.out.println("Consumo totale elettrodomestici: " + String.format("%.2f", casa.getKwhUsed()) + " kWh");
        System.out.println("Energia prodotta dai pannelli: " + String.format("%.2f", casa.getEnergiaProdotta()) + " kWh");
        System.out.println("Consumo netto (consumo - produzione): " + String.format("%.2f", Math.max(0, consumoNetto)) + " kWh");
        System.out.println("Costo stimato (" + String.format("%.2f", casa.getCostoKwh()) + " €/kWh): " + String.format("%.2f", casa.getconsume()) + " €");
        double risparmio = casa.getEnergiaProdotta() * casa.getCostoKwh();
        System.out.println("Risparmio grazie ai pannelli: " + String.format("%.2f", risparmio) + " €");
        System.out.println("Costo finale: " + String.format("%.2f", casa.getconsume() - risparmio) + " €" );
    }

    private void scheduleActivation(Casa casa) {
        int currentHour = casa.getClock().getTime();
        int currentDay = casa.getClock().getDay();

        casa.getAppliances().stream()
                .filter(appliance -> !(appliance instanceof Frigo) && !(appliance instanceof PannelliFotovoltaici))
                .filter(appliance -> !appliance.isAcceso())
                .forEach(appliance -> {
                    if (appliance instanceof Lavastoviglie) {
                        if (currentHour % 24 == 8 || currentHour % 24 == 20) {
                            appliance.turnOn(180);
                        }
                    } else if (appliance instanceof Lavatrice || appliance instanceof Asciugatrice) {
                        if (currentDay % 7 == 0 || currentDay % 7 == 3) {
                            if (currentHour % 24 == 10) {
                                appliance.turnOn(180);
                            }
                        }
                    }
                });

        casa.getAppliances().stream()
                .filter(appliance -> !(appliance instanceof Frigo))
                .filter(Elettrodomestico::isAcceso)
                .forEach(Elettrodomestico::updateConsumo);
    }
}
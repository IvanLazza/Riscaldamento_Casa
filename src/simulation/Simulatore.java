package simulation;

import enums.SimulationStatus;
import exception.ApplianceDoesntExistException;
import graphic.GraphicMenu;
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

    private GraphicMenu graphicMenu;
    private Float dayDuration = 0f;

    private SimulationStatus simulationStatus =  SimulationStatus.READY;

    public Simulatore(Casa casa, String fileJson) {
        this.graphicMenu = new GraphicMenu();
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


        System.out.printf("SIMULAZIONE: %s%n",casa.getNome());
        System.out.printf("Durata simulazione: %s giorni%n",casa.getDurationSimulation());

        if(simulation.has("stagione")) {
            System.out.println("Stagione: " + simulation.getString("stagione"));
        }
        System.out.println("\nINIZIO SIMULAZIONE");
    }


    public boolean esegui(Casa casa,String fileJson) {
        BeginDrawing();
        if (casa.isSimulationRunning()){
            dayDuration = 0f;
            simulationStatus = casa.getClock().update(GetFrameTime(), simulationStatus);
            DrawRectangle(0,0,100,100,BLACK);
            if (casa.hasHourPass()){
                casa.updateConsume();
                scheduleActivation(casa);
            }
        }


        if (simulationStatus == SimulationStatus.FINISHED) {
            simulationStatus = SimulationStatus.WAITING;
            endSimulation(casa, casa.getDurationSimulation(), casa.getconsume(), casa.getCostoKwh());
            casa.clearHouse();
        }
        graphicMenu.drawGraphicSimulation(casa.getClock());
        graphicMenu.drawMenu(casa.getClock(), (float)casa.getClock().getDuration(), casa, simulationStatus);

        if (simulationStatus == SimulationStatus.WAITING) simulationStatus = endSimulation(casa);
        EndDrawing();
        dayDuration += GetFrameTime();
        return true;
    }

    public SimulationStatus endSimulation(Casa casa) {
        return graphicMenu.endSimulation(casa);
    }

    public SimulationStatus endSimulation(Casa casa, Integer totalDays, Double consumo, Double costo) {
        graphicMenu.setConsumo(consumo);
        graphicMenu.setCosto(costo);
        graphicMenu.setTotalDays(totalDays);
        return graphicMenu.endSimulation(casa);
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
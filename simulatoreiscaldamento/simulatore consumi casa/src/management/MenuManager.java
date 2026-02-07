package management;

import java.util.Scanner;
import casa.Casa;
import home_appliances.Riscaldamento;
import home_appliances.eletrico.*;
import home_appliances.idronici.*;
public class MenuManager {
    private Scanner scanner;
    private Casa casa;
    private DeviceManager deviceManager;
    private SimulationManager simulationManager;

    public MenuManager() {
        this.scanner = new Scanner(System.in);
        this.deviceManager = new DeviceManager();
        this.simulationManager = new SimulationManager();
    }

    public void run() {
        boolean running = true;

        while (running) {
            displayMainMenu();
            int scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    createOrResetSimulation();
                    break;
                case 2:
                    if (checkSimulationExists()) {
                        deviceManager.addDevice(casa, scanner);
                    }
                    break;
                case 3:
                    if (checkSimulationExists()) {
                        deviceManager.removeDevice(casa, scanner);
                    }
                    break;
                case 4:
                    if (checkSimulationExists()) {
                        displayCurrentDevices();
                    }
                    break;
                case 5:
                    if (checkSimulationExists()) {
                        simulationManager.startSimulation(casa);
                    }
                    break;
                case 6:
                    if (checkSimulationExists()) {
                        displayTemperatureInfo();
                    }
                    break;
                case 0:
                    running = false;
                    IO.println("Arrivederci!");
                    break;
                default:
                    IO.println("Scelta non valida!");
            }
        }

        scanner.close();
    }

    private void displayMainMenu() {
        IO.println("\n=== SIMULAZIONE CONSUMI RISCALDAMENTO ===");
        IO.println("1. Crea/Ricrea simulazione");
        IO.println("2. Aggiungi dispositivo");
        IO.println("3. Elimina dispositivo");
        IO.println("4. Visualizza dispositivi attuali");
        IO.println("5. Avvia/Riavvia simulazione");
        IO.println("6. Visualizza info temperatura");
        IO.println("0. Esci");
        IO.print("Scelta: ");
    }

    private void createOrResetSimulation() {
        IO.print("Inserisci la durata della simulazione in giorni: ");
        int durataSimulazione = scanner.nextInt();

        if (casa != null) {
            IO.println("Casa già esistente. Vuoi:");
            IO.println("1. Mantenere i dispositivi e cambiare solo durata");
            IO.println("2. Ricominciare da zero");
            IO.print("Scelta: ");
            int sceltaCasa = scanner.nextInt();

            if (sceltaCasa == 1) {
                casa.resettaSimulazione(durataSimulazione);
                IO.println("Simulazione resettata con nuova durata!");
            } else {
                casa = new Casa(durataSimulazione);
                IO.println("Nuova casa creata!");
            }
        } else {
            casa = new Casa(durataSimulazione);
            IO.println("Casa creata!");
        }
    }

    private boolean checkSimulationExists() {
        if (casa == null) {
            IO.println("Prima devi creare una simulazione (opzione 1)!");
            return false;
        }
        return true;
    }

    private void displayCurrentDevices() {
        IO.println("\n=== DISPOSITIVI ATTUALI ===");
        casa.stampaStatoImpianti();
    }

    private void displayTemperatureInfo() {
        IO.println("\n=== INFORMAZIONI TEMPERATURA ===");
        IO.println("Temperatura attuale: " + String.format("%.1f", casa.getClock().getTemperature()) + "°C");
        IO.println("\nDispositivi e loro temperature desiderate:");
        for (Riscaldamento r : casa.getImpiantiRiscaldamento()) {
            IO.println("- " + r.getNome() + ": si attiva sotto " + r.getDesiredTemperature() + "°C");
        }
    }
}
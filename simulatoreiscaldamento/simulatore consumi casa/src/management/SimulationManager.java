package management;

import casa.Casa;

public class SimulationManager {

    public void startSimulation(Casa casa) {
        // Resetta e avvia
        casa.resettaSimulazione(casa.getClock().getSimulationDuration());

        IO.println("\n=== INIZIO SIMULAZIONE ===");
        IO.println("Inizio: " + casa.getClock());

        // Simula
        while (!casa.getClock().isSimulationFinished()) {
            casa.getClock().tick();
            casa.aggiornaConsumi();
            casa.getPanelliSolari().tick(casa.getClock());
        }

        // Riepilogo semplice
        showSimpleReport(casa);
    }

    private void showSimpleReport(Casa casa) {
        IO.println("\n=== FINE SIMULAZIONE ===");

        double consumoKWh = casa.getConsumoTotale();
        consumoKWh = consumoKWh - casa.getPanelliSolari().getGuadagnoTotale();
        double prezzoKWh = 0.20;
        double costo = consumoKWh * prezzoKWh;

        int oreSimulate = casa.getClock().getTotalTick();
        int giorniSimulati = oreSimulate / 24;

        IO.println("Durata: " + giorniSimulati + " giorni");
        IO.println("Energia Risparmiata: " + String.format("%.2f", casa.getPanelliSolari().getGuadagnoTotale())+ " kWh");
        IO.println("Consumo totale: " + String.format("%.2f", consumoKWh) + " kWh");
        IO.println("Costo (" + prezzoKWh + " €/kWh): " + String.format("%.2f", costo) + " €");
    }
}
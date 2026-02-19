package casa;
import home_appliances.*;
import home_appliances.eletrico.PannelliRadianti;
import home_appliances.eletrico.Termoventilatori;
import home_appliances.generatori.PannelliSolari;
import time.Clock;

import java.util.ArrayList;

public class Casa {
    private ArrayList<Riscaldamento> impiantiRiscaldamento;
    private Clock clock;
    private PannelliSolari panelliSolari;

    public Casa(int simulationDuration) {
        impiantiRiscaldamento = new ArrayList<>();
        clock = new Clock(simulationDuration);
        panelliSolari = new PannelliSolari();
    }

    public void aggiornaConsumi() {
        for (Riscaldamento i : impiantiRiscaldamento) {
            i.ChangeStatus(clock);
            i.aggiornaConsumo();
        }
    }

    public double getConsumoTotale() {
        double totale = 0;
        for (Riscaldamento e : impiantiRiscaldamento) {
            totale += e.getConsumoAccumulato();
        }
        return totale;
    }

    public void stampaStatoImpianti() {
        IO.println("\n=== STATO IMPIANTI RISCALDAMENTO ===");
        for (Riscaldamento e : impiantiRiscaldamento) {
            IO.println(e.toString());
        }
        IO.println("Consumo totale casa: " + String.format("%.2f", getConsumoTotale()) + " kWh");
        IO.println();
    }

    public ArrayList<Riscaldamento> getImpiantiRiscaldamento() {
        return impiantiRiscaldamento;
    }

    public Clock getClock() {
        return clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public void aggiungiImpiantoRiscaldamento(Riscaldamento impianto) {
        impiantiRiscaldamento.add(impianto);
    }

    public void rimuoviImpiantoRiscaldamento(int index) {
        if (index >= 0 && index < impiantiRiscaldamento.size()) {
            impiantiRiscaldamento.remove(index);
        }
    }

    public void resettaSimulazione(int nuovaDurata) {
        this.clock = new Clock(nuovaDurata);
        for (Riscaldamento impianto : impiantiRiscaldamento) {
            impianto.resetConsumo();
        }
    }

    public PannelliSolari getPanelliSolari() {
        return panelliSolari;
    }
}
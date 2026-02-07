package management;

import java.util.Scanner;
import casa.Casa;
import home_appliances.eletrico.*;
import home_appliances.idronici.*;

public class DeviceManager {

    public void addDevice(Casa casa, Scanner scanner) {
        IO.println("\n1. Elettrici\n2. Idronici (acqua)");
        IO.print("Scelta: ");
        int tipo = scanner.nextInt();

        if (tipo == 1) {
            addElectricDevice(casa, scanner);
        } else if (tipo == 2) {
            addHydronicDevice(casa, scanner);
        } else {
            IO.println("Scelta sbagliata!");
        }
    }

    private void addElectricDevice(Casa casa, Scanner scanner) {
        IO.println("\n1. Termoventilatore\n2. Pompa di calore\n3. Radiatore");
        IO.println("4. Riscaldamento a pavimento\n5. Stufa\n6. Termoconvettore");
        IO.println("7. Piastra\n8. Termocoperta\n9. Ventilconvettore");
        IO.println("10. Caldaia elettrica\n11. Caminetto elettrico\n12. Pannello radiante");
        IO.print("Scelta: ");

        int scelta = scanner.nextInt();

        switch (scelta) {
            case 1: casa.aggiungiImpiantoRiscaldamento(new Termoventilatori()); break;
            case 2: casa.aggiungiImpiantoRiscaldamento(new PompeCalore()); break;
            case 3: casa.aggiungiImpiantoRiscaldamento(new Radiatori()); break;
            case 4: casa.aggiungiImpiantoRiscaldamento(new RiscaldamentoPavimento()); break;
            case 5: casa.aggiungiImpiantoRiscaldamento(new Stufe()); break;
            case 6: casa.aggiungiImpiantoRiscaldamento(new Termoconvettori()); break;
            case 7: casa.aggiungiImpiantoRiscaldamento(new Piastre()); break;
            case 8: casa.aggiungiImpiantoRiscaldamento(new Termocoperte()); break;
            case 9: casa.aggiungiImpiantoRiscaldamento(new VentilconettoriElettrici()); break;
            case 10: casa.aggiungiImpiantoRiscaldamento(new CaldaiaElettrica()); break;
            case 11: casa.aggiungiImpiantoRiscaldamento(new CaminettiElettrici()); break;
            case 12: casa.aggiungiImpiantoRiscaldamento(new PannelliRadianti()); break;
            default: IO.println("Scelta sbagliata!");
        }

        if (scelta >= 1 && scelta <= 12) {
            IO.println("Dispositivo aggiunto!");
        }
    }

    private void addHydronicDevice(Casa casa, Scanner scanner) {
        IO.println("\n1. Pompa di calore H2O\n2. Riscaldamento a pavimento H2O");
        IO.println("3. Termosifone\n4. Ventilconvettore H2O\n5. Caldaia H2O");
        IO.print("Scelta: ");

        int scelta = scanner.nextInt();

        switch (scelta) {
            case 1: casa.aggiungiImpiantoRiscaldamento(new PompaCaloreH2O()); break;
            case 2: if(casa.getImpiantiRiscaldamento().contains(new CaldaiaH2O()) || casa.getImpiantiRiscaldamento().contains(new PompaCaloreH2O())) {
                casa.aggiungiImpiantoRiscaldamento(new RiscaldamentoPavimentoH2O());
                IO.println("Dispositivo aggiunto!");}else{
                IO.println("Non ci sta nessuna caldaia o pompa di calore!");
            }break;
            case 3: if(casa.getImpiantiRiscaldamento().contains(new CaldaiaH2O()) || casa.getImpiantiRiscaldamento().contains( new PompaCaloreH2O())) {
                casa.aggiungiImpiantoRiscaldamento(new Termosifoni());
                IO.println("Dispositivo aggiunto!");}else{
                IO.println("Non ci sta nessuna caldaia o pompa di calore!");
            } break;
            case 4: if(casa.getImpiantiRiscaldamento().contains(new CaldaiaH2O()) || casa.getImpiantiRiscaldamento().contains(new PompaCaloreH2O())) {
                casa.aggiungiImpiantoRiscaldamento(new VentilconvettoriH2O());
                IO.println("Dispositivo aggiunto!");}else{
                IO.println("Non ci sta nessuna caldaia o pompa di calore!");
            } break;
            case 5: casa.aggiungiImpiantoRiscaldamento(new CaldaiaH2O()); break;
            default: IO.println("Scelta sbagliata!");
        }

        if (scelta == 1 || scelta == 5) {
            IO.println("Dispositivo aggiunto!");
        }
    }

    public void removeDevice(Casa casa, Scanner scanner) {
        var dispositivi = casa.getImpiantiRiscaldamento();

        if (dispositivi.isEmpty()) {
            IO.println("Nessun dispositivo!");
            return;
        }

        IO.println("\nDispositivi:");
        for (int i = 0; i < dispositivi.size(); i++) {
            IO.println((i + 1) + ". " + dispositivi.get(i).getNome());
        }

        IO.print("Quale eliminare? (0 per annullare): ");
        int scelta = scanner.nextInt();

        if (scelta > 0 && scelta <= dispositivi.size()) {
            casa.rimuoviImpiantoRiscaldamento(scelta - 1);
            IO.println("Eliminato!");
        } else if (scelta != 0) {
            IO.println("Scelta sbagliata!");
        }
    }
}
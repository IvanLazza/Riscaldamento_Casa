import simulation.*;

import static com.raylib.Raylib.*;

void main() {

    InitWindow(1080, 920, "Simulatore Casa");
    SetTargetFPS(0);
    Casa casa1 = new Casa("cicciogamer89");
    String fileJson = "config.json";
    Simulatore simulatore = new Simulatore(casa1,  fileJson);
    boolean isProgrammRunning;

    do {
        isProgrammRunning = simulatore.esegui(casa1, fileJson);
    } while (isProgrammRunning);

    IO.println("Uscita dal programma");
}

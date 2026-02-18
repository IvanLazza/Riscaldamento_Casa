package graphic;

import enums.ProgramState;
import enums.SimulationStatus;
import simulation.Casa;
import simulation.Clock;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;

public class GraphicMenu {

    private GraphicSimulation graphicSimulation;

    private ProgramState programState;
    private SimulationStatus simulationStatus;

    private boolean isHovering;
    private ButtonArrayList bottoniMenu;

    private Texture sidebar;
    private PannelloStart startPanel;
    private PannelloAggiungi addPanel;
    private Texture panel;
    private Texture backButton;

    private Rectangle hitbox;
    private Rectangle backButtonHitbox;

    private Integer totalDays = 0;
    private Double costo = 0d;
    private Double consumo = 0d;

    public GraphicMenu() {
        graphicSimulation = new GraphicSimulation();

        bottoniMenu = new ButtonArrayList();
        bottoniMenu.add(new MenuButton("bottoneAvvio", 810f, 200f, 260f, 60f, "AVVIO SIMULAZIONE", LIGHTGRAY));
        bottoniMenu.add(new MenuButton("bottoneAggiungi", 810f, 290f, 260f, 60f, "AGGIUNGI IMPIANTO", LIGHTGRAY));

        programState = ProgramState.RUNNING;
        startPanel = new PannelloStart("AVVIO SIMULAZIONE");
        addPanel = new PannelloAggiungi("AGGIUNGI IMPIANTO");
        sidebar = LoadTexture("sprites/menu/Sidebar.png");
        panel = LoadTexture("sprites/menu/Panel.png");
        backButton = LoadTexture("sprites/menu/back.png");

        hitbox = new Rectangle();
        hitbox.x(100); hitbox.y(200); hitbox.width(900); hitbox.height(700);

        backButtonHitbox = new Rectangle();
        backButtonHitbox.x(hitbox.x()); backButtonHitbox.y(200); backButtonHitbox.width(100); backButtonHitbox.height(100);
    }

    public void drawStats(Clock clock) {
        if (clock.getHour() >= 6f && clock.getHour() < 19f) {
            DrawText("Giorno: " + clock.getDay() + " [" + clock.getHour() + ":00]", 20, 20, 20, BLACK);
        } else {
            DrawText("Giorno: " + clock.getDay() + " [" + clock.getHour() + ":00]", 20, 20, 20, WHITE);
        }
        DrawText("SIMULAZIONE CASA", 820, 45, 25, WHITE);
    }

    public void drawGraphicSimulation(Clock clock) {
        graphicSimulation.drawSimulation(clock);
    }

    public void drawMenu(Clock clock, Float durata, Casa casa, SimulationStatus simulationStatus) {


        DrawTexture(sidebar, 800, 0, WHITE);
        drawStats(clock);

        bottoniMenu.get("bottoneAvvio").draw(programState);
        if (bottoniMenu.get("bottoneAvvio").isHovering(programState)) {
            bottoniMenu.get("bottoneAvvio").setColor(WHITE);
            if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) bottoneAvvio(clock, casa);
        } else bottoniMenu.get("bottoneAvvio").setColor(LIGHTGRAY);

        bottoniMenu.get("bottoneAggiungi").draw(programState);
        if (bottoniMenu.get("bottoneAggiungi").isHovering(programState)) {
            bottoniMenu.get("bottoneAggiungi").setColor(WHITE);
            if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) bottoneAggiungi(clock, casa);
        } else bottoniMenu.get("bottoneAggiungi").setColor(LIGHTGRAY);

        isHovering = bottoniMenu.hasHovered(programState);

        switch (programState) {
            case RUNNING: if ((isHovering) || CheckCollisionPointRec(GetMousePosition(), backButtonHitbox)) SetMouseCursor(MOUSE_CURSOR_POINTING_HAND);
                            else SetMouseCursor(MOUSE_CURSOR_DEFAULT);break;
            case START: bottoneAvvio(clock, casa); break;
            case ADD: bottoneAggiungi(clock, casa); break;
        }
    }

    public SimulationStatus endSimulation(Casa casa) {

        DrawTexture(panel, (int)hitbox.x(), (int)hitbox.y(), WHITE);
        DrawText("FINE SIMULAZIONE", (int)hitbox.x()+290, (int)hitbox.y()+45, 25, WHITE);
        DrawTexture(backButton, (int)backButtonHitbox.x(), (int)backButtonHitbox.y(), WHITE);

        DrawText("Giorni trascorsi: " + totalDays, 160, 400, 20, WHITE);
        DrawText("Consumo totale:  " + consumo, 155, 500, 20, WHITE);
        DrawText("Prezzo totale:  " + costo, 155, 600, 20, WHITE);

        if (CheckCollisionPointRec(GetMousePosition(), backButtonHitbox) && IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            casa.clearHouse();
            return SimulationStatus.READY;
        }
        return SimulationStatus.WAITING;

    }

    private Float bottoneAvvio(Clock clock, Casa casa) {
        programState = ProgramState.START;
        StartConfig cfg = startPanel.update(programState, casa);
        programState = cfg.programState;

        graphicSimulation.resetSimulation(clock);
        clock.setDuration(cfg.giorni);
        clock.setDayDuration(cfg.durata);
        graphicSimulation.setDayDuration((float)cfg.durata);
        simulationStatus = clock.Start(simulationStatus);

        startPanel.draw(casa);

        return (float)cfg.durata;
    }

    private void bottoneAggiungi(Clock clock, Casa casa) {
        programState = ProgramState.ADD;
        programState = addPanel.update();
        addPanel.draw(casa);
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public void setConsumo(Double consumo) {
        this.consumo = consumo;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }
}


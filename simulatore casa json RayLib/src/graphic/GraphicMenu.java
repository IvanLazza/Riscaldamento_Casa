package graphic;

import enums.ProgramState;
import simulation.Clock;

import java.awt.*;
import java.util.ArrayList;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;

public class GraphicMenu {

    private ProgramState programState;

    private boolean isHovering;
    private ButtonArrayList bottoniMenu;

    private Texture sidebar;
    private PannelloStart startPanel;

    public GraphicMenu() {
        bottoniMenu = new ButtonArrayList();
        bottoniMenu.add(new MenuButton("bottoneAvvio", 810f, 200f, 260f, 60f, "AVVIO SIMULAZIONE", LIGHTGRAY));
        bottoniMenu.add(new MenuButton("bottoneAggiungi", 810f, 290f, 260f, 60f, "AGGIUNGI IMPIANTO", LIGHTGRAY));
        bottoniMenu.add(new MenuButton("bottoneElimina", 810f, 380f, 260f, 60f, "RIMUOVI IMPIANTO", LIGHTGRAY));
        bottoniMenu.add(new MenuButton("bottoneVisualizza", 810f, 470f, 260f, 60f, "VISUALIZZA IMPIANTI", LIGHTGRAY));

        programState = ProgramState.RUNNING;
        startPanel = new PannelloStart("AVVIO SIMULAZIONE");
        sidebar = LoadTexture("sprites/menu/Sidebar.png");
    }

    public void drawStats(Clock clock) {
        if (clock.getHour() >= 6f && clock.getHour() < 19f) {
            DrawText("Giorno: " + clock.getDay() + " [" + clock.getHour() + ":00]", 20, 20, 20, BLACK);
        } else {
            DrawText("Giorno: " + clock.getDay() + " [" + clock.getHour() + ":00]", 20, 20, 20, WHITE);
        }
        DrawText("SIMULAZIONE CASA", 820, 45, 25, WHITE);
    }

    public void drawMenu(Clock clock) {

        DrawTexture(sidebar, 800, 0, WHITE);
        drawStats(clock);

        bottoniMenu.get("bottoneAvvio").draw(programState);
        if (bottoniMenu.get("bottoneAvvio").isHovering(programState)) {
            bottoniMenu.get("bottoneAvvio").setColor(WHITE);
            if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) bottoneAvvio();
        } else bottoniMenu.get("bottoneAvvio").setColor(LIGHTGRAY);

        bottoniMenu.get("bottoneAggiungi").draw(programState);
        if (bottoniMenu.get("bottoneAggiungi").isHovering(programState)) {
            bottoniMenu.get("bottoneAggiungi").setColor(WHITE);
            if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) bottoneAvvio();
        } else bottoniMenu.get("bottoneAggiungi").setColor(LIGHTGRAY);

        bottoniMenu.get("bottoneElimina").draw(programState);
        if (bottoniMenu.get("bottoneElimina").isHovering(programState)) {
            bottoniMenu.get("bottoneElimina").setColor(WHITE);
            if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) bottoneAvvio();
        } else bottoniMenu.get("bottoneElimina").setColor(LIGHTGRAY);

        bottoniMenu.get("bottoneVisualizza").draw(programState);
        if (bottoniMenu.get("bottoneVisualizza").isHovering(programState)) {
            bottoniMenu.get("bottoneVisualizza").setColor(WHITE);
            if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) bottoneAvvio();
        } else bottoniMenu.get("bottoneVisualizza").setColor(LIGHTGRAY);


        isHovering = bottoniMenu.hasHovered(programState);

        if (isHovering) SetMouseCursor(MOUSE_CURSOR_POINTING_HAND);
        else SetMouseCursor(MOUSE_CURSOR_DEFAULT);

        switch (programState) {
            case RUNNING: break;
            case START: bottoneAvvio(); break;
            case ADD: bottoneAggiungi(); break;
            case REMOVE: bottoneElimina(); break;
            case VISUALIZE: bottoneVisualizza(); break;
        }
    }

    private void bottoneAvvio() {
        programState = ProgramState.START;
        startPanel.draw(programState);
    }

    private void bottoneAggiungi() {}

    private void bottoneElimina() {}

    private void bottoneVisualizza() {}
}


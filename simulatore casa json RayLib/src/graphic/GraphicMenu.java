package graphic;

import simulation.Clock;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;

public class GraphicMenu {

    private Rectangle bottoneAvvio;
    private Rectangle bottoneAggiungi;
    private Rectangle bottoneElimina;
    private Rectangle bottoneVisualizza;

    private Vector2 mousePosition;

    public GraphicMenu() {
        bottoneAvvio = new Rectangle();
        bottoneAvvio.x(810); bottoneAvvio.y(100); bottoneAvvio.width(260); bottoneAvvio.height(60);
        bottoneAggiungi = new Rectangle();
        bottoneAggiungi.x(810); bottoneAggiungi.y(190); bottoneAggiungi.width(260); bottoneAggiungi.height(60);
        bottoneElimina = new Rectangle();
        bottoneElimina.x(810); bottoneElimina.y(280); bottoneElimina.width(260); bottoneElimina.height(60);
        bottoneVisualizza = new Rectangle();
        bottoneVisualizza.x(810); bottoneVisualizza.y(370); bottoneVisualizza.width(260); bottoneVisualizza.height(60);

        mousePosition = new Vector2();
    }

    public void drawStats(Clock clock) {
        if (clock.getHour() >= 6f && clock.getHour() < 19f) {
            DrawText("Giorno: " + clock.getDay() + " [" + clock.getHour() + ":00]", 20, 20, 20, BLACK);
        } else {
            DrawText("Giorno: " + clock.getDay() + " [" + clock.getHour() + ":00]", 20, 20, 20, WHITE);
        }
        DrawText("SIMULAZIONE CASA", 820, 20, 25, WHITE);
    }

    public void drawMenu(Clock clock) {

        mousePosition = GetMousePosition();
        DrawRectangle(800, 0, 1080, 920, DARKGRAY);
        drawStats(clock);


         if (CheckCollisionPointRec(mousePosition, bottoneAvvio)) {
            DrawRectangleRec(bottoneAvvio, WHITE);
            if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
                bottoneAvvio();
            }
        } else {
            DrawRectangleRec(bottoneAvvio, LIGHTGRAY);
        }
        DrawText("AVVIO SIMULAZIONE", (int)bottoneAvvio.x()+20, (int)bottoneAvvio.y()+25, 20, BLACK);


        if (CheckCollisionPointRec(mousePosition, bottoneAggiungi)) {
            DrawRectangleRec(bottoneAggiungi, WHITE);
            if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
                bottoneAggiungi();
            }
        } else {
            DrawRectangleRec(bottoneAggiungi, LIGHTGRAY);
        }
        DrawText("AGGIUNGI IMPIANTO", (int)bottoneAggiungi.x()+20, (int)bottoneAggiungi.y()+25, 20, BLACK);


        if (CheckCollisionPointRec(mousePosition, bottoneElimina)) {
            DrawRectangleRec(bottoneElimina, WHITE);
            if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
                bottoneElimina();
            }
        } else {
            DrawRectangleRec(bottoneElimina, LIGHTGRAY);
        }
        DrawText("RIMUOVI IMPIANTO", (int)bottoneElimina.x()+20, (int)bottoneElimina.y()+25, 20, BLACK);

        if (CheckCollisionPointRec(mousePosition, bottoneVisualizza)) {
            DrawRectangleRec(bottoneVisualizza, WHITE);
            if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
                bottoneVisualizza();
            }
        } else {
            DrawRectangleRec(bottoneVisualizza, LIGHTGRAY);
        }
        DrawText("VISUALIZZA IMPIANTI", (int)bottoneVisualizza.x()+20, (int)bottoneVisualizza.y()+25, 20, BLACK);
    }

    private void bottoneAvvio() {}

    private void bottoneAggiungi(){}

    private void bottoneElimina(){}

    private void bottoneVisualizza(){}
}


package graphic;

import enums.ProgramState;
import simulation.Clock;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;

public class GraphicSimulation {

    private GraphicMenu graphicMenu;

    private Float timePassed = 0f;
    private Vector2 sunPosition;
    private Vector2 moonPosition;
    private Float cx = 400f;
    private Float cy = 450f;
    private Float r = 350f;


    private Texture dayHouseTexture;
    private Texture nightHouseTexture;
    private Texture moonTexture;
    private Texture sunTexture;

    public GraphicSimulation() {
        dayHouseTexture = LoadTexture("sprites/casa/DayHouse.png");
        nightHouseTexture = LoadTexture("sprites/casa/NightHouse.png");

        sunPosition = new Vector2();
        sunPosition.x(50);
        sunPosition.y(300);

        sunTexture = LoadTexture("sprites/casa/sole_trasparente.png");

        moonPosition = new Vector2();
        moonPosition.x(50);
        moonPosition.y(300);

        moonTexture = LoadTexture("sprites/casa/luna_trasparente.png");

        graphicMenu = new GraphicMenu();
    }

    public void drawSimulation(Clock clock) {
        BeginDrawing();

        drawBackGround(clock);

        drawDayNightCycle(clock);

        graphicMenu.drawMenu(clock);

        EndDrawing();
        if (WindowShouldClose()) CloseWindow();
    }

    public void drawDayNightCycle(Clock clock) {

        if (clock.isSimulationRunning()) {
            timePassed += GetFrameTime();
            if (timePassed >= 30f) {
                timePassed = 0f;
            }
        }

        float gameHour = (timePassed % 30f) / 30f * 24f;

        if (gameHour >= 6f && gameHour <= 19f) {

            float t = (gameHour - 6f) / 13f;
            float angle = t * (float) Math.PI;

            sunPosition.x(cx + (float) Math.cos(angle - Math.PI) * r);
            sunPosition.y(cy + (float) Math.sin(angle - Math.PI) * r);

            sunPosition.x(sunPosition.x()-70);
            sunPosition.y(sunPosition.y()-80);
            DrawTextureV(sunTexture, sunPosition, WHITE);
        } else {
            float moonHour;
            if (gameHour >= 19) {
                moonHour = gameHour - 19f;
            } else {
                moonHour = gameHour + 5f;
            }

            float t =  moonHour / 11f;
            float angle = t * (float) Math.PI;

            moonPosition.x(cx + (float) Math.cos(angle - Math.PI) * r);
            moonPosition.y(cy + (float) Math.sin(angle - Math.PI) * r);

            moonPosition.x(moonPosition.x()-110);
            moonPosition.y(moonPosition.y()-80);
            DrawTextureV(moonTexture, moonPosition, WHITE);
        }
    }


    public void drawBackGround(Clock clock) {
        if (clock.getHour() >= 6f && clock.getHour() < 19f) {
            DrawTexture(dayHouseTexture, -350, 0, WHITE);
        } else {
            DrawTexture(nightHouseTexture, -350, 0, WHITE);
        }
    }
}

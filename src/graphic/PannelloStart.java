package graphic;

import enums.ProgramState;
import simulation.Casa;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;

public class PannelloStart {

    private String nome;
    private Rectangle hitbox;
    private Rectangle backButtonHitbox;
    private Rectangle buttonHitbox;
    private TextBox tb1;
    private TextBox tb2;

    private Texture pannello;
    private Texture backButton;
    private Texture button;
    private Texture hoveredButton;

    private boolean conditions;

    public PannelloStart(String nome) {
        this.nome = nome;
        hitbox = new Rectangle();
        hitbox.x(100); hitbox.y(200); hitbox.width(900); hitbox.height(700);

        backButtonHitbox = new Rectangle();
        backButtonHitbox.x(hitbox.x()); backButtonHitbox.y(200); backButtonHitbox.width(100); backButtonHitbox.height(100);

        buttonHitbox = new Rectangle();
        buttonHitbox.x(380); buttonHitbox.y(600); buttonHitbox.width(260); buttonHitbox.height(60);

        Rectangle hitboxTB1 = new Rectangle();
        hitboxTB1.x(350); hitboxTB1.y(380); hitboxTB1.width(400); hitboxTB1.height(50);
        tb1 = new TextBox(hitboxTB1);
        Rectangle hitboxTB2 = new Rectangle();
        hitboxTB2.x(350); hitboxTB2.y(480); hitboxTB2.width(400); hitboxTB2.height(50);
        tb2 = new TextBox(hitboxTB2);

        pannello = LoadTexture("sprites/menu/Panel.png");
        backButton = LoadTexture("sprites/menu/back.png");

        button = LoadTexture("sprites/menu/Button.png");
        hoveredButton = LoadTexture("sprites/menu/PressedButton.png");
    }

    public StartConfig update(ProgramState programState, Casa casa) {
        if (tb1.checkConditions() == GREEN && tb2.checkConditions() == GREEN) {
            conditions = true;
        } else {
            conditions = false;
        }
        if (CheckCollisionPointRec(GetMousePosition(), backButtonHitbox) && IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) return new StartConfig(ProgramState.RUNNING, -1, -1);
        if (CheckCollisionPointRec(GetMousePosition(), buttonHitbox) && IsMouseButtonPressed(MOUSE_BUTTON_LEFT) && conditions && (!casa.getElettrodomestici().isEmpty() || !casa.getImpiantiRiscaldamento().isEmpty())) {
            int giorni = Integer.parseInt(tb1.getString().toString());
            int durata = Integer.parseInt(tb2.getString().toString());
            programState = programState.RUNNING;
            return new StartConfig(programState, giorni, durata);
        }
        return new StartConfig(programState, -1, -1);
    }

    public void draw(Casa casa) {
        DrawTexture(pannello, (int)hitbox.x(), (int)hitbox.y(), WHITE);
        DrawText(nome, (int)hitbox.x()+290, (int)hitbox.y()+45, 25, WHITE);
        DrawTexture(backButton, (int)backButtonHitbox.x(), (int)backButtonHitbox.y(), WHITE);

        tb1.showText();
        DrawText("Numero Giorni:  ", 160, 400, 20, WHITE);
        tb2.showText();
        DrawText("Lunghezza Giorni:  ", 155, 500, 20, WHITE);

        if (!CheckCollisionPointRec(GetMousePosition(), buttonHitbox)) DrawTexture(button, (int)buttonHitbox.x(), (int)buttonHitbox.y(), WHITE);
        else DrawTexture(hoveredButton, (int)buttonHitbox.x(), (int)buttonHitbox.y(), WHITE);
        if (!casa.getImpiantiRiscaldamento().isEmpty() || !casa.getElettrodomestici().isEmpty()) {
            if (conditions) DrawText("Avvia Simulazione", 420, 620, 20, WHITE);
            else DrawText("Variabili Errate", 420, 620, 20, RED);
        } else DrawText("Assenza Impianti", 420, 620, 20, RED);

        if (CheckCollisionPointRec(GetMousePosition(), backButtonHitbox) || CheckCollisionPointRec(GetMousePosition(), buttonHitbox)) SetMouseCursor(MOUSE_CURSOR_POINTING_HAND);
        else if (tb1.checkCollision() || tb2.checkCollision()) SetMouseCursor(MOUSE_CURSOR_IBEAM);
        else SetMouseCursor(MOUSE_CURSOR_DEFAULT);
    }
}
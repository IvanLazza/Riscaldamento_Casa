package graphic;

import enums.ProgramState;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;

public class PannelloStart {

    private String nome;
    private Rectangle hitbox;
    private Rectangle backButtonHitbox;
    private TextBox tb1;
    private TextBox tb2;

    private Texture pannello;
    private Texture backButton;

    public PannelloStart(String nome) {
        this.nome = nome;
        hitbox = new Rectangle();
        hitbox.x(100); hitbox.y(200); hitbox.width(900); hitbox.height(700);

        backButtonHitbox = new Rectangle();
        backButtonHitbox.x(hitbox.x()+hitbox.width()-200); backButtonHitbox.y(200); hitbox.width(55); hitbox.height(55);

        tb1 = new TextBox();
        tb2 = new TextBox();

        pannello = LoadTexture("sprites/menu/Panel.png");
        backButton = LoadTexture("sprites/menu/back.png");
    }

    public void draw(ProgramState programState) {
        DrawTexture(pannello, (int)hitbox.x(), (int)hitbox.y(), WHITE);
        DrawText(nome, (int)hitbox.x()+20, (int)hitbox.y()+35, 25, WHITE);
        DrawTexture(backButton, (int)backButtonHitbox.x(), (int)backButtonHitbox.y(), WHITE);
        if (CheckCollisionPointRec(GetMousePosition(), backButtonHitbox) && IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) programState = ProgramState.RUNNING;
    }

}

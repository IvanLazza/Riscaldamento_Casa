package graphic;

import enums.ProgramState;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;

import static com.raylib.Colors.BLACK;
import static com.raylib.Raylib.DrawText;

public class MenuButton {

    private String name;
    private Rectangle hitbox;
    private String text;
    private Color color;

    private Texture button;
    private Texture hoveredButton;

    public MenuButton(String name, Float x, Float y, Float witdh, Float heigh, String text, Color color) {
        this.name = name;
        this.hitbox = new Rectangle();
        hitbox.x(x); hitbox.y(y); hitbox.width(witdh); hitbox.height(heigh);
        this.text = text;
        this.color = color;

        button = LoadTexture("sprites/menu/Button.png");
        hoveredButton = LoadTexture("sprites/menu/pressedButton.png");
    }

    public void draw(ProgramState programState) {
        if (!isHovering(programState)) DrawTexture(button, (int)hitbox.x(), (int)hitbox.y(), WHITE);
        else DrawTexture(hoveredButton, (int)hitbox.x(), (int)hitbox.y(), WHITE);
        DrawText(text, (int)hitbox.x()+20, (int)hitbox.y()+25, 20, WHITE);
    }

    public boolean isHovering(ProgramState programState) {
        if (programState==ProgramState.RUNNING) return CheckCollisionPointRec(GetMousePosition(), hitbox);
        else return false;
    }

    public String getName() {
        return name;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

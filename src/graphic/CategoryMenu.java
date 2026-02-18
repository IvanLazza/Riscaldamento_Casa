package graphic;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;

public class CategoryMenu {

    private String nome;
    private Rectangle hitbox;

    private Texture panel;


    public CategoryMenu(String nome, Rectangle hitbox) {
        this.nome = nome;
        this.hitbox = hitbox;

        panel = LoadTexture("sprites/menu/smallPanel.png");
    }

    public void draw() {
        DrawTexture(panel, (int)hitbox.x(), (int)hitbox.y(), WHITE);
        DrawText(nome, (int)hitbox.x()+5, (int)hitbox.y()+65, 19, WHITE);
    }

    public boolean checkCollision() {
        return CheckCollisionPointRec(GetMousePosition(), hitbox);
    }
}

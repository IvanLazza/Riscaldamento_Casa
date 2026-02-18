package graphic;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;

public class TextBox {

    private StringBuilder string;
    private Rectangle hitbox;
    private Color color;

    public TextBox(Rectangle hitbox) {
        string = new StringBuilder();
        this.hitbox = hitbox;
    }

    public void showText() {
        DrawRectangleRec(hitbox, WHITE);
        color = checkConditions();
        DrawText(string.toString(), (int)hitbox.x()+10, (int)hitbox.y()+20, 20, color);
        addToString();
    }

    public void addToString() {
        if (checkCollision()) {
            int key = GetCharPressed();
            if (key > 0) string.append((char) key);
            if (IsKeyPressed(KEY_BACKSPACE)) if (!string.isEmpty()) string.deleteCharAt(string.length()-1);
        }
    }

    public Color checkConditions() {
        if (string.isEmpty()) return RED;
        for (Character c : string.toString().toCharArray()) {
            if (!Character.isDigit(c)) return RED;
        }
        return GREEN;
    }

    public boolean checkCollision() {
        return CheckCollisionPointRec(GetMousePosition(), hitbox);
    }

    public void setString(StringBuilder string) {
        this.string = string;
    }

    public StringBuilder getString() {
        return string;
    }
}

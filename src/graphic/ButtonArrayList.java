package graphic;

import enums.ProgramState;

import java.awt.*;
import java.util.ArrayList;

public class ButtonArrayList extends ArrayList {

    private ArrayList<MenuButton> list;

    public ButtonArrayList() {
        super(0);
        list = new ArrayList<>();
    }

    public void add(MenuButton button) {
        list.add(button);
    }

    public MenuButton get(String name) {
        for (var button : list) {
            if (name.equals(button.getName())) return button;
        }
        return null;
    }

    public boolean hasHovered(ProgramState programState) {
        for (var button : list) {
            if (button.isHovering(programState)) return true;
        }
        return false;
    }
}

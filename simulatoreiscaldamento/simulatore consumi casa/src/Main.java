import casa.Casa;
import home_appliances.eletrico.*;
import home_appliances.idronici.*;
import management.MenuManager;

import static com.raylib.Raylib.*;

void main() {

    SetTargetFPS(60);
    InitWindow(720,480,"Ciola");

    MenuManager menuManager = new MenuManager();
    menuManager.run();
}

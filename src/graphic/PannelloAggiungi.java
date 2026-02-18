package graphic;

import enums.AddOption;
import enums.ProgramState;
import home_appliances.*;
import org.json.JSONObject;
import simulation.Casa;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;

import static com.raylib.Raylib.LoadTexture;

public class PannelloAggiungi {

    private String nome;
    private Rectangle hitbox;
    private Rectangle backButtonHitbox;

    private Rectangle panelAsciugatrice;
    private Rectangle panelFrigo;
    private Rectangle panelLavastoviglie;
    private Rectangle panelLavatrice;

    private AddOption addOption;

    private CategoryMenu categoryMenu1;
    private CategoryMenu categoryMenu2;

    private Texture pannello;
    private Texture backButton;
    private Texture smallPanel;
    private Texture largePanel;

    private boolean hovering;

    private Float timer;
    private boolean aggiunto;

    private Rectangle panelCaldaiaElettrica;
    private Rectangle panelCaminettiElettrici;
    private Rectangle panelPannelliRadianti;
    private Rectangle panelPiastre;

    private Rectangle panelPompeCalore;
    private Rectangle panelRadiatori;
    private Rectangle panelRiscaldamentoPavimento;
    private Rectangle panelStufe;

    private Rectangle panelTermoventilatori;
    private Rectangle panelTermocoperte;
    private Rectangle panelVentilconvettoriElettrici;
    private Rectangle panelCaldaiaH2O;

    private Rectangle panelPompaCaloreH2O;
    private Rectangle panelRiscaldamentoPavimentoH2O;
    private Rectangle panelTermosifoni;
    private Rectangle panelVentilconvettoriH2O;

    public PannelloAggiungi(String nome) {
        this.nome = nome;

        hitbox = new Rectangle();
        hitbox.x(100);
        hitbox.y(200);
        hitbox.width(900);
        hitbox.height(700);

        backButtonHitbox = new Rectangle();
        backButtonHitbox.x(hitbox.x());
        backButtonHitbox.y(200);
        backButtonHitbox.width(100);
        backButtonHitbox.height(100);

        var r1 = new Rectangle();
        r1.x(280); r1.y(400); r1.width(150); r1.height(150);
        categoryMenu1 = new CategoryMenu("Elettrodomestici", r1);

        var r2 = new Rectangle();
        r2.x(580); r2.y(400); r2.width(150); r2.height(150);
        categoryMenu2 = new CategoryMenu("Riscaldamento", r2);

        panelAsciugatrice = new Rectangle(); panelAsciugatrice.x(180); panelAsciugatrice.y(400); panelAsciugatrice.width(150); panelAsciugatrice.height(150);
        panelFrigo = new Rectangle(); panelFrigo.x(350); panelFrigo.y(400); panelFrigo.width(150); panelFrigo.height(150);
        panelLavastoviglie = new Rectangle(); panelLavastoviglie.x(520); panelLavastoviglie.y(400); panelLavastoviglie.width(150); panelLavastoviglie.height(150);
        panelLavatrice = new Rectangle(); panelLavatrice.x(690); panelLavatrice.y(400); panelLavatrice.width(150); panelLavatrice.height(150);

        panelCaldaiaElettrica = new Rectangle(); panelCaldaiaElettrica.x(220); panelCaldaiaElettrica.y(200); panelCaldaiaElettrica.width(130); panelCaldaiaElettrica.height(130);
        panelCaminettiElettrici = new Rectangle(); panelCaminettiElettrici.x(370); panelCaminettiElettrici.y(200); panelCaminettiElettrici.width(130); panelCaminettiElettrici.height(130);
        panelPannelliRadianti = new Rectangle(); panelPannelliRadianti.x(520); panelPannelliRadianti.y(200); panelPannelliRadianti.width(130); panelPannelliRadianti.height(130);
        panelPiastre = new Rectangle(); panelPiastre.x(670); panelPiastre.y(200); panelPiastre.width(130); panelPiastre.height(130);

        panelPompeCalore = new Rectangle(); panelPompeCalore.x(220); panelPompeCalore.y(350); panelPompeCalore.width(130); panelPompeCalore.height(130);
        panelRadiatori = new Rectangle(); panelRadiatori.x(370); panelRadiatori.y(350); panelRadiatori.width(130); panelRadiatori.height(130);
        panelRiscaldamentoPavimento = new Rectangle(); panelRiscaldamentoPavimento.x(520); panelRiscaldamentoPavimento.y(350); panelRiscaldamentoPavimento.width(130); panelRiscaldamentoPavimento.height(130);
        panelStufe = new Rectangle(); panelStufe.x(670); panelStufe.y(350); panelStufe.width(130); panelStufe.height(130);

        panelTermoventilatori = new Rectangle(); panelTermoventilatori.x(220); panelTermoventilatori.y(500); panelTermoventilatori.width(130); panelTermoventilatori.height(130);
        panelTermocoperte = new Rectangle(); panelTermocoperte.x(370); panelTermocoperte.y(500); panelTermocoperte.width(130); panelTermocoperte.height(130);
        panelVentilconvettoriElettrici = new Rectangle(); panelVentilconvettoriElettrici.x(520); panelVentilconvettoriElettrici.y(500); panelVentilconvettoriElettrici.width(130); panelVentilconvettoriElettrici.height(130);
        panelCaldaiaH2O = new Rectangle(); panelCaldaiaH2O.x(670); panelCaldaiaH2O.y(500); panelCaldaiaH2O.width(130); panelCaldaiaH2O.height(130);

        panelPompaCaloreH2O = new Rectangle(); panelPompaCaloreH2O.x(220); panelPompaCaloreH2O.y(650); panelPompaCaloreH2O.width(130); panelPompaCaloreH2O.height(130);
        panelRiscaldamentoPavimentoH2O = new Rectangle(); panelRiscaldamentoPavimentoH2O.x(370); panelRiscaldamentoPavimentoH2O.y(650); panelRiscaldamentoPavimentoH2O.width(130); panelRiscaldamentoPavimentoH2O.height(130);
        panelTermosifoni = new Rectangle(); panelTermosifoni.x(520); panelTermosifoni.y(650); panelTermosifoni.width(130); panelTermosifoni.height(130);
        panelVentilconvettoriH2O = new Rectangle(); panelVentilconvettoriH2O.x(670); panelVentilconvettoriH2O.y(650); panelVentilconvettoriH2O.width(130); panelVentilconvettoriH2O.height(130);


        pannello = LoadTexture("sprites/menu/Panel.png");
        backButton = LoadTexture("sprites/menu/back.png");
        smallPanel = LoadTexture("sprites/menu/smallPanel.png");
        largePanel = LoadTexture("sprites/menu/LargePanel.png");

        addOption = AddOption.NULL;
        hovering = false;
        timer = 0f;
        aggiunto = false;
    }

    public ProgramState update() {
        if (CheckCollisionPointRec(GetMousePosition(), backButtonHitbox) && IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            addOption = AddOption.NULL;
            return ProgramState.RUNNING;
        }
        return ProgramState.ADD;
    }

    public void draw(Casa casa) {
        DrawTexture(pannello, (int)hitbox.x(), (int)hitbox.y(), WHITE);
        DrawText(nome, (int)hitbox.x()+290, (int)hitbox.y()+45, 25, WHITE);
        DrawTexture(backButton, (int)backButtonHitbox.x(), (int)backButtonHitbox.y(), WHITE);

        if (CheckCollisionPointRec(GetMousePosition(), backButtonHitbox)) {
            hovering = true;
            SetMouseCursor(MOUSE_CURSOR_POINTING_HAND);
        } else {
            hovering = false;
        }

        if (aggiunto) {
            DrawText("Impianto Aggiunto", 420, 600, 20, DARKGREEN);
            timer += GetFrameTime();
            if (timer >= 2f) {
                timer = 0f;
                aggiunto = false;
            }
        }

        switch (addOption) {
            case NULL: drawCategories(); break;
            case ELETTRODOMESTICI: drawElettrodomestici(casa); break;
            case RISCALDAMENTO: drawRiscaldamento(casa); break;
            default:
        }
    }

    public void drawCategories() {
        categoryMenu1.draw();
        categoryMenu2.draw();
        if (categoryMenu1.checkCollision() || categoryMenu2.checkCollision()) SetMouseCursor(MOUSE_CURSOR_POINTING_HAND);
        else if (!hovering) SetMouseCursor(MOUSE_CURSOR_DEFAULT);
        if (categoryMenu1.checkCollision() && IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            addOption = AddOption.ELETTRODOMESTICI;
        }
        if (categoryMenu2.checkCollision() && IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            addOption = AddOption.RISCALDAMENTO;
        }
    }

    public void drawElettrodomestici(Casa casa) {

        DrawTexture(smallPanel, (int)panelAsciugatrice.x(), (int)panelAsciugatrice.y(), WHITE);
        DrawText("Asciugatrice", (int)(panelAsciugatrice.x() + 10), (int)(panelAsciugatrice.y() + 65), 20, WHITE);
        if (CheckCollisionPointRec(GetMousePosition(), panelAsciugatrice) && IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            casa.addAppliance(new Asciugatrice(3f));
            aggiunto = true;
        }

        DrawTexture(smallPanel, (int)panelFrigo.x(), (int)panelFrigo.y(), WHITE);
        DrawText("Frigo", (int)(panelFrigo.x() + 45), (int)(panelFrigo.y() + 65), 20, WHITE);
        if (CheckCollisionPointRec(GetMousePosition(), panelFrigo) && IsMouseButtonPressed(MOUSE_BUTTON_LEFT)){
            casa.addAppliance(new Frigo(0.1f));
            aggiunto = true;
        }

        DrawTexture(smallPanel, (int)panelLavastoviglie.x(), (int)panelLavastoviglie.y(), WHITE);
        DrawText("Lavastoviglie", (int)(panelLavastoviglie.x() + 10), (int)(panelLavastoviglie.y() + 65), 20, WHITE);
        if (CheckCollisionPointRec(GetMousePosition(), panelLavastoviglie) && IsMouseButtonPressed(MOUSE_BUTTON_LEFT)){
            casa.addAppliance(new Lavastoviglie(1.2f));
            aggiunto = true;
        }

        DrawTexture(smallPanel, (int)panelLavatrice.x(), (int)panelLavatrice.y(), WHITE);
        DrawText("Lavatrice", (int)(panelLavatrice.x() + 20), (int)(panelLavatrice.y() + 65), 20, WHITE);
        if (CheckCollisionPointRec(GetMousePosition(), panelLavatrice) && IsMouseButtonPressed(MOUSE_BUTTON_LEFT)){
            casa.addAppliance(new Lavatrice(2.0f));
            aggiunto = true;
        }

        if (CheckCollisionPointRec(GetMousePosition(), panelLavatrice) || CheckCollisionPointRec(GetMousePosition(), panelLavastoviglie) || CheckCollisionPointRec(GetMousePosition(), panelFrigo) || CheckCollisionPointRec(GetMousePosition(), panelAsciugatrice)) SetMouseCursor(MOUSE_CURSOR_POINTING_HAND);
        else if (!hovering) SetMouseCursor(MOUSE_CURSOR_DEFAULT);
    }

    private boolean drawPanel(Rectangle r,String text,Runnable action){
        DrawTexture(smallPanel,(int)r.x(),(int)r.y(),WHITE);
        DrawText(text,(int)r.x()+10,(int)r.y()+55,18,WHITE);

        if(CheckCollisionPointRec(GetMousePosition(),r)){
            if(IsMouseButtonPressed(MOUSE_BUTTON_LEFT)){
                action.run();
                aggiunto=true;
            }
            return true;
        }
        return false;
    }

    public void drawRiscaldamento(Casa casa) {
        boolean hover=false;
        DrawTexture(largePanel, (int)hitbox.x(), (int)hitbox.y()-150, WHITE);
        DrawText(nome, (int)hitbox.x()+200, (int)hitbox.y()+60-150, 40, WHITE);
        DrawTexture(backButton, (int)backButtonHitbox.x(), (int)backButtonHitbox.y(), WHITE);

        hover |= drawPanel(panelCaldaiaElettrica,"CaldaiaEl",()->casa.addAppliance(new Riscaldamento("CaldaiaElettrica", 0.6, 15.0f)));
        hover |= drawPanel(panelCaminettiElettrici,"Caminetti",()->casa.addAppliance(new Riscaldamento("CaminettoElettrico", 1.3, 18.0f)));
        hover |= drawPanel(panelPannelliRadianti,"Pannelli",()->casa.addAppliance(new Riscaldamento("PannelloRadiante", 0.7, 13.0f)));
        hover |= drawPanel(panelPiastre,"Piastre",()->casa.addAppliance(new Riscaldamento("Piastra", 1.0, 14.0f)));

        hover |= drawPanel(panelPompeCalore,"Pompe",()->casa.addAppliance(new Riscaldamento("PompaDiCalore", 1.2, 10.0f)));
        hover |= drawPanel(panelRadiatori,"Radiatori",()->casa.addAppliance(new Riscaldamento("Radiatore", 1.5, 16.0f)));
        hover |= drawPanel(panelRiscaldamentoPavimento,"Pavimento",()->casa.addAppliance(new Riscaldamento("RiscaldamentoPavimento", 0.12, 19.0f)));
        hover |= drawPanel(panelStufe,"Stufe",()->casa.addAppliance(new Riscaldamento("Stufa", 1.5, 15.0f)));

        hover |= drawPanel(panelTermoventilatori,"Termovent",()->casa.addAppliance(new Riscaldamento("Termoventilatore", 1.6, 15.0f)));
        hover |= drawPanel(panelTermocoperte,"Coperte",()->casa.addAppliance(new Riscaldamento("Termocoperta", 0.05, 18.0f)));
        hover |= drawPanel(panelVentilconvettoriElettrici,"VentEl",()->casa.addAppliance(new Riscaldamento("VentilConvettoreElettrico", 1.7, 12.0f)));
        hover |= drawPanel(panelCaldaiaH2O,"CaldaiaH2O",()->casa.addAppliance(new Riscaldamento("CaldaiaH20", 7.0, 16.0f)));

        hover |= drawPanel(panelPompaCaloreH2O,"PompaH2O",()->casa.addAppliance(new Riscaldamento("PompaH2O", 2.3, 19.0f)));
        hover |= drawPanel(panelRiscaldamentoPavimentoH2O,"PavH2O",()->casa.addAppliance(new Riscaldamento("RiscaldamentoPavimentoH20", 0.05, 18.0f)));
        hover |= drawPanel(panelTermosifoni,"Termosifoni",()->casa.addAppliance(new Riscaldamento("Termosifone", 7.0, 17.0f)));
        hover |= drawPanel(panelVentilconvettoriH2O,"VentH2O",()->casa.addAppliance(new Riscaldamento("VentilconvettoreH2O", 0.07, 14.0f)));

        if(hover) SetMouseCursor(MOUSE_CURSOR_POINTING_HAND);
        else if(!hovering) SetMouseCursor(MOUSE_CURSOR_DEFAULT);
    }
}
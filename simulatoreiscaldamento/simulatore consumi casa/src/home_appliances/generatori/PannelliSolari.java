package home_appliances.generatori;

import time.Clock;

public class PannelliSolari {
    private double guadagnoTotale;

    public void tick(Clock clock){
        switch (clock.getHour()){
            case 0:guadagnoTotale += 0; break;
            case 1:guadagnoTotale += 0;break;
            case 2:guadagnoTotale += 0;break;
            case 3:guadagnoTotale += 0;break;
            case 4:guadagnoTotale += 0;break;
            case 5:guadagnoTotale += 0;break;
            case 6:guadagnoTotale += 0.05;break;
            case 7:guadagnoTotale += 0.10;break;
            case 8:guadagnoTotale += 0.18;break;
            case 9:guadagnoTotale += 0.28;break;
            case 10:guadagnoTotale += 0.35;break;
            case 11:guadagnoTotale += 0.38;break;
            case 12:guadagnoTotale += 0.40;break;
            case 13:guadagnoTotale += 0.38;break;
            case 14:guadagnoTotale += 0.35;break;
            case 15:guadagnoTotale += 0.30;break;
            case 16:guadagnoTotale += 0.22;break;
            case 17:guadagnoTotale += 0.15;break;
            case 18:guadagnoTotale += 0.08;break;
            case 19:guadagnoTotale += 0.03;break;
            case 20:guadagnoTotale += 0;break;
            case 21:guadagnoTotale += 0;break;
            case 22:guadagnoTotale += 0;break;
            case 23:guadagnoTotale += 0;break;
            default:break;
        }
    }

    public double getGuadagnoTotale() {
        return guadagnoTotale;
    }
}

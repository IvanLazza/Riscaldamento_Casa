package graphic;

import enums.ProgramState;

public class StartConfig {

    public ProgramState programState;
    public Integer giorni;
    public Integer durata;

    public StartConfig(ProgramState programState, Integer giorni, Integer durata) {
        this.programState = programState;
        this.giorni = giorni;
        this.durata = durata;
    }
}

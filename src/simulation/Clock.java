package simulation;

import enums.SimulationStatus;

public class Clock {

    private int hour;
    private int day;
    private int totalTick;
    private int simulationDuration;
    private float temperature;
    private Season season;

    private Integer dayDuration;
    private Float secondsPerHour;
    private boolean running;
    private Float accumulator;

    public Clock(int simulationDuration) {
        this.hour = 0;
        this.day = 0;
        this.simulationDuration = simulationDuration;
        this.temperature = 15;
        season = Season.SPRING;
        totalTick = 0;

        accumulator = 0f;
        dayDuration = 30;
        secondsPerHour = dayDuration/24f;
        running = false;
    }

    public SimulationStatus Start(SimulationStatus simulationStatus) {
        simulationStatus = SimulationStatus.RUNNING;
        running = true;
        return simulationStatus;
    }
    public SimulationStatus Stop(SimulationStatus simulationStatus) {
        running = false;
        simulationStatus = SimulationStatus.FINISHED;
        return simulationStatus;
    }

    public SimulationStatus update(float deltaTime, SimulationStatus simulationStatus) {
        if (!running || simulationDuration <= 0) return simulationStatus;

        accumulator += deltaTime;

        if (accumulator >= secondsPerHour) {
            accumulator = 0f;
            simulationStatus = tick(simulationStatus);
        }
        return simulationStatus;
    }

    public SimulationStatus tick(SimulationStatus simulationStatus) {
        simulationStatus = increment(simulationStatus);
        totalTick++;
        return simulationStatus;
    }

    private SimulationStatus increment(SimulationStatus simulationStatus) {
        hour += 1;
        if (hour >= 24) {
            hour = 0;
            day++;
            if (simulationDuration > 0) {
                simulationDuration--;
            }
            if (simulationDuration == 0) {
                simulationStatus = Stop(simulationStatus);
            }
        }

        if (hour == 0 && day % 10 == 0 && day != 0){
            switch (season) {
                case SPRING -> season = Season.SUMMER;
                case SUMMER -> season = Season.AUTUMN;
                case AUTUMN -> season = Season.WINTER;
                case WINTER -> season = Season.SPRING;
            }
        }
        switch (season) {
            case SPRING : if (hour > 7 && hour <= 12) {
                temperature += 0.5f;
            } else if (hour > 12 && hour <= 17) {
                temperature += 1.2f;
            } else if (hour > 17 && hour <= 21) {
                temperature -= 0.3f;
            } else {
                temperature -= 0.7f;
            }
                break;
            case SUMMER : if (hour > 7 && hour <= 12) {
                temperature += 1f;
            } else if (hour > 12 && hour <= 17) {
                temperature += 2f;
            } else if (hour > 17 && hour <= 21) {
                temperature -= 0.4f;
            } else {
                temperature -= 0.8f;
            }
                break;
            case AUTUMN : if (hour > 7 && hour <= 12) {
                temperature += 0.35f;
            } else if (hour > 12 && hour <= 17) {
                temperature += 0.7f;
            } else if (hour > 17 && hour <= 21) {
                temperature -= 0.7f;
            } else {
                temperature -= 1.2f;
            }
                break;
            case WINTER : if (hour > 7 && hour <= 12) {
                temperature += 0.2f;
            } else if (hour > 12 && hour <= 17) {
                temperature += 0.55f;
            } else if (hour > 17 && hour <= 21) {
                temperature -= 0.9f;
            } else {
                temperature -= 1.5f;
            }
                break;
        }
        return simulationStatus;
    }

    public void setDayDuration(Integer dayDuration) {
        this.dayDuration = dayDuration;
        secondsPerHour = dayDuration/24f;
    }

    public boolean isSimulationRunning() {
        return simulationDuration > 0 && running;
    }

    public int getSimulationDuration() {
        return simulationDuration;
    }

    public void setSimulationDuration(int simulationDuration) {
        this.simulationDuration = simulationDuration;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTime() {
        return day * 24 + hour;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public float getTemperature() {
        return temperature;
    }

    public int getDuration() {
        return simulationDuration + day;
    }

    public void setDuration(int simulationDuration) {
        this.simulationDuration = simulationDuration;
    }

    public boolean hasHourPassed() {
        return totalTick % 1 == 0;
    }

    @Override
    public String toString() {
        return "Giorno " + day + " - " + String.format("%02d", hour) + ":00";
    }
}
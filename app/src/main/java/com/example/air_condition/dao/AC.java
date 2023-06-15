package com.example.air_condition.dao;

public class AC {

    private String ac;
    private int temperature;
    private boolean status;
    private String fanOption;
    private String directionOptions;
    private String modeOptions;
    private boolean eco;
    private boolean auto;
    private boolean sleep;

    public AC(String ac, int temperature, boolean status, String fanOption, String directionOptions, String modeOptions, boolean eco, boolean auto, boolean sleep) {
        this.ac = ac;
        this.temperature = temperature;
        this.status = status;
        this.fanOption = fanOption;
        this.directionOptions = directionOptions;
        this.modeOptions = modeOptions;
        this.eco = eco;
        this.auto = auto;
        this.sleep = sleep;
    }

    public AC() {
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus() {
        this.status = !this.status;
    }

    public String getFanOption() {
        return fanOption;
    }

    public void setFanOption(String fanOption) {
        this.fanOption = fanOption;
    }

    public String getDirectionOptions() {
        return directionOptions;
    }

    public void setDirectionOptions(String directionOptions) {
        this.directionOptions = directionOptions;
    }

    public String isModeOptions() {
        return modeOptions;
    }

    public void setModeOptions(String modeOptions) {
        this.modeOptions = modeOptions;
    }

    public boolean isEco() {
        return eco;
    }

    public void setEco() {
        this.eco = !this.eco;
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto() {
        this.auto = !this.auto;
    }

    public boolean isSleep() {
        return sleep;
    }

    public void setSleep() {
        this.sleep = !this.sleep;
    }

    public int getFahrenheit() {
        return (this.temperature * 9/5) + 32;
    }

    public void setFahrenheit(int temp) {
        this.temperature = (temp - 32) * 5/9;
    }

    @Override
    public String toString() {
        return "AC{" +
                "ac='" + ac + '\'' +
                ", temperature=" + temperature +
                ", status=" + status +
                ", fanOption='" + fanOption + '\'' +
                ", directionOptions='" + directionOptions + '\'' +
                ", modeOptions=" + modeOptions +
                ", eco=" + eco +
                ", sleep=" + sleep +
                '}';
    }
}

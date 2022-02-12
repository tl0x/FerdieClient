package me.tl0x.ferdieclient.base.config;

public class IntegerConfig {

    public final String name;
    public final int min;
    public final int max;
    public int value;

    public IntegerConfig(String name, int min, int max, int value) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.value = value;
    }

    public int getMax() {
        return max;
    }

    public int getValue() {
        return value;
    }

    public int getMin() {
        return min;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

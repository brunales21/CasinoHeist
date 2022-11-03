package com.casino;

public class Case {
    private int number;
    private Color color;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setCase(int number, Color color) {
        this.number = number;
        this.color = color;
    }

    public Case(int number, int colorIndex) {
        this.number = number;
        if (colorIndex == 0) {
            this.color = Color.B;
        } else {
            this.color = Color.R;
        }
    }

    public Case(int number, Color color) {
        this.number = number;
        this.color = color;
    }

    public Case(int number){
        this.number = number;
        this.color = Color.Y;
    }

    @Override
    public String toString() {
        return String.format("[%d %s]", this.number, this.color);
    }
}

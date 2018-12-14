package com.example.findfriends;

public class Answer {
    private String text;
    private int value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", value=" + value +
                '}';
    }

    public Answer(String text, int value) {
        this.text = text;
        this.value = value;
    }
}

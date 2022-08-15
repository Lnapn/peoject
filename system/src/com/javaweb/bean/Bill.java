package com.javaweb.bean;

import java.util.Objects;

public class Bill {
    String no;
    String date;
    String type;
    String amount;
    String consumer;

    public Bill() {
    }

    public Bill(String no, String date, String type, String amount, String consumer) {
        this.no = no;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.consumer = consumer;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Objects.equals(no, bill.no) && Objects.equals(date, bill.date) && Objects.equals(type, bill.type) && Objects.equals(amount, bill.amount) && Objects.equals(consumer, bill.consumer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, date, type, amount, consumer);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "no='" + no + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", amount='" + amount + '\'' +
                ", consumer='" + consumer + '\'' +
                '}';
    }
}

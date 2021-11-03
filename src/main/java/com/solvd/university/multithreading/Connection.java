package com.solvd.university.multithreading;

import java.util.Objects;

public class Connection implements Runnable {

    private final String name;

    public Connection(String name) {
        this.name = name;
    }

    public void create() throws InterruptedException {
        Thread.sleep(100);
        System.out.printf("- Method #create() of Connection %s was called\n", this.name);
    }

    public void read() throws InterruptedException {
        Thread.sleep(200);
        System.out.printf("- Method #read() of Connection '%s' was called\n", this.name);
    }

    public void readAll() throws InterruptedException {
        Thread.sleep(300);
        System.out.printf("- Method #readAll() of Connection '%s' was called\n", this.name);
    }

    public void update() throws InterruptedException {
        Thread.sleep(400);
        System.out.printf("- Method #update() of Connection '%s' was called\n", this.name);
    }

    public void delete() throws InterruptedException {
        Thread.sleep(50);
        System.out.printf("- Method #delete() of Connection '%s' was called\n", this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void run() {
        System.out.printf("Connection thread  %s was started using <Runnable>", this.name);
    }
}

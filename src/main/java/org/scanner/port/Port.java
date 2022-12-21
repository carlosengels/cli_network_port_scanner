package org.scanner.port;

public class Port {
    int number;
    Status status;


    public Port( int number, Status status) {
        this.number = number;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.format("%d - %s\n", getNumber(), getStatus());
    }
}

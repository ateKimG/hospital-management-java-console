package aPackage;

import java.util.Scanner;

// Patient class
class Patient implements Comparable<Patient> {
    private String name;
    private String symptoms;
    private int priority; // Lower number = higher priority

    public Patient(String name, String symptoms, int priority) {
        this.name = name;
        this.symptoms = symptoms;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Patient other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return name + " (Priority: " + priority + ", Symptoms: " + symptoms + ")";
    }
}

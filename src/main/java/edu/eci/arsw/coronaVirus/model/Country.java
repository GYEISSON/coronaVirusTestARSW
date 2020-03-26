package edu.eci.arsw.coronaVirus.model;

public class Country implements Comparable {
    private String name;
    private int deaths;
    private int confirmed;
  
    private int recovered;

    public Country(String name,int deaths,int confirmed,int recovered){
        this.name=name;
        this.confirmed=confirmed;
        this.deaths=deaths;
        this.recovered=recovered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setCured(int cured) {
        this.recovered = cured;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

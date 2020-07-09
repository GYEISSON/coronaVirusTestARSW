package edu.eci.arsw.covid19arsw.CoronavirusStatsModel;

public class Country implements Comparable {
    private String name;
    private int deaths;
    private int confirmed;
    private int recovered;

    public Country(String name, int deaths, int confirmed, int recovered) {
        this.name = name;
        this.setConfirmed(confirmed);
        this.setDeaths(deaths);
        this.setRecovered(recovered);
    }

    public String getName() {
        return name;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }
}

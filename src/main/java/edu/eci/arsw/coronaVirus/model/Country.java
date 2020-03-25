package edu.eci.arsw.coronaVirus.model;

public class Country {
    private String name;
    private int deaths;
    private int infected;
  
    private int cured;

    public Country(String name,int deaths,int infected,int cured){
        this.name=name;
        this.infected=infected;
        this.deaths=deaths;
        this.cured=cured;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInfected() {
        return infected;
    }

    public void setInfected(int infected) {
        this.infected = infected;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getCured() {
        return cured;
    }

    public void setCured(int cured) {
        this.cured = cured;
    }
}

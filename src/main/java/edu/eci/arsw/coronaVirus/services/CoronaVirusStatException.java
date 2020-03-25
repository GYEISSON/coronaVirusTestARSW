package edu.eci.arsw.coronaVirus.services;

public class CoronaVirusStatException extends Exception{
    public static final String noSeEncontraronDatos = "No se pudo encontrar paises";
    public CoronaVirusStatException(String msj){
        super(msj);
    }
}

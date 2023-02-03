package com.example.aviones.models;

import java.util.Observable;
import java.util.Random;

public class Avion extends Observable implements Runnable {
    private Vector vector;
    private boolean estado;

    public Avion() {
        estado=true;
    }
    public void posicion(Vector vector){
        this.vector=vector;

    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public void run() {
        while (estado){
            vector.setX(vector.getX()+30);
            setChanged();
            notifyObservers(vector);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}

package com.example.aviones.models;

import java.util.Observable;


public class Helicoptero extends Observable implements Runnable {

    private Vector vector;
    private boolean estado;
    public void posicion(Vector vector){
        this.vector=vector;

    }

    public Helicoptero() {
        estado=true;

    }

    public boolean isEstado() {
        return this.estado=estado;
    }

    @Override
    public void run() {
        while (estado){
            vector.setX(vector.getX()-35);
            setChanged();
            notifyObservers(vector);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

    }

    public void setEstado(boolean estado) {
        this.estado=estado;
    }
}

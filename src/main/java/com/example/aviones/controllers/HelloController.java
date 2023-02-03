package com.example.aviones.controllers;

import com.example.aviones.models.Helicoptero;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import com.example.aviones.models.Avion;
import com.example.aviones.models.Vector;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class HelloController implements Observer {


        private Avion avion;
        private Helicoptero helicoptero;
        private Helicoptero helicoptero2;
        private Helicoptero helicoptero3;
        private Random random;
        @FXML
        private ImageView avionAliado;

        @FXML
        private AnchorPane cielo;
        @FXML
        private Label end;
        @FXML
        private ImageView enemigo;

        @FXML
        private ImageView enemigo2;

        @FXML
        private ImageView enemigo3;


        public void initialize() {
                helicoptero = new Helicoptero();
                helicoptero.posicion(new Vector(1, 838, 285));
                helicoptero.addObserver(this);
                new Thread(helicoptero).start();

                helicoptero2 = new Helicoptero();
                helicoptero2.posicion(new Vector(2, 1058, 210));
                helicoptero2.addObserver(this);
                new Thread(helicoptero2).start();

                helicoptero3 = new Helicoptero();
                helicoptero3.posicion(new Vector(3, 1058, 360));
                helicoptero3.addObserver(this);
                new Thread(helicoptero3).start();

//                enemigo2 = new ImageView(new Image(getClass().getResourceAsStream("/imgs/helicoptero.gif")));
//                enemigo2.setLayoutX(1058);
//                enemigo2.setLayoutY(485);
//                enemigo2.setFitWidth(150);
//                enemigo2.setFitHeight(150);
//                cielo.getChildren().add(enemigo2);



        }

        @FXML
        void movimientoAvion(KeyEvent event) {
                if(event.getCode() == KeyCode.W) {

                        if (avionAliado.getLayoutY()<0){
                                avionAliado.setLayoutY(avionAliado.getLayoutY());
                        }else{
                                avionAliado.setLayoutY(avionAliado.getLayoutY()-15);
                        }
                }
                if(event.getCode() == KeyCode.S) {

                        if (avionAliado.getLayoutY()>560){
                                avionAliado.setLayoutY(avionAliado.getLayoutY());
                        }else{
                                avionAliado.setLayoutY(avionAliado.getLayoutY()+15);
                        }
                }


        }

        @Override
        public void update(Observable o, Object arg) {
                random = new Random(System.currentTimeMillis());
                Vector vector=(Vector)arg;

                switch (vector.getId()){
                        case 1:
                                Platform.runLater(()-> enemigo.setLayoutX(vector.getX()));
                                System.out.println(vector.getId());
                                break;
                        case 2:
                                Platform.runLater(()-> enemigo2.setLayoutX(vector.getX()));
                                System.out.println(vector.getId());
                                break;
                        case 3:
                                Platform.runLater(()-> enemigo3.setLayoutX(vector.getX()));
                                System.out.println(vector.getId());
                                break;
                        }


                if(avionAliado.getBoundsInParent().intersects(enemigo.getBoundsInParent())) {
                        //|| avionAliado.getBoundsInParent().intersects(enemigo2.getBoundsInParent())|| avionAliado.getBoundsInParent().intersects(enemigo2.getBoundsInParent())|| avionAliado.getBoundsInParent().intersects(enemigo3.getBoundsInParent())
                        helicoptero.setEstado(false);
                        helicoptero2.setEstado(false);
                        helicoptero3.setEstado(false);
                        end.setVisible(true);
                }



                if (enemigo.getLayoutX() <= -130){
                        int aleatorio = random.nextInt((520));
                        int aleatorio2 = random.nextInt((520));
                        int aleatorio3 = random.nextInt((520));

                        this.helicoptero.setEstado(false);
                        this.enemigo.setVisible(false);

                        this.helicoptero2.setEstado(false);
                        this.enemigo2.setVisible(false);

                        this.helicoptero3.setEstado(false);
                        this.enemigo3.setVisible(false);


                        if (!helicoptero.isEstado()) {
                                this.helicoptero.setEstado(true);
                                helicoptero.posicion(new Vector(1, 1400, aleatorio));
                                this.enemigo.setVisible(true);
                                enemigo.setLayoutY(aleatorio);
                                enemigo.setLayoutX(1400);

                                this.helicoptero2.setEstado(true);
                                helicoptero2.posicion(new Vector(2, 1400, aleatorio2));
                                this.enemigo2.setVisible(true);
                                enemigo2.setLayoutY(aleatorio);
                                enemigo2.setLayoutX(1400);

                                this.helicoptero3.setEstado(true);
                                helicoptero3.posicion(new Vector(3, 1400, aleatorio3));
                                this.enemigo3.setVisible(true);
                                enemigo3.setLayoutY(aleatorio);
                                enemigo3.setLayoutX(1400);

                        }
                }
        }
}
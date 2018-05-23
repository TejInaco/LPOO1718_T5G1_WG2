package com.velesc.game.InputHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.sun.corba.se.impl.presentation.rmi.ExceptionHandler;
import com.velesc.game.Sprites.CarroControlado;
import com.velesc.game.Sprites.Fisica;
import com.velesc.game.VelocidadeEscaldante;
import sun.management.Sensor;
import javax.naming.Context;

public class InputHandlerAndroid {
    CarroControlado player;

    private boolean gyroscopeAvail = Gdx.input.isPeripheralAvailable(Input.Peripheral.Gyroscope);
    //    private SensorManager sensorManager;
//    private Sensor sensor;
    private float gyroX = 0;
    private float gyroY = 0;
    Fisica fisica = new Fisica();

    public InputHandlerAndroid(CarroControlado player1, float dt) {
        this.player = player1;
        if(!gyroscopeAvail)throw new IllegalArgumentException();
  //      sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    public void inputmanager(){

        gyroX += Gdx.input.getGyroscopeX();
        gyroY += Gdx.input.getGyroscopeY();


        float velociActual = player.getLinearVelocity();
        Vector2 pos = player.getBodyCarroControlado().getPosition();

        if(gyroY > 0.5f && velociActual < fisica.MAX_VELOCITY.y ) { //player.b2body.getWorldCenter()
            player.getBodyCarroControlado().applyLinearImpulse(0, 80f, pos.x, pos.y, true);
            Gdx.input.vibrate(100);
        }
        if(gyroX > 0.5f) {//Left
            player.getBodyCarroControlado().applyLinearImpulse(1000f, 0, pos.x, pos.y, true);
            Gdx.input.vibrate(100);
        }
        if(gyroX < -0.5f) {//Right
            player.getBodyCarroControlado().applyLinearImpulse(-1000f, 0, pos.x, pos.y, true);
            Gdx.input.vibrate(100);
        }
        if(gyroY < -0.5f) {
            player.getBodyCarroControlado().applyLinearImpulse(0, -100f,pos.x,pos.y,true);
        }
    }

}

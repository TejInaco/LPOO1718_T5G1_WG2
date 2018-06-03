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
    private final float LIMITS_SENSIBILITY_POS = 0.5f;
    private final float LIMITS_SENSIBILITY_NEG = -0.5f;
    private final int VIBRATION_TIME = 100;

    private boolean gyroscopeAvail = Gdx.input.isPeripheralAvailable(Input.Peripheral.Gyroscope);

    private float gyroX = 0;
    private float gyroY = 0;
    Fisica fisica = new Fisica();

    public InputHandlerAndroid() {
        if(!gyroscopeAvail)throw new IllegalArgumentException();
  //      sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }
    /**
     * Controlos the car with the input given by the smart phone
     * @param player receives Object CarroControlado
     * */
    public void updateInputAndroid(CarroControlado player){

        gyroX += Gdx.input.getGyroscopeX();
        gyroY += Gdx.input.getGyroscopeY();


        float velociActual = player.getLinearVelocity();
        Vector2 pos = player.getBodyCarroControlado().getPosition();

        if(gyroY > LIMITS_SENSIBILITY_POS && velociActual < fisica.MAX_VELOCITY.y ) { //player.b2body.getWorldCenter()
            player.getBodyCarroControlado().applyLinearImpulse(0, 80f, pos.x, pos.y, true);
            Gdx.input.vibrate(VIBRATION_TIME);
        }
        if(gyroX > LIMITS_SENSIBILITY_POS) {//Left
            player.getBodyCarroControlado().applyLinearImpulse(1000f, 0, pos.x, pos.y, true);
            Gdx.input.vibrate(VIBRATION_TIME);
        }
        if(gyroX < LIMITS_SENSIBILITY_NEG) {//Right
            player.getBodyCarroControlado().applyLinearImpulse(-1000f, 0, pos.x, pos.y, true);
            Gdx.input.vibrate(VIBRATION_TIME);
        }
        if(gyroY < LIMITS_SENSIBILITY_NEG) {
            player.getBodyCarroControlado().applyLinearImpulse(0, -100f,pos.x,pos.y,true);
            Gdx.input.vibrate(VIBRATION_TIME);
        }
    }

}

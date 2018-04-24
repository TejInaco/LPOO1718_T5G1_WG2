package com.velesc.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.velesc.game.Scenes.EcraVizualizado;
import com.velesc.game.Tools.B2WorldCreator;
import com.velesc.game.VelocidadeEscaldante;
import com.velesc.game.Sprites.CarroControlado;

/**Play Screen*/
public class EcraJogo implements Screen {

    private VelocidadeEscaldante game;

    private OrthographicCamera gameCamera;
    private FitViewport gamePort;
    private EcraVizualizado hud;

    /**Tiled map variables                   ###  Render the map added more variables*/
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d Variables
    private World world;
    //Graphical representation of our bodies inside of our box2d
    private Box2DDebugRenderer b2dr;
    /**NOT IN USE YET*/
    //Texture texture;
    /***/
    private  CarroControlado player;



    /**Constructor*/
    public EcraJogo(VelocidadeEscaldante game){
        this.game = game;

        //adicionar textura jpg TODO
        //texture = new Texture(Gdx.files.internal("resources/estradaTeste.jpg"));
        //"resources/estradaTeste.jpg"

        /**Create cam used to follow the car through cam world*/
        gameCamera = new OrthographicCamera();
        /**Create FitViewPort to maintain virtual aspect ratio despite screen*/
        gamePort = new FitViewport(VelocidadeEscaldante.larguraVirtual/VelocidadeEscaldante.PPM, VelocidadeEscaldante.alturaVirtual/VelocidadeEscaldante.PPM, gameCamera);
        //Test with */
        //could use the Screenviewport*/
        /**Create our game HUD for scories/timers/level info*/
        hud = new EcraVizualizado(game.batch);

        /**Load our map and setup our map renderer*/
        maploader = new TmxMapLoader();
        /**TODO Aqui vai a textura do nivel 1 na string do maploader*/
        map = maploader.load("Level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/VelocidadeEscaldante.PPM);

        /**TODO certificar se a camera esta no sitio correcto*/
        /**initially set our gamCam to be centered correctly at the start of*/
        gameCamera.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2, 0);

        /***world class manages all physics entities, dynamic simulation, and asynchronous queries. The world
         also contains efficient memory management facilities */
        world = new World(new Vector2(0,-10),true);

        //allows the debug lines fo our  box2d world
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator( world, map );

        //Creates car in our world
        player = new CarroControlado(world);

    }

    @Override
    public void show() {

    }
    public void handleInput(float dt){
        //TODO for test purposes only to check the map texture . 2 lines blow
/*
        if(Gdx.input.isTouched() )
            gameCamera.position.x += 100 * dt;
*/
        //TODO end of the tests
        //Actuall controls of the car
        //TODO thing about adding && player.b2.body.getLinear Velocity().y <= 2
        //TODO Test the changes in the keys pressed, the deslocations on x and y
        //may be needed some negative configurations on the vector2
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            player.b2body.applyLinearImpulse(new Vector2(0,4f), player.b2body.getWorldCenter(),true );
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
            player.b2body.applyLinearImpulse(new Vector2(0.1f,0), player.b2body.getWorldCenter(),true);
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
            player.b2body.applyLinearImpulse(new Vector2(0.1f,0), player.b2body.getWorldCenter(),true);

    }
    /**
     * @param dt recives a float delta time
     * */
    public void update(float dt){
        handleInput(dt);

        /**Take a time step. This performs collision detection, integration, and constraint solution.
         Parameters:
         @param timeStep - the amount of time to simulate, this should not vary.
         @param velocityIterations - for the velocity constraint solver.
         @param positionIterations - for the position constraint solver.*/
        world.step(1/60f,6,2);
        /**tracking the car with game camera*/
        //TODO already change the code to corresponde the camera and the car in the yy axes - TEST
        gameCamera.position.y = player.b2body.getPosition().y;
        /**update our gamecam with correct coordinates after changes*/
        gameCamera.update();
        /**tell our renderer to draw only what our camera can see in our game world*/
        renderer.setView(gameCamera);
    }

    @Override
    public void render(float delta) {
        /**separate our update logic from render*/
        update(delta);

        /**Clear the game screen with Black*/
        /**TODO change the color of it*/
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    /** to render what camera can see*/
//    game.batch.setProjectionMatrix(gamecamera.combined);
    /***/
//    game.batch.begin();
//    game.batch.draw(texture,0,0);
//    game.batch.end();

        /**renderer our game map*/
        renderer.render();

        //TODO do  run to check if there is a green line around the objects
        /**Renderer our BOX2dDebugLines*/
        b2dr.render(world, gameCamera.combined);

        /**Set our batch to now draw what HUD camera sees*/
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
         map.dispose();
         renderer.dispose();
         world.dispose();
         b2dr.dispose();
         hud.dispose();
    }
}

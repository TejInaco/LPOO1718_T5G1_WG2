package com.velesc.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.velesc.game.Scenes.EcraVizualizado;
import com.velesc.game.Tools.B2WorldCreator;
import com.velesc.game.VelocidadeEscaldante;
import com.velesc.game.Sprites.CarroControlado;

/**Play Screen*/
public class EcraJogo implements Screen {

    private VelocidadeEscaldante game;

    private OrthographicCamera gameCamera;
    private FitViewport gamePort;
    private EcraVizualizado gameInformation;

    /**Tiled map variables                   ###  Render the map added more variables*/
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private AssetManager assetManager;
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

        /**Create cam used to follow the car through cam world*/
        gameCamera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        gameCamera.translate(gameCamera.viewportWidth/2,gameCamera.viewportHeight/2);
        //Numero of tiles - NO RESULT
        //gameCamera.setToOrtho(false, 64,64);
        //TODO camera.position.set(camera.viewPortWidth,...)
        /**Create FitViewPort to maintain virtual aspect ratio despite screen*/
        //Gdx.graphics.getWidth(), Gdx.graphics.getHeight();

        gamePort = new FitViewport(VelocidadeEscaldante.largura /VelocidadeEscaldante.PPM, VelocidadeEscaldante.altura /VelocidadeEscaldante.PPM , gameCamera);
        //Test with */
        //could use the Screenviewport*/

        gameInformation = new EcraVizualizado(game.batch);
        /**Load our map and setup our map renderer*/

        maploader = new TmxMapLoader();

        assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("android/assets/roadv6.tmx", TiledMap.class);
        assetManager.finishLoading();
        map = assetManager.get("android/assets/roadv6.tmx");

        // The unit scales tells the renderer how many pixels map to a single world unit. In the
        //bellow case 16 pixels would equal one unit.  The unit scale is a way to couple your rendering
        //coordinate system with the logical or worlxd coordinate system
        renderer = new OrthogonalTiledMapRenderer(map, 1/VelocidadeEscaldante.PPM);

        /**TODO certificar se a camera esta no sitio correcto*/
        /**initially set our gamCam to be centered correctly at the start of*/
        gameCamera.position.set(gamePort.getWorldWidth(),gamePort.getWorldHeight(), 0);
        //gamePort.getWorldWidth(),gamePort.getWorldHeight()
        /***world class manages all physics entities, dynamic simulation, and asynchronous queries. The world
         also contains efficient memory management facilities */
        world = new World(new Vector2(0,0),true);

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
/*    //TODO for test purposes only to check the map texture . 2 lines blow
        //no much diference in the movement of the camera position
        if(Gdx.input.isTouched() )
            gameCamera.position.x += 100 * dt;
*/
        //TODO end of the tests
        //Actual controls of the car
        //TODO thing about adding && player.b2.body.getLinear Velocity().y <= 2
        //behaviour acting a bit of weird with the direction change :(
        //may be needed some negative configurations on the vector2
        Vector2 vel = this.player.b2body.getLinearVelocity();
        Vector2 pos = this.player.b2body.getPosition();
        //TODO Retirar esta macro
        float MAX_VELOCITY = 100;

        if(Gdx.input.isKeyPressed(Input.Keys.UP) && vel.x < MAX_VELOCITY ) //player.b2body.getWorldCenter()
            player.b2body.applyLinearImpulse( 0,80f, pos.x, pos.y,true );
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            player.b2body.applyLinearImpulse(1000f,0 , pos.x, pos.y,true);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            player.b2body.applyLinearImpulse(-1000f,0,pos.x, pos.y,true);
    //Old movement equation        //new Vector2(-6000f,0), player.b2body.getWorldCenter(),true and
        // used isKeyJustPressed
    }
    /**
     * @param dt recives a float delta time
     * */
    public void update(float dt){
        //TODO add MOBILE working with Android
            handleInput(dt);

        /**Take a time step. This performs collision detection, integration, and constraint solution.
         Parameters:
         @param timeStep - the amount of time to simulate, this should not vary.
         @param velocityIterations - for the velocity constraint solver.
         @param positionIterations - for the position constraint solver.*/
        world.step(1/60f,6,2);
        /**tracking the car with game camera*/
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
        Gdx.gl.glClearColor(1,1,1,1);
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
        //there is a green line but not sure if actual is the map correct

        /**Renderer our BOX2dDebugLines*/
        b2dr.render(world, gameCamera.combined);
        /**Set our batch to now draw what HUD camera sees*/
        game.batch.setProjectionMatrix(gameInformation.stage.getCamera().combined);
        gameInformation.stage.draw();
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
         gameInformation.dispose();
    }
}

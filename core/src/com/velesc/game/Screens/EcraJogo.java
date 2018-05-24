package com.velesc.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.velesc.game.Assets;
import com.velesc.game.InputHandler.InputHandlerAndroid;
import com.velesc.game.InputHandler.InputHandlerDesktop;
import com.velesc.game.Sprites.CarroSecundario;
import com.velesc.game.Tools.B2WorldCreator;
import com.velesc.game.VelocidadeEscaldante;
import com.velesc.game.Sprites.CarroControlado;

/**
 * Main action
 **/
public class EcraJogo implements Screen {

    VelocidadeEscaldante game;

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

    public  CarroControlado player;
    public  CarroSecundario enemies;
    Assets assets = new Assets();

    /**Constructor*/
    public EcraJogo(VelocidadeEscaldante game){
        this.game = game;
        maploader = new TmxMapLoader();
        assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("android/assets/roadv6.tmx", TiledMap.class);
        assetManager.finishLoading();
        map = assetManager.get("android/assets/roadv6.tmx");


        /**Create cam used to follow the car through cam world*/
        gameCamera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        gameCamera.translate(gameCamera.viewportWidth/VelocidadeEscaldante.PPM,gameCamera.viewportHeight/VelocidadeEscaldante.PPM);
        /**Create FitViewPort to maintain virtual aspect ratio despite screen*/
        gamePort = new FitViewport(VelocidadeEscaldante.largura /VelocidadeEscaldante.PPM, VelocidadeEscaldante.altura /VelocidadeEscaldante.PPM , gameCamera);

        gameInformation = new EcraVizualizado(game.batch);
        /**Load our map and setup our map renderer*/


        // The unit scales tells the renderer how many pixels map to a single world unit. In the
        //bellow case 16 pixels would equal one unit.  The unit scale is a way to couple your rendering
        //coordinate system with the logical or worlxd coordinate system
        renderer = new OrthogonalTiledMapRenderer(map, 1/VelocidadeEscaldante.PPM);

        /**TODO certificar se a camera esta no sitio correcto*/
        /**initially set our gamCam to be centered correctly at the start of*/
        gameCamera.position.set(VelocidadeEscaldante.largura/VelocidadeEscaldante.PPM,gamePort.getWorldHeight(), 0);
        //gamePort.getWorldWidth(),gamePort.getWorldHeight()
        /***world class manages all physics entities, dynamic simulation, and asynchronous queries. The world
         also contains efficient memory management facilities */
        world = new World(new Vector2(0,0),true);

        //allows the debug lines fo our  box2d world
        //b2dr = new Box2DDebugRenderer();

        //new B2WorldCreator( world, map );

        //Creates car in our world
        player = new CarroControlado(world, 50, 50, assets.CAMARO.getWidth(), assets.CAMARO.getHeight());
//        enemies = new CarroSecundario(world);

    }

    @Override
    public void show() {

    }

    /**
     * Fuction to separate from the input from desktop and the input from android
     * @param dt recives a float delta time
     * */
    public void handleInput(float dt){

        if(game.IS_MOBILE == true){
            InputHandlerAndroid inputandroid = new InputHandlerAndroid( player, dt);
            inputandroid.inputmanager();
        }else{
            InputHandlerDesktop inputdesktop = new InputHandlerDesktop(player,dt);
            inputdesktop.inputManager();
        }

    }
    /**
     * @param dt recives a float delta time
     * */
    public void update(float dt){
        handleInput(dt);
        gameInformation.addVelocidade(player.carroPositionY());
        gameInformation.addPontos(player.carroPositionX());
        gameInformation.update(dt);
//        world.step(1/60f,16,2);
        /**tracking the car with game camera*/
        gameCamera.position.y = player.getBodyCarroControlado().getPosition().y/0.05f;
        /**update our gamecam with correct coordinates after changes*/
        gameCamera.update();
        /**tell our renderer to draw only what our camera can see in our game world*/
        renderer.setView(gameCamera);
    }
    public void updateEcraVizualiazado(){
        this.gameInformation.setWorldTimer(player.carroPositionY());
    }

    @Override
    public void render(float delta) {
        /**separate our update logic from render*/
        update(delta);
        /**Clear the game screen with Black*/
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /**renderer our game map*/
        renderer.render();

        /**Renderer our BOX2dDebugLines*/
        //b2dr.render(world, gameCamera.combined);
        /**Set our batch to now draw what HUD camera sees*/
        game.batch.setProjectionMatrix(gameInformation.stage.getCamera().combined);
        game.batch.begin();
        player.update();
        game.batch.end();
        gameInformation.stage.draw();
    }

    @Override
    public void resize(int width, int height){
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

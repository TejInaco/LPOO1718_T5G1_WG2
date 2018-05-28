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
import com.velesc.game.Sprites.CarrosSecundariosManager;
import com.velesc.game.VelocidadeEscaldante;
import com.velesc.game.Sprites.CarroControlado;

/**
 * Class responsible for the game itself
 **/
public class EcraJogo implements Screen {

    private static float GAME_CAMERA_RACIO = 0.05f;
    private final int CAR_INITIAL_POSITION_X = 50;
    private final int CAR_INITIAL_POSITION_Y = 50;

    VelocidadeEscaldante game;

    //Cameras and game information visualization
    private OrthographicCamera gameCamera;
    private FitViewport gamePort;
    private GameInformation gameInformation;

    //Tiled map Objects Variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private AssetManager assetManager;

    //World, bodies and textures variables in the game
    private World world;
    public  CarroControlado player;
    public  CarrosSecundariosManager enemies;
    Assets assets = new Assets();


    public EcraJogo(VelocidadeEscaldante game){
        this.game = game;

        this.maploader = new TmxMapLoader();
        setMapaJogo();
        this.map = assetManager.get("android/assets/roadv6.tmx");
        setCameraCarro();
        //Create FitViewPort to maintain virtual aspect ratio despite screen
        this.gamePort = new FitViewport(VelocidadeEscaldante.largura /VelocidadeEscaldante.PPM, VelocidadeEscaldante.altura /VelocidadeEscaldante.PPM , gameCamera);
        this.gameInformation = new GameInformation(game.batch);
        //Load our map and setup our map renderer
        this.renderer = new OrthogonalTiledMapRenderer(map, 1/VelocidadeEscaldante.PPM);
        setGameCameraPosition();
        this.world = new World(new Vector2(0,0),true);
        this.player = new CarroControlado(world, CAR_INITIAL_POSITION_X, CAR_INITIAL_POSITION_Y, assets.CAMARO.getWidth(), assets.CAMARO.getHeight());
        this.enemies = new CarrosSecundariosManager(world);
    }
    /**
     * Set Game camera center position
     * */
    public void setGameCameraPosition(){
        gameCamera.position.set(VelocidadeEscaldante.largura/VelocidadeEscaldante.PPM,gamePort.getWorldHeight(), 0);

    }
    /**
     * Loads the background game for a tmx file
     * */
    public void setMapaJogo(){
        assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("android/assets/roadv6.tmx", TiledMap.class);
        assetManager.finishLoading();
    }
    /**
     * Create cam used to follow the car through  world
     * */
    public void setCameraCarro(){
        gameCamera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        gameCamera.translate(gameCamera.viewportWidth/VelocidadeEscaldante.PPM,gameCamera.viewportHeight/VelocidadeEscaldante.PPM);

    }

    @Override
    public void show() {

    }

    /**
     * Function to separate from the input from desktop and the input from android
     * @param dt recives a float delta time
     * */
    public void handleInput(float dt){
        if(this.game.getIsMobile() == true){
            InputHandlerAndroid inputandroid = new InputHandlerAndroid(this.player, dt);
            inputandroid.inputmanager();
        }else{
            InputHandlerDesktop inputdesktop = new InputHandlerDesktop(this.player,dt);
            inputdesktop.inputManager();
        }

    }
    /**
     * Updates Velocity, points and time in the game information area
     * @param dt recives a float delta time
     * */
    public void updateInformacaoDoJogo(float dt){
        //this.gameInformation.setWorldTimer(player.carroPositionY());
        gameInformation.setVelocidadeCarro(player.carroPositionY());
        gameInformation.addPontos(player.carroPositionX());
        gameInformation.updateLabels();
        gameInformation.update(dt);
    }
    public float getCarPositionYDuringGame(){
        return player.getBodyCarroControlado().getPosition().y/GAME_CAMERA_RACIO;
    }
    /**
     * Tracking the car with game camera
     * */
    public void updateCameraPositionWithCarPosition(){
        gameCamera.position.y = getCarPositionYDuringGame();
    }
    /**
     * Updates HUD game information, game camera with car position and input received
     * @param dt recives a float delta time
     * */
    public void update(float dt){
        this.handleInput(dt);
        this.updateInformacaoDoJogo(dt);
        updateCameraPositionWithCarPosition();
        gameCamera.update();
        renderer.setView(gameCamera);
    }


    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        //Set our batch to now draw what HUD camera sees
        game.batch.setProjectionMatrix(gameInformation.stage.getCamera().combined);
        game.batch.begin();
        enemies.update(player.getBodyCarroControlado());
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
         gameInformation.dispose();
    }
}

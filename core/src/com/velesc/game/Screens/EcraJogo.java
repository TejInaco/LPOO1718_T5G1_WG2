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
import com.velesc.game.Sprites.OtherCars;
import com.velesc.game.VelocidadeEscaldante;
import com.velesc.game.Sprites.CarroControlado;

/**
 * Class responsible for the game itself
 **/
public class EcraJogo implements Screen {

    private static float GAME_CAMERA_RACIO = 0.05f;

    private VelocidadeEscaldante game;

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
    private Box2DDebugRenderer b2dr;


    private  CarroControlado player;
    private OtherCars taxi;
    private OtherCars civil1;
    private OtherCars civil2;

    private boolean gameOver =false;
    private Assets assets = new Assets();


    public EcraJogo(VelocidadeEscaldante game){
        this.game = game;
        this.maploader = new TmxMapLoader();
        setMapaJogo();
        this.map = assetManager.get("roadv6.tmx");
        setCameraCarro();
        //Create FitViewPort to maintain virtual aspect ratio despite screen
        this.gamePort = new FitViewport(VelocidadeEscaldante.largura /VelocidadeEscaldante.PPM, VelocidadeEscaldante.altura /VelocidadeEscaldante.PPM , gameCamera);
        this.gameInformation = new GameInformation(game.batch);
        //Load our map and setup our map renderer
        this.renderer = new OrthogonalTiledMapRenderer(map, 1/VelocidadeEscaldante.PPM);
        setGameCameraPosition();
        this.world = new World(new Vector2(0,0),true);
        this.player = new CarroControlado(this);


        this.taxi = new OtherCars(this, 75,1000,-1, assets.CAR_3);
        this.civil1 = new OtherCars(this,235,1000,-2,assets.CAR_1);
        this.civil2 = new OtherCars(this,380,2600, -3,assets.CAR_2);

        //allows for debug lines of our box2d world
        b2dr = new Box2DDebugRenderer();
        changeLevel();
    }
    public World getWorld(){

        return world;
    }
    public VelocidadeEscaldante getGame() {
        return game;
    }
    /**
     * Set Game camera center position
     * */
    private void setGameCameraPosition(){

        gameCamera.position.set(gamePort.getWorldWidth(),gamePort.getWorldHeight(), 0);

    }
    /**
     * Loads the background game for a tmx file
     * */
    private void setMapaJogo(){
        assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("roadv6.tmx", TiledMap.class);
        assetManager.finishLoading();
    }
    /**
     * Create cam used to follow the car through  world
     * */
    private void setCameraCarro(){
        gameCamera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        gameCamera.translate(gameCamera.viewportWidth/VelocidadeEscaldante.PPM,gameCamera.viewportHeight/VelocidadeEscaldante.PPM);

    }
    private void changeLevel(){
        player.getFisica().update(game.getLevel());
    }

    @Override
    public void show() {

    }

    /**
     * Updates Velocity, points and time in the game information area
     * @param dt recives a float delta time
     * */
    private void updateInformacaoDoJogo(float dt){
        float playervelocity = player.getSpeedKMH();

        gameInformation.setVelocidadeCarro(playervelocity);
        gameInformation.addPontos(playervelocity, player.getFisica().MAX_VELOCITY);
        gameInformation.setLevel(game.getLevel());
        gameInformation.updateLabels();
        gameInformation.update(dt);
    }
    /**
     * @return coordinate y of the player by secren racio
     * */
    private float getCarPositionYDuringGame(){
        return player.getBodyCarroControlado().getPosition().y/GAME_CAMERA_RACIO;
    }
    /**
     * Tracking the car with game camera
     * */
    private void updateCameraPositionWithCarPosition(){
        gameCamera.position.y = getCarPositionYDuringGame();
    }
    /**
     * Updates HUD game information, game camera with car position and input received
     * @param dt receives a float delta time
     * */
    private void update(float dt){

        this.updateInformacaoDoJogo(dt);
        updateCameraPositionWithCarPosition();
        gameCamera.update();
        renderer.setView(gameCamera);
    }

    private boolean finishedLine(){
        return player.currentState == CarroControlado.State.FINISHING_LINE;
    }
    /**
     * Checks collisions with other cars
     * @param posPlayerX user car position x
     * @param posPlayerY user car position y
     * @param altura sprite height
     * @param largura sprite width
     * */
    private void checkCollisions(float posPlayerX, float posPlayerY, float largura, float altura){
        if(taxi.hitByEnemy(posPlayerX, posPlayerY,  largura, altura) ||
            civil1.hitByEnemy(posPlayerX, posPlayerY,  largura, altura)  ||
            civil2.hitByEnemy(posPlayerX, posPlayerY,  largura, altura) ){
                gameOver = true;
        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        checkCollisions(player.getBodyCarroControlado().getPosition().x,
                player.getBodyCarroControlado().getPosition().y,
                50 + assets.CAMARO.getWidth()/2,25 + assets.CAMARO.getHeight()/2);

        //renderer our Box2DDebugLines
        b2dr.render(world, gameCamera.combined);

        //Set our batch to now draw what HUD camera sees
        game.batch.setProjectionMatrix(gameCamera.combined);
        game.batch.begin();

        taxi.update(delta);
        civil1.update(delta);
        civil2.update(delta);
        player.update();

        game.batch.end();
        game.batch.setProjectionMatrix(gameInformation.stage.getCamera().combined);
        gameInformation.stage.draw();
        if(gameOver){
            game.setScreen(new GameOver(game));
            dispose();
        }
        if(finishedLine()){
            game.setLevel();
            game.setScreen(new Finished(game));
        }
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
         gameInformation.dispose();
    }
}

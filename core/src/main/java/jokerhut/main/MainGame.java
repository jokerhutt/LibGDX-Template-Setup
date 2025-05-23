package jokerhut.main;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import engine.inputHandling.KeyInputBroadcaster;
import jokerhut.main.constant.SETUPCONSTANTS;
import jokerhut.main.screen.AbstractScreen;
import jokerhut.main.screen.GameScreen;
import jokerhut.main.screen.ScreenType;

import java.util.EnumMap;

import static jokerhut.main.constant.SETUPCONSTANTS.FIXED_TIME_STEP;
import static jokerhut.main.util.SkinInitializer.initializeSkin;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends Game {

    //RENDERING
    private SpriteBatch spriteBatch;
    private EnumMap<ScreenType, AbstractScreen> screenCache;
    private OrthographicCamera gameCamera;
    private FitViewport screenViewport;
    private Box2DDebugRenderer box2DDebugRenderer;
    private AssetManager assetManager;
    private KeyInputBroadcaster keyInputBroadcaster;
    private Stage stage;

    private Skin skin;
    private I18NBundle i18NBundle;

    //PHYSICS
    public static final BodyDef BODY_DEF = new BodyDef();
    public static final FixtureDef FIXTURE_DEF = new FixtureDef();

    private World world;

    //DELTA TIME

    //SCALING
    public static final float UNIT_SCALE = 1 / 16f;

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public EnumMap<ScreenType, AbstractScreen> getScreenCache() {
        return screenCache;
    }

    public OrthographicCamera getGameCamera() {
        return gameCamera;
    }

    public FitViewport getScreenViewport() {
        return screenViewport;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public Stage getStage() {
        return stage;
    }

    public Box2DDebugRenderer getBox2DDebugRenderer() {
        return box2DDebugRenderer;
    }

    public Skin getSkin() {
        return skin;
    }

    public I18NBundle getI18NBundle() {
        return i18NBundle;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void create() {


        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        //BATCH
        spriteBatch = new SpriteBatch();

        //BOX2d
        Box2D.init();
        world = new World(new Vector2(0, -9.8f), true);
        box2DDebugRenderer = new Box2DDebugRenderer();

        //FPS


        //ASSETS
        assetManager = new AssetManager();
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(assetManager.getFileHandleResolver()));
        assetManager.load(SETUPCONSTANTS.MAPPATH, TiledMap.class);
        assetManager.finishLoading();
        skin = initializeSkin(assetManager);

        //STAGE AND CAMERA
        stage = new Stage(new FitViewport(1500, 500), spriteBatch);
        gameCamera = new OrthographicCamera();
        screenViewport = new FitViewport(30, 10, gameCamera);

        keyInputBroadcaster = new KeyInputBroadcaster();

        setScreen(new GameScreen(this));


    }

    @Override
    public void render () {

        super.render();

//        stage.getViewport().apply();
//        stage.act();
//        stage.draw();

    }

    @Override
    public void dispose () {
        super.dispose();
        world.dispose();
        assetManager.dispose();
        spriteBatch.dispose();
        box2DDebugRenderer.dispose();
        stage.dispose();
    }

    public KeyInputBroadcaster getKeyInputBroadcaster() {
        return keyInputBroadcaster;
    }
}

package jokerhut.main.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import jokerhut.main.MainGame;
import jokerhut.main.constant.SETUPCONSTANTS;
import jokerhut.main.entity.MarioEntity;
import jokerhut.main.map.GameMap;
import jokerhut.main.view.GameUI;

import static engine.map.MapUtils.parsePlayerStartLocation;
import static jokerhut.main.MainGame.UNIT_SCALE;
import static jokerhut.main.constant.SETUPCONSTANTS.FIXED_TIME_STEP;
import static jokerhut.main.constant.SETUPCONSTANTS.marioTextureRegionSheet;

public class GameScreen extends AbstractScreen<GameUI>{

    private final AssetManager assetManager;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final OrthographicCamera gameCamera;
    private final GLProfiler profiler;
    private GameMap gameMap;
    float accumulator = 0f;
    MarioEntity mario;
    Vector2 marioPosition;

    public GameScreen (final MainGame context) {
        super(context);

        //INITIALIZE ASSET MANAGERS
        this.assetManager = context.getAssetManager();
        this.profiler = new GLProfiler(Gdx.graphics);
        profiler.enable();

        //LOAD MAP
        this.mapRenderer = new OrthogonalTiledMapRenderer(null, UNIT_SCALE, context.getSpriteBatch());
        final TiledMap tiledMap = assetManager.get(SETUPCONSTANTS.MAPPATH, TiledMap.class);
        mapRenderer.setMap(tiledMap);
        gameMap = new GameMap(tiledMap);

        //LOAD COLLISION AREAS INTO WORLD
        gameMap.initializeGroundCollisions(world);

        //LOAD CAMERA
        gameCamera = context.getGameCamera();
        gameCamera.position.set(15, 5, 0); // Adjust to your map's center in world units
        gameCamera.update();

        Gdx.app.log("Profiler",
            "Java Heap: " + Gdx.app.getJavaHeap() / 1024 / 1024 + " MB, " +
                "Native Heap: " + Gdx.app.getNativeHeap() / 1024 / 1024 + " MB"
        );

        //INPUT
        Gdx.input.setInputProcessor(context.getKeyInputBroadcaster());

        //LOAD ENTITIES
        marioPosition = parsePlayerStartLocation("PlayerSpawn", tiledMap);
        mario = new MarioEntity(marioPosition.x, marioPosition.y + 1f, 5f, 1f, 1f, marioTextureRegionSheet, this, context.getKeyInputBroadcaster());



    }

    public void updateLogic (float delta) {
        world.step(delta, 6, 2);
        mario.update(delta);
        gameCamera.position.x = mario.body.getPosition().x;
        gameCamera.update();
        mapRenderer.setView(gameCamera);
        viewport.apply(false);
    }

    @Override
    public void render(float delta) {
        delta = Math.min(delta, 0.25f);
        accumulator += delta;

        int updatesThisFrame = 0;

        System.out.println(accumulator);

        while (accumulator >= FIXED_TIME_STEP) {
            updateLogic(FIXED_TIME_STEP);
            accumulator -= FIXED_TIME_STEP;
            updatesThisFrame++;
        }

        System.out.println("Updates this frame: " + updatesThisFrame);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapRenderer.setView(gameCamera);
//        mapRenderer.render();

        box2DDebugRenderer.render(world, viewport.getCamera().combined);
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
//      gameMap.renderCollisionObjects(spriteBatch);
        spriteBatch.end();

        profiler.reset();
    }

    @Override
    protected GameUI getScreenUI(MainGame context) {
        return new GameUI(this, context.getSkin());
    }

    @Override
    public void dispose() {
        mapRenderer.dispose();
    }
}

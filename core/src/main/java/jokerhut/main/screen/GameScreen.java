package jokerhut.main.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import jokerhut.main.MainGame;
import jokerhut.main.constant.SETUPCONSTANTS;
import engine.map.GameMap;
import jokerhut.main.view.GameUI;

import static jokerhut.main.MainGame.UNIT_SCALE;

public class GameScreen extends AbstractScreen<GameUI>{

    private final AssetManager assetManager;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final OrthographicCamera gameCamera;
    private final GLProfiler profiler;
    private GameMap gameMap;

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

        //LOAD CAMERA
        gameCamera = context.getGameCamera();
        gameCamera.position.set(15, 5, 0); // Adjust to your map's center in world units
        gameCamera.update();

        //INPUT
        Gdx.input.setInputProcessor(context.getStage());

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply(false);
        mapRenderer.setView(gameCamera);
        mapRenderer.render();
        box2DDebugRenderer.render(world, viewport.getCamera().combined);
        spriteBatch.setProjectionMatrix(gameCamera.combined);
        spriteBatch.begin();

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

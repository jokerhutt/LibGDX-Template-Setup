package jokerhut.main.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import engine.map.CollisionArea;
import jokerhut.main.screen.GameScreen;

import static engine.map.CollisionUtils.addStaticChainShapesToWorld;
import static engine.map.MapUtils.parseCollisionLayer;

public class GameMap {

    public static final String TAG = GameMap.class.getSimpleName();
    private TiledMap tiledMap;
    private final Array<CollisionArea> collisionAreas;

    public GameMap (final TiledMap tiledMap) {
        this.tiledMap = tiledMap;
        collisionAreas = new Array<>();
    }

    public Array<CollisionArea> getCollisionAreas() {
        return collisionAreas;
    }

    public void renderCollisionObjects (SpriteBatch batch) {
        for (CollisionArea collisionArea : collisionAreas) {
            collisionArea.render(batch);
        }
    }

    public void initializeGroundCollisions (final World world) {
        parseCollisionLayer(tiledMap, collisionAreas, "GroundLayer");
        addStaticChainShapesToWorld(collisionAreas, world, "GROUND");
    }

}


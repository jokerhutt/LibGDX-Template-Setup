package engine.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


import static jokerhut.main.MainGame.UNIT_SCALE;

public class GameMap {

    public static final String TAG = GameMap.class.getSimpleName();
    private TiledMap tiledMap;
    private final Array<CollisionArea> collisionAreas;

    public GameMap (final TiledMap tiledMap) {
        this.tiledMap = tiledMap;
        collisionAreas = new Array<>();
    }



}


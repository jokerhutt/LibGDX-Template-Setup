package jokerhut.main.map;

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

//    private final Vector2 alliedStartLocation;
//    private final Vector2 enemyStartLocation;

    public GameMap (final TiledMap tiledMap) {
        this.tiledMap = tiledMap;
        collisionAreas = new Array<>();

//        alliedStartLocation = parsePlayerStartLocation("AlliedSpawnLocation");
//        enemyStartLocation = parsePlayerStartLocation("EnemySpawnLocation");
//
//        System.out.println(alliedStartLocation.y);


    }

//    public Vector2 getAlliedStartLocation() {
//        return alliedStartLocation;
//    }
//
//    public Vector2 getEnemyStartLocation() {
//        return enemyStartLocation;
//    }

    private RectangleMapObject createRectangleMapObject (String layerName, String objectName) {
        MapLayer layer = tiledMap.getLayers().get(layerName);
        if (layer == null) return null;

        for (MapObject obj : layer.getObjects()) {
            if (objectName.equals(obj.getName()) && obj instanceof RectangleMapObject) {
                return (RectangleMapObject) obj;
            }
        }

        return null;
    }

    public Vector2 parsePlayerStartLocation (String locationName) {
        final MapLayer startLocationLayer = tiledMap.getLayers().get(locationName);
        Vector2 targetLocation = new Vector2();
        if (startLocationLayer == null) {
            return null;
        } else {
            final MapObjects objects = startLocationLayer.getObjects();
            for (MapObject object : objects) {
                if (object instanceof RectangleMapObject) {
                    final RectangleMapObject rectangleMapObject = (RectangleMapObject) object;
                    final Rectangle rectangle = rectangleMapObject.getRectangle();
                    targetLocation.set(rectangle.x * UNIT_SCALE, rectangle.y * UNIT_SCALE);
                }
            }
            return targetLocation;
        }
    }

}

package engine.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import static jokerhut.main.map.GameMap.TAG;
import static jokerhut.main.MainGame.UNIT_SCALE;

public class MapUtils {


    /**
     * Loads the spawn point location from a named rectangle object layer. Used for insert point in tiled
     */
    public static Vector2 parsePlayerStartLocation (String locationName, TiledMap tiledMap) {
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

    /**
     * Grabs and returns a rectangle tile from tiled editor map
     */
    public static RectangleMapObject retrieveRectangleMapObject (String layerName, String objectName, TiledMap tiledMap) {
        MapLayer layer = tiledMap.getLayers().get(layerName);
        if (layer == null) return null;

        for (MapObject obj : layer.getObjects()) {
            if (objectName.equals(obj.getName()) && obj instanceof RectangleMapObject) {
                return (RectangleMapObject) obj;
            }
        }

        return null;
    }

    //TODO add documentation
    public static void parseCollisionLayer (TiledMap tiledMap, Array<CollisionArea> collisionAreas, String layerName) {

        final MapLayer collisionLayer = tiledMap.getLayers().get(layerName);
        if (collisionLayer == null) {
            Gdx.app.debug(TAG, " there is no collision layer");
            return;
        }

        for (MapObject mapObject : collisionLayer.getObjects()) {
            System.out.println("Parsing object: " + mapObject);

            if (mapObject instanceof RectangleMapObject) {
                final RectangleMapObject rectangleMapObject = (RectangleMapObject) mapObject;
                final Rectangle rectangle = rectangleMapObject.getRectangle();
                final float[] rectVertices = new float[10];

                //left-bot
                rectVertices[0] = 0;
                rectVertices[1] = 0;

                //left-top
                rectVertices[2] = 0;
                rectVertices[3] = rectangle.height;

                //right-top
                rectVertices[4] = rectangle.width;
                rectVertices[5] = rectangle.height;

                //right-bottom
                rectVertices[6] = rectangle.width;
                rectVertices[7] = 0;

                //left-bottom
                rectVertices[8] = 0;
                rectVertices[9] = 0;

                collisionAreas.add(new CollisionArea(rectangle.x, rectangle.y, rectVertices, false));

            } else if (mapObject instanceof PolylineMapObject) {
                final PolylineMapObject polylineMapObject = (PolylineMapObject) mapObject;
                final Polyline polyline = polylineMapObject.getPolyline();
                System.out.println(polyline.getX());
                System.out.println(polyline.getY());
                final float[] polyVertices = polyline.getVertices();

                collisionAreas.add(new CollisionArea(polyline.getX(), polyline.getY(), polyVertices, true));
            } else if (mapObject.getProperties().containsKey("gid")) {
                float x = mapObject.getProperties().get("x", Float.class);
                float y = mapObject.getProperties().get("y", Float.class);
                float width = mapObject.getProperties().get("width", Float.class);
                float height = mapObject.getProperties().get("height", Float.class);
                int gid = mapObject.getProperties().get("gid", Integer.class);
                TiledMapTile tile = tiledMap.getTileSets().getTile(gid);
                TextureRegion region = tile != null ? tile.getTextureRegion() : null;

                float[] rectVertices = new float[] {
                    0, 0,
                    0, height,
                    width, height,
                    width, 0,
                    0, 0
                };

                System.out.println("X: " + x + " Y: " + y + " W: " + width + " H: " + height);

                CollisionArea area = new CollisionArea(x, y, rectVertices, false);
                if (region != null) {
                    area.setTexture(region, width, height);
                }
                collisionAreas.add(area);
            } else {
                Gdx.app.debug(TAG, " Not supported");
            }
        }

    }





}

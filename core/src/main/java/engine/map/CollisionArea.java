package engine.map;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import static jokerhut.main.MainGame.UNIT_SCALE;

public class CollisionArea {

    private final float x;
    private final float y;
    private final float[] vertices;
    private boolean polyLine;

    private Body body;
    private TextureRegion textureRegion;

    private float width;
    private float height;

    public float[] getVertices() {
        return vertices;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public void setTexture(TextureRegion region, float width, float height) {
        this.textureRegion = region;
        this.width = width * UNIT_SCALE;
        this.height = height * UNIT_SCALE;
    }

    public CollisionArea (final float x, final float y, final float[] vertices, boolean isPoly) {
        this.x = x * UNIT_SCALE;
        this.y = y * UNIT_SCALE;
        this.vertices = vertices;
        for (int i = 0; i < vertices.length; i+=2) {
            vertices[i] = vertices[i] * UNIT_SCALE;
            vertices[i + 1] = vertices[i + 1] * UNIT_SCALE;

        }
        this.polyLine = isPoly;
    }

    public boolean isPolyLine() {
        return polyLine;
    }

    public void render (SpriteBatch batch) {
        if (textureRegion != null) {
            batch.draw(textureRegion, x, y, width, height);
        }
    }

}

package jokerhut.main.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import engine.inputHandling.AbstractInputBroadcaster;
import engine.inputHandling.InputBroadcastListener;
import engine.inputHandling.KeyInputBroadcaster;
import engine.inputHandling.KeyType;
import jokerhut.main.screen.GameScreen;

import static jokerhut.main.constant.SETUPCONSTANTS.*;

public class MarioEntity extends Entity implements InputBroadcastListener {

    protected float xFactor = 0;
    protected float yFactor = 0;
    private KeyInputBroadcaster keyInputBroadcaster;
    Vector2 prevPos = new Vector2(0, 0);
    Vector2 currentPos = new Vector2(0, 0);

    public MarioEntity (float x, float y, float speed, float width, float height, TextureRegion textureRegion, GameScreen gameContext, KeyInputBroadcaster keyInputBroadcaster) {
        super(x, y, speed, width, height, textureRegion);
        createBody(gameContext.getWorld(), x, y);
        this.keyInputBroadcaster = keyInputBroadcaster;
        this.keyInputBroadcaster.subscribeToInputBroadcast(this);
        prevPos = this.body.getPosition();
        currentPos = this.body.getPosition();
    }

    @Override
    public void keyPressed(AbstractInputBroadcaster broadcaster, KeyType key) {
        switch (key) {
            case LEFT:
                System.out.println("Pressed Left");
                xFactor = -1;
                break;
            case RIGHT:
                System.out.println("Pressed Right");
                xFactor = 1;
                break;
            case UP:
                System.out.println("Pressed Jump Key");
                break;
            case DOWN:
                yFactor = 0;
            default:
                return;
        }
    }

    @Override
    public void keyUp(AbstractInputBroadcaster broadcaster, KeyType key) {
        switch (key) {
            case LEFT:
                xFactor = broadcaster.isKeyPressed(KeyType.RIGHT) ? 1 : 0;
                break;
            case RIGHT:
                xFactor = broadcaster.isKeyPressed(KeyType.LEFT) ? -1 : 0;
                break;
            default:
                break;
        }
    }

    public void processMovement () {

        float desiredVelocity = xFactor * speed;

        body.setLinearVelocity(desiredVelocity, body.getLinearVelocity().y);

    }

    @Override
    public void update (float deltaTime) {
        processMovement();
    }


    @Override
    public void createBody(World world, float spawnX, float spawnY) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(spawnX, spawnY + 1f);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        body.setUserData("PLAYER");
        FixtureDef fixtureDef = new FixtureDef();

        fixtureDef.filter.categoryBits = BIT_PLAYER;
        fixtureDef.filter.maskBits = BIT_GROUND;
        final PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(0.5f, 0.5f);
        fixtureDef.shape = polygonShape;
        body.createFixture(fixtureDef);
        polygonShape.dispose();

        this.body = body;
    }

    @Override
    public void updateMovement(float deltaTime) {

    }
}

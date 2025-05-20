package jokerhut.main.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import engine.inputHandling.AbstractInputBroadcaster;
import engine.inputHandling.InputBroadcastListener;
import engine.inputHandling.KeyType;
import jokerhut.main.screen.GameScreen;

import static jokerhut.main.MainGame.*;
import static jokerhut.main.constant.SETUPCONSTANTS.*;

public class MarioEntity extends Entity implements InputBroadcastListener {

    public MarioEntity (float x, float y, float speed, float width, float height, TextureRegion textureRegion, GameScreen gameContext) {
        super(x, y, speed, width, height, textureRegion);
        createBody(gameContext.getWorld(), x, y);


    }

    @Override
    public void keyPressed(AbstractInputBroadcaster broadcaster, KeyType key) {

    }

    @Override
    public void keyUp(AbstractInputBroadcaster broadcaster, KeyType key) {

    }

    @Override
    public void createBody(World world, float spawnX,float spawnY) {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(spawnX, spawnY + 0.5f);
        bodyDef.fixedRotation = true;

        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f);
        fixtureDef.isSensor = false;
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;
        fixtureDef.filter.categoryBits = BIT_ENEMY;
        fixtureDef.filter.maskBits = BIT_GROUND | BIT_PLAYER;
        body.setBullet(true);
        body.createFixture(fixtureDef);
        shape.dispose();

        this.body = body;
    }

    @Override
    public void updateMovement(float deltaTime) {

    }
}

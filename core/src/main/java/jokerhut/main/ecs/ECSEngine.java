package jokerhut.main.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import jokerhut.main.MainGame;

public class ECSEngine {

    private final World world;
    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;

    public ECSEngine (final MainGame context) {
        super();
        this.world = context.getWorld();
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
    }

//    public void addBox2DComponent (Entity entity, Vector2 spawnPos) {
//        Box2DComponent box = createComponent(Box2DComponent.class);
//        box.width = 2f;
//        box.height = 2f;
//
//        bodyDef.type = BodyDef.BodyType.DynamicBody;
//        bodyDef.position.set(spawnPos);
//        bodyDef.fixedRotation = true;
//
//        Body body = world.createBody(bodyDef);
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(box.width / 2f, box.height / 2f);
//        fixtureDef.isSensor = true;
//        fixtureDef.shape = shape;
//        fixtureDef.density = 1f;
//        fixtureDef.friction = 0.5f;
//
//        body.createFixture(fixtureDef);
//        shape.dispose();
//
//        box.body = body;
//
//        entity.add(box);
//    }

}

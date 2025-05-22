package jokerhut.main.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static jokerhut.main.constant.SETUPCONSTANTS.marioTextureRegionSheet;

public abstract class Entity {

    public Body body;
    protected float speed;
    protected Sprite sprite;
    protected float width;
    protected float height;


    public Entity (float x, float y, float speed, float width, float height, TextureRegion texture) {
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.sprite = new Sprite(texture);
        sprite.setRegion(texture);
        sprite.setCenter(0, 0);
        sprite.setSize(1f, 1f);
    }

    public abstract void createBody (World world,float spawnX,float spawnY);

    public abstract void updateMovement (float deltaTime);

    public void updateSprite (TextureRegion textureRegion) {

        sprite.setRegion(textureRegion);
        if (body == null) {
            sprite.setCenter(0, 0);
        }
//        } else {
//            sprite.setCenter(body.getPosition().x, body.getPosition().y);
//        }

        sprite.setSize(1f, 1f);



    }

    public void update (float deltaTime) {

    }

    public void render (SpriteBatch batch) {
//        updateSprite(marioTextureRegionSheet);
        sprite.draw(batch);

    }

}

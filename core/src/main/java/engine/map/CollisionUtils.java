package engine.map;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import static jokerhut.main.MainGame.*;
import static jokerhut.main.MainGame.FIXTURE_DEF;
import static jokerhut.main.constant.SETUPCONSTANTS.BIT_GROUND;

public class CollisionUtils {

    /**
     * adds
     */
    public static void createStaticChainShapeBody (CollisionArea collisionArea, World world, String userData) {
        BODY_DEF.position.set(collisionArea.getX(), collisionArea.getY());
        BODY_DEF.fixedRotation = true;
        final Body body = world.createBody(BODY_DEF);
        collisionArea.setBody(body);
        body.setUserData(userData);

        FIXTURE_DEF.filter.categoryBits = BIT_GROUND;
        FIXTURE_DEF.filter.maskBits = -1;

        final ChainShape chainShape = new ChainShape();
        float[] vertices = collisionArea.getVertices();
        for (int i = 0; i < vertices.length; i += 2) {
            System.out.println("(" + vertices[i] + ", " + vertices[i+1] + ")");
        }
        chainShape.createChain(collisionArea.getVertices());
        FIXTURE_DEF.shape = chainShape;
        body.createFixture(FIXTURE_DEF);
        chainShape.dispose();
    }

    public static void addStaticChainShapesToWorld (Array<CollisionArea> collisionAreas, World world, String userData) {

        for (CollisionArea collisionArea : collisionAreas) {
            createStaticChainShapeBody(collisionArea, world, userData);
        }

    }

}

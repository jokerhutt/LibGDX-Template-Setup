package jokerhut.main.constant;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SETUPCONSTANTS {

    public static final String MAPPATH = "map/mariomap2.tmx";
    public static final String FONTTTFPATH = "ui/font.ttf";
    public static final String ATLASPATH = "ui/hud.atlas";
    public static final String JSONPATH = "ui/hud.json";

    //0000 0000 0000 0001
    public static final short BIT_PLAYER = 1 <<0;
    //0000 0000 0000 0010
    public static final short BIT_GROUND = 1 << 1;

    //0000 0000 0000 0100
    public static final short BIT_ENEMY = 1 << 2;

    private float accumulator;
    private static final float FIXED_TIME_STEP = 1 / 60f;

    public static final Texture marioTexture = new Texture("gfx/little_mario.png");
    public static final TextureRegion marioTextureRegionSheet = new TextureRegion(marioTexture, 0, 0, 16, 16);


}

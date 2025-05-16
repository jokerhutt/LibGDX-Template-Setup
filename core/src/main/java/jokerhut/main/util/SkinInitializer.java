package jokerhut.main.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;
import jokerhut.main.constant.SETUPCONSTANTS;

public class SkinInitializer {

    public static Skin initializeSkin (AssetManager contextAssetManager) {

        //setup markup colors
        Colors.put("Red", Color.RED);
        Colors.put("Blue", Color.BLUE);

        //generate ttf bitmaps
        final ObjectMap<String, Object> resources = new ObjectMap<>();
        final FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(SETUPCONSTANTS.FONTTTFPATH));
        final FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.minFilter = Texture.TextureFilter.Linear;
        fontParameter.magFilter = Texture.TextureFilter.Linear;
        final int[] sizesToCreate = {16, 20, 26, 32}; //s m l xl
        for (int size : sizesToCreate) {
            fontParameter.size = size;
            final BitmapFont bitmapFont = fontGenerator.generateFont(fontParameter);
            bitmapFont.getData().markupEnabled = true;
            resources.put("font_" + size, bitmapFont);
        }
        fontGenerator.dispose();

        //load skin
        final SkinLoader.SkinParameter skinParameter = new SkinLoader.SkinParameter(SETUPCONSTANTS.ATLASPATH, resources);
        contextAssetManager.load(SETUPCONSTANTS.JSONPATH, Skin.class, skinParameter);
        contextAssetManager.finishLoading();
        Skin skin = contextAssetManager.get(SETUPCONSTANTS.JSONPATH, Skin.class);

        return skin;

    }
}

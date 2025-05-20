package engine.inputHandling;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;

public abstract class AbstractInputBroadcaster implements InputProcessor {

    protected final KeyType[] keyMapping;
    protected final boolean[] keyState;
    protected final Array<InputBroadcastListener> listeners;

    public AbstractInputBroadcaster() {
        this.keyMapping = new KeyType[256];
        for (final KeyType gameKey : KeyType.values()) {
            for (final int code : gameKey.keyCode) {
                keyMapping[code] = gameKey;
            }
        }
        keyState = new boolean[KeyType.values().length];
        listeners = new Array<InputBroadcastListener>();
    }

    public abstract void notifyKeyDown (final KeyType gameKey);

    public abstract void notifyKeyUp (final KeyType gameKey);


    public abstract void subscribeToInputBroadcast (final InputBroadcastListener subscriber);

    public abstract void removeFromInputBroadcast (final InputBroadcastListener subscriber);

    @Override
    public abstract boolean keyDown(int keycode);
    @Override
    public abstract boolean keyUp(final int keycode);

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

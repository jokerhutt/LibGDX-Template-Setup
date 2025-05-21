package engine.inputHandling;

public class KeyInputBroadcaster extends AbstractInputBroadcaster {

    public KeyInputBroadcaster () {
        super();

    }

    @Override
    public void subscribeToInputBroadcast(InputBroadcastListener subscriber) {
        listeners.add(subscriber);
    }

    @Override
    public void removeFromInputBroadcast(InputBroadcastListener subscriber) {
        listeners.removeValue(subscriber, true);
    }

    @Override
    public void notifyKeyDown (final KeyType gameKey) {
        System.out.println(">>> notifyKeyDown: " + gameKey);
        keyState[gameKey.ordinal()] = true;
        for (final InputBroadcastListener listener : listeners) {
            listener.keyPressed(this, gameKey);
        }
    }

    @Override
    public void notifyKeyUp (final KeyType gameKey) {
        keyState[gameKey.ordinal()] = false;
        for (final InputBroadcastListener listener : listeners) {
            listener.keyUp(this, gameKey);
        }
    }

    //ON KEY PRESS
    @Override
    public boolean keyDown(int keycode) {
        final KeyType gameKey = keyMapping[keycode];
        if (gameKey == null) {
            return false;
        }
        notifyKeyDown (gameKey);
        return false;
    }

    //ON KEY RELEASE
    //USE FOR CHECKING IF STOP OR MOVE IN OTHER DIRECTION
    @Override
    public boolean keyUp(int keycode) {
        final KeyType gameKey = keyMapping[keycode];
        if (gameKey == null) {
            return false;
        }
        notifyKeyUp(gameKey);
        return false;
    }
}

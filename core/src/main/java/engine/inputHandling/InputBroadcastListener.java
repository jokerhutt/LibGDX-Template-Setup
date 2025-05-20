package engine.inputHandling;

public interface InputBroadcastListener {

    void keyPressed (final AbstractInputBroadcaster broadcaster, final KeyType key);

    void keyUp (final AbstractInputBroadcaster broadcaster, final KeyType key);

}

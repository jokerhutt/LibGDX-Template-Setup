package engine.inputHandling;

import com.badlogic.gdx.Input;

public enum KeyType {

    UP (Input.Keys.W, Input.Keys.UP),
    DOWN (Input.Keys.S, Input.Keys.DOWN),
    LEFT (Input.Keys.A, Input.Keys.LEFT),
    RIGHT (Input.Keys.D, Input.Keys.RIGHT),
    SELECT (Input.Keys.ENTER, Input.Keys.SPACE),
    BACK (Input.Keys.ESCAPE, Input.Keys.BACKSPACE);

    final int[] keyCode;

    KeyType (final int... keyCode) {
        this.keyCode = keyCode;
    }

    public int[] getKeyCode() {
        return keyCode;
    }

}

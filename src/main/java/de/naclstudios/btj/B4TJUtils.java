package de.naclstudios.btj;

import de.edgelord.saltyengine.gameobject.GameObject;

import java.util.Optional;

/**
 * static utility functions
 */
public class B4TJUtils {
    /**
     * Returns an optional of the given GameObject
     * cast to a B4TJEntity if it is an instance of
     * said class or else returns an empty Optional.
     *
     * @param object a GameObject which may or may not be a B4TJEntity
     * @return an optionals with either the given GameObject cast to a B4TJEntity
     * or an empty Optional if it is not an instance of said class
     */
    public static Optional<B4TJEntity> optionalB4TJEntity(final GameObject object) {
        if (object instanceof B4TJEntity) {
            return Optional.of((B4TJEntity) object);
        } else {
            return Optional.empty();
        }
    }
}

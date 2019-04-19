package com.dorsolo.architecture_components.listeners;

public interface SwipeAnimationListener {

    /**
     * @param fromPos the starting position of the item
     * @param toPos   the end position of the item
     */
    void onMove(int fromPos, int toPos);

    /**
     * @param direction the direction of the swipe
     * @param pos       the position of the swiped item
     */
    void onSwiped(int direction, int pos);
}

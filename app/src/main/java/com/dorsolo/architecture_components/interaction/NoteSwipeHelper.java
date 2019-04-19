package com.dorsolo.architecture_components.interaction;

import com.dorsolo.architecture_components.listeners.SwipeAnimationListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class NoteSwipeHelper extends ItemTouchHelper.Callback {

    @NonNull
    private SwipeAnimationListener swipeAnimationListener;

    public NoteSwipeHelper(@NonNull SwipeAnimationListener swipeAnimationListener) {
        this.swipeAnimationListener = swipeAnimationListener;
    }

    /**
     * In this method we determine which directions we support for drag & drop and for swiping,
     * We can cancel an option by returning 0, or we can support all directions for an action state
     *
     * @param recyclerView the recycler view that display the data
     * @param viewHolder   the viewHolder that contains the Views for the recyclerView
     * @return we return a makeMovementFlags with tow parameters (Drag & Drop, Swipe) directions
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        int swipeFlags = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        return makeMovementFlags(0, swipeFlags);
    }

    /**
     * onMove get's called when a drag & drop action happened
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        swipeAnimationListener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    /**
     * onSwiped get's called when a swipe action happened
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        swipeAnimationListener.onSwiped(direction, viewHolder.getAdapterPosition());
    }
}

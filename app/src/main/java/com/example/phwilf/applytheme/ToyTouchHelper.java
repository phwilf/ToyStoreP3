package com.example.phwilf.applytheme;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.phwilf.applytheme.Adapter.RecyclerAdapter;

/**
 * Created by phwilf on 4/4/16.
 *
 * Helps deal with swiping and dragging / dropping for the RecyclerView Catalogue of Toys
 */
public class ToyTouchHelper extends ItemTouchHelper.SimpleCallback {

    private RecyclerAdapter mRecyclerAdapter;

    //constructor
    public ToyTouchHelper(RecyclerAdapter recyclerAdapter){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            //parameter 1 is for drag directions
            //paramater 2 is for swiping directions
        this.mRecyclerAdapter = recyclerAdapter;
    }

    //use for drag/drop
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mRecyclerAdapter.swap(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    //use for swipe
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mRecyclerAdapter.remove(viewHolder.getAdapterPosition());
    }
}

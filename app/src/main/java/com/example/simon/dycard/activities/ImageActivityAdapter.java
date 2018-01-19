package com.example.simon.dycard.activities;
import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simon.dycard.R;
import com.example.simon.dycard.model.Item;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by s.sako.15 on 17/01/2018.
 *
*/
    public class ImageActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static String TAG = ImageActivityAdapter.class.getSimpleName();

        private Context mContext;
    private ArrayList<Integer> formesArrayList;
    List<Item> items;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView forme_id;

            public ViewHolder(View view) {
                super(view);
                forme_id = (ImageView) itemView.findViewById(R.id.forme_id);
            }


            @Override
            public void onClick(View view) {

            }
        }

        public ImageActivityAdapter(Context mContext, ArrayList<Integer>  formesArrayList) {
            this.mContext = mContext;
            this.formesArrayList =  formesArrayList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView;

            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.new_content, parent, false);

            return new ViewHolder(itemView);
        }

        @Override
        public int getItemViewType(int position) {

            return position;
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            int forme = formesArrayList.get(position);
            ((ViewHolder) holder).forme_id.setImageResource(forme);


        }

        @Override
        public int getItemCount() {
            return formesArrayList.size();
        }

        public interface ClickListener {
            void onClick(View view, int position);

            void onLongClick(View view, int position);
        }

        public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

            private GestureDetector gestureDetector;
            private ClickListener clickListener;

            public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
                this.clickListener = clickListener;
                gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public void onLongPress(MotionEvent e) {
                        View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (child != null && clickListener != null) {
                            clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                        }
                    }
                });
            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                    clickListener.onClick(child, rv.getChildPosition(child));
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        }
}



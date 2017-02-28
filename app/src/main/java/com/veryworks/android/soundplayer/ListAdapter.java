package com.veryworks.android.soundplayer;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.veryworks.android.soundplayer.domain.Common;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<?> datas;
    private String flag;
    private int item_layout_id;
    private Context context;

    public ListAdapter(Context context, List<?> datas, String flag) {
        this.context = context;
        this.datas = datas;
        this.flag = flag;
        switch(flag){
            case ListFragment.TYPE_SONG:
                item_layout_id = R.layout.list_fragment_item;
                break;
            case ListFragment.TYPE_ALBUM:
            case ListFragment.TYPE_GENRE:
            case ListFragment.TYPE_ARTIST:
                item_layout_id = R.layout.list_fragment_item_album;
                break;
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(item_layout_id, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Common common = (Common) datas.get(position);

        holder.imageView.setImageURI(common.getImageUri());
        Glide.with(context)
                .load(common.getImageUri())
                // 이미지가 없을 경우 대체 이미지
                .placeholder(android.R.drawable.ic_menu_close_clear_cancel)
                .into(holder.imageView);

        holder.textTitle.setText(common.getTitle());
        holder.textArtist.setText(common.getArtist());

        switch(flag){
            case ListFragment.TYPE_SONG:
                holder.textDuration.setText(common.getDuration());
                break;
            case ListFragment.TYPE_ALBUM:
            case ListFragment.TYPE_GENRE:
            case ListFragment.TYPE_ARTIST:
                break;
        }

        holder.box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public int position;
        ImageView imageView;
        TextView textTitle;
        TextView textArtist;
        TextView textDuration;
        ConstraintLayout box;

        public ViewHolder(View view) {
            super(view);

            box = (ConstraintLayout) view.findViewById(R.id.list_item);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            textTitle = (TextView) view.findViewById(R.id.textTitle);
            textArtist = (TextView) view.findViewById(R.id.textArtist);
            switch(flag){
                case ListFragment.TYPE_SONG:
                    textDuration = (TextView) view.findViewById(R.id.textDuration);
                    break;
                default :
                    // nothing
                    break;
            }
        }
    }
}

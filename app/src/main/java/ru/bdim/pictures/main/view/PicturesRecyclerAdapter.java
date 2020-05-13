package ru.bdim.pictures.main.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.squareup.picasso.Picasso;

import ru.bdim.pictures.R;
import ru.bdim.pictures.main.presenter.IPictureRecyclerPresenter;
import ru.bdim.pictures.main.view.PicturesRecyclerAdapter.PictureViewHolder;

public class PicturesRecyclerAdapter extends RecyclerView.Adapter<PictureViewHolder> {

    private IPictureRecyclerPresenter iAdapterPresenter;
    private OnItemClickListener listener;

    public PicturesRecyclerAdapter(IPictureRecyclerPresenter presenter) {
        this.iAdapterPresenter = presenter;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_pictures, parent, false);
        return new PictureViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {
          holder.index = position;
          iAdapterPresenter.bindViewHolder(holder);
    }
    @Override
    public int getItemCount() {
        return iAdapterPresenter.getItemCount();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class PictureViewHolder extends ViewHolder implements IPictureViewHolder{
        private ImageView imageView;
        private int index = 0;

        public PictureViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_recycler_image);
            itemView.setOnLongClickListener((v -> {
                listener.onItemClick(index);
                return false;
            }));
        }
        @Override
        public void setImage(String url) {
            //PictureLoader.setPicture(url, imageView);
            Picasso.with(imageView.getContext()).load(url).into(imageView);
        }
        @Override
        public int getIndex() {
            return index;
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
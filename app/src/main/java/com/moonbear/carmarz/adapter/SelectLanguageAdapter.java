package com.moonbear.carmarz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moonbear.carmarz.activitiesandfragment.SelectLanguageF;
import com.moonbear.carmarz.Interface.AdapterClickListener;
import com.moonbear.carmarz.databinding.ItemLanguageBinding;
import com.moonbear.carmarz.model.LanguageModel;

import java.util.List;


public class SelectLanguageAdapter extends RecyclerView.Adapter<SelectLanguageAdapter.ViewHolder> {

    private final List<LanguageModel> modelList;
    private final AdapterClickListener click;
    private final Context context;
    ItemLanguageBinding binding;

    public SelectLanguageAdapter(Context context, List<LanguageModel> modellist, AdapterClickListener click) {
        this.modelList = modellist;
        this.click = click;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemLanguageBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LanguageModel model = modelList.get(position);
        holder.itemBinding.tvCountry.setText(model.getName());

        if (model.getName().equals(SelectLanguageF.selectedLanguage)) {
            holder.itemBinding.ivTick.setVisibility(View.VISIBLE);
        } else {
            holder.itemBinding.ivTick.setVisibility(View.GONE);
        }

        holder.bind(position, model, click);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ItemLanguageBinding itemBinding;
        ViewHolder(@NonNull ItemLanguageBinding itemView) {
            super(itemView.getRoot());
            itemBinding = itemView;
        }

        public void bind(final int item, final LanguageModel model,
                         final AdapterClickListener listener) {
            itemView.setOnClickListener(v -> {
                // This is OnClick of any list Item
                listener.onItemClickListener(item, model, v);
            });
        }
    }
}
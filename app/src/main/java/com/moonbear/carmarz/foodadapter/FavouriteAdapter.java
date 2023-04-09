package com.moonbear.carmarz.foodadapter;

import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.moonbear.carmarz.codeclasses.DateOperations;
import com.moonbear.carmarz.codeclasses.MyPreferences;
import com.moonbear.carmarz.Constants;
import com.moonbear.carmarz.Interface.AdapterClickListener;
import com.moonbear.carmarz.model.ResturantModel;
import com.moonbear.carmarz.R;
import com.moonbear.carmarz.databinding.ItemFavListBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

    ItemFavListBinding  binding;
    Context context;
    ArrayList<ResturantModel> favouriteModelArrayList = new ArrayList<>();
    AdapterClickListener adapterClickListener;
    String currencyUnit;
    private AnimatedVectorDrawable emptyHeart;
    private AnimatedVectorDrawable fillHeart;
    private boolean full = false;

    public FavouriteAdapter(Context context, ArrayList<ResturantModel> favouriteModelArrayList, AdapterClickListener adapterClickListener) {
        this.context = context;
        this.favouriteModelArrayList = favouriteModelArrayList;
        this.adapterClickListener = adapterClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        binding = ItemFavListBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            emptyHeart = (AnimatedVectorDrawable) ContextCompat.getDrawable(context, R.drawable.avd_heart_empty);
            fillHeart = (AnimatedVectorDrawable) ContextCompat.getDrawable(context, R.drawable.avd_heart_fill);
        }


        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ResturantModel item = favouriteModelArrayList.get(position);
        currencyUnit = MyPreferences.getSharedPreference(context).getString(MyPreferences.currencyUnit, Constants.defaultCurrency);

        holder.itemView.resturantName.setText(item.getResturantName());
        holder.itemView.deliveryAmount.setText(currencyUnit + item.getDeliveryFee() + context.getResources().getString(R.string.delivery_free));

        if (item.getTotalRatingCount() != null && (!item.getTotalRatingCount().equals("") || !item.getTotalRatingCount().equals("null"))) {
            holder.itemView.rating.setText(String.format("%.03s", item.getTotalRatings()));
            holder.itemView.ratingLayout.setVisibility(View.VISIBLE);
        } else {
            holder.itemView.ratingLayout.setVisibility(View.GONE);
        }

        if (item.getBlock().equals("1")) {
            holder.itemView.unavailableLayout.setVisibility(View.VISIBLE);
            holder.itemView.warningTxt.setText(context.getResources().getString(R.string.not_available));
        } else {
            if (item.getOpen().equals("0")) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                holder.itemView.unavailableLayout.setVisibility(View.VISIBLE);
                holder.itemView.warningTxt.setText(context.getResources().getString(R.string.open_at) + " " + DateOperations.changeDateFormat("HH:mm:ss", "hh:mm a", item.getTimeModelArrayList().get(day - 1).getOpening_time()));
            }
        }

        String resturantImage = item.getResturantImage();

        if (resturantImage != null && !resturantImage.equals("")) {
            Uri uri = Uri.parse(Constants.BASE_URL + resturantImage);
            holder.itemView.menuImage.setImageURI(uri);
        }


        if (item.getIsLiked().equals("null") || item.getIsLiked().equals("0")) {
            full = true;
            holder.itemView.favBtn.setImageResource(R.drawable.ic_empty_heart);
        } else {
            full = false;
            holder.itemView.favBtn.setImageResource(R.drawable.ic_filled_heart);
        }


        holder.bind(position, item, adapterClickListener);

    }

    @Override
    public int getItemCount() {
        return favouriteModelArrayList.size();
    }

    // This method help to animate our view.
    public void animate(ImageView view) {
        AnimatedVectorDrawable drawable
                = full
                ? emptyHeart
                : fillHeart;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setImageDrawable(drawable);
        }
        drawable.start();
        full = !full;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemFavListBinding itemView;
        public ViewHolder(@NonNull ItemFavListBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;

        }

        public void bind(final int pos, final ResturantModel item, final AdapterClickListener adapter_clickListener) {

            itemView.favLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter_clickListener.onItemClickListener(pos, item, v);
                }
            });

            itemView.ratingLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter_clickListener.onItemClickListener(pos, item, v);
                }
            });

            itemView.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter_clickListener.onItemClickListener(pos, item, v);
                }
            });


        }
    }
}


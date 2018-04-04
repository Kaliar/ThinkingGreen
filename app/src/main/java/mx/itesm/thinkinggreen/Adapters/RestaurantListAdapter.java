package mx.itesm.thinkinggreen.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mx.itesm.thinkinggreen.Models.Restaurants;
import mx.itesm.thinkinggreen.R;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.ViewHolder> {

    private Restaurants[] arrRestautants;
    private int layout;
    private StoreListAdapter.OnItemClickListener listener;

    public RestaurantListAdapter(Restaurants[] arrRestautants, int layout, StoreListAdapter.OnItemClickListener listener) {
        this.arrRestautants = arrRestautants;
        this.layout = layout;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView card= (CardView) LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new RestaurantListAdapter.ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(arrRestautants[position]);
    }

    @Override
    public int getItemCount() {
        return arrRestautants.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView card;

        public ViewHolder(CardView itemView) {
            super(itemView);
            this.card = itemView;
        }

        public void bind(Restaurants restaurant) {
            ImageView img = card.findViewById(R.id.imgStoreItem);
            TextView tvName = card.findViewById(R.id.tvTitleStoreItem);
            TextView tvDesc = card.findViewById(R.id.tvDescStoreItem);

            img.setImageDrawable(card.getResources().getDrawable(restaurant.getImgId()));
            tvName.setText(restaurant.getName());
            tvDesc.setText(restaurant.getDescription());

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    // Personalized click listener
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}

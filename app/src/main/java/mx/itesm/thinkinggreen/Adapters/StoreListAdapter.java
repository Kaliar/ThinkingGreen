package mx.itesm.thinkinggreen.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mx.itesm.thinkinggreen.Models.Stores;
import mx.itesm.thinkinggreen.R;

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.ViewHolder> {
    private Stores[] arrStores;
    private int layout;
    private OnItemClickListener listener;

    public StoreListAdapter(Stores[] arrStores, int layout, OnItemClickListener listener) {
        this.arrStores = arrStores;
        this.layout = layout;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView card= (CardView) LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new StoreListAdapter.ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(arrStores[position]);

    }

    @Override
    public int getItemCount() {
        return arrStores.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView card;

        public ViewHolder(CardView itemView) {
            super(itemView);
            this.card = itemView;
        }


        public void bind(Stores store) {
            ImageView img = card.findViewById(R.id.imgStoreItem);
            TextView tvName = card.findViewById(R.id.tvTitleStoreItem);
            TextView tvDesc = card.findViewById(R.id.tvDescStoreItem);

            img.setImageDrawable(card.getResources().getDrawable(store.getImgId()));
            tvName.setText(store.getName());
            tvDesc.setText(store.getDescription());

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

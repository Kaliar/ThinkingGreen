package mx.itesm.thinkinggreen.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import mx.itesm.thinkinggreen.Models.RewardsCategories;
import mx.itesm.thinkinggreen.Models.RewardsItems;
import mx.itesm.thinkinggreen.R;

public class RewardsCategoryItemAdapter extends RecyclerView.Adapter<RewardsCategoryItemAdapter.ViewHolder> {

    private RewardsCategories[] arrCategorias;
    private RewardsItems[] arrItems;
    private int layout;
    private OnItemClickListener listener;

    public RewardsCategoryItemAdapter(RewardsCategories[] arrCategorias, int layout, OnItemClickListener listener) {
        this.arrCategorias = arrCategorias;
        this.layout = layout;
        this.listener = listener;
    }

    public RewardsCategoryItemAdapter(RewardsItems[] arrItems, int layout, OnItemClickListener listener) {
        this.arrItems = arrItems;
        this.layout = layout;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView card= (CardView) LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new RewardsCategoryItemAdapter.ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (arrCategorias != null){
            holder.bind(arrCategorias[position]);
        }
        else if (arrItems != null){
            holder.bind(arrItems[position]);
        }
    }

    @Override
    public int getItemCount() {
        if (arrCategorias != null && arrItems == null){
            return  arrCategorias.length;
        }
        else {
            return arrItems.length;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView card;
        public ViewHolder(CardView itemView) {
            super(itemView);
            this.card = itemView;
        }

        public void bind(RewardsCategories categoria) {
            TextView tvTitle = card.findViewById(R.id.tvTitleCatRew);
            ImageView imgCat = card.findViewById(R.id.imgCatRew);

            tvTitle.setText(categoria.getTitle());
            imgCat.setImageDrawable(card.getResources().getDrawable(categoria.getImgID()));

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }

        public void bind(RewardsItems item) {
            TextView tvTitle = card.findViewById(R.id.tvDescItemRew);
            TextView tvDesc = card.findViewById(R.id.tvTitleItemRew);
            ImageButton imgCart = card.findViewById(R.id.imgItemRew);
            TextView tvLeaf = card.findViewById(R.id.tvLeafRew);
            ImageView imgPrev = card.findViewById(R.id.imgThemePrev);

            tvTitle.setText(item.getTitle());
            tvDesc.setText(item.getCode());
            tvLeaf.setText(item.getPrice()+"");
            switch (item.getCode()){
                case R.string.strThemeDefaultCode:
                    imgPrev.setImageDrawable(card.getResources().getDrawable(R.drawable.defaulttheme));
                    break;
                case R.string.strThemeDarkCode:
                    imgPrev.setImageDrawable(card.getResources().getDrawable(R.drawable.darktheme));
                    break;
                case R.string.strThemeInvCode:
                    imgPrev.setImageDrawable(card.getResources().getDrawable(R.drawable.opaquetheme));
                    break;
                case R.string.strThemeLightCode:
                    imgPrev.setImageDrawable(card.getResources().getDrawable(R.drawable.pasteltheme));
                    break;
                case R.string.strThemeSeaCode:
                    imgPrev.setImageDrawable(card.getResources().getDrawable(R.drawable.aquatheme));
                    break;

            }
            //img.setImageDrawable(card.getResources().getDrawable(item.getImgID()));

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

package mx.itesm.thinkinggreen.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import mx.itesm.thinkinggreen.Models.Advices;
import mx.itesm.thinkinggreen.R;

// Says sets the contents of the CardView
public class AdviceListAdapter extends RecyclerView.Adapter<AdviceListAdapter.ViewHolder> {

    private Advices[] arrAdvs; // Dummy Array of advices
    private int layout; // XML Layout that will be inflated
    private OnItemClickListener listener; // OnClickListener

    // Constructor
    public AdviceListAdapter(Advices[] arrAdvs, int layout, OnItemClickListener listener) {
        this.arrAdvs = arrAdvs;
        this.layout = layout;
        this.listener = listener;
    }

    // Inflates the Card XML layout
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView card= (CardView) LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new AdviceListAdapter.ViewHolder(card);
    }

    // Card/UI components are linked
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // We call the bind method of the inner class in order to get the adapter position
        holder.bind(arrAdvs[position]);
    }

    // Returns the number of cards to display
    @Override
    public int getItemCount() {
        return arrAdvs.length;
    }

    // Inner class necessary for binding the RV with the Cards
    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView card;

        // Constructor
        public ViewHolder(CardView itemView) {
            super(itemView);
            this.card = itemView;
        }

        // Method that links the Card components
        public void bind(Advices advice) {
            // Find the components with the view passed by parameter
            TextView tvTitle = card.findViewById(R.id.tvTitleAdvItem);
            TextView tvDescr = card.findViewById(R.id.tvDescAdvItem);
            ImageView img = card.findViewById(R.id.imgAdvItem);

            // Set the Card contents according to the data set
            tvTitle.setText(advice.getTitle());
            tvDescr.setText(advice.getDescription());
            img.setImageDrawable(card.getResources().getDrawable(advice.getImgId()));
            //img.setBackground(card.getResources().getDrawable(advice.getImgId()));

            // Set the View OnClickListener with the listener of the inner interface
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
        // El primer parámetro es un objeto. Se puede mandar a otra actividad desde MainActivity.
        // La posición sirve en caso de que se quiera eliminar un elemento o reemplazar
        void onItemClick(int position);
    }
}

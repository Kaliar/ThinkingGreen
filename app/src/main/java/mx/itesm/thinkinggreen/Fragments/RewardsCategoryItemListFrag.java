package mx.itesm.thinkinggreen.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import mx.itesm.thinkinggreen.Adapters.RestaurantListAdapter;
import mx.itesm.thinkinggreen.Adapters.RewardsCategoryItemAdapter;
import mx.itesm.thinkinggreen.Adapters.StoreListAdapter;
import mx.itesm.thinkinggreen.Models.Restaurants;
import mx.itesm.thinkinggreen.Models.RewardsCategories;
import mx.itesm.thinkinggreen.Models.RewardsItems;
import mx.itesm.thinkinggreen.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RewardsCategoryItemListFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RewardsCategoryItemListFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RewardsCategoryItemListFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String VIENDO_CATEGORIAS = "viendoCategorias";
    private static final String CATEGORY = "category";

    // TODO: Rename and change types of parameters
    private boolean viendoCategorias;
    private int categoryPos;

    private OnFragmentInteractionListener mListener;

    public RewardsCategoryItemListFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param viendoCategorias Parameter 1.
     * //@param param2 Parameter 2.
     * @return A new instance of fragment RewardsCategoryItemListFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static RewardsCategoryItemListFrag newInstance(boolean viendoCategorias) {
        RewardsCategoryItemListFrag fragment = new RewardsCategoryItemListFrag();
        Bundle args = new Bundle();
        args.putBoolean(VIENDO_CATEGORIAS, viendoCategorias);
        fragment.setArguments(args);
        return fragment;
    }

    public static RewardsCategoryItemListFrag newInstance(boolean viendoCategorias, int category) {
        RewardsCategoryItemListFrag fragment = new RewardsCategoryItemListFrag();
        Bundle args = new Bundle();
        args.putBoolean(VIENDO_CATEGORIAS, viendoCategorias);
        args.putInt(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.viendoCategorias = getArguments().getBoolean(VIENDO_CATEGORIAS);
            if (getArguments().containsKey(CATEGORY)){
                this.categoryPos = getArguments().getInt(CATEGORY);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rewards_category_item_list, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        if (viendoCategorias){
            createCategoryAdapter();
        }
        else {
            createItemAdapter();
        }
    }

    private void createItemAdapter() {
        final RewardsItems[] arrItems = RewardsItems.getRewItems(this.categoryPos); // Hardcoded Advices Array (Temporal)
        Log.i("itemClick","categoryPos: "+categoryPos);
        Log.i("itemClick","ItemAdap recuperado ");
        // Instantiate an adapter for the advice list
        // Send the CardVIew XML for the advice
        RewardsCategoryItemAdapter adapter = new RewardsCategoryItemAdapter(arrItems, R.layout.card_reward_item,
                new RewardsCategoryItemAdapter.OnItemClickListener() {
                    // Define the onClick response for each card
                    @Override
                    public void onItemClick(int position) {
                        RewardsItems rewItem = arrItems[position];
                        Toast.makeText(getContext(),  "Seleccionaste el item: " + rewItem.getCode(), Toast.LENGTH_LONG).show();
                    }
                });

        // Link the Recycler View with the adapter
        RecyclerView rvRewCat = getActivity().findViewById(R.id.rvRewards);
        rvRewCat.setHasFixedSize(true);   // Same size for each card, enhances performance
        rvRewCat.setItemAnimator(new DefaultItemAnimator());
        rvRewCat.setLayoutManager(new LinearLayoutManager(getContext())); // Cards distribution
        rvRewCat.setAdapter(adapter);

    }

    private void createCategoryAdapter() {
        final RewardsCategories[] arrCateg = RewardsCategories.getRewCate(); // Hardcoded Advices Array (Temporal)

        // Instantiate an adapter for the advice list
        // Send the CardVIew XML for the advice
        RewardsCategoryItemAdapter adapter = new RewardsCategoryItemAdapter(arrCateg, R.layout.card_reward_category,
                new RewardsCategoryItemAdapter.OnItemClickListener() {
                    // Define the onClick response for each card
                    @Override
                    public void onItemClick(int position) {
                        Log.i("categoryClick","Todo chido");
                        Log.i("itemClick","categoryPos: "+position);
                        RewardsCategories category = arrCateg[position];
                        Toast.makeText(getContext(),  "Seleccionaste la categoría: " + category.getTitle(), Toast.LENGTH_LONG).show();

                        RewardsCategoryItemListFrag fragPlaceDesc = RewardsCategoryItemListFrag.newInstance(false, position); // Fragment of the advices of the week
                        Log.i("categoryClick","fragment items creado");
                        FragmentTransaction fragTrans = getActivity().getSupportFragmentManager().beginTransaction();
                        fragTrans.replace(R.id.frameRewards, fragPlaceDesc); // Set the AdviceWeek Layout
                        fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        fragTrans.addToBackStack(null);
                        fragTrans.commit(); // Schedule the operation into thread

                    }
                });

        // Link the Recycler View with the adapter
        RecyclerView rvRewCat = getActivity().findViewById(R.id.rvRewards);
        rvRewCat.setHasFixedSize(true);   // Same size for each card, enhances performance
        rvRewCat.setItemAnimator(new DefaultItemAnimator());
        rvRewCat.setLayoutManager(new LinearLayoutManager(getContext())); // Cards distribution
        rvRewCat.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

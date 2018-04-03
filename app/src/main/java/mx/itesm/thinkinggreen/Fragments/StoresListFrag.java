package mx.itesm.thinkinggreen.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import mx.itesm.thinkinggreen.Adapters.AdviceListAdapter;
import mx.itesm.thinkinggreen.Adapters.StoreListAdapter;
import mx.itesm.thinkinggreen.Models.Advices;
import mx.itesm.thinkinggreen.Models.Stores;
import mx.itesm.thinkinggreen.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StoresListFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StoresListFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoresListFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    protected static Stores store;
    private OnFragmentInteractionListener mListener;

    public StoresListFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoresListFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static StoresListFrag newInstance(String param1, String param2) {
        StoresListFrag fragment = new StoresListFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stores_list, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        createStoreListAdapter();
    }

    private void createStoreListAdapter() {
        final Stores[] arrStores = Stores.getArrStores(); // Hardcoded Advices Array (Temporal)

        // Instantiate an adapter for the advice list
        // Send the CardVIew XML for the advice
        StoreListAdapter adapter = new StoreListAdapter(arrStores, R.layout.card_store_item,
                new StoreListAdapter.OnItemClickListener() {
                    // Define the onClick response for each card
                    @Override
                    public void onItemClick(int position) {
                        store = arrStores[position];
                        Toast.makeText(getContext(),  "Seleccionaste la tienda: " + store.getName(), Toast.LENGTH_LONG).show();

                        PlaceDetailsFrag fragPlaceDesc = new PlaceDetailsFrag(); // Fragment of the advices of the week
                        FragmentTransaction fragTrans = getActivity().getSupportFragmentManager().beginTransaction();
                        fragTrans.replace(R.id.frameStores, fragPlaceDesc); // Set the AdviceWeek Layout
                        fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        fragTrans.addToBackStack(null);
                        fragTrans.commit(); // Schedule the operation into thread

                    }
                });

        // Link the Recycler View with the adapter
        RecyclerView rvAdvsList = getActivity().findViewById(R.id.rvStoresList);
        rvAdvsList.setHasFixedSize(true);   // Same size for each card, enhances performance
        rvAdvsList.setItemAnimator(new DefaultItemAnimator());
        rvAdvsList.setLayoutManager(new LinearLayoutManager(getContext())); // Cards distribution
        rvAdvsList.setAdapter(adapter);
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

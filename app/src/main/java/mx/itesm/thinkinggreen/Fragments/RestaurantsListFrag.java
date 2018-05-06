package mx.itesm.thinkinggreen.Fragments;

import android.app.ProgressDialog;
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

import com.parse.ParseGeoPoint;

import mx.itesm.thinkinggreen.Activities.RestaurantsActiv;
import mx.itesm.thinkinggreen.Adapters.RestaurantListAdapter;
import mx.itesm.thinkinggreen.Adapters.StoreListAdapter;
import mx.itesm.thinkinggreen.Models.Restaurants;
import mx.itesm.thinkinggreen.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RestaurantsListFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RestaurantsListFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestaurantsListFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    protected static Restaurants[] arrRestaurans;
    protected static Restaurants restaurant;

    public RestaurantsListFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RestaurantsListFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static RestaurantsListFrag newInstance(String param1, String param2) {
        RestaurantsListFrag fragment = new RestaurantsListFrag();
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
        return inflater.inflate(R.layout.fragment_restaurants_list_frag, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i("OnStart RestListFrag","ME CREE CHIDO");
        createRestaurantsAdapter();
    }

    private void createRestaurantsAdapter() {
        //TODO PONER LOCALIZACION DEL USUARIO AQUI
        ParseGeoPoint usrLocation = new ParseGeoPoint(RestaurantsActiv.userLocation.getLatitude(), RestaurantsActiv.userLocation.getLongitude());
        Log.i("CREATE ADP RestListFrag","VOA PEDIR LOS RESTAURANTES");
        final Restaurants[] arrRestaurants = Restaurants.getArrRestaurants(usrLocation, getContext()); // Hardcoded Advices Array (Temporal)
        Log.i("CREATE ADP RestListFrag","tENGO LOS RESTAURANTES");
        Log.i("CREATE ADP RestListFrag","rESAURANTES: " +(arrRestaurants == null));
        // Instantiate an adapter for the advice list
        // Send the CardVIew XML for the advice
        RestaurantListAdapter adapter = new RestaurantListAdapter(arrRestaurants, R.layout.card_store_item,
                new StoreListAdapter.OnItemClickListener() {
                    // Define the onClick response for each card
                    @Override
                    public void onItemClick(int position) {
                        restaurant = arrRestaurants[position];
                        Toast.makeText(getContext(),  "Seleccionaste el restaurante: " + restaurant.getName(), Toast.LENGTH_SHORT).show();

                        PlaceDetailsFrag fragPlaceDesc = PlaceDetailsFrag.newInstance(false); // Fragment of the advices of the week
                        FragmentTransaction fragTrans = getActivity().getSupportFragmentManager().beginTransaction();
                        fragTrans.replace(R.id.frameRestaurants, fragPlaceDesc); // Set the AdviceWeek Layout
                        fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        fragTrans.addToBackStack(null);
                        fragTrans.commit(); // Schedule the operation into thread

                    }
                });

        // Link the Recycler View with the adapter
        RecyclerView rvAdvsList = getActivity().findViewById(R.id.rvRestaurantsList);
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
       /* if (context instanceof OnFragmentInteractionListener) {
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

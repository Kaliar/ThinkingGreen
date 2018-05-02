package mx.itesm.thinkinggreen.Fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

import mx.itesm.thinkinggreen.Models.Restaurants;
import mx.itesm.thinkinggreen.Models.Stores;
import mx.itesm.thinkinggreen.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlaceDetailsFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlaceDetailsFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaceDetailsFrag extends Fragment {

    private TextView tvName;
    private TextView tvAddress;
    private TextView tvDescription;
    private TextView tvContact;
    private TextView tvTyPlace;
    private ImageView imgLogo;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String isStoreFlag = "isStoreFlag";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private boolean isStore;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PlaceDetailsFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * //@param param2 Parameter 2.
     * @return A new instance of fragment PlaceDetailsFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static PlaceDetailsFrag newInstance(boolean param1) {
        PlaceDetailsFrag fragment = new PlaceDetailsFrag();
        Bundle args = new Bundle();
        args.putBoolean(isStoreFlag, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isStore = getArguments().getBoolean(isStoreFlag);
        }
    }

    @Override
    public void onStart(){
        super.onStart();

        tvName = getActivity().findViewById(R.id.tvNamePlace);
        tvAddress = getActivity().findViewById(R.id.tvAddressFullPlace);
        tvContact = getActivity().findViewById(R.id.tvContactFullPlace);
        tvDescription = getActivity().findViewById(R.id.tvDescriptionFullPlace);
        tvTyPlace = getActivity().findViewById(R.id.tvTypePlace);
        imgLogo = getActivity().findViewById(R.id.imgPlace);
        if (isStore){
            displayStore();
        }
        else {
            displayRestaurant();
        }
    }

    private void displayRestaurant() {
        Restaurants restaurant = RestaurantsListFrag.restaurant;
        tvName.setText(restaurant.getName());
        tvAddress.setText(restaurant.getAddress());
        tvContact.setText(restaurant.getMail() + "\n" + restaurant.getPhone());
        tvDescription.setText(restaurant.getDescription());
        tvTyPlace.setText("Restaurante");
        ByteArrayInputStream is = new ByteArrayInputStream(restaurant.getImgId());
        Drawable drw = Drawable.createFromStream(is, "ABSOLUTE UNIT");
        imgLogo.setImageDrawable(drw);
        //imgLogo.setImageDrawable(getActivity().getResources().getDrawable(restaurant.getImgId()));
    }

    private void displayStore() {
        Stores store = StoresListFrag.store;
        tvName.setText(store.getName());
        tvAddress.setText(store.getAddress());
        tvContact.setText(store.getMail() + "\n" + store.getPhone());
        tvDescription.setText(store.getDescription());
        tvTyPlace.setText("Tienda");
        ByteArrayInputStream is = new ByteArrayInputStream(store.getImgId());
        Drawable drw = Drawable.createFromStream(is, "ABSOLUTE UNIT");
        imgLogo.setImageDrawable(drw);
        //imgLogo.setImageDrawable(getActivity().getResources().getDrawable(store.getImgId()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place_details, container, false);
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

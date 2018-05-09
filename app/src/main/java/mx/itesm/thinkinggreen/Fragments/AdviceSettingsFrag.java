package mx.itesm.thinkinggreen.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Set;

import mx.itesm.thinkinggreen.R;
import mx.itesm.thinkinggreen.Settings;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdviceSettingsFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AdviceSettingsFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdviceSettingsFrag extends Fragment {

    // TODO: Create UI elements

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private CheckBox cbRecycle;
    private CheckBox cbReduce;
    private CheckBox cbDIY;
    private CheckBox cbZeroWaste;
    private Button btnSaveCatPref;

    private OnFragmentInteractionListener mListener;

    public AdviceSettingsFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdviceSettingsFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static AdviceSettingsFrag newInstance(String param1, String param2) {
        AdviceSettingsFrag fragment = new AdviceSettingsFrag();
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

    //
    private void saveCatPrefs(){
        String catPrefs = "";
        if(cbRecycle.isChecked()){
            catPrefs = catPrefs + "Reciclaje ";
        }
        if(cbReduce.isChecked()){
            catPrefs = catPrefs + "Reducir ";
        }
        if(cbDIY.isChecked()){
            catPrefs = catPrefs + "DIY ";
        }
        if(cbZeroWaste.isChecked()){
            catPrefs = catPrefs + "ZeroWaste ";
        }
        if(catPrefs.equals(""))
            Toast.makeText(getContext(), "Por favor selecciona al menos UNA categoría", Toast.LENGTH_SHORT).show();
        else
            Settings.saveAdvPrefs(catPrefs, getContext());
    }

    private boolean updateCheckboxes(CheckBox cb, String[] catPrefs , String cat){
        //String[] cat = Settings.getAdvCategory();
        for (int i = 0; i < catPrefs.length; i++){
            if(cat.equals(catPrefs[i])){
                return true;
            }
            Log.i("Checkbox", "Cambie " + cat);

        }
        return false;
        /*switch (cat.length){
            case 1:
                cbRecycle.setChecked(true);
                cbReduce.setChecked(false);
                cbDIY.setChecked(false);
                cbZeroWaste.setChecked(false);
                break;
            case 2:
                cbRecycle.setChecked(true);
                cbReduce.setChecked(true);
                cbDIY.setChecked(false);
                cbZeroWaste.setChecked(false);
                break;
            case 3:
                cbRecycle.setChecked(true);
                cbReduce.setChecked(true);
                cbDIY.setChecked(true);
                cbZeroWaste.setChecked(false);
                break;
            case 4:
                cbRecycle.setChecked(true);
                cbReduce.setChecked(true);
                cbDIY.setChecked(true);
                cbZeroWaste.setChecked(true);
                break;
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_advice_settings, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();

        cbRecycle = getActivity().findViewById(R.id.cbRecycle);
        cbReduce = getActivity().findViewById(R.id.cbReduce);
        cbDIY = getActivity().findViewById(R.id.cbDIY);
        cbZeroWaste = getActivity().findViewById(R.id.cbZeroWate);

        //Setting checkboxes to user data
        if(!updateCheckboxes(cbRecycle, Settings.getAdvCategory(), "Reciclaje"))
            cbRecycle.setChecked(false);
        if(!updateCheckboxes(cbReduce, Settings.getAdvCategory(), "Reducir"))
            cbReduce.setChecked(false);
        if(!updateCheckboxes(cbDIY, Settings.getAdvCategory(), "DIY"))
            cbDIY.setChecked(false);
        if(!updateCheckboxes(cbZeroWaste, Settings.getAdvCategory(), "ZeroWaste"))
            cbZeroWaste.setChecked(false);


        btnSaveCatPref = getActivity().findViewById(R.id.btnSaveCat);
        btnSaveCatPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCatPrefs();
            }
        });
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

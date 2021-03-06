package mx.itesm.thinkinggreen.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.parse.LogInCallback;

import mx.itesm.thinkinggreen.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdviceWeekFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AdviceWeekFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdviceWeekFrag extends Fragment {

    private TextView tvTitle;
    private TextView tvDescription;
    private ImageView imgWeeklyAdvice;
    private Button btnRead;
    private boolean isRead =  false;
    // TODO: CREATE CODE FOR DOWNLOADING THE WEEK ADVICE FRM THE DB/INTERNET

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AdviceWeekFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdviceWeekFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static AdviceWeekFrag newInstance(String param1, String param2) {
        AdviceWeekFrag fragment = new AdviceWeekFrag();
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
        return inflater.inflate(R.layout.fragment_advice_week, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        tvTitle = getActivity().findViewById(R.id.tvTitleWeeklyAdvice);
        tvDescription = getActivity().findViewById(R.id.tvDescriptionWeeklyAdvice);
        imgWeeklyAdvice = getActivity().findViewById(R.id.imgWeeklyAdvice);
        btnRead = getActivity().findViewById(R.id.btnFinish);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickRead();
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

    private void clickRead(){
        if(!isRead) {
            ParseUser user = ParseUser.getCurrentUser();
            int points = (Integer) user.get("points");
            points++;
            user.put("points", points);
            user.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.str_getPoints), Toast.LENGTH_SHORT).show();
                        isRead = true;
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.strFailTitle), Toast.LENGTH_LONG).show();

                    }
                }
            });
        } else{
            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.str_AlreadyRead), Toast.LENGTH_SHORT).show();
        }
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

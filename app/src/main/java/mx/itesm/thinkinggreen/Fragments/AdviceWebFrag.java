package mx.itesm.thinkinggreen.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import mx.itesm.thinkinggreen.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdviceWebFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AdviceWebFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdviceWebFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String URL = "url";

    // TODO: Rename and change types of parameters
    private String url;

    private OnFragmentInteractionListener mListener;
    private Button btnRead;
    private boolean isRead =  false;

    public AdviceWebFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param link Parameter 1.
     * @return A new instance of fragment AdviceWebFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static AdviceWebFrag newInstance(String link) {
        AdviceWebFrag fragment = new AdviceWebFrag();
        Bundle args = new Bundle();
        args.putString(URL, link);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(URL);
        }
        else {
            url = "http://www.sustentator.com/blog-es/2017/07/tips-para-empezar-con-el-zero-waste/";
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        btnRead = getActivity().findViewById(R.id.btnWebRead);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickRead();
            }
        });
        WebView webView;
        webView=getActivity().findViewById(R.id.webWiew);
        webView.loadUrl(url);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_advice_web, container, false);
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

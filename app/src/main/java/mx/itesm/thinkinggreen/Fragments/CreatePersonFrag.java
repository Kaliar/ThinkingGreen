package mx.itesm.thinkinggreen.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
//Imports necessary to add in each class that connects to the back end
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import mx.itesm.thinkinggreen.Activities.LoginActiv;
import mx.itesm.thinkinggreen.R;
import mx.itesm.thinkinggreen.Settings;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreatePersonFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreatePersonFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatePersonFrag extends Fragment {

    private TextInputEditText etName;
    private TextInputEditText etMail;
    private TextInputEditText etPassword;
    private Button btnAddUser;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CreatePersonFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreatePersonFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static CreatePersonFrag newInstance(String param1, String param2) {
        CreatePersonFrag fragment = new CreatePersonFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Parse initialization
        Parse.initialize(getContext());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_person, container, false);

    }

    @Override
    public void onStart(){
        super.onStart();
        // Link UI components
        etName = getActivity().findViewById(R.id.teNameAddUser);
        etMail = getActivity().findViewById(R.id.teMailAddUser);
        etPassword = getActivity().findViewById(R.id.tePasswordAddUser);
        btnAddUser = getActivity().findViewById(R.id.btnAddUser);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerPerson();
            }
        });

    }

    private void registerPerson() {
        if (Settings.isNetAvailable((ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE))
            && Settings.isOnline()){
            //Create the user instance
            ParseUser user=new ParseUser();
            // Get User Input
            final String name = etName.getText().toString();
            String mail = etMail.getText().toString();
            String password = etPassword.getText().toString();

            //check if the fields arent empty
            if(name.equals("")){
                etName.setError(getResources().getString(R.string.strFieldError));
            }
            if(mail.equals("")){
                etMail.setError(getResources().getString(R.string.strFieldError));
            }
            if(password.equals("")){
                etPassword.setError(getResources().getString(R.string.strFieldError));
            } else {
                //assign the values to the user and send them to the db
                user.setUsername(name);
                user.setEmail(mail);
                user.setPassword(password);
                user.put("points", 0);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            //in case of succes will display an alert displayer
                            alertDisplayer("¡Registrado exitosamente!", "¡Bienvenido " + name + "!");
                        } else {
                            ParseUser.logOut();
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
        else {
            Toast.makeText(getContext(), R.string.strNoNetwork, Toast.LENGTH_LONG).show();
        }

    }
    private void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intLogin = new Intent(getActivity(), LoginActiv.class);
                        startActivity(intLogin);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
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

package org.robotics.tj2.scout88;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Pregame.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Pregame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pregame extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Performance currentMatch;

    public Pregame() {
        // Required empty public constructor
    }

    public Pregame(Performance currentMatch){
        this.currentMatch = currentMatch;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pregame.
     */
    // TODO: Rename and change types and number of parameters
    public static Pregame newInstance(String param1, String param2) {
        Pregame fragment = new Pregame();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v("Lifecycle" , "Pregame.onCreate() called.\n");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("Lifecycle" , "Pregame.onCreateView() called.\n");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pregame, container, false);
        final ImageButton panel_btn = (ImageButton) view.findViewById(R.id.panel_button);
        final ImageButton cargo_btn = (ImageButton) view.findViewById(R.id.cargo_button);
        final ImageView level_1_hab_start_btn = (ImageView) view.findViewById(R.id.level_1_hab_start);
        final ImageView level_2_hab_start_btn = (ImageView) view.findViewById(R.id.level_2_hab_start);
        panel_btn.setAlpha((float)0.4);
        cargo_btn.setAlpha((float)0.4);
        panel_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                panel_btn.setAlpha((float)1.0);
                cargo_btn.setAlpha((float)0.3);
                currentMatch.setStartingElement("panel");
            }
        });
        cargo_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                panel_btn.setAlpha((float)0.3);
                cargo_btn.setAlpha((float)1.0);
                currentMatch.setStartingElement("cargo");
            }
        });

        EditText teamNumberBox = (EditText) view.findViewById(R.id.team_number_edit_text);
        teamNumberBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentMatch.setTeamNumber(Integer.parseInt(s.toString()));
            }
        });

        EditText matchNumberBox = (EditText) view.findViewById(R.id.match_number_edit_text_box);
        matchNumberBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentMatch.setMatchNumber(Integer.parseInt(s.toString()));
            }
        });
        EditText scoutNameBox = (EditText) view.findViewById(R.id.scouter_edit_text_box);
        scoutNameBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentMatch.setScouter(s.toString());
            }
        });
        Spinner spinner = (Spinner) view.findViewById(R.id.alliance_dropdown);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.team_pos_array, android.R.layout.simple_spinner_dropdown_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        level_1_hab_start_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                level_1_hab_start_btn.setImageResource(R.drawable.ic_starting_pos_check);
                level_2_hab_start_btn.setImageResource(R.drawable.ic_starting_pos_uncheck);
                currentMatch.setStartingLevel(1);
            }
        });
        level_2_hab_start_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                level_1_hab_start_btn.setImageResource(R.drawable.ic_starting_pos_uncheck);
                level_2_hab_start_btn.setImageResource(R.drawable.ic_starting_pos_check);
                currentMatch.setStartingLevel(2);
            }
        });
        return view;
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
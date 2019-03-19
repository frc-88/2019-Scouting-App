package org.robotics.tj2.scout88.fragments;

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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.robotics.tj2.scout88.R;
import org.robotics.tj2.scout88.etc.Performance;

import java.util.Arrays;


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

    private final static String[] TEAM_NUMBERS = {"23",
            "58",
            "69",
            "78",
            "88",
            "97",
            "121",
            "125",
            "172",
            "173",
            "246",
            "1100",
            "1153",
            "1786",
            "1965",
            "2079",
            "2168",
            "2523",
            "2877",
            "3205",
            "3236",
            "3566",
            "4048",
            "4151",
            "4176",
            "5000",
            "5112",
            "5422",
            "5813",
            "5846",
            "6201",
            "6224",
            "6301",
            "6333",
            "6529",
            "6731",
            "6763"
    };

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Performance currentMatch;

    public String currentScouterName = "";
    public String autoFillMatchNumber;

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
        final ImageView deployed_true_btn = (ImageView) view.findViewById(R.id.deployArrow);
        final ImageView deployed_false_btn = (ImageView) view.findViewById(R.id.deployCross);
        final ImageView no_show_button = (ImageView) view.findViewById(R.id.no_show_button);

        panel_btn.setAlpha((float)0.3);
        cargo_btn.setAlpha((float)0.3);
        deployed_false_btn.setAlpha((float)0.3);
        deployed_true_btn.setAlpha((float)0.3);

        panel_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!currentMatch.getStarting_Game_Piece().equals("panel")){
                    panel_btn.setAlpha((float)1.0);
                    cargo_btn.setAlpha((float)0.3);
                    currentMatch.setStarting_Game_Piece("panel");
                }
                else{
                    panel_btn.setAlpha((float)0.3);
                    currentMatch.setStarting_Game_Piece("none");
                }
            }
        });
        cargo_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!currentMatch.getStarting_Game_Piece().equals("cargo")){
                    panel_btn.setAlpha((float)0.3);
                    cargo_btn.setAlpha((float)1.0);
                    currentMatch.setStarting_Game_Piece("cargo");
                }
                else{
                    cargo_btn.setAlpha((float)0.3);
                    currentMatch.setStarting_Game_Piece("none");
                }

            }
        });
        deployed_false_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deployed_false_btn.setAlpha((float)1.0);
                deployed_true_btn.setAlpha((float)0.3);
                currentMatch.setSandstorm_Cross(0);
            }
        });
        deployed_true_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deployed_false_btn.setAlpha((float)0.3);
                deployed_true_btn.setAlpha((float)1.0);
                currentMatch.setSandstorm_Cross(1);
            }
        });

        final AutoCompleteTextView teamNumberBox = (AutoCompleteTextView) view.findViewById(R.id.team_number_edit_text);

        ArrayAdapter<String> teamNumAdapter = new ArrayAdapter<>(getContext() , android.R.layout.simple_list_item_1 , TEAM_NUMBERS);
        teamNumberBox.setThreshold(0);
        teamNumberBox.setAdapter(teamNumAdapter);


        teamNumberBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    if(Arrays.asList(TEAM_NUMBERS).contains(s.toString())){
                        teamNumberBox.setError(null);
                        currentMatch.setTeam_Number(Integer.parseInt(s.toString()));
                    }else{
                        teamNumberBox.setError("Enter a Valid Team Number.");
                    }

                }
                catch(Exception e){
                    Log.v("88error" , e.toString());
                }
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
                try{
                    currentMatch.setMatch_Number(Integer.parseInt(s.toString()));
                } catch (Exception e){

                }

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
                try{
                    currentMatch.setScouter(s.toString());
                }catch (Exception e){

                }
            }
        });

        level_1_hab_start_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(currentMatch.getStarting_Position() == 1){
                    level_1_hab_start_btn.setImageResource(R.drawable.ic_starting_pos_uncheck);
                    currentMatch.setStarting_Position(0);
                }else {
                    level_1_hab_start_btn.setImageResource(R.drawable.ic_starting_pos_check);
                    level_2_hab_start_btn.setImageResource(R.drawable.ic_starting_pos_uncheck);
                    currentMatch.setStarting_Position(1);
                }
            }
        });
        level_2_hab_start_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(currentMatch.getStarting_Position() == 2){
                    level_2_hab_start_btn.setImageResource(R.drawable.ic_starting_pos_uncheck);
                    currentMatch.setStarting_Position(0);
                }
                else {
                    level_1_hab_start_btn.setImageResource(R.drawable.ic_starting_pos_uncheck);
                    level_2_hab_start_btn.setImageResource(R.drawable.ic_starting_pos_check);
                    currentMatch.setStarting_Position(2);
                }
            }
        });
        no_show_button.setAlpha((float)0.4);
        no_show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentMatch.getNo_Show() == 1){
                    no_show_button.setAlpha((float)0.4);
                    currentMatch.setNo_Show(0);
                }else{
                    no_show_button.setAlpha((float)1.0);
                    currentMatch.setNo_Show(1);
                }
                Log.v("performance" , "NO SHOW: " + currentMatch.getNo_Show());
            }
        });

        Log.v("activity_switch" , "pregame" + currentScouterName);
        scoutNameBox.setText(currentScouterName);
        matchNumberBox.setText(autoFillMatchNumber);


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
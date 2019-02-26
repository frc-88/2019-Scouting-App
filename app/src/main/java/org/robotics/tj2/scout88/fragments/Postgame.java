package org.robotics.tj2.scout88.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.robotics.tj2.scout88.R;
import org.robotics.tj2.scout88.activities.MainActivity;
import org.robotics.tj2.scout88.etc.FirebaseInterface;
import org.robotics.tj2.scout88.etc.Performance;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Postgame.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Postgame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Postgame extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Performance currentMatch;

    private OnFragmentInteractionListener mListener;

    public Postgame() {
        // Required empty public constructor
    }

    public Postgame(Performance p){
        currentMatch = p;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Postgame.
     */
    // TODO: Rename and change types and number of parameters
    public static Postgame newInstance(String param1, String param2) {
        Postgame fragment = new Postgame();
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
        View view = inflater.inflate(R.layout.fragment_postgame, container, false);
        final EditText commentBox = (EditText) view.findViewById(R.id.comment_box);

        Button commitBtn = (Button) view.findViewById(R.id.commit_data_button);
        try {
            if (currentMatch.getNoShow() == 1) {
                Log.v("performance", "NO SHOW POSTGAME: " + currentMatch.getNoShow());
                commitBtn.setBackgroundColor(Color.RED);
                commitBtn.setText("Commit No Show");
            }
        } catch (Exception e){

        }

        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitComments(commentBox);
                FirebaseInterface fbi = new FirebaseInterface();
                fbi.addPerformance(currentMatch);
                Intent intent = new Intent();
                intent.setClass(getContext() , MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("scouter_name" , currentMatch.getScouter());
                bundle.putString("new_match_number" , (currentMatch.getMatchNumber()+1) + "");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        final ImageView highClimbBtn = (ImageView) view.findViewById(R.id.high_hab);
        final ImageView midClimbBtn = (ImageView) view.findViewById(R.id.middle_hab);
        final ImageView lowClimbBtn = (ImageView) view.findViewById(R.id.low_hab);
        final ImageView noClimnBtn = (ImageView) view.findViewById(R.id.no_climb);
        noClimnBtn.setAlpha((float)0.3);

        highClimbBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                highClimbBtn.setImageResource(R.drawable.ic_starting_pos_check);
                midClimbBtn.setImageResource(R.drawable.ic_starting_pos_uncheck);
                lowClimbBtn.setImageResource(R.drawable.ic_starting_pos_uncheck);
                noClimnBtn.setAlpha((float)0.3);
                currentMatch.setLevelOfClimb(3);
            }
        });
        midClimbBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                highClimbBtn.setImageResource(R.drawable.ic_starting_pos_uncheck);
                midClimbBtn.setImageResource(R.drawable.ic_starting_pos_check);
                lowClimbBtn.setImageResource(R.drawable.ic_starting_pos_uncheck);
                noClimnBtn.setAlpha((float)0.3);
                currentMatch.setLevelOfClimb(2);
            }
        });
        lowClimbBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                highClimbBtn.setImageResource(R.drawable.ic_starting_pos_uncheck);
                midClimbBtn.setImageResource(R.drawable.ic_starting_pos_uncheck);
                lowClimbBtn.setImageResource(R.drawable.ic_starting_pos_check);
                noClimnBtn.setAlpha((float)0.3);
                currentMatch.setLevelOfClimb(1);
            }
        });

        noClimnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                highClimbBtn.setImageResource(R.drawable.ic_starting_pos_uncheck);
                midClimbBtn.setImageResource(R.drawable.ic_starting_pos_uncheck);
                lowClimbBtn.setImageResource(R.drawable.ic_starting_pos_uncheck);
                currentMatch.setLevelOfClimb(0);
                noClimnBtn.setAlpha((float)1.0);
            }
        });

        final ImageView emoji100 = view.findViewById(R.id.emoji_100);
        final ImageView emojiFlex = view.findViewById(R.id.emoji_flex);
        final ImageView emojiFacepalm = view.findViewById(R.id.emoji_facepalm);
        final ImageView emojiBroken = view.findViewById(R.id.emoji_broken);

        emoji100.setAlpha((float)0.4);
        emojiFlex.setAlpha((float)0.4);
        emojiFacepalm.setAlpha((float)0.4);
        emojiBroken.setAlpha((float)0.4);

        emoji100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentMatch.getMvp() == 1){
                    emoji100.setAlpha((float)0.4);
                    currentMatch.setMvp(0);
                    return;
                }
                currentMatch.setMvp(1);
                emoji100.setAlpha((float)1.0);
            }
        });

        emojiFlex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentMatch.getStrongDefense() == 1){
                    emojiFlex.setAlpha((float)0.4);
                    currentMatch.setStrongDefense(0);
                    return;
                }
                currentMatch.setStrongDefense(1);
                emojiFlex.setAlpha((float)1.0);
            }
        });

        emojiBroken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentMatch.getBroken() == 1){
                    emojiBroken.setAlpha((float)0.4);
                    currentMatch.setBroken(0);
                    return;
                }
                currentMatch.setBroken(1);
                emojiBroken.setAlpha((float)1.0);
            }
        });

        emojiFacepalm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentMatch.getBeans() == 1){
                    emojiFacepalm.setAlpha((float)0.4);
                    currentMatch.setBeans(0);
                    return;
                }
                currentMatch.setBeans(1);
                emojiFacepalm.setAlpha((float)1.0);
            }
        });

        return view;
    }

    public void commitComments(EditText commentBox){
        currentMatch.setComments(commentBox.getText().toString());
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

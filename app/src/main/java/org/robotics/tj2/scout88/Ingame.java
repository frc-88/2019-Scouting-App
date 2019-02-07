package org.robotics.tj2.scout88;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Ingame.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Ingame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Ingame extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public class RocketChanges{
        public int level;
        public boolean trueIfCargo;
        public int amount;

        public RocketChanges(int l , boolean tic , int amt){
            amount = amt;
            level = l;
            trueIfCargo = tic;
        }
    }

    public class CargoBayChanges{
        public int index;
        //0 if unscored, 1 if scored, 2 if scored in sandstorm
        public int scored;
        public boolean trueIfCargo;

        public CargoBayChanges(int i , int s , boolean tic){
            index = i;
            scored = s;
            trueIfCargo = tic;
        }
    }



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Stack<CargoBayChanges> cargoBayChangesStack = new Stack<>();
    private Stack<RocketChanges> rocketChangesStack = new Stack<>();
    private Performance currentMatch;

    private OnFragmentInteractionListener mListener;

    public Ingame() {
        // Required empty public constructor
    }

    public Ingame(Performance p){
        this.currentMatch = p;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Ingame.
     */
    // TODO: Rename and change types and number of parameters
    public static Ingame newInstance(String param1, String param2) {
        Ingame fragment = new Ingame();
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
        View view = inflater.inflate(R.layout.fragment_ingame, container, false);
        final ImageView[] cargoBayCargo = new ImageView[8];
        cargoBayCargo[0] = (ImageView) view.findViewById(R.id.cargo0);
        cargoBayCargo[1] = (ImageView) view.findViewById(R.id.cargo1);
        cargoBayCargo[2] = (ImageView) view.findViewById(R.id.cargo2);
        cargoBayCargo[3] = (ImageView) view.findViewById(R.id.cargo3);
        cargoBayCargo[4] = (ImageView) view.findViewById(R.id.cargo4);
        cargoBayCargo[5] = (ImageView) view.findViewById(R.id.cargo5);
        cargoBayCargo[6] = (ImageView) view.findViewById(R.id.cargo6);
        cargoBayCargo[7] = (ImageView) view.findViewById(R.id.cargo7);

        final ImageView[] cargoBayPanels = new ImageView[8];
        cargoBayPanels[0] = (ImageView) view.findViewById(R.id.panel0);
        cargoBayPanels[1] = (ImageView) view.findViewById(R.id.panel1);
        cargoBayPanels[2] = (ImageView) view.findViewById(R.id.panel2);
        cargoBayPanels[3] = (ImageView) view.findViewById(R.id.panel3);
        cargoBayPanels[4] = (ImageView) view.findViewById(R.id.panel4);
        cargoBayPanels[5] = (ImageView) view.findViewById(R.id.panel5);
        cargoBayPanels[6] = (ImageView) view.findViewById(R.id.panel6);
        cargoBayPanels[7] = (ImageView) view.findViewById(R.id.panel7);

        final TextView[] ssFlagsCargo = new TextView[8];
        ssFlagsCargo[0] = (TextView) view.findViewById(R.id.ss_flag_cargo0);
        ssFlagsCargo[1] = (TextView) view.findViewById(R.id.ss_flag_cargo1);
        ssFlagsCargo[2] = (TextView) view.findViewById(R.id.ss_flag_cargo2);
        ssFlagsCargo[3] = (TextView) view.findViewById(R.id.ss_flag_cargo3);
        ssFlagsCargo[4] = (TextView) view.findViewById(R.id.ss_flag_cargo4);
        ssFlagsCargo[5] = (TextView) view.findViewById(R.id.ss_flag_cargo5);
        ssFlagsCargo[6] = (TextView) view.findViewById(R.id.ss_flag_cargo6);
        ssFlagsCargo[7] = (TextView) view.findViewById(R.id.ss_flag_cargo7);

        final TextView[] ssFlagsPanels = new TextView[8];
        ssFlagsPanels[0] = (TextView) view.findViewById(R.id.ss_flag_panel0);
        ssFlagsPanels[1] = (TextView) view.findViewById(R.id.ss_flag_panel1);
        ssFlagsPanels[2] = (TextView) view.findViewById(R.id.ss_flag_panel2);
        ssFlagsPanels[3] = (TextView) view.findViewById(R.id.ss_flag_panel3);
        ssFlagsPanels[4] = (TextView) view.findViewById(R.id.ss_flag_panel4);
        ssFlagsPanels[5] = (TextView) view.findViewById(R.id.ss_flag_panel5);
        ssFlagsPanels[6] = (TextView) view.findViewById(R.id.ss_flag_panel6);
        ssFlagsPanels[7] = (TextView) view.findViewById(R.id.ss_flag_panel7);

        for(int ii = 0; ii < cargoBayCargo.length; ii++){
            final int iii = ii;
            ssFlagsCargo[ii].setAlpha((float)0.0);
            ssFlagsPanels[ii].setAlpha((float)0.0);
            cargoBayCargo[ii].setAlpha((float)0.4);
            cargoBayPanels[ii].setAlpha((float)0.4);
            cargoBayCargo[ii].setLongClickable(true);
            cargoBayPanels[ii].setLongClickable(true);
            cargoBayCargo[ii].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cargoBayChangesStack.push(new CargoBayChanges(iii, currentMatch.getCargo().get(iii) , true));
                    cargoBayCargo[iii].setAlpha((float)1.0);
                    ArrayList<Integer> cargo = currentMatch.getCargo();
                    cargo.set(iii , 1);
                    currentMatch.setCargo(cargo);

                }
            });
            cargoBayPanels[ii].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cargoBayChangesStack.push(new CargoBayChanges(iii, currentMatch.getPanels().get(iii) , false));
                    cargoBayPanels[iii].setAlpha((float)1.0);
                    ArrayList<Integer> panels = currentMatch.getPanels();
                    panels.set(iii , 1);
                    currentMatch.setPanels(panels);
                }
            });

            cargoBayCargo[ii].setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    cargoBayChangesStack.push(new CargoBayChanges(iii, currentMatch.getCargo().get(iii) , true));
                    ssFlagsCargo[iii].setAlpha((float)1.0);
                    ArrayList<Integer> cargo = currentMatch.getCargo();
                    cargo.set(iii , 2);
                    currentMatch.setCargo(cargo);
                    cargoBayCargo[iii].setAlpha((float)0.66);
                    return true;
                }
            });
            cargoBayPanels[ii].setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    cargoBayChangesStack.push(new CargoBayChanges(iii, currentMatch.getPanels().get(iii) , false));
                    ssFlagsPanels[iii].setAlpha((float)1.0);
                    ArrayList<Integer> panels = currentMatch.getPanels();
                    panels.set(iii , 2);
                    currentMatch.setPanels(panels);
                    cargoBayPanels[iii].setAlpha((float)0.66);
                    return true;
                }
            });

        }

        final ImageView cargoHigh = (ImageView) view.findViewById(R.id.high_cargo);
        final ImageView cargoMid = (ImageView) view.findViewById(R.id.mid_cargo);
        final ImageView cargoLow = (ImageView) view.findViewById(R.id.low_cargo);
        final ImageView panelHigh = (ImageView) view.findViewById(R.id.high_panel);
        final ImageView panelMid = (ImageView) view.findViewById(R.id.mid_panel);
        final ImageView panelLow = (ImageView) view.findViewById(R.id.low_panel);
        final TextView cargoHighText = (TextView) view.findViewById(R.id.cargo_high_number);
        final TextView cargoMidText = (TextView) view.findViewById(R.id.cargo_med_number);
        final TextView cargoLowText = (TextView) view.findViewById(R.id.cargo_low_number);
        final TextView panelHighText = (TextView) view.findViewById(R.id.panel_high_number);
        final TextView panelMidText = (TextView) view.findViewById(R.id.panel_med_number);
        final TextView panelLowText = (TextView) view.findViewById(R.id.panel_low_number);

        cargoHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RocketChanges rc = new RocketChanges(2 , true , currentMatch.getNumHighCargo());
                rocketChangesStack.push(rc);
                currentMatch.setNumHighCargo(currentMatch.getNumHighCargo() + 1);
                cargoHighText.setText(currentMatch.getNumHighCargo() + "");
            }
        });

        cargoMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RocketChanges rc = new RocketChanges(1 , true , currentMatch.getNumMedCargo());
                rocketChangesStack.push(rc);
                currentMatch.setNumMedCargo(currentMatch.getNumMedCargo() + 1);
                cargoMidText.setText(currentMatch.getNumMedCargo() + "");
            }
        });
        cargoLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RocketChanges rc = new RocketChanges(0 , true , currentMatch.getNumLowCargo());
                rocketChangesStack.push(rc);
                currentMatch.setNumLowCargo(currentMatch.getNumLowCargo() + 1);
                cargoLowText.setText(currentMatch.getNumLowCargo() + "");
            }
        });

        panelHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RocketChanges rc = new RocketChanges(2 , false , currentMatch.getNumHighPanels());
                rocketChangesStack.push(rc);
                currentMatch.setNumHighPanels(currentMatch.getNumHighPanels() + 1);
                panelHighText.setText(currentMatch.getNumHighPanels() + "");
            }
        });

        panelMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RocketChanges rc = new RocketChanges(1 , false , currentMatch.getNumMedPanels());
                rocketChangesStack.push(rc);
                currentMatch.setNumMedPanels(currentMatch.getNumMedPanels() + 1);
                panelMidText.setText(currentMatch.getNumMedPanels() + "");
            }
        });

        panelLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RocketChanges rc = new RocketChanges(0 , false , currentMatch.getNumLowPanels());
                rocketChangesStack.push(rc);
                currentMatch.setNumLowPanels(currentMatch.getNumLowPanels() + 1);
                panelLowText.setText(currentMatch.getNumLowPanels() + "");
            }
        });

        Button undoRocketBtn = (Button) view.findViewById(R.id.undo_rocket_button);
        undoRocketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rocketChangesStack.isEmpty()){
                    return;
                }
                RocketChanges rc = rocketChangesStack.pop();
                if (rc.trueIfCargo){
                    switch(rc.level){
                        case 0:
                            currentMatch.setNumLowCargo(rc.amount);
                            cargoLowText.setText(currentMatch.getNumLowCargo() + "");
                            break;
                        case 1:
                            currentMatch.setNumMedCargo(rc.amount);
                            cargoMidText.setText(currentMatch.getNumMedCargo() + "");
                            break;
                        case 2:
                            currentMatch.setNumHighCargo(rc.amount);
                            cargoHighText.setText(currentMatch.getNumHighCargo() + "");
                            break;
                        default:
                            break;
                    }
                }
                else{
                    switch(rc.level){
                        case 0:
                            currentMatch.setNumLowPanels(rc.amount);
                            panelLowText.setText(currentMatch.getNumLowPanels() + "");
                            break;
                        case 1:
                            currentMatch.setNumMedPanels(rc.amount);
                            panelMidText.setText(currentMatch.getNumMedPanels() + "");
                            break;
                        case 2:
                            currentMatch.setNumHighPanels(rc.amount);
                            panelHighText.setText(currentMatch.getNumHighPanels() + "");
                            break;
                        default:
                            break;
                    }

                }
            }
        });

        Button undoShipBtn = (Button) view.findViewById(R.id.undo_ship_button);
        undoShipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cargoBayChangesStack.isEmpty()){
                    return;
                }
                CargoBayChanges cbc = cargoBayChangesStack.pop();
                if(cbc.trueIfCargo){
                    ssFlagsCargo[cbc.index].setAlpha((float)0.0);
                    currentMatch.setIndividualCargoBayCargo(cbc.scored , cbc.index);
                    switch (cbc.scored){
                        case 1:
                            cargoBayCargo[cbc.index].setAlpha((float)1.0);
                            break;
                        case 2:
                            cargoBayCargo[cbc.index].setAlpha((float).66);
                            ssFlagsCargo[cbc.index].setAlpha((float)1.0);
                            break;
                        case 0:
                            cargoBayCargo[cbc.index].setAlpha((float).4);
                            break;
                    }
                }
                else{
                    ssFlagsPanels[cbc.index].setAlpha((float)0.0);
                    currentMatch.setIndividualCargoBayPanel(cbc.scored , cbc.index);
                    switch (cbc.scored){
                        case 1:
                            cargoBayPanels[cbc.index].setAlpha((float)1.0);
                            break;
                        case 2:
                            cargoBayPanels[cbc.index].setAlpha((float).66);
                            ssFlagsPanels[cbc.index].setAlpha((float)1.0);
                            break;
                        case 0:
                            cargoBayPanels[cbc.index].setAlpha((float).4);
                            break;
                    }
                }
            }
        });

        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (OnFragmentInteractionListener) context;
        } catch (Exception e) {
            Log.v("fragmentz" , "Could not attach: " + e.toString());
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

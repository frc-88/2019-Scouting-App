package org.robotics.tj2.scout88.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import org.robotics.tj2.scout88.etc.Performance;
import org.robotics.tj2.scout88.R;


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

    private boolean trueIfRocket = false;
    private int selectedItem = -1;
    private boolean trueIfCargo = false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Performance currentMatch;

    private OnFragmentInteractionListener mListener;

    private boolean sandstorm = true;

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
        final View view = inflater.inflate(R.layout.fragment_ingame, container, false);

        final Switch sandstormToggle = (Switch) view.findViewById(R.id.sandstorm_switch);
        final TextView sandstormModeText = (TextView) view.findViewById(R.id.sandstorm_mode_txt);
        sandstormToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sandstormToggle.setChecked(isChecked);
                sandstorm = isChecked;
                if(sandstorm){
                    sandstormModeText.setVisibility(View.VISIBLE);
                }else{
                    sandstormModeText.setVisibility(View.INVISIBLE);
                }
            }
        });

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

        final TextView cargoHighTextSS = (TextView) view.findViewById(R.id.cargo_high_number_ss);
        final TextView cargoMidTextSS = (TextView) view.findViewById(R.id.cargo_mid_number_ss);
        final TextView cargoLowTextSS = (TextView) view.findViewById(R.id.cargo_low_number_ss);
        final TextView panelHighTextSS = (TextView) view.findViewById(R.id.panel_high_number_ss);
        final TextView panelMidTextSS = (TextView) view.findViewById(R.id.panel_mid_number_ss);
        final TextView panelLowTextSS = (TextView) view.findViewById(R.id.panel_low_number_ss);

        for(int ii = 0; ii < cargoBayCargo.length; ii++){
            final int iii = ii;
            ssFlagsCargo[ii].setVisibility(View.INVISIBLE);
            ssFlagsPanels[ii].setVisibility(View.INVISIBLE);
            cargoBayCargo[ii].setAlpha((float)0.4);
            cargoBayPanels[ii].setAlpha((float)0.4);
            cargoBayCargo[ii].setPadding(3,3,3,3);
            cargoBayCargo[ii].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedItem = iii;
                    trueIfRocket = false;
                    trueIfCargo = true;

                    cargoHigh.setImageResource(R.drawable.cargo);
                    cargoMid.setImageResource(R.drawable.cargo);
                    cargoLow.setImageResource(R.drawable.cargo);
                    panelHigh.setImageResource(R.drawable.panel);
                    panelMid.setImageResource(R.drawable.panel);
                    panelLow.setImageResource(R.drawable.panel);

                    for(int n = 0;  n < cargoBayCargo.length; n++){
                        cargoBayCargo[n].setImageResource(R.drawable.cargo);
                        cargoBayPanels[n].setImageResource(R.drawable.panel);
                    }
                    cargoBayCargo[iii].setImageResource(R.drawable.cargo_selected);

                }
            });
            cargoBayPanels[ii].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedItem = iii;
                    trueIfRocket = false;
                    trueIfCargo = false;

                    cargoHigh.setImageResource(R.drawable.cargo);
                    cargoMid.setImageResource(R.drawable.cargo);
                    cargoLow.setImageResource(R.drawable.cargo);
                    panelHigh.setImageResource(R.drawable.panel);
                    panelMid.setImageResource(R.drawable.panel);
                    panelLow.setImageResource(R.drawable.panel);

                    for(int n = 0;  n < cargoBayPanels.length; n++){
                        cargoBayCargo[n].setImageResource(R.drawable.cargo);
                        cargoBayPanels[n].setImageResource(R.drawable.panel);
                    }
                    cargoBayPanels[iii].setImageResource(R.drawable.panel_selected);
                }
            });

        }

        cargoHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem = 0;
                trueIfRocket = true;
                trueIfCargo = true;

                cargoHigh.setImageResource(R.drawable.cargo_selected);
                cargoMid.setImageResource(R.drawable.cargo);
                cargoLow.setImageResource(R.drawable.cargo);
                panelHigh.setImageResource(R.drawable.panel);
                panelMid.setImageResource(R.drawable.panel);
                panelLow.setImageResource(R.drawable.panel);

                for(int n = 0;  n < cargoBayPanels.length; n++){
                    cargoBayCargo[n].setImageResource(R.drawable.cargo);
                    cargoBayPanels[n].setImageResource(R.drawable.panel);
                }
            }
        });

        cargoMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem = 1;
                trueIfRocket = true;
                trueIfCargo = true;

                cargoHigh.setImageResource(R.drawable.cargo);
                cargoMid.setImageResource(R.drawable.cargo_selected);
                cargoLow.setImageResource(R.drawable.cargo);
                panelHigh.setImageResource(R.drawable.panel);
                panelMid.setImageResource(R.drawable.panel);
                panelLow.setImageResource(R.drawable.panel);

                for(int n = 0;  n < cargoBayPanels.length; n++){
                    cargoBayCargo[n].setImageResource(R.drawable.cargo);
                    cargoBayPanels[n].setImageResource(R.drawable.panel);
                }
            }
        });

        cargoLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem = 2;
                trueIfRocket = true;
                trueIfCargo = true;

                cargoHigh.setImageResource(R.drawable.cargo);
                cargoMid.setImageResource(R.drawable.cargo);
                cargoLow.setImageResource(R.drawable.cargo_selected);
                panelHigh.setImageResource(R.drawable.panel);
                panelMid.setImageResource(R.drawable.panel);
                panelLow.setImageResource(R.drawable.panel);

                for(int n = 0;  n < cargoBayPanels.length; n++){
                    cargoBayCargo[n].setImageResource(R.drawable.cargo);
                    cargoBayPanels[n].setImageResource(R.drawable.panel);
                }
            }
        });

        panelHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem = 3;
                trueIfRocket = true;
                trueIfCargo = false;

                cargoHigh.setImageResource(R.drawable.cargo);
                cargoMid.setImageResource(R.drawable.cargo);
                cargoLow.setImageResource(R.drawable.cargo);
                panelHigh.setImageResource(R.drawable.panel_selected);
                panelMid.setImageResource(R.drawable.panel);
                panelLow.setImageResource(R.drawable.panel);

                for(int n = 0;  n < cargoBayPanels.length; n++){
                    cargoBayCargo[n].setImageResource(R.drawable.cargo);
                    cargoBayPanels[n].setImageResource(R.drawable.panel);
                }
            }
        });

        panelMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem = 4;
                trueIfRocket = true;
                trueIfCargo = false;

                cargoHigh.setImageResource(R.drawable.cargo);
                cargoMid.setImageResource(R.drawable.cargo);
                cargoLow.setImageResource(R.drawable.cargo);
                panelHigh.setImageResource(R.drawable.panel);
                panelMid.setImageResource(R.drawable.panel_selected);
                panelLow.setImageResource(R.drawable.panel);

                for(int n = 0;  n < cargoBayPanels.length; n++){
                    cargoBayCargo[n].setImageResource(R.drawable.cargo);
                    cargoBayPanels[n].setImageResource(R.drawable.panel);
                }
            }
        });

        panelLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem = 5;
                trueIfRocket = true;
                trueIfCargo = false;

                cargoHigh.setImageResource(R.drawable.cargo);
                cargoMid.setImageResource(R.drawable.cargo);
                cargoLow.setImageResource(R.drawable.cargo);
                panelHigh.setImageResource(R.drawable.panel);
                panelMid.setImageResource(R.drawable.panel);
                panelLow.setImageResource(R.drawable.panel_selected);

                for(int n = 0;  n < cargoBayPanels.length; n++){
                    cargoBayCargo[n].setImageResource(R.drawable.cargo);
                    cargoBayPanels[n].setImageResource(R.drawable.panel);
                }
            }
        });


        Button addButton = view.findViewById(R.id.add_button);
        Button deleteButton = view.findViewById(R.id.delete_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem == -1) {
                    return;
                }
                if (!sandstorm) {
                    if (trueIfRocket) {
                        switch (selectedItem) {
                            case 0:
                                currentMatch.setHigh_Cargo(currentMatch.getHigh_Cargo() + 1);
                                cargoHighText.setText(currentMatch.getHigh_Cargo() + "");
                                break;
                            case 1:
                                currentMatch.setMiddle_Cargo(currentMatch.getMiddle_Cargo() + 1);
                                cargoMidText.setText(currentMatch.getMiddle_Cargo() + "");
                                break;
                            case 2:
                                currentMatch.setLow_Cargo_Rocket(currentMatch.getLow_Cargo_Rocket() + 1);
                                cargoLowText.setText(currentMatch.getLow_Cargo_Rocket() + "");
                                break;
                            case 3:
                                currentMatch.setHigh_Panels(currentMatch.getHigh_Panels() + 1);
                                panelHighText.setText(currentMatch.getHigh_Panels() + "");
                                break;
                            case 4:
                                currentMatch.setMiddle_Panels(currentMatch.getMiddle_Panels() + 1);
                                panelMidText.setText(currentMatch.getMiddle_Panels() + "");
                                break;
                            case 5:
                                currentMatch.setLow_Panels_Rocket(currentMatch.getLow_Panels_Rocket() + 1);
                                panelLowText.setText(currentMatch.getLow_Panels_Rocket() + "");
                                break;
                            default:
                                break;
                        }
                    } else {
                        if (trueIfCargo) {
                            currentMatch.setIndividualCargoBayCargo(1, selectedItem);
                            cargoBayCargo[selectedItem].setAlpha((float) 1.0);
                            ssFlagsCargo[selectedItem].setVisibility(View.INVISIBLE);
                        } else {
                            currentMatch.setIndividualCargoBayPanel(1, selectedItem);
                            cargoBayPanels[selectedItem].setAlpha((float) 1.0);
                            ssFlagsPanels[selectedItem].setVisibility(View.INVISIBLE);
                        }
                    }
                }else{
                    if(trueIfRocket){
                        switch (selectedItem){
                            case 0:
                                currentMatch.setSandstorm_High_Cargo(currentMatch.getSandstorm_High_Cargo() + 1);
                                cargoHighTextSS.setText(currentMatch.getSandstorm_High_Cargo() + " Sandstorm");
                                cargoHighTextSS.setVisibility(View.VISIBLE);
                                break;
                            case 1:
                                currentMatch.setSandstorm_Middle_Cargo(currentMatch.getSandstorm_Middle_Cargo() + 1);
                                cargoMidTextSS.setText(currentMatch.getSandstorm_Middle_Cargo() + " Sandstorm");
                                cargoMidTextSS.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                currentMatch.setSandstorm_Low_Cargo_Rocket(currentMatch.getSandstorm_Low_Cargo_Rocket() + 1);
                                cargoLowTextSS.setText(currentMatch.getSandstorm_Low_Cargo_Rocket() + " Sandstorm");
                                cargoLowTextSS.setVisibility(View.VISIBLE);
                                break;
                            case 3:
                                currentMatch.setSandstorm_High_Panels(currentMatch.getSandstorm_High_Panels() + 1);
                                panelHighTextSS.setText(currentMatch.getSandstorm_High_Panels() + " Sandstorm");
                                panelHighTextSS.setVisibility(View.VISIBLE);
                                break;
                            case 4:
                                currentMatch.setSandstorm_Middle_Panels(currentMatch.getSandstorm_Middle_Panels() + 1);
                                panelMidTextSS.setText(currentMatch.getSandstorm_Middle_Panels() + " Sandstorm");
                                panelMidTextSS.setVisibility(View.VISIBLE);
                                break;
                            case 5:
                                currentMatch.setSandstorm_Low_Panels_Rocket(currentMatch.getSandstorm_Low_Panels_Rocket() + 1);
                                panelLowTextSS.setText(currentMatch.getSandstorm_Low_Panels_Rocket() + " Sandstorm");
                                panelLowTextSS.setVisibility(View.VISIBLE);
                                break;
                            default:
                                break;
                        }
                    }
                    else{
                        if(trueIfCargo){
                            currentMatch.setIndividualCargoBayCargo(2, selectedItem);
                            cargoBayCargo[selectedItem].setAlpha((float)0.8);
                            ssFlagsCargo[selectedItem].setVisibility(View.VISIBLE);
                            ssFlagsCargo[selectedItem].bringToFront();
                        }else{
                            currentMatch.setIndividualCargoBayPanel(2 , selectedItem);
                            cargoBayPanels[selectedItem].setAlpha((float)0.8);
                            ssFlagsPanels[selectedItem].setVisibility(View.VISIBLE);
                            ssFlagsPanels[selectedItem].bringToFront();
                        }
                    }
                }
                cargoHigh.setImageResource(R.drawable.cargo);
                cargoMid.setImageResource(R.drawable.cargo);
                cargoLow.setImageResource(R.drawable.cargo);
                panelHigh.setImageResource(R.drawable.panel);
                panelMid.setImageResource(R.drawable.panel);
                panelLow.setImageResource(R.drawable.panel);

                for(int n = 0;  n < cargoBayPanels.length; n++){
                    cargoBayCargo[n].setImageResource(R.drawable.cargo);
                    cargoBayPanels[n].setImageResource(R.drawable.panel);
                }
                selectedItem = -1;
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedItem == -1) {
                    return;
                }
                if(sandstorm && trueIfRocket){
                    switch (selectedItem){
                        case 0:
                            currentMatch.setSandstorm_High_Cargo(currentMatch.getSandstorm_High_Cargo() - 1);
                            if(currentMatch.getSandstorm_High_Cargo() == 0){
                                cargoHighTextSS.setVisibility(View.INVISIBLE);
                            }else{
                                cargoHighTextSS.setText(currentMatch.getSandstorm_High_Cargo() + " Sandstorm");
                                cargoHighTextSS.setVisibility(View.VISIBLE);
                            }
                            break;
                        case 1:
                            currentMatch.setSandstorm_Middle_Cargo(currentMatch.getSandstorm_Middle_Cargo() - 1);
                            if(currentMatch.getSandstorm_Middle_Cargo() == 0){
                                cargoMidTextSS.setVisibility(View.INVISIBLE);
                            }else{
                                cargoMidTextSS.setText(currentMatch.getSandstorm_Middle_Cargo() + " Sandstorm");
                                cargoMidTextSS.setVisibility(View.VISIBLE);
                            }
                            break;
                        case 2:
                            currentMatch.setSandstorm_Low_Cargo_Rocket(currentMatch.getSandstorm_Low_Cargo_Rocket() - 1);
                            if(currentMatch.getSandstorm_Low_Cargo_Rocket() == 0){
                                cargoLowTextSS.setVisibility(View.INVISIBLE);
                            }else{
                                cargoLowTextSS.setText(currentMatch.getSandstorm_Low_Cargo_Rocket() + " Sandstorm");
                                cargoLowTextSS.setVisibility(View.VISIBLE);
                            }
                            break;
                        case 3:
                            currentMatch.setSandstorm_High_Panels(currentMatch.getSandstorm_High_Panels() - 1);
                            if(currentMatch.getSandstorm_High_Panels() == 0){
                                panelHighTextSS.setVisibility(View.INVISIBLE);
                            }else{
                                panelHighTextSS.setText(currentMatch.getSandstorm_High_Panels() + " Sandstorm");
                                panelHighTextSS.setVisibility(View.VISIBLE);
                            }
                            break;
                        case 4:
                            currentMatch.setSandstorm_Middle_Panels(currentMatch.getSandstorm_Middle_Panels() - 1);
                            if(currentMatch.getSandstorm_Middle_Panels() == 0){
                                panelMidTextSS.setVisibility(View.INVISIBLE);
                            }else{
                                panelMidTextSS.setText(currentMatch.getSandstorm_Middle_Panels() + " Sandstorm");
                                panelMidTextSS.setVisibility(View.VISIBLE);
                            }
                            break;
                        case 5:
                            currentMatch.setSandstorm_Low_Panels_Rocket(currentMatch.getSandstorm_Low_Panels_Rocket() - 1);
                            if(currentMatch.getSandstorm_Low_Panels_Rocket() == 0){
                                panelLowTextSS.setVisibility(View.INVISIBLE);
                            }else{
                                panelLowTextSS.setText(currentMatch.getSandstorm_Low_Panels_Rocket() + " Sandstorm");
                                panelLowTextSS.setVisibility(View.VISIBLE);
                            }
                            break;
                        default:
                            break;
                    }
                }
                else if(trueIfRocket){
                    switch (selectedItem){
                        case 0:
                            currentMatch.setHigh_Cargo(currentMatch.getHigh_Cargo() - 1);
                            cargoHighText.setText(currentMatch.getHigh_Cargo() + "");
                            break;
                        case 1:
                            currentMatch.setMiddle_Cargo(currentMatch.getMiddle_Cargo() - 1);
                            cargoMidText.setText(currentMatch.getMiddle_Cargo() + "");
                            break;
                        case 2:
                            currentMatch.setLow_Cargo_Rocket(currentMatch.getLow_Cargo_Rocket() - 1);
                            cargoLowText.setText(currentMatch.getLow_Cargo_Rocket() + "");
                            break;
                        case 3:
                            currentMatch.setHigh_Panels(currentMatch.getHigh_Panels() - 1);
                            panelHighText.setText(currentMatch.getHigh_Panels() + "");
                            break;
                        case 4:
                            currentMatch.setMiddle_Panels(currentMatch.getMiddle_Panels() - 1);
                            panelMidText.setText(currentMatch.getMiddle_Panels() + "");
                            break;
                        case 5:
                            currentMatch.setLow_Panels_Rocket(currentMatch.getLow_Panels_Rocket() - 1);
                            panelLowText.setText(currentMatch.getLow_Panels_Rocket() + "");
                            break;
                        default:
                            break;
                    }
                }
                else{
                    if(trueIfCargo){
                        currentMatch.setIndividualCargoBayCargo(0, selectedItem);
                        cargoBayCargo[selectedItem].setAlpha((float)0.4);
                        ssFlagsCargo[selectedItem].setVisibility(View.INVISIBLE);
                    }else{
                        currentMatch.setIndividualCargoBayPanel(0 , selectedItem);
                        cargoBayPanels[selectedItem].setAlpha((float)0.4);
                        ssFlagsPanels[selectedItem].setVisibility(View.INVISIBLE);
                    }
                }
                cargoHigh.setImageResource(R.drawable.cargo);
                cargoMid.setImageResource(R.drawable.cargo);
                cargoLow.setImageResource(R.drawable.cargo);
                panelHigh.setImageResource(R.drawable.panel);
                panelMid.setImageResource(R.drawable.panel);
                panelLow.setImageResource(R.drawable.panel);

                for(int n = 0;  n < cargoBayPanels.length; n++){
                    cargoBayCargo[n].setImageResource(R.drawable.cargo);
                    cargoBayPanels[n].setImageResource(R.drawable.panel);
                }
                selectedItem = -1;
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

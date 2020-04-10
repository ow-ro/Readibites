package ws.roberts.android.readibites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.UUID;

import static android.widget.CompoundButton.*;

public class RBLocationFragment extends Fragment {

    private RBLocation mLocation;

    private EditText mTitleField;
    private EditText mTypeField;
    private EditText mSubtitleField;

    private EditText mVegStatus;
    private EditText mCalStatus;
    private EditText mGFStatus;

    private ImageView mGreenVegDetail;
    private ImageView mAmberVegDetail;
    private ImageView mRedVegDetail;

    private ImageView mGreenGFDetail;
    private ImageView mAmberGFDetail;
    private ImageView mRedGFDetail;

    private ImageView mGreenCalDetail;
    private ImageView mAmberCalDetail;
    private ImageView mRedCalDetail;

    private Button mMapButton;
    private Button mAllergenButton;

    private EditText mPhoneField;
    private ImageView mPhoneImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID locationId = (UUID) getActivity().getIntent().getSerializableExtra(RBLocationActivity.EXTRA_LOCATION_ID);
        mLocation = RBLocationLab.get(getActivity()).getLocation(locationId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rblocation, container, false);

        mTitleField = (EditText) v.findViewById(R.id.location_title);
        mTitleField.setText(mLocation.getTitle());
        mTitleField.setFocusable(false);

        mTypeField = (EditText) v.findViewById(R.id.location_type);
        if (mLocation.isRestaurant()) {
            mTypeField.setText("Restaurant");
        } else if (mLocation.isBar()) {
            mTypeField.setText("Bar");
        } else if (mLocation.isCafe()) {
            mTypeField.setText("Cafe");
        } else if (mLocation.isDessertBar()) {
            mTypeField.setText("Dessert Bar");
        } else if (mLocation.isGastroPub()) {
            mTypeField.setText("Gastropub");
        } else if (mLocation.isJuiceBar()) {
            mTypeField.setText("Juice Bar");
        }
        mTypeField.setFocusable(false);

        mSubtitleField = (EditText) v.findViewById(R.id.location_subtitle);
        mSubtitleField.setText(mLocation.getSubtitle());
        mSubtitleField.setFocusable(false);

        mGreenVegDetail = (ImageView) v.findViewById(R.id.ic_green_veg_detail);
        mAmberVegDetail = (ImageView) v.findViewById(R.id.ic_amber_veg_detail);
        mRedVegDetail = (ImageView) v.findViewById(R.id.ic_red_veg_detail);
            mGreenVegDetail.setVisibility(View.GONE);
            mAmberVegDetail.setVisibility(View.GONE);
            mRedVegDetail.setVisibility(View.GONE);

        mVegStatus = (EditText) v.findViewById(R.id.location_veg_status);
        if (mLocation.getVegStatus() == 1) {
            mGreenVegDetail.setVisibility(View.VISIBLE);
            mVegStatus.setText("Vegan & vegetarian options listed on the menu");
        } else if (mLocation.getVegStatus() == 2) {
            mAmberVegDetail.setVisibility(View.VISIBLE);
            mVegStatus.setText("Vegetarian options listed on the menu");
        } else if (mLocation.getVegStatus() == 3) {
            mRedVegDetail.setVisibility(View.VISIBLE);
            mVegStatus.setText("No listed vegan or vegetarian options");
        }
        mVegStatus.setFocusable(false);

        mGreenCalDetail = (ImageView) v.findViewById(R.id.ic_green_cal_detail);
        mAmberCalDetail = (ImageView) v.findViewById(R.id.ic_amber_cal_detail);
        mRedCalDetail = (ImageView) v.findViewById(R.id.ic_red_cal_detail);
            mGreenCalDetail.setVisibility(View.GONE);
            mAmberCalDetail.setVisibility(View.GONE);
            mRedCalDetail.setVisibility(View.GONE);

        mCalStatus = (EditText) v.findViewById(R.id.location_cal_status);
        if (mLocation.getCalStatus() == 1) {
            mGreenCalDetail.setVisibility(View.VISIBLE);
            mCalStatus.setText("All menu items listed with calories");
        } else if (mLocation.getCalStatus() == 2) {
            mAmberCalDetail.setVisibility(View.VISIBLE);
            mCalStatus.setText("Some menu items are listed with calories");
        } else if (mLocation.getCalStatus() == 3) {
            mRedCalDetail.setVisibility(View.VISIBLE);
            mCalStatus.setText("No calories listed on the menu");
        }
        mCalStatus.setFocusable(false);

        mGreenGFDetail = (ImageView) v.findViewById(R.id.ic_green_gf_detail);
        mAmberGFDetail = (ImageView) v.findViewById(R.id.ic_amber_gf_detail);
        mRedGFDetail = (ImageView) v.findViewById(R.id.ic_red_gf_detail);
        mGreenGFDetail.setVisibility(View.GONE);
        mAmberGFDetail.setVisibility(View.GONE);
        mRedGFDetail.setVisibility(View.GONE);

        mGFStatus = (EditText) v.findViewById(R.id.location_gf_status);
        if (mLocation.getGFStatus() == 1) {
            mGreenGFDetail.setVisibility(View.VISIBLE);
            mGFStatus.setText("Gluten free options listed on the menu");
        } else if (mLocation.getGFStatus() == 2) {
            mAmberGFDetail.setVisibility(View.VISIBLE);
            mGFStatus.setText("Allergen info regarding gluten free options");
        } else if (mLocation.getGFStatus() == 3) {
            mRedGFDetail.setVisibility(View.VISIBLE);
            mGFStatus.setText("No gluten free information found");
        }
        mGFStatus.setFocusable(false);

        mMapButton = (Button) v.findViewById(R.id.map_link);
        mMapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(mLocation.getMapSearch()));
                startActivity(intent);
            }
        });

        mAllergenButton = (Button) v.findViewById(R.id.allergen_link);
        mAllergenButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(mLocation.getAllergenLink()));
                startActivity(intent);
            }
        });

            if (mLocation.getAllergenLink().startsWith("null")) {
                mAllergenButton.setVisibility(View.GONE);
            } else {
                mAllergenButton.setVisibility(View.VISIBLE);
            }

        mPhoneField = (EditText) v.findViewById(R.id.location_phone);
        mPhoneField.setText(mLocation.getPhoneNum());
        mPhoneField.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + mLocation.getPhoneNum()));
                startActivity(intent);
            }
        });
        mPhoneField.setFocusable(false);

        mPhoneImageView = (ImageView) v.findViewById(R.id.ic_phone);
        mPhoneImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + mLocation.getPhoneNum()));
                startActivity(intent);
            }
        });


        return v;
    }
}

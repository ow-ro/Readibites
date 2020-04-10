package ws.roberts.android.readibites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutContactFragment extends Fragment {

    private EditText mAboutContent;
    private EditText mContactContent;
    private TextView mTwitterText;
    private ImageView mTwitterIcon;

    public static AboutContactFragment newInstance() {
        return new AboutContactFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about_contact, container, false);

        mAboutContent = (EditText) v.findViewById(R.id.about_content);
        mAboutContent.setFocusable(false);

        mContactContent = (EditText) v.findViewById(R.id.contact_content);
        mContactContent.setFocusable(false);

        mTwitterText = (TextView) v.findViewById(R.id.twitter);
        mTwitterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://twitter.com/DinoMines"));
                startActivity(intent);
            }
        });
        mTwitterText.setFocusable(false);

        mTwitterIcon = (ImageView) v.findViewById(R.id.ic_twitter);
        mTwitterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://twitter.com/DinoMines"));
                startActivity(intent);
            }
        });

        return v;
    }
}

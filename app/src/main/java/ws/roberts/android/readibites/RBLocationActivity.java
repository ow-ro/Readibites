package ws.roberts.android.readibites;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class RBLocationActivity extends SingleFragmentActivity {

    public static final String EXTRA_LOCATION_ID = "ws.roberts.android.readibites.location_id";

    public static Intent newIntent(Context packageContext, UUID locationId) {
        Intent intent = new Intent(packageContext, RBLocationActivity.class);
        intent.putExtra(EXTRA_LOCATION_ID, locationId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new RBLocationFragment();
    }

}

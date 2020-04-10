package ws.roberts.android.readibites;

import android.support.v4.app.Fragment;

public class RBLocationListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new RBLocationListFragment();
    }
}

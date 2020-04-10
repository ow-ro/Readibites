package ws.roberts.android.readibites;

import android.support.v4.app.Fragment;

public class AboutContactActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return AboutContactFragment.newInstance();
    }

}

package ws.roberts.android.readibites;

import android.app.SearchManager;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RBLocationListFragment extends Fragment {

    private RecyclerView mRBLocationRecyclerView;
    private RBLocationAdapter mAdapter;

    private MenuItem restFilter;
    private MenuItem barFilter;
    private MenuItem cafeFilter;
    private MenuItem juiceFilter;
    private MenuItem gastroFilter;
    private MenuItem dessertFilter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rblocation_list, container, false);
        setHasOptionsMenu(true);

        mRBLocationRecyclerView = (RecyclerView) view.findViewById(R.id.rblocation_recycler_view);
        mRBLocationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.fragment_list, menu);

        restFilter = menu.findItem(R.id.filterRestaurant);
        barFilter = menu.findItem(R.id.filterBar);
        cafeFilter = menu.findItem(R.id.filterCafe);
        juiceFilter = menu.findItem(R.id.filterJuiceBar);
        gastroFilter = menu.findItem(R.id.filterGastroPub);
        dessertFilter = menu.findItem(R.id.filterDessertBar);

        MenuItem searchItem = menu.findItem(R.id.list_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                updateUI();
                newText = newText.toLowerCase();
                ArrayList<RBLocation> searchQueryList = new ArrayList<>();
                for (RBLocation rbLocation : mAdapter.mLocations) {
                    String locationName = rbLocation.getTitle().toLowerCase();
                    if (locationName.contains(newText)) {
                        searchQueryList.add(rbLocation);
                    }
                }
                mAdapter.setSearchFilter(searchQueryList);
                return true;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                updateUI();
                return false;
            }
        });
    }

    public void hideMenuFilterButtons() {
        restFilter.setEnabled(false);
        barFilter.setEnabled(false);
        cafeFilter.setEnabled(false);
        juiceFilter.setEnabled(false);
        gastroFilter.setEnabled(false);
        dessertFilter.setEnabled(false);
    }

    public void showMenuFilterButtons() {
        restFilter.setEnabled(true);
        barFilter.setEnabled(true);
        cafeFilter.setEnabled(true);
        juiceFilter.setEnabled(true);
        gastroFilter.setEnabled(true);
        dessertFilter.setEnabled(true);
    }

    public void removeFilter() {
        updateUI();
        RBLocationLab resetDefaults = new RBLocationLab();
        resetDefaults.populateList();
        mAdapter.mLocations.clear();
        mAdapter.mLocations.addAll(resetDefaults.getLocations());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.filterRestaurant:
                removeFilter();
                ArrayList<RBLocation> restLocations = new ArrayList<>();
                for(RBLocation location : mAdapter.mLocations) {
                    if (location.isRestaurant()) {
                        restLocations.add(location);
                    }
                }
                mAdapter.applyFilter(restLocations);
                break;

            case R.id.filterBar:
                removeFilter();
                ArrayList<RBLocation> barLocations = new ArrayList<>();
                for (RBLocation location : mAdapter.mLocations) {
                    if (location.isBar()) {
                        barLocations.add(location);
                    }
                }
                mAdapter.applyFilter(barLocations);
                break;

            case R.id.filterCafe:
                removeFilter();
                ArrayList<RBLocation> cafeLocations = new ArrayList<>();
                for (RBLocation location : mAdapter.mLocations) {
                    if (location.isCafe()) {
                        cafeLocations.add(location);
                    }
                }
                mAdapter.applyFilter(cafeLocations);
                break;

            case R.id.filterJuiceBar:
                removeFilter();
                ArrayList<RBLocation> juicebarLocations = new ArrayList<>();
                for(RBLocation location : mAdapter.mLocations) {
                    if (location.isJuiceBar()) {
                        juicebarLocations.add(location);
                    }
                }
                mAdapter.applyFilter(juicebarLocations);
                break;

            case R.id.filterGastroPub:
                removeFilter();
                ArrayList<RBLocation> gastropubLocations = new ArrayList<>();
                for(RBLocation location : mAdapter.mLocations) {
                    if (location.isGastroPub()) {
                        gastropubLocations.add(location);
                    }
                }
                mAdapter.applyFilter(gastropubLocations);
                break;

            case R.id.filterDessertBar:
                removeFilter();
                ArrayList<RBLocation> dessertBar = new ArrayList<>();
                for(RBLocation location : mAdapter.mLocations) {
                    if (location.isDessertBar()) {
                        dessertBar.add(location);
                    }
                }
                mAdapter.applyFilter(dessertBar);
                break;



            case R.id.bsmFilter:
                removeFilter();
                ArrayList<RBLocation> bsm = new ArrayList<>();
                for(RBLocation location : mAdapter.mLocations) {
                    if(location.getArea().equals("Broad Street Mall")) {
                        bsm.add(location);
                    }
                }
                mAdapter.applyFilter(bsm);
                break;

            case R.id.cavFilter:
                removeFilter();
                ArrayList<RBLocation> cav = new ArrayList<>();
                for(RBLocation location : mAdapter.mLocations) {
                    if(location.getArea().equals("Caversham")) {
                        cav.add(location);
                    }
                }
                mAdapter.applyFilter(cav);
                break;

            case R.id.centFilter:
                removeFilter();
                ArrayList<RBLocation> central = new ArrayList<>();
                for(RBLocation location : mAdapter.mLocations) {
                    if(location.getArea().equals("Central Reading")) {
                        central.add(location);
                    }
                }
                mAdapter.applyFilter(central);
                break;

            case R.id.emmerFilter:
                removeFilter();
                ArrayList<RBLocation> emmer = new ArrayList<>();
                for(RBLocation location : mAdapter.mLocations) {
                    if(location.getArea().equals("Emmer Green")) {
                        emmer.add(location);
                    }
                }
                mAdapter.applyFilter(emmer);
                break;

            case R.id.northFilter:
                removeFilter();
                ArrayList<RBLocation> north = new ArrayList<>();
                for(RBLocation location : mAdapter.mLocations) {
                    if(location.getArea().equals("North Reading")) {
                        north.add(location);
                    }
                }
                mAdapter.applyFilter(north);
                break;

            case R.id.oracleFilter:
                removeFilter();
                ArrayList<RBLocation> oracle = new ArrayList<>();
                for(RBLocation location : mAdapter.mLocations) {
                    if(location.getArea().equals("The Oracle")) {
                        oracle.add(location);
                    }
                }
                mAdapter.applyFilter(oracle);
                break;

            case R.id.oracleRivFilter:
                removeFilter();
                ArrayList<RBLocation> oracleRiv = new ArrayList<>();
                for(RBLocation location : mAdapter.mLocations) {
                    if(location.getArea().equals("Oracle Riverside")) {
                        oracleRiv.add(location);
                    }
                }
                mAdapter.applyFilter(oracleRiv);
                break;

            case R.id.southFilter:
                removeFilter();
                ArrayList<RBLocation> south = new ArrayList<>();
                for(RBLocation location : mAdapter.mLocations) {
                    if(location.getArea().equals("South Reading")) {
                        south.add(location);
                    }
                }
                mAdapter.applyFilter(south);
                break;

            case R.id.tileFilter:
                removeFilter();
                ArrayList<RBLocation> tile = new ArrayList<>();
                for(RBLocation location : mAdapter.mLocations) {
                    if(location.getArea().equals("Tilehurst")) {
                        tile.add(location);
                    }
                }
                mAdapter.applyFilter(tile);
                break;

            case R.id.townFilter:
                removeFilter();
                ArrayList<RBLocation> town = new ArrayList<>();
                for(RBLocation location : mAdapter.mLocations) {
                    if(location.getArea().equals("Town Center")) {
                        town.add(location);
                    }
                }
                mAdapter.applyFilter(town);
                break;

            case R.id.westFilter:
                removeFilter();
                ArrayList<RBLocation> west = new ArrayList<>();
                for(RBLocation location : mAdapter.mLocations) {
                    if(location.getArea().equals("West Reading")) {
                        west.add(location);
                    }
                }
                mAdapter.applyFilter(west);
                break;

            case R.id.removeFilterBoth:
                removeFilter();
                break;



            case R.id.list_item_alpha_name:
                Collections.sort(mAdapter.mLocations, new Comparator<RBLocation>() {
                    @Override
                    public int compare(RBLocation o1, RBLocation o2) {
                        return o1.getTitle().compareTo(o2.getTitle());
                    }
                });
                break;

            case R.id.list_item_alpha_area:
                Collections.sort(mAdapter.mLocations, new Comparator<RBLocation>() {
                    @Override
                    public int compare(RBLocation o1, RBLocation o2) {
                        return o1.getArea().compareTo(o2.getArea());
                    }
                });
                break;

            case R.id.list_item_alpha_type:
                Collections.sort(mAdapter.mLocations, new Comparator<RBLocation>() {
                    @Override
                    public int compare(RBLocation o1, RBLocation o2) {
                        return o1.getLocationType().compareTo(o2.getLocationType());
                    }
                });
                break;

            case R.id.list_item_desc_veg:
                Collections.sort(mAdapter.mLocations, new Comparator<RBLocation>() {
                    @Override
                    public int compare(RBLocation o1, RBLocation o2) {
                        return Integer.toString(o1.getVegStatus()).compareTo(Integer.toString(o2.getVegStatus()));
                    }
                });
                break;

            case R.id.list_item_desc_gf:
                Collections.sort(mAdapter.mLocations, new Comparator<RBLocation>() {
                    @Override
                    public int compare(RBLocation o1, RBLocation o2) {
                        return Integer.toString(o1.getGFStatus()).compareTo(Integer.toString(o2.getGFStatus()));
                    }
                });
                break;

            case R.id.list_item_desc_cal:
                Collections.sort(mAdapter.mLocations, new Comparator<RBLocation>() {
                    @Override
                    public int compare(RBLocation o1, RBLocation o2) {
                        return Integer.toString(o1.getCalStatus()).compareTo(Integer.toString(o2.getCalStatus()));
                    }
                });
                break;

            case R.id.list_item_desc_allergen:
                Collections.sort(mAdapter.mLocations, new Comparator<RBLocation>() {
                    @Override
                    public int compare(RBLocation o1, RBLocation o2) {
                        return o1.getAllergenLink().compareTo(o2.getAllergenLink());
                    }
                });
                break;

            case R.id.list_about_contact:
                Intent intent = new Intent(getActivity(),AboutContactActivity.class);
                startActivityForResult(intent, 0);
                break;
        }
        mAdapter.notifyDataSetChanged();
        return true;
    }

    private void updateUI() {
        RBLocationLab locationLab = RBLocationLab.get(getActivity());
        List<RBLocation> locations = locationLab.getLocations();

        mAdapter = new RBLocationAdapter(locations);
        mRBLocationRecyclerView.setAdapter(mAdapter);
    }

    private class RBLocationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mSubtitleTextView;

        private ImageView mIsRestaurantImageView;
        private ImageView mIsDessertBarImageView;
        private ImageView mIsBarImageView;
        private ImageView mIsGastropubImageView;
        private ImageView mIsCafeImageView;
        private ImageView mIsJuiceBarImageView;

        private ImageView mIsAllergenImageView;

        private ImageView mIsGreenVegImageView;
        private ImageView mIsAmberVegImageView;
        private ImageView mIsRedVegImageView;

        private ImageView mIsGreenGFImageView;
        private ImageView mIsAmberGFImageView;
        private ImageView mIsRedGFImageView;

        private ImageView mIsGreenCalImageView;
        private ImageView mIsAmberCalImageView;
        private ImageView mIsRedCalImageView;

        private RBLocation mLocation;

        public RBLocationHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_rblocation, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.rblocation_title);
            mSubtitleTextView = (TextView) itemView.findViewById(R.id.rblocation_subtitle);

            mIsRestaurantImageView = (ImageView) itemView.findViewById(R.id.ic_restaurant);
            mIsDessertBarImageView = (ImageView) itemView.findViewById(R.id.ic_dessert_bar);
            mIsBarImageView = (ImageView) itemView.findViewById(R.id.ic_bar);
            mIsGastropubImageView = (ImageView) itemView.findViewById(R.id.ic_gastropub);
            mIsCafeImageView = (ImageView) itemView.findViewById(R.id.ic_cafe);
            mIsJuiceBarImageView = (ImageView) itemView.findViewById(R.id.ic_juicebar);

            mIsAllergenImageView = (ImageView) itemView.findViewById(R.id.ic_allergen);

            mIsGreenVegImageView = (ImageView) itemView.findViewById(R.id.ic_veg_green);
            mIsAmberVegImageView = (ImageView) itemView.findViewById(R.id.ic_veg_amber);
            mIsRedVegImageView = (ImageView) itemView.findViewById(R.id.ic_veg_red);

            mIsGreenGFImageView = (ImageView) itemView.findViewById(R.id.ic_gf_green);
            mIsAmberGFImageView = (ImageView) itemView.findViewById(R.id.ic_gf_amber);
            mIsRedGFImageView = (ImageView) itemView.findViewById(R.id.ic_gf_red);

            mIsGreenCalImageView = (ImageView) itemView.findViewById(R.id.ic_cal_green);
            mIsAmberCalImageView = (ImageView) itemView.findViewById(R.id.ic_cal_amber);
            mIsRedCalImageView = (ImageView) itemView.findViewById(R.id.ic_cal_red);
        }

        public void bind(RBLocation location) {
            mLocation = location;
            mTitleTextView.setText(mLocation.getTitle());
            mSubtitleTextView.setText(mLocation.getSubtitle());

            mIsRestaurantImageView.setVisibility(location.isRestaurant() ? View.VISIBLE : View.GONE);
            mIsDessertBarImageView.setVisibility(location.isDessertBar() ? View.VISIBLE : View.GONE);
            mIsBarImageView.setVisibility(location.isBar() ? View.VISIBLE : View.GONE);
            mIsGastropubImageView.setVisibility(location.isGastroPub()? View.VISIBLE : View.GONE);
            mIsCafeImageView.setVisibility(location.isCafe() ? View.VISIBLE : View.GONE);
            mIsJuiceBarImageView.setVisibility(location.isJuiceBar() ? View.VISIBLE : View.GONE);

            mIsAllergenImageView.setVisibility(!location.getAllergenLink().equals("null") ? View.VISIBLE : View.GONE);

            mIsGreenVegImageView.setVisibility(location.getVegStatus() == 1 ? View.VISIBLE : View.GONE);
            mIsAmberVegImageView.setVisibility(location.getVegStatus() == 2 ? View.VISIBLE : View.GONE);
            mIsRedVegImageView.setVisibility(location.getVegStatus() == 3 ? View.VISIBLE : View.GONE);

            mIsGreenGFImageView.setVisibility(location.getGFStatus() == 1 ? View.VISIBLE : View.GONE);
            mIsAmberGFImageView.setVisibility(location.getGFStatus() == 2 ? View.VISIBLE : View.GONE);
            mIsRedGFImageView.setVisibility(location.getGFStatus() == 3 ? View.VISIBLE : View.GONE);

            mIsGreenCalImageView.setVisibility(location.getCalStatus() == 1 ? View.VISIBLE : View.GONE);
            mIsAmberCalImageView.setVisibility(location.getCalStatus() == 2 ? View.VISIBLE : View.GONE);
            mIsRedCalImageView.setVisibility(location.getCalStatus() == 3 ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View view) {
            Intent intent = RBLocationActivity.newIntent(getActivity(), mLocation.getId());
            startActivity(intent);
        }

    }

    private class RBLocationAdapter extends RecyclerView.Adapter<RBLocationHolder> {

        private List<RBLocation> mLocations;

        public RBLocationAdapter(List<RBLocation> locations) {
            mLocations = locations;
        }

        public void setSearchFilter(List<RBLocation> searchList) {
            mLocations = new ArrayList<>();
            mLocations.addAll(searchList);
            notifyDataSetChanged();
        }

        public void applyFilter(List<RBLocation> filteredList) {
            mLocations = new ArrayList<>();
            mLocations.addAll(filteredList);
            notifyDataSetChanged();
        }

        @Override
        public RBLocationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new RBLocationHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(RBLocationHolder holder, int position) {
            RBLocation location = mLocations.get(position);
            holder.bind(location);
        }

        @Override
        public int getItemCount() {
            return mLocations.size();
        }

    }

}

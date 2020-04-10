package ws.roberts.android.readibites;

import java.util.UUID;

public class RBLocation {

    private UUID mId;
    private String mTitle;
    private String mFoodType;
    private String mArea;
    private String mSubtitle;

    private String mLocationType;

    private boolean mIsRestaurant;
    private boolean mIsCafe;
    private boolean mIsGastroPub;
    private boolean mIsDessertBar;
    private boolean mIsJuiceBar;
    private boolean mIsBar;

    private int mVegStatus;
    private int mCalStatus;
    private int mGFStatus;

    private String mMapSearch;
    private String mAllergenLink;

    private String mPhoneNum;

    public RBLocation() {
        mId = UUID.randomUUID();
        mAllergenLink = "null";
    }

    public UUID getId() {
        return mId;
    }

    public void generateSubtitle() {
        String ft = this.getFoodType();
        String area = this.getArea();
        mSubtitle = ft + " • " + area;
    }

    public void generateTypeString() {
        if (this.isRestaurant()) {
            this.setLocationType("Restaurant");
        } else if (this.isCafe()) {
            this.setLocationType("Cafe");
        } else if (this.isGastroPub()) {
            this.setLocationType("Gastropub");
        } else if (this.isDessertBar()) {
            this.setLocationType("Dessert Bar");
        } else if (this.isJuiceBar()) {
            this.setLocationType("Juice Bar");
        } else if (this.isBar()) {
            this.setLocationType("Bar");
        }
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getFoodType() {
        return mFoodType;
    }

    public void setFoodType(String foodType) {
        mFoodType = foodType;
    }

    public String getArea() {
        return mArea;
    }

    public void setArea(String area) {
        mArea = area;
    }



    public boolean isRestaurant() {
        return mIsRestaurant;
    }

    public void setIsRestaurant(boolean restaurant) {
        mIsRestaurant = restaurant;
    }

    public boolean isCafe() {
        return mIsCafe;
    }

    public void setIsCafe(boolean cafe) {
        mIsCafe = cafe;
    }

    public boolean isGastroPub() {
        return mIsGastroPub;
    }

    public void setIsGastroPub(boolean gastroPub) {
        mIsGastroPub = gastroPub;
    }

    public boolean isBar() {
        return mIsBar;
    }

    public void setIsBar(boolean bar) {
        mIsBar = bar;
    }

    public boolean isDessertBar() {
        return mIsDessertBar;
    }

    public void setIsDessertBar(boolean dessert) {
        mIsDessertBar = dessert;
    }

    public boolean isJuiceBar() {
        return mIsJuiceBar;
    }

    public void setIsJuiceBar(boolean juice) {
        mIsJuiceBar = juice;
    }



    public int getVegStatus() {
        return mVegStatus;
    }

    public void setVegStatus(int vegStatus) {
        mVegStatus = vegStatus;
    }

    public int getCalStatus() {
        return mCalStatus;
    }

    public void setCalStatus(int calStatus) {
        mCalStatus = calStatus;
    }

    public int getGFStatus() {
        return mGFStatus;
    }

    public void setGFStatus(int gfStatus) {
        mGFStatus = gfStatus;
    }



    public String getMapSearch() {
        return mMapSearch;
    }

    public void setMapSearch(String map) {
        mMapSearch = map;
    }

    public String getAllergenLink() {
        return mAllergenLink;
    }

    public void setAllergenLink(String allergen) {
        mAllergenLink = allergen;
    }


    public String getLocationType() {
        return mLocationType;
    }

    public void setLocationType(String lct) {
        mLocationType = lct;
    }


    public String getPhoneNum() {
        return mPhoneNum;
    }

    public void setPhoneNum(String phone) {
        mPhoneNum = phone;
    }

}

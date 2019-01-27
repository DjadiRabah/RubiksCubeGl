package iutfbleau.rubikscube.models;

public class AchievementItem {

    //fields

    private String name;
    private String desc;
    private String mnemonic;
    private boolean state;

    // constructor

    public AchievementItem(String name, String mnemonic, String desc, boolean state) {

        this.name = name;
        this.desc = desc;
        this.mnemonic = mnemonic;
        this.state = state;

    }

    //methods

    public String getName() {
        return name;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public String getDesc() {
        return desc;
    }

    public boolean getState(){ return state; }

    public void setState(boolean state){ this.state = state; }

}

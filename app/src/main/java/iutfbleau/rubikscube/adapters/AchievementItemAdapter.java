package iutfbleau.rubikscube.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import iutfbleau.rubikscube.R;
import iutfbleau.rubikscube.models.AchievementItem;

public class AchievementItemAdapter extends BaseAdapter {

    // fields

    private Context context;
    private List<AchievementItem> achievementItemList;
    private LayoutInflater inflater;

    //constructor

    public AchievementItemAdapter(Context context, List<AchievementItem> highTechItemList) {

        this.context = context;
        this.achievementItemList = highTechItemList;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {

        return achievementItemList.size();
    }

    @Override
    public Object getItem(int position) {

        return achievementItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) { // if convertView is null
            convertView = inflater.inflate(R.layout.adapter_item, parent, false);

            //get informations about item
            AchievementItem currentItem = (AchievementItem) getItem(position);
            final String itemName = currentItem.getName();
            String mnemonic = currentItem.getMnemonic();
            final String itemDesc = currentItem.getDesc();

            //get image icon view
            ImageView itemIconView = convertView.findViewById(R.id.item_icon);

            //get item desc view
            TextView itemPriceView = convertView.findViewById(R.id.item_desc);
            itemPriceView.setText(itemDesc);

            //get item name view
            TextView itemNameView = convertView.findViewById(R.id.item_name);

            if (currentItem.getState()) {
                String ressourceName = "item_" + mnemonic + "_icon";
                int resId = context.getResources().getIdentifier(ressourceName, "drawable", context.getPackageName());
                itemIconView.setImageResource(resId);
                itemNameView.setText(itemName);

            } else {

                int resId = context.getResources().getIdentifier(String.valueOf(R.drawable.item_lock_icon), "drawable", context.getPackageName());
                itemIconView.setImageResource(resId);
                itemNameView.setText(itemName + " [Locked]");

            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Achievement : " + itemName, Toast.LENGTH_SHORT).show();
                }
            });

        }

        return convertView;
    }
}

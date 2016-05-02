package cafe.adriel.nmsalphabet.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;
import com.readystatesoftware.viewbadger.BadgeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cafe.adriel.nmsalphabet.App;
import cafe.adriel.nmsalphabet.R;
import cafe.adriel.nmsalphabet.model.AlienRace;
import cafe.adriel.nmsalphabet.model.AlienWord;
import cafe.adriel.nmsalphabet.util.ThemeUtil;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private List<AlienWord> wordList;

    public HomeAdapter(Context context, List<AlienWord> wordList) {
        this.context = context;
        this.wordList = wordList;
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        AlienWord word = wordList.get(position);
        AlienRace race = App.getRaceById(word.getRace().getObjectId());
        holder.cardLayout.initialize(1000, context.getResources().getColor(R.color.gray), 2);
        holder.alienRaceTitleView.setBackground(ThemeUtil.getWordRaceTitleDrawable(context));
        holder.alienWordTitleView.setText(word.getWord());
        holder.alienWordView.setText(word.getWord());
        if(race != null) {
            holder.alienRaceTitleView.setText(race.getName());
            holder.alienRaceView.setText(race.getName());
        }

        addBadge(holder.translation1View, 12345);
        addBadge(holder.translation2View, 1234);
        addBadge(holder.translation3View, 123);
        addBadge(holder.translation4View, 12);
        addBadge(holder.translation5View, 1);
    }

    private void addBadge(View view, int count){
        BadgeView badge = new BadgeView(context, view);
        badge.setText(count+"");
        badge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        badge.setBadgeBackgroundColor(ThemeUtil.getAccentColor(context));
        badge.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
        badge.setTextSize(11);
        badge.show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_layout)
        FoldingCell cardLayout;
        @BindView(R.id.alien_word_title)
        TextView alienWordTitleView;
        @BindView(R.id.alien_word)
        TextView alienWordView;
        @BindView(R.id.tag_title)
        TextView alienRaceTitleView;
        @BindView(R.id.tag)
        TextView alienRaceView;
        @BindView(R.id.country_flag)
        ImageView countryFlagView;
        @BindView(R.id.translation_1)
        TextView translation1View;
        @BindView(R.id.translation_2)
        TextView translation2View;
        @BindView(R.id.translation_3)
        TextView translation3View;
        @BindView(R.id.translation_4)
        TextView translation4View;
        @BindView(R.id.translation_5)
        TextView translation5View;
        @BindView(R.id.add_translation)
        TextView addTranslationView;
        @BindView(R.id.report_translation)
        TextView reportTranslationView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
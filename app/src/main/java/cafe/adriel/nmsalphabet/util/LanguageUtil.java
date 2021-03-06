package cafe.adriel.nmsalphabet.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.Locale;

import cafe.adriel.nmsalphabet.Constant;
import cafe.adriel.nmsalphabet.R;

public class LanguageUtil {

    public static final String LANGUAGE_PT = "pt";
    public static final String LANGUAGE_EN = "en";
    public static final String LANGUAGE_DE = "de";

    public static String getCurrentLanguageCode(Context context){
        String languageCode = Util.getSettings(context).getString(Constant.SETTINGS_GENERAL_LANGUAGE, null);
        if(Util.isEmpty(languageCode)) {
            Configuration conf = context.getResources().getConfiguration();
            switch (conf.locale.getLanguage()){
                case LANGUAGE_PT:
                    languageCode = LANGUAGE_PT;
                    break;
                case LANGUAGE_DE:
                    languageCode = LANGUAGE_DE;
                    break;
                default:
                    languageCode = LANGUAGE_EN;
            }
            Util.getSettings(context)
                    .edit()
                    .putString(Constant.SETTINGS_GENERAL_LANGUAGE, languageCode)
                    .apply();
        }
        return languageCode;
    }

    public static String languageCodeToLanguage(Context context, String languageCode){
        switch (languageCode){
            case LANGUAGE_EN:
                return context.getString(R.string.english);
            case LANGUAGE_PT:
                return context.getString(R.string.portuguese);
            case LANGUAGE_DE:
                return context.getString(R.string.german);
            default:
                return null;
        }
    }

    public static void updateLanguage(Context context){
        String language = getCurrentLanguageCode(context);
        Resources res = context.getResources();
        Configuration conf = res.getConfiguration();
        conf.locale = new Locale(language.toLowerCase());
        res.updateConfiguration(conf, res.getDisplayMetrics());
    }

    public static Drawable getLanguageFlagDrawable(Context context, String language){
        switch (language){
            case LANGUAGE_PT:
                return context.getResources().getDrawable(R.drawable.flag_pt_small);
            case LANGUAGE_DE:
                return context.getResources().getDrawable(R.drawable.flag_de_small);
            default:
                return context.getResources().getDrawable(R.drawable.flag_en_small);
        }
    }

}
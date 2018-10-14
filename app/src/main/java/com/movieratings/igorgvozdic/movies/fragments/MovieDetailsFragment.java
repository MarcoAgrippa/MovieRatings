package com.movieratings.igorgvozdic.movies.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;
import com.movieratings.igorgvozdic.movies.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MovieDetailsFragment extends Fragment {

    SmileRating smileRating;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.movie_details_fragment, container, false);

        ImageView posterView = view.findViewById(R.id.imgPoster);
        TextView titleTxt = view.findViewById(R.id.txtTitle);
        TextView voteCountTxt = view.findViewById(R.id.txtVoteCount);
        TextView voteAverageTxt = view.findViewById(R.id.txtVoteAverage);
        TextView languageTxt = view.findViewById(R.id.txtLanguage);
        TextView adultTxt = view.findViewById(R.id.txtAdults);
        TextView releaseDateTxt = view.findViewById(R.id.txtReleaseDate);
        TextView overviewTxt = view.findViewById(R.id.txtOverview);

        smileRating = view.findViewById(R.id.smile_rating);

        Bundle bundle = getArguments();

        if (bundle != null){
            if (bundle.getString("poster_path") != null){
                String imgUrl = bundle.getString("poster_path");
                Picasso.get().load(imgUrl).into(posterView);
            }
            if (bundle.getString("title") != null){
                String title = bundle.getString("title");
                titleTxt.setText(title);
            }

            int voteCount = bundle.getInt("vote_count");
            voteCountTxt.setText(String.valueOf(voteCount));

            double voteAverage = bundle.getDouble("vote_average");
            voteAverageTxt.setText(String.valueOf(voteAverage));
            setSmileyValue(voteAverage);

            String languageShort = bundle.getString("language");
            String language = getFullLanguageName(languageShort);
            languageTxt.setText(language);

            boolean adult = bundle.getBoolean("adult");
            String adultOrNot = adultOrNot(adult);
            adultTxt.setText(adultOrNot);

            String releaseDate = bundle.getString("release_date");
            String reformatedDate = reformatDate(releaseDate);
            releaseDateTxt.setText(reformatedDate);

            final String overview = bundle.getString("overview");
            overviewTxt.setText(overview);
            overviewTxt.setMovementMethod(new ScrollingMovementMethod());

        }

       return view;
    }

    private String adultOrNot(boolean yesOrNo){

        if (yesOrNo){
            return "Yes";
        }else {
            return "No";
        }
    }

    private String getFullLanguageName(String language) {

        HashMap<String, String> languages = new HashMap<>();

        languages.put("en", "English");
        languages.put("fr", "French");
        languages.put("it", "Italian");
        languages.put("es", "Spanish");
        languages.put("pr", "Portugese");
        languages.put("de", "German");
        languages.put("ru", "Russian");
        languages.put("sr", "Serbian");
        languages.put("bg", "Bulgarian");
        languages.put("hu", "Hungarian");
        languages.put("pl", "Polish");
        languages.put("sw", "Swedish");
        languages.put("nr", "Norway");
        languages.put("dn", "Danish");
        languages.put("ro", "Romanian");
        languages.put("uk", "Ukrainian");
        languages.put("jp", "Japanese");
        languages.put("ch", "Chinese-Mandarin");
        languages.put("mo", "Mongolian");
        languages.put("hi", "Hindi");
        languages.put("pk", "Urdu");
        languages.put("ep", "Esperanto");
        languages.put("ar", "Arabic");

        for (Map.Entry<String, String> entry : languages.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (language.equalsIgnoreCase(key)) {
                return value;
            }
        }
        return "Language unknown";
    }

    private String reformatDate(String dateToReformat){
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("dd. MMM yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(dateToReformat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  outputFormat.format(date);
    }

    private void setSmileyValue(double voteAverage){

        if (voteAverage > 8.0){
            smileRating.setSelectedSmile(BaseRating.GREAT);
        }
        if (voteAverage <= 8.0 && voteAverage > 7.0){
            smileRating.setSelectedSmile(BaseRating.GOOD);
        }
        if (voteAverage <= 7.0 && voteAverage > 6.0){
            smileRating.setSelectedSmile(BaseRating.OKAY);
        }
        if (voteAverage <= 6.0 && voteAverage > 5.0){
            smileRating.setSelectedSmile(BaseRating.BAD);
        }
        if (voteAverage <= 5){
            smileRating.setSelectedSmile(BaseRating.TERRIBLE);
        }
    }


}

package com.movieratings.igorgvozdic.movies.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.movieratings.igorgvozdic.movies.R;

public class CreditsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.credits_fragment, container, false);

        ImageView imgGithub = view.findViewById(R.id.img_github_logo);
        ImageView imgLinkedIn = view.findViewById(R.id.img_linkedin_logo);
        ImageView imgGmail = view.findViewById(R.id.img_gmail_logo);

        TextView txtGitHub = view.findViewById(R.id.github_account);
        TextView txtLinkedIn = view.findViewById(R.id.txt_linkedin_profile);
        TextView txtGmail = view.findViewById(R.id.txt_gmail_logo);



        return view;
    }
}

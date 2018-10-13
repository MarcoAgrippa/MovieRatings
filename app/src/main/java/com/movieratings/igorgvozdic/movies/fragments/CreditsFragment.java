package com.movieratings.igorgvozdic.movies.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.movieratings.igorgvozdic.movies.R;

public class CreditsFragment extends Fragment implements View.OnClickListener {

    private ImageView imgProfilePic;
    private ImageView imgGithub;
    private ImageView imgLinkedIn;
    private ImageView imgGmail;
    private TextView txtHi;
    private TextView txtBio;
    private TextView txtWww;
    private TextView txtWebPage;
    private TextView txtGitHub;
    private TextView txtLinkedIn;
    private TextView txtGmail;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.credits_fragment, container, false);

        imgProfilePic = view.findViewById(R.id.profilePic);
        imgGithub = view.findViewById(R.id.img_github_logo);
        imgLinkedIn = view.findViewById(R.id.img_linkedin_logo);
        imgGmail = view.findViewById(R.id.img_gmail_logo);

        txtHi = view.findViewById(R.id.txtHi);
        txtWww = view.findViewById(R.id.txtWww);
        txtWebPage = view.findViewById(R.id.txtWebPage);
        txtBio = view.findViewById(R.id.txtBio);
        txtGitHub = view.findViewById(R.id.github_account);
        txtLinkedIn = view.findViewById(R.id.txt_linkedin_profile);
        txtGmail = view.findViewById(R.id.txt_gmail_logo);


        animateFragment();

        imgGithub.setOnClickListener(this);
        imgLinkedIn.setOnClickListener(this);
        imgGmail.setOnClickListener(this);

        txtGitHub.setOnClickListener(this);
        txtLinkedIn.setOnClickListener(this);
        txtGmail.setOnClickListener(this);
        txtWebPage.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        Intent intent;
        view.playSoundEffect(SoundEffectConstants.CLICK);

        switch (view.getId()){

            case R.id.txtWebPage:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://igorgvozdic.com"));
                startActivity(intent);
                break;
            case R.id.img_github_logo:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/MarcoAgrippa"));
                startActivity(intent);
                break;
            case R.id.github_account:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/MarcoAgrippa"));
                startActivity(intent);
                break;
            case R.id.img_linkedin_logo:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/gvozdic-igor-6032026a/"));
                startActivity(intent);
                break;
            case R.id.txt_linkedin_profile:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/gvozdic-igor-6032026a/"));
                startActivity(intent);
                break;
            case R.id.img_gmail_logo:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "igorgvozdic@gmail.com"));
                startActivity(Intent.createChooser(intent, "Chooser Title"));
                break;
            case R.id.txt_gmail_logo:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "igorgvozdic@gmail.com"));
                startActivity(Intent.createChooser(intent, "Chooser Title"));
                break;
        }
    }

    private void animateFragment(){
        Animation aniFadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        Animation slideInFromLeft = AnimationUtils.loadAnimation(getContext(), R.anim.slide_left_to_right);
        Animation slideInFromRight = AnimationUtils.loadAnimation(getContext(), R.anim.slide_right_to_left);

        imgProfilePic.startAnimation(aniFadeIn);
        txtHi.startAnimation(aniFadeIn);
        txtBio.startAnimation(aniFadeIn);

        txtWww.startAnimation(slideInFromLeft);
        txtWebPage.startAnimation(slideInFromRight);

        imgGithub.startAnimation(slideInFromLeft);
        txtGitHub.startAnimation(slideInFromRight);

        imgLinkedIn.startAnimation(slideInFromLeft);
        txtLinkedIn.startAnimation(slideInFromRight);

        imgGmail.startAnimation(slideInFromLeft);
        txtGmail.startAnimation(slideInFromRight);

    }


}

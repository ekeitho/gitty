package com.ekeitho.github2.model;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

import com.ekeitho.github2.R;

public class GithubRepo {
    public final String name;
    public final String description;

    public GithubRepo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void onRepoClick(final View view) {
        int colorFrom = view.getContext().getResources().getColor(R.color.white);
        int colorTo = view.getContext().getResources().getColor(R.color.diff);
        animateView(view, colorFrom, colorTo, true);
    }

    private void animateView(View view, int colorFrom, int colorTo, boolean animateBack) {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(450);
        colorAnimation.addUpdateListener(animation -> view.setBackgroundColor((int) animation.getAnimatedValue()));
        if (animateBack) {
            colorAnimation.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    animateView(view, colorTo, colorFrom, false);
                }
            });
        }
        colorAnimation.start();
    }
}

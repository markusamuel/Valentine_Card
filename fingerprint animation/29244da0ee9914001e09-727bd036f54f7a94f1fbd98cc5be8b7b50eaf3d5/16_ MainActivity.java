/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.example.fingerprintscan;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView iv;
    private AnimatedVectorDrawable showFingerprint;
    private AnimatedVectorDrawable scanFingerprint;
    private AnimatedVectorDrawable fingerprintToTick;
    private AnimatedVectorDrawable fingerprintToCross;

    private boolean isDone = false;
    private boolean success = true;
    private int backgroundColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.fingerprint);
        backgroundColor = getColor(R.color.circle_default);
        showFingerprint = (AnimatedVectorDrawable) getDrawable(R.drawable.show_fingerprint);
        scanFingerprint = (AnimatedVectorDrawable) getDrawable(R.drawable.scan_fingerprint);
        fingerprintToTick = (AnimatedVectorDrawable) getDrawable(R.drawable.fingerprint_to_tick);
        fingerprintToCross = (AnimatedVectorDrawable) getDrawable(R.drawable.fingerprint_to_cross);

        scanFingerprint.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (success) {
                    iv.setImageDrawable(fingerprintToTick);
                    fingerprintToTick.start();
                    setCircleColor(getColor(R.color.circle_success));
                } else {
                    iv.setImageDrawable(fingerprintToCross);
                    fingerprintToCross.start();
                    setCircleColor(getColor(R.color.circle_error));
                }
                success = !success;
            }
        });

        iv.setImageDrawable(showFingerprint);
        showFingerprint.start();
    }

    public void animate(View view) {
        if (!isDone) {
            iv.setImageDrawable(scanFingerprint);
            scanFingerprint.start();
            isDone = true;
        } else {
            iv.setImageDrawable(showFingerprint);
            showFingerprint.start();
            setCircleColor(getColor(R.color.circle_default));
            isDone = false;
        }
    }

    private void setCircleColor(int to) {
        ObjectAnimator.ofArgb(iv.getBackground(), "color", backgroundColor, to).start();
        backgroundColor = to;
    }

}

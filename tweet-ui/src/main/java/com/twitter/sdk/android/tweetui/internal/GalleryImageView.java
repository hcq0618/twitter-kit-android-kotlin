/*
 * Copyright (C) 2015 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.twitter.sdk.android.tweetui.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

public class GalleryImageView extends FrameLayout {
    final MultiTouchImageView imageView;
    final ProgressBar progressBar;

    public GalleryImageView(Context context) {
        this(context, new MultiTouchImageView(context), new ProgressBar(context));
    }

    GalleryImageView(Context context, MultiTouchImageView imageView, ProgressBar progressBar) {
        super(context);

        this.imageView = imageView;
        this.progressBar = progressBar;

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
                Gravity.CENTER);
        progressBar.setLayoutParams(params);
        addView(progressBar);

        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        imageView.setLayoutParams(params);
        addView(imageView);
    }

    public void setSwipeToDismissCallback(SwipeToDismissTouchListener.Callback callback) {
        final View.OnTouchListener listener =
                SwipeToDismissTouchListener.createFromView(imageView, callback);
        imageView.setOnTouchListener(listener);
    }

    public void onBitmapLoaded(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
        progressBar.setVisibility(View.GONE);
    }

    public void onBitmapFailed(Drawable errorDrawable) {

    }

    public void onPrepareLoad(Drawable placeHolderDrawable) {
        imageView.setImageResource(android.R.color.transparent);
        progressBar.setVisibility(View.VISIBLE);
    }
}

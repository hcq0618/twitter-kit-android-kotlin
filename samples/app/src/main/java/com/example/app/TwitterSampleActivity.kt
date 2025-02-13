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
package com.example.app

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.example.app.tweetcomposer.TweetComposerMainActivity
import com.example.app.tweetui.TweetUiMainActivity
import com.example.app.twittercore.TwitterCoreMainActivity

class TwitterSampleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.twitter_activity)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.twitter_activity_actions, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun onTwitterCore(view: View) {
        startActivity(Intent(this, TwitterCoreMainActivity::class.java))
    }

    fun onTweetComposer(view: View) {
        startActivity(Intent(this, TweetComposerMainActivity::class.java))
    }

    fun onTweetUi(view: View) {
        startActivity(Intent(this, TweetUiMainActivity::class.java))
    }
}
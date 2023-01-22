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
package com.example.app.tweetui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import com.example.app.R
import com.twitter.sdk.android.tweetui.CollectionTimeline
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter

/**
 * CollectionTimelineFragment demonstrates a TimelineListAdapter with a CollectionTimeline.
 */
class CollectionTimelineFragment : ListFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val collectionTimeline: CollectionTimeline = CollectionTimeline.Builder()
            .id(659110687482839040L).build()
        val adapter = TweetTimelineListAdapter(
            activity,
            collectionTimeline
        )
        listAdapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.tweetui_timeline, container, false)
    }

    companion object {

        fun newInstance(): CollectionTimelineFragment {
            return CollectionTimelineFragment()
        }
    }
}
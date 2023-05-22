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
package com.twitter.sdk.android.core.services

import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.core.models.TweetContent
import com.twitter.sdk.android.core.models.TweetResponse
import retrofit2.Call
import retrofit2.http.*

interface StatusesService {

    /**
     * Returns most recent mentions (tweets containing a user's @screen_name) for the
     * authenticating user, by default returns 20 tweets.
     *
     *
     * The timeline returned is the equivalent of the one seen when you view your mentions on
     * twitter.com.
     *
     *
     * The Twitter REST API goes back up to 800 tweets.
     *
     * @param count (optional) Specifies the number of tweets to try and retrieve, up to a maximum
     * of 200. The value of count is best thought of as a limit to the number of tweets
     * to return because suspended or deleted content is removed after the count has
     * been applied. We include retweets in the count, even if include_rts is not
     * supplied. It is recommended you always send include_rts=1 when using this API
     * method.
     * @param sinceId (optional) Returns results with an ID greater than (that is, more recent than)
     * the specified ID. There are limits to the number of tweets which can be
     * accessed through the API. If the limit of tweets has occurred since the
     * since_id, the since_id will be forced to the oldest ID available.
     * @param maxId (optional) Returns results with an ID less than (that is, older than) or equal
     * to the specified ID.
     * @param trimUser (optional) When set to either true, t or 1, each Tweet returned in a timeline
     * will include a user object including only the status authors numerical ID.
     * Omit this parameter to receive the complete user object.
     * @param contributeDetails (optional) This parameter enhances the contributors element of the
     * status response to include the screen_name of the contributor. By
     * default only the user_id of the contributor is included.
     * @param includeEntities (optional) The entities node will be disincluded when set to false.
     */
    @GET(
        "/1.1/statuses/mentions_timeline.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13"
    )
    fun mentionsTimeline(
        @Query("count") count: Int,
        @Query("since_id") sinceId: Long,
        @Query("max_id") maxId: Long,
        @Query("trim_user") trimUser: Boolean,
        @Query("contributor_details") contributeDetails: Boolean,
        @Query("include_entities") includeEntities: Boolean
    ): Call<List<Tweet>>

    /**
     * Returns a collection of the most recent tweets posted by the user indicated by the
     * screen_name or user_id parameters.
     *
     *
     * User timelines belonging to protected users may only be requested when the authenticated user
     * either "owns" the timeline or is an approved follower of the owner.
     *
     *
     * The timeline returned is the equivalent of the one seen when you view a user's profile on
     * twitter.com.
     *
     *
     * The Twitter REST API goes back up to 3,200 of a user's most recent tweets.
     * Native retweets of other statuses by the user is included in this total, regardless of
     * whether include_rts is set to false when requesting this resource.
     *
     *
     * Always specify either an user_id or screen_name when requesting a user timeline.
     *
     * @param userId (optional) The ID of the user for whom to return results for.
     * @param screenName (optional) The screen name of the user for whom to return results for.
     * @param count (optional) Specifies the number of tweets to try and retrieve, up to a maximum
     * of 200. The value of count is best thought of as a limit to the number of tweets
     * to return because suspended or deleted content is removed after the count has
     * been applied. We include retweets in the count, even if include_rts is not
     * supplied. It is recommended you always send include_rts=1 when using this API
     * method.
     * @param sinceId (optional) Returns results with an ID greater than (that is, more recent than)
     * the specified ID. There are limits to the number of tweets which can be
     * accessed through the API. If the limit of tweets has occurred since the
     * since_id, the since_id will be forced to the oldest ID available.
     * @param maxId (optional) Returns results with an ID less than (that is, older than) or equal
     * to the specified ID.
     * @param trimUser (optional) When set to either true, t or 1, each Tweet returned in a timeline
     * will include a user object including only the status authors numerical ID.
     * Omit this parameter to receive the complete user object.
     * @param excludeReplies (optional) This parameter will prevent replies from appearing in the
     * returned timeline. Using exclude_replies with the count parameter will
     * mean you will receive up-to count tweets — this is because the count
     * parameter retrieves that many tweets before filtering out retweets and
     * replies. This parameter is only supported for JSON and XML responses.
     * @param contributeDetails (optional) This parameter enhances the contributors element of the
     * status response to include the screen_name of the contributor. By
     * default only the user_id of the contributor is included.
     * @param includeRetweets (optional) When set to false, the timeline will strip any native
     * retweets (though they will still count toward both the maximal length
     * of the timeline and the slice selected by the count parameter).
     * Note: If you're using the trim_user parameter in conjunction with
     * include_rts, the retweets will still contain a full user object.
     */
    @GET(
        "/1.1/statuses/user_timeline.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13"
    )
    fun userTimeline(
        @Query("user_id") userId: Long,
        @Query("screen_name") screenName: String,
        @Query("count") count: Int,
        @Query("since_id") sinceId: Long?,
        @Query("max_id") maxId: Long?,
        @Query("trim_user") trimUser: Boolean,
        @Query("exclude_replies") excludeReplies: Boolean,
        @Query("contributor_details") contributeDetails: Boolean?,
        @Query("include_rts") includeRetweets: Boolean
    ): Call<List<Tweet>>

    /**
     * Returns a collection of the most recent Tweets and retweets posted by the authenticating user
     * and the users they follow. The home timeline is central to how most users interact with the
     * Twitter service.
     *
     *
     * The Twitter REST API goes back up to 800 tweets on the home timeline.
     * It is more volatile for users that follow many users or follow users who Tweet frequently.
     *
     * @param count (optional) Specifies the number of tweets to try and retrieve, up to a maximum
     * of 200. The value of count is best thought of as a limit to the number of tweets
     * to return because suspended or deleted content is removed after the count has
     * been applied. We include retweets in the count, even if include_rts is not
     * supplied. It is recommended you always send include_rts=1 when using this API
     * method.
     * @param sinceId (optional) Returns results with an ID greater than (that is, more recent than)
     * the specified ID. There are limits to the number of Tweets which can be
     * accessed through the API. If the limit of Tweets has occurred since the
     * since_id, the since_id will be forced to the oldest ID available.
     * @param maxId (optional) Returns results with an ID less than (that is, older than) or equal
     * to the specified ID.
     * @param trimUser (optional) When set to either true, t or 1, each Tweet returned in a timeline
     * will include a user object including only the status authors numerical ID.
     * Omit this parameter to receive the complete user object.
     * @param excludeReplies (optional) This parameter will prevent replies from appearing in the
     * returned timeline. Using exclude_replies with the count parameter will
     * mean you will receive up-to count tweets — this is because the count
     * parameter retrieves that many tweets before filtering out retweets and
     * replies. This parameter is only supported for JSON and XML responses.
     * @param contributeDetails (optional) This parameter enhances the contributors element of the
     * status response to include the screen_name of the contributor. By
     * default only the user_id of the contributor is included.
     * @param includeEntities (optional) The entities node will be disincluded when set to false.
     */
    @GET(
        "/1.1/statuses/home_timeline.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13"
    )
    fun homeTimeline(
        @Query("count") count: Int,
        @Query("since_id") sinceId: Long,
        @Query("max_id") maxId: Long,
        @Query("trim_user") trimUser: Boolean,
        @Query("exclude_replies") excludeReplies: Boolean,
        @Query("contributor_details") contributeDetails: Boolean,
        @Query("include_entities") includeEntities: Boolean
    ): Call<List<Tweet>>

    /**
     * Returns the most recent tweets authored by the authenticating user that have been retweeted
     * by others. This timeline is a subset of the user's GET statuses / user_timeline.
     *
     * @param count (optional) Specifies the number of tweets to try and retrieve, up to a maximum
     * of 200. The value of count is best thought of as a limit to the number of tweets
     * to return because suspended or deleted content is removed after the count has
     * been applied. We include retweets in the count, even if include_rts is not
     * supplied. It is recommended you always send include_rts=1 when using this API
     * method.
     * @param sinceId (optional) Returns results with an ID greater than (that is, more recent than)
     * the specified ID. There are limits to the number of Tweets which can be
     * accessed through the API. If the limit of Tweets has occurred since the
     * since_id, the since_id will be forced to the oldest ID available.
     * @param maxId (optional) Returns results with an ID less than (that is, older than) or equal
     * to the specified ID.
     * @param trimUser (optional) When set to either true, t or 1, each Tweet returned in a timeline
     * will include a user object including only the status authors numerical ID.
     * Omit this parameter to receive the complete user object.
     * @param includeEntities (optional) The entities node will be disincluded when set to false.
     * @param includeUserEntities (optional) The user entities node will not be included when set to
     * false.
     */
    @GET(
        "/1.1/statuses/retweets_of_me.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13"
    )
    fun retweetsOfMe(
        @Query("count") count: Int,
        @Query("since_id") sinceId: Long,
        @Query("max_id") maxId: Long,
        @Query("trim_user") trimUser: Boolean,
        @Query("include_entities") includeEntities: Boolean,
        @Query("include_user_entities") includeUserEntities: Boolean
    ): Call<List<Tweet>>

    /**
     * Returns a single Tweet, specified by the id parameter. The Tweet's author will also be
     * embedded within the Tweet.
     *
     * @param id (required) The numerical ID of the desired Tweet.
     * @param trimUser (optional) When set to either true, t or 1, each Tweet returned in a timeline
     * will include a user object including only the status authors numerical ID.
     * Omit this parameter to receive the complete user object.
     * @param includeMyRetweet (optional) When set to either true, t or 1, any Tweets returned that
     * have been retweeted by the authenticating user will include an
     * additional current_user_retweet node, containing the ID of the source
     * status for the retweet.
     * @param includeEntities (optional) The entities node will be disincluded when set to false.
     */
    @GET(
        "/1.1/statuses/show.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13"
    )
    fun show(
        @Query("id") id: Long,
        @Query("trim_user") trimUser: Boolean?,
        @Query("include_my_retweet") includeMyRetweet: Boolean?,
        @Query("include_entities") includeEntities: Boolean?
    ): Call<Tweet>

    /**
     * Returns fully-hydrated Tweet objects for up to 100 tweets per request, as specified by
     * comma-separated values passed to the id parameter.
     *
     *
     * This method is especially useful to get the details (hydrate) a collection of Tweet IDs.
     *
     *
     * GET statuses / show / :id is used to retrieve a single Tweet object.
     *
     *
     * There are a few things to note when using this method.
     *
     *  * You must be following a protected user to be able to see their most recent tweets. If you
     * don't follow a protected user their status will be removed.
     *  * The order of Tweet IDs may not match the order of tweets in the returned array.
     *  * If a requested Tweet is unknown or deleted, then that Tweet will not be returned in the
     * results list, unless the map parameter is set to true, in which case it will be returned with
     * a value of null.
     *  * If none of your lookup criteria matches valid Tweet IDs an empty array will be returned
     * for map=false.
     *  * You are strongly encouraged to use a POST for larger requests.
     *
     *
     * @param id (required) The comma separated ids of the desired Tweets as a string.
     * @param includeEntities (optional) The entities node will be disincluded when set to false.
     * @param trimUser (optional) When set to either true, t or 1, each Tweet returned in a timeline
     * will include a user object including only the status authors numerical ID.
     * Omit this parameter to receive the complete user object.
     * @param map (optional) When using the map parameter, tweets that do not exist or cannot be
     * viewed by the current user will still have their key represented but with an
     * explicitly null value paired with it
     */
    @GET(
        "/1.1/statuses/lookup.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13"
    )
    fun lookup(
        @Query("id") id: String,
        @Query("include_entities") includeEntities: Boolean?,
        @Query("trim_user") trimUser: Boolean?,
        @Query("map") map: Boolean?
    ): Call<List<Tweet>>

    /**
     * Updates the authenticating user's current status, also known as tweeting.
     *
     *
     * For each update attempt, the update text is compared with the authenticating user's recent
     * tweets. Any attempt that would result in duplication will be blocked, resulting in a 403
     * error. Therefore, a user cannot submit the same status twice in a row.
     *
     *
     * While not rate limited by the API a user is limited in the number of tweets they can create
     * at a time. If the number of updates posted by the user reaches the current allowed limit this
     * method will return an HTTP 403 error.
     *
     * @param status (required) The text of your status update, typically up to 140 characters. URL
     * encode as necessary. [node:840,title="t.co link wrapping"] may effect character
     * counts. There are some special commands in this field to be aware of. For
     * instance, preceding a message with "D " or "M " and following it with a screen
     * name can create a direct message to that user if the relationship allows for
     * it.
     * @param inReplyToStatusId (optional) The ID of an existing status that the update is in reply
     * to. Note:: This parameter will be ignored unless the author of the
     * Tweet this parameter references is mentioned within the status text.
     * Therefore, you must include @username, where username is the author
     * of the referenced Tweet, within the update.
     * @param possiblySensitive (optional) If you upload Tweet media that might be considered
     * sensitive content such as nudity, violence, or medical procedures,
     * you should set this value to true. See Media setting and best
     * practices for more context. Defaults to false.
     * @param latitude (optional) The latitude of the location this Tweet refers to. This parameter
     * will be ignored unless it is inside the range -90.0 to +90.0 (North is
     * positive) inclusive. It will also be ignored if there isn't a corresponding
     * long parameter.
     * @param longitude (optional) The longitude of the location this Tweet refers to. The valid
     * ranges for longitude is -180.0 to +180.0 (East is positive) inclusive. This
     * parameter will be ignored if outside that range, if it is not a number, if
     * geo_enabled is disabled, or if there not a corresponding lat parameter.
     * @param placeId (optional) A place in the world. These IDs can be retrieved from [node:29].
     * @param displayCoordinates (optional) Whether or not to put a pin on the exact coordinates a
     * Tweet has been sent from.
     * @param trimUser (optional) When set to either true, t or 1, each Tweet returned in a timeline
     * will include a user object including only the status authors numerical ID.
     * Omit this parameter to receive the complete user object.
     * @param mediaIds A comma separated media ids as a string for uploaded media to associate
     * with a Tweet. You may include up to 4 photos in a Tweet.
     */
    @FormUrlEncoded
    @POST(
        "/1.1/statuses/update.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13"
    )
    fun update(
        @Field("status") status: String,
        @Field("in_reply_to_status_id") inReplyToStatusId: Long?,
        @Field("possibly_sensitive") possiblySensitive: Boolean?,
        @Field("lat") latitude: Double?,
        @Field("long") longitude: Double?,
        @Field("place_id") placeId: String?,
        @Field("display_coordinates") displayCoordinates: Boolean?,
        @Field("trim_user") trimUser: Boolean,
        @Field("media_ids") mediaIds: String?
    ): Call<Tweet>

    /*
     https://developer.twitter.com/en/docs/twitter-api/migrate/data-formats/standard-v1-1-to-v2
     https://developer.twitter.com/en/docs/twitter-api/tweets/manage-tweets/api-reference/post-tweets
     */
    @POST("/2/tweets")
    fun updateV2(@Body tweet: TweetContent): Call<TweetResponse>

    /**
     * Retweets a Tweet. Returns the original Tweet with retweet details embedded.
     *
     * @param id (required) The numerical ID of the desired Tweet.
     * @param trimUser (optional) When set to either true, t or 1, each Tweet returned in a timeline
     * will include a user object including only the status authors numerical ID.
     * Omit this parameter to receive the complete user object.
     */
    @FormUrlEncoded
    @POST(
        "/1.1/statuses/retweet/{id}.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13"
    )
    fun retweet(
        @Path("id") id: Long,
        @Field("trim_user") trimUser: Boolean
    ): Call<Tweet>

    /**
     * Destroys the status specified by the required ID parameter. The authenticating user must be
     * the author of the specified status. Returns the destroyed status if successful.
     *
     * @param id (required) The numerical ID of the desired Tweet.
     * @param trimUser (optional) When set to either true, t or 1, each Tweet returned in a timeline
     * will include a user object including only the status authors numerical ID.
     * Omit this parameter to receive the complete user object.
     */
    @FormUrlEncoded
    @POST(
        "/1.1/statuses/destroy/{id}.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13"
    )
    fun destroy(
        @Path("id") id: Long,
        @Field("trim_user") trimUser: Boolean
    ): Call<Tweet>

    /**
     * Destroys the retweet specified by the required source Tweet's ID parameter. Returns the
     * source Tweet if successful.
     *
     * @param id (required) The numerical ID of the source Tweet.
     * @param trimUser (optional) When set to either true, t or 1, each Tweet returned in a timeline
     * will include a user object including only the status authors numerical ID.
     * Omit this parameter to receive the complete user object.
     */
    @FormUrlEncoded
    @POST(
        "/1.1/statuses/unretweet/{id}.json?tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13"
    )
    fun unretweet(
        @Path("id") id: Long,
        @Field("trim_user") trimUser: Boolean
    ): Call<Tweet>
}
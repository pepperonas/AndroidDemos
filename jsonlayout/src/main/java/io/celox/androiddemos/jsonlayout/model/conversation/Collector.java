/*
 * Copyright (c) 2017 Martin Pfeffer
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
 */

package io.celox.androiddemos.jsonlayout.model.conversation;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Martin Pfeffer <a href="mailto:martin.pfeffer@kjtech.com">martin.pfeffer@kjtech.com</a>, <a
 *         href="mailto:martin.pfeffer@celox.io">martin.pfeffer@celox.io</a>
 * @see <a href="http://www.kjtech.de">www.kjtech.de</a>
 */

public class Collector {

    private static final String TAG = "CLL";

    private List<Headline> headlines = new ArrayList<>();

    public Collector(String jsonContent) {
        try {

            JSONArray mainObject = new JSONArray(jsonContent);

            JSONObject jsonObject = mainObject.getJSONObject(0);

            JSONArray headlineArray = jsonObject.getJSONArray("headlines");
            Log.i(TAG, "JSONArray headlines: " + headlineArray.length());

            for (int hItr = 0; hItr < headlineArray.length(); hItr++) {
                JSONObject headlineObject = headlineArray.getJSONObject(hItr);
                Log.v(TAG, "\t" + hItr + ". headline has " + headlineObject.length() + " tags.");
                Log.v(TAG, "\t" + " -> " + headlineObject.names());

                JSONArray hTags = headlineObject.names();
                for (int hTagItr = 0; hTagItr < hTags.length() - 1; hTagItr++) {
                    Object object = hTags.get(hTagItr);
                    Log.i(TAG, "\t" + "\t" + object + " => " + headlineObject.get((String) object));
                }

                collectHeadlines(headlineObject);


                JSONArray questionArray = headlineObject.getJSONArray("questions");
                for (int qItr = 0; qItr < questionArray.length(); qItr++) {
                    JSONObject questionObject = questionArray.getJSONObject(qItr);
                    Log.v(TAG, "\t" + "\t" + "\t" + qItr + ". question has " + questionObject.length() + " tags.");
                    Log.v(TAG, "\t" + "\t" + "\t" + " -> " + questionObject.names());

                    JSONArray qTags = questionObject.names();
                    for (int qTagItr = 0; qTagItr < qTags.length(); qTagItr++) {
                        Object object = qTags.get(qTagItr);
                        Log.i(TAG, "\t" + "\t" + "\t" + "\t" + object + " => " + questionObject.get((String) object));
                    }

                    collectQuestions(questionObject);

                } // question loop

            } // headline loop


            Log.i(TAG, "==> " + toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<Headline> getHeadlines() {
        return headlines;
    }

    public List<Headline> getHeadlinesSorted(final boolean ascending) {
        Collections.sort(headlines, new Comparator<Headline>() {
            @Override
            public int compare(Headline lhs, Headline rhs) {
                if (ascending) {
                    return lhs.getPosition() < rhs.getPosition() ? -1 : (lhs.getPosition() > rhs.getPosition()) ? 1 : 0;
                } else {
                    return lhs.getPosition() > rhs.getPosition() ? -1 : (lhs.getPosition() < rhs.getPosition()) ? 1 : 0;
                }
            }
        });
        return headlines;
    }

    private void addHeadline(Headline headline) {
        headlines.add(headline);
    }

    public void addHeadlines(List<Headline> headlines) {
        this.headlines.addAll(headlines);
    }

    public void setHeadlines(List<Headline> headlines) {
        this.headlines = headlines;
    }

    private void collectHeadlines(JSONObject jsonObject) {
        try {
            addHeadline(new Headline(
                    jsonObject.getInt("id"),
                    jsonObject.getInt("sort_headline"),
                    jsonObject.getString("name"),
                    jsonObject.getString("creation_time"),
                    jsonObject.getString("template_id")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void collectQuestions(JSONObject jsonObject) {
        try {
            for (Headline headline : headlines) {

                if (!jsonObject.has("question_type")) continue;

                switch (jsonObject.getString("question_type")) {

                    case "checkbox": {
                        headline.addQuestion(new QuestionCheckbox(
                                jsonObject.getInt("id"),
                                jsonObject.getInt("sort"),
                                jsonObject.getString("question"),
                                jsonObject.getInt("mandatory"),
                                jsonObject.getString("question")));
                        break;
                    }

                    case "dropdown": {
                        headline.addQuestion(new QuestionDropdown(
                                jsonObject.getInt("id"),
                                jsonObject.getInt("sort"),
                                jsonObject.getString("question"),
                                jsonObject.getInt("mandatory"),
                                jsonObject.getString("question")));
                        break;
                    }

                    case "radiobutton": {
                        headline.addQuestion(new QuestionRadio(
                                jsonObject.getInt("id"),
                                jsonObject.getInt("sort"),
                                jsonObject.getString("question"),
                                jsonObject.getInt("mandatory"),
                                jsonObject.getString("keyword")));
                        break;
                    }

                    case "textbox": {
                        headline.addQuestion(new QuestionText(
                                jsonObject.getInt("id"),
                                jsonObject.getInt("sort"),
                                jsonObject.getString("question"),
                                jsonObject.getInt("mandatory"),
                                jsonObject.getString("keyword"),
                                jsonObject.getString("input_type"),
                                jsonObject.getString("min_value"),
                                jsonObject.getString("max_value"),
                                jsonObject.getString("decimals"),
                                jsonObject.getString("date_time_format"),
                                jsonObject.getString("operator"),
                                jsonObject.getString("variable")));
                        break;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int ctr = 1;

        headlines = getHeadlinesSorted(true);

        for (Headline headline : headlines) {
            builder.append("\n").append(ctr++).append(". ").append(headline.toString()).append("\n");

            for (Question question : headline.getQuestionsSorted(true)) {
                builder.append("\t\t").append(question.toString());
            }
        }
        return builder.toString();
    }
}

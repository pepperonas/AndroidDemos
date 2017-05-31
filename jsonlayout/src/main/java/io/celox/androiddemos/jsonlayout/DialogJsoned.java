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

package io.celox.androiddemos.jsonlayout;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.pepperonas.materialdialog.MaterialDialog;

import java.util.List;

import io.celox.androiddemos.jsonlayout.model.conversation.Collector;
import io.celox.androiddemos.jsonlayout.model.conversation.Headline;
import io.celox.androiddemos.jsonlayout.model.conversation.Question;
import io.celox.androiddemos.jsonlayout.model.conversation.QuestionCheckbox;
import io.celox.androiddemos.jsonlayout.model.conversation.QuestionDropdown;
import io.celox.androiddemos.jsonlayout.model.conversation.QuestionRadio;
import io.celox.androiddemos.jsonlayout.model.conversation.QuestionText;

/**
 * @author Martin Pfeffer
 *         <a href="mailto:martin.pfeffer@celox.io">martin.pfeffer@celox.io</a>
 * @see <a href="https://celox.io">https://celox.io</a>
 */
class DialogJsoned {

    public DialogJsoned(final Context context, final Collector collector) {
        new MaterialDialog.Builder(context)
                .title("JSON-Dialog")
                .customView(R.layout.dialog_json)
                .positiveText("OK")
                .showListener(new MaterialDialog.ShowListener() {

                    private LinearLayout layout;

                    @Override
                    public void onShow(AlertDialog dialog) {
                        super.onShow(dialog);

                        layout = (LinearLayout) dialog.findViewById(R.id.ll_container);

                        List<Headline> headlines = collector.getHeadlinesSorted(false);

                        for (Headline headline : headlines) {
                            TextView textView = new TextView(context);
                            textView.setText(headline.getDescription());
                            layout.addView(textView);

                            List<Question> questions = headline.getQuestionsSorted(false);
                            for (Question question : questions) {
                                if (question instanceof QuestionText) {
                                    addEditText(question);
                                } else if (question instanceof QuestionCheckbox) {
                                    addCheckbox(question);
                                } else if (question instanceof QuestionRadio) {
                                    addRadio(question);
                                } else if (question instanceof QuestionDropdown) {
                                    addSpinner(question);
                                }
                            }
                        }

                    }

                    private void addEditText(Question question) {
                        EditText editText = new EditText(context);
                        editText.setHint(question.getDescription());
                        layout.addView(editText);
                    }

                    private void addCheckbox(Question question) {
                        CheckBox checkBox = new CheckBox(context);
                        checkBox.setHint(question.getDescription());
                        layout.addView(checkBox);
                    }

                    private void addRadio(Question question) {
                        RadioButton radioButton = new RadioButton(context);
                        radioButton.setText(question.getDescription());
                        layout.addView(radioButton);
                    }

                    private void addSpinner(Question question) {
                        Spinner spinner = new Spinner(context);
                        spinner.setPrompt(question.getDescription());
                        layout.addView(spinner);
                    }
                })
                .show();
    }

}

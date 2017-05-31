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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Martin Pfeffer <a href="mailto:martin.pfeffer@kjtech.com">martin.pfeffer@kjtech.com</a>, <a
 *         href="mailto:martin.pfeffer@celox.io">martin.pfeffer@celox.io</a>
 * @see <a href="http://www.kjtech.de">www.kjtech.de</a>
 */

public class Headline extends BaseEntity {

    private String created;
    private String templateId;

    private List<Question> questions = new ArrayList<>();

    public Headline(int id, int position, String description, String created, String templateId) {
        super(id, position, description);
        this.created = created;
        this.templateId = templateId;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void addQuestions(List<Question> questions) {
        this.questions.addAll(questions);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<Question> getQuestionsSorted(final boolean ascending) {
        Collections.sort(questions, new Comparator<Question>() {
            @Override
            public int compare(Question lhs, Question rhs) {
                if (ascending) {
                    return lhs.getPosition() < rhs.getPosition() ? -1 : (lhs.getPosition() > rhs.getPosition()) ? 1 : 0;
                } else {
                    return lhs.getPosition() > rhs.getPosition() ? -1 : (lhs.getPosition() < rhs.getPosition()) ? 1 : 0;
                }
            }
        });
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getCreated() {
        return created;
    }

    public String getTemplateId() {
        return templateId;
    }

    @Override
    public String toString() {
        return "[" + super.getPosition() + "] " + super.getDescription();
    }

}

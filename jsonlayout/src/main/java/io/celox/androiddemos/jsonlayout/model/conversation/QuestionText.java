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

/**
 * @author Martin Pfeffer <a href="mailto:martin.pfeffer@kjtech.com">martin.pfeffer@kjtech.com</a>, <a
 *         href="mailto:martin.pfeffer@celox.io">martin.pfeffer@celox.io</a>
 * @see <a href="http://www.kjtech.de">www.kjtech.de</a>
 */

public class QuestionText extends QuestionSelectable {

    private String inputType;

    private String minValue;
    private String maxValue;

    private String precision;

    private String dateFormat;

    private String operator;
    private String variable;

    public QuestionText(int id, int position, String description, int mandatory, String keyword, String inputType,
                        String minValue, String maxValue, String precision, String dateFormat,
                        String operator, String variable) {

        super(id, position, description, mandatory, keyword);

        this.inputType = ""; // TODO: 30.05.2017 implement

        this.minValue = minValue;
        this.maxValue = maxValue;
        this.precision = precision;

        this.dateFormat = "MM:HH"; // TODO: 30.05.2017 implement

        this.operator = operator;
        this.variable = variable;
    }

    public String getInputType() {
        return inputType;
    }

    public String getMinValue() {
        return minValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public String getPrecision() {
        return precision;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public String getOperator() {
        return operator;
    }

    public String getVariable() {
        return variable;
    }

}

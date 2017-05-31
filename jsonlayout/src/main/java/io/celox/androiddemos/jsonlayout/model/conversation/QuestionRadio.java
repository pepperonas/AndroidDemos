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

public class QuestionRadio extends QuestionSelectable {

    public QuestionRadio(int id, int position, String description, int mandatory, String keyword) {
        super(id, position, description, mandatory, keyword);
    }

}

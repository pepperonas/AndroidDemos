package io.celox.androiddemos.jsonlayout.model.conversation;

import java.util.List;

/**
 * @author Martin Pfeffer <a href="mailto:martin.pfeffer@kjtech.com">martin.pfeffer@kjtech.com</a>, <a
 *         href="mailto:martin.pfeffer@celox.io">martin.pfeffer@celox.io</a>
 * @see <a href="http://www.kjtech.de">www.kjtech.de</a>
 */

public class QuestionCheckbox extends Question {

    private List<String> selectedItems;

    public QuestionCheckbox(int id, int position, String description, int mandatory, String keyword) {
        super(id, position, description, mandatory, keyword);
    }

}

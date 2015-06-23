/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.database.fielddecorators;

import hwcore.modules.java.src.library.database.EntityModel;
import hwcore.modules.java.src.library.database.FieldModel;

/**
 *
 * @author giuseppe
 */
public class HiddenField extends FieldModel {

    public HiddenField(String field, EntityModel model) {
        super(field, model);
    }

}

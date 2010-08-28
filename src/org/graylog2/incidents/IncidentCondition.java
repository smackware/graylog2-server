/**
 * Copyright 2010 Lennart Koopmann <lennart@scopeport.org>
 *
 * This file is part of Graylog2.
 *
 * Graylog2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog2.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

/**
 * IncidentCondition.java: Lennart Koopmann <lennart@scopeport.org> | Aug 28, 2010 12:52:33 AM
 */

package org.graylog2.incidents;

public class IncidentCondition {

    public static final int TYPE_AND = 0;
    public static final int TYPE_OR = 1;

    public static final int SUBTYPE_NONE = 0;
    public static final int SUBTYPE_SUBSTRING = 1;
    public static final int SUBTYPE_HOST = 2;
    public static final int SUBTYPE_SEVERITY = 3;
    public static final int SUBTYPE_REGEX = 4;

    private int type;
    private int subtype;
    private Object value;

    public void setType(int type) {
        this.type = type;
    }

    public void setSubtype(int subtype) {
        this.subtype = subtype;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}

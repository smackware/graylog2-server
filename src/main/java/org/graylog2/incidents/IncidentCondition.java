/**
 * Copyright 2010 Lennart Koopmann <lennart@socketfeed.com>
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

package org.graylog2.incidents;

/**
 * IncidentCondition.java: Aug 28, 2010 12:52:33 AM
 *
 * A condition of an incident. Every incident includes this conditions. Chained via AND/OR.
 * 
 * @author Lennart Koopmann <lennart@socketfeed.com>
 */
public class IncidentCondition {

    /**
     * Chain as AND
     */
    public static final int TYPE_AND = 0;

    /**
     * Chain as OR
     */
    public static final int TYPE_OR = 1;

    /**
     * Always true.
     */
    public static final int SUBTYPE_NONE = 0;

    /**
     * Search for a substring in message.
     */
    public static final int SUBTYPE_SUBSTRING = 1;

    /**
     * Match a hostname
     */
    public static final int SUBTYPE_HOST = 2;

    /**
     * Match a severity.
     */
    public static final int SUBTYPE_SEVERITY = 3;

    /**
     * Match a facility.
     */
    public static final int SUBTYPE_FACILITY = 4;

    /**
     * Match REGEX on message.
     */
    public static final int SUBTYPE_REGEX = 5;

    private int type;
    private int subtype;
    private Object value;

    /**
     * The chaining type of this condition. (Constants: IncidentCondition.TYPE_*)
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * The match type of this condition. (Constants: IncidentCondition.SUBTYPE_*)
     * @param subtype
     */
    public void setSubtype(int subtype) {
        this.subtype = subtype;
    }

    /**
     * The value to match for.
     * @param value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Get a better, human readable formatted string of this condition.
     * @return The formatted string
     */
    @Override public String toString() {
        return "Type: " + this.type + ", Subtype: " + this.subtype + ", Value: " + this.value;
    }

}

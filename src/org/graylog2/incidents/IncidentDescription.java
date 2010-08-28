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
 * IncidentDescription.java: Lennart Koopmann <lennart@scopeport.org> | Aug 28, 2010 12:17:06 AM
 */

package org.graylog2.incidents;

import java.util.ArrayList;

public class IncidentDescription {

    private String name;
    private int timerange;
    private ArrayList<IncidentCondition> conditions;

    public void setName(String name) {
        this.name = name;
    }

    public void setTimerange(int minutes) {
        this.timerange = minutes;
    }

    public void setConditions(ArrayList<IncidentCondition> conditions) {
        this.conditions = conditions;
    }

    public String getName() {
        return this.name;
    }

    public int getTimerange() {
        return this.timerange;
    }

    public ArrayList<IncidentCondition> getConditions() {
        return this.conditions;
    }

}

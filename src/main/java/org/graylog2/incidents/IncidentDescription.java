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

import java.util.ArrayList;

/**
 * IncidentDescription.java: Aug 28, 2010 12:17:06 AM
 *
 * Describes an incident and it'S rules/conditions.
 *
 * @author Lennart Koopmann <lennart@socketfeed.com>
 */
public class IncidentDescription {

    private String name;
    private int timerange;
    private ArrayList<IncidentCondition> conditions;

    /**
     * The name of the incident.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The timerange to scan for condition hits in minutes.
     * @param minutes
     */
    public void setTimerange(int minutes) {
        this.timerange = minutes;
    }

    /**
     * The conditions to scan for as ArrayList.
     * @param conditions
     */
    public void setConditions(ArrayList<IncidentCondition> conditions) {
        this.conditions = conditions;
    }

    /**
     * @return The name of the incident
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The timerange to scan for condition hits in minutes.
     */
    public int getTimerange() {
        return this.timerange;
    }

    /**
     * @return All conditions to scan for as ArrayList.
     */
    public ArrayList<IncidentCondition> getConditions() {
        return this.conditions;
    }

}

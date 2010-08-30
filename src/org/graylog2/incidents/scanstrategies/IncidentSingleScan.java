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

package org.graylog2.incidents.scanstrategies;

import com.mongodb.DBCursor;
import org.graylog2.incidents.IncidentDescription;

/**
 * IncidentSingleScan.java: Aug 30, 2010 10:22:14 PM
 *
 * Provides best/optimized way to scan messages collection for a single/non-chained incident condition.
 *
 * @author: Lennart Koopmann <lennart@socketfeed.com>
 */
public class IncidentSingleScan implements IncidentScanStrategyIF {

    private boolean status = false;

    @Override public void scan(DBCursor cursor, IncidentDescription description) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override public boolean getResult() {
        return this.status;
    }

}
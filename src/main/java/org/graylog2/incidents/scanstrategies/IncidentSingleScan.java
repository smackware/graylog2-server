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
import com.mongodb.DBObject;
import java.util.List;
import org.graylog2.Log;
import org.graylog2.incidents.IncidentCondition;
import org.graylog2.incidents.IncidentDescription;

/**
 * IncidentSingleScan.java: Aug 30, 2010 10:22:14 PM
 *
 * Provides best/optimized way to scan messages collection for a single/non-chained incident condition.
 *
 * @author: Lennart Koopmann <lennart@socketfeed.com>
 */
public class IncidentSingleScan implements IncidentScanStrategyIF {

    private IncidentDescription description;
    private DBCursor cursor;

    private boolean status = false;

    /**
     * @param cursor A MongoDB cursor to all messages we want to match on.
     * @param description The description to match for.
     */
    public IncidentSingleScan(DBCursor cursor, IncidentDescription description) {
        this.cursor = cursor;
        this.description = description;
    }

    /**
     * Perform the scan. Get the result via getResult()
     * @throws InvalidStrategyException
     */
    @Override public void scan() throws InvalidStrategyException {
        this.incidentLog("Starting SingleScan. Parsing " + this.cursor.count() + " messages.");
        this.incidentLog("Parsing for following condition: " + this.description.getConditions());

        List<IncidentCondition> conditions = this.description.getConditions();
        //if (conditions.size() != 1) {
        if (true) {
            throw new InvalidStrategyException("Conditions count for IncidentSingleScan must be 1");
        }

        List<DBObject> messages = cursor.toArray();

        int i = 0;
        for(DBObject message : messages) {
            
            i++;
        }

        this.incidentLog("Done.");
    }

    /**
     * Get the result of the scan. Call scan() before.
     * @return
     */
    @Override public boolean getResult() {
        return this.status;
    }

    /**
     * Calls an INFO log with prepended information of current scan.
     * @param message The message to log.
     */
    public void incidentLog(String message) {
        Log.info("Incident: " + this.description.getTitle() + " - SINGLESCAN - " + message);
    }

}
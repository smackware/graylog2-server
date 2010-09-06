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

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import java.util.Date;
import org.graylog2.Log;
import org.graylog2.database.MongoBridge;
import org.graylog2.incidents.scanstrategies.IncidentScanStrategyIF;
import org.graylog2.incidents.scanstrategies.IncidentSingleScan;
import org.graylog2.incidents.scanstrategies.InvalidStrategyException;

class MissingScanStrategyException extends Exception {

    public MissingScanStrategyException() {
    }

}

/**
 * IncidentScan.java: Aug 30, 2010 12:04:59 AM
 *
 * Perform the scan for an IncidentDescription.
 *
 * @author Lennart Koopmann <lennart@socketfeed.com>
 */
public class IncidentScan extends Thread {
    private IncidentDescription description;

    /**
     * Scan for a given IncidentDescription.
     *
     * @param description The IncidentDescription to scan for.
     */
    public IncidentScan(IncidentDescription description) {
        this.description = description;
    }

    /**
     * Start the scan.
     */
    @Override public void run() {
        this.incidentDebugLog("Scanning for incident: " + description.getTitle());

        try {
            // Decide a strategy.
            IncidentScanStrategyIF scan = this.decideScanStragegy();
            scan.scan();
        } catch (MissingScanStrategyException e) {
            Log.crit("Missing scan strategy for IncidentDescription: " + this.description.getTitle());
        } catch (InvalidStrategyException e) {
            Log.crit("Invalid scan strategy for IncidentDescription: " + this.description.getTitle() + " (" + e.toString() + ")");
        }
  
    }

    private DBCursor getMessagesInTimerage() {
        MongoBridge mongo = new MongoBridge();
        DBCollection coll = mongo.getMessagesColl();

        Date since = new Date(System.currentTimeMillis()-(description.getTimerange()*60*1000));
        long sinceStamp = since.getTime()/1000;

        this.incidentDebugLog("Getting all messages since UNIX " + sinceStamp + " (" + since + ")");

        // Build query.
        BasicDBObject query = new BasicDBObject();
        query.put("created_at", new BasicDBObject("$gt", sinceStamp));

        // Build ordering.
        BasicDBObject ordering = new BasicDBObject();
        ordering.put("created_at", -1);

        // Perform query.
        return coll.find(query).sort(ordering);
    }

    private void incidentDebugLog(String message) {
        Log.info("Incident: " + this.description.getTitle() + " - " + message);
    }

    private IncidentScanStrategyIF decideScanStragegy() throws MissingScanStrategyException {
        if (this.description.getConditions().size() == 1) {
            return new IncidentSingleScan(this.getMessagesInTimerage(), this.description);
        }

        throw new MissingScanStrategyException();
    }
}

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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.graylog2.Log;
import org.graylog2.database.MongoBridge;
import org.graylog2.incidents.scanstrategies.IncidentScanStrategyIF;
import org.graylog2.incidents.scanstrategies.IncidentSingleScan;

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
        this.incidentDebugLog("Scanning for incident: " + description.getName());

        try {
            // Decide a strategy.
            IncidentScanStrategyIF scan = this.decideScanStragegy();
            scan.scan(this.getMessagesInTimerage(), this.description);
        } catch (MissingScanStrategyException ex) {
            Log.crit("Missing scan strategy for IncidentDescription: " + this.description.getName());
        }
  
    }

    private DBCursor getMessagesInTimerage() {
        MongoBridge mongo = new MongoBridge();
        DBCollection coll = mongo.getMessagesColl();

        int since = ((int) (System.currentTimeMillis()/1000))-description.getTimerange()*60;

        this.incidentDebugLog("Getting all messages since UNIX " + since);

        BasicDBObject query = new BasicDBObject();
        query.put("created_at", new BasicDBObject("$gt", since));

        return coll.find();
    }

    private void incidentDebugLog(String message) {
        Log.info("Incident:  " + this.description.getName() + " - " + message);
    }

    private IncidentScanStrategyIF decideScanStragegy() throws MissingScanStrategyException {
        if (this.description.getConditions().size() == 1) {
            return new IncidentSingleScan();
        }

        throw new MissingScanStrategyException();
    }
}
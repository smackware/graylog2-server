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
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.graylog2.Log;

/**
 * IncidentScanThread.java: Aug 29, 2010 11:27:50 PM
 * 
 * Fetches all IncidentDescriptions every X (config: incident_check_interval) and runs scans forn them.
 *
 * @author Lennart Koopmann <lennart@socketfeed.com>
 */
public class IncidentScanThread extends Thread {

    private int interval = 0;

    ExecutorService threadPool = Executors.newCachedThreadPool();

    /**
     *
     * @param interval How often ot run the scans in seconds.
     * @throws Exception
     */
    public IncidentScanThread(int interval) throws Exception {
        if (interval <= 0) {
            throw new Exception("Interval for IncidentScanThread must be higher than 0!");
        }

        this.interval = interval;
    }

    /**
     * Start the incident reporting/scanning. Runs forever.
     */
    @Override public void run() {
        // Run forever.
        while (true) {
            try {
                // Fetch all descriptions.
                ArrayList<IncidentDescription> descriptions = IncidentManager.fetchIncidentDescriptions();
                Iterator iter = descriptions.iterator();
                // Go through every description and start the scan in own thread.
                while (iter.hasNext()) {
                    // Start the scan via thread pool.
                    threadPool.execute(new IncidentScan((IncidentDescription) iter.next()));
                }
            } catch (Exception e) {
                Log.warn("Error in IncidentScanThread: " + e.toString());
            }

           // Run as often as defined in config.
           try { Thread.sleep(this.interval*1000); } catch(InterruptedException e) {}
        }
    }

}

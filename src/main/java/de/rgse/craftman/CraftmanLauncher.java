/*
 * Copyright (C) 2017 BWXT109
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package de.rgse.craftman;

import de.rgse.craftman.config.Cli;
import org.apache.commons.cli.CommandLine;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jpa.JPAFraction;

/**
 *
 * @author BWXT109
 */
public class CraftmanLauncher {

    public static void main(String... args) throws Exception {
        CommandLine commandline = Cli.createCommandline(args);

        System.setProperty("swarm.http.port", commandline.getOptionValue("p", "80"));

        Swarm swarm = new Swarm().fraction(new DatasourcesFraction())
                .fraction(new JPAFraction())
                .start();

        try {
            swarm.deploy();

        } catch (Exception exception) {
            swarm.stop();
        }
    }
}

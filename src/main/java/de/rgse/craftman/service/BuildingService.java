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
package de.rgse.craftman.service;

import de.rgse.craftman.model.Building;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author BWXT109
 */
public interface BuildingService {

    List<Building> getBuildingsForUser(Optional<String> user);

    public Optional<Building> getBuilding(String buildingId);

    public Building createBuilding(Building building);
    
}

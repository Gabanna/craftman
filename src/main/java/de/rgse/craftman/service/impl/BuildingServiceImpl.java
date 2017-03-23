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
package de.rgse.craftman.service.impl;

import com.querydsl.jpa.impl.JPAQuery;
import de.rgse.craftman.model.Building;
import de.rgse.craftman.model.QBuilding;
import de.rgse.craftman.service.BuildingService;
import java.util.List;
import java.util.Optional;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author BWXT109
 */
@Named
public class BuildingServiceImpl implements BuildingService {

    private static final QBuilding BUILDING = QBuilding.building;

    @PersistenceContext(unitName = "craftmanPU")
    private EntityManager entityManager;

    @Override
    public List<Building> getBuildingsForUser(Optional<String> user) {
        JPAQuery<Building> query = new JPAQuery<Building>(entityManager).from(BUILDING);

        if (user.isPresent()) {
            query = query.where(BUILDING.user.name.eq(user.get()));
        }

        return query.fetch();
    }

    @Override
    public Optional<Building> getBuilding(String buildingId) {
        return Optional.ofNullable(new JPAQuery<Building>(entityManager).from(BUILDING).where(BUILDING.id.eq(buildingId)).fetchOne());
    }

    @Override
    public Building createBuilding(Building building) {
        entityManager.persist(building);
        return building;
    }

}

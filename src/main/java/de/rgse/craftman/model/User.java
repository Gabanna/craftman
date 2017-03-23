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
package de.rgse.craftman.model;

import com.google.gson.Gson;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;

/**
 *
 * @author BWXT109
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class User {

    private static final String EMAIL_REGEX
            = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]"
            + "+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b"
            + "\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09"
            + "\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*"
            + "[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]"
            + "|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|"
            + "[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c"
            + "\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c"
            + "\\x0e-\\x7f])+)\\])";

    @Id
    @Pattern(regexp = EMAIL_REGEX)
    private String email;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "user")
    private Set<Building> buildings;

    public String toJson() {
        return new Gson().toJson(this);
    }
    
    public static User fromJson(String json) {
        return new Gson().fromJson(json, User.class);
    }
}

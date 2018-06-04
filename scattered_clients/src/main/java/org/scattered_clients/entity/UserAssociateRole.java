package org.scattered_clients.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAssociateRole {
    private int userId;
    private long roleId;

    public UserAssociateRole(){
        super();
    }

    public UserAssociateRole(int userId, long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}

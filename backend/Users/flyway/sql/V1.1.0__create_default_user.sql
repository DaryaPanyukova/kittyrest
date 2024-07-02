INSERT INTO users (id, username, password, owner_id, roles)
VALUES (${admin_id},
        '${admin_username}',
        '${admin_password}',
        ${admin_owner_id},
        '${admin_roles}');
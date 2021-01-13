package io.neoOkpara.ws.hr.entiy.user;

public enum ApplicationUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
//    ADMIN_READ("admin:read"),
//    ADMIN_WRITE("admin:write"),
	HR_READ("hr:read"),
	HR_WRITE("hr:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
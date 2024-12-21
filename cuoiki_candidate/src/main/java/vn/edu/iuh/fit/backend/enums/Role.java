package vn.edu.iuh.fit.backend.enums;

public enum Role {
    ADMININSTRATOR(1), STAFF(2), MANAGER(3), EXECUTIVE(4);

    private int value;

    private Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Role findByValue(int value) {
        for (Role role : Role.values()) {
            if (role.getValue() == value) {
                return role;
            }
        }
        return null;
    }
}

package vn.edu.iuh.fit.enums;

public enum ProductStatus {
    ACTIVE (1),
    IN_ACTIVE (0),
    DELETED (-1);

    private int value;

    private ProductStatus(int value) {
        this.value = value;
    }
}

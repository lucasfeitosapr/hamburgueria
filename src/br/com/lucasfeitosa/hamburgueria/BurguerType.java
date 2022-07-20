package br.com.lucasfeitosa.hamburgueria;

public enum BurguerType {
    X_BURGUER("x-burguer"), X_SALADA("x-salada"), X_BACON("x-bacon"), X_TUDO("x-tudo");

    private final String value;

    BurguerType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static BurguerType findByValue(String value) {
        BurguerType result = null;
        for (BurguerType burguerType : values()) {
            if (burguerType.getValue().equalsIgnoreCase(value)) {
                result = burguerType;
                break;
            }
        }
        return result;
    }
}

package org.example.laba_13.Builder;

public class BuilderFactory {
    private static final String USER = "USERS";
    private static final String UCLASS = "PEOPLE";
    private static final String MESSAGE= "Unknown Builder name!";
    public static Builder create(String builderName) {
        switch (builderName) {
            case USER: {
                return new UserBuilder();
            }
            case UCLASS: {
                return new UnivClassBuilder();
            }
            default:
                throw new IllegalArgumentException(MESSAGE);
        }
    }
}

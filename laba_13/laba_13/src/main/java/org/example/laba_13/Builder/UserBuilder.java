package org.example.laba_13.Builder;

import org.example.laba_13.Exceptions.RepositoryException;
import org.example.laba_13.Models.User;
import org.example.laba_13.Repository.dbconstants.UserTableConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {
    @Override
    public User build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(UserTableConstants.ID.getFieldName());
            String login = resultSet.getString(UserTableConstants.LOGIN.getFieldName());
            byte[] password = resultSet.getBytes(UserTableConstants.PASSWORD.getFieldName());
            return new User(login, password);
        }
        catch (SQLException exception) {
            throw new RepositoryException(exception.getMessage(), exception);
        }
    }
}

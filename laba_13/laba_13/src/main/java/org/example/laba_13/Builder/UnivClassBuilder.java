package org.example.laba_13.Builder;

import org.example.laba_13.Exceptions.RepositoryException;
import org.example.laba_13.Models.PeopleClass;
import org.example.laba_13.Repository.dbconstants.UnivClassTableConstants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UnivClassBuilder  implements Builder <PeopleClass>{
    @Override
    public PeopleClass build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(UnivClassTableConstants.ID.getFieldName());
            String name = resultSet.getString(UnivClassTableConstants.NAME.getFieldName());
            String days = resultSet.getString(UnivClassTableConstants.DAY.getFieldName());
            String hours = resultSet.getString(UnivClassTableConstants.HOURS.getFieldName());
            return new PeopleClass(id, name, days, hours);
        } catch (SQLException exception) {
            throw new RepositoryException(exception.getMessage(), exception);
        }
    }

}

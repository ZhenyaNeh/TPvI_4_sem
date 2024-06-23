package org.example.laba_13.Repository;

import org.example.laba_13.Builder.UnivClassBuilder;
import org.example.laba_13.Exceptions.RepositoryException;
import org.example.laba_13.Models.PeopleClass;
import org.example.laba_13.Repository.dbconstants.UnivClassTableConstants;
import org.example.laba_13.Repository.paramspecification.Parameter;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UnivClassRepository extends AbstractRepository<PeopleClass> {
    public UnivClassRepository(Connection connection){
        super(connection);
    }
    @Override
    protected String getTableName() {
        return SQLHelper.TIMETABLE_TABLE;
    }
    @Override
    public List<PeopleClass> query(String sqlString, Parameter paramater) throws RepositoryException {
        List<PeopleClass> classes = executeQuery(sqlString,new UnivClassBuilder(), paramater.getParameters());
        return classes;
    }
    @Override
    public Optional<PeopleClass> queryForSingleResult(String sqlString, Parameter parameter) throws RepositoryException {
        List<PeopleClass> person = query(sqlString, parameter);
        return person.size() == 1 ?
                Optional.of(person.get(0)) :
                Optional.empty();
    }
    public Map<String, Object> getFields(PeopleClass uClass) {
        Map<String,Object> fields = new HashMap<>();
        fields.put(UnivClassTableConstants.NAME.getFieldName(), uClass.getClassName());
        fields.put(UnivClassTableConstants.DAY.getFieldName(), uClass.getClassDay());
        fields.put(UnivClassTableConstants.HOURS.getFieldName(), uClass.getClassHours());
        return fields;
    }

}
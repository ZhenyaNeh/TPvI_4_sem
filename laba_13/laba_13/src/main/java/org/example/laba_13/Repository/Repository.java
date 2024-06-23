package org.example.laba_13.Repository;

import org.example.laba_13.Exceptions.RepositoryException;
import org.example.laba_13.Repository.paramspecification.Parameter;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    List<T> query(String sqlString, Parameter parameter) throws RepositoryException;
    Optional<T> queryForSingleResult(String sqlString, Parameter parameter) throws RepositoryException;
    List<T> findAll() throws RepositoryException;
    Integer save(T object) throws RepositoryException;
    void remove(Integer object) throws RepositoryException;

}

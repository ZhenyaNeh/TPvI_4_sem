package org.example.laba_13.Builder;

import org.example.laba_13.Exceptions.RepositoryException;

import java.sql.ResultSet;

public interface Builder <T> {
    T build(ResultSet resultSet) throws RepositoryException;

}

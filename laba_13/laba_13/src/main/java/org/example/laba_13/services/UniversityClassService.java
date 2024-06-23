package org.example.laba_13.services;

import org.example.laba_13.Exceptions.RepositoryException;
import org.example.laba_13.Exceptions.ServiceException;
import org.example.laba_13.Models.PeopleClass;
import org.example.laba_13.Repository.RepositoryCreator;
import org.example.laba_13.Repository.UnivClassRepository;

import java.util.List;

public class UniversityClassService {
    public List<PeopleClass> findAll() throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UnivClassRepository uniClassRepository = repositoryCreator.getClassRepository();
            return uniClassRepository.findAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
    public void save(PeopleClass uniClass) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UnivClassRepository uniClassRepository = repositoryCreator.getClassRepository();
            uniClassRepository.save(uniClass);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public void remove(Integer uniClass) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UnivClassRepository uniClassRepository = repositoryCreator.getClassRepository();
            uniClassRepository.remove(uniClass);
        } catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}

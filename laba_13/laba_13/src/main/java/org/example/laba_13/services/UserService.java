package org.example.laba_13.services;

import org.example.laba_13.Exceptions.RepositoryException;
import org.example.laba_13.Exceptions.ServiceException;
import org.example.laba_13.Models.User;
import org.example.laba_13.Repository.RepositoryCreator;
import org.example.laba_13.Repository.SQLHelper;
import org.example.laba_13.Repository.UserRepository;
import org.example.laba_13.Repository.paramspecification.UserByLogin;
import org.example.laba_13.Repository.paramspecification.UserByLoginPassword;

import java.util.Optional;

public class UserService {

    public Optional<User> login(String login, byte[] password) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            UserByLoginPassword params = new UserByLoginPassword(login, password);
            return userRepository.queryForSingleResult(SQLHelper.SQL_GET_USER, params);
        }
        catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Integer save(User user) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            UserByLogin param = new UserByLogin(user.getLogin());
            if (!userRepository.queryForSingleResult(SQLHelper.SQL_CHECK_LOGIN, param).isPresent()) {
                return userRepository.save(user);
            }
            else {
                return 0;
            }
        }
        catch (RepositoryException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}

package org.example.laba_13.Command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.example.laba_13.Exceptions.IncorrectDataException;
import org.example.laba_13.Exceptions.ServiceException;
import org.example.laba_13.Models.User;
import org.example.laba_13.services.UserService;
import org.example.laba_13.util.HashPassword;
import org.example.laba_13.util.Page;

import java.io.IOException;
import java.util.Optional;

import static com.microsoft.sqlserver.jdbc.StringUtils.isEmpty;
import static org.example.laba_13.Command.AuthConstants.*;


public class LoginCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class.getName());

    private void setAttributesToSession(String name, String role, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("NAME", name);
        session.setAttribute("ROLE", role);
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException,
            IncorrectDataException, ServletException, IOException {
        boolean isUserFind = false;

        Optional<String> login = Optional.ofNullable(request.getParameter(LOGIN));
        Optional<String> password = Optional.ofNullable(request.getParameter(PASSWORD));

        if (isEmpty(login.get()) || isEmpty(password.get())) {
            return forwardLoginWithError(request, ERROR_MESSAGE, ERROR_MESSAGE_TEXT);
        }

        byte[] pass = HashPassword.getHash(password.get());

        isUserFind = initializeUserIfExist(login.get(), pass, request);

        if (!isUserFind) {
            LOGGER.info("user with such login and password doesn't exist");
            return forwardLoginWithError(request, ERROR_MESSAGE, AUTHENTICATION_ERROR_TEXT);
        } else {
            LOGGER.info("user has been authorized: login:" + login + " password:" + password);
            return new CommandResult(COMMAND_WELCOME, false);
        }
    }

    public boolean initializeUserIfExist(String login, byte[] password, HttpServletRequest request) throws ServiceException {
        UserService userService = new UserService();
        Optional<User> user = userService.login(login, password);
        boolean userExist = false;
        if (user.isPresent()) {
            setAttributesToSession(user.get().getLogin(), user.get().getRole(), request);
            userExist = true;
        }
        return userExist;
    }

    private CommandResult forwardLoginWithError(HttpServletRequest request, final String ERROR, final String ERROR_MESSAGE) {
        request.setAttribute(ERROR, ERROR_MESSAGE);
        return new CommandResult(Page.LOGIN_PAGE.getPage(), false);
    }
}

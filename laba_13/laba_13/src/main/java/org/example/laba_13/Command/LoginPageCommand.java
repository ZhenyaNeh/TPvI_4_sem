package org.example.laba_13.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.laba_13.Exceptions.IncorrectDataException;
import org.example.laba_13.Exceptions.ServiceException;
import org.example.laba_13.util.Page;

public class LoginPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request,
                                 HttpServletResponse response) throws ServiceException,
            IncorrectDataException {
        System.out.println("LOGIN_PAGE");
        return new CommandResult(Page.LOGIN_PAGE.getPage(), false);

    }
}

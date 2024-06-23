package org.example.laba_13.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.example.laba_13.Exceptions.IncorrectDataException;
import org.example.laba_13.Exceptions.ServiceException;
import org.example.laba_13.util.Page;

public class SingOutCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SingOutCommand.class.getName());
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException,
            IncorrectDataException {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("NAME");
        LOGGER.info("user was above: name:" + username);
        session.removeAttribute("NAME");
        return new CommandResult(Page.LOGIN_PAGE.getPage(), false);
    }

}

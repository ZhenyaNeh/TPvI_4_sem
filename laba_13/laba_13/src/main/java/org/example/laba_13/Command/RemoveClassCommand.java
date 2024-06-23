package org.example.laba_13.Command;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.example.laba_13.Exceptions.IncorrectDataException;
import org.example.laba_13.Exceptions.ServiceException;
import org.example.laba_13.Models.PeopleClass;
import org.example.laba_13.Repository.RepositoryCreator;
import org.example.laba_13.Repository.SQLHelper;
import org.example.laba_13.Repository.UnivClassRepository;
import org.example.laba_13.services.UniversityClassService;
import org.example.laba_13.util.Page;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import static org.example.laba_13.Command.AuthConstants.ERROR_MESSAGE;
import static org.example.laba_13.Command.AuthConstants.ERROR_MESSAGE_TEXT;
import static com.microsoft.sqlserver.jdbc.StringUtils.isEmpty;

public class RemoveClassCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddNewClassCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IncorrectDataException, ServletException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        UniversityClassService classService = new UniversityClassService();
        Optional<String> id = Optional.ofNullable(request.getParameter("id"));

        if (isEmpty(id.get())) {
            LOGGER.info("missing parameter for new person in addition mode");
            request.setAttribute(ERROR_MESSAGE, ERROR_MESSAGE_TEXT);
        } else {
            classService.remove(Integer.parseInt( id.get()));
        }
        List<PeopleClass> clients = classService.findAll();
        if (!clients.isEmpty()) {
            request.setAttribute("classes", clients);
        }


        return new CommandResult(Page.WELCOME_PAGE.getPage(), false);
    }
}


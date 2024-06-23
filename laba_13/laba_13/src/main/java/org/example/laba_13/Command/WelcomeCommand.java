package org.example.laba_13.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.laba_13.Exceptions.IncorrectDataException;
import org.example.laba_13.Exceptions.ServiceException;
import org.example.laba_13.Models.PeopleClass;
import org.example.laba_13.services.UniversityClassService;
import org.example.laba_13.util.Page;

import java.util.List;

public class WelcomeCommand implements Command{
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException, IncorrectDataException {
        UniversityClassService classService = new UniversityClassService();
        List<PeopleClass> classes = classService.findAll();
        if (!classes.isEmpty()) {
            request.setAttribute("classes", classes);
        }
        return new CommandResult(Page.WELCOME_PAGE.getPage(), false);
    }

}

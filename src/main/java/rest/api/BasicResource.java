package rest.api;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rest.api.exceptions.DivisionByZero;
import rest.api.Uris;


@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.BASICS)
public class BasicResource {

    @RequestMapping(value = Uris.STATE, method = RequestMethod.GET)
    public String start() {
        return "{\"response\":\"OKKKKKK " + Uris.VERSION + "\"}";
    }
    
    @RequestMapping(value = Uris.CALC, method = RequestMethod.GET)
    public String division(@RequestParam(defaultValue = "1") Double dividendo, @RequestParam(defaultValue = "1") Double divisor) throws DivisionByZero {
        if (divisor == 0)
            throw new DivisionByZero("divisor debe ser distinto de cero!");
        return "division : " + (dividendo / divisor);
    }


}

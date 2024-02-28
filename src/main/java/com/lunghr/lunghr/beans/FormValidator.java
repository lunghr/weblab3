package com.lunghr.lunghr.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("formValidator")
public class FormValidator implements Validator {

    private void error(String summary) throws ValidatorException {
        FacesMessage message = new FacesMessage(summary);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(message);
    }

    protected void validateX(Double x) throws ValidatorException {
        if (x < -3.d || x > 5.d) {
            error(" X should be in range (-3;5)");
        }
    }

    protected void validateY(Double y) throws ValidatorException {
        if (y < -5.d || y > 5.d) {
            error(" Y should be in range (-5;5)");
        }
    }

    protected void validateR(Double r) throws ValidatorException {
        if (r < 1.d || r > 5.d) {
            error(" R should be in range (1;5)");
        } else {
            System.out.println("error R");
        }
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (o == null) {
            error("Enter value");
        }
        switch(uiComponent.getId()) {
            case "x":
                validateX((Double) o);
                break;
            case "y":
                validateY((Double) o);
                break;
            case "r":
                validateR((Double) o);
        }
    }
}
package com.lunghr.lunghr.beans;

import com.lunghr.lunghr.db.HitResult;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;


@Getter
@Setter
@ToString
@Named("client")
@ApplicationScoped
public class ClientBean implements Serializable {
    private final String sessionId;
    private final LinkedList<HitResult> currentHits;

    @ManagedProperty(value = "#{coordinates}")
    private Coordinates coordinates = new Coordinates();
    @ManagedProperty(value = "#{service}")
    private Service service = new Service();

    public ClientBean() {
        this.sessionId = FacesContext.getCurrentInstance().getExternalContext().getSessionId(true);
        this.currentHits = service.getUserHits(sessionId);
    }

    public void updateXWithValue(Double value) {
        coordinates.setX(value);
    }
    public void updateRWithValue(Double value) {
        coordinates.setR(value);
    }


    public void makeUserRequest() {
        makeRequest(this.coordinates);
    }



    public void makeRemoteRequest() {
        try {
            makeRequest(coordinates);
        } catch (NullPointerException | NumberFormatException exception) {
            System.out.println("error in params");

        }
    }

    public void makeCanvasRequest() {
        final Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            String xValue = params.get("x");
            String yValue = params.get("y");
            String rValue = params.get("r");
            System.out.println(xValue + " " + yValue + " " + rValue);

            if (xValue != null && yValue != null && rValue != null) {
                double x = Double.parseDouble(xValue.trim());
                double y = Double.parseDouble(yValue.trim());
                double r = Double.parseDouble(rValue.trim());
                double currX=coordinates.getX();
                double currY=coordinates.getY();

                coordinates.setX(x);
                coordinates.setY(y);
                coordinates.setR(r);
                makeRequest(coordinates);
                coordinates.setX(currX);
                coordinates.setY(currY);
            } else {
                System.out.println(xValue);
                System.out.println(yValue);
                System.out.println(rValue);
            }
        } catch ( IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


    public void makeRequest(Coordinates coordinates) {
        System.out.println("make rq");
        HitResult res = service.processRequest(this.sessionId, coordinates);
        if (res != null) {
            this.currentHits.addFirst(res);
        }
    }


    public void clearHits() {
        currentHits.clear();
        service.clearUserHits(this.sessionId);
    }
}

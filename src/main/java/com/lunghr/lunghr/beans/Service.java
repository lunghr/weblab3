package com.lunghr.lunghr.beans;

import com.lunghr.lunghr.db.HitResult;
import com.lunghr.lunghr.db.dbController;
import com.lunghr.lunghr.utils.AreaCheck;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
@ToString
@ApplicationScoped
@Named("service")
public class Service implements Serializable {
    private final AreaCheck areaCheck;
    private final com.lunghr.lunghr.db.dbController dbController;

    public Service() {
        areaCheck = new AreaCheck();
        dbController = new dbController();
    }
    public LinkedList<HitResult> getUserHits(String sessionId) {
        if (sessionId == null) return new LinkedList<>();
        List<HitResult> hits = dbController.getUserHits(sessionId);
        System.out.println("userhits" + hits);
        return hits != null ? new LinkedList<>(hits) : new LinkedList<>();
    }
    public HitResult processRequest(String sessionId, Coordinates coordinates) {
        boolean isHit = areaCheck.isHit(coordinates);
        HitResult hitResult = new HitResult(sessionId, coordinates, isHit, getCurrentDate());
        System.out.println(hitResult);
        dbController.addHitResult(hitResult);
        return hitResult;
    }

    public void clearUserHits(String sessionId) {dbController.markUserHitsRemoved(sessionId);}
    public String getCurrentDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withLocale(Locale.UK).withZone(ZoneId.systemDefault()));
    }

}

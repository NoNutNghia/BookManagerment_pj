package service;

import controller.request.dvd.SearchDvdRequest;
import models.Dvd;

import java.util.List;

public interface DvdService {
    List<Dvd> findAll();

    void insertDvd(Dvd dvd);

    void updateDvd(Dvd dvd, int id);

    void deleteDvd(Integer id);

    List<Dvd> searchDvd(SearchDvdRequest searchDvdRequest);
}

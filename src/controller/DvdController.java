

// NGUYEN NGOC NGHIA
package controller;

import controller.request.dvd.SearchDvdRequest;
import models.Dvd;
import service.DvdService;

import java.sql.SQLException;
import java.util.List;

public class DvdController {
    private final DvdService dvdService;

    public DvdController(DvdService dvdService) {
        this.dvdService = dvdService;
    }

    public List<Dvd> findAll() throws SQLException {
        return dvdService.findAll();
    }

    public void insert(Dvd dvd) {
        dvdService.insertDvd(dvd);
    }

    public void update(Dvd dvd, int id) {
        dvdService.updateDvd(dvd, id);
    }

    public void delete(int id) {
        dvdService.deleteDvd(id);
    }

    public List<Dvd> search(SearchDvdRequest searchDvdRequest) {
        return dvdService.searchDvd(searchDvdRequest);
    }
}

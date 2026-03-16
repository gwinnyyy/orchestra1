package com.hps.orchestraspa.service;

import com.hps.orchestraspa.model.Massage;

public interface MassageService {
    Massage[] getAll() throws Exception;
    Massage get(Integer massageId) throws Exception;
    Massage create(Massage massage) throws Exception;
    Massage update(Massage massage) throws Exception;
    void delete(Integer massageId) throws Exception;
}

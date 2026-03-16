package com.hps.orchestraspa.service;

import com.hps.orchestraspa.entities.MassageData;
import com.hps.orchestraspa.model.Massage;
import com.hps.orchestraspa.repository.MassageDataRepository;
import com.hps.orchestraspa.transform.TransformMassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MassageServiceImpl implements MassageService {

    @Autowired
    private MassageDataRepository massageDataRepository;

    @Autowired
    private TransformMassageService transformMassageService;

    @Override
    public Massage[] getAll() throws Exception {
        List<Massage> massageModels = new ArrayList<>();
        massageDataRepository.findAll().forEach(data -> {
            massageModels.add(transformMassageService.transform(data));
        });
        return massageModels.toArray(new Massage[0]);
    }

    @Override
    public Massage get(Integer massageId) throws Exception {
        MassageData data = massageDataRepository.findById(massageId)
                .orElseThrow(() -> new Exception("Massage type not found"));
        return transformMassageService.transform(data);
    }

    @Override
    public Massage create(Massage massage) throws Exception {
        MassageData data = transformMassageService.transform(massage);
        return transformMassageService.transform(massageDataRepository.save(data));
    }

    @Override
    public Massage update(Massage massage) throws Exception {
        MassageData existing = massageDataRepository.findById(massage.getMassageId())
                .orElseThrow(() -> new Exception("Massage not found"));
        
        existing.setMassageName(massage.getMassageName());
        existing.setMassageDesc(massage.getMassageDesc());
        
        return transformMassageService.transform(massageDataRepository.save(existing));
    }

    @Override
    public void delete(Integer massageId) throws Exception {
        massageDataRepository.deleteById(massageId);
    }
}
package com.hps.orchestraspa.transform;
import org.springframework.stereotype.Service;

import com.hps.orchestraspa.entities.*;
import com.hps.orchestraspa.model.*;

@Service
public class TransformMassageServiceImpl implements TransformMassageService {
    @Override
    public MassageData transform(Massage massage){
        MassageData massageData = new MassageData();
        massageData.setMassageId(massage.getMassageId());
        massageData.setMassageDesc(massage.getMassageDesc());
        massageData.setMassageName(massage.getMassageName());

        return massageData;
    }

    @Override
    public Massage transform(MassageData massageData){
        Massage massage = new Massage();
        massage.setMassageId(massageData.getMassageId());
        massage.setMassageDesc(massageData.getMassageDesc());
        massage.setMassageName(massageData.getMassageName());

        return massage;
    }
}

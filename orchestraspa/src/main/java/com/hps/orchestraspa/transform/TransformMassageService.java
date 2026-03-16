package com.hps.orchestraspa.transform;

import com.hps.orchestraspa.entities.*;
import com.hps.orchestraspa.model.*;

public interface TransformMassageService {
    MassageData transform(Massage massage);
    Massage transform(MassageData massageData);
}

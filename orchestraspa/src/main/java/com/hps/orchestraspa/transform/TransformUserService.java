package com.hps.orchestraspa.transform;


import com.hps.orchestraspa.entities.*;
import com.hps.orchestraspa.model.*;

public interface TransformUserService {
    UserData transform(User user);
    User transform(UserData userData);    
}

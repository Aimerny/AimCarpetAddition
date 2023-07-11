package com.github.aimerny.carpet;

import carpet.api.settings.Rule;

public class AcaSetting {

    public static final String ACA = "ACA";
    public static final String EXPERIMENTAL = "experimental";


    @Rule(
            categories = {ACA, EXPERIMENTAL}
    )
    public static boolean trapDoorUpdateRedStoneWireDirection = false;

}

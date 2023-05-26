package com.github.aimerny.carpet;

import carpet.settings.Rule;

public class AcaSetting {

    public static final String ACA = "ACA";
    public static final String EXPERIMENTAL = "experimental";


    @Rule(
            desc = "hopper minecart and chest minecart drop self when be destroyed",
            category = {ACA, EXPERIMENTAL}
    )
    public static boolean minecartsDropSelf = false;

}

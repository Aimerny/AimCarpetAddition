package com.github.aimerny.carpet;


import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.utils.Translations;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.fabricmc.api.ModInitializer;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;


public class AimCarpetAddition implements CarpetExtension, ModInitializer {


    public static final String MOD_ID = "aca";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    static{
        CarpetServer.manageExtension(new AimCarpetAddition());
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(AcaSetting.class);
    }

    @Override
    public Map<String, String> canHasTranslations(String lang) {
        String dataJSON;
        try {
            dataJSON = IOUtils.toString(
                    Objects.requireNonNull(
                            Translations.class
                                    .getClassLoader()
                                    .getResourceAsStream(String.format("assets/aca/lang/%s.json", lang))
                    ),
                    StandardCharsets.UTF_8
            );
        } catch (NullPointerException | IOException e) {
            try {
                dataJSON = IOUtils.toString(
                        Objects.requireNonNull(
                                Translations.class
                                        .getClassLoader()
                                        .getResourceAsStream("assets/aca/lang/en_us.json")
                        ),
                        StandardCharsets.UTF_8
                );
            } catch (NullPointerException | IOException ex) {
                return null;
            }
        }
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.fromJson(dataJSON, new TypeToken<Map<String, String>>() {
        }.getType());
    }

    @Override
    public void onInitialize() {
    }
}

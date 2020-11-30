package dev.conductor.centra.domain.settings.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Settings {

    private String id;
    private String key;
    private String value;

    public Settings(){}

    public Settings(String key, String value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public String toString() {
        return value;
    }

}

package org.greencode.modules.app.entity.wx;

import lombok.Data;

@Data
public class TemplateData {
    private String value;//

    public TemplateData(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

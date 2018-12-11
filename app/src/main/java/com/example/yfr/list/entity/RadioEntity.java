package com.example.yfr.list.entity;

import android.widget.RadioButton;

import com.example.yfr.list.Enum.AdviceEnum;

public class RadioEntity {
    private RadioButton radioButton;
    private AdviceEnum adviceEnum;
    private boolean checked;

    public RadioButton getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(RadioButton radioButton) {
        this.radioButton = radioButton;
    }

    public AdviceEnum getAdviceEnum() {
        return adviceEnum;
    }

    public void setAdviceEnum(AdviceEnum adviceEnum) {
        this.adviceEnum = adviceEnum;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}

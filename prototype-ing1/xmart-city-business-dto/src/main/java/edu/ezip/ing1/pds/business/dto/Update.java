package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "update")
public class Update {
    private String newColumn;
    private String newValue;
    private String conditionColumn;
    private String conditionValue;

    public String getNewColumn() {
        return newColumn;
    }
    public void setNewColumn(String newColumn) {
        this.newColumn = newColumn;
    }
    public String getNewValue() {
        return newValue;
    }
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
    public String getConditionColumn() {
        return conditionColumn;
    }
    public void setConditionColumn(String conditionColumn) {
        this.conditionColumn = conditionColumn;
    }
    public String getConditionValue() {
        return conditionValue;
    }
    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }
    @Override
    public String toString() {
        return "Update{" +
                "newColumn='" + newColumn + '\'' +
                ", newValue='" + newValue + '\'' +
                ", conditionColumn" + conditionColumn + '\'' +
                ", conditionValue" + conditionValue + '\'' + '}'; 
    }
}

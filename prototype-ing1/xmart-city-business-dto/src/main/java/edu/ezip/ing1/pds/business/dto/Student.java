package edu.ezip.ing1.pds.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@JsonRootName(value = "student")
public class Student {
    private  String name;
    private  String firstname;
    private  String group;

    public Student() {
    }
    public final Student build(final ResultSet resultSet)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        setFieldsFromResulset(resultSet, "name", "firstname","group");
        return this;
    }
    public final PreparedStatement build(PreparedStatement preparedStatement)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        return buildPreparedStatement(preparedStatement, name, firstname,group);
    }
    public Student(String name, String firstname, String group) {
        this.name = name;
        this.firstname = firstname;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getGroup() {
        return group;
    }

    @JsonProperty("student_name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("student_1stname")
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @JsonProperty("student_group")
    public void setGroup(String group) {
        this.group = group;
    }

    private void setFieldsFromResulset(final ResultSet resultSet, final String ... fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        for(final String fieldName : fieldNames ) {
            final Field field = this.getClass().getDeclaredField(fieldName);
            field.set(this, resultSet.getObject(fieldName));
        }
    }
    private final PreparedStatement buildPreparedStatement(PreparedStatement preparedStatement, final String ... fieldNames )
            throws NoSuchFieldException, SQLException, IllegalAccessException {
        int ix = 0;
        for(final String fieldName : fieldNames ) {
            preparedStatement.setString(++ix, fieldName);
        }
        return preparedStatement;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}

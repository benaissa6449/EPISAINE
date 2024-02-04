package edu.ezip.ing1.pds.business.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Student;
import edu.ezip.ing1.pds.business.dto.Students;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMartCityService {

    private final static String LoggingLabel = "B u s i n e s s - S e r v e r";
    private final Logger logger = LoggerFactory.getLogger(LoggingLabel);

    private enum Queries {
        SELECT_ALL_STUDENTS("SELECT t.name, t.firstname, t.group FROM \"ezip-ing1\".students t"),
        INSERT_STUDENT("INSERT into \"ezip-ing1\".students (\"name\", \"firstname\", \"group\") values (?, ?, ?)");
        private final String query;

        private Queries(final String query) {
            this.query = query;
        }
    }

    public static XMartCityService inst = null;
    public static final XMartCityService getInstance()  {
        if(inst == null) {
            inst = new XMartCityService();
        }
        return inst;
    }

    private XMartCityService() {

    }

    public final Response dispatch(final Request request, final Connection connection)
            throws InvocationTargetException, IllegalAccessException {
            Response response = null;
        
        PreparedStatement pstmt;
        Statement stmt;
        ResultSet res;
        
        try {
            Class.forName("org.postgresql.Driver");
            switch (request.getRequestOrder()) {
                case "SELECT_ALL_STUDENTS":
                    stmt = connection.createStatement();
                    res = stmt.executeQuery(Queries.SELECT_ALL_STUDENTS.query);
                    response = new Response();
                    response.setRequestId(request.getRequestId());
                    while (res.next()) {
                        response.setResponseBody(response.getResponseBody() + res.getString(1) + "," + res.getString(2) + ";" + res.getString(3) + "\n");
                    }
                    break;
                case "INSERT_STUDENT" :
                    pstmt = connection.prepareStatement(Queries.INSERT_STUDENT.query);
                    Student student = stringToStudent(request.getRequestBody());
                    pstmt.setString(1, student.getName());
                    pstmt.setString(2,student.getFirstname());
                    pstmt.setString(3,student.getGroup());
                    pstmt.executeUpdate();
                    break;
                default:
                    break;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private static Student stringToStudent(String s){
        String[] stringArray = s.split("\n");
        
        String name = stringArray[1].substring(stringArray[1].indexOf(":") + 3,stringArray[1].lastIndexOf("\"") );
        String firstname = stringArray[2].substring(stringArray[2].indexOf(":") + 3,stringArray[2].lastIndexOf("\"") );
        String group = stringArray[3].substring(stringArray[3].indexOf(":") + 3,stringArray[3].lastIndexOf("\"") );

        Student student = new Student(name, firstname, group);
        return student;
    }
}

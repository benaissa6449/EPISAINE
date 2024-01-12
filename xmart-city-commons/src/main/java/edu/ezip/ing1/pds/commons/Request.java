package edu.ezip.ing1.pds.commons;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;

@JsonRootName(value = "request")
public class Request {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String requestId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String requestOrder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String requestBody;

    @JsonProperty("request_order")
    public void setRequestOrder(String requestOrder) {
        this.requestOrder = requestOrder;
    }

    @JsonSetter("request_body")
    public void setRequestBody(JsonNode requestBody) {
        this.requestBody = requestBody.toString();
    }

    public void setRequestContent(final String requestBody) {
        this.requestBody = requestBody;
    }

    @JsonProperty("request_id")
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getRequestOrder() {
        return requestOrder;
    }

    @JsonRawValue
    public String getRequestBody() {
        return requestBody;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestDd=" + requestId +
                ", requestOrder='" + requestOrder + '\'' +
                ", requestBody='" + requestBody + '\'' +
                '}';
    }
}

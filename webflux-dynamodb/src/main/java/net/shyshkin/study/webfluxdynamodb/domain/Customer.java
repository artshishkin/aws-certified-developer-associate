package net.shyshkin.study.webfluxdynamodb.domain;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    private String customerID;
    private String firstName;
    private String lastName;
    private String contactNo;
    private Address address;
    private String createdTimeStamp;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("CustomerID")
    public String getCustomerID() {
        return customerID;
    }

    @DynamoDbAttribute("CustomerFirstName")
    public String getFirstName() {
        return firstName;
    }

    @DynamoDbAttribute("CustomerLastName")
    public String getLastName() {
        return lastName;
    }

    @DynamoDbAttribute("CustomerContactNumber")
    public String getContactNo() {
        return contactNo;
    }

    @DynamoDbAttribute("CustomerAddress")
    public Address getAddress() {
        return address;
    }

    @DynamoDbAttribute("CustomerCreatedTime")
    public String getCreatedTimeStamp() {
        return createdTimeStamp;
    }
}

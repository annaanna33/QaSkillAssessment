package com.omniupdate.addressbook.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactPOJO {

    private String address, city, email, firstName, lastName, state, id, apikey, phone, zipcode;

}

package com.company.retailprocessor.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AddressBuilder
{
    //builder pattern implementation. Instead can use @builder in lombok for this structure
    private String doorNo;
    private String streetName;
    private String landmark;
    private String city;
    private String state;
    private String pincode;

    public AddressBuilder setDoorNo(String doorNo) {
        this.doorNo = doorNo;
        return this;
    }

    public AddressBuilder setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public AddressBuilder setLandmark(String landmark) {
        this.landmark = landmark;
        return this;
    }

    public AddressBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder setState(String state) {
        this.state = state;
        return this;
    }

    public AddressBuilder setPincode(String pincode) {
        this.pincode = pincode;
        return this;
    }

    public Address setAddress()
    {
        return new Address(doorNo, streetName, landmark, city, state, pincode);
    }
}

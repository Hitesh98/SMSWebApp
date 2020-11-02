package com.flipkart.rest;

import com.flipkart.bean.CustomerXML;
import com.flipkart.test.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;

@Path("/customer")
public class CustomerController {

    @GET
    @Path("/listCustomer")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer viewCustomer()
    {
        Customer customer = new Customer();
        customer.setAddress("ADB");
        customer.setName("Sanju");
        return customer;
    }

    @GET
    @Path("/getCustomerXML")
    @Produces(MediaType.APPLICATION_XML)
    public CustomerXML getCustomerXML() {
        CustomerXML customer = new CustomerXML();
        customer.setId(1);
        customer.setName("Shubham");
        customer.setAddress("India");
        return customer;
    }

    @POST
    @Path("/post")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomerInJSON(Customer customer) {
        System.out.println("hit post service");

        System.out.println("Customer name is " +customer.getName());
        System.out.println("Cusotmer address " +customer.getAddress());


        String result = "Customer saved : " + customer;


        return Response.status(201).entity(result).build();

    }


    @DELETE
    @Path("/delete/{customerId}")
    public Response deleteTrackInJSON(@PathParam("customerId") int customerId)
            throws URIReferenceException {
        // when
        // implementation
        return Response.status(200).entity("customer id " +customerId +
                "successfully deleted").build();


    }

    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer updateTrackInJson(Customer customer){

        customer.setName(customer.getName() +"updated");
        customer.setAddress(customer.getAddress() + " updated");
        return customer;

    }

}

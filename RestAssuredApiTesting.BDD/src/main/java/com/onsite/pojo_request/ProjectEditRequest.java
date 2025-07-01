package com.onsite.pojo_request;

import java.util.List;

public class ProjectEditRequest {

    private String id;
    private String name;
    private String bg_image;
    private String contractor;
    private List<String> admins;
    private List<String> supervisors;
    private Long customer_contact;        
    private String customer_name;
    private String customer_company_name;
    private String customer_company_address;
    private String customer_gst;
    private String customer_profile_image;
    private String address;
    private String city;
    private String state;
    private String customer_email;
    private String status;
    private List<String> contact_book;
    private int delete;
    private String company_id;
    private Location location;
    private Object google_address;
    private double estimated_cost;
    private String start_date;
    private String end_date;
    private double attendance_radius;

    public static class Location {
        private String type;
        private List<Double> coordinates;  // Use List<Double>, not List<double>

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;  // use 'this' keyword!
        }

        public List<Double> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBg_image() {
        return bg_image;
    }

    public void setBg_image(String bg_image) {
        this.bg_image = bg_image;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public List<String> getAdmins() {
        return admins;
    }

    public void setAdmins(List<String> admins) {
        this.admins = admins;
    }

    public List<String> getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(List<String> supervisors) {
        this.supervisors = supervisors;
    }

    public Long getCustomer_contact() {
        return customer_contact;
    }

    public void setCustomer_contact(Long customer_contact) {
        this.customer_contact = customer_contact;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_company_name() {
        return customer_company_name;
    }

    public void setCustomer_company_name(String customer_company_name) {
        this.customer_company_name = customer_company_name;
    }

    public String getCustomer_company_address() {
        return customer_company_address;
    }

    public void setCustomer_company_address(String customer_company_address) {
        this.customer_company_address = customer_company_address;
    }

    public String getCustomer_gst() {
        return customer_gst;
    }

    public void setCustomer_gst(String customer_gst) {
        this.customer_gst = customer_gst;
    }

    public String getCustomer_profile_image() {
        return customer_profile_image;
    }

    public void setCustomer_profile_image(String customer_profile_image) {
        this.customer_profile_image = customer_profile_image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getContact_book() {
        return contact_book;
    }

    public void setContact_book(List<String> contact_book) {
        this.contact_book = contact_book;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Object getGoogle_address() {
        return google_address;
    }

    public void setGoogle_address(Object google_address) {
        this.google_address = google_address;
    }

    public double getEstimated_cost() {
        return estimated_cost;
    }

    public void setEstimated_cost(double estimated_cost) {
        this.estimated_cost = estimated_cost;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public double getAttendance_radius() {
        return attendance_radius;
    }

    public void setAttendance_radius(double attendance_radius) {
        this.attendance_radius = attendance_radius;
    }
}

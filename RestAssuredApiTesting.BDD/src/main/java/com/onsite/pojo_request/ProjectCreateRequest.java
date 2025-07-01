package com.onsite.pojo_request;

import java.util.List;

public class ProjectCreateRequest {
	
	private String name;
    private String company_id;
    private String contractor;
    private String start_date;
    private String end_date;
    private String address;
    private String city;
    private String state;
    private String status;
    private int estimated_cost;
    private int attendance_radius;
    private List<String> admins;
    private Location location;

    // Create Location as inner static class
    public static class Location {
        private String type;
        private List<Double> coordinates;

        // getters & setters
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public List<Double> getCoordinates() {
            return coordinates;
        }
        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }
    }

    // getters & setters
    // (use your IDE: right click > generate > getters & setters)
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCompany_id() {
        return company_id;
    }
    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
    public String getContractor() {
        return contractor;
    }
    public void setContractor(String contractor) {
        this.contractor = contractor;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getEstimated_cost() {
        return estimated_cost;
    }
    public void setEstimated_cost(int estimated_cost) {
        this.estimated_cost = estimated_cost;
    }
    public int getAttendance_radius() {
        return attendance_radius;
    }
    public void setAttendance_radius(int attendance_radius) {
        this.attendance_radius = attendance_radius;
    }
    public List<String> getAdmins() {
        return admins;
    }
    public void setAdmins(List<String> admins) {
        this.admins = admins;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
}

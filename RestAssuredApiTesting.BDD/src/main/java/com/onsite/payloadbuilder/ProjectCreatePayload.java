package com.onsite.payloadbuilder;

import java.util.Arrays;

import com.onsite.pojo_request.ProjectCreateRequest;
import com.onsite.pojo_request.ProjectCreateRequest.Location;

public class ProjectCreatePayload {
	
	public static ProjectCreateRequest buildcreateProjectRequest() {
		
		ProjectCreateRequest payload = new ProjectCreateRequest(); 

        payload.setName("API Test Project - t7");
        payload.setCompany_id("75916659-9cbe-4ca7-812e-181a29229772");
        payload.setContractor("c5b0bdca-6941-4411-aa64-369f6bb8cc32");
        payload.setStart_date("2025-06-10T00:00:00Z");
        payload.setEnd_date("2026-05-11T00:00:00Z");
        payload.setAddress("Pawar Mala");
        payload.setCity("Nashik");
        payload.setState("Maharashtra");
        payload.setStatus("Ongoing");
        payload.setEstimated_cost(5000);
        payload.setAttendance_radius(400);
        payload.setAdmins(Arrays.asList("c5b0bdca-6941-4411-aa64-369f6bb8cc32"));

        Location loc = new Location();
        loc.setType("Point");
        loc.setCoordinates(Arrays.asList(1.1, 1.1));
        payload.setLocation(loc);

        return payload;
    }
}

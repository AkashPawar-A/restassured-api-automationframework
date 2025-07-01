package com.onsite.payloadbuilder;

import java.util.Arrays;

import com.onsite.context.ProjectDetail;
import com.onsite.pojo_request.ProjectEditRequest;

public class ProjectEditPayload {
    
    public static ProjectEditRequest editProjectRequest() {
        
        ProjectEditRequest editPayload = new ProjectEditRequest();
        ProjectDetail.editPayload = editPayload;
        
        editPayload.setId(ProjectDetail.projectId);
        editPayload.setName("API Test Project - t9");
        editPayload.setContractor(ProjectDetail.contractorId);
        editPayload.setAdmins(Arrays.asList(ProjectDetail.adminsId));
        editPayload.setSupervisors(Arrays.asList(ProjectDetail.supervisorId));
        editPayload.setCustomer_contact(7499358226L);  // Long literal
        editPayload.setCustomer_name("Akash Pawar");
        editPayload.setCustomer_company_name("AkashOnSite Pvt. Ltd.");
        editPayload.setCustomer_company_address("Captain Vijyant Thapar Marg, Sector 2, Noida");
        editPayload.setCustomer_gst("09AAACH7409R1ZZ");
        editPayload.setCustomer_profile_image("https://onsite.com/images/customer_profile.png");
        editPayload.setAddress("Near Mira Datar Mandir, National Highway-2");
        editPayload.setCity("Nagpur");
        editPayload.setState("Maharashtra");
        editPayload.setCustomer_email("akash.pawar@onsiteteams.com");
        editPayload.setStatus("Ongoing");
        editPayload.setDelete(0);
        editPayload.setCompany_id(ProjectDetail.companyId);
        
        editPayload.setGoogle_address(null);
        editPayload.setEstimated_cost(5500);
        editPayload.setStart_date("2025-06-23T00:00:00Z");
        editPayload.setEnd_date("2026-06-25T00:00:00Z");
        editPayload.setAttendance_radius(500.5);  // ✅ changed to double, not List

        // ✅ Correcting location object
        ProjectEditRequest.Location location = new ProjectEditRequest.Location();
        location.setType("Point");
        location.setCoordinates(Arrays.asList(1.1, 1.2)); // ✅ List<Double>, not List<String>
        editPayload.setLocation(location);
        
        return editPayload;
    }
}

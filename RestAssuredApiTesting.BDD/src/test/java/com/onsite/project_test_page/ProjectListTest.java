package com.onsite.project_test_page;

import com.onsite.pojo_response.ProjectResponseBody;
import com.onsite.utilities_page.PaginationUtils;
import org.testng.annotations.Test;

import java.util.List;

public class ProjectListTest {

    @Test
    public void getAllProjectsUsingPagination() {

        String endpoint = "/list/dashboard/project";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NTQ5Mzk5MTUsImlkIjoiYzViMGJkY2EtNjk0MS00NDExLWFhNjQtMzY5ZjZiYjhjYzMyIiwidXVpZCI6ImU2ZjE3YzliLWYzZWEtNGNjYy05N2E2LTVjY2IyNGZjOGYyNSJ9.ZhvbK8PV3cvM6OSOLNeFrcIW0ABBY6jyi3xbkQVYiik"; // Use a valid token
        String companyId = "75916659-9cbe-4ca7-812e-181a29229772";

        List<ProjectResponseBody> allProjects = PaginationUtils.getAllPaginatedDataAsPojo(
                endpoint, "data.projects", token, companyId, ProjectResponseBody.class
        );

        System.out.println("Total projects fetched: " + allProjects.size());

        for (ProjectResponseBody project : allProjects) {
            System.out.println("ID: " + project.getId() +
                               " | Name: " + project.getName() +
                               " | Status: " + project.getStatus());
        }
    }
}






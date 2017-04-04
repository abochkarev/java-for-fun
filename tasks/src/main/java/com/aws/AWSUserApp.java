package com.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.*;

import java.util.ArrayList;
import java.util.List;

public class AWSUserApp {

    static class AWSUserManager {
        static AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder
                .standard()
                .withRegion(Regions.US_WEST_2)
                .build();

        List<User> getUserList() {
            ListUsersRequest request = new ListUsersRequest();
            ListUsersResult response = iam.listUsers(request);
            List<User> users = new ArrayList<>();
            do {
                users.addAll(response.getUsers());
                request.setMarker(response.getMarker());
            } while (response.isTruncated());
            return users;
        }

        User getUser(String userName) {
            GetUserRequest request = new GetUserRequest().withUserName(userName);
            return iam.getUser(request).getUser();
        }

        User createUser(String userName) {
            CreateUserRequest request = new CreateUserRequest()
                    .withUserName(userName);
            return iam.createUser(request).getUser();
        }

        User updateUser(String userName, String newUserName) {
            UpdateUserRequest request = new UpdateUserRequest()
                    .withUserName(userName)
                    .withNewUserName(newUserName);
            iam.updateUser(request);
            return getUser(newUserName);
        }

        String deleteUser(String userName) {
            DeleteUserRequest request = new DeleteUserRequest()
                    .withUserName(userName);
            iam.deleteUser(request);
            return userName;
        }
    }

    public static void main(String[] args) {
        AWSUserManager awsUserManager = new AWSUserManager();
        try {
            System.out.println("Getting a user list");
            System.out.println("You've got " + awsUserManager.getUserList().size() + " aws users");

            System.out.println("Creating a new user");
            System.out.println("You've created a new user " + awsUserManager.createUser("Bob"));

            System.out.println("Getting a user list");
            System.out.println("You've got " + awsUserManager.getUserList().size() + " aws users");

            System.out.println("Updating a user");
            System.out.println("You've updated a user " + awsUserManager.updateUser("Bob", "John"));

            System.out.println("Getting a user list");
            System.out.println("You've got " + awsUserManager.getUserList().size() + " aws users");
        } catch (AmazonServiceException ase) {
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } finally {
            System.out.println("Deleting a user");
            System.out.println("You've deleted a user " + awsUserManager.deleteUser("John"));
        }
    }
}

package com.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.*;

import java.util.Iterator;
import java.util.Stack;

public class AWSAdminApp {

    public static final String POLICY_DOCUMENT =
            "{" +
                    "  \"Version\": \"2012-10-17\"," +
                    "  \"Statement\": [" +
                    "    {" +
                    "        \"Effect\": \"Allow\"," +
                    "        \"Action\": \"logs:CreateLogGroup\"," +
                    "        \"Resource\": \"*\"" +
                    "    }," +
                    "    {" +
                    "        \"Effect\": \"Allow\"," +
                    "        \"Action\": [" +
                    "            \"dynamodb:DeleteItem\"," +
                    "            \"dynamodb:GetItem\"," +
                    "            \"dynamodb:PutItem\"," +
                    "            \"dynamodb:Scan\"," +
                    "            \"dynamodb:UpdateItem\"" +
                    "       ]," +
                    "       \"Resource\": \"*\"" +
                    "    }" +
                    "   ]" +
                    "}";
    static AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder
            .standard()
            .withRegion(Regions.US_WEST_2)
            .build();

    public static void main(String[] args) throws Exception {
        AWSUserManager awsUserManager = new AWSUserManager();
        AWSGroupManager awsGroupManager = new AWSGroupManager();
        AWSPolicyManager awsPolicyManager = new AWSPolicyManager();
        ResourcesManager resourcesManager = new ResourcesManager();
        String userName = "Bob";
        String groupName = "Group";
        String policyName = "Policy";
        String policyDocument = POLICY_DOCUMENT;

        try {
            resourcesManager.addResource(new Resource() {

                User user;

                @Override
                public void create() {
                    System.out.println("Create user " + userName);
                    user = awsUserManager.createUser(userName);
                }

                @Override
                public void delete() {
                    System.out.println("Delete user " + userName);
                    awsUserManager.deleteUser(user.getUserName());
                }
            });


            resourcesManager.addResource(new Resource() {

                Group group;

                @Override
                public void create() {
                    System.out.println("Create group " + groupName);
                    group = awsGroupManager.createGroup(groupName);
                }

                @Override
                public void delete() {
                    System.out.println("Delete group " + groupName);
                    awsGroupManager.deleteGroup(group.getGroupName());
                }
            });

            resourcesManager.addResource(new Resource() {

                private Policy policy;

                @Override
                public void create() {
                    System.out.println("Create policy " + policyName);
                    policy = awsPolicyManager.createPolicy(policyName, policyDocument);
                }

                @Override
                public void delete() {
                    System.out.println("Delete policy " + policyName);
                    awsPolicyManager.deletePolicy(policy.getArn());
                }
            });

            resourcesManager.addResource(new Resource() {
                @Override
                public void create() {
                    System.out.println("Add user " + userName + " to group " + groupName);
                    awsUserManager.addUserToGroup(userName, groupName);
                }

                @Override
                public void delete() {
                    System.out.println("Remove user " + userName + " from group " + groupName);
                    awsUserManager.removeUserFromGroup(userName, groupName);
                }
            });
        } catch (AmazonServiceException ase) {
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } finally {
            resourcesManager.releaseResources();
        }
    }

    interface Resource {
        void create();

        void delete();
    }

    static class AWSUserManager {

        User getUser(String userName) {
            try {
                GetUserRequest request = new GetUserRequest()
                        .withUserName(userName);
                return iam.getUser(request).getUser();
            } catch (NoSuchEntityException nse) {
                return null;
            }
        }

        User createUser(String userName) {
            User user = getUser(userName);
            if (user != null) {
                return user;
            }

            CreateUserRequest request = new CreateUserRequest()
                    .withUserName(userName);
            return iam.createUser(request).getUser();
        }

        String deleteUser(String userName) {
            User user = getUser(userName);
            if (user != null) {
                DeleteUserRequest request = new DeleteUserRequest()
                        .withUserName(userName);
                iam.deleteUser(request);
            }
            return userName;
        }

        void addUserToGroup(String userName, String groupName) {
            AddUserToGroupRequest request = new AddUserToGroupRequest()
                    .withUserName(userName)
                    .withGroupName(groupName);
            iam.addUserToGroup(request);
        }

        void removeUserFromGroup(String userName, String groupName) {
            RemoveUserFromGroupRequest request = new RemoveUserFromGroupRequest()
                    .withUserName(userName)
                    .withGroupName(groupName);
            iam.removeUserFromGroup(request);
        }
    }

    static class AWSGroupManager {

        Group getGroup(String groupName) {
            try {
                GetGroupRequest request = new GetGroupRequest()
                        .withGroupName(groupName);
                return iam.getGroup(request).getGroup();
            } catch (NoSuchEntityException nse) {
                return null;
            }
        }

        Group createGroup(String groupName) {
            Group group = getGroup(groupName);
            if (group != null) {
                return group;
            }

            CreateGroupRequest request = new CreateGroupRequest()
                    .withGroupName(groupName);
            return iam.createGroup(request).getGroup();
        }

        String deleteGroup(String groupName) {
            Group group = getGroup(groupName);
            if (group != null) {
                DeleteGroupRequest request = new DeleteGroupRequest()
                        .withGroupName(groupName);
                iam.deleteGroup(request);
            }
            return groupName;
        }

    }

    static class AWSPolicyManager {


        Policy createPolicy(String policyName, String policyDocument) {
            CreatePolicyRequest request = new CreatePolicyRequest()
                    .withPolicyName(policyName)
                    .withPolicyDocument(policyDocument);
            return iam.createPolicy(request).getPolicy();
        }

        String deletePolicy(String policyArn) {
            DeletePolicyRequest request = new DeletePolicyRequest()
                    .withPolicyArn(policyArn);
            iam.deletePolicy(request);
            return policyArn;
        }

    }

    static class ResourcesManager {

        Stack<Resource> resources = new Stack<>();

        public void addResource(Resource resource) {
            resource.create();
            resources.push(resource);
        }

        public void releaseResources() {
            Iterator<Resource> iter = resources.iterator();
            while (iter.hasNext()) {
                Resource resource = resources.pop();
                resource.delete();
            }
        }
    }
}

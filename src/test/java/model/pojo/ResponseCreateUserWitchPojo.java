package model.pojo;

public class ResponseCreateUserWitchPojo {

    //" { \"name\": \"morpheus\", \"job\": \"leader\", \"id\": \"552\", \"createdAt\": \"2023-04-21T11:44:02.077Z\" } "

    String name, job, id, createdAt;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

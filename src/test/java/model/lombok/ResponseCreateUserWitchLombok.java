package model.lombok;


import lombok.Data;

@Data
public class ResponseCreateUserWitchLombok {

    //" { \"name\": \"morpheus\", \"job\": \"leader\", \"id\": \"552\", \"createdAt\": \"2023-04-21T11:44:02.077Z\" } "

    String name, job, id, createdAt;

}

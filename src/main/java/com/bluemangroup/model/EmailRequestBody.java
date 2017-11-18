package com.bluemangroup.model;

import lombok.Data;

import java.util.Date;

@Data
public class EmailRequestBody {

    private String userRow;
    private String email;
    private Date startTime;
    private Date endTime;

}

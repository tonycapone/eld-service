package com.bluemangroup.model.dao;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "DRIVERPINFO")
public class VCOMDriver {

    @EmbeddedId
    private Id id;

    @Column(name = "NAME_FRST")
    private String firstName;

    @Column(name = "NAME_LAST")
    private String lastName;

    @Column(name = "EMAIL_ADDR_LINE")
    private String emailAddress;

    @Data
    @Builder
    @ToString
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Id implements Serializable {

        @Column(name = "IDEN_DRVR")
        private String driverIdentity;

        @Column(name = "CODE_SUB_CMPY_DRVR")
        private String driverCompanyCode;

    }

}

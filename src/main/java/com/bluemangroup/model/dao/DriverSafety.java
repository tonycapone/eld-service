package com.bluemangroup.model.dao;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "DRIVERSFTY")
public class DriverSafety {

    @EmbeddedId
    private Id id;

    @Column(name = "DRVR_RATG")
    private String driverRating;

    @Column(name = "DRVR_RATG_PREV")
    private String prevDriverRating;

    @Column(name = "SP_DRVR_RATG")
    private String spDriverRating;

    @Column(name = "SP_DRVR_RATG_PREV")
    private String prevSpDriverRating;

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

package com.bluemangroup.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @JsonProperty("user_row")
    private String userRow;

    @JsonProperty("name_first")
    private String firstName;

    @JsonProperty("name_last")
    private String lastName;

    @JsonProperty("roles")
    private String roles;

    @JsonProperty("email_address")
    private String email;

    @JsonProperty("violations")
    private int violations;

    @JsonProperty("cur_ids")
    private String curIds;

    @JsonProperty("vcomDriverId")
    private String vcomDriverId;

//    "user_row":"5976",
//            "name_first":"Mark",
//            "name_last":"Oates",
//            "email_address":"mark_oates@blueinktech.com",
//            "timezone_id":"2",
//            "timezone":"US/Eastern",
//            "language_id":"1",
//            "language":"English",
//            "admin_can_edit":"f",
//            "cur_ids":"8872",
//            "role_ids":"2",
//            "roles":"Driver",
//            "personal_use_ok":"t",
//            "yard_moves_ok":"t",
//            "eld_exempt":"f",
//            "driver_row":"3179",
//            "license_number":"0987654321",
//            "cycle_rule_id":"1",
//            "cycle_rule":"USA 70 hour / 8 day                               ",
//            "cycle_rule_hours":"70",
//            "cycle_rule_days":"8",
//            "drive_hrs":"11",
//            "odnd_hrs":"14",
//            "break_after_odnd_hrs":"8",
//            "sleeper_for_split_hrs":"8",
//            "shift_reset_hrs":"10",
//            "cycle_reset_hrs":"34",
//            "sleeper_odnd_ext_hrs":"8",
//            "last_rods_event":"Off Duty",
//            "last_rods_event_id":"1",
//            "last_rods_time":"2017-11-17 12:22:04",
//            "uncert":"0",
//            "drive_time":"5.96",
//            "shift_time":"7.77",
//            "cycle_time":"64.33",
//            "cycle_violations":"0",
//            "max_drive_violations":"0",
//            "max_duty_violations":"0",
//            "break_violations":"0",
//            "violations":"0"
}

package com.example.springopenapirest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class Device {
    @Id
    @Column(name = "deviceId", nullable = false)
    private String deviceId;

    @NotBlank
    @Size(min = 0, max = 20)
    private String name;

    @NotBlank
    @Size(min = 0, max = 30)
    private String deviceType;

    public Device(String deviceId) {
        this.deviceId = deviceId;
    }

    public Device() {
    }
}

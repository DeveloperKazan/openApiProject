package com.example.springopenapirest.controllers;

import com.example.springopenapirest.entity.Device;
import com.example.springopenapirest.exceptions.DeviceNotFoundException;
import com.example.springopenapirest.repository.DeviceRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

@RestController
@RequestMapping("/api/device")
public class DeviceResources {

    @Autowired
    private DeviceRepository repository;

    @Operation(summary = "Get device by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the device",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Device.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Device not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public Device findById(@Parameter(description = "device id to be searched") @PathVariable long id) {
        return repository.findById(id)
                .orElseThrow(DeviceNotFoundException::new);
    }

    @Operation(summary = "Get list of devices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of devices",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Device.class)))})
    })

    @GetMapping("/")
    public Collection<Device> findDevices() {
        return repository.findAll();
    }

    @GetMapping("/filter")
    public Page<Device> filterDevices(@ParameterObject Pageable pageable) {
        return repository.findAll(pageable);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Device updateDevice(
            @PathVariable("id") final String id, @RequestBody final Device book) {
        return book;
    }

    @PostMapping("/save")
    public ResponseEntity<URI> createDevice(@RequestBody @NotNull @Valid Device book) throws URISyntaxException {
        repository.save(book);
        return ResponseEntity.created(new URI("")).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDevice(@NotNull @PathVariable("id") String deviceId){
        Device device = new Device(deviceId);
        repository.delete(device);
        return ResponseEntity.noContent().build();
    }
}

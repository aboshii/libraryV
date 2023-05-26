package com.library.springlibrary.controller.Endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.library.springlibrary.exceptions.UserNotFoundException;
import com.library.springlibrary.model.dto.UserDto;
import com.library.springlibrary.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/users", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
class UserEndpoint {
    private UserService userService;
    private ObjectMapper objectMapper;

    @GetMapping()
    List<UserDto> getBookList() {
        return userService.getUsers();
    }

    @RequestMapping("/{id}")
    ResponseEntity<UserDto> getUser(@PathVariable(required = false, name = "id") Long id) {
        UserDto userById;
        try {
            userById = userService.getUserDtoById(id);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userById);
    }

    @PostMapping()
    ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto) {
        UserDto savedUser = userService.addUser(userDto);
        URI savedUserUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(savedUserUri).body(savedUser);
    }

    @PatchMapping("/{id}")
    ResponseEntity<?> updateUser(@PathVariable(required = true, name = "id") Long id,
                                 @RequestBody JsonMergePatch patch) {
        try {
            UserDto userDtoById = userService.getUserDtoById(id);
            UserDto userDto = applyPatch(userDtoById, patch);
            userService.updateUser(userDto);
        } catch (JsonPatchException | JsonProcessingException E) {
            return ResponseEntity.internalServerError().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUsers(@PathVariable(required = true) Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    private UserDto applyPatch(UserDto userDtoById, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode jsonNode = objectMapper.valueToTree(userDtoById);
        JsonNode jsonNodePatched = patch.apply(jsonNode);
        return objectMapper.treeToValue(jsonNodePatched, UserDto.class);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }
}


package com.example.web.app.controllers;

import com.example.web.app.api.request.UserByIdRequest;
import com.example.web.app.dao.DbSqlite;
import com.example.web.app.dao.model.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/select/user")
public class SelectUserController {
    private final DbSqlite dbSqlite;

    public SelectUserController(DbSqlite dbSqlite) {
        this.dbSqlite = dbSqlite;
    }

    @ApiOperation(value = "Выборка User по id")
    @RequestMapping(value = "by/id", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> selectUserById(@RequestBody UserByIdRequest id) {
        User user = dbSqlite.selectUserById(id.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }
}

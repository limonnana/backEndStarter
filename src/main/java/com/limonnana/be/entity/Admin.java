package com.limonnana.be.entity;

import com.limonnana.be.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    public Admin(UserDto userDto){
        if (userDto.getUsername() != null){
            this.username = userDto.getUsername();
        }
        if (userDto.getPassword() != null){
            this.password = userDto.getPassword();
        }
    }

}

package com.backend.airline_tickets_agency_management.model.dto.password;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordDto {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public PasswordDto() {
    }

    public PasswordDto(String oldPassword, String newPassword, String confirmPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}


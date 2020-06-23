package com.penchev.perfume.models.binding;

import com.penchev.perfume.validate.ContainSpaceBetweenFirstAndLastNames;
import com.penchev.perfume.validate.ExistEmail;
import com.penchev.perfume.validate.NotSamePassword;
import com.penchev.perfume.validator.ValidationGroups.ExistGroup;
import com.penchev.perfume.validator.ValidationGroups.LengthGroup;
import com.penchev.perfume.validator.ValidationGroups.ValidGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
@NotSamePassword.List({
        @NotSamePassword(
                password = "password",
                confirmPassword = "confirmPassword",
                message = "{password.match.confirmPassword}"
        )
})
public class RegisterBindingModel {

    @NotBlank(message = "{firstAndLastNames.empty}")
    @ContainSpaceBetweenFirstAndLastNames(
            message = "{firstAndLastNames.match.pattern}",
            groups = ValidGroup.class)
    private String firstAndLastNames;

    @NotBlank(message = "{email.empty}")
    @Email(message = "{email.valid}", groups = ValidGroup.class)
    @ExistEmail(message = "{email.already.exists}", groups = ExistGroup.class)
    @Size.List({
            @Size(min = 6, message = "{email.size.min}", groups = LengthGroup.class),
            @Size(max = 60, message = "{email.size.max}", groups = LengthGroup.class)
    })
    private String email;

    @NotBlank(message = "{password.empty}")
    @Size.List({
            @Size(min = 8, message = "{password.size.min}", groups = LengthGroup.class),
            @Size(max = 30, message = "{password.size.max}", groups = LengthGroup.class)
    })
    private String password;

    @NotBlank(message = "{confirm.password.empty}")
    private String confirmPassword;

    @NotBlank(message = "{phone.empty}")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "{phone.size}", groups = LengthGroup.class)
    private String phone;

    @NotBlank(message = "{address.empty}")
    private String address;

    @NotBlank(message = "{city.empty}")
    private String city;

    private String postalCode;
}

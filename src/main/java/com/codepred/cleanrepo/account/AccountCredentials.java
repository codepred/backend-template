package com.codepred.cleanrepo.account;

import com.codepred.cleanrepo.account.value_object.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "account")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCredentials implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Valid @Embedded
    private UserEmail email;

    @Valid @Embedded
    private EncodedPassword encodedPassword;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role must not be null")
    private UserRole role;

    @Valid @Embedded
    private Locked locked;

    public boolean isAccountLocked() {
        return this.locked.isLocked();
    }
}

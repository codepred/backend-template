package com.codepred.cleanrepo.account.command;

import com.codepred.cleanrepo.account.value_object.UserEmail;
import com.codepred.cleanrepo.account.value_object.Username;
import com.codepred.cleanrepo.common.SelfValidating;
import jakarta.validation.Valid;
import lombok.Getter;

@Getter
public final class HostRegisterCommand extends SelfValidating<HostRegisterCommand> {

    @Valid
    private final UserEmail email;

    @Valid
    private final Username name;

    private HostRegisterCommand(Json json) {
        this.email = new UserEmail(json.email);
        this.name = new Username(json.name);
        validateSelf();
    }

    public record Json(String email, String name) {
        public HostRegisterCommand toCommand() {
            return new HostRegisterCommand(this);
        }
    }
}

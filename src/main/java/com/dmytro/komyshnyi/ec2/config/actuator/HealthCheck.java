package com.dmytro.komyshnyi.ec2.config.actuator;

import com.dmytro.komyshnyi.ec2.entity.Permission;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@ConfigurationProperties("actuator.health")
@Getter
@Setter
public class HealthCheck implements HealthIndicator {

    private Set<Permission> permissionsToShowDetails;

    @Override
    public Health health() {
        if (hasAccess()) {
            return Health.up().withDetail("Secure information", "something important").build();
        }
        return Health.up().build();
    }

    private boolean hasAccess() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(auth -> permissionsToShowDetails
                        .stream()
                        .anyMatch(perm -> perm.getPermission().equals(auth)));
    }
}

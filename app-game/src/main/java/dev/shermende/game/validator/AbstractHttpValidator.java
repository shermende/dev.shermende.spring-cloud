package dev.shermende.game.validator;

import org.jetbrains.annotations.NotNull;
import org.springframework.validation.Validator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractHttpValidator implements Validator {
    protected static final String ID = "id";

    protected boolean isNotExistPathVariable(
        @NotNull String key
    ) {
        return !isExistPathVariable(key);
    }

    protected boolean isExistPathVariable(
        @NotNull String key
    ) {
        final HttpServletRequest request = getRequest();
        final Map<?, ?> map = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        return map.containsKey(key);
    }

    @NotNull
    protected String getPathVariable(
        @NotNull String key
    ) {
        final HttpServletRequest request = getRequest();
        final Map<?, ?> map = (Map<?, ?>) Objects.requireNonNull(request).getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        return Optional.ofNullable(map.get(key)).orElseThrow(IllegalArgumentException::new).toString();
    }

    @NotNull
    protected HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    @NotNull
    protected ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
    }

}


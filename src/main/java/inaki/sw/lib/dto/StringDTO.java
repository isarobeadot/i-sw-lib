package inaki.sw.lib.dto;

import lombok.With;

/**
 * @author inaki
 * @since 1.1
 */
@With
public record StringDTO(String value) implements IDTO {
}
